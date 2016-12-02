/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.BadgeGet;
import ch.heigvd.gamification.api.dto.BadgePost;
import ch.heigvd.gamification.dao.ApplicationRepository;
import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.model.Badge;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author marco
 */
@RestController
public class BadgesEndpoint implements BadgesApi {

    BadgesRepository badgesRepository;
    ApplicationRepository applicationsRepository;
    
    @Autowired
    BadgesEndpoint(BadgesRepository badgesRepository, ApplicationRepository applicationsRepository) {
            this.badgesRepository = badgesRepository;
            this.applicationsRepository = applicationsRepository;
    }
    
    @Override
    @RequestMapping(value = "/badges", method = RequestMethod.GET)
    public ResponseEntity<List<BadgeGet>> badgesGet() {
        
        LinkedList<Badge> listTmp = badgesRepository.findAll();
        LinkedList<BadgeGet> listTmpDtoGet = new LinkedList<BadgeGet>();
        
        for(Badge badge : listTmp){
            listTmpDtoGet.add(toDTO(badge));
        }
        
        return ResponseEntity.ok().body(listTmpDtoGet);
    }
    
    @Override
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.GET)    
    public ResponseEntity<Object> badgesIdGet(Long id) {
        if(badgesRepository.exists(id)){
            return ResponseEntity.ok().body(toDTO(badgesRepository.findOne(id)));
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
       
    }

    @Override
    @RequestMapping(value = "/badges", method = RequestMethod.POST)
    public ResponseEntity<Void> badgesPost(BadgePost badge) {
        
        Badge badgeToCreate = new Badge( badge.getName(),
                                        badge.getDescription(),
                                        badge.getIcon());
        
        badgesRepository.save(badgeToCreate);
        
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(badgeToCreate.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> badgesIdDelete(Long id) {
        
        if(badgesRepository.exists(id)){
            badgesRepository.delete(id);
            return ResponseEntity.ok().body(null);
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @RequestMapping(value = "/badges/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> badgesIdPut(Long id, BadgePost badge) {
        
        if(badgesRepository.exists(id)){
        
            badgesRepository.findOne(id).setName(badge.getName());
            badgesRepository.findOne(id).setDescription(badge.getDescription());
            badgesRepository.findOne(id).setIcon(badge.getIcon());

            URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(id).toUri();

            return ResponseEntity.ok(location);
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    
    public BadgeGet toDTO(Badge badge){
    
       
        BadgeGet badgeGet = new BadgeGet();
        badgeGet.setId(badge.getId());
        badgeGet.setName(badge.getName());
        badgeGet.setDescription(badge.getDescription());
        badgeGet.setIcon(badge.getIcon());
        return badgeGet; 
    }
}
