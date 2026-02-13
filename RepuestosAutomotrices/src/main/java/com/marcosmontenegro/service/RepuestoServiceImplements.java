package com.marcosmontenegro.service;

import com.marcosmontenegro.entity.Repuesto;
import com.marcosmontenegro.repository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepuestoServiceImplements implements RepuestoService{

    private final RepuestoRepository repuestoRepository;

    public RepuestoServiceImplements(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }


    @Override
    public List<Repuesto> getAllRepuestos() {return repuestoRepository.findAll();}

    @Override
    public Repuesto getRepuestoById(Integer id) {return repuestoRepository.findById(id).orElse(null);}

    @Override
    public Repuesto saveRepuesto(Repuesto repuesto) {

        if (repuesto == null) {
        throw new RuntimeException("El repuesto no puede ser null");
        }

        if (repuesto.getNombreRepuesto() == null || repuesto.getNombreRepuesto().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio");
         }

        if (repuesto.getCategoriaRepuesto() == null || repuesto.getCategoriaRepuesto().isBlank()) {
             throw new RuntimeException("La categoría es obligatoria");
        }

        if (repuesto.getPrecioCompra() == null) {
            throw new RuntimeException("El precio compra es obligatorio");
        }

        if (repuesto.getPrecioVenta() == null) {
         throw new RuntimeException("El precio venta es obligatorio");
        }

        if (repuesto.getIdProveedor() == null) {
            throw new RuntimeException("El proveedor es obligatorio");
        }

        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto updateRepuesto(Integer id, Repuesto repuesto) {

    if (!repuestoRepository.existsById(id)) {
        throw new RuntimeException("El repuesto no existe");
    }

    repuesto.setIdRepuesto(id);

    return repuestoRepository.save(repuesto);
    }

    @Override
    public void deleteRepuesto(Integer id) {
        try {
            if (id == null || id == 0){
                throw new RuntimeException("El id esta vacío o es igual a 0");
            }

            if (!repuestoRepository.existsById(id)){
                throw new RuntimeException("El id no existe");
            }
            repuestoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
