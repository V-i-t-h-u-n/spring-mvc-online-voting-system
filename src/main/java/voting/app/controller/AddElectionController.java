package voting.app.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import voting.app.entities.Candidate;
import voting.app.entities.NewElection;
import voting.app.repository.CandidateDao;
import voting.app.repository.ElectionCommissionerDao;
import voting.app.repository.VoterDao;

@Controller
@RequestMapping(value = {"/add-new-election","/regional-login/add-new-election","/"})
public class AddElectionController {
	@Autowired
	ElectionCommissionerDao commissionerDao;
	
	@Autowired
	VoterDao voterDao;
	
	@Autowired
	CandidateDao candidateDao;

	@GetMapping("/add-election")
	public String openAddElectionPage() {
		return "add-election";
	}

	
	@PostMapping("/addElectionFormDetails")
	public String handleAddElectionFormDetails(

			@RequestParam("electionList") String electionName, 
			@RequestParam("electionDate") String electionDate) {

		NewElection newElection = new NewElection();
		newElection.setElectionName(electionName);
		newElection.setElectionDate(Date.valueOf(electionDate));
		newElection.setStatus(true);

		return commissionerDao.addElection(newElection) == 1 ? "/admin-home-page" : "failed";

	}
	
	@GetMapping("add-party")
	public String openAddPartyPage(Model model) {
		model.addAttribute("electionList",voterDao.displayElectionDetails());
		return "add-party";
	}
	
	@PostMapping("/addPartyForm")
	public String handleAddPartyForm(
			@RequestParam("electionId") int electionId,
			@RequestParam("partyLogo") MultipartFile partyLogo,
			@RequestParam("partyManifesto") MultipartFile partyManifesto,
			@RequestParam("newParty") String partyName
			) {
		
		byte[] partyLogoArr;
		Blob partyLogoImg = null;
		try {
			partyLogoArr = partyLogo.getBytes();
			partyLogoImg = new SerialBlob(partyLogoArr);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		
		byte[] partyManifestoArr;
		Blob partyManifestoImg = null;
		try {
			partyManifestoArr = partyManifesto.getBytes();
			partyManifestoImg = new SerialBlob(partyManifestoArr);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(electionId + " - " + partyName);
		
		int addPartyResult = commissionerDao
				.setParty(electionId,partyName,partyLogoImg,partyManifestoImg);
		
		return "redirect:/admin-home-page";
	}
	
	
//	@GetMapping("election-commissioner-dashboard")
//	public String openDashboard() {
//		return "election-commissioner-dashboard";
//	}
	
	
	
}
