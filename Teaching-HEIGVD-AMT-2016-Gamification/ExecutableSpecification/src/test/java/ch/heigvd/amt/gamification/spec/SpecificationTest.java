package ch.heigvd.amt.gamification.spec;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author J. Ayoub
 */
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/scenarios/", plugin = {"pretty", "html:target/cucumber"})
public class SpecificationTest {

    public SpecificationTest() {
    }

  /**
   * Test of main method, of class Specification.
   */
  @Test
  public void testMain() {
  }

}