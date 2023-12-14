package voting.app.entities;

public class Vote {
	
	private int voteCount;

	public Vote(int voteCount) {
		super();
		this.voteCount = voteCount;
	}

	public Vote() {
		super();
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "Vote [voteCount=" + voteCount + "]";
	}
	
	
}
