package com.neon.sve.model.Producto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.categoria.DatosActualizarCategoria;
import com.neon.sve.dto.categoria.DatosRegistroCategoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@EqualsAndHashCode(of = "id_categoria")
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    @Column(unique = true, nullable = false)
    private String nombre_categoria;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @JsonIgnore
    @OneToMany(mappedBy = "id_categoria")
    private List<Producto> productos;

    public Categoria(@Valid DatosRegistroCategoria datosRegistroCategoria) {
        this.nombre_categoria = datosRegistroCategoria.nombre_categoria();
        this.activo = datosRegistroCategoria.activo();
    }

    public void actualizar(@Valid DatosActualizarCategoria datosActualizarCategoria) {
        this.nombre_categoria = datosActualizarCategoria.nombre_categoria();
        this.activo = datosActualizarCategoria.activo();
    }

}
