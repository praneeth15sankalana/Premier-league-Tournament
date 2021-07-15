import java.util.Comparator;

public class Compare  implements Comparator<FootballClub> {
	private static final long seralVersionUID = 3L;
	
    @Override
	
	public int  compare (FootballClub   te, FootballClub te1) {
		
		if(te.getNumberofpoints() > te1.getNumberofpoints())
			return -1;
		else 
			if (te.getNumberofpoints() < te1.getNumberofpoints())
				return 1;
			else {
				int goaldif = te.getNumberofgoalscored() - te.getNumberofgoalsreceived();
				int goaldif1 = te1.getNumberofgoalscored() - te1.getNumberofgoalsreceived();
				if(goaldif > goaldif1)
					return -1;
				else return 0;
				
			}
		
		
	}

}
