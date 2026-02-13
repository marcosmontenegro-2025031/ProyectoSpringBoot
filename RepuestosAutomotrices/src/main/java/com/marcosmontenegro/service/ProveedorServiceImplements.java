package com.marcosmontenegro.service;

import com.marcosmontenegro.entity.Proveedor;
import com.marcosmontenegro.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImplements implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImplements(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException {
        try {
            if (proveedor == null) {
                throw new RuntimeException("El proveedor no puede ser null");
            }

            if (proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().isBlank()) {
                throw new RuntimeException("El nombre del proveedor es obligatorio");
            }

            if (proveedor.getTelefonoProveedor() == null) {
                throw new RuntimeException("El teléfono del proveedor es obligatorio");
            }

            if (proveedor.getDireccionProveedor() == null || proveedor.getDireccionProveedor().isBlank()) {
                throw new RuntimeException("La dirección del proveedor es obligatoria");
            }

            if (proveedor.getEmailProveedor() == null || proveedor.getEmailProveedor().isBlank()) {
                throw new RuntimeException("El correo electrónico del proveedor es obligatorio");
            }

            if (!(proveedor.getEmailProveedor().contains("@gmail.com") ||
                  proveedor.getEmailProveedor().contains("@yahoo.com") ||
                  proveedor.getEmailProveedor().contains("@outlook.com") ||
                  proveedor.getEmailProveedor().contains("@icloud.com") ||
                  proveedor.getEmailProveedor().contains("@hotmail.com"))) {

                throw new RuntimeException("Dominio de correo no válido");
            }

            if (proveedorRepository.existsByNombreProveedorAndTelefonoProveedorAndDireccionProveedorAndEmailProveedor(
                    proveedor.getNombreProveedor(),
                    proveedor.getTelefonoProveedor(),
                    proveedor.getDireccionProveedor(),
                    proveedor.getEmailProveedor())) {

                throw new RuntimeException("El proveedor ya existe");
            }

            return proveedorRepository.save(proveedor);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

     @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedor) {
        try {
            if (id == null || id == 0) {
                throw new RuntimeException("El id está vacío o es igual a 0");
            }

            if (!proveedorRepository.existsById(id)) {
                throw new RuntimeException("El id no existe");
            }

            if (proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().isBlank()) {
                throw new RuntimeException("El nombre del proveedor es obligatorio");
            }

            if (proveedor.getTelefonoProveedor() == null) {
                throw new RuntimeException("El teléfono del proveedor es obligatorio");
            }

            if (proveedor.getDireccionProveedor() == null || proveedor.getDireccionProveedor().isBlank()) {
                throw new RuntimeException("La dirección del proveedor es obligatoria");
            }

            if (proveedor.getEmailProveedor() == null || proveedor.getEmailProveedor().isBlank()) {
                throw new RuntimeException("El correo electrónico del proveedor es obligatorio");
            }

            if (!(proveedor.getEmailProveedor().contains("@gmail.com") ||
                  proveedor.getEmailProveedor().contains("@yahoo.com") ||
                  proveedor.getEmailProveedor().contains("@outlook.com") ||
                  proveedor.getEmailProveedor().contains("@icloud.com") ||
                  proveedor.getEmailProveedor().contains("@hotmail.com"))) {

                throw new RuntimeException("Dominio de correo no válido");
            }

            proveedor.setIdProveedor(id);
            return proveedorRepository.save(proveedor);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteProveedor(Integer id) {

        try {

            if (id == null || id == 0) {
                throw new RuntimeException("El id está vacío o es igual a 0");
            }

            if (!proveedorRepository.existsById(id)) {
                throw new RuntimeException("El id de proveedor no existe");
            }

            proveedorRepository.deleteById(id);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}