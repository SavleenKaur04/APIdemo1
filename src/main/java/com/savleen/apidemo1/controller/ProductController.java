package com.savleen.apidemo1.controller;

import com.savleen.apidemo1.dtos.createProductRequestDtos;
import com.savleen.apidemo1.models.product;
import org.springframework.web.bind.annotation.*;
import com.savleen.apidemo1.service.productservice;

@RestController
public class ProductController {

    private productservice ps;
    public ProductController(productservice ps) {
        this.ps = ps;
    }

    @PostMapping("/products")
    public product createProduct(@RequestBody createProductRequestDtos productreq){
          return ps.createProduct(productreq.getTitle(),
                  productreq.getDescription(),
                  productreq.getImage(),
                  productreq.getCategory(),
                  productreq.getPrice()
                  );

    }
    @GetMapping("/products")
    public void getAllProducts(){

    }
    //jackson
    @GetMapping("/products/{id}")
    public product getProductById(@PathVariable("id") long id){
          return ps.getsingleproduct(id);

    }

    public void deleteProductById(long id){

    }
}
