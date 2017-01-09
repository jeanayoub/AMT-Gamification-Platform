/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.services;

import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Award;
import ch.heigvd.gamification.model.BadgeAward;
import ch.heigvd.gamification.model.PointAward;
import ch.heigvd.gamification.model.Progression;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.RuleCondition;
import ch.heigvd.gamification.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import ch.heigvd.gamification.dao.ApplicationsRepository;
import ch.heigvd.gamification.dao.AwardsRepository;
import ch.heigvd.gamification.dao.PointScalesRepository;
import ch.heigvd.gamification.dao.ProgressionsRepository;
import ch.heigvd.gamification.dao.RulesRepository;
import ch.heigvd.gamification.dao.UsersRepository;

@Service
public class EventsProcessor {

    RulesRepository ruleRepository;
    UsersRepository userRepository;
    ApplicationsRepository applicationRepository;
    BadgesRepository badgesRepository;
    PointScalesRepository pointScaleRepository;
    AwardsRepository awardRepository;
    ProgressionsRepository progressionRepository;

   /* @Autowired
    EventsProcessor(RuleRepository ruleRepository, ApplicationRepository applicationRepository, BadgesRepository badgesRepository, PointScaleRepository pointScaleRepository, UserRepository userRepository, ProgressionRepository progressionRepository) {
            this.ruleRepository = ruleRepository;
            this.userRepository = userRepository;
            this.applicationRepository = applicationRepository;
            this.badgesRepository = badgesRepository;
            this.pointScaleRepository = pointScaleRepository;
            this.progressionRepository = progressionRepository;
            
    }*/
    public EventsProcessor(UsersRepository userRepository, ApplicationsRepository applicationRepository, AwardsRepository awardRepository, PointScalesRepository pointScaleRepository, ProgressionsRepository progressionRepository) {
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
        this.awardRepository = awardRepository;
        this.pointScaleRepository = pointScaleRepository;
        this.progressionRepository = progressionRepository;

    }

    public void application(Long userId, String eventType, Long appId) {

        User userTmp = userRepository.findOne(userId);
        Application appTmp = applicationRepository.findOne(appId);
        boolean badgeReward = true;

        for (Rule rule : appTmp.getRuleList()) {
            // For all matching rules.
            if (rule.getTypeEvent().equalsIgnoreCase(eventType)) {

                
                if (rule.getPointScale() != null) {

                    PointAward pointAward = new PointAward(new Date());
                    pointAward.setPointScale(rule.getPointScale());
                    pointAward.setUser(userTmp);
                    pointAward.setAwardedPoint(rule.getPoint());

                    awardRepository.save(pointAward);
                    
                    for (Progression progression : userTmp.getListProgression()) {
                        if (progression.getPointScale().getId().equals(rule.getPointScale().getId())) {
                            progression.addPoint(rule.getPoint());
                            progressionRepository.save(progression);
                        }
                    }
                    
                    /*for (Progression progression : userTmp.getListProgression()) {
                        
                        if (progression.getPointScale().getId().equals(rule.getPointScale().getId())) {

                            // If we didn't already hit the max point limit of the pointScale we add point to the progression.
                            if(progression.getActualPoint() < rule.getPointScale().getMaxPoints()){
                                progression.addPoint(rule.getNumberOfPoints());
                                progressionRepository.save(progression);
                            
                                //If we hit the max point just now, we can add the badge (if there is one).
                                if(progression.getActualPoint() >= rule.getPointScale().getMaxPoints()){
                                    badgeReward = true;
                                }
                                else // 
                                    badgeReward = false;
                            }else // We dont have to add the badge again.
                                badgeReward = false;
                        }
                    }*/
                }
                
                // If a badge dont depend of a pointScale, it will be allowed.
                if (rule.getBadge() != null && badgeReward) {

                    
                    
                    BadgeAward badgeAward = new BadgeAward(new Date());
                    badgeAward.setBadge(rule.getBadge());
                    badgeAward.setUser(userTmp);

                    awardRepository.save(badgeAward);
                }
            }
            
            List<RuleCondition> ruleConditionList = rule.getListCondition();
            
           
            
           // String ruleEventType = ruleConditionList.get(0).getRuleCondition();
            //Long pointScaleId = new Long(ruleConditionList.get(1).getRuleCondition());
            //Long seuil = rule.getPoint();
            
            
           /* if((ruleConditionList.get(0).equals(eventType)) && ){
                
            }*/
            
            
        }
    }
}
