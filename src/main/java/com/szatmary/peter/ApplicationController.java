package com.szatmary.peter;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nue on 22.6.2016.
 */
@RestController
public class ApplicationController {

    @RequestMapping(value = "/hello")
    public @ResponseBody
    String hello() {
        return "hello";
    }

    @RequestMapping(value = "/car")
    public @ResponseBody Car car(@RequestParam("owner name") String owner) {
        return new Car("BMW", new Owner(owner));
    }

}
