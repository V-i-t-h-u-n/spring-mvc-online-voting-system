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
import voting.app.entities.ElectionAdmin;
import voting.app.entities.LoginCredentials;
import voting.app.entities.NewElection;
import voting.app.entities.RegionalCommissioner;
import voting.app.entities.Voter;
import voting.app.repository.CandidateDao;
import voting.app.repository.ElectionCommissionerDao;
import voting.app.repository.RegionalCommissionerDao;
import voting.app.repository.VoterDao;
import voting.app.utilities.MultipartFileToBlobPropertyEditor;
import voting.app.utilities.PasswordEncryption;

@Controller
@RequestMapping(value = {"/","/add-new-election"})
public class ElectionCommissionerController {

	@Autowired
	ElectionCommissionerDao commissionerDao;
	
	@Autowired
	ElectionAdmin commissioner;
	
	@Autowired
	LoginCredentials credentials;
	
	@Autowired
	CandidateDao candidateDao;
	
	@Autowired
	VoterDao voterDao;
	
	@Autowired
	RegionalCommissionerDao regionalCommissionerDao;
	
	@Autowired
	NewElection election;

	@GetMapping("/")
	public String openLandingPage() {
		return "home-page";
	}

	@GetMapping("/home")
	public String openLanding() {
		return "admin-home-page";
	}
	
	@GetMapping("logout")
	public String handleLogout() {
		
		return "election-commissioner-login";
	}

	@GetMapping("/election-commissioner-login")
	public String openLoginForm() {
		return "election-commissioner-login";
	}

	@PostMapping("/elecCommLoginFormDetails")
	public String handleLoginFormData(@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpServletRequest request
			) {
		
		//HttpServletResponse response
//		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//	    response.setHeader("Pragma", "no-cache");
//	    response.setHeader("Expires", "0");
		
		System.out.println(email);
		LoginCredentials credentials = commissionerDao.login(email);

		System.out.println(credentials);

		ElectionAdmin admin = commissionerDao.displayProfile(email).get(0);
		System.out.println("admin - \n"+admin);
//		System.out.println(admin.isAdmin() + " @ " + admin.isLoggedIn());

		HttpSession session = request.getSession();

		String new_hash = null;
		try {
			new_hash = PasswordEncryption.getHashedPassword(password, credentials.getSalt());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (new_hash.equals(credentials.getHashedPassword())
			 && !admin.isLoggedIn()
				) {
			session.setAttribute("ElecCommAdmin", admin);
			return "election-commissioner-dashboard";
		} else {
			return "election-commissioner-login";
		}
	}
	
	
	@GetMapping("/candidate-approval")
	public String openCandidateApprovalPage(Model model) {
		
		List<Candidate> candidateApprovalList = candidateDao.displayCandidateListRegional();

		model.addAttribute("candidate_approval_list", candidateApprovalList);
		return "candidate-approval";
	}
	
	
	@PostMapping("/accessAction")
	public String candidateaccessAction(
			@RequestParam("access") String access,
			@RequestParam("id") String id,
			Model model
			) {
		
		
		int result = regionalCommissionerDao.candidateApproval(Integer.parseInt(id), access);
		System.out.println(Integer.parseInt(id) + " : " + access + " : " + result);
		
		if(result == 0) 
			return "failed";
		else 
			return "election-commissioner-dashboard";
			//		System.out.println(access + " : "+ id);
//
//		return "redirect:candidate-approval";
	}

	
	@GetMapping("/voter-approval")
	public String openVoterApprovalPage(Model model) {
		
		List<Voter> voterApprovalList = voterDao.displayAllVoterList();

		model.addAttribute("voter_approval_list", voterApprovalList);
		return "voter-approval";
	}
	
	@PostMapping("/voterAccessAction")
	public String voteraccessAction(
			@RequestParam("access") String access,
			@RequestParam("id") String id,
			Model model
			) {
		
		
		int result = regionalCommissionerDao.voterApproval(Integer.parseInt(id), access);
		System.out.println(Integer.parseInt(id) + " : " + access + " : " + result);
		
		if(result == 0) 
			return "failed";
		else 
			return "election-commissioner-dashboard";
		}
	

//	@GetMapping("/election-commissioner-profile")
//	public String handleElectionCommissionerProfile(Model model,
//			HttpSession session
//			) {
//
//		System.out.println("Elec - "+session.getAttribute("ElecCommAdmin"));
//		if(session.getAttribute("ElecCommAdmin") != null)
//		{
//			return "election-commissioner-profile";
//		}
//		else {
//			model.addAttribute("message", "Login to continue" );
//			return "election-commissioner-login";
//		}
	
	
//	=========================
	
	@GetMapping("/election-commissioner-profile")
	public String handleElectionCommissionerProfile(Model model,
			HttpSession session
			) {

		System.out.println("Elec - "+session.getAttribute("ElecCommAdmin"));
		if(session.getAttribute("ElecCommAdmin") != null)
		{
			ElectionAdmin elecCommAdmin=(ElectionAdmin) session.getAttribute("ElecCommAdmin");
			session.setAttribute("elecCommAdmin", elecCommAdmin);
			return "election-commissioner-profile";
		}
		else {
			model.addAttribute("message", "Login to continue" );
			return "election-commissioner-login";
		}
	
		
//		List<ElectionAdmin> profileList = commissionerDao.displayProfile();

		
	}
		
	@GetMapping("/regmanager-approval")
	public String openRegManagerApprovalPage(Model model) {
		
		List<RegionalCommissioner> regManagerApprovalList = 
				regionalCommissionerDao.displayRegionalManagerList();

		model.addAttribute("regManager_approval_list", regManagerApprovalList);
		return "regional-manager-approval";
	}

	@PostMapping("/regManagerAccessAction")
	public String accessAction(
			@RequestParam("access") String access,
			@RequestParam("id") String id,
			Model model
			) {
		
		
		int result = commissionerDao.regManagerApproval(Integer.parseInt(id), access) ;
		System.out.println(Integer.parseInt(id) + " : " + access + " : " + result);
		
		if(result == 0) 
			return "failed";
		else 
			return "election-commissioner-dashboard";
	
}

//	@GetMapping("/voting-result-page")
//	public String openvotingResultPage(Model model) {
//		
//		List<NewElection> elecList = commissionerDao.getAllElections();
//		System.out.println(elecList);
//		model.addAttribute("elecList",elecList);
//		
//		return "voting-result-page";
//	}
	
	
	//=======================================
	
	@GetMapping("elecomm-profile-update")
	public String openUpdatePage(
			HttpSession session,
			Model model) {
		ElectionAdmin ElecCommAdmin = (ElectionAdmin) session.getAttribute("ElecCommAdmin");
		model.addAttribute("electionAdmin",ElecCommAdmin);
//		System.out.println(regAdmin);
		return "elecomm-profile-update";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Blob.class, new MultipartFileToBlobPropertyEditor());
    }
	
