/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.PointScaleGet;
import ch.heigvd.gamification.api.dto.PointScalePost;
import ch.heigvd.gamification.dao.ApplicationRepository;
import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.dao.PointScaleRepository;
import ch.heigvd.gamification.dao.RuleRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.PointScale;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PointScaleEndPoint implements PointScalesApi{

    RuleRepository ruleRepository;
    ApplicationRepository applicationRepository;
    BadgesRepository badgesRepository;
    PointScaleRepository pointScaleRepository;
    
    @Autowired
    PointScaleEndPoint(RuleRepository ruleRepository, ApplicationRepository applicationRepository, BadgesRepository badgesRepository, PointScaleRepository pointScaleRepository) {
            this.ruleRepository = ruleRepository;
            this.applicationRepository = applicationRepository;
            this.badgesRepository = badgesRepository;
            this.pointScaleRepository = pointScaleRepository;
            
    }
    
    
    
    @Override
    public ResponseEntity<List<PointScaleGet>> pointScalesGet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<Void> pointScalesIdDelete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<Object> pointScalesIdGet(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<Object> pointScalesIdPut(Long id, PointScalePost pointScaleDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping(value = "/pointScales", method = RequestMethod.POST)
    public ResponseEntity<Void> pointScalesPost(@RequestBody PointScalePost pointScaleDTO, @RequestHeader String token) {
        
        Application appTmp = applicationRepository.findByName(token);
        
        PointScale pointScaleToCreate = new PointScale(appTmp, pointScaleDTO.getName(), pointScaleDTO.getMaxPoint());
        pointScaleRepository.save(pointScaleToCreate);
        
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(pointScaleToCreate.getId()).toUri();

        return ResponseEntity.created(location).build();
        
        
    }
    
}
