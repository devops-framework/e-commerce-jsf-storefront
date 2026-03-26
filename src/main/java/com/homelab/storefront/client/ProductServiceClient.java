
package com.homelab.storefront.client;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class ProductServiceClient {

    @Inject
    @ConfigProperty(name = "product.service.base.url")
    private String productServiceBaseUrl;

    public String getProductById(String id) {
        String fullUrl = "http://" + productServiceBaseUrl;

        if (id == null || id.trim().isEmpty()) {
            return "{\"error\": \"Product ID cannot be empty\"}";
        }
        Client client = ClientBuilder.newClient();
        try {
            return client.target(fullUrl)
                    .path("api")
                    .path("items")
                    .path(id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to fetch from Product Service: " + e.getMessage() + "\"}";
        }
    }
}
