package hu.home.sbv.service;

import hu.home.sbv.model.Rsu;
import hu.home.sbv.model.RsuSell;
import hu.home.sbv.model.Tax;
import hu.home.sbv.model.TaxType;
import hu.home.sbv.repo.ArgsRepo;
import hu.home.sbv.repo.TaxRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaxService extends BaseService<Tax, Long> {

    private static final Logger LOG = LoggerFactory.getLogger(TaxService.class.getName());

    private final ArgsRepo argsRepo;
    private final TaxTypeService taxTypeService;
    private final TaxRepo repo;

    public TaxService(@Autowired TaxRepo repo,
                      @Autowired TaxTypeService taxTypeService,
                      @Autowired ArgsRepo args) {
        super(repo);
        this.repo = repo;
        this.taxTypeService = taxTypeService;
        this.argsRepo = args;
    }

    @Override
    public Tax add(Tax toAdd) {
        return add(toAdd, false);
    }

    private Date calculateDueDate(Date settleDate, boolean isOnSell) {
        final LocalDate date = settleDate.toInstant()
                .atZone(ZoneId.of("Europe/Budapest"))
                .toLocalDate();
        return isOnSell ?
                Date.from(date.plusYears(1l)
                        .withMonth(5)
                        .withDayOfMonth(21)
                        .atStartOfDay()
                        .toInstant(ZoneOffset.UTC)) :
                Date.from(date.plusMonths(1)
                        .withDayOfMonth(12)
                        .atStartOfDay()
                        .toInstant(ZoneOffset.UTC));
    }

    void saveTaxesForNewRsu(Rsu newRsu){
        List<Tax> taxes = createTaxesForRSUVest(newRsu);
        taxes.forEach(tax -> {
            LOG.info("Adding new Tax: {}", tax);
            add(tax);
        });
    }

    void saveTaxesForRsuSell(RsuSell sell){
        List<Tax> taxes  = createTaxesForRSUSell(sell);
        taxes.forEach(tax -> {
            LOG.info("Adding new Tax: {}", tax);
            add(tax, true);
        });
    }

    private Tax add(Tax toAdd, boolean isSellTax){
        toAdd.setDueDate(calculateDueDate(toAdd.getRsu().getSettleDate(), true));
        return super.add(toAdd);
    }

    private List<Tax> createTaxesForRSUVest(Rsu vested) {
        List<Tax> vestTaxes = new ArrayList<>();
        BigDecimal taxBase = new BigDecimal(argsRepo.getTaxBase());
        for (TaxType taxType : taxTypeService.getAll()) {
            vestTaxes.add(buildNewTax(vested, taxBase, taxType));
        }
        return vestTaxes;
    }

    private BigDecimal calculateTaxValue(Rsu vested, BigDecimal taxBase, TaxType taxType) {
        final BigDecimal result = vested.getValue()
                .multiply(BigDecimal.valueOf(vested.getCount()))
                .multiply(vested.getUsdhuf())
                .multiply(taxBase.divide(new BigDecimal(100.00)))
                .multiply(taxType.getPercentage()
                        .divide(new BigDecimal(100.00)));
        LOG.info("New Tax: {} X {} X {} X {} X {} = {}", vested.getValue(), BigDecimal.valueOf(vested.getCount()), vested.getUsdhuf(),taxBase.divide(new BigDecimal(100.00)), taxType.getPercentage()
                .divide(new BigDecimal(100.00)), result);
        return result;
    }

    private List<Tax> createTaxesForRSUSell(RsuSell sold) {
        List<Tax> sellTaxes = new ArrayList<>();
        taxTypeService.getAll()
                .stream()
                .filter(TaxType::isOnSell)
                .forEach(taxType -> {
                    sellTaxes.add(buildTaxForSell(sold, taxType));
                });
        return sellTaxes;
    }

    private Tax buildNewTax(Rsu vested, BigDecimal taxBase, TaxType taxType) {
        final Tax newTax = new Tax();
        newTax.setDueDate(calculateDueDate(vested.getSettleDate(), false));
        newTax.setRsu(vested);
        newTax.setValue(calculateTaxValue(vested, taxBase, taxType));
        newTax.setPaid(false);
        newTax.setTaxType(taxType);
        return newTax;
    }

    private Tax buildTaxForSell(RsuSell sold, TaxType taxType) {
        final Tax newTax = new Tax();
        newTax.setDueDate(calculateDueDate(sold.getSource().getSettleDate(), true));
        newTax.setRsu(sold.getSource());
        newTax.setValue(BigDecimal.valueOf(sold.getCount())
                .multiply(sold.getValue().subtract(sold.getSource().getValue()))
        .multiply(taxType.getPercentage().divide(BigDecimal.valueOf(100.00), RoundingMode.HALF_UP)));
        newTax.setPaid(false);
        newTax.setTaxType(taxType);
        return newTax;
    }

    void updateTaxesForRsu(Rsu updated) {
        List<Tax> taxesToUpdate = repo.findByRsuId(updated.getId());
        BigDecimal taxBase = new BigDecimal(argsRepo.getTaxBase());
        taxesToUpdate.forEach(tax ->{
            tax.setValue(calculateTaxValue(updated, taxBase, tax.getTaxType()));
            tax.setDueDate(calculateDueDate(updated.getSettleDate(), false));
            repo.save(tax);
        });
    }

    public void deleteTaxesForRsu(Long toDelete) {
        for (Tax tax : repo.findByRsuId(toDelete)) {
            repo.delete(tax);
        }
    }
}
