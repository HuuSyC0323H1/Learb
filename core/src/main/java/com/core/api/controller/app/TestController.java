package com.core.api.controller.app;

import com.core.api.forms.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app")
public class TestController {

    @GetMapping()
    public ResponseObject test(){
        return new ResponseObject("TEST URL APP");
    }

}
