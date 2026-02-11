package com.marcosmontenegro.controller;

import com.marcosmontenegro.entity.Repuesto;
import com.marcosmontenegro.service.RepuestoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {this.repuestoService = repuestoService;}

    @GetMapping
    public List<Repuesto> getAllRepuestos(){return repuestoService.getAllRepuestos();}
}
