package com.marcosmontenegro.service;

import com.marcosmontenegro.entity.Proveedor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProveedorService {
    List<Proveedor> getAllProveedores();
    Proveedor getProveedoresById(Integer id);
    Proveedor saveProveedores(Proveedor proveedor) throws RuntimeException;
    Proveedor updateProveedores(Integer id, Proveedor proveedor);
    void deleteProveedores(Integer id);


}
