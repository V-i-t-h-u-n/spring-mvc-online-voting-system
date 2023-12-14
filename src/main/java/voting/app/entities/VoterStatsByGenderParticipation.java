package voting.app.entities;

public class VoterStatsByGenderParticipation {
	
	
	private int maleVoters;
	private int femaleVoters;
	private double percentageOfMaleVoted;
	private double percentageOfFemaleVoted;
	
	
	public double getPercentageOfMaleVoted() {
		return percentageOfMaleVoted;
	}
	public void setPercentageOfMaleVoted(double percentageOfMaleVoted) {
		this.percentageOfMaleVoted = percentageOfMaleVoted;
	}
	public double getPercentageOfFemaleVoted() {
		return percentageOfFemaleVoted;
	}
	public void setPercentageOfFemaleVoted(double percentageOfFemaleVoted) {
		this.percentageOfFemaleVoted = percentageOfFemaleVoted;
	}
	public int getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(int maleVoters) {
		this.maleVoters = maleVoters;
	}
	public int getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(int femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	
	
	@Override
	public String toString() {
		return "VoterStatsByGenderParticipation [maleVoters=" + maleVoters + ", femaleVoters=" + femaleVoters
				+ ", percentageOfMaleVoted=" + percentageOfMaleVoted + ", percentageOfFemaleVoted="
				+ percentageOfFemaleVoted + "]";
	}
	

}
