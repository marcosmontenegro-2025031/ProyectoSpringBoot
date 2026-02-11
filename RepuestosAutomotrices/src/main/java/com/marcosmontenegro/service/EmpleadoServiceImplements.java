package com.marcosmontenegro.service;


import com.marcosmontenegro.entity.Empleado;
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
        try {
            if (empleado == null || empleado.getNombreEmpleado().isBlank()|| empleado.getApellidoEmpleado().isBlank()|| empleado.getPuestoEmpleado().isBlank() || empleado.getEmailEmpleado().isBlank()){
                throw new IllegalArgumentException("Todos Los campos son obligatorios");
            }

            if (!empleado.getEmailEmpleado().contains("@gmail.com") || empleado.getEmailEmpleado().contains("@yahoo.com")
                    || empleado.getEmailEmpleado().contains("@outlook.com") || empleado.getEmailEmpleado().contains("@icloud.com") || empleado.getEmailEmpleado().contains("@hotmail.com")){
                throw new IllegalArgumentException("El correo electronico debe tener un dominio como @gmail.com, @yahoo.com, @outlook.com, @icloud.com y @hotmail.com");
            }

            if (empleadoRepository.existsByNombreEmpleadoAndApellidoEmpleadoAndPuestoEmpleadoAndEmailEmpleado(
                    empleado.getNombreEmpleado() ,
                    empleado.getApellidoEmpleado(),
                    empleado.getPuestoEmpleado(),
                    empleado.getEmailEmpleado()
            )){
                throw new RuntimeException("El empleado ya existe");
            }
            return empleadoRepository.save(empleado);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado){

            try {
                if (!empleado.getEmailEmpleado().contains("@gmail.com") || empleado.getEmailEmpleado().contains("@yahoo.com")
                    || empleado.getEmailEmpleado().contains("@outlook.com") || empleado.getEmailEmpleado().contains("@icloud.com") || empleado.getEmailEmpleado().contains("@hotmail.com")){
                throw new IllegalArgumentException("El correo electronico debe tener un dominio como @gmail.com, @yahoo.com, @outlook.com, @icloud.com y @hotmail.com");
            }
            return empleadoRepository.save(empleado);
            } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    
    }


    @Override
    public void deleteEmpleado(Integer id) {
        try {
            if (id == null || id == 0){
                throw new RuntimeException("El id esta vacío o es igual a 0");
            }

            if (!empleadoRepository.existsById(id)) {
                throw new RuntimeException("El id no existe");
            }
            empleadoRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
