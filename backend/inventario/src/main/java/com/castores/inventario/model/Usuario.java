package com.castores.inventario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50, unique = true)
    private String correo;

    @Column(nullable = false, length = 255)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;

    @Column(nullable = false)
    private Integer estatus; // 1 = activo, 0 = inactivo
}
