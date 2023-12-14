package voting.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import voting.app.entities.ElectionAdmin;
import voting.app.entities.LoginCredentials;
import voting.app.entities.NewElection;
import voting.app.entities.Voter;
import voting.app.repository.CandidateDao;
import voting.app.repository.ElectionCommissionerDao;
import voting.app.repository.VoterDao;
import voting.app.utilities.PasswordEncryptionWrapper;

@ExtendWith(MockitoExtension.class)
class VoterControllerTest {

    @Mock
    private VoterDao voterDaoMock;

    @Mock
    private CandidateDao candidateDaoMock;

    @Mock
    private NewElection electionMock;

    @Mock
    private ElectionCommissionerDao commissionerDao;

    @InjectMocks
    private VoterController voterController;
    
    @InjectMocks
    private ElectionCommissionerController commissionerController;
    
    @Mock
    private LoginCredentials credentials;
    
    @Mock
	private HttpServletRequest request;
    
    @Mock
	private HttpSession session;

	@Mock
	private Model model;
    
    @Mock
    private PasswordEncryptionWrapper passwordEncryptionWrapperMock;


    
    @Test
    	public void testHandleVoterLogin_SuccessfulLogin() {
        // Mock data for successful login scenario
        String email = "voter@gmail.com";
        String password = "1234";
        String hashedPassword = "d0eb50f7e06ca26cc464fac32d94ab8893369d61b02c62b40fa1e47b15529535";
        String salt = "Gy/fvhe81xAbnVf2uVVIfQ==";

        LoginCredentials mockCredentials = new LoginCredentials();
    	mockCredentials.setSalt(salt);
    	mockCredentials.setHashedPassword(hashedPassword);       
    	Voter mockVoter = new Voter();
        mockVoter.setStatus(true);

        List<NewElection> mockElectionDetails = new ArrayList<>();
        // Add mock election details if needed

        when(voterDaoMock.login(email)).thenReturn(mockCredentials);
        when(voterDaoMock.displayVoter(email)).thenReturn(mockVoter);
        when(voterDaoMock.displayElectionDetails(mockVoter)).thenReturn(mockElectionDetails);
        when(request.getSession()).thenReturn(session);

        String result = voterController.handleVoterLogin(email, password, request, model);

        assertEquals("voter-dashboard", result);
        verify(session, times(1)).setAttribute(eq("voter"), any(Voter.class));
        verify(session, times(1)).setAttribute(eq("eletionList"), anyList());
        // Verify other attributes as needed
    }
    
        @Test
        public void testHandleVoterLogin_NonApprovedProfile() {
        // Mock data for non-approved profile scenario
    	String email = "test@example.com";
    	String password = "password123";
    	String hashedPassword = "hashedPassword123";
    	String salt = "salt123";

    	LoginCredentials mockCredentials = new LoginCredentials();
    	mockCredentials.setSalt(salt);
    	mockCredentials.setHashedPassword(hashedPassword);
        Voter mockVoter = new Voter();
        mockVoter.setStatus(false); // Assuming the status is not approved

        when(voterDaoMock.login(email)).thenReturn(mockCredentials);
        when(voterDaoMock.displayVoter(email)).thenReturn(mockVoter);
        when(request.getSession()).thenReturn(session);

        String result = voterController.handleVoterLogin(email, password, request, model);

        assertEquals("voter-login", result);
        // Verify the model attribute for the message
    }
        
        @Test
        public void testHandleLoginFormData_SuccessfulLogin() {
            // Mock data for successful login scenario
            String email = "admin1@gmail.com";
            String password = "1234";
            String hashedPassword = "f73e5c6b76facc5100abe50f3d27fe64a4436c8c4ae63d029e1de009d6c8fed8";
            String salt = "otYqxeGaJvlzCLXJ8hvjNw==";
            LoginCredentials mockCredentials = new LoginCredentials();
        	mockCredentials.setSalt(salt);
        	mockCredentials.setHashedPassword(hashedPassword);
        	ElectionAdmin mockAdmin = new ElectionAdmin();
            mockAdmin.setLoggedIn(false);

            when(commissionerDao.login(email)).thenReturn(mockCredentials);
            when(commissionerDao.displayProfile(email)).thenReturn(List.of(mockAdmin));
            when(request.getSession()).thenReturn(session);

            String result = commissionerController.handleLoginFormData(email, password, request);

            assertEquals("election-commissioner-dashboard", result);
            verify(session, times(1)).setAttribute(eq("ElecCommAdmin"), any(ElectionAdmin.class));
            // Verify other expected behavior
        }

        @Test
        public void testHandleLoginFormData_UnsuccessfulLogin() {
            // Mock data for unsuccessful login scenario
            String email = "test@example.com";
            String password = "password123";
            String hashedPassword = "hashedPassword123";
            String salt = "salt123";
            LoginCredentials mockCredentials = new LoginCredentials();
        	mockCredentials.setSalt(salt);
        	mockCredentials.setHashedPassword(hashedPassword);
        	ElectionAdmin mockAdmin = new ElectionAdmin();
            mockAdmin.setLoggedIn(true); // Assuming the admin is already logged in

            when(commissionerDao.login(email)).thenReturn(mockCredentials);
            when(commissionerDao.displayProfile(email)).thenReturn(List.of(mockAdmin));

            String result = commissionerController.handleLoginFormData(email, password, request);

            assertEquals("election-commissioner-login", result);
            // Verify other expected behavior for unsuccessful login
        }
        
        
        
        
    
}