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


import ch.heigvd.gamification.api.dto.ApplicationEventGet;
import ch.heigvd.gamification.api.dto.ApplicationGet;
import ch.heigvd.gamification.api.dto.ApplicationPost;
import ch.heigvd.gamification.dao.BadgesRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Event;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ch.heigvd.gamification.dao.ApplicationsRepository;
import ch.heigvd.gamification.utils.ToDTO;


@RestController
public class ApplicationsEndpoint implements ApplicationsApi {

    ApplicationsRepository applicationRepository;
    BadgesRepository badgesRepository;
    
    
    @Autowired
    ApplicationsEndpoint(BadgesRepository badgesRepository, ApplicationsRepository applicationRepository) {
            this.applicationRepository = applicationRepository;
            this.badgesRepository = badgesRepository;
    }
    
    @Override
    @RequestMapping(value = "/applications", method = RequestMethod.POST)
    public ResponseEntity<Void> applicationsPost(@RequestBody ApplicationPost application) {
        
        // Create the new application
        Application appliToCreate = new Application(application.getName(),
                                                    application.getPassword(),
                                                    application.getDescription());
        // Save the application into the DB
        applicationRepository.save(appliToCreate);
        
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(appliToCreate.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    
    @Override
    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationGet>> applicationsGet() {
     
        // Return all the applications
        LinkedList<Application> listTmp = applicationRepository.findAll();
        LinkedList<ApplicationGet> listTmpDtoGet = new LinkedList<ApplicationGet>();
        
        
        // Transform the applications to DTO.
        for(Application application : listTmp){
            listTmpDtoGet.add(ToDTO.applicationtoDTO(application));
        }
        return ResponseEntity.ok().body(listTmpDtoGet);
    }
    
    @Override
    @RequestMapping(value = "/applications/{id}", method = RequestMethod.GET) 
    public ResponseEntity<Object> applicationsIdGet(@PathVariable Long id) {
    
        // If it exist return the application choosed by the ID.
        if(applicationRepository.exists(id)){
            return ResponseEntity.ok().body(ToDTO.applicationtoDTO(applicationRepository.findOne(id)));
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("test");
    }
    
    @Override
    @RequestMapping(value = "/applications/{id}/events", method = RequestMethod.GET) 
    public ResponseEntity<Object> applicationsIdEventsGet(@PathVariable Long id) {
        
        if(applicationRepository.findOne(id) != null){
            return ResponseEntity.ok().body(applicationEventGetToDTO(applicationRepository.findOne(id)));   
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @Override
    @RequestMapping(value = "/applications/{id}", method = RequestMethod.PUT) 
    public ResponseEntity<Object> applicationsIdPut(@PathVariable Long id, @RequestBody ApplicationPost application) {
         
        if(applicationRepository.exists(id)){
            Application tmpApp = applicationRepository.findOne(id);
            tmpApp.setName(application.getName());
            tmpApp.setPassword(application.getPassword());
            tmpApp.setDescription(application.getDescription());
            applicationRepository.save(tmpApp);

            URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(id).toUri();

            return ResponseEntity.status(HttpStatus.OK).body(location);
            
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @Override
    @RequestMapping(value = "/applications/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> applicationsIdDelete(@PathVariable Long id) {
        
        if(applicationRepository.exists(id)){
            applicationRepository.delete(id);
            return ResponseEntity.ok().body(null);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @Override
    @RequestMapping(value = "/applications/{id}/badges/{idBadge}", method = RequestMethod.GET) 
    public ResponseEntity<Object> applicationsIdBadgesIdBadgeGet(@PathVariable Long id, @PathVariable Long idBadge) {
        
        if(Objects.equals(badgesRepository.findOne(idBadge).getApplication().getId(), id)){
            return ResponseEntity.ok().body(ToDTO.badgetoDTO((badgesRepository.findOne(idBadge))));
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Used to return all event for an application
    public ApplicationEventGet applicationEventGetToDTO(Application application){
        
        List<Object> eventList = new ArrayList<>();
        
        for(Event event : application.getEventList()){
            eventList.add(ToDTO.eventToDTO(event));
        }
        
        ApplicationEventGet applicationEventGet = new ApplicationEventGet();
        applicationEventGet.setEventList(eventList);
        return applicationEventGet;    
        
    }
}
