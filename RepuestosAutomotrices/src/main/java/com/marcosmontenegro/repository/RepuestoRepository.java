package com.marcosmontenegro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcosmontenegro.entity.Repuesto;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {

    boolean existsByNombreRepuestoAndCategoriaRepuestoAndPrecioCompraAndPrecioVentaAndIdProveedor(
            String nombreRepuesto,
            String categoriaRepuesto,
            Double precioCompra,
            Double precioVenta,
            Integer idProveedor
    );
}
