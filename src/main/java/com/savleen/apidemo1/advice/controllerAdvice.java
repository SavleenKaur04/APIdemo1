package com.savleen.apidemo1.advice;

import com.savleen.apidemo1.dtos.ErrorDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class controllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException(){
        ErrorDto errorDto=new ErrorDto();
        errorDto.setMsg("something wrong");
        ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto,
                HttpStatusCode.valueOf(404));
        return responseEntity;
    }
}
