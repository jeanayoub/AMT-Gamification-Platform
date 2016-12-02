/**
 * 
 */
package ch.heigvd.gamification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


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
	private       long   ID;
	private       String name;
	private       String description;
	private       String icon;
	
	/**
	 * This is the only constructor for a level. It requires all the parameters.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param icon
	 */
	public PointScale(long id, String name, String description, String icon) {
		super();
		ID = id;
		this.name = name;
		this.description = description;
		this.icon = icon;
	}
        
        public PointScale(){
            
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

	public long getID() {
		return ID;
	}
	
	
	
}
