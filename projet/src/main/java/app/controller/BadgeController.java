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
    int count = 0;

    /**
     * retourne le badge passé en parametre
     *
     * @date 14 Nov 2016
     * @param id
     * @return le badge avec l'id en param, null si rien n'est trouvé
     */
     @RequestMapping("/badge")
    public Badge badge(@RequestParam int id) {
         for(int i = 0; i < badgesList.size(); i++) {
            if(badgesList.get(i).getId() == id)
            {
                return badgesList.get(i);
            }
         }
         
         return null;
    }


    /**
     * retourne tous les bages
     *
     * @date 15 Nov 2016
     * @param -
     * @return retourne tous les badges
     */
    @RequestMapping("/badges")
    public LinkedList badges() {
        return badgesList;
    }

    /**
     * post et retourne le badge passé en parametre
     *
     * @date 15 Nov 2016
     * @param name desc image
     * @return lpost et retourne badge avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping(value = "/badge", method = RequestMethod.POST)
    public Badge doPost(@RequestParam String name, String desc, String image) {
        Badge b = new Badge(++count, name, desc, image);
        badgesList.add(b);
        return b;
    }

    /**
     * modifie le badge passé en parametre
     *
     * @date 15 Nov 2016
     * @param name desc image
     * @return modifie et retourne badge avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping(value = "/badge", method = RequestMethod.PUT)
    public Badge doLPut(@RequestParam int id, String name, String desc, String image) {
        for(int i = 0; i < badgesList.size(); i++) {
            if (badgesList.get(i).getId() == id) {
                if (name != null)
                    badgesList.get(i).setName(name);
                if (desc != null)
                    badgesList.get(i).setDescription(desc);
                if (image != null)
                    badgesList.get(i).setIcon(image);
            }
            return badgesList.get(i);
        }
        return null;
    }
}
