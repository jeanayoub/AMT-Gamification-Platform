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
	private       long   id;
	private       String name;
        private       int points;
        
        
        @OneToMany(mappedBy = "pointScale")
        private List<PointAward> userList = new ArrayList();
	
        @OneToMany(mappedBy = "pointScale")
        private List<Rule> RuleList = new ArrayList();
        
        
	public PointScale(String name, int points) {
		this.name = name;
                this.points = points;
	}
        
        public PointScale(){
            
        }

	public String getName() {
            return name;
	}

	public void setName(String name) {
            this.name = name;
	}


	public long getID() {
            return id;
	}
        
        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
	
	
	
}
