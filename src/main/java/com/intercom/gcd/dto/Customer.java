package com.intercom.gcd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    @JsonProperty("user_id")
    private long userId;
    private String name;

    public Customer copy(){
        Customer customer = new Customer();
        customer.setName(getName());
        customer.setUserId(getUserId());
        return customer;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
