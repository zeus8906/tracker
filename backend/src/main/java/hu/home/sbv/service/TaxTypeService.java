package hu.home.sbv.service;

import hu.home.sbv.model.TaxType;
import hu.home.sbv.repo.TaxTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxTypeService extends BaseService<TaxType, Long> {

    public TaxTypeService(@Autowired TaxTypeRepo repo) {
        super(repo);
    }
}
