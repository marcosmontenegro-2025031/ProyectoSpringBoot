package com.marcosmontenegro.service;

import com.marcosmontenegro.entity.Venta;
import com.marcosmontenegro.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImplements implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImplements(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getVentaById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta saveVenta(Venta venta) throws RuntimeException {
        try {
            if (venta == null) {
                throw new RuntimeException("La venta no puede ser null");
            }
            if (venta.getFechaVenta() == null) {
                throw new RuntimeException("La fecha de la venta es obligatoria");
            }
            if (venta.getCantidad() == null) {
                throw new RuntimeException("La cantidad es obligatoria");
            }
            if (venta.getTotal() == null) {
                throw new RuntimeException("El total es obligatorio");
            }
            if (venta.getIdEmpleado() == null || venta.getIdEmpleado() == 0) {
                throw new RuntimeException("El id del empleado es obligatorio");
            }
            if (venta.getIdRepuesto() == null || venta.getIdRepuesto() == 0) {
                throw new RuntimeException("El id del repuesto es obligatorio");
            }

            if (ventaRepository.existsByFechaVentaAndCantidadAndTotalAndIdEmpleadoAndIdRepuesto(
                    venta.getFechaVenta(),
                    venta.getCantidad(),
                    venta.getTotal(),
                    venta.getIdEmpleado(),
                    venta.getIdRepuesto()
            )) {
                throw new RuntimeException("La venta ya existe");
            }

            return ventaRepository.save(venta);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Venta updateVenta(Integer id, Venta venta) {
        try {
            if (id == null || id == 0) {
                throw new RuntimeException("El id es inválido");
            }
            if (!ventaRepository.existsById(id)) {
                throw new RuntimeException("La venta no existe");
            }

            if (venta.getFechaVenta() == null) {
                throw new RuntimeException("La fecha de la venta es obligatoria");
            }
            if (venta.getCantidad() == null) {
                throw new RuntimeException("La cantidad es obligatoria");
            }
            if (venta.getTotal() == null) {
                throw new RuntimeException("El total es obligatorio");
            }
            if (venta.getIdEmpleado() == null || venta.getIdEmpleado() == 0) {
                throw new RuntimeException("El id del empleado es obligatorio");
            }
            if (venta.getIdRepuesto() == null || venta.getIdRepuesto() == 0) {
                throw new RuntimeException("El id del repuesto es obligatorio");
            }

            venta.setIdVenta(id);
            return ventaRepository.save(venta);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteVenta(Integer id) {
        try {
            if (id == null || id == 0) {
                throw new RuntimeException("El id es inválido");
            }
            if (!ventaRepository.existsById(id)) {
                throw new RuntimeException("La venta no existe");
            }

            ventaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}