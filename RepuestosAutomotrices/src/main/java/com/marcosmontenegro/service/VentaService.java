package com.marcosmontenegro.service;

import com.marcosmontenegro.entity.Venta;
import java.util.List;

public interface VentaService {
    List<Venta> getAllVentas();
    Venta getVentaById(Integer id);
    Venta saveVenta(Venta venta) throws RuntimeException;
    Venta updateVenta(Integer id, Venta venta);
    void deleteVenta(Integer id);
}
