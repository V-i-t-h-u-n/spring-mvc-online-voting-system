package voting.app.entities;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class NewElection {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private int electionId;
	private String electionName;
	private Date electionDate;
	private boolean status;
	

	public NewElection(String electionName, Date electionDate,
			boolean status) {
		super();
		this.electionName = electionName;
		this.electionDate = electionDate;
		this.status = status;
	}

	public NewElection(int electionId, String electionName,
			Date electionDate, boolean status) {
		super();
		this.electionId = electionId;
		this.electionName = electionName;
		this.electionDate = electionDate;
		this.status = status;
	}

	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}

	public Date getElectionDate() {
		return electionDate;
	}

	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getElectionId() {
		return electionId;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NewElection() {
		super();
	}

	@Override
	public String toString() {
		return "NewElection [jdbcTemplate=" + jdbcTemplate + ", electionId=" + electionId + ", electionName="
				+ electionName + ", electionDate=" + electionDate + ", status=" + status + "]";
	}

	
	

}
