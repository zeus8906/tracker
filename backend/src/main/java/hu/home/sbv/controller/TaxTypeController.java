package hu.home.sbv.controller;

import hu.home.sbv.model.TaxType;
import hu.home.sbv.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tax_type")
public class TaxTypeController {
    private static final Logger LOG = LoggerFactory.getLogger("Api Controller");

    private BaseService<TaxType> service;

    public TaxTypeController(@Autowired BaseService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public @ResponseBody  TaxType add(@RequestBody TaxType toAdd){
        LOG.info("Adding TaxType: {}", toAdd);
        return service.add(toAdd);
    }

    @GetMapping("/get_all")
    public @ResponseBody List<TaxType> getAll(){
        return service.getAll();
    }

    @GetMapping("/get_by_id/{id}")
    public @ResponseBody TaxType getById(@PathVariable long id){
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody boolean delete(@PathVariable long id){
        service.delete(id);
        return true;
    }

    @PutMapping("/update")
    public @ResponseBody TaxType update(TaxType toUpdate){
        return service.update(toUpdate);
    }
}
