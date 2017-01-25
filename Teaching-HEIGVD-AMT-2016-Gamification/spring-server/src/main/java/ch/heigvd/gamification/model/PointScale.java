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
public class PointScale {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private       Long   id;
    private       String name;

    @OneToMany(mappedBy = "pointScale")
    private List<Rule> RuleList = new ArrayList();

    @OneToMany(mappedBy = "pointScale")
    private List<Award> listAward = new ArrayList<>();

    @ManyToOne
    private Application application;

    public PointScale(Application application, String name) {
            this.application = application;
            this.name = name;

    }
    
    public PointScale(){

    }
    
    public Application getApplication() {
        return application;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public List<Rule> getRuleList() {
        return RuleList;
    }

    public List<Award> getListAward() {
        return listAward;
    }
}
