package com.savleen.apidemo1.dtos;

import lombok.Getter;
import lombok.Setter;
import com.savleen.apidemo1.models.Category;
import com.savleen.apidemo1.models.product;

@Getter
@Setter
public class fakestoreProductDtos {
    private long Id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    public product toproduct(){
        product p=new product();
        p.setId(getId());
        p.setTitle(getTitle());
        p.setDescription(getDescription());
        p.setImageurl(getImage());

        Category c=new Category();
        c.setTitle(getCategory());
        p.setCategory(c);

        return p;
    }

}
