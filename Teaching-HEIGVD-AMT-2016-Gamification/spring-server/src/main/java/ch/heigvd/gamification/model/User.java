/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long userAppId;
    
    @ManyToOne
    private Application application;
    
    @OneToMany(mappedBy = "user")
     private List<Event> userList = new ArrayList<>();
    
    
   
    @OneToMany(mappedBy = "user")
    private List<PointAward> listPointScale = new ArrayList<>();
    
    public void User(){
    
    }
    
    public long getId() {
        return id;
    }

    public long getUserAppId() {
        return userAppId;
    }
    
    public void setUserAppId(long userAppId) {
        this.userAppId = userAppId;
    }
}
