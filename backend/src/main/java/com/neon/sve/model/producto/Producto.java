package com.neon.sve.model.producto;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.producto.DatosActualizarProducto;
import com.neon.sve.dto.producto.DatosRegistroProducto;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.model.ventas.IngresoStock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String sku;
    
    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = true, length = 50)
    private String modelo;

    @Column(nullable = true, length = 50)
    private String color;

    @Column(nullable = false, length = 250)
    private String descripcion;

    @Column(nullable = false)
    private double precio_venta;

    @Column(nullable = false)
    private double precio_compra;

    @Column(nullable = false)
    private int min_stock;

    @Column(nullable = false)
    private int max_stock;

    @Column(nullable = false)
    private int garantia_meses;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    @Column(name = "fecha_creacion", updatable = false)
    @CreationTimestamp
    private Timestamp fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria", nullable = false)
    private SubCategoria id_subcategoria;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca id_marca;

    @JsonIgnore
    @OneToMany(mappedBy = "id_producto")
    private List<IngresoStock> ingresoStocks;

    public Producto(@Valid DatosRegistroProducto datosRegistroProducto, Usuario usuario, SubCategoria subCategoria,
            Marca marca,String sku) {

        this.nombre = datosRegistroProducto.nombre();
        this.modelo = datosRegistroProducto.modelo();
        this.color = datosRegistroProducto.color();
        this.descripcion = datosRegistroProducto.descripcion();
        this.precio_venta = datosRegistroProducto.precio_venta();
        this.precio_compra = datosRegistroProducto.precio_compra();
        this.min_stock = datosRegistroProducto.min_stock();
        this.max_stock = datosRegistroProducto.max_stock();
        this.garantia_meses = datosRegistroProducto.garantia_meses();
        this.id_usuario = usuario;
        this.id_subcategoria = subCategoria;
        this.id_marca = marca;
        this.sku = sku;

    }

    public void actualizar(@Valid DatosActualizarProducto datosActualizarProducto, Usuario usuario,
            SubCategoria subCategoria,
            Marca marca) {

        this.nombre = datosActualizarProducto.nombre();
        this.modelo = datosActualizarProducto.modelo();
        this.color = datosActualizarProducto.color();
        this.descripcion = datosActualizarProducto.descripcion();
        this.precio_venta = datosActualizarProducto.precio_venta();
        this.precio_compra = datosActualizarProducto.precio_compra();
        this.min_stock = datosActualizarProducto.min_stock();
        this.max_stock = datosActualizarProducto.max_stock();
        this.garantia_meses = datosActualizarProducto.garantia_meses();
        this.id_usuario = usuario;
        this.id_subcategoria = subCategoria;
        this.id_marca = marca;

    }

}
