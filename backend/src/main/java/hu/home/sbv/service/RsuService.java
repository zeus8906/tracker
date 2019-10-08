package hu.home.sbv.service;

import hu.home.sbv.model.Rsu;
import hu.home.sbv.repo.RsuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RsuService extends BaseService<Rsu, Long> {

    private final TaxService taxService;

    public RsuService(@Autowired RsuRepo repo, @Autowired TaxService taxService) {
        super(repo);
        this.taxService = taxService;
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
}
