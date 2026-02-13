package com.marcosmontenegro.controller;

import com.marcosmontenegro.entity.Repuesto;
import com.marcosmontenegro.service.RepuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {
        this.repuestoService = repuestoService;
    }

    @GetMapping
    public List<Repuesto> getAllRepuestos() {
        return repuestoService.getAllRepuestos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRepuestoById(@PathVariable Integer id) {
        Repuesto repuesto = repuestoService.getRepuestoById(id);

        if (repuesto == null) {
            return ResponseEntity.badRequest().body("El repuesto no existe");
        }

        return ResponseEntity.ok(repuesto);
    }

    @PostMapping
    public ResponseEntity<Object> createRepuesto(@Valid @RequestBody Repuesto repuesto) {
        try {
            Repuesto createRepuesto = repuestoService.saveRepuesto(repuesto);
            return new ResponseEntity<>(createRepuesto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRepuesto(@PathVariable Integer id,@Valid @RequestBody Repuesto repuesto) {
        try {
            Repuesto updateRepuesto = repuestoService.updateRepuesto(id, repuesto);
            return new ResponseEntity<>(updateRepuesto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRepuesto(@PathVariable Integer id) {
        try {
            repuestoService.deleteRepuesto(id);
            return ResponseEntity.ok("Se elimino el repuesto correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}