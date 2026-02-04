package com.marcosmontenegro.Ejemplo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/holamundo")
    public String holaMundo(){return "Hola desde Spring Boot";}
}
