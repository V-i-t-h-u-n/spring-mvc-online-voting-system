package voting.app.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import voting.app.entities.Candidate;
import voting.app.entities.LoginCredentials;
import voting.app.entities.NewElection;
import voting.app.entities.Party;
import voting.app.entities.VoteResult;
import voting.app.entities.Voter;
import voting.app.entities.VoterDetails;
import voting.app.entities.VoterStatsByAge;
import voting.app.entities.VoterStatsByGenderParticipation;
import voting.app.entities.VoterStatsByVotersParticipation;
import voting.app.entities.VoterStatsbyConstitutionParticipation;
import voting.app.excptHandler.VoterNotFoundExcpt;
import voting.app.repository.CandidateDao;
import voting.app.repository.ElectionCommissionerDao;
import voting.app.repository.VoterDao;
import voting.app.utilities.MultipartFileToBlobPropertyEditor;
import voting.app.utilities.PasswordEncryption;

@Controller
@RequestMapping(value = {"/voter","/regional-login/voter","/","regional-login"})
public class VoterController {

	@Autowired
	VoterDao voterDao;
	
	@Autowired
	CandidateDao candidateDao;

	@Autowired
	NewElection election;
	
	@Autowired
	ElectionCommissionerDao commissionerDao;


	@GetMapping("/voter-registration")
	public String openVoterRegistration() {
		return "voter-registration";
	}

	@GetMapping("/voter-login")
	public String openVoterLogin() {
		return "voter-login";
	}
	
	@GetMapping("/home-page")
	public String openHome() {
		return "voter-home-page";
	}
	
	@GetMapping("/logout")
	public String openLogout(HttpSession session) {
		session.invalidate();
		return "voter-login";
	}

