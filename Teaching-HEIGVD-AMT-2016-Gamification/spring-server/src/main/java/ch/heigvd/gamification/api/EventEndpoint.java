/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.services.EventsProcessor;
import ch.heigvd.gamification.api.dto.EventPost;
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
import ch.heigvd.gamification.dao.ApplicationsRepository;
import ch.heigvd.gamification.dao.AwardsRepository;
import ch.heigvd.gamification.dao.EventsRepository;
import ch.heigvd.gamification.dao.PointScalesRepository;
import ch.heigvd.gamification.dao.UsersRepository;
import org.springframework.http.HttpStatus;

@RestController
public class EventEndpoint implements EventsApi {
    
    
    EventsProcessor eventsProcessor;
    EventsRepository eventRepository;
    ApplicationsRepository applicationRepository;
    UsersRepository userRepository;
    AwardsRepository awardRepository;
    PointScalesRepository pointScaleRepository;
    
    
    @Autowired
    EventEndpoint(EventsRepository eventRepository, ApplicationsRepository applicationRepository, UsersRepository userRepository, AwardsRepository awardRepository,PointScalesRepository pointScaleRepository, EventsProcessor eventsProcessor) {
            this.applicationRepository = applicationRepository;
            this.eventRepository = eventRepository;
            this.userRepository = userRepository;
            this.awardRepository = awardRepository;
            this.pointScaleRepository = pointScaleRepository;
            this.eventsProcessor = eventsProcessor;
    }

    @Override
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseEntity<Void> eventsPost(@RequestBody EventPost eventDTO, @RequestHeader String token) {
               
        Application appTmp = applicationRepository.findByName(token);
        if(appTmp != null){
            User userTmp = appTmp.findUserByAppId(eventDTO.getUserAppId());
            if(userTmp == null){

                userTmp = new User(eventDTO.getUserAppId(), appTmp);
                userRepository.save(userTmp);
                appTmp.getUserList().add(userTmp); 
            }

            // Creation of the the new event.
            Event eventTmp = new Event(eventDTO.getUserAppId(),
                                       userTmp,
                                       appTmp,
                                       eventDTO.getEventType());
            
            // Add the new event to the DB.
            eventRepository.save(eventTmp);
            appTmp.getEventList().add(eventTmp);
            
            EventsProcessor rulesApplication = new EventsProcessor(userRepository, applicationRepository, awardRepository, pointScaleRepository);
            eventsProcessor.application(userTmp.getId(), eventDTO.getEventType(), appTmp.getId());
           
            URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(eventTmp.getId()).toUri();

            return ResponseEntity.created(location).build();
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    } 
}
