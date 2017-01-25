/**
 * Author : Aghamahdi Mohammad Hossein
 *          Ayoub jean
 *          Baehler Simon
 *          Monzione Marco
 * 
 * Project : AMT-Gamification-platform
 * 
 * Date : 25.01.2017
 *          
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

    RulesRepository rulesRepository;
    ApplicationsRepository applicationsRepository;
    BadgesRepository badgesRepository;
    PointScalesRepository pointScaleRepository;
    
    @Autowired
    PointScaleEndPoint(RulesRepository ruleRepository, ApplicationsRepository applicationRepository, BadgesRepository badgesRepository, PointScalesRepository pointScaleRepository) {
            this.rulesRepository = ruleRepository;
            this.applicationsRepository = applicationRepository;
            this.badgesRepository = badgesRepository;
            this.pointScaleRepository = pointScaleRepository;
            
    }


    @Override
    @RequestMapping(value = "/pointScales", method = RequestMethod.GET)
    public ResponseEntity<List<PointScaleGet>> pointScalesGet(@RequestHeader String token) {

        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            LinkedList<PointScale> listTmp = pointScaleRepository.findByApplicationId(appTmp.getId());
            LinkedList<PointScaleGet> listTmpDtoGet = new LinkedList<PointScaleGet>();

            for(PointScale ps : listTmp){
                PointScaleGet tmpPS = new PointScaleGet();
                tmpPS.setId(ps.getId());
                tmpPS.setName(ps.getName());
                tmpPS.setApllicationName(ps.getApplication().getName());

                listTmpDtoGet.add(tmpPS);
            }

            return ResponseEntity.ok().body(listTmpDtoGet);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Override
    @RequestMapping(value = "/pointScales/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> pointScalesIdGet(@PathVariable Long id, @RequestHeader String token) {
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            if(pointScaleRepository.exists(id)){
                
                PointScale pointScaleTmp = pointScaleRepository.findById(id);
                PointScaleGet tmpPS = new PointScaleGet();
                tmpPS.setId(pointScaleTmp.getId());
                tmpPS.setName(pointScaleTmp.getName());
                tmpPS.setApllicationName(pointScaleTmp.getApplication().getName());
                
                return ResponseEntity.ok().body(tmpPS);
            }
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

    }


    @Override
    public ResponseEntity<Object> pointScalesIdPut(@PathVariable Long id, @RequestBody PointScalePost pointScaleDTO, @RequestHeader String token) {
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            if(pointScaleRepository.exists(id)){

                PointScale pointScaleExisting = pointScaleRepository.findOne(id);

                pointScaleExisting.setName(pointScaleDTO.getName());

                pointScaleRepository.save(pointScaleExisting);

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(id).toUri();

                return ResponseEntity.status(HttpStatus.OK).body(location);
            }
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Override
    @RequestMapping(value = "/pointScales/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> pointScalesIdDelete(Long id, @RequestHeader String token) {
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            if(pointScaleRepository.exists(id)){
                pointScaleRepository.delete(id);
                return ResponseEntity.ok().body(null);
            }
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Override
    @RequestMapping(value = "/pointScales", method = RequestMethod.POST)
    public ResponseEntity<Void> pointScalesPost(@RequestBody PointScalePost pointScaleDTO, @RequestHeader String token) {
        
         Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
        
            PointScale pointScaleToCreate = new PointScale(appTmp, pointScaleDTO.getName());
            pointScaleRepository.save(pointScaleToCreate);
            
            appTmp.getPointScaleList().add(pointScaleToCreate);

            URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(pointScaleToCreate.getId()).toUri();

            return ResponseEntity.created(location).build();
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        
    }

    
}
