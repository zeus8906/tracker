package hu.home.sbv.service;

import hu.home.sbv.model.Tax;
import hu.home.sbv.repo.TaxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class TaxService extends BaseService<Tax, Long> {

    public TaxService(@Autowired TaxRepo repo) {
        super(repo);
    }
}
