package com.neon.sve.model.Producto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.marca.DatosActualizarMarca;
import com.neon.sve.dto.marca.DatosRegistroMarca;

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
@EqualsAndHashCode(of = "id_marca")
@Table(name = "marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_marca;

    @Column(unique = true, nullable = false)
    private String nombre_marca;

    @JsonIgnore
    @OneToMany(mappedBy = "id_marca")
    private List<Producto> productos;

    public Marca(@Valid DatosRegistroMarca datosRegistroMarca){
        this.nombre_marca = datosRegistroMarca.nombre_marca();
    }

    public void Actualizar(@Valid DatosActualizarMarca datosActualizarMarca){
        this.nombre_marca = datosActualizarMarca.nombre_marca();
    }

}
