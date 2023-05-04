package com.example.fontend.common;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ErrorHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    public result insertErrorHandler(DuplicateKeyException error){
        return result.error("数据库中已经有重复");
    }

}
