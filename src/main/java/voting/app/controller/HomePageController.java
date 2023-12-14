package voting.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

	@GetMapping("/home-page")
	public String openHomePage() {
		return "home-page";
	}
	
	@GetMapping("/candidate-home-page")
	public String openCandidateHome() {
		return "candidate-home-page";
	}
	
	@GetMapping("/admin-home-page")
	public String openAdminHome() {
		return "admin-home-page";
	}
	
	@GetMapping("/voter-home-page")
	public String openVoterHome() {
		return "voter-home-page";
	}
	
	
}
