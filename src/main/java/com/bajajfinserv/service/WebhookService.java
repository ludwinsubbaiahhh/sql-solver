package com.bajajfinserv.service;

import com.bajajfinserv.dto.SolutionRequest;
import com.bajajfinserv.dto.WebhookRequest;
import com.bajajfinserv.dto.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    
    private final RestTemplate restTemplate;
    private final SqlQueryBuilder sqlQueryBuilder;

    public WebhookService(RestTemplate restTemplate, SqlQueryBuilder sqlQueryBuilder) {
        this.restTemplate = restTemplate;
        this.sqlQueryBuilder = sqlQueryBuilder;
    }

    public void processWebhookFlow() {
        try {
            logger.info("Starting webhook generation process...");
            
            // Step 1: Generate webhook
            WebhookResponse webhookResponse = generateWebhook();
            
            if (webhookResponse == null || webhookResponse.getWebhook() == null || webhookResponse.getAccessToken() == null) {
                logger.error("Failed to generate webhook or receive valid response");
                return;
            }
            
            logger.info("Webhook generated successfully: {}", webhookResponse.getWebhook());
            
            // Step 2: Build SQL query
            String sqlQuery = sqlQueryBuilder.buildQuery();
            logger.info("SQL Query generated: {}", sqlQuery);
            
            // Step 3: Submit solution
            submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), sqlQuery);
            
        } catch (Exception e) {
            logger.error("Error processing webhook flow", e);
        }
    }

    private WebhookResponse generateWebhook() {
        try {
            WebhookRequest request = new WebhookRequest(
                "John Doe",
                "REG12347",
                "john@example.com"
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                GENERATE_WEBHOOK_URL,
                HttpMethod.POST,
                entity,
                WebhookResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody();
            }

            logger.error("Failed to generate webhook. Status: {}", response.getStatusCode());
            return null;

        } catch (Exception e) {
            logger.error("Exception while generating webhook", e);
            return null;
        }
    }

    private void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        try {
            SolutionRequest request = new SolutionRequest(sqlQuery);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);

            HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                webhookUrl,
                HttpMethod.POST,
                entity,
                String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Solution submitted successfully! Response: {}", response.getBody());
            } else {
                logger.error("Failed to submit solution. Status: {}, Response: {}", 
                    response.getStatusCode(), response.getBody());
            }

        } catch (Exception e) {
            logger.error("Exception while submitting solution", e);
        }
    }
}

