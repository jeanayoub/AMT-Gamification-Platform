/**
 * 
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


/**
 * This class represents a level. Each level has a unique Id, a numericalName, a name, a description and an icon.
 * 
 * Once set, the id can not be changed.
 * 
 * @author M-H. Aghamahdi, J. Ayoub, S. Baehler, M. Monzione
 * @date 14 Nov 2016
 */

@Entity
public class PointScale {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private       Long   id;
    private       String name;
    private       Long maxPoints;


    @OneToMany(mappedBy = "pointScale")
    private List<Rule> RuleList = new ArrayList();

    @OneToMany(mappedBy = "pointScale")
    private List<Award> listAward = new ArrayList<>();
    
    @OneToMany(mappedBy = "pointScale")
    private List<Progression> listProgression = new ArrayList<>();
    
    @ManyToOne
    private Application application;


    public PointScale(Application application, String name, Long maxPoints) {
            this.application = application;
            this.name = name;
            this.maxPoints = maxPoints;
    }

    public PointScale(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Long maxPoints) {
        this.maxPoints = maxPoints;
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
