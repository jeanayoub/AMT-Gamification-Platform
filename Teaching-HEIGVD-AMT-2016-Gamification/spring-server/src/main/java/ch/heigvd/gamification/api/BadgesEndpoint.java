/**
 * Author : Aghamahdi Mohammad Hossein
 *          Ayoub jean
 *          Baehler Simon
 *          Monzione Marco
 * 
 * Project : AMT-Gamification-platform
 * 
 * Date : 25.01.2017
 *          
 */

package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.BadgeGet;
import ch.heigvd.gamification.api.dto.BadgePost;
import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Badge;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ch.heigvd.gamification.dao.ApplicationsRepository;
import ch.heigvd.gamification.utils.ToDTO;

@RestController
public class BadgesEndpoint implements BadgesApi {

    BadgesRepository badgesRepository;
    ApplicationsRepository applicationsRepository;
    
    @Autowired
    BadgesEndpoint(BadgesRepository badgesRepository, ApplicationsRepository applicationsRepository) {
            this.badgesRepository = badgesRepository;
            this.applicationsRepository = applicationsRepository;
    }
    
    @Override
    @RequestMapping(value = "/badges", method = RequestMethod.GET)
    public ResponseEntity<List<BadgeGet>> badgesGet(@RequestHeader String token) {
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            // Return all the badge found for an application.
            LinkedList<Badge> listTmp = badgesRepository.findByApplicationId(appTmp.getId());
            LinkedList<BadgeGet> listTmpDtoGet = new LinkedList<BadgeGet>();

            // transform all the badges to DTO.
            for(Badge badge : listTmp){
                listTmpDtoGet.add(ToDTO.badgetoDTO(badge));
            }

            return ResponseEntity.ok().body(listTmpDtoGet);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
    
    @Override
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.GET)    
    public ResponseEntity<Object> badgesIdGet(@PathVariable Long id,  @RequestHeader String token) {
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            if(badgesRepository.exists(id)){
                // If it exist, return the badge defined by the the ID
                return ResponseEntity.ok().body(ToDTO.badgetoDTO(badgesRepository.findOne(id)));
            }
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong login"); 
    }

    @Override
    @RequestMapping(value = "/badges", method = RequestMethod.POST)
    public ResponseEntity<Void> badgesPost(@RequestBody BadgePost badge, @RequestHeader String token) {
        
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            // Create the new badge
            Badge badgeToCreate = new Badge( badge.getName(),
                                        badge.getDescription(),
                                        badge.getIcon(),
                                        appTmp);
            // Add the new badge to the DB
            badgesRepository.save(badgeToCreate);
            // Add the badge to the app.
            appTmp.getBadgesList().add(badgeToCreate);
        
            URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(badgeToCreate.getId()).toUri();
            
            System.out.println("cacacaca : " + badgeToCreate.getId());

            return ResponseEntity.created(location).build();
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Override
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> badgesIdDelete(@PathVariable Long id,  @RequestHeader String token) {
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            // If the badge exist we delete it.
            if(badgesRepository.exists(id)){
                badgesRepository.delete(id);
                return ResponseEntity.ok().body(null);
            }
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Override
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> badgesIdPut(@PathVariable Long id,  @RequestHeader String token, @RequestBody BadgePost badge) {
        
        Application appTmp = applicationsRepository.findByName(token);
        // If we didn't we find the application we cannot create the badge.
        if(appTmp != null){
            if(badgesRepository.exists(id)){
                
                // Get the badge in the DB if it exist.
                Badge existingBadge = badgesRepository.findOne(id);
                // Modifie these values.                
                existingBadge.setIcon(badge.getIcon());
                existingBadge.setDescription(badge.getDescription());
                existingBadge.setName(badge.getName());
                // Save de modification in the DB
                badgesRepository.save(existingBadge);

                URI location = ServletUriComponentsBuilder
                                .fromCurrentRequest().path("/{id}")
                                .buildAndExpand(id).toUri();

                return ResponseEntity.status(HttpStatus.OK).body(location);
            }
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
}
