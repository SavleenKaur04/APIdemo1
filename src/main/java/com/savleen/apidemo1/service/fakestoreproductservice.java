package com.savleen.apidemo1.service;
import org.springframework.context.ApplicationContext;
import com.savleen.apidemo1.dtos.fakestoreProductDtos;
import com.savleen.apidemo1.models.Category;
import com.savleen.apidemo1.models.product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

//@Service
@Service("fakeStoreService")
@Primary
public class fakestoreproductservice implements productservice {

    private RestTemplate rt;
    private RedisTemplate<String,Object> redisTemplate;
    public fakestoreproductservice(RestTemplate rt,
                                   RedisTemplate<String,Object> redisTemplate){

        this.rt=rt;
        this.redisTemplate=redisTemplate;
    }
    public product getsingleproduct(long id){

        //fakestoreProductDtos fakedtos=rt.getForObject(
          //      "https://fakestoreapi.com/products/"+id,
            //    fakestoreProductDtos.class
              //  );
        product product=(product) redisTemplate.opsForValue().get(String.valueOf(id));
        if(product!=null){
            return product;
        }
        ResponseEntity<fakestoreProductDtos> responseEntity=rt.getForEntity("https://fakestoreapi.com/products/"+id,
                   fakestoreProductDtos.class);

        fakestoreProductDtos fakedtos=responseEntity.getBody();

        product p=new product();
        p.setId(fakedtos.getId());
        p.setTitle(fakedtos.getTitle());
        p.setDescription(fakedtos.getDescription());
        p.setImageurl(fakedtos.getImage());

        Category c=new Category();
        c.setTitle(fakedtos.getCategory());
        p.setCategory(c);
        redisTemplate.opsForValue().set(String.valueOf(id), p); //saving in redisc
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
           //call from prodservice to fakestore (3 party)
        fakestoreProductDtos fakedtos=rt.postForObject("https://fakestoreapi.com/products",
                fakestore,
                fakestoreProductDtos.class);

        return fakedtos.toproduct();
    }


    @Override
    public List<product> getAllProducts() {
        fakestoreProductDtos[] fake=
                rt.getForObject("https://fakestoreapi.com/products",//List<fakestoreProductDtos>.class
                fakestoreProductDtos[].class);

        List<product> p=new ArrayList<>();
        for(fakestoreProductDtos fakestoreProductDtos:fake){
            p.add(fakestoreProductDtos.toproduct());
        }

        return p;
    }
    public product deleteProductById(long id)  {
        //ResponseEntity<fakestoreProductDtos> f=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<fakestoreProductDtos> f=rt.exchange("https://fakestoreapi.com/products/{id}" ,
                    HttpMethod.DELETE,
                    requestEntity,
                    fakestoreProductDtos.class,
                    id);
            return f.getBody().toproduct();

        }
        //catch(HttpClientErrorException ex) {
        //   System.out.println("product not found");
        //}
       // return null ;
    //}
    public ArrayList<String> getAllCategories(){
        ArrayList<String> al=new ArrayList<>();
        try{
            al=rt.getForObject("https://fakestoreapi.com/products/categories",
                    ArrayList.class);
            //ArrayList<String> p=new ArrayList<>();
            //for(fakestoreProductDtos fakestoreProductDtos:f){
             // p.add(fakestoreProductDtos.pro());
            //}
            //product p=new product();
            //Category c=new Category();
            //c.setTitle(f.getCategory());
            //p.setCategory(c);
            //al.add(c.getTitle());
        }
        catch(Exception ex){
            System.out.println("not found");
        }
        return al;
    }
    public List<product> getProductOfCategory(String category){
        try{
            fakestoreProductDtos[] f=rt.getForObject("https://fakestoreapi.com/products/category"+category,
                    fakestoreProductDtos[].class);
            List<product> al=new ArrayList<>();
            for(fakestoreProductDtos fa:f){
                al.add(fa.toproduct());
            }
            return al;
        }
        catch(Exception ex){
            System.out.println("no such product");
        }
        return null;
    }
    public product updateProduct(Long id, String title, String image,
                                 String description, String category, double price){
        try {
            fakestoreProductDtos currentProduct = rt.getForObject(
                    "https://fakestoreapi.com/products/" + id,
                    fakestoreProductDtos.class
            );
            currentProduct.setCategory(category);
            currentProduct.setDescription(description);
            currentProduct.setTitle(title);
            currentProduct.setImage(image);
            currentProduct.setPrice(price);
            HttpEntity<fakestoreProductDtos> http = new HttpEntity<>(currentProduct);
            ResponseEntity<fakestoreProductDtos> f= rt.
                    exchange("https://fakestoreapi.com/products/{id}" ,
                            HttpMethod.PUT,
                            http,
                            fakestoreProductDtos.class);
            return f.getBody().toproduct();
        }
        catch(Exception e){
            System.out.println("error");
        }
        return null;

    }

}
