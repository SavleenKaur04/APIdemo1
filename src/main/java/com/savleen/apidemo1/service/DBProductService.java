package com.savleen.apidemo1.service;

import com.savleen.apidemo1.dtos.fakestoreProductDtos;
import com.savleen.apidemo1.models.Category;
import com.savleen.apidemo1.models.product;
import com.savleen.apidemo1.repository.categoryRepo;
import com.savleen.apidemo1.repository.productRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("DBService")
public class DBProductService implements productservice{

    private productRepo productrepo;
    private categoryRepo categoryrepo;
    public DBProductService(productRepo productrepo, categoryRepo categoryrepo){
        this.productrepo=productrepo;
        this.categoryrepo=categoryrepo;
    }

    public product getsingleproduct(long id){
        return productrepo.findByIdIs(id);
        //return null;
    }

    //public product createProduct(createProductRequestDtos productreq);
    public product createProduct(String title, String description, String image,
                                 String categoryTitle, double price){

        product product=new product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageurl(image);
        product.setPrice(price);
        //first set category in category table
        Category categoryFoundFromDB= categoryrepo.findByTitle(categoryTitle);//if category exists
        if(categoryFoundFromDB==null){
           Category newcategory=new Category();
           newcategory.setTitle(categoryTitle);
           categoryFoundFromDB=categoryrepo.save(newcategory);//if category doesn't exist-save in DB
            //instead of line 37-->product.setcategory(newcategory)-->cascade persist(db m save nhi kiya, directly product m daaldiya)
        }
        //now set this category in product table
        product.setCategory(categoryFoundFromDB);
        return productrepo.save(product); //directly returning a product a
    }
    public List<product> getAllProducts(){
        return productrepo.findAll();
        //return null;
    }
    public product deleteProductById(long id) {
        return productrepo.deleteById(id);
    }

    public ArrayList<String> getAllCategories(){
        List<Category> l= categoryrepo.findAll();
        ArrayList<String> al=new ArrayList<>();
        for(Category list:l){
            al.add(list.getTitle());
        }
        return al;
    }
    public List<product> getProductOfCategory(String category){
       return productrepo.getProductsOfSpecificCategory(category);

    }
    public product updateProduct(Long id, String title, String image,
                                                String description, String category, double price){
        return null;
   }
}
