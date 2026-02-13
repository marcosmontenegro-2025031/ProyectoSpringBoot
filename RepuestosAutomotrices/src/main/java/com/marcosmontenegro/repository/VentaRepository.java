package com.marcosmontenegro.repository;

import com.marcosmontenegro.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    boolean existsByFechaVentaAndCantidadAndTotalAndIdEmpleadoAndIdRepuesto(
            String fechaVenta,
            Integer cantidad,
            Double total,
            Integer idEmpleado,
            Integer idRepuesto
    );
}