package co.uniquindio.marketplacefx.marketplaceapp.service;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.*;
import co.uniquindio.marketplacefx.marketplaceapp.model.*;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;

public interface IModelFactoryService {
    List<VendedorDto>obtenerListaVendedorDto();
    List<Usuario> obtenerListaUsuario();
    List<UsuarioDto> obtenerListaUsuarioDto();
    String obtenerUsuario(UsuarioDto usuario);
    boolean agregarVendedorDto(VendedorDto vendedorDto);
    boolean actualizarVendedor(VendedorDto vendedorOld,VendedorDto vendedorActualizado);
    boolean eliminarVendedor(VendedorDto vendedorDto);
    boolean validaUsuario(UsuarioDto usuario);
    List<ProductoDto> obtenerListaProductosVendedor(String nickNameTab);

    boolean agregarProducto(ProductoDto productoDto, String nickNameTab);

    boolean publicarProducto(String idProducto, String nickNameTab);


    List<Publicacion> obtenerPublicaciones(String nickNameTab);

    boolean agregarComentario(Comentario comentario, String idProducto, String nickNameTab);

    boolean agregarLike(Publicacion publicacionSeleccionada, String nickNameTab);

    boolean actualizarProducto(ProductoDto productoActualizado, String nickNameTab);

    boolean actualizarProductoPublicado(ProductoDto productoActualizado, String nickName);

    boolean eliminarProducto(String idProducto, String nickNameTab);

    boolean eliminarProductoPublicado(String idProducto, String nickNameTab);

    boolean productoVendido(String idProducto, String nickNameTab);

    List<Usuario> obtenerListaUsuariosVendedores();

    void iniciarVista(String txtNombreUsuario);

    String obtenerUsuarioActual();

    List<SugerenciaDto> listaSugerencias(String nickName);

    boolean agregarContacto(String agregar, String usuarioActual);

    List<ProductoDto> obtener(String nickName);

    ProductoDto obtenerProductoAliado(String nickName,String idProducto);

    String contactosPorVendedor();

    Administrador obtenerAdmin();

    List<SugerenciaDto> obtenerUsuarioContacto(String nickName);


    String obtenerMensajesVendedor(String nickNameTab, String nickNameSeleccion);

    boolean enviarMensaje(Mensaje mensaje);

    String cantidadMensajesEntreVendedores();

    String obtenerProductosTop();

    String cantidadProductosPublicados(String nickName);

    String obtenerPublicacionesFecha(LocalDate fechaPublicacion);
}
