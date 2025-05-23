package com.neon.sve.service.rol;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.rol.DatosActualizarRol;
import com.neon.sve.dto.rol.DatosListadoRol;
import com.neon.sve.dto.rol.DatosRegistroRol;
import com.neon.sve.dto.rol.DatosRespuestaRol;
import com.neon.sve.model.usuario.Rol;
import com.neon.sve.repository.RolRepository;

@Service
public class RolServiceImpl  implements RolService{

    @Autowired
    private RolRepository rolRepository;

    @Override
    public DatosRespuestaRol getRolById(Long id) {
        Optional<Rol> rolOptional = rolRepository.findById(id);
        if (rolOptional.isPresent()) {
            Rol rol = rolOptional.get();
            return new DatosRespuestaRol(rol);
        } else {
            throw new RuntimeException("Rol no encontrado");
        }
    }

    @Override
    public Page<DatosListadoRol> getAllRoles(Pageable pageable) {
        Page<Rol> rolPage= rolRepository.findAll(pageable);
        return rolPage.map(DatosListadoRol::new);
    }

    @Override
    public DatosRespuestaRol createRol(DatosRegistroRol datosRegistroRol) {
        Rol rol = rolRepository.save(new Rol(datosRegistroRol));
        return new DatosRespuestaRol(rol);
    }

    @Override
    public DatosRespuestaRol updateRol(DatosActualizarRol datosActualizarRol) {
        Rol rol=rolRepository.getReferenceById(datosActualizarRol.id());
        rol.actualizar(datosActualizarRol);
        rol = rolRepository.save(rol);
        return new DatosRespuestaRol(rol);
    }

    @Override
    public void deleteRol(Long id) {
        Rol rol = rolRepository.findById(id).orElse(null);
        if(rol != null){
            rolRepository.delete(rol);
        }else{
            throw new IllegalArgumentException("No se encontro el Rol con el ID proporcionado");
        }
    }

    @Override
    public Optional<Rol> findByNombreRol(String nombre) {
        return rolRepository.findByNombreRol(nombre);
    }

}