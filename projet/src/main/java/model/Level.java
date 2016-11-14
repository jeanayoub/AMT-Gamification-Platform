/**
 * 
 */
package model;

/**
 * @author J. Ayoub
 *
 */
public class Level {

	private final long   ID;
	private       int    numericalName;
	private       String name;
	private       String description;
	private       String icon;
	
	public Level(long iD, int numericalName, String name, String description, String icon) {
		super();
		ID = iD;
		this.numericalName = numericalName;
		this.name = name;
		this.description = description;
		this.icon = icon;
	}

	public int getNumericalName() {
		return numericalName;
	}

	public void setNumericalName(int numericalName) {
		this.numericalName = numericalName;
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
