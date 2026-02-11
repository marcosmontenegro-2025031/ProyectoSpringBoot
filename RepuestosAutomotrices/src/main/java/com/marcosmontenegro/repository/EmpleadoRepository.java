package com.marcosmontenegro.repository;


import com.marcosmontenegro.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    boolean existsByNombreProveedorAndTelefonoProveedorAndDireccionProveedorAndEmailProveedor(

            String nombreEmpleado ,
            String apellidoEmpleado,
            String puestoEmpleado,
            String emailEmpleado
    );

}
