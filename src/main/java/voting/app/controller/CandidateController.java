package voting.app.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
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
import voting.app.entities.Party;
import voting.app.repository.CandidateDao;
import voting.app.repository.VoterDao;

@Controller
@RequestMapping(value = { "/candidate","/voter","/" })
public class CandidateController {

	@Autowired
	CandidateDao candidateDao;

	@Autowired
	VoterDao voterDao;

	@GetMapping("/candidate_registration")
	public String openCandidateRegister(Model model) {
		model.addAttribute("partyList", candidateDao.getAllParties());
		System.out.println("partyList \n" + candidateDao.getAllParties());
		model.addAttribute("electionList", voterDao.displayElectionDetails());

		System.out.println("candidate controller - " + voterDao.displayElectionDetails());

		return "candidate_registration";
	}

	@GetMapping("/landing-page")
	public String openLandingPage() {
		return "redirect:/";
	}

	@PostMapping("/candidateRegistrationForm")
	public String openCandidateRegistrationForm(
			@RequestParam("candidateName") String candidateName,
			@RequestParam("candidateDob") String candidateDob, 
			@RequestParam("partyName") String partyName,
//			@RequestParam("partyManifesto") MultipartFile partyManifesto, 
//			@RequestParam("partyLogo") MultipartFile partyLogo,
			@RequestParam("idProof") MultipartFile idProof, 
			@RequestParam("electionId") String electionIdAndName,
			@RequestParam("constitution") String constitution, 
			@RequestParam("email") String email,
			@RequestParam("dp")MultipartFile displayImage) {

		String electionId = electionIdAndName.split(",")[0];
		String electName = electionIdAndName.split(",")[1];

		Blob partyLogoImg = null;
		Blob partyManifestoFile = null;

		List<Party> partyList = candidateDao.getAllParties();

		for (Party party : new HashSet<Party>(partyList)) {

			if (party.getPartyName().equals(partyName)) {

				partyLogoImg = party.getPartyLogo();
				partyManifestoFile = party.getPartyLogo();
				break;
			}

		}

//		System.out.println("elect id - "+electionId.split(",")[1]);

//		String electionId = electionIdAndName.split(",")[0];
//		String electName = electionIdAndName.split(",")[1];

//		byte[] partyLogoArr;
//		Blob partyLogoImg = null;
//		try {
//			partyLogoArr = partyLogo.getBytes();
//			partyLogoImg = new SerialBlob(partyLogoArr);
//		} catch (IOException | SQLException e) {
//			e.printStackTrace();
//		}

		byte[] idProofArr;
		Blob idProofImg = null;
		try {
			idProofArr = idProof.getBytes();
			idProofImg = new SerialBlob(idProofArr);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		byte[] displayImageArr;
		Blob displayImg = null;
		try {
			displayImageArr = displayImage.getBytes();
			displayImg = new SerialBlob(displayImageArr);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		

//		byte[] partyManifestoArr;
//		Blob partyManifestoFile = null;
//		try {
//			partyManifestoArr = partyManifesto.getBytes();
//			partyManifestofile = new SerialBlob(partyManifestoArr);
//		} catch (IOException | SQLException e) {
//			e.printStackTrace();
//		}

		Candidate candidate = new Candidate(candidateName, Date.valueOf(candidateDob), partyName, partyManifestoFile,
				partyLogoImg, idProofImg, Integer.parseInt(electionId),
				electName, false, constitution, 0, email,displayImg);
		System.out.println(candidate);
		candidateDao.registerCandidate(candidate);
		System.out.println(candidateName);

//		candidateDao.insertStatusTable(candidate.getCandidateId());

		return "candidate-home-page";
	}

	@GetMapping("/candidate_list")
	public String openCandidateList(Model model) {
		List<Candidate> candidateList = candidateDao.displayCandidateList();
		model.addAttribute("candidate_list", candidateList);
		return "candidate_list";
	}
	
	@GetMapping("/candidate-faq")
	public String openFaq() {
		return "candidate-faq";
	}
	
	@GetMapping("/home-page")
	public String openHome() {
		return "home-page";
	}

	
}

//@PostMapping("/candidateRegistrationForm")
//public String openCandidateRegistrationForm(
//		@RequestParam String candidateName,
//		@RequestParam String candidateDob,
//		@RequestParam String partyName,
//		@RequestParam String partyManifesto,
//		@RequestParam String partyLogo,
//		@RequestParam String idProof,
//		@RequestParam String electionName
//		) {
//	
//	
//	//file to byte[] byte[] 
//	//bytes = IOUtils.toByteArray(fis);
//	//https://stackoverflow.com/questions/51570268/convert-file-or-byte-into-blob-in-java
//	
//	byte[] partyLogoArr = partyLogo.getBytes();
//	Blob img = null;
//	try {
//		img = new SerialBlob(partyLogoArr);
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	
//	
//	Candidate candidate = 
//			new Candidate(candidateName, Date.valueOf(candidateDob), partyName, img, img, img, electionName);
//	
//	candidateDao.registerCandidate(candidate);
//	
//	System.out.println(candidateName);
//	return "success";
//}

// file to byte[] byte[]
// bytes = IOUtils.toByteArray(fis);
// https://stackoverflow.com/questions/51570268/convert-file-or-byte-into-blob-in-java

//@PostMapping("/candidateRegistrationForm")
//public String openCandidateRegistrationForm(
//		@ModelAttribute Candidate candidate
//		) {
//	System.out.println(candidate);
//	return "success";
//}
//