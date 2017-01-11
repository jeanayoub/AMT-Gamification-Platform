/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.PointScaleGet;
import ch.heigvd.gamification.api.dto.PointScalePost;
import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.PointScale;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ch.heigvd.gamification.dao.ApplicationsRepository;
import ch.heigvd.gamification.dao.PointScalesRepository;
import ch.heigvd.gamification.dao.RulesRepository;

@RestController
public class PointScaleEndPoint implements PointScalesApi{

    RulesRepository ruleRepository;
    ApplicationsRepository applicationRepository;
    BadgesRepository badgesRepository;
    PointScalesRepository pointScaleRepository;
    
    @Autowired
    PointScaleEndPoint(RulesRepository ruleRepository, ApplicationsRepository applicationRepository, BadgesRepository badgesRepository, PointScalesRepository pointScaleRepository) {
            this.ruleRepository = ruleRepository;
            this.applicationRepository = applicationRepository;
            this.badgesRepository = badgesRepository;
            this.pointScaleRepository = pointScaleRepository;
            
    }




    @Override
    @RequestMapping(value = "/pointScales", method = RequestMethod.GET)
    public ResponseEntity<List<PointScaleGet>> pointScalesGet() {

        LinkedList<PointScale> listTmp = pointScaleRepository.findAll();
        LinkedList<PointScaleGet> listTmpDtoGet = new LinkedList<PointScaleGet>();

        for(PointScale ps : listTmp){
            PointScaleGet tmpPS = new PointScaleGet();
            tmpPS.setId(ps.getId());
            tmpPS.setName(ps.getName());
            tmpPS.setApllicationName(ps.getApplication().getName());
            //tmpPS.setApplicationId(ps.getApplication().getId());
            
            
            listTmpDtoGet.add(tmpPS);
        }

        return ResponseEntity.ok().body(listTmpDtoGet);
    }

    @Override
    @RequestMapping(value = "/pointScales/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> pointScalesIdGet(@PathVariable Long id) {
        if(pointScaleRepository.exists(id)){
            return ResponseEntity.ok().body(pointScaleRepository.findOne(id));
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }

    @Override
    @RequestMapping(value = "/pointScales/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> pointScalesIdPut(@ApiParam(value = "id of the pointScale to modify", required = true) @PathVariable("id") Long id, @ApiParam(value = "The new values for an existing pointScale", required = true) @RequestBody PointScalePost pointScaleDTO) {
        if(pointScaleRepository.exists(id)){
            pointScaleRepository.findOne(id).setName(pointScaleDTO.getName());
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();

            return ResponseEntity.ok(location);
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @RequestMapping(value = "/pointScales/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> pointScalesIdDelete(Long id) {
        if(pointScaleRepository.exists(id)){
            pointScaleRepository.delete(id);
            return ResponseEntity.ok().body(null);
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /*@Override
    OLD
    @RequestMapping(value = "/pointScales/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> pointScalesIdPut(@PathVariable Long id, @RequestBody PointScalePost pointScaleDTO, @RequestHeader String token) {
        if(pointScaleRepository.exists(id)){
            
            pointScaleRepository.findOne(id).setName(pointScaleDTO.getName());            
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();

            return ResponseEntity.ok(location);
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }*/

    @Override
    @RequestMapping(value = "/pointScales", method = RequestMethod.POST)
    public ResponseEntity<Void> pointScalesPost(@RequestBody PointScalePost pointScaleDTO, @RequestHeader String token) {
        
        Application appTmp = applicationRepository.findByName(token);
        
        PointScale pointScaleToCreate = new PointScale(appTmp, pointScaleDTO.getName());
        pointScaleRepository.save(pointScaleToCreate);
        
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(pointScaleToCreate.getId()).toUri();

        return ResponseEntity.created(location).build();
        
    }
}
