package com.savleen.apidemo1.repository;

import com.savleen.apidemo1.models.product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface productRepo extends JpaRepository<product,Long> {
    product save(product entity);
    product findByIdIs(long id);
    List<product> findAll();
    product deleteById(long id);

    //hql query
    @Query("select p from product p where p.category.title=:title and p.id=:id")
    product getProductWithSpecificTitleAndId(@Param("title") String title, @Param("id") Long id);

    //native query
     @Query(value ="select * from product  where title=:title and id=:id" , nativeQuery = true )
    product getProductWithSomeTitleAndId(@Param("title") String title, @Param("id") Long id);

     //projection
    @Query("select p.id, p.title from product p where p.category.title=:title and p.id=:id")
    ProductWithTitleAndId getProductWithSpecificTitleAndId2(@Param("title") String title, @Param("id") Long id);


    @Query("select p from product p where p.category.title=:title")
    List<product> getProductsOfSpecificCategory(@Param("title") String title);


    Page<product> findByTitleContaining(String title, Pageable pageable);



}
