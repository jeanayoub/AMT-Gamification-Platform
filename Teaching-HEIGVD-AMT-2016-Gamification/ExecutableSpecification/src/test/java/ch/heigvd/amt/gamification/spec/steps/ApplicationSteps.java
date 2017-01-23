package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.ApplicationPost;
import ch.heigvd.gamification.api.dto.ApplicationGet;
import ch.heigvd.gamification.api.dto.RegistrationSummary;
import com.google.gson.internal.LinkedTreeMap;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author J. Ayoub
 */
public class ApplicationSteps {

    private final DefaultApi api = new DefaultApi();
    private int applicationsCounter = 1;
    private int statusCode;
    private ApplicationGet applicationGet;
    private ApplicationPost applicationPost = new ApplicationPost();
    private List<ApplicationGet> applications;
    private ApiResponse response = null;
    private Long id = null;
        
    
    /***************************************************************************
     * General
     **************************************************************************/
    
    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, response.getStatusCode());
    }
    
    
    @Given("^I have an application with an id$")
    public void i_have_an_application_with_an_id() throws Throwable {
       
        try {
            statusCode = api.applicationsIdGetWithHttpInfo(id).getStatusCode();
            
          
        } catch (ApiException e) {
            System.err.println(e.getCause());
        }
        assertEquals(200, statusCode);
    }

    
    
    @Given("^I have an application in my database with an id$")
    public void i_have_an_application_in_my_database_with_a_id() throws Throwable {
       
        String randomApplicationName = "random-app-" + (applicationsCounter++) + "-" + System.currentTimeMillis();
        applicationPost.setName(randomApplicationName);
        applicationPost.setPassword("pass");
        
        try {
            response = api.applicationsPostWithHttpInfo(applicationPost);
            //String location = (String)response.getHeaders().get("location");
            Map map = (Map)response.getHeaders();
            String s = map.values().toString();
            s = s.substring(41);
            String [] l = s.split("]");
            id = Long.valueOf(location.substring(39));
             System.err.println("nelfnefnekwwnfewlnfewnlfnewldcnkldscnjdcjkdnscdjcdncjfnewfknew: " + s);
            statusCode = response.getStatusCode();
            //statusCode = api.applicationsPostWithHttpInfo(applicationPost).getStatusCode();
        } catch (ApiException e) {
            System.err.println(e.getCode());
        }
        assertEquals(201, statusCode);
    }
    

    @When("^I ask for the application with a GET on the /applications/id endpoint$")
    public void I_ask_for_the_application_with_a_GET_on_the_applications_id_endpoint() 
            throws Throwable {
        try {
            //response = api.applicationsIdGetWithHttpInfo(id);
            statusCode = api.applicationsIdGetWithHttpInfo(id).getStatusCode();
        } catch (ApiException e) {
            System.err.println(e.getCause());
        }
    }
    
    
    @When("^I ask for a list of registered apps with a GET on the /applications endpoint$")
    public void i_ask_for_a_list_of_registered_apps_with_a_GET_on_the_registrations_endpoint() throws Throwable {
        try {
            applications = api.applicationsGet();
            //response = api.applicationsGetWithHttpInfo();
            statusCode = api.applicationsGetWithHttpInfo().getStatusCode();
        } catch (ApiException e) {
            System.err.println(e.getCause());
        }
    }
    /***************************************************************************
     **************************************************************************/
    
    
    
    @Given("^I have an application payload$")
    public void i_have_an_application_payload() throws Throwable {
        String randomApplicationName = "random-app-" + (applicationsCounter++) + "-" + System.currentTimeMillis();
        applicationPost.setName(randomApplicationName);
        applicationPost.setPassword("password");
    }

    @When("^I POST it to the /applications endpoint$")
    public void i_POST_it_to_the_applications_endpoint() throws Throwable {
        try {
            //response = api.applicationsPostWithHttpInfo(applicationPost);
            statusCode = api.applicationsPostWithHttpInfo(applicationPost).getStatusCode();
        } catch (ApiException e) {
            System.err.println(e.getCode());
        }
    }


    @Then("^I see my app in the list$")
    public void i_see_my_app_in_the_list() throws Throwable {
        RegistrationSummary expected = new RegistrationSummary();
        expected.setApplicationName(applicationPost.getName());
        assertThat(applications).extracting("name").contains(applicationPost.getName());
    }

   

    @Given("^I have an existing application ID with a different payload$")
    public void i_have_an_existing_application_ID_with_a_different_payload() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @Then("^I don't see my app in the list$")
    public void i_don_t_see_my_app_in_the_list() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }



    
    /***************************************************************************
     * applicationRegistration
     **************************************************************************/
    
     /***************************************************************************
     **************************************************************************/
    
    
    

    /***************************************************************************
     * applicationModification
     **************************************************************************/
     @Given("^I have an application with a specific payload$")
     public void i_have_an_application_with_a_specific_payload() throws Throwable {
        applicationPost.setName("PutTest");
        applicationPost.setPassword("PutTestPass");
        applicationPost.setDescription("PutTest_Description");
     }
     
     
     @When("^I modify the payload of that application$")
     public void i_modify_the_payload_of_that_application() throws Throwable {
         applicationPost.setDescription("PutTest_Description_Success");
     }
     
     
    @When("^I PUT it to the /applications/id endpoint$")
    public void i_PUT_it_to_the_applications_id_endpoint() throws Throwable {

        try {         
            applications = api.applicationsGet();
            for (ApplicationGet app : applications) {
                if (app.getName().equals("PutTest")) {
                    id = app.getId();
                }
            }
            
            //response = api.applicationsIdPutWithHttpInfo(id, applicationPost);
            statusCode = api.applicationsIdPutWithHttpInfo(id, applicationPost).getStatusCode();
            api.applicationsIdPut(id, applicationPost);
        } catch (ApiException e) {
            System.err.println(e.getCode());
        }
    }
    
    
    @Then("^I see that the modifications took effect$")
    public void i_see_that_the_modifications_took_effect() throws Throwable {
        LinkedTreeMap ltp = (LinkedTreeMap) api.applicationsIdGet(id);
        String description = (String)ltp.get("description");
        
        assertEquals("PutTest_Description_Success", description);
    }
    
    /***************************************************************************
     **************************************************************************/
    
    
    
    /***************************************************************************
     * applicationDeletion
     **************************************************************************/
       
    @When("^I DELETE it using /applications/id endpoint$")
    public void i_DELETE_it_using_applications_id_endpoint() throws Throwable {
        try {
            //response = api.applicationsIdDeleteWithHttpInfo(id);
            statusCode = api.applicationsIdDeleteWithHttpInfo(id).getStatusCode();
        } catch (ApiException e) {
            System.err.println(e.getCause());
        }
    }
   
    
    @Given("^I have a nonexistent application id like (\\d+)$")
    public void i_have_a_nonexistent_application_id_like(int arg1) throws Throwable {
        id = new Long(arg1);
    }  
    /***************************************************************************
     **************************************************************************/
    
    

    
    /***************************************************************************
     * applicationReading
     **************************************************************************/
    
    
   /***************************************************************************
    **************************************************************************/ 
    
    
    

    
}
