/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.EventPost;
import ch.heigvd.gamification.dao.ApplicationRepository;
import ch.heigvd.gamification.dao.EventRepository;
import ch.heigvd.gamification.dao.UserRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.User;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EventEndpoint implements EventsApi {
    
    
    EventRepository eventRepository;
    ApplicationRepository applicationRepository;
    UserRepository userRepository;
    
    @Autowired
    EventEndpoint(EventRepository eventRepository, ApplicationRepository applicationRepository, UserRepository userRepository) {
            this.applicationRepository = applicationRepository;
            this.eventRepository = eventRepository;
            this.userRepository = userRepository;
    }

    @Override
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseEntity<Void> eventsPost(@RequestBody EventPost event, @RequestHeader String token) {
        
        
        Application appTmp = applicationRepository.findByName(token);
        
        if(appTmp != null){
            User userTmp = appTmp.findUserByAppId(event.getUserAppId());
            if(userTmp == null){

                userTmp = new User(event.getUserAppId(), appTmp);
                userRepository.save(userTmp);
            }

            // Création du nouvelle événement.
            Event eventTmp = new Event(event.getUserAppId(),
                                       userTmp,
                                       appTmp,
                                       event.getEventType());
            
            // On ajoute cette événement à la liste
            //appTmp.getEventList().add(eventTmp);

            // Ajout de l'événement dans la base de données.
            eventRepository.save(eventTmp);

            URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(eventTmp.getId()).toUri();

            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.created(null).build();
        
        
    }
    
    
    
    
}
