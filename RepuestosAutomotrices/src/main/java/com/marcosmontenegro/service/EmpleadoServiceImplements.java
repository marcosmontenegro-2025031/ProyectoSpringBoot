package com.marcosmontenegro.service;


import com.marcosmontenegro.model.Empleado;
import com.marcosmontenegro.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImplements implements EmpleadoService{

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImplements(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) throws RuntimeException {
        return empleadoRepository.save(empleado);
    }


    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado){
        Empleado existingEmpleado = empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("El empleado no existe"));

            existingEmpleado.setNombreEmpleado(empleado.getNombreEmpleado());
            existingEmpleado.setApellidoEmpleado(empleado.getApellidoEmpleado());
            existingEmpleado.setPuestoEmpleado(empleado.getPuestoEmpleado());
            existingEmpleado.setEmailEmpleado(empleado.getEmailEmpleado());

            return empleadoRepository.save(existingEmpleado);
    
    }


    @Override
    public void deleteEmpleado(Integer id) {
        if (!empleadoRepository.existsById(id)) {
         throw new RuntimeException("Empleado no existe");
        }
        empleadoRepository.deleteById(id);
    }
}
