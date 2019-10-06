package hu.home.sbv.service;

import hu.home.sbv.model.Rsu;
import hu.home.sbv.model.Tax;
import hu.home.sbv.model.TaxType;
import hu.home.sbv.repo.ArgsRepo;
import hu.home.sbv.repo.TaxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaxService extends BaseService<Tax, Long> {

    private final ArgsRepo argsRepo;
    private final TaxTypeService taxTypeService;

    public TaxService(@Autowired TaxRepo repo,
                      @Autowired TaxTypeService taxTypeService,
                      @Autowired ArgsRepo args) {
        super(repo);
        this.taxTypeService = taxTypeService;
        this.argsRepo = args;
    }

    @Override
    public Tax add(Tax toAdd) {
        toAdd.setDueDate(calculateDueDate(toAdd.getRsu().getSettleDate()));
        return super.add(toAdd);
    }

    private Date calculateDueDate(Date settleDate) {
        LocalDate date = settleDate.toInstant().atZone(ZoneId.of("Europe/Budapest")).toLocalDate();
        return Date.from(date.plusMonths(1)
                .withDayOfMonth(12).atStartOfDay().toInstant(ZoneOffset.UTC));
    }

    private List<Tax> createTaxesForRSUVest(Rsu vested) {
        List<Tax> vestTaxes = new ArrayList<>();
        BigDecimal taxBase = new BigDecimal(argsRepo.getTaxBase());
        taxTypeService.getAll().stream().forEach(taxType -> {
            final Tax newTax = new Tax();
            newTax.setDueDate(calculateDueDate(vested.getSettleDate()));
            newTax.setRsu(vested);
            newTax.setValue(calculateTaxValue(vested, taxBase, taxType));
            newTax.setPaid(false);
            newTax.setTaxType(taxType);
            vestTaxes.add(newTax);
        });
        return vestTaxes;
    }

    private BigDecimal calculateTaxValue(Rsu vested, BigDecimal taxBase, TaxType taxType) {
        return vested.getValue()
                .multiply(BigDecimal.valueOf(vested.getCount()))
                .multiply(vested.getUsdhuf())
                .multiply(taxBase)
                .multiply(taxType.getPercentage()
                        .divide(new BigDecimal(100)));
    }

    private List<Tax> createTaxesForRSUSell(Rsu vested) {
        List<Tax> sellTaxes = new ArrayList<>();
        BigDecimal taxBase = new BigDecimal(argsRepo.getTaxBase());
        taxTypeService.getAll()
                .stream()
                .filter(type -> type.isOnSell())
                .forEach(taxType -> {
                    final Tax newTax = new Tax();
                    newTax.setDueDate(calculateDueDate(vested.getSettleDate()));
                    newTax.setRsu(vested);
                    newTax.setValue(calculateTaxValue(vested, taxBase, taxType));
                    newTax.setPaid(false);
                    newTax.setTaxType(taxType);
                    sellTaxes.add(newTax);
                });
        return sellTaxes;
    }

}