	@PostMapping("/elecomm-update")
	public String handleUpdatePage(
			@ModelAttribute ElectionAdmin electionAdmin
			) {

		System.out.println("test - \n"+electionAdmin);
		commissionerDao.updateEleCommDetails(electionAdmin);
		return "redirect:/";
	}
	
	//=======================
//	@GetMapping("delete-election-page")
//	public String deleteElection() {
//		
//		return "delete-election-page";
//	}
	
	
	
	@GetMapping("/delete_election")
	public String deleteElection(Model model) {
		
		List<NewElection> toDeleteElection= commissionerDao.viewDeleteElection();
		model.addAttribute("toDeleteElection", toDeleteElection);
		return "delete_election";
		
	}
	
	@GetMapping("/handle_delete_election")
	public String handleDeleteElection(@RequestParam("electionId") String electionId) {
		
		NewElection handleDeleteElection=new NewElection();
		int result=commissionerDao.deleteElection(Integer.parseInt(electionId));
		 if(result==1) {
			return "admin-home-page";
		}else {
			return "failed";
		}
		
	};
	
	@GetMapping("/deleted_election")
	public String viewDeletedElection(Model model) {
		
		List<NewElection> deletedElection= commissionerDao.viewDeletedElection();
		model.addAttribute("deletedElection", deletedElection);
		return "deleted_election";
	}
	
	
	@GetMapping("/admin-faq")
	public String openFaq() {
		return "admin-faq";
	}
	
	
	
//	@GetMapping("regional-commissioner-registration")
//	public String openRegManagerRegister() {
//		return "regional-commissioner-registration";
//	}
//	
//	@PostMapping("/handle-reg-manager-register")
//	public String handleRegMangerRegister(@RequestParam("userName") String userName,
//			@RequestParam("dob") String dob,
//			@RequestParam("nationality") String nationality,
//			@RequestParam("idProof") MultipartFile idProof,
//			@RequestParam("email") String email,
//			@RequestParam("password") String password) {
//		
//		byte[] idProofArr;
//		Blob idProofImg = null;
//		try {
//			idProofArr = idProof.getBytes();
//			idProofImg = new SerialBlob(idProofArr);
//		} catch (IOException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		String salt = "";
//		byte[] byteSalt = null;
//		String hashedPassword = "";
//		try {
//			salt = PasswordEncryption.byteArrayToString(new PasswordEncryption().getSalt());
//
//			byteSalt = PasswordEncryption.stringToByteArray(salt);
//
//			hashedPassword = PasswordEncryption.getSecurePassword(password, byteSalt);
//
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		
//		Date dateOfBirth=Date.valueOf(dob);
//		ElectionAdmin regManager = new ElectionAdmin(userName,email,
//				dateOfBirth, nationality, 
//				idProofImg, password, hashedPassword, salt, false, false);
//		int result=commissionerDao.addRegmanager(regManager);
//		if(result==1)
//		return "regional-commissioner-login";
//		else
//			return"failed";
//		
//	}

	
	
}
