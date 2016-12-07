package ch.heigvd.gamification.api;


import ch.heigvd.gamification.api.ApplicationsApi;
import ch.heigvd.gamification.api.dto.ApplicationGet;
import ch.heigvd.gamification.api.dto.ApplicationPost;
import ch.heigvd.gamification.api.dto.BadgeGet;
import ch.heigvd.gamification.dao.ApplicationRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
@RestController
public class ApplicationsEndpoint implements ApplicationsApi {

    ApplicationRepository applicationRepository;
    BadgesRepository badgesRepository;
    
    
    @Autowired
    ApplicationsEndpoint(BadgesRepository badgesRepository, ApplicationRepository applicationRepository) {
            this.applicationRepository = applicationRepository;
            this.badgesRepository = badgesRepository;
    }
    
    
    
    @Override
    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationGet>> applicationsGet() {
     
        LinkedList<Application> listTmp = applicationRepository.findAll();
        LinkedList<ApplicationGet> listTmpDtoGet = new LinkedList<ApplicationGet>();
        
        for(Application application : listTmp){
            listTmpDtoGet.add(toDTO(application));
        }
        
        return ResponseEntity.ok().body(listTmpDtoGet);
    }

    @Override
    @RequestMapping(value = "/applications/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> applicationsIdDelete(@PathVariable Long id) {
        
        if(applicationRepository.exists(id)){
            applicationRepository.delete(id);
            return ResponseEntity.ok().body(null);
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @RequestMapping(value = "/applications", method = RequestMethod.POST)
    public ResponseEntity<Void> applicationsPost(@RequestBody ApplicationPost application) {
        
        Application appliToCreate = new Application(application.getName(),
                                                    application.getPassword(),
                                                    application.getDescription());
        
        applicationRepository.save(appliToCreate);
        
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(appliToCreate.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @Override
    @RequestMapping(value = "/applications/{id}", method = RequestMethod.GET) 
    public ResponseEntity<Object> applicationsIdGet(@PathVariable Long id) {
    
        if(applicationRepository.exists(id)){
            return ResponseEntity.ok().body(toDTO(applicationRepository.findOne(id)));
        }
         
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @RequestMapping(value = "/applications/{id}", method = RequestMethod.PUT) 
    public ResponseEntity<Object> applicationsIdPut(@PathVariable Long id, ApplicationPost application) {
         
        if(applicationRepository.exists(id)){
            applicationRepository.findOne(id).setName(application.getName());
            applicationRepository.findOne(id).setDescription(application.getDescription());
            
            URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(id).toUri();

            return ResponseEntity.ok(location);
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    
    public ApplicationGet toDTO(Application application){
        
        
         LinkedList<String> listBadgesUrl = new LinkedList<String>();
        
        ApplicationGet appGetTmp = new ApplicationGet();
        appGetTmp.setId(application.getId());
        appGetTmp.setName(application.getName());
        appGetTmp.setDescription(application.getDescription());
        
        
        for(Badge badge : application.getBadges()){
            listBadgesUrl.add("api/badges/" + badge.getId());
        }
        
        appGetTmp.setBadgesList(listBadgesUrl);
        
        return appGetTmp;  
    }

    @Override
    @RequestMapping(value = "/applications/{id}/{idBadge}", method = RequestMethod.GET) 
    public ResponseEntity<Object> applicationsIdIdBadgeGet(@PathVariable Long id, @PathVariable Long idBadge) {
        
        if(badgesRepository.findOne(idBadge).getApplication().getId() == id){
        
            return ResponseEntity.ok().body(badgeToDTO(badgesRepository.findOne(idBadge)));
        }
        
        return ResponseEntity.ok().body(null);
    }
    
    // SALE
    public BadgeGet badgeToDTO(Badge badge){
    
       
        BadgeGet badgeGet = new BadgeGet();
        badgeGet.setId(badge.getId());
        badgeGet.setName(badge.getName());
        badgeGet.setDescription(badge.getDescription());
        badgeGet.setIcon(badge.getIcon());
        return badgeGet; 
    }
    
}