package com.savleen.apidemo1.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class product {
    private long Id;
    private String title;
    private String description;
    private double price;
    private String imageurl;
    private Category category;

}
