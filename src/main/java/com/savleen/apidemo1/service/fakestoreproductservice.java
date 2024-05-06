package com.savleen.apidemo1.service;

import com.savleen.apidemo1.dtos.fakestoreProductDtos;
import com.savleen.apidemo1.models.Category;
import com.savleen.apidemo1.models.product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class fakestoreproductservice implements productservice {

    private RestTemplate rt;
    public fakestoreproductservice(RestTemplate rt){
        this.rt=rt;
    }
    public product getsingleproduct(long id){

        fakestoreProductDtos fakedtos=rt.getForObject(
                "https://fakestoreapi.com/products/"+id, fakestoreProductDtos.class
                );
        product p=new product();
        p.setId(fakedtos.getId());
        p.setTitle(fakedtos.getTitle());
        p.setDescription(fakedtos.getDescription());
        p.setImageurl(fakedtos.getImage());

        Category c=new Category();
        c.setTitle(fakedtos.getCategory());
        p.setCategory(c);

        return p;
    }

    public product createProduct( String title, String description, String image,
                                  String category, double price){
        fakestoreProductDtos fakestore=new fakestoreProductDtos();
        fakestore.setCategory(category);
        fakestore.setDescription(description);
        fakestore.setTitle(title);
        fakestore.setImage(image);
        fakestore.setPrice(price);

        fakestoreProductDtos fakedtos=rt.postForObject("https://fakestoreapi.com/products",
                fakestore,
                fakestoreProductDtos.class);

        return fakedtos.toproduct();
    }
}
