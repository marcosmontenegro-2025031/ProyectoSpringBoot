package com.marcosmontenegro.service;

import com.marcosmontenegro.entity.Empleado;
import com.marcosmontenegro.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImplements implements EmpleadoService {

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
            if (empleado == null) {
                throw new RuntimeException("El empleado no puede ser null");
            }

            if (empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().isBlank()) {
                throw new RuntimeException("El nombre es obligatorio");
            }

            if (empleado.getApellidoEmpleado() == null || empleado.getApellidoEmpleado().isBlank()) {
                throw new RuntimeException("El apellido es obligatorio");
            }

            if (empleado.getPuestoEmpleado() == null || empleado.getPuestoEmpleado().isBlank()) {
                throw new RuntimeException("El puesto es obligatorio");
            }

            if (empleado.getEmailEmpleado() == null || empleado.getEmailEmpleado().isBlank()) {
                throw new RuntimeException("El email es obligatorio");
            }

            if (!(empleado.getEmailEmpleado().contains("@gmail.com") ||
                  empleado.getEmailEmpleado().contains("@yahoo.com") ||
                  empleado.getEmailEmpleado().contains("@outlook.com") ||
                  empleado.getEmailEmpleado().contains("@icloud.com") ||
                  empleado.getEmailEmpleado().contains("@hotmail.com"))) {
                throw new RuntimeException("El correo debe tener un dominio válido: @gmail.com, @yahoo.com, @outlook.com, @icloud.com o @hotmail.com");
            }

            if (empleadoRepository.existsByNombreEmpleadoAndApellidoEmpleadoAndPuestoEmpleadoAndEmailEmpleado(
                    empleado.getNombreEmpleado(),
                    empleado.getApellidoEmpleado(),
                    empleado.getPuestoEmpleado(),
                    empleado.getEmailEmpleado()
            )) {
                throw new RuntimeException("El empleado ya existe");
            }

            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado) throws RuntimeException {
        try {
            if (empleado == null) {
                throw new RuntimeException("El empleado no puede ser null");
            }

            if (empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().isBlank()) {
                throw new RuntimeException("El nombre es obligatorio");
            }

            if (empleado.getApellidoEmpleado() == null || empleado.getApellidoEmpleado().isBlank()) {
                throw new RuntimeException("El apellido es obligatorio");
            }

            if (empleado.getPuestoEmpleado() == null || empleado.getPuestoEmpleado().isBlank()) {
                throw new RuntimeException("El puesto es obligatorio");
            }

            if (empleado.getEmailEmpleado() == null || empleado.getEmailEmpleado().isBlank()) {
                throw new RuntimeException("El email es obligatorio");
            }

            if (!(empleado.getEmailEmpleado().contains("@gmail.com") ||
                  empleado.getEmailEmpleado().contains("@yahoo.com") ||
                  empleado.getEmailEmpleado().contains("@outlook.com") ||
                  empleado.getEmailEmpleado().contains("@icloud.com") ||
                  empleado.getEmailEmpleado().contains("@hotmail.com"))) {
                throw new RuntimeException("El correo debe tener un dominio válido: @gmail.com, @yahoo.com, @outlook.com, @icloud.com o @hotmail.com");
            }

            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteEmpleado(Integer id) throws RuntimeException {
        try {
            if (id == null || id == 0) {
                throw new RuntimeException("El id está vacío o es igual a 0");
            }

            if (!empleadoRepository.existsById(id)) {
                throw new RuntimeException("El id no existe");
            }

            empleadoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}