package com.example.harm.blindwallgallery;

import java.io.Serializable;

/**
 * Created by harm on 20-2-2018.
 */

public class Blindwall implements Serializable {
    private String name;
    private String material;
    private String address;
    private String description;
    private String photographer;
    private String imageURL;
    private int addressNumber;

    public Blindwall(String name, String material, String address, String description, String fotograap, String imageURL, int addressNumber) {
        this.name = name;
        this.material = material;
        this.address = address;
        this.description = description;
        this.photographer = fotograap;
        this.imageURL = imageURL;
        this.addressNumber = addressNumber;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotographer() {
        return photographer;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getAddressNumber() {
        return addressNumber;
    }
}
