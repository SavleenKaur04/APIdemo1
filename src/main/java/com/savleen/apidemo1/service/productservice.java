package com.savleen.apidemo1.service;

import com.savleen.apidemo1.dtos.fakestoreProductDtos;
import com.savleen.apidemo1.models.Category;
import com.savleen.apidemo1.models.product;

import java.util.ArrayList;
import java.util.List;

public interface productservice {
    public product getsingleproduct(long id);
    //public product createProduct(createProductRequestDtos productreq);
    public product createProduct(String title, String description, String image,
                                        String category, double price);
    public List<product> getAllProducts();
    public product deleteProductById(long id);
    public ArrayList<String> getAllCategories();
    public List<product> getProductOfCategory(String category);
    public product updateProduct(Long id, String title, String image,
                                 String description, String category, double price);
}
