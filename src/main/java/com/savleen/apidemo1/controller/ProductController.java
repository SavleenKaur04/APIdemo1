package com.savleen.apidemo1.controller;
import com.savleen.apidemo1.dtos.ErrorDto;
import com.savleen.apidemo1.dtos.fakestoreProductDtos;
import com.savleen.apidemo1.dtos.updateDto;
import com.savleen.apidemo1.models.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import com.savleen.apidemo1.dtos.createProductRequestDtos;
import com.savleen.apidemo1.models.product;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.savleen.apidemo1.service.productservice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.service.annotation.PutExchange;
import org.springframework.web.servlet.function.ServerRequest;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private productservice ps;

    public ProductController(@Qualifier("fakeStoreService") productservice ps) {
        this.ps = ps;
    }

    @PostMapping("/products")
    public product createProduct(@RequestBody createProductRequestDtos productreq) {
        return ps.createProduct(
                productreq.getTitle(),
                productreq.getDescription(),
                productreq.getImage(),
                productreq.getCategory(),
                productreq.getPrice()
        );

    }

    @GetMapping("/products")
    //public List<product> getAllProducts(){
    //   return ps.getAllProducts();
    public ResponseEntity<List<product>> getAllProducts() {
        List<product> data = ps.getAllProducts();
        ResponseEntity<List<product>> re = new ResponseEntity<>(data,
                HttpStatusCode.valueOf(201));
        return re;
    }

    //jackson
    @GetMapping("/products/{id}")
    public product getProductById(@PathVariable("id") long id) {
        return ps.getsingleproduct(id);  //if breaks stops due to exception flow break nd goes to method handleNullPointer

    }
    //  @ExceptionHandler(NullPointerException.class)
    //public ResponseEntity<ErrorDto> handleNullPointerException(){
    //  ErrorDto errorDto=new ErrorDto();
    //errorDto.setMsg("something wrong");
    //ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto,
    //      HttpStatusCode.valueOf(404));
    //return responseEntity;
    //}

    @DeleteMapping("/delete/{id}")
    public product deleteProductById(@PathVariable("id") long id) {
        return ps.deleteProductById(id);
        //return new ResponseEntity<>(ps.deleteProductById(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/categories")
    public ArrayList<String> getAllCategories(){
        return ps.getAllCategories();
    }
    @GetMapping("/product/{category}")
     public List<product> getProductOfCategory(@PathVariable("category") String category){
       return ps.getProductOfCategory(category);
     }
     @PutMapping("/product/{id}")
     public product updateProduct(@PathVariable("id") Long id,
                                  @RequestBody updateDto update){
        return ps.updateProduct(id,
                update.getTitle(),
                update.getDescription(),
                update.getImage(),
                update.getCategory(),
                update.getPrice());

         //ResponseEntity<product> responseEntity=new ResponseEntity<>(data, HttpStatusCode.valueOf(200));
         //return responseEntity;
     }
}

