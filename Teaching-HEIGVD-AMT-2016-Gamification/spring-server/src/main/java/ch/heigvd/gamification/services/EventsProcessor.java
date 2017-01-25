/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.services;

import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.BadgeAward;
import ch.heigvd.gamification.model.PointAward;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.User;
import java.util.Date;

import org.springframework.stereotype.Service;
import ch.heigvd.gamification.dao.ApplicationsRepository;
import ch.heigvd.gamification.dao.AwardsRepository;
import ch.heigvd.gamification.dao.PointScalesRepository;
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

    //This should be used, but it did't work, all the repository stay "Null".
    
   /* @Autowired
    EventsProcessor(RuleRepository ruleRepository, ApplicationRepository applicationRepository, BadgesRepository badgesRepository, PointScaleRepository pointScaleRepository, UserRepository userRepository, ProgressionRepository progressionRepository) {
            this.ruleRepository = ruleRepository;
            this.userRepository = userRepository;
            this.applicationRepository = applicationRepository;
            this.badgesRepository = badgesRepository;
            this.pointScaleRepository = pointScaleRepository;
            this.progressionRepository = progressionRepository;
            
    }*/
    
    // Only here to allow the application to "work"
    public EventsProcessor(UsersRepository userRepository, ApplicationsRepository applicationRepository, AwardsRepository awardRepository, PointScalesRepository pointScaleRepository) {
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
        this.awardRepository = awardRepository;
        this.pointScaleRepository = pointScaleRepository;
    }

    public void application(Long userId, String eventType, Long appId) {

        User userTmp = userRepository.findOne(userId);
        Application appTmp = applicationRepository.findOne(appId);

        for (Rule rule : appTmp.getRuleList()) {
            // For all matching rules.
            if (rule.getTypeEvent().equalsIgnoreCase(eventType)) {

                // If there is a PointScale in the rule we grant it.
                if (rule.getPointScale() != null) {

                    PointAward pointAward = new PointAward(new Date());
                    pointAward.setPointScale(rule.getPointScale());
                    pointAward.setUser(userTmp);
                    pointAward.setAwardedPoint(rule.getPoint());

                    awardRepository.save(pointAward);
                }
                
                // If there is a Badge in the rule we grant it.
                if (rule.getBadge() != null) {
                    
                    BadgeAward badgeAward = new BadgeAward(new Date());
                    badgeAward.setBadge(rule.getBadge());
                    badgeAward.setUser(userTmp);

                    awardRepository.save(badgeAward);
                }
            } 
        }
    }
}
