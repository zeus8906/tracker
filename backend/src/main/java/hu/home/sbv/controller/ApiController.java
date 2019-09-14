package hu.home.sbv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger LOG = LoggerFactory.getLogger("Api Controller");

    @RequestMapping("/hello")
    public @ResponseBody  String hello(){
        LOG.info("Called endpoint: Hello");
        return "Hello Rest!";
    }
}
