package au.com.telstra.simcardactivator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ActivationController {

    @PostMapping("/activate")
    public String activateSIM(@RequestBody ActivationRequest request) {
        // Extract ICCID from the request
        String iccid = request.getIccid();

        // Prepare request payload for the actuator microservice
        ActivationRequest actuatorRequest = new ActivationRequest();
        actuatorRequest.setIccid(iccid);

        // Send POST request to the actuator microservice
        RestTemplate restTemplate = new RestTemplate();
        ActivationResponse response = restTemplate.postForObject("http://localhost:8444/actuate", actuatorRequest, ActivationResponse.class);

        // Construct JSON response
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Boolean> jsonResponse = new HashMap<>();
        jsonResponse.put("success", response != null && response.isSuccess());

        try {
            // Convert the map to JSON string
            return objectMapper.writeValueAsString(jsonResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"success\": false}"; // Return a default error response
        }
    }
}
