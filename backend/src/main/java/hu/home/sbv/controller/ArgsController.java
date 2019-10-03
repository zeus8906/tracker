package hu.home.sbv.controller;

import hu.home.sbv.repo.ArgsRepo;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ArgsController {

    private ArgsRepo repo;

    public ArgsController(@Autowired ArgsRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/args/tax_base")
    public BigDecimal getTaxBase(){
        String queryResult = repo.getTaxBase();
        if(StringUtils.isNullOrEmpty(queryResult)){
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal(queryResult);
        }
    }

    @PutMapping("/args/tax_base/{newVal}")
    public BigDecimal updateTaxBase(@PathVariable BigDecimal newVal){
        String queryResult = repo.getTaxBase();
        if(StringUtils.isNullOrEmpty(queryResult)){
            repo.createTaxBase(newVal.toString());
        } else {
            repo.updateTaxBase(newVal.toString());
        }
        return newVal;
    }
}
