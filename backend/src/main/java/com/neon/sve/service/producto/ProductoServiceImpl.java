package com.neon.sve.service.producto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.producto.DatosActualizarProducto;
import com.neon.sve.dto.producto.DatosListadoProducto;
import com.neon.sve.dto.producto.DatosRegistroProducto;
import com.neon.sve.dto.producto.DatosRespuestaProducto;
import com.neon.sve.model.Producto.Categoria;
import com.neon.sve.model.Producto.Marca;
import com.neon.sve.model.Producto.Producto;
import com.neon.sve.model.Producto.Proveedor;
import com.neon.sve.model.Usuario.Empleado;
import com.neon.sve.repository.CategoriaRepository;
import com.neon.sve.repository.EmpleadoRepository;
import com.neon.sve.repository.MarcaRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.ProveedorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public DatosRespuestaProducto getProductoById(Long id_producto) {

        Optional<Producto> productOptional = productoRepository.findById(id_producto);
        if (productOptional.isPresent()) {

            Producto producto = productOptional.get();
            return new DatosRespuestaProducto(producto);

        } else {

            throw new RuntimeException("Producto no encontrado");
        }

    }

    @Override
    public Page<DatosListadoProducto> getAllProducto(Pageable pageable) {

        Page<Producto> productoPage = productoRepository.findAll(pageable);
        return productoPage.map(DatosListadoProducto::new);

    }

    @Override
    public DatosRespuestaProducto createProducto(DatosRegistroProducto datosRegistroProducto) {

        Empleado empleado = empleadoRepository.getReferenceById(datosRegistroProducto.id_empleado());
        Categoria categoria = categoriaRepository.getReferenceById(datosRegistroProducto.id_categoria());
        Proveedor proveedor = proveedorRepository.getReferenceById(datosRegistroProducto.id_proveedor());
        Marca marca = marcaRepository.getReferenceById(datosRegistroProducto.id_marca());
        Producto producto = productoRepository
                .save(new Producto(datosRegistroProducto, empleado, categoria, proveedor, marca));
        return new DatosRespuestaProducto(producto);

    }

    @Override
    public DatosRespuestaProducto updateProducto(DatosActualizarProducto datosActualizarProducto) {
        Empleado empleado = empleadoRepository.getReferenceById(datosActualizarProducto.id_empleado());
        Categoria categoria = categoriaRepository.getReferenceById(datosActualizarProducto.id_categoria());
        Proveedor proveedor = proveedorRepository.getReferenceById(datosActualizarProducto.id_proveedor());
        Marca marca = marcaRepository.getReferenceById(datosActualizarProducto.id_marca());
        Producto producto = productoRepository.getReferenceById(datosActualizarProducto.id_producto());
        producto.actualizar(datosActualizarProducto, empleado, categoria, proveedor, marca);
        producto = productoRepository.save(producto);
        return new DatosRespuestaProducto(producto);

    }

    @Override
    public void activarProducto(Long id_producto) {
        Producto producto = productoRepository.findById(id_producto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Producto no encontrado con el ID ingresado, verifique ID : " + id_producto));

        if (!producto.getActivo()) {
            producto.setActivo(true);
            productoRepository.save(producto);
        }

    }

    @Override
    public void desactivarProducto(Long id_producto) {
        Producto producto = productoRepository.findById(id_producto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Producto no encontrado con el ID ingresado, verifique ID : " + id_producto));

        if (producto.getActivo()) {
            producto.setActivo(false);
            productoRepository.save(producto);
        }
    }

}
