package com.savleen.apidemo1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class searchRequestDto {
    private String query;
    private int pageNumber;
    private int pageSize;
}
