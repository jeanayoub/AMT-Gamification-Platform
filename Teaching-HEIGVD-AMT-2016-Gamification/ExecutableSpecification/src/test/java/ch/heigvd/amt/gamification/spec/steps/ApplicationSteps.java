package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Applications;
import ch.heigvd.gamification.api.dto.RegistrationSummary;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class ApplicationSteps {

  private final DefaultApi api = new DefaultApi();

  private int applicationsCounter = 1;

  private int statusCode;
  private Applications applications;
  private List<RegistrationSummary> registrations;

  @Given("^I have an application payload$")
  public void i_have_an_application_payload() throws Throwable {
    applications = new Applications();
    String randomApplicationName = "random-app-" + (applicationsCounter++) + "-" + System.currentTimeMillis();
    applications.setApplicationName(randomApplicationName);
    applications.setPassword("toto123");
  }

  @When("^I POST it to the /applications endpoint$")
  public void i_POST_it_to_the_applications_endpoint() throws Throwable {
    try {
      ApiResponse response = api.applicationsPostWithHttpInfo(applications);
      statusCode = response.getStatusCode();
    } catch (ApiException e) {
      statusCode = e.getCode();
    }
  }

  @Then("^I receive a (\\d+) status code$")
  public void i_receive_a_status_code(int arg1) throws Throwable {
    assertEquals(arg1, statusCode);
  }

  @When("^I ask for a list of registered apps with a GET on the /registrations endpoint$")
  public void i_ask_for_a_list_of_registered_apps_with_a_GET_on_the_registrations_endpoint() throws Throwable {
    registrations = api.applicationsGet();
  }

  @Then("^I see my app in the list$")
  public void i_see_my_app_in_the_list() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    RegistrationSummary expected = new RegistrationSummary();
    expected.setApplicationName(applications.getApplicationName());

    assertThat(registrations).extracting("applicationName").contains(applications.getApplicationName());
  }

  @Given("^I know the name of an application$")
  public void i_know_the_name_of_an_application() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^I send a DELETE on the /registrations endpoint$")
  public void i_send_a_DELETE_on_the_registrations_endpoint() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }
}
