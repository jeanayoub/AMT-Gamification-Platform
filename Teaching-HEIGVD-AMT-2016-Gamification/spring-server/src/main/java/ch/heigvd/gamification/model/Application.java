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

package ch.heigvd.gamification.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String description;
   
    
    @OneToMany(mappedBy = "application")
    private List<Rule> ruleList = new ArrayList<>();
    
    @OneToMany(mappedBy = "application")
    private List<Badge> badgesList = new ArrayList<>();
    
    @OneToMany(mappedBy = "application")
    private List<User> userList = new ArrayList<>();
    
    @OneToMany(mappedBy = "application")
    private List<Event> eventList = new ArrayList<>();
    
    @OneToMany(mappedBy = "application")
    private List<PointScale> pointScaleList = new ArrayList<>();
        
   
    public Application(){
        
    }

    public void setPointScaleList(List<PointScale> pointScaleList) {
        this.pointScaleList = pointScaleList;
    }
    
    public Application(String name, String password, String description){
        this.name = name;
        this.password = password;
        this.description = description;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Badge> getBadges() {
        return badgesList;
    }
    
    public List<Rule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }

    public List<Badge> getBadgesList() {
        return badgesList;
    }

    public void setBadgesList(List<Badge> badgesList) {
        this.badgesList = badgesList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
    
    public User findUserByAppId(Long id){
        for(User user : userList){
            if(user.getUserAppId()==id)
                return user;
        }
        return null;
    }
    
    public List<PointScale> getPointScaleList() {
        return pointScaleList;
    }
}
