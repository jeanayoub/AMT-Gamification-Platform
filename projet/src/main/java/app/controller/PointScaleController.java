/*
 * 
 */
package app.controller;


import app.model.PointScale;
import app.model.PointScaleRepository;
import java.net.URI;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * This class offers REST standard operations for a PointScale.
 *
 * @author M-H. Aghamahdi, J. Ayoub, S. Baehler, M. Monzione
 * @date 14 Nov 2016
 */
@RestController
public class PointScaleController {
    
    PointScaleRepository pointScaleRepository;
    
    
    @Autowired
    PointScaleController(PointScaleRepository pointScaleRepository) {
            this.pointScaleRepository = pointScaleRepository;
    }

    @RequestMapping("/pointScale/{id}")
    public PointScale badge(@PathVariable("id") long id) {
        return  pointScaleRepository.findOne(id);
    }

    @RequestMapping("/pointScales")
    public LinkedList badges() {
        return  pointScaleRepository.findAll();
    }

    @RequestMapping(value = "/pointScale", method = RequestMethod.POST)
    public ResponseEntity<?> doPost(@RequestBody PointScale pointScale, HttpServletResponse response) {
        
        pointScaleRepository.save(pointScale);
        
        response.setStatus(HttpServletResponse.SC_CREATED);

        
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(pointScale.getID()).toUri();

        return ResponseEntity.created(location).build();
    }
    
    
    @RequestMapping(value = "/pointScale/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> doPut(@PathVariable("id") long id, @RequestBody PointScale pointScale, HttpServletResponse response) {
    
        pointScaleRepository.findOne(id).setName(pointScale.getName());
        pointScaleRepository.findOne(id).setDescription(pointScale.getDescription());
        pointScaleRepository.findOne(id).setIcon(pointScale.getIcon());
        
        response.setStatus(HttpServletResponse.SC_CREATED);
       
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }


    /**
     * delete le pointScale passé en parametre
     *
     * @date 15 Nov 2016
     * @param id
     * @return delete pointScale avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping(value = "/pointScale/{id}", method = RequestMethod.DELETE)
    public ResponseEntity doDelete(@PathVariable("id") long id, HttpServletResponse response) {
        
        pointScaleRepository.delete(id);
        
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
       
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand().toUri();

        return ResponseEntity.created(location).build();
        
    }
    
     
    
}
