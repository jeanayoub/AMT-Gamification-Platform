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
import javax.persistence.OneToMany;

@Entity
public class Event {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long userAppId;
    private String type;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Application application;
    
    public Event(){
        
    }
    
    public Event(long userId, String type){
        this.userAppId = userId;
        this.type = type;
    }
    
    public void setUserAppId(long userAppId) {
        this.userAppId = userAppId;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public long getId() {
        return id;
    }

    public long getUserAppId() {
        return userAppId;
    }

    public String getType() {
        return type;
    }
    
    
}
