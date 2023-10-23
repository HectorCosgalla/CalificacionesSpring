package com.valcos98.schoolproject.generalComponents;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class GeneralController {
    
    @GetMapping
    public String getIndex(){
        return "general_purposes/index";
    }
}
