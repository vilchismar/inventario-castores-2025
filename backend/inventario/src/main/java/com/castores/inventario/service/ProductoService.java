package com.castores.inventario.service;

import com.castores.inventario.model.Producto;
import com.castores.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarPorEstatus(boolean activo) {
        return productoRepository.findByEstatus(activo);
    }

    public Producto agregarProducto(Producto producto) {
        producto.setCantidad(0);
        producto.setEstatus(true);
        return productoRepository.save(producto);
    }

    public Optional<Producto> obtenerPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }
}
