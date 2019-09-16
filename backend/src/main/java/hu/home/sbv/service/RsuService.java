package hu.home.sbv.service;

import hu.home.sbv.model.Rsu;
import hu.home.sbv.repo.RsuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RsuService implements BaseService<Rsu> {

    private RsuRepo repo;

    public RsuService(@Autowired RsuRepo repo) {
        this.repo = repo;
    }

    @Override
    public Rsu add(Rsu newRsu) {
        return repo.save(newRsu);
    }

    @Override
    public void delete(long toRemove) {
      repo.deleteById(toRemove);
    }

    @Override
    public Rsu update(Rsu toUpdate) {
        return repo.save(toUpdate);
    }

    @Override
    public List<Rsu> getAll() {
        List<Rsu> all = new ArrayList<>();
        repo.findAll().forEach(rsu->all.add(rsu));
        return all;
    }

    @Override
    public Rsu getById(long id) {
        return repo.findById(id).orElse(null);
    }
}
