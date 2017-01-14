/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Event {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long userExtAppId;
    private String eventType;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Application application;
    
    public Event(){
        
    }
    
    public Event(Long userExtAppId, User user, Application application, String EventType){
        this.userExtAppId = userExtAppId;
        this.user = user;
        this.application = application;
        this.eventType = EventType;
        
    }
    
    public void setUserAppId(long userAppId) {
        this.userExtAppId = userAppId;
    }

    public void setType(String Type) {
        this.eventType = Type;
    }

    public Long getId() {
        return id;
    }

    public Long getUserAppId() {
        return userExtAppId;
    }

    public String getType() {
        return eventType;
    }
    
        public Long getUserExtAppId() {
        return userExtAppId;
    }

    public void setUserExtAppId(Long userExtAppId) {
        this.userExtAppId = userExtAppId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
    
}
