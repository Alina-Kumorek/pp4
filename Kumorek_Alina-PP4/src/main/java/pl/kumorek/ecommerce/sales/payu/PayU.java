package pl.kumorek.ecommerce.sales.payu;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class PayU {
    RestTemplate http;
    private PayUCredentials credentials;

    public PayU(RestTemplate http, PayUCredentials credentials) {
        this.http = http;
        this.credentials = credentials;
    }

    public OrderCreateResponse handle(OrderCreateRequest request) {
        var url = getUrl("/api/v2_1/orders");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", getToken()));
        headers.add("Content-Type", "application/json");

        HttpEntity<OrderCreateRequest> headerAwareRequest = new HttpEntity<>(request, headers);

        ResponseEntity<OrderCreateResponse> response = http.postForEntity(
                url, headerAwareRequest, OrderCreateResponse.class);

        return response.getBody();
    }

    private String getToken() {
        // var url = "https://secure.snd.payu.com/pl/standard/user/oauth/authorize";
        // String body = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
        //     "300746",
        //     "2ee86a66e5d97e3fadc400c9f19b065d");

        var url = getUrl("/pl/standard/user/oauth/authorize");
        String body = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
                credentials.getClientId(),
                credentials.getClientSecret()
                );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<AuthorizationResponse> response = http.postForEntity(
                url, request, AuthorizationResponse.class
        );

        return response.getBody().getAccessToken();
    }

    private String getUrl(String path) {
        return String.format("%s%s", credentials.getBaseUrl(), path);
    }
}
