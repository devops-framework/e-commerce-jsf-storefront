package com.homelab.storefront.bean;

import com.homelab.storefront.client.OrderServiceClient;
import com.homelab.storefront.client.ProductServiceClient;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("storefrontBean")
@SessionScoped
public class StorefrontBean implements Serializable {

    @Inject
    private ProductServiceClient productServiceClient;

    @Inject
    private OrderServiceClient orderServiceClient;

    private String inputId;
    private String productResponse;
    private String orderResponse;

    public void fetchData() {
        this.productResponse = productServiceClient.getProductById(inputId);
        this.orderResponse = orderServiceClient.getOrderById(inputId);
    }

    // Getters and Setters
    public String getInputId() {
        return inputId;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public String getProductResponse() {
        return productResponse;
    }

    public void setProductResponse(String productResponse) {
        this.productResponse = productResponse;
    }

    public String getOrderResponse() {
        return orderResponse;
    }

    public void setOrderResponse(String orderResponse) {
        this.orderResponse = orderResponse;
    }
}
