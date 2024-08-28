package com.savleen.apidemo1.service;
import com.savleen.apidemo1.models.product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.savleen.apidemo1.repository.productRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.PageFormat;
//import java.awt.print.Pageable;
import java.awt.print.Printable;

@Service
public class searchService {

    private productRepo productRepo;
    public searchService(productRepo productRepo){
        this.productRepo=productRepo;
    }
    public Page<product> search(String query, int pageNumber, int pageSize){
        Sort sort=Sort.by("title").ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return productRepo.findByTitleContaining(query, pageable);


    }
}
