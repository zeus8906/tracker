package hu.home.sbv.service;

import hu.home.sbv.model.TaxType;
import hu.home.sbv.repo.TaxTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseService<T,V> {

    protected CrudRepository<T,V> repo;

    public BaseService(CrudRepository repo) {
        this.repo = repo;
    }

    public T add(T toAdd){
        return repo.save(toAdd);
    }

    public void delete(V toRemove){
        repo.deleteById(toRemove);
    }

    public T update(T toUpdate){
        return repo.save(toUpdate);
    }
    public List<T> getAll(){
        List<T> all = new ArrayList<>();
        repo.findAll().forEach(type->all.add(type));
        return all;
    }

    public T getById(V id){
        return repo.findById(id).orElse(null);
    }
}
