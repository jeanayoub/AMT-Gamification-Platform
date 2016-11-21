/*
 *
 */
package app.controller;


import DTO.BadgeDTO;
import app.model.Badge;
import app.model.BadgesRepository;
import java.net.URI;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity doPost(@RequestBody Badge badge, HttpServletResponse response) {
 
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
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.PUT)
    public ResponseEntity doLPut(@PathVariable("id") Long id , HttpServletResponse response, @RequestBody Badge badge) {
        
        badgesRepository.findOne(id).setName(badge.getName());
        badgesRepository.findOne(id).setDescription(badge.getDescription());
        badgesRepository.findOne(id).setIcon(badge.getIcon());
      
        response.setStatus(HttpServletResponse.SC_CREATED);
       
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * delete le badge passé en parametre
     *
     * @date 15 Nov 2016
     * @param id
     * @return delete badge avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.DELETE)
    public ResponseEntity doDelete(@PathVariable("id") long id, HttpServletResponse response) {
        
        badgesRepository.delete(id);
        
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
       
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand().toUri();

        return ResponseEntity.created(location).build();
    }
    
    
    
     public BadgeDTO toDTO(Badge badge){
       
         return new BadgeDTO(badge.getID(),
                             badge.getName(),
                             badge.getDescription(),
                             badge.getIcon());
     }
     
     public Badge fromDTO(BadgeDTO badgeDTO){
        
         return new Badge(badgeDTO.getID(),
                         badgeDTO.getName(),
                         badgeDTO.getDescription(),
                         badgeDTO.getIcon());
     }
}
