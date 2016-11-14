/**
 * 
 */
package model;



/**
 * @author J. Ayoub
 *
 */
public class Badge {

	private final long   ID;
	private       String name;
	private       String description;
	private       String icon;
	
	public Badge(long id, String name, String description, String icon) {
		this.ID          = id;
		this.name        = name;
		this.description = description;
		this.icon        = icon;
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
		return ID;
	}
	
	
}