	@PostMapping(value = { "/voterLogin", "/voterLoginForm" })
	public String handleVoterLogin(
			@RequestParam("email") String email, 
			@RequestParam("password") String password,
			HttpServletRequest request,
//			HttpServletResponse response,
			Model model) {
		
//		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//	    response.setHeader("Pragma", "no-cache");
//	    response.setHeader("Expires", "0");

		LoginCredentials credentials = voterDao.login(email);
		System.out.println(credentials);

		String new_hash = null;
		try {
			new_hash = PasswordEncryption.getHashedPassword(password, credentials.getSalt());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		Voter voter = voterDao.displayVoter(email);
		System.out.println(voter);
		
		List<NewElection> electionDetails = voterDao.displayElectionDetails(voter);
		List<NewElection> pastElections = voterDao.displayPastElectionDetails(voter);
		List<NewElection> futureElections = voterDao.displayFutureElectionDetails(voter);
		
 		
//		voterDao.displayElectionDetails();

		if (new_hash.equals(credentials.getHashedPassword())
				&& voter.isStatus())
//				&& admin.isAdmin())
		{
			System.out.println(voter.isStatus());
			
			session.setAttribute("voter", voter);
//			System.out.println(electionDetails);
			session.setAttribute("eletionList", electionDetails);
			session.setAttribute("pastElections", pastElections);
			session.setAttribute("futureElections", futureElections);
			return "voter-dashboard";
		} else {
			model.addAttribute("message","Admin Has not approved! your profile");
			return "voter-login";
		}

	}

	

	@PostMapping("/voter-Registration")
	public String handleVoterRegistration(
			@RequestParam("name") String voterName,
			@RequestParam("email") String voterEmail,
			@RequestParam("phone") String voterPhoneNumber,
			@RequestParam("dob") String voterDateOfBirth,
			@RequestParam("gender") String voterGender,
			@RequestParam("nationality") String voterNationality,
			@RequestParam("idProof") MultipartFile voterIdProof,
			@RequestParam("aadhar") String aadhar,
			@RequestParam("profilePic") MultipartFile voterProfilePic,
			@RequestParam("password") String password,
			@RequestParam("constitution") String constitution

	) {

		byte[] voterPicArr;
		Blob voterImg = null;
		try {
			voterPicArr = voterProfilePic.getBytes();
			voterImg = new SerialBlob(voterPicArr);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		byte[] idProofArr;
		Blob idProofImg = null;
		try {
			idProofArr = voterIdProof.getBytes();
			idProofImg = new SerialBlob(idProofArr);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		String salt = "";
		byte[] byteSalt = null;
		String hashedPassword = "";
		try {
			salt = PasswordEncryption.byteArrayToString(new PasswordEncryption().getSalt());

			byteSalt = PasswordEncryption.stringToByteArray(salt);

			hashedPassword = PasswordEncryption.getSecurePassword(password, byteSalt);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		Voter voter = new Voter(voterName, 
				voterEmail, voterPhoneNumber, 
				Date.valueOf(voterDateOfBirth), voterGender,
				voterNationality, idProofImg, 
				voterImg, salt, hashedPassword, 
				aadhar, false,constitution);

		System.out.println(voter);
		int result = voterDao.registerVoter(voter);
		return "voter-login";
	}

	@GetMapping("voter-profile")
	public String openProfile(HttpSession session) {
		Voter voter = (Voter) session.getAttribute("voter");
		session.setAttribute("voterProfile", voter);
		return "voter-profile";
	}

	@GetMapping("voter-profile-update")
	public String openUpdatePage(
			HttpSession session,
			Model model) {
		Voter voter = (Voter) session.getAttribute("voter");
		model.addAttribute("voter",voter);
		System.out.println(voter);
		return "voter-profile-update";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Blob.class, new MultipartFileToBlobPropertyEditor());
    }
	
	@PostMapping("/voter-update")
	public String handleUpdatePage(
			@ModelAttribute Voter voter
			) {

		System.out.println(voter);
		voterDao.updateVoterDetails(voter);
		return "redirect:updated-profile-page";
	}
	
	@GetMapping("voting-page")
	public String openVotingPage(
			@RequestParam("name") String elecName,
			@RequestParam("date") String elecDate,
			@RequestParam("electId") String electId,
			Model model,
			HttpSession session
 			) {
		System.out.println(elecDate + " : " + elecName);
		
		model.addAttribute("electionId",electId);
		
		Voter voter = (Voter) session.getAttribute("voter");
		
//		System.out.println(voter.getVoterName());
		
//		System.out.println(voter.getConstitution() + " " + electId);
		System.out.println(candidateDao.
				displayCandidateListbyConstitution
				(voter.getConstitution(),Integer.parseInt(electId)));
		
		session.setAttribute("candidateList", candidateDao
								.displayCandidateListbyConstitution
									(voter.getConstitution(),Integer.parseInt(electId)));

		return "voting-page";
	}
	
	@GetMapping("/updated-profile-page")
	public String openUpdatedProfile(
			HttpSession session,
			Model model) {
		
		Voter voter = (Voter)session.getAttribute("voter");
		
//		model.addAttribute("voter",
//				voterDao.displayVoter(voter.getVoterEmail()));
		
		
		return "voter-profile";
	}

	@GetMapping("voted") // Total voting percentage should be displayed
	public String openResultPage(
			@RequestParam("voting-status") String status,
			@RequestParam("electName") String elecName,
			@RequestParam("id") String candidateId,
			@RequestParam("partyName") String partyName,
			@RequestParam("elecId") String electionId,
			HttpSession session,
			Model model
			) {
			
			System.out.println(status + " " + elecName + " " + candidateId + " " + electionId);
			
//			int updatedVoteresult = candidateDao.updateVote(Integer.parseInt(candidateId));
			
			Voter voter = (Voter)session.getAttribute("voter");
			
			List<NewElection> elecList = 
							(List<NewElection>) session.getAttribute("eletionList");
			
			System.out.println("Voting"+elecList);
			
			int partyRes = voterDao.updatePartyVoteCount(candidateId,elecName,partyName,Integer.parseInt(electionId));
			int candidateRes = candidateDao.updateCandidateVoteCount
					(candidateId,Integer.parseInt(electionId),partyName);
			
			int result = voterDao.castVote(
			voter.getVoterId(),Integer.parseInt(electionId));

			if(result != 0) {
				
				List<VoterDetails> voterDetails = voterDao.displayVoterDetails(voter.getVoterId());
				model.addAttribute("voterDetails",voterDetails);
				System.out.println(voterDetails);
				return "voter_status-pdf_download";
			}
		
		return "success";
	}
		
	@GetMapping("/seeResult")
	public String handleSeeResult(
			@RequestParam("electionId") String electionId ,
			Model model,
			HttpSession session
			) {
		
//		String voterConstitution = ((Voter)session.getAttribute("voter")).getConstitution();
		System.out.println(electionId);
		List<VoteResult>partyVoteList = voterDao.displayPartyVoteCount(electionId);
		List<Candidate> candidateVoteList = voterDao.displayCandidateVoteCount(electionId);
		
		model.addAttribute("partyVoteList",partyVoteList);
		System.out.println(partyVoteList.get(0).getpartyName());
		model.addAttribute("candidateVoteList",candidateVoteList);
		
		
		return "validateResult";
	}
	
	
	@GetMapping("seeElectionStats")
	public String handleStats(
			@RequestParam("electionId") String electionId,
			Model model
			) {
		
		System.out.println(electionId);
		List<VoterStatsByAge> statsByAges = voterDao.getVoterStatsByAge();
		
		List<VoterStatsbyConstitutionParticipation> 
			constitutionParticipations = voterDao.getVoterStatsbyConstitutionParticipation(electionId);
		
		List<VoterStatsByGenderParticipation> genderParticipations = 
				voterDao.getVoterStatsByGenderParticipation(electionId);
		
		List<VoterStatsByVotersParticipation> statsByVotersParticipations = 
				voterDao.getVoterStatsByVotersParticipation(electionId);
		
		model.addAttribute("statsByAges",statsByAges);
		model.addAttribute("constitutionParticipations",constitutionParticipations);
		model.addAttribute("genderParticipations",genderParticipations);
		model.addAttribute("statsByVotersParticipations",statsByVotersParticipations);

//		System.out.println(statsByAges);
//		System.out.println(constitutionParticipations);
//		System.out.println(genderParticipations);
//		System.out.println(statsByVotersParticipations);
		
		
		return "election-stats";
	}
	
	@GetMapping("voting-result-page")
	public String openvotingResultPage(Model model) {
		
		List<NewElection> elecList = commissionerDao.getAllElections();
		System.out.println(elecList);
		model.addAttribute("elecList",elecList);
		
		return "voting-result-page";
	}
	
	
	@GetMapping("/voter-faq")
	public String openFaq() {
		return "voter-faq";
	}
	
	
}


	



//@PostMapping("/voterLoginForm")
//public String handleVoterDashboard(HttpSession session) {
//	
////	List<NewElection> electionDetails = voterDao.displayElectionDetails();
////	voterDao.displayElectionDetails();
////	System.out.println(electionDetails);
////	session.setAttribute("eletionList", electionDetails);
//	
//	
//	return "voter-dashboard";
//}
