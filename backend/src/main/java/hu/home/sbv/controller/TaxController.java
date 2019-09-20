package hu.home.sbv.controller;

import hu.home.sbv.model.Tax;
import hu.home.sbv.service.TaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tax")
public class TaxController {
    private static final Logger LOG = LoggerFactory.getLogger("Tax Api Controller");

    private TaxService service;

    public TaxController(@Autowired TaxService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public @ResponseBody
    Tax add(@RequestBody Tax toAdd){
        LOG.info("Adding TaxType: {}", toAdd);
        return service.add(toAdd);
    }

    @GetMapping("/get_all")
    public @ResponseBody
    List<Tax> getAll(){
        return service.getAll();
    }

    @GetMapping("/get_by_id/{id}")
    public @ResponseBody Tax getById(@PathVariable long id){
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody boolean delete(@PathVariable long id){
        service.delete(id);
        return true;
    }

    @PutMapping("/update")
    public @ResponseBody Tax update(@RequestBody Tax toUpdate){
        return service.update(toUpdate);
    }

}


