import java.io.Serializable;

public  class SportsClub implements Serializable {
	private static final long seralVersionUID = 3L;
	
	private String name;
	private String location;
	
	
	
	public SportsClub(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	@Override
	
	public boolean equals (Object o) {
		return this.name.equals(((SportsClub)o).name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
