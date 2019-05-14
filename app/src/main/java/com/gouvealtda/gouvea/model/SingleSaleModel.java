package com.gouvealtda.gouvea.model;

public class SingleSaleModel {

    private int status;
    private double amount;

    protected SingleSaleModel() {
    }

    protected int getStatus() {
        return status;
    }

    protected void setStatus(int status) {
        this.status = status;
    }

    protected double getAmount() {
        return amount;
    }

    protected void setAmount(double amount) {
        this.amount = amount;
    }
}