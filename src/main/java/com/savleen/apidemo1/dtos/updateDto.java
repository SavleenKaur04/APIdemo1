package com.savleen.apidemo1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class updateDto {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
