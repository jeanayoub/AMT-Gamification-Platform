package ch.heigvd.gamification.utils;

import ch.heigvd.gamification.api.dto.ApplicationGet;
import ch.heigvd.gamification.api.dto.BadgeGet;
import ch.heigvd.gamification.api.dto.EventGet;
import ch.heigvd.gamification.api.dto.UserAwardGet;
import ch.heigvd.gamification.api.dto.UserAwardGetAwardList;
import ch.heigvd.gamification.api.dto.UserGet;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Award;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.BadgeAward;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.PointAward;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.model.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by oem on 14.01.17.
 */
public class ToDTO {

    public static EventGet eventToDTO(Event event){

        EventGet eventGet = new EventGet();
        eventGet.setId(event.getId());
        eventGet.setUserExtAppId(event.getUserAppId());
        eventGet.setEventType(event.getEventType());
        eventGet.setUserId(event.getUser().getId());

        return eventGet;
    }

    public static UserGet userToDTO(User user)
    {
        UserGet userGetTmp = new UserGet();
        userGetTmp.setId(user.getId());

        return  userGetTmp;
    }

    public static ApplicationGet applicationtoDTO(Application application){

        LinkedList<String> listBadgesUrl = new LinkedList<String>();

        ApplicationGet appGetTmp = new ApplicationGet();
        appGetTmp.setId(application.getId());
        appGetTmp.setName(application.getName());
        appGetTmp.setDescription(application.getDescription());


        for(Badge badge : application.getBadges()){
            listBadgesUrl.add("api/badges/" + badge.getId());
        }

        appGetTmp.setBadgesList(listBadgesUrl);

        return appGetTmp;
    }
    public static BadgeGet badgetoDTO(Badge badge){
        BadgeGet badgeGet = new BadgeGet();
        badgeGet.setId(badge.getId());
        badgeGet.setName(badge.getName());
        badgeGet.setDescription(badge.getDescription());
        badgeGet.setIcon(badge.getIcon());
        return badgeGet;
    }
    
    public static UserAwardGet UserAwardToDTO(User user){
        
        List<Award> awardList = user.getListAward();
        List<UserAwardGetAwardList> awardStringList = new LinkedList<UserAwardGetAwardList>();
        
        UserAwardGet userAwardGet = new UserAwardGet();
        
        userAwardGet.setId(user.getId());
        
        Badge badgeTmp;
        PointScale pointScaleTmp;
        
         
        for(Award awardTmp : awardList){
            
            badgeTmp = awardTmp.getBadge();
            pointScaleTmp = awardTmp.getPointScale();
            
            UserAwardGetAwardList userAwardGetAwardListTmp = new UserAwardGetAwardList();
            
            if(badgeTmp != null){ 
                
                BadgeAward badgeAwardTmp = (BadgeAward)awardTmp;
                
                userAwardGetAwardListTmp.setAwardDate(badgeAwardTmp.getDate().toString());
                userAwardGetAwardListTmp.setAwardLocation("api/badges/" + badgeTmp.getId());
                userAwardGetAwardListTmp.setAwardType("Badge");
            }
             
            if(pointScaleTmp != null){

                
                PointAward pointAwardTmp = (PointAward)awardTmp;
                
                userAwardGetAwardListTmp.setAwardDate(pointAwardTmp.getDate().toString());
                userAwardGetAwardListTmp.setAwardLocation("api/pointScales/" + pointScaleTmp.getId());
                userAwardGetAwardListTmp.setAwardType("PointScale");
            }
            awardStringList.add(userAwardGetAwardListTmp);
        }
        
        userAwardGet.setAwardList(awardStringList);
        
        return userAwardGet;
        
    }
}
