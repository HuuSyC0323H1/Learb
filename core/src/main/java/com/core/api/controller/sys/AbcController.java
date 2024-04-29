package com.core.api.controller.sys;

import com.core.api.forms.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Huu Sy
 * @Date: 27/04/2024 4:18 CH
 */
@RestController
@RequestMapping("/api/sys")
@RequiredArgsConstructor
public class AbcController {

    @GetMapping()
    public ResponseObject getString(){
        return new ResponseObject("Xin Chao");
    }
}
