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

@Entity
public class Event {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long userId;
    private String type;
    
    public Event(){
        
    }
    
    public Event(long userId, String type){
        this.userId = userId;
        this.type = type;
    }
    
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }
    
    
}
