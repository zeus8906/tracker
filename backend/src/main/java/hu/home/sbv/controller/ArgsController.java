package hu.home.sbv.controller;

import hu.home.sbv.repo.ArgsRepo;
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
        List<String> queryResult = new ArrayList<>(repo.getTaxBase());
        if(queryResult.size() != 1){
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal(queryResult.get(0));
        }
    }

    @PutMapping("/args/tax_base/{newVal}")
    public BigDecimal updateTaxBase(@PathVariable BigDecimal newVal){
        List<Object> queryResult = new ArrayList<>(repo.getTaxBase());
        if(queryResult.size() == 0){
            repo.createTaxBase(newVal.toString());
        } else {
            repo.updateTaxBase(newVal.toString());
        }
        return newVal;
    }
}
