package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.ApplicationPost;
import ch.heigvd.gamification.api.dto.ApplicationGet;
import ch.heigvd.gamification.api.dto.BadgeGet;
import ch.heigvd.gamification.api.dto.BadgePost;
import ch.heigvd.gamification.api.dto.LoginPost;
import ch.heigvd.gamification.api.dto.EventPost;
import ch.heigvd.gamification.api.dto.PointScalePost;
import ch.heigvd.gamification.api.dto.PointScaleGet;
import ch.heigvd.gamification.api.dto.RulePost;
import ch.heigvd.gamification.api.dto.RuleGet;
import ch.heigvd.gamification.api.dto.RegistrationSummary;
import com.google.gson.internal.LinkedTreeMap;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author J. Ayoub
 */
public class ApplicationSteps {

    private final DefaultApi  api      = new DefaultApi();
    private       ApiResponse response = null;
  
    
    private int applicationsCounter = 1;
    private int badgesCounter       = 1;
    
    
    private ApplicationGet  applicationGet  = new ApplicationGet();
    private ApplicationPost applicationPost = new ApplicationPost();
    private BadgePost       badgePost       = new BadgePost();
    private BadgeGet        badgeGet        = new BadgeGet();
    private LoginPost       loginPost       = new LoginPost();
    private EventPost       eventPost       = new EventPost();
    private PointScalePost  pointScalePost  = new PointScalePost();
    private RulePost        rulePost        = new RulePost();
    private RuleGet         ruleGet         = new RuleGet();
    
    private List<ApplicationGet> applications;
    private List<BadgeGet>       badges;
    private List<PointScaleGet>  pointScales;
    private List<RuleGet>        rules;
    
