import java.io.Serializable;

public class FootballClub extends SportsClub implements Serializable{
	private static final long seralVersionUID = 3L;
	
	private int wins;
	private int draws;
	private int defeats;
	private int numberofgoalsreceived;
	private int numberofgoalscored;
	private int numberofpoints;
	private int numberofmatchesplayed;
	
	
	
	public FootballClub(String name, String location, int wins, int draws, int defeats, int numberofgoalsreceived,
			int numberofgoalscored, int numberofpoints, int numberofmatchesplayed) {
		super(name, location);
		this.wins = wins;
		this.draws = draws;
		this.defeats = defeats;
		this.numberofgoalsreceived = numberofgoalsreceived;
		this.numberofgoalscored = numberofgoalscored;
		this.numberofpoints = numberofpoints;
		this.numberofmatchesplayed = numberofmatchesplayed;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	public int getDefeats() {
		return defeats;
	}
	public void setDefeats(int defeats) {
		this.defeats = defeats;
	}
	public int getNumberofgoalsreceived() {
		return numberofgoalsreceived;
	}
	public void setNumberofgoalsreceived(int numberofgoalsreceived) {
		this.numberofgoalsreceived = numberofgoalsreceived;
	}
	public int getNumberofgoalscored() {
		return numberofgoalscored;
	}
	public void setNumberofgoalscored(int numberofgoalscored) {
		this.numberofgoalscored = numberofgoalscored;
	}
	public int getNumberofpoints() {
		return numberofpoints;
	}
	public void setNumberofpoints(int numberofpoints) {
		this.numberofpoints = numberofpoints;
	}
	public int getNumberofmatchesplayed() {
		return numberofmatchesplayed;
	}
	public void setNumberofmatchesplayed(int numberofmatchesplayed) {
		this.numberofmatchesplayed = numberofmatchesplayed;
	}
	@Override
	public String toString() {
		return "FootballClub [wins=" + wins + ", draws=" + draws + ", defeats=" + defeats + ", numberofgoalsreceived="
				+ numberofgoalsreceived + ", numberofgoalscored=" + numberofgoalscored + ", numberofpoints="
				+ numberofpoints + ", numberofmatchesplayed=" + numberofmatchesplayed + "]";
	}
	
	
	


}
