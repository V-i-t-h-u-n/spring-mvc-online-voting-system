package voting.app.entities;

public class VoterStatsByVotersParticipation {
	private int totalVotersRegistered;
	private int totalVotesCasted;
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
		return "VoterStatsByVotersParticipation [totalVotersRegistered=" + totalVotersRegistered + ", totalVotesCasted="
				+ totalVotesCasted + "]";
	}
	
	
	

}
