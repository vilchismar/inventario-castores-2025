package com.castores.inventario.service;

import com.castores.inventario.model.*;
import com.castores.inventario.repository.MovimientoRepository;
import com.castores.inventario.repository.ProductoRepository;
import com.castores.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Movimiento registrarMovimiento(Integer productoId, TipoMovimiento tipo, Integer cantidad, Integer usuarioId) {
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (tipo == TipoMovimiento.SALIDA && producto.getCantidad() < cantidad) {
            throw new RuntimeException("No hay suficiente inventario para retirar");
        }

        int nuevaCantidad = tipo == TipoMovimiento.ENTRADA
                ? producto.getCantidad() + cantidad
                : producto.getCantidad() - cantidad;

        producto.setCantidad(nuevaCantidad);
        productoRepository.save(producto);

        Movimiento movimiento = new Movimiento();
        movimiento.setProducto(producto);
        movimiento.setCantidad(cantidad);
        movimiento.setTipo(tipo);
        movimiento.setUsuario(usuario);
        movimiento.setFecha(LocalDateTime.now());

        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> listarPorTipo(TipoMovimiento tipo) {
        return movimientoRepository.findByTipo(tipo);
    }

    public List<Movimiento> listarTodos() {
        return movimientoRepository.findAll();
    }
}
