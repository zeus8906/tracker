package hu.home.sbv.service;

import hu.home.sbv.model.Tax;
import hu.home.sbv.repo.TaxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxService extends BaseService<Tax, Long> {

    public TaxService(@Autowired TaxRepo repo) {
        super(repo);
    }
}
