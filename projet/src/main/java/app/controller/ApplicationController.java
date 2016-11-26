/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.Application;
import app.model.ApplicationRepository;
import java.net.URI;
import java.util.LinkedList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class ApplicationController {
    
    ApplicationRepository applicationRepository;
    
    
    @Autowired
    ApplicationController(ApplicationRepository applicationRepository) {
            this.applicationRepository = applicationRepository;
    }
    
    @RequestMapping("/application/{id}")
    public Application application(@PathVariable("id") long id) {
        Application app = applicationRepository.findOne(id);
        return app;
    }
    
    @RequestMapping("/applications")
    public LinkedList applications() {
        return applicationRepository.findAll();
    }
    
    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public ResponseEntity doPost(@RequestBody Application application, HttpServletResponse response) {
 
        applicationRepository.save(application);
    
        response.setStatus(HttpServletResponse.SC_CREATED);

        
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(application.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    
    @RequestMapping(value = "/application/{id}", method = RequestMethod.PUT)
    public ResponseEntity doPut(@PathVariable("id") Long id , HttpServletResponse response, @RequestBody Application application) {
        
        applicationRepository.findOne(id).setName(application.getName());
        applicationRepository.findOne(id).setDescription(application.getDescription());
      
        response.setStatus(HttpServletResponse.SC_CREATED);
       
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }
    
    @RequestMapping(value = "/application/{id}", method = RequestMethod.DELETE)
    public ResponseEntity doDelete(@PathVariable("id") long id, HttpServletResponse response) {
        
        applicationRepository.delete(id);
        
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
       
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand().toUri();

        return ResponseEntity.created(location).build();
    }

    
}
