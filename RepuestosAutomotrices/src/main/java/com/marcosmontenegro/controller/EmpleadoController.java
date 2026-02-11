package com.marcosmontenegro.controller;


import com.marcosmontenegro.entity.Empleado;
import com.marcosmontenegro.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/empleados")

public class EmpleadoController {

   private final EmpleadoService empleadoService;


    public EmpleadoController(EmpleadoService empleadoService) {this.empleadoService = empleadoService;}

    @GetMapping
    public List<Empleado> getAllEmpleados(){return empleadoService.getAllEmpleados();}

    @PostMapping
    public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Empleado empleado){
        try {
         Empleado createdEmpleado = empleadoService.saveEmpleado(empleado);
         return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleado(Integer id, @RequestBody Empleado empleado) {
    
        try {
            Empleado updatEmpleado = empleadoService.updateEmpleado(id, empleado);
            return new ResponseEntity<>(updatEmpleado, HttpStatus.OK);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Integer id){
        try {
            empleadoService.deleteEmpleado(id);
            return ResponseEntity.ok("Empleado Eliminado Correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
