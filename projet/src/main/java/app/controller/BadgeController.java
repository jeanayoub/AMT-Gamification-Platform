/*
 *
 */
package app.controller;


import app.model.Badge;
import app.model.BadgesRepository;
import java.net.URI;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 * This class offers REST standard operations for a Badge.
 *
 * @author M-H. Aghamahdi, J. Ayoub, S. Baehler, M. Monzione
 * @date 14 Nov 2016
 */
@RestController
public class BadgeController {
   
    BadgesRepository badgesRepository;
    
    
    @Autowired
    BadgeController(BadgesRepository badgesRepository) {
            this.badgesRepository = badgesRepository;
    }
    
    
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
    public LinkedList badges() {
        return badgesRepository.findAll();
    }

    /**
     * post et retourne le badge passé en parametre
     *
     * @param payload
     * @param response
     * @date 15 Nov 2016
     * @return lpost et retourne badge avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping(value = "/badges", method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> doPost(@RequestBody Badge badge, HttpServletResponse response) {
 
        badgesRepository.save(badge);
    
        response.setStatus(HttpServletResponse.SC_CREATED);

        
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(badge.getID()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * modifie le badge passé en parametre
     *
     * @date 15 Nov 2016
     * @param name desc image
     * @return modifie et retourne badge avec l'id en param, null si rien n'est trouvé
     */
  /*  @RequestMapping(value = "/badges/{id}", method = RequestMethod.PUT)
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
    }*/

    /**
     * delete le badge passé en parametre
     *
     * @date 15 Nov 2016
     * @param id
     * @return delete badge avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.DELETE)
    public Badge doDelete(@PathVariable("id") long id) {
        badgesRepository.delete(id);
        return null;
    }
}
