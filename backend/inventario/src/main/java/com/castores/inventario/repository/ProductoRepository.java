package com.castores.inventario.repository;

import com.castores.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByEstatus(boolean estatus);
}
