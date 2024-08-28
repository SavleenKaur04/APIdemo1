package com.savleen.apidemo1;

import com.savleen.apidemo1.models.Category;
import com.savleen.apidemo1.models.product;
import com.savleen.apidemo1.repository.ProductWithTitleAndId;
import com.savleen.apidemo1.repository.categoryRepo;
import com.savleen.apidemo1.repository.productRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

@SpringBootTest
class ApIdemo1ApplicationTests {
    @Autowired
    productRepo pr;

    @Autowired
    categoryRepo cr;

    @Test
    void contextLoads() {
    }
    @Test
    public void testingQuery(){
        product p=pr.getProductWithSpecificTitleAndId("electronic",1L);
        System.out.println(p.getTitle());
    }
    @Test
    public void testingQuery2(){
        ProductWithTitleAndId p=pr.getProductWithSpecificTitleAndId2("electronic",1L);
        System.out.println(p.getTitle());
        System.out.println(p.getId());
    }
    @Test
    @Transactional
    public void testingFetchTypes(){
        Category c=cr.findByTitle("electronic");
        //System.out.println(c.getTitle()); //single query
        System.out.println((c.getProduct())); //double query
    }
    @Test
    public void testingFetchTypes2(){
        Optional<Category> c=cr.findById(1l);
        //System.out.println(c.getTitle()); //single query
        System.out.println(c.get().getTitle()) ;//double query
    }

    @Test
    @Transactional
    public void nplus1Problems(){
        //get all categories, find products of each category, print title for each product
       List<Category> c=cr.findAll();
        for(Category cat: c){
            for(product prod: cat.getProduct()){
                System.out.println(prod.getTitle());
            }
        }
    }
}
