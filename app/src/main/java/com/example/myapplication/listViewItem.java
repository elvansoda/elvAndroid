package com.example.myapplication;

public class listViewItem {
    private String ProductName;
    private int Amount;
    private int Price;

    public void setName(String name) { ProductName = name; }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getName() {
        return ProductName;
    }

    public int getAmount() {
        return Amount;
    }

    public int getPrice() {
        return Price;
    }
}
