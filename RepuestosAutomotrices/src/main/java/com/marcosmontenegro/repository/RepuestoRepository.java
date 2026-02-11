package com.marcosmontenegro.repository;

import com.marcosmontenegro.entity.Proveedor;
import com.marcosmontenegro.entity.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {

    boolean existsByNombreRepuestoAndCategoriaRepuestoAndPrecioCompraAndPrecioVentaAndProveedor(

            String nombreRepuesto,
            String categoriaRepuesto,
            Double precioCompra,
            Double precioVenta,
            Proveedor proveedor
    );
}
