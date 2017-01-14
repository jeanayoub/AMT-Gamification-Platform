package ch.heigvd.gamification.utils;

import ch.heigvd.gamification.api.dto.ApplicationGet;
import ch.heigvd.gamification.api.dto.BadgeGet;
import ch.heigvd.gamification.api.dto.EventGet;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.Event;

import java.util.LinkedList;

/**
 * Created by oem on 14.01.17.
 */
public class toDTO {

    public static EventGet eventToDTO(Event event){

        EventGet eventGet = new EventGet();
        eventGet.setId(event.getId());
        eventGet.setUserExtAppId(event.getUserAppId());
        eventGet.setEventType(event.getEventType());
        eventGet.setUserId(event.getUser().getId());

        return eventGet;
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
}
