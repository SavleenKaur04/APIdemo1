package com.savleen.apidemo1.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class product extends baseModel implements Serializable {
    //private long Id;
    private String title;
    private String description;
    private double price;
    private String imageurl;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;

}
