package hu.home.sbv.service;

import hu.home.sbv.model.*;
import hu.home.sbv.repo.RsuRepo;
import hu.home.sbv.repo.RsuSellRepo;
import hu.home.sbv.repo.RsuSellTransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class RsuService extends BaseService<Rsu, Long> {

    private static final Logger LOG = LoggerFactory.getLogger(RsuService.class.getName());

    private final TaxService taxService;
    private final RsuSellTransactionRepo transactionRepo;
    private final RsuSellRepo rsuSellRepo;

    public RsuService(@Autowired RsuRepo repo,
                      @Autowired TaxService taxService,
                      @Autowired RsuSellTransactionRepo transactionRepo,
                      @Autowired RsuSellRepo rsuSellRepo) {
        super(repo);
        this.taxService = taxService;
        this.rsuSellRepo = rsuSellRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Rsu add(Rsu toAdd){
        final Rsu saved = super.add(toAdd);
        taxService.saveTaxesForNewRsu(saved);
        return saved;
    }

    @Override
    public Rsu update(Rsu toUpdate){
        final Rsu updated = super.update(toUpdate);
        taxService.updateTaxesForRsu(updated);
        return updated;
    }

    @Override
    public void delete(Long toDelete){
      super.delete(toDelete);
      taxService.deleteTaxesForRsu(toDelete);
    }

    public SellResponseDto sell(SellTransactionRequest request) {
        final SellResponseDto responseDto = new SellResponseDto();
        RsuSellTransaction transaction = buildRsuSellTransaction(request);
        LOG.info("Transaction object build up: {}", transaction);
        saveTransaction(transaction);
        responseDto.setSuccess(true);
        responseDto.setSellItems(transaction.getItems());
        return responseDto;
    }

    @Transactional
    protected void saveTransaction(RsuSellTransaction transaction) {
        LOG.info("Start saving transaction");
        List<RsuSell> savedItems = new ArrayList<>();
        for(int index=0; index<transaction.getItems().size(); ++index){
            final RsuSell current = transaction.getItems().get(index);
            LOG.info("Saving sell Item: {}", current);
            savedItems.add(rsuSellRepo.save(current));
        }
        transaction.setItems(savedItems);
        LOG.info("Save transaction itself");
        transactionRepo.save(transaction);
        LOG.info("Sell transaction saving is done: {}", transactionRepo);
    }

    private RsuSellTransaction buildRsuSellTransaction(SellTransactionRequest request) {
        RsuSellTransaction transaction = new RsuSellTransaction();
        transaction.setIncome(request.getIncome());
        transaction.setTransactionFee(request.getFee());
        for(int rsuId: request.getItems().keySet()){
            RsuSell sellItem = buildRsuSell(request, rsuId);
            transaction.addItem(sellItem);
        }
        return transaction;
    }

    private RsuSell buildRsuSell(SellTransactionRequest request, int rsuId) {
        RsuSell sellItem = new RsuSell();
        sellItem.setSource(repo.findById((long)rsuId).orElse(null));
        sellItem.setCount(request.getItems().get(rsuId));
        sellItem.setUsdhuf(request.getUsdhuf());
        sellItem.setValue(request.getSellPrice());
        sellItem.setSellDate(request.getSellDate());
        return sellItem;
    }

    @Transactional
    public void createTaxesForSellTransaction(SellResponseDto sellResponse) {
        for(RsuSell sellItem: sellResponse.getSellItems()){
            taxService.saveTaxesForRsuSell(sellItem);
        }
    }
}
