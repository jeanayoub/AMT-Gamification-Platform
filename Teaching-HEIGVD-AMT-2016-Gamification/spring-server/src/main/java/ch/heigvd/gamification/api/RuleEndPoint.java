/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.RuleGet;
import ch.heigvd.gamification.api.dto.RulePost;
import ch.heigvd.gamification.dao.ApplicationRepository;
import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.dao.PointScaleRepository;
import ch.heigvd.gamification.dao.RuleRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.RuleCondition;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.User;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ch.heigvd.gamification.dao.RuleConditionRepository;

@RestController
public class RuleEndPoint implements RulesApi {
    
    RuleRepository ruleRepository;
    ApplicationRepository applicationRepository;
    BadgesRepository badgesRepository;
    PointScaleRepository pointScaleRepository;
    RuleConditionRepository conditionRepository;
    
    @Autowired
    RuleEndPoint(RuleConditionRepository conditionRepository, RuleRepository ruleRepository, ApplicationRepository applicationRepository, BadgesRepository badgesRepository, PointScaleRepository pointScaleRepository) {
            this.conditionRepository = conditionRepository;
            this.ruleRepository = ruleRepository;
            this.applicationRepository = applicationRepository;
            this.badgesRepository = badgesRepository;
            this.pointScaleRepository = pointScaleRepository;
            
    }

    @Override
    public ResponseEntity<List<RuleGet>> rulesGet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<Void> rulesIdDelete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<Object> rulesIdGet(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<Object> rulesIdPut(Long id, RulePost ruleDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping(value = "/rules", method = RequestMethod.POST)
    public ResponseEntity<Void> rulesPost(@RequestBody RulePost ruleDTO, @RequestHeader String token) {
        
        Application appTmp = applicationRepository.findByName(token);
        Badge badgeTmp = null;
        PointScale pointScaleTmp = null;
        List<RuleCondition> listCondition = new ArrayList<>();
              
        // If we found an application corresponging to the name received.
        if(appTmp != null){

            if(badgesRepository.exists(ruleDTO.getAwardBadgeId()))
               badgeTmp = badgesRepository.findOne(ruleDTO.getAwardBadgeId()); 

            if(pointScaleRepository.exists(ruleDTO.getAwardPointScaleId()))
                pointScaleTmp = pointScaleRepository.findOne(ruleDTO.getAwardPointScaleId());
            
            
            // if one of the two is set at least we can continue.
            if(badgeTmp != null || pointScaleTmp != null){
                
                // Create thhe new rule and add it to the DB.
                Rule rule = new Rule(appTmp, pointScaleTmp, badgeTmp, ruleDTO.getEventType(), ruleDTO.getPoint());
                ruleRepository.save(rule);
                
                for(String str : ruleDTO.getConditions()){
                    RuleCondition ruleConditionTmp = new RuleCondition(rule, str); 
                    conditionRepository.save(ruleConditionTmp);
                    //listCondition.add(cacaTmp);
                }
                
                //rule.setListCondition(listCondition);
                
                

                URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(rule.getId()).toUri();
                return ResponseEntity.created(location).build();

            }
            
           /* //Update all the users Awards
            for (User user : appTmp.getUserList()){
                
            }*/       
        }
                
        return ResponseEntity.created(null).build();
    }


    
    
    
}
