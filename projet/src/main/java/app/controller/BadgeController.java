/*
 *
 */
package app.controller;


import app.model.Badge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;


/**
 * This class offers REST standard operations for a Badge.
 *
 * @author M-H. Aghamahdi, J. Ayoub, S. Baehler, M. Monzione
 * @date 14 Nov 2016
 */
@RestController
public class BadgeController {
    LinkedList<Badge> badgesList = new LinkedList<Badge>();
    Badge b1 = new Badge(1 , "name", "name", "Image1");
    int count = 0;



     @RequestMapping("/badge")
    public Badge badge(@RequestParam(value="id") int id) {
         for(int i = 0; i < badgesList.size(); i++) {
            if(badgesList.get(i).getId() == id)
            {
                return badgesList.get(i);
            }
         }
         return null;
    }

    @RequestMapping("/badges")
    public LinkedList badges() {
        return badgesList;
    }

    @RequestMapping(value = "/badge", method = RequestMethod.POST)
    public Badge doLogin(@RequestParam String name) {
        Badge b = new Badge(++count, name, name, name);
        badgesList.add(b);
        return b;
    }
}
