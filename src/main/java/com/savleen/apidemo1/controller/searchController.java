package com.savleen.apidemo1.controller;

import com.savleen.apidemo1.dtos.searchRequestDto;
import com.savleen.apidemo1.models.product;
import com.savleen.apidemo1.service.searchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class searchController {

    private searchService searchService;
    public searchController(searchService searchService){
        this.searchService=searchService;
    }
    @PostMapping("/search")
    public Page<product> search(@RequestBody searchRequestDto searchRequestDto){
          return searchService.search(searchRequestDto.getQuery(),
                  searchRequestDto.getPageNumber(),
                  searchRequestDto.getPageSize());
    }
}
