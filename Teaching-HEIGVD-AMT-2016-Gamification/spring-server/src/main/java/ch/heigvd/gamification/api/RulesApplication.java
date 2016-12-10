/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.dao.ApplicationRepository;
import ch.heigvd.gamification.dao.AwardRepository;
import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.dao.PointScaleRepository;
import ch.heigvd.gamification.dao.ProgressionRepository;
import ch.heigvd.gamification.dao.RuleRepository;
import ch.heigvd.gamification.dao.UserRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Award;
import ch.heigvd.gamification.model.BadgeAward;
import ch.heigvd.gamification.model.PointAward;
import ch.heigvd.gamification.model.Progression;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesApplication {

    RuleRepository ruleRepository;
    UserRepository userRepository;
    ApplicationRepository applicationRepository;
    BadgesRepository badgesRepository;
    PointScaleRepository pointScaleRepository;
    AwardRepository awardRepository;
    ProgressionRepository progressionRepository;

    /* @Autowired
    RulesApplication(RuleRepository ruleRepository, ApplicationRepository applicationRepository, BadgesRepository badgesRepository, PointScaleRepository pointScaleRepository, UserRepository userRepository) {
            this.ruleRepository = ruleRepository;
            this.userRepository = userRepository;
            this.applicationRepository = applicationRepository;
            this.badgesRepository = badgesRepository;
            this.pointScaleRepository = pointScaleRepository;
            
    }*/
    public RulesApplication(UserRepository userRepository, ApplicationRepository applicationRepository, AwardRepository awardRepository, PointScaleRepository pointScaleRepository, ProgressionRepository progressionRepository) {
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
                    pointAward.setAwardedPoint(rule.getNumberOfPoints());

                    awardRepository.save(pointAward);
                    
                    for (Progression progression : userTmp.getListProgression()) {
                        
                        if (progression.getPointScale().getId().equals(rule.getPointScale().getId())) {

                            if(progression.getActualPoint() < rule.getPointScale().getMaxPoints()){
                                progression.addPoint(rule.getNumberOfPoints());
                                progressionRepository.save(progression);
                            
                            
                                if(progression.getActualPoint() >= rule.getPointScale().getMaxPoints()){
                                    badgeReward = true;
                                }
                                else
                                    badgeReward = false;
                            }else
                                badgeReward = false;
                        }
                    }
                }
                
                // If a badge dont depend of a pointScale, it will be allowed.
                if (rule.getBadge() != null && badgeReward) {

                    
                    
                    BadgeAward badgeAward = new BadgeAward(new Date());
                    badgeAward.setBadge(rule.getBadge());
                    badgeAward.setUser(userTmp);

                    awardRepository.save(badgeAward);
                }
            }
        }
    }
}
