/**
 * 
 */
package app.model;


/**
 * This class represents a level. Each level has a unique Id, a numericalName, a name, a description and an icon.
 * 
 * Once set, the id can not be changed.
 * 
 * @author M-H. Aghamahdi, J. Ayoub, S. Baehler, M. Monzione
 * @date 14 Nov 2016
 */
public class Level {

	private final long   ID;
	private       int    numericalName;
	private       String name;
	private       String description;
	private       String icon;
	
	/**
	 * This is the only constructor for a level. It requires all the parameters.
	 * 
	 * @param iD
	 * @param numericalName
	 * @param name
	 * @param description
	 * @param icon
	 */
	public Level(long id, int numericalName, String name, String description, String icon) {
		super();
		ID = id;
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