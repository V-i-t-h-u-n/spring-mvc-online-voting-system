package voting.app.controller;

import java.sql.Blob;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import voting.app.entities.Candidate;
import voting.app.entities.Party;
import voting.app.repository.CandidateDao;
import voting.app.utilities.EmailSender;
import voting.app.utilities.MultipartFileToBlobPropertyEditor;

@Controller
public class UpdateCandidateProfileController {

	@Autowired
	CandidateDao candidateDao;

	@GetMapping("/candidate_details_updation")
	public String openCandidateUpdate() {
		return "candidate-update-details";
	}

	@PostMapping("/verifyEmail")
	public String verifyEmail(@RequestParam("email") String email,
			HttpServletRequest request,
			Model model) {

		if (candidateDao.isEmailInDatabase(email)) {
			String generatedOTP = generateOTP(6);
			HttpSession session = request.getSession();
			session.setAttribute("otp", generatedOTP);
			Candidate candidate = candidateDao.getCandidate(email);
			session.setAttribute("candidate-update", candidate);
			sendOTPEmail(email, generatedOTP);
			return "otp-page";
		} else {
			model.addAttribute("message","Candidate Email not found! check email");
			return "candidate-update-details";
		}
	}

	// Method to generate OTP
	private String generateOTP(int length) {
		// Range of characters to generate the OTP from
		String numbers = "0123456789";

		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * numbers.length());
			otp.append(numbers.charAt(index));
		}

		return otp.toString();
	}

	// Method to send OTP via email
	private void sendOTPEmail(String email, String otp) {

		EmailSender emailSender = new EmailSender();
		emailSender.sendEmail(email, otp);

	}

	@PostMapping("/update-details")
	public String openOTPPage(@RequestParam("otp") String otp,
			HttpSession session,Model model) {
		String sentOTP = (String) session.getAttribute("otp");
		if(sentOTP.equals(otp)) {
			Candidate candidate = (Candidate) session.getAttribute("candidate-update");
			model.addAttribute("candidate",candidate);
			return "candidate-profile-update";
		}

		return "failed";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Blob.class, new MultipartFileToBlobPropertyEditor());
    }
	
	@PostMapping("/candidate-update")
	public String handleCandidateUpdate(@ModelAttribute Candidate candidate) {
//		System.out.println(candidate);
		
		Blob partyLogoImg = null;
		Blob partyManifestoFile = null;

		List<Party> partyList = candidateDao.getAllParties();

		for (Party party : new HashSet<Party>(partyList)) {

			if (party.getPartyName().equals(candidate.getPartyName())) {

				partyLogoImg = party.getPartyLogo();
				partyManifestoFile = party.getPartyLogo();
				break;
			}

		}
		
		Candidate newCandidate = new Candidate
				(candidate.getCandidateId(),candidate.getCandidateName(), candidate.getCandidateDob(), candidate.getPartyName(), 
				partyManifestoFile, partyLogoImg, candidate.getIdProof(),
				candidate.getElectionId(), candidate.getElectionName(), candidate.isStatus(), candidate.getConstitution(),
				candidate.getVoteCount(), candidate.getEmail(), candidate.getCandidateProfilePic());
		
		System.out.println(newCandidate);
		int res = candidateDao.updateCandidate(newCandidate);

		System.out.println(res);
		return "candidate-home-page";
	}

}
