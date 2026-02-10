package com.marcosmontenegro.service;


import com.marcosmontenegro.entity.Proveedor;
import com.marcosmontenegro.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImplements implements ProveedorService{

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImplements(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores(){return proveedorRepository.findAll();}

    @Override
    public Proveedor getProveedorById(Integer id){return proveedorRepository.findById(id).orElse(null);}

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException {
        try {
            if (proveedor == null || proveedor.getNombreProveedor().isBlank()|| proveedor.getTelefonoProveedor() == null || proveedor.getDireccionProveedor().isBlank() || proveedor.getEmailProveedor().isBlank()){
                throw new IllegalArgumentException("Todos Los campos son obligatorios");
            }

            if (!proveedor.getEmailProveedor().contains("@gmail.com") || proveedor.getEmailProveedor().contains("@yahoo.com")
                    || proveedor.getEmailProveedor().contains("@outlook.com") || proveedor.getEmailProveedor().contains("@icloud.com") || proveedor.getEmailProveedor().contains("@hotmail.com")){
                throw new IllegalArgumentException("El correo electronico debe tener un dominio como @gmail.com, @yahoo.com, @outlook.com, @icloud.com y @hotmail.com");
            }

            if (proveedorRepository.existByNombreProveedorAndtelefonoProveedorAnddireccionProveedorAndemailProveedor(
                    proveedor.getNombreProveedor() ,
                    proveedor.getTelefonoProveedor(),
                    proveedor.getDireccionProveedor(),
                    proveedor.getEmailProveedor()
            )){
                throw new RuntimeException("El proveedor ya existe");
            }
            return proveedorRepository.save(proveedor);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedor){
        try {
            if (!proveedor.getEmailProveedor().contains("@gmail.com") || proveedor.getEmailProveedor().contains("@yahoo.com")
                    || proveedor.getEmailProveedor().contains("@outlook.com") || proveedor.getEmailProveedor().contains("@icloud.com") || proveedor.getEmailProveedor().contains("@hotmail.com")){
                throw new IllegalArgumentException("El correo electronico debe tener un dominio como @gmail.com, @yahoo.com, @outlook.com, @icloud.com y @hotmail.com");
            }
            return proveedorRepository.save(proveedor);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        }

    @Override
    public void deleteProveedor(Integer id) {
        try {
            if (id == null || id == 0){
                throw new RuntimeException("El id esta vacío o es igual a 0");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
