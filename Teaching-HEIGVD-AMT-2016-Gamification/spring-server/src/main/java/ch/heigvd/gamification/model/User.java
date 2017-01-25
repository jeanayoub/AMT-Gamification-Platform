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
    private List<Event> eventList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Award> listAward = new ArrayList<>();
    

    public User(){
    
    }

    public User(Long userAppId, Application application){
        this.userAppId = userAppId;
        this.application = application;
             
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
    
        public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<Event> getUserList() {
        return eventList;
    }

    public void setUserList(List<Event> userList) {
        this.eventList = userList;
    }

    public List<Award> getListAward() {
        return listAward;
    }

    public void setListAward(List<Award> listAward) {
        this.listAward = listAward;
    }
}

