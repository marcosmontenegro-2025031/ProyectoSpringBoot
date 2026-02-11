package com.marcosmontenegro.repository;

import com.marcosmontenegro.entity.Proveedor;
import com.marcosmontenegro.entity.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {

    boolean existsNombreRepuestoAndCategoriaRepuestoAndPrecioCompraAndPrecioVentaAndProveedor(

            String nombreRepuesto,
            String categoriaRepuesto,
            double precioCompra,
            double precioVenta,
            Proveedor proveedor
    );
}