    private Long   id           = null;
    private Long   idBadge      = null;
    private Long   idPointScale = null;
    private Long   idRule       = null;
    private String token        = null;
    private int    statusCode;    
    
    
    
/*******************************************************************************
* Application
*******************************************************************************/
    
    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, statusCode);
    }
    
    
    @Given("^I have an application with an id$")
    public void i_have_an_application_with_an_id() throws Throwable {
        try {
            statusCode = api.applicationsIdGetWithHttpInfo(id).getStatusCode();
                      
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
        assertEquals(200, statusCode);
    }

    
    
    @Given("^I have an application in my database with an id$")
    public void i_have_an_application_in_my_database_with_a_id() throws Throwable {
       
        String randomApplicationName = "random-app-" + (applicationsCounter++) + "-" + System.currentTimeMillis();
        applicationPost.setName(randomApplicationName);
        applicationPost.setPassword("pass");
        
        i_POST_it_to_the_applications_endpoint();
        
        assertEquals(201, statusCode);
    }

    
    @Given("^I have an application payload$")
    public void i_have_an_application_payload() throws Throwable {
        String randomApplicationName = "random-app-" + (applicationsCounter++) + "-" + System.currentTimeMillis();
        applicationPost.setName(randomApplicationName);
        applicationPost.setPassword("password");
    }

    @When("^I POST it to the /applications endpoint$")
    public void i_POST_it_to_the_applications_endpoint() throws Throwable {
        try {
            response = api.applicationsPostWithHttpInfo(applicationPost);
            statusCode = response.getStatusCode();
            
            Map<String,List<String>> map = response.getHeaders();
            String s = map.get("Location").get(0);
            String [] list = s.split("/");
            id = Long.valueOf(list[list.length - 1]);
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCode());
        }
    }


    @Then("^I see my app in the list$")
    public void i_see_my_app_in_the_list() throws Throwable {
        RegistrationSummary expected = new RegistrationSummary();
        expected.setApplicationName(applicationPost.getName());
        assertThat(applications).extracting("name").contains(applicationPost.getName());
    }

    
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
            statusCode = api.applicationsIdPutWithHttpInfo(id, applicationPost).getStatusCode();
            api.applicationsIdPut(id, applicationPost);
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCode());
        }
    }
    
    
    @Then("^I see that the modifications took effect$")
    public void i_see_that_the_modifications_took_effect() throws Throwable {
         String description = null;
        try {
            LinkedTreeMap ltp = (LinkedTreeMap) api.applicationsIdGet(id);
            description = (String)ltp.get("description");
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
        assertEquals("PutTest_Description_Success", description);
    }
    

    @When("^I DELETE it using /applications/id endpoint$")
    public void i_DELETE_it_using_applications_id_endpoint() throws Throwable {
        try {
            statusCode = api.applicationsIdDeleteWithHttpInfo(id).getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }
   
    
    @Given("^I have a nonexistent application id like (\\d+)$")
    public void i_have_a_nonexistent_application_id_like(int arg1) throws Throwable {
        id = new Long(arg1);
    }  

    
    
     @When("^I ask for the application with a GET on the /applications/id endpoint$")
    public void I_ask_for_the_application_with_a_GET_on_the_applications_id_endpoint() 
            throws Throwable {
        try {
            statusCode = api.applicationsIdGetWithHttpInfo(id).getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }
    
    
    @When("^I ask for a list of registered apps with a GET on the /applications endpoint$")
    public void i_ask_for_a_list_of_registered_apps_with_a_GET_on_the_registrations_endpoint() throws Throwable {
        try {
            applications = api.applicationsGet();
            statusCode = api.applicationsGetWithHttpInfo().getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }
    

    @Given("^I have an application with an id and a related badge with an idBadge$")
    public void i_have_an_application_with_an_id_and_a_related_badge_with_an_idBadge() throws Throwable {
        i_have_a_badge_payload_and_a_token();
        i_POST_it_to_the_badges_endpoint();
        i_ask_for_the_badges_using_badges_endpoint();
        idBadge = badges.get(0).getId();
    }

    @When("^I ask for the badge using /applications/id/badges/idBadge endpoint$")
    public void i_ask_for_the_badge_using_applications_id_badges_idBadge_endpoint() throws Throwable {
        
        try {
           response = api.applicationsIdBadgesIdBadgeGetWithHttpInfo(id, idBadge);
           statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }


    
    @When("^I ask for the events using /applications/id/events endpoint$")
    public void i_ask_for_the_events_using_applications_id_events_endpoint() throws Throwable {
        try {
           response = api.applicationsIdEventsGetWithHttpInfo(id);
           statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }

/*******************************************************************************
*******************************************************************************/ 
    
    
    
    
    
    
    
/*******************************************************************************
* Badge
*******************************************************************************/    
    @Given("^I have a badge payload and a token$")
    public void i_have_a_badge_payload_and_a_token() throws Throwable {
        String randomBadgeName = "badge-" + (badgesCounter++) + "-" + System.currentTimeMillis();
        badgePost.setName(randomBadgeName);
        badgePost.setDescription("description bagde banale");
        badgePost.setIcon("icon");
        
        i_have_an_application_in_my_database_with_a_id();
        i_POST_it_to_the_login_endpoint();
    }

    @When("^I POST it to the /badges endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {
        try {
            response = api.badgesPostWithHttpInfo(badgePost, token);
            statusCode = response.getStatusCode();
             
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }
    
    
    @When("^I ask for the badges using /badges endpoint$")
    public void i_ask_for_the_badges_using_badges_endpoint() throws Throwable {
        try {
            response = api.badgesGetWithHttpInfo(token);
            statusCode = response.getStatusCode();
            badges = api.badgesGet(token);
            
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }
    
    @When("^I ask for the badge using /badges/id$")
    public void i_ask_for_the_badge_using_badges_id() throws Throwable {
        try {
            response = api.badgesIdGetWithHttpInfo(idBadge, token);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }

    @When("^I DELETE it using /badges/id endpoint$")
    public void i_DELETE_it_using_badges_id_endpoint() throws Throwable {
        try {
            response = api.badgesIdDeleteWithHttpInfo(idBadge, token);
            statusCode = response.getStatusCode();
            
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }

    

    @When("^I modify the payload of that badge$")
    public void i_modify_the_payload_of_that_badge() throws Throwable {
        badgePost.setName("badgeTestPut_Success");
        badgePost.setDescription("descriptionTestPut_Sucess");
        badgePost.setIcon("iconTestPut_Sucess");
    }

    @When("^I PUT it to the /badges/id endpoint$")
    public void i_PUT_it_to_the_badges_id_endpoint() throws Throwable {
        try {
            response = api.badgesIdPutWithHttpInfo(idBadge, token, badgePost);
            statusCode = response.getStatusCode();
            
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }

    @Then("^I see that the badge modifications took effect$")
    public void i_see_that_the_badge_modifications_took_effect() throws Throwable {
        String name = null;
        try {
            
            LinkedTreeMap ltp = (LinkedTreeMap) api.badgesIdGet(idBadge, token);
            name = (String)ltp.get("name");
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
        assertEquals("badgeTestPut_Success", name);
    }
/*******************************************************************************
*******************************************************************************/     
    
    

    
    
/*******************************************************************************
* Login
*******************************************************************************/

    @When("^I POST it to the /login endpoint$")
    public void i_POST_it_to_the_login_endpoint() throws Throwable {
        

        loginPost.setName(applicationPost.getName());
        loginPost.setPassword(applicationPost.getPassword());
        
        try {
            response = api.loginPostWithHttpInfo(loginPost);
            statusCode = response.getStatusCode();
             LinkedTreeMap ltp = (LinkedTreeMap)api.loginPost(loginPost);
             token = (String)ltp.get("token");
   
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    } 
/*******************************************************************************
*******************************************************************************/    
    
    
    
    
    
    
    
    
    
/*******************************************************************************
* Event
*******************************************************************************/
    
    @Given("^I have an event payload with a token$")
    public void i_have_an_event_payload_with_a_token() throws Throwable {
        eventPost.setEventType("I Like it");
        eventPost.setUserAppId(new Long(1));
        
        i_have_an_application_in_my_database_with_a_id();
        i_POST_it_to_the_login_endpoint();
    }

    @When("^I POST it to /events endpoint$")
    public void i_POST_it_to_events_endpoint() throws Throwable {
        try {
            response = api.eventsPostWithHttpInfo(eventPost, token);
            statusCode = response.getStatusCode();
            
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }
    
    
    @When("^I ask for the event with a GET on /applications/id/events$")
    public void i_ask_for_the_event_with_a_GET_on_applications_id_events() throws Throwable {
        try {
            response = api.applicationsIdEventsGetWithHttpInfo(id);
            statusCode = response.getStatusCode();
            
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }
    
/*******************************************************************************
*******************************************************************************/  








/*******************************************************************************
* PointScale
*******************************************************************************/

    @Given("^I have a point scale payload with a token$")
    public void i_have_a_point_scale_payload_with_a_token() throws Throwable {
         pointScalePost.setName("pointScale");
        
        i_have_an_application_in_my_database_with_a_id();
        i_POST_it_to_the_login_endpoint();
    }

    @When("^I POST it to the /pointScales endpoint$")
    public void i_POST_it_to_the_pointScales_endpoint() throws Throwable {
        try {
            response = api.pointScalesPostWithHttpInfo(pointScalePost, token);
            statusCode = response.getStatusCode();
            Map map = response.getHeaders();
            String s = map.get("Location").toString();
            s = s.substring(1, s.length()-1);
            String [] list = s.split("/");
            idPointScale = Long.valueOf(list[list.length - 1]);
            
            System.err.println("nelfnefnekwwnfewlnfewnlfnewldcnkldscnjdcjkdnewfknew+++++++++++++++: " + idPointScale);
            
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }

    @When("^I ask for the pointScales with a GET on the /pointScales endpoint$")
    public void i_ask_for_the_pointScales_with_a_GET_on_the_pointScales_endpoint() throws Throwable {
       try {
            response = api.pointScalesGetWithHttpInfo(token);
            pointScales = api.pointScalesGet(token);
            statusCode = response.getStatusCode();
            
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }


   
    
    @When("^I ask for the pointScale with a GET on the /pointScales/id endpoint$")
    public void i_ask_for_the_pointScale_with_a_GET_on_the_pointScales_id_endpoint() throws Throwable {
        try {
            
            response = api.pointScalesIdGetWithHttpInfo(idPointScale, token);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }

    @When("^I DELETE it using /pointScales/id endpoint$")
    public void i_DELETE_it_using_pointScales_id_endpoint() throws Throwable {
        System.err.println("nelfnefnekwwnfewlnfewnlfnewldcnkldscnjdcjkdnewfknew-----------: " + token);
            System.err.println("nelfnefnekwwnfewlnfewnlfnewldcnkldscnjdcjkdnewfknew-----------: " + idPointScale);
        try { 
            response = api.pointScalesIdDeleteWithHttpInfo(idPointScale, token);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }

    
    @When("^I modify the payload of that PointScale$")
    public void i_modify_the_payload_of_that_PointScale() throws Throwable {
        pointScalePost.setName("pointScale_Success");
    }
    
    @When("^I PUT it to the /pointScales/id endpoint$")
    public void i_PUT_it_to_the_pointScales_id_endpoint() throws Throwable {
        try {
            response = api.pointScalesIdPutWithHttpInfo(idPointScale, pointScalePost, token);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
}

    @Then("^I see that the pointScale modifications took effect$")
    public void i_see_that_the_pointScale_modifications_took_effect() throws Throwable {
        String name = null;
        try {
            LinkedTreeMap ltp = (LinkedTreeMap) api.pointScalesIdGet(idPointScale, token);
            name = (String)ltp.get("name");
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
        assertEquals("pointScale_Success", name);
    }
/*******************************************************************************
*******************************************************************************/ 
 
    
    
    
    
    
    
    
    
/*******************************************************************************
* Rule
*******************************************************************************/    
    @Given("^I have a rule payload with a token$")
    public void i_have_a_rule_payload_with_a_token() throws Throwable {
         
        i_have_an_application_with_an_id_and_a_related_badge_with_an_idBadge();
        pointScalePost.setName("pointScale");
        i_POST_it_to_the_pointScales_endpoint();
        
        rulePost.setPoint(new Long(15));
        rulePost.setAwardBadgeId(idBadge);
        rulePost.setAwardPointScaleId(idPointScale);
        rulePost.setEventType("Big one");
        
    }

    @When("^I POST it to the /rules endpoint$")
    public void i_POST_it_to_the_rules_endpoint() throws Throwable {
        try {
            response = api.rulesPostWithHttpInfo(rulePost, token);
            statusCode = response.getStatusCode();
            
            Map map = response.getHeaders();
            String s = map.get("Location").toString();
            s = s.substring(1, s.length()-1);
            String [] list = s.split("/");
            idRule = Long.valueOf(list[list.length - 1]);
            
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }



    @When("^I ask for the rules with a GET on the /rules endpoint$")
    public void i_ask_for_the_rules_with_a_GET_on_the_rules_endpoint() throws Throwable {
       try {
            response = api.rulesGetWithHttpInfo(token);
            statusCode = response.getStatusCode();
      
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }

    @When("^I ask for the pointScale with a GET on the /rules/id endpoint$")
    public void i_ask_for_the_pointScale_with_a_GET_on_the_rules_id_endpoint() throws Throwable {
        try {
            
            System.err.println("nelfnefnekwwnfewlnfewnlfnewldcnkldscnjdcjkdnewfknew-----------: " + token);
            System.err.println("nelfnefnekwwnfewlnfewnlfnewldcnkldscnjdcjkdnewfknew-----------: " + idRule);
            response = api.rulesIdGetWithHttpInfo(idRule, token);
            statusCode = response.getStatusCode();
      
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }



    
    @When("^I modify the payload of that rule$")
    public void i_modify_the_payload_of_that_rule() throws Throwable {
        rulePost.setEventType("Dislike");
    }

    
    @When("^I PUT it to the /rules/id endpoint$")
    public void i_PUT_it_to_the_rules_id_endpoint() throws Throwable {
        try {
            response = api.rulesIdPutWithHttpInfo(idRule, token, rulePost);
            statusCode = response.getStatusCode();
      
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }

    
    @Then("^I see that the rule modifications took effect$")
    public void i_see_that_the_rule_modifications_took_effect() throws Throwable {
        String eventType = null;
        try {
            LinkedTreeMap ltp = (LinkedTreeMap) api.rulesIdGet(idPointScale, token);
            eventType = (String)ltp.get("");
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
        assertEquals("Dislike", eventType);
    }
    

    
    @When("^I DELETE it using /rules/id endpoint$")
    public void i_DELETE_it_using_rules_id_endpoint() throws Throwable {
        try { 
            response = api.rulesIdDeleteWithHttpInfo(idRule, token);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
            System.err.println(e.getCause());
        }
    }
/*******************************************************************************
*******************************************************************************/

    
    
    
}



