package org.fasttrackit.phoneagenda.transfer;

public class UpdateItemRequest {
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "UpdateItemRequest{" +
                "number='" + number + '\'' +
                '}';
    }
}
