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
    public Repuesto saveRepuesto(Repuesto repuesto) throws RuntimeException {
        try {
            if (repuesto == null || repuesto.getNombreRepuesto().isBlank() || repuesto.getCategoriaRepuesto().isBlank() || repuesto.getPrecioCompra() == null || repuesto.getPrecioVenta() == null || repuesto.getProveedor() == null){
                    throw new IllegalArgumentException("Todos los campos son obligatorios");
            }

            if (repuestoRepository.existsByNombreRepuestoAndCategoriaRepuestoAndPrecioCompraAndPrecioVentaAndProveedor(
                    repuesto.getNombreRepuesto(),
                    repuesto.getCategoriaRepuesto(),
                    repuesto.getPrecioCompra(),
                    repuesto.getPrecioVenta(),
                    repuesto.getProveedor()
            )){
                throw new RuntimeException("El repuesto ya existe");
            }
            return repuestoRepository.save(repuesto);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Repuesto updateRepuesto(Integer id, Repuesto repuesto) {
        try {
            return repuestoRepository.save(repuesto);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
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
