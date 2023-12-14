package voting.app.entities;

public class VoterStatsByAge {
	
	private String ageRange;
	private int totalCountOfVoters;
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public int getTotalCountOfVoters() {
		return totalCountOfVoters;
	}
	public void setTotalCountOfVoters(int totalCountOfVoters) {
		this.totalCountOfVoters = totalCountOfVoters;
	}
	@Override
	public String toString() {
		return "VoterStatsByAge [ageRange=" + ageRange + ", totalCountOfVoters=" + totalCountOfVoters + "]";
	}
	
	

}
