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
import voting.app.entities.Voter;
import voting.app.repository.CandidateDao;
import voting.app.repository.ElectionCommissionerDao;
import voting.app.repository.RegionalCommissionerDao;
import voting.app.repository.VoterDao;
import voting.app.utilities.MultipartFileToBlobPropertyEditor;
import voting.app.utilities.PasswordEncryption;

@Controller

@RequestMapping("/regional-login")
public class RegionalCommissionerController {

	@Autowired
	CandidateDao candidateDao;
	
	@Autowired
	RegionalCommissionerDao regionalCommissionerDao;

	@Autowired
	ElectionCommissionerDao commissionerDao;
	
	@Autowired
	VoterDao voterDao;

	@GetMapping("/regional-commissioner-login")
	public String openRegionalCommissionerLogin() {
		return "regional-commissioner-login";
	}

	@GetMapping("/home")
	public String openLandingPage() {
		return "admin-home-page";
	}
	
	
	@GetMapping("/logout")
	public String openLogout(HttpSession session) {
		session.invalidate();
		return "regional-commissioner-login";
	}
	
	@PostMapping("/regionalLoginForm")
	public String handleRegionalLoginForm(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpServletRequest request,
//			HttpServletResponse response,
			Model model
			) {

//		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//	    response.setHeader("Pragma", "no-cache");
//	    response.setHeader("Expires", "0");
		
		LoginCredentials credentials = regionalCommissionerDao.login(email);

		System.out.println(credentials);	
		
		String new_hash = null;
		try {
			new_hash = PasswordEncryption.getHashedPassword(password, credentials.getSalt());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		HttpSession  session = request.getSession();
		ElectionAdmin admin = regionalCommissionerDao.getStatus(email);

		if (new_hash.equals(credentials.getHashedPassword()) 
				&& admin.isAdmin() && admin.isLoggedIn())
		{
			session.setAttribute("regAdmin", admin);
			return "regional-commissioner-dashboard";
		}
		else {
			model.addAttribute("message","Admin has not approved your Profile!");
			return "regional-commissioner-login";
		}

//		return "regional-commissioner-dashboard";
	}
	
	
	@GetMapping("regional-commissioner-registration")
	public String openRegManagerRegister() {
		return "regional-commissioner-registration";
	}
	
	@PostMapping("/handle-reg-manager-register")
	public String handleRegMangerRegister(@RequestParam("userName") String userName,
			@RequestParam("dob") String dob,
			@RequestParam("nationality") String nationality,
			@RequestParam("idProof") MultipartFile idProof,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		
		byte[] idProofArr;
		Blob idProofImg = null;
		try {
			idProofArr = idProof.getBytes();
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
		
		Date dateOfBirth=Date.valueOf(dob);
		ElectionAdmin regManager = new ElectionAdmin(userName,email,
				dateOfBirth, nationality, 
				idProofImg, password, hashedPassword, salt, false, false);
		int result=regionalCommissionerDao.addRegmanager(regManager);
		if(result==1)
		return "regional-commissioner-login";
		else
			return"failed";
		
	}


	@GetMapping("/candidate-approval")
	public String openCandidateApprovalPage(Model model) {
		
		List<Candidate> candidateApprovalList = candidateDao.displayCandidateListRegional();

		model.addAttribute("candidate_approval_list", candidateApprovalList);
		return "candidate-approval";
	}

	@PostMapping("/accessAction")
	public String accessAction(
			@RequestParam("access") String access,
			@RequestParam("id") String id,
			Model model
			) {
		
		
		int result = regionalCommissionerDao.candidateApproval(Integer.parseInt(id), access);
		System.out.println(Integer.parseInt(id) + " : " + access + " : " + result);
		
		if(result == 0) 
			return "failed";
		else 
			return "regional-commissioner-dashboard";
			//		System.out.println(access + " : "+ id);
//
//		return "redirect:candidate-approval";
	}

//	@GetMapping("/candidate_approval")
//	public String openCandidateList(Model model) {
//		List<Candidate> candidateApprovalList = candidateDao.displayCandidateList();
//
//		model.addAttribute("candidate_approval_list", candidateApprovalList);
//		return "candidate_approval";
//	}
	
	@GetMapping("/profile")
	public String openProfile(HttpSession session) {
//		System.out.println(session.getAttribute("regAdmin"));
		ElectionAdmin regAdmin=(ElectionAdmin) session.getAttribute("regAdmin");
		session.setAttribute("regAdminProfile", regAdmin);
		return "regional-commissioner-profile";
	}
	
	//--------------------------------------------
	
	
	
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
			return "regional-commissioner-dashboard";
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
	
	//---------------------------------------
	
	@GetMapping("regmanager-profile-update")
	public String openUpdatePage(
			HttpSession session,
			Model model) {
		ElectionAdmin regAdmin = (ElectionAdmin) session.getAttribute("regAdmin");
		model.addAttribute("regAdmin",regAdmin);
//		System.out.println(regAdmin);
		return "regmanager-profile-update";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Blob.class, new MultipartFileToBlobPropertyEditor());
    }
	
	@PostMapping("/regmanager-update")
	public String handleUpdatePage(
			@ModelAttribute ElectionAdmin regAdmin
			) {

		System.out.println(regAdmin);
		regionalCommissionerDao.updateRegAdminDetails(regAdmin);
		return "redirect:/";
	}
	
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
			return "success";
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
	
		

}
