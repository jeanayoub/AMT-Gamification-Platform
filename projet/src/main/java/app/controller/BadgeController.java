/*
 *
 */
package app.controller;


import app.model.Badge;
import app.model.BadgesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


/**
 * This class offers REST standard operations for a Badge.
 *
 * @author M-H. Aghamahdi, J. Ayoub, S. Baehler, M. Monzione
 * @date 14 Nov 2016
 */
@RestController
public class BadgeController {
   
    BadgesRepository badgesRepository;
    int count = 0;

    /**
     * retourne le badge passé en parametre
     *
     * @date 14 Nov 2016
     * @param id
     * @return le badge avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping("/badges/{id}")
    public Badge badge(@PathVariable("id") long id) {
        return badgesRepository.findOne(id);
    }


    /**
     * retourne tous les bages
     *
     * @date 15 Nov 2016
     * @param -
     * @return retourne tous les badges
     */
    @RequestMapping("/badges")
    public List badges() {
        return badgesRepository.findAll();
    }

    /**
     * post et retourne le badge passé en parametre
     *
     * @date 15 Nov 2016
     * @param name desc image
     * @return lpost et retourne badge avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping(value = "/badges", method = RequestMethod.POST)
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
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.PUT)
    public Badge doLPut(@PathVariable("id") int id , @RequestParam String name, String desc, String image) {
        for(int i = 0; i < badgesList.size(); i++) {
            if (badgesList.get(i).getID() == id) {
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

    /**
     * delete le badge passé en parametre
     *
     * @date 15 Nov 2016
     * @param id
     * @return delete badge avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.DELETE)
    public Badge doDelete(@PathVariable("id") int id) {
        for(int i = 0; i < badgesList.size(); i++) {
                if(badgesList.get(i).getID() == id)
                {
                    badgesList.remove(i);
                }
            }
        return null;
    }
}
