package com.savleen.apidemo1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class createProductRequestDtos {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
//no id->provided by backend
}
