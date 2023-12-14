package voting.app.entities;

public class VoterStatsbyConstitutionParticipation {
	
	
	private String constitution;
	private int totalVotersRegistered;
	private int totalVotesCasted;
	public String getConstitution() {
		return constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public int getTotalVotersRegistered() {
		return totalVotersRegistered;
	}
	public void setTotalVotersRegistered(int totalVotersRegistered) {
		this.totalVotersRegistered = totalVotersRegistered;
	}
	public int getTotalVotesCasted() {
		return totalVotesCasted;
	}
	public void setTotalVotesCasted(int totalVotesCasted) {
		this.totalVotesCasted = totalVotesCasted;
	}
	@Override
	public String toString() {
		return "VoterStatsbyConstitutionParticipation [constitution=" + constitution + ", totalVotersRegistered="
				+ totalVotersRegistered + ", totalVotesCasted=" + totalVotesCasted + "]";
	}
	
	
	
}
