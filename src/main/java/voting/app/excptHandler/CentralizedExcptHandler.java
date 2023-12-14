package voting.app.excptHandler;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralizedExcptHandler { 
	
	@ExceptionHandler(value=IndexOutOfBoundsException.class)
	public String itOfBoundsHandler(Model model) {
	model.addAttribute("errormessage","The content you are trying to access is empty!");
	return "exception_handling_page";
	}
	
	@ExceptionHandler(value=NullPointerException.class)
	public String nPEHandler(Model model) {
		model.addAttribute("errormessage","Null_Pointer_Exception------PROVIDED DATA SHOULD NOT BE NULL");
		return "exception_handling_page";
		}
	
	@ExceptionHandler(value=IllegalStateException.class)
	public String illegalst(Model model) {
		model.addAttribute("errormessage","------Login Again");
		return "exception_handling_page";
		}
	
	
	
	
	
	@ExceptionHandler(value=ArithmeticException.class)
	public String arthematicExcptHandler(Model model) {
		model.addAttribute("errormessage","Arithmetic_Exception------Check The Data Provided");
		return "exception_handling_page";
		}
	@ExceptionHandler(value=IOException.class)
	public String iOExcptHandler(Model model) {
		model.addAttribute("errormessage","IO_Exception------Provided Crt Data");
		return "exception_handling_page";
		}
	
	@ExceptionHandler(value=DuplicateKeyException.class)
	public String duplicate(Model model) {
		model.addAttribute("errormessage","This action is not Possible because, Action already performed!");
		return "exception_handling_page";
		}
	
	@ExceptionHandler(value=EmptyResultDataAccessException.class)
	public String emptyAccess(Model model) {
		model.addAttribute("errormessage","Required Data Not Found!");
		return "exception_handling_page";
		}
	
	@ExceptionHandler(value=SQLException.class)
	public String sQLExcptHandler(Model model) {
		model.addAttribute("errormessage","SQL_Exception------Error Occured In DataBase Side");
		return "exception_handling_page";
		}
	@ExceptionHandler(value=VoterNotFoundExcpt.class)
	public String voterNotFoundExcptHandler(Model model) {
		model.addAttribute("errormessage","Voter_not_found_Exception------Voter Detail Is Not Precent");
		return "VoterNotFoundExcpt";
		}
	@ExceptionHandler(value=RegionalAdminNotFoundExcpt.class)
	public String regionalAdminNotFoundExcptHandler(Model model) {
		model.addAttribute("errormessage","Regional_Admin_Not_Found_Exception------Regional Admin Detail Is Not Present");
		return "exception_handling_page";
		}
	@ExceptionHandler(value=ElectionCommisionerNotFound.class)
	public String ElectionCommisionerNotFoundHandler(Model model) {
		model.addAttribute("errormessage","Election_Commisioner_Not_Found_Exception----Election Commisioner Detail Is Not Precent");
		return "exception_handling_page";
		}
	@ExceptionHandler(value=Exception.class)
	public String allException(Model model) {
		model.addAttribute("errormessage","-----Exception Occured");
		return "exception_handling_page";
		}
}
