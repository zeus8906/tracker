package hu.home.sbv.service;

import hu.home.sbv.model.TaxType;
import hu.home.sbv.repo.TaxTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxTypeService implements BaseService<TaxType> {

    private TaxTypeRepo repo;

    public TaxTypeService(@Autowired TaxTypeRepo repo) {
        this.repo = repo;
    }

    @Override
    public TaxType add(TaxType newType) {
        return repo.save(newType);
    }

    @Override
    public void delete(long toRemove) {
        repo.deleteById(toRemove);
    }

    @Override
    public TaxType update(TaxType toUpdate) {
        return repo.save(toUpdate);
    }

    @Override
    public List<TaxType> getAll() {
        List<TaxType> all = new ArrayList<>();
        repo.findAll().forEach(type->all.add(type));
        return all;
    }

    @Override
    public TaxType getById(long id) {
        return repo.findById(id).orElse(null);
    }
}
