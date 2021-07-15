import java.io.IOException;

public interface LeagueManager {

	public void gUI();
	
	
	
	
	
	public void displaymanu() throws IOException;
	
	
	public void exit() throws IOException ;
	
	public void addTeam();
	
	public void deleteTeam();
	
	public void displayStatistics();
	
	public void displayTable();
	
	public void addPlayedmatch();
	
	public void write() throws IOException;
	
	public void read() throws IOException;
	
	
	
}
