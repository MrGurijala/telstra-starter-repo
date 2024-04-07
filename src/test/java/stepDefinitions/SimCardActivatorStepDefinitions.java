package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private String iccid;
    private boolean activationStatus;

    @Given("the ICCID {string} is submitted for activation")
    public void the_iccid_is_submitted_for_activation(String iccid) {
        this.iccid = iccid;
    }

    @When("the activation request is processed")
    public void the_activation_request_is_processed() {
        // Send activation request to the microservice
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8080/activate",
                "{\"iccid\": \"" + iccid + "\", \"customerEmail\": \"test@example.com\"}",
                String.class);

        // Extract activation status from the response
        activationStatus = response.getBody().contains("success");
    }

    @Then("the activation status should be {string}")
    public void the_activation_status_should_be(String expectedStatus) {
        boolean expectedActivationStatus = expectedStatus.equals("successful");
        Assert.assertEquals(expectedActivationStatus, activationStatus);
    }

}