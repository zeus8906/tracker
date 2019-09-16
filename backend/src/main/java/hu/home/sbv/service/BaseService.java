package hu.home.sbv.service;

import hu.home.sbv.model.TaxType;
import hu.home.sbv.repo.TaxTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BaseService<T> {

    T add(T toAdd);
    void delete(long toRemove);
    T update(T toUpdate);
    List<T> getAll();
    T getById(long id);
}
