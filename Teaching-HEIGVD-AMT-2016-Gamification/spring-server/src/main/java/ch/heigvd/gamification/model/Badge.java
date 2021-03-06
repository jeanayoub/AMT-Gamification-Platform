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
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String icon;

    @OneToMany(mappedBy = "badge")
    private List<Rule> badgesRule = new ArrayList<>();
    
    @OneToMany(mappedBy = "badge")
    private List<Award> listAward = new ArrayList<>();
    
    
    @ManyToOne
    private Application application;

    /**
     * This is the only constructor for a badge. It requires all the parameters.
     *
     * @param name
     * @param description
     * @param icon
     * @param application
     */
    public Badge(String name, String description, String icon, Application application) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.application = application;
    }

    public Badge(Long ID, String name, String description, String icon) {
        this.id = ID;
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public Badge() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

}
