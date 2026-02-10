package com.marcosmontenegro.service;


import com.marcosmontenegro.entity.Proveedor;
import com.marcosmontenegro.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImplements implements ProveedorService{

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImplements(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores(){return proveedorRepository.findAll();}

    @Override
    public Proveedor getProveedoresById(Integer id){return proveedorRepository.findById().orElse(null);}

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedor){
        Proveedor existingProveedor = proveedorRepository.findById(id).orElseThrow(() -> new RuntimeException("El proveedor no existe"));

        existingProveedor.set
    }

}
