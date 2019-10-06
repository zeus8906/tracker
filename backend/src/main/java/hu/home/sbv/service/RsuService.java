package hu.home.sbv.service;

import hu.home.sbv.model.Rsu;
import hu.home.sbv.repo.RsuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RsuService extends BaseService<Rsu, Long> {

    public RsuService(@Autowired RsuRepo repo) {
        super(repo);
    }

}
