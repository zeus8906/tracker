package hu.home.sbv.controller;

import hu.home.sbv.model.Rsu;
import hu.home.sbv.model.TaxType;
import hu.home.sbv.service.BaseService;
import hu.home.sbv.service.RsuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rsu")
public class RsuController {
    private static final Logger LOG = LoggerFactory.getLogger("RSU Api Controller");

    private RsuService service;

    public RsuController(@Autowired RsuService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public @ResponseBody
    Rsu add(@RequestBody Rsu toAdd){
        LOG.info("Adding RSU: {}", toAdd);
        return service.add(toAdd);
    }

    @GetMapping("/get_all")
    public @ResponseBody
    List<Rsu> getAll(){
        return service.getAll();
    }

    @GetMapping("/get_by_id/{id}")
    public @ResponseBody Rsu getById(@PathVariable long id){
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody boolean delete(@PathVariable long id){
        service.delete(id);
        return true;
    }

    @PutMapping("/update")
    public @ResponseBody Rsu update(@RequestBody Rsu toUpdate){
        LOG.info("Updating RSU: {}", toUpdate);
        return service.update(toUpdate);
    }
}
