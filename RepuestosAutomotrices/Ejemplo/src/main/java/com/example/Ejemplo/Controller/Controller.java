package com.example.Ejemplo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping( "Hola Mundo")
    public String hola() { return "Hola mundo este es mi primer programa"; }
}
