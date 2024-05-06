package com.savleen.apidemo1.service;

import com.savleen.apidemo1.models.product;

public interface productservice {
    public product getsingleproduct(long id);
    //public product createProduct(createProductRequestDtos productreq);
    public product createProduct(String title, String description, String image,
                                        String category, double price);
}
