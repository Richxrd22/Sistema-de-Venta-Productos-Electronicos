package com.neon.sve.model.Ventas;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRegistroIngresoStock;
import com.neon.sve.model.Producto.Producto;
import com.neon.sve.model.Producto.Proveedor;
import com.neon.sve.model.Usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "ingreso_stocks")

public class IngresoStock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto id_producto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor id_proveedor;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario id_usuario;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "fecha_ingreso", updatable = false)
    @CreationTimestamp
    private Timestamp fecha_ingreso;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    public IngresoStock(@Valid DatosRegistroIngresoStock datosRegistroIngresoStock, Producto producto, Proveedor proveedor, Usuario usuario) {
        this.id_producto = producto;
        this.id_proveedor = proveedor;
        this.id_usuario = usuario;
        this.sku = datosRegistroIngresoStock.sku();
        this.cantidad = datosRegistroIngresoStock.cantidad();
    }

    public void actualizar(@Valid DatosActualizarIngresoStock datosActualizarIngresoStock, Producto producto, Proveedor proveedor, Usuario usuario) {
        this.id_producto = producto;
        this.id_proveedor = proveedor;
        this.id_usuario = usuario;
        this.sku = datosActualizarIngresoStock.sku();
        this.cantidad = datosActualizarIngresoStock.cantidad();
        this.activo = datosActualizarIngresoStock.activo();
    }

}
