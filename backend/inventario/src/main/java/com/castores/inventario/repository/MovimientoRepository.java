package com.castores.inventario.repository;

import com.castores.inventario.model.Movimiento;
import com.castores.inventario.model.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByTipo(TipoMovimiento tipo);
}
