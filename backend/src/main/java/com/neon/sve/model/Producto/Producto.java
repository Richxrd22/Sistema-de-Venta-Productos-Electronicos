package com.neon.sve.model.Producto;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.producto.DatosActualizarProducto;
import com.neon.sve.dto.producto.DatosRegistroProducto;
import com.neon.sve.model.Usuario.Empleado;

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
@EqualsAndHashCode(of = "id_producto")
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id_producto;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(nullable = false, length = 250)
    private String descripcion;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int min_stock;

    @Column(nullable = false)
    private int max_stock;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int garantia_meses;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @Column(name = "fecha_creacion", updatable = false)
    @CreationTimestamp
    private Timestamp fecha_creacion;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado id_empleado;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria id_categoria;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca id_marca;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor id_proveedor;

    public Producto(@Valid DatosRegistroProducto datosRegistroProducto, Empleado empleado, Categoria categoria,
            Proveedor proveedor, Marca marca) {

        this.nombre = datosRegistroProducto.nombre();
        this.descripcion = datosRegistroProducto.descripcion();
        this.sku = datosRegistroProducto.SKU();
        this.precio = datosRegistroProducto.precio();
        this.min_stock = datosRegistroProducto.min_stock();
        this.max_stock = datosRegistroProducto.max_stock();
        this.stock = datosRegistroProducto.stock();
        this.garantia_meses = datosRegistroProducto.garantia_meses();
        this.activo = datosRegistroProducto.activo();
        this.fecha_creacion = datosRegistroProducto.fecha_creacion();
        this.id_empleado = empleado;
        this.id_categoria = categoria;
        this.id_proveedor = proveedor;
        this.id_marca = marca;

    }

    public void actualizar(@Valid DatosActualizarProducto datosActualizarProducto, Empleado empleado,
            Categoria categoria,
            Proveedor proveedor, Marca marca) {

        this.nombre = datosActualizarProducto.nombre();
        this.descripcion = datosActualizarProducto.descripcion();
        this.sku = datosActualizarProducto.SKU();
        this.precio = datosActualizarProducto.precio();
        this.min_stock = datosActualizarProducto.min_stock();
        this.max_stock = datosActualizarProducto.max_stock();
        this.stock = datosActualizarProducto.stock();
        this.garantia_meses = datosActualizarProducto.garantia_meses();
        this.activo = datosActualizarProducto.activo();
        this.fecha_creacion = datosActualizarProducto.fecha_creacion();
        this.id_empleado = empleado;
        this.id_categoria = categoria;
        this.id_proveedor = proveedor;
        this.id_marca = marca;

    }

}
