package com.marcosmontenegro.repository;

import com.marcosmontenegro.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    boolean existByNombreProveedorAndtelefonoProveedorAnddireccionProveedorAndemailProveedor(

            String nombreEmpleado ,
            Integer telefonoProveedor,
            String direccionProveedor,
            String emailProveedor
    );

}
