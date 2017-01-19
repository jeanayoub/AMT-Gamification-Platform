/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.RuleGet;
import ch.heigvd.gamification.api.dto.RulePost;
import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.model.Rule;

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
import ch.heigvd.gamification.dao.RuleConditionsRepository;
import ch.heigvd.gamification.dao.RulesRepository;

@RestController
public class RuleEndPoint implements RulesApi {

    RulesRepository rulesRepository;
    ApplicationsRepository applicationsRepository;
    BadgesRepository badgesRepository;
    PointScalesRepository pointScaleRepository;
    RuleConditionsRepository conditionsRepository;

    @Autowired
    RuleEndPoint(RuleConditionsRepository conditionRepository, RulesRepository ruleRepository, ApplicationsRepository applicationRepository, BadgesRepository badgesRepository, PointScalesRepository pointScaleRepository) {
        this.conditionsRepository = conditionRepository;
        this.rulesRepository = ruleRepository;
        this.applicationsRepository = applicationRepository;
        this.badgesRepository = badgesRepository;
        this.pointScaleRepository = pointScaleRepository;

    }

    @Override
    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public ResponseEntity<List<RuleGet>> rulesGet(@RequestHeader String token) {

        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            LinkedList<Rule> listTmp = rulesRepository.findByApplicationId(appTmp.getId());
            LinkedList<RuleGet> listTmpDtoGet = new LinkedList<RuleGet>();

            for (Rule rule : listTmp) {
                RuleGet tmpRule = new RuleGet();
                tmpRule.setApplicationId(rule.getApplication().getId());
                tmpRule.setApplicationName(rule.getApplication().getName());
                
                // We check if the rule has a badge and/or a pointScale.
                if(rule.getBadge() != null)
                    tmpRule.setAwardBadgeId(rule.getBadge().getId());
                if(rule.getPointScale() != null)
                    tmpRule.setAwardPointScaleId(rule.getPointScale().getId());
                
                tmpRule.setEventType(rule.getTypeEvent());
                tmpRule.setNumberOfPoint(rule.getPoint());
                listTmpDtoGet.add(tmpRule);
            }

            return ResponseEntity.ok().body(listTmpDtoGet);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Override
    @RequestMapping(value = "/rules/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> rulesIdDelete(@PathVariable  Long id, @RequestHeader String token) {
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            if(rulesRepository.exists(id)){
                rulesRepository.delete(id);
                return ResponseEntity.ok().body(null);
            }
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Override
    @RequestMapping(value = "/rules/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> rulesIdGet(@PathVariable  Long id, @RequestHeader String token) {
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            if(rulesRepository.exists(id)){
                return ResponseEntity.ok().body(rulesRepository.findOne(id));
            }
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Override
    @RequestMapping(value = "/rules/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> rulesIdPut(@PathVariable Long id, @RequestHeader String token, @RequestBody RulePost ruleDTO) {
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            if(rulesRepository.exists(id)){
                Rule tmpRule = rulesRepository.findOne(id);
                tmpRule.setPoint(ruleDTO.getPoint());
                tmpRule.setTypeEvent(ruleDTO.getEventType());

                //TODO do this
                //tmpRule.setPointScale(ruleDTO.getPointScale);
                //tmpRule.setBadge(ruleDTO.getBage);
                PointScale tmpPS = pointScaleRepository.findOne(ruleDTO.getAwardPointScaleId());
                tmpRule.setPointScale(tmpPS);

                Badge tmpBadge = badgesRepository.findOne(ruleDTO.getAwardBadgeId());
                tmpRule.setBadge(tmpBadge);

                ruleDTO.getAwardBadgeId();
                ruleDTO.getAwardPointScaleId();
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(id).toUri();

                return ResponseEntity.ok(location);
            }
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Override
    @RequestMapping(value = "/rules", method = RequestMethod.POST)
    public ResponseEntity<Void> rulesPost(@RequestBody RulePost ruleDTO, @RequestHeader String token) {

        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            Badge badgeTmp = null;
            PointScale pointScaleTmp = null;

           // List<RuleCondition> listCondition = new ArrayList<>();

            // If we found an application corresponging to the name received.
            if (appTmp != null) {

                if (badgesRepository.exists(ruleDTO.getAwardBadgeId()))
                    badgeTmp = badgesRepository.findOne(ruleDTO.getAwardBadgeId());

                if (pointScaleRepository.exists(ruleDTO.getAwardPointScaleId()))
                    pointScaleTmp = pointScaleRepository.findOne(ruleDTO.getAwardPointScaleId());


                // if one of the two is set at least we can continue.
                if (badgeTmp != null || pointScaleTmp != null) {

                    // Create thhe new rule and add it to the DB.
                    Rule rule = new Rule(appTmp, pointScaleTmp, badgeTmp, ruleDTO.getEventType(), ruleDTO.getPoint());
                    rulesRepository.save(rule);

                    /*for (String str : ruleDTO.getConditions()) {
                        RuleCondition ruleConditionTmp = new RuleCondition(rule, str);
                        conditionRepository.save(ruleConditionTmp);
                    }*/

                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(rule.getId()).toUri();
                    return ResponseEntity.created(location).build();
                }
            }
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
}
