package com.marcosmontenegro.controller;

import com.marcosmontenegro.entity.Proveedor;
import com.marcosmontenegro.service.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorService.getAllProveedores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProveedorById(@PathVariable Integer id) {
        Proveedor proveedor = proveedorService.getProveedorById(id);
        if (proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
        }
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    public ResponseEntity<Object> createProveedor(@RequestBody Proveedor proveedor) {
        try {
            Proveedor createdProveedor = proveedorService.saveProveedor(proveedor);
            return new ResponseEntity<>(createdProveedor, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        try {
            Proveedor updatedProveedor = proveedorService.updateProveedor(id, proveedor);
            return ResponseEntity.ok(updatedProveedor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProveedor(@PathVariable Integer id) {
        try {
            proveedorService.deleteProveedor(id);
            return ResponseEntity.ok("Proveedor eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}