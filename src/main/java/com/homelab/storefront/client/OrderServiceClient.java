
package com.homelab.storefront.client;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class OrderServiceClient {

    @Inject
    @ConfigProperty(name = "order.service.base.url")
    private String orderServiceBaseUrl;

    public String getOrderById(String id) {
        String fullUrl = "http://" + orderServiceBaseUrl;

        if (id == null || id.trim().isEmpty()) {
            return "{\"error\": \"Order ID cannot be empty\"}";
        }
        Client client = ClientBuilder.newClient();
        try {
            return client.target(fullUrl)
                    .path("api")
                    .path("checkout")
                    .path(id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to fetch from Order Service: " + e.getMessage() + "\"}";
        } finally {
            client.close();
        }
    }
}
