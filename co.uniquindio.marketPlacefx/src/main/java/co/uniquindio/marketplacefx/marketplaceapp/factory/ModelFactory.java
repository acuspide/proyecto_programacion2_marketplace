package co.uniquindio.marketplacefx.marketplaceapp.factory;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.*;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.mappers.PrestamoMappingImpl;
import co.uniquindio.marketplacefx.marketplaceapp.model.*;
import co.uniquindio.marketplacefx.marketplaceapp.service.IModelFactoryService;
import co.uniquindio.marketplacefx.marketplaceapp.service.IPrestamoMapping;
import co.uniquindio.marketplacefx.marketplaceapp.service.IVendedorDtoCrud;
import co.uniquindio.marketplacefx.marketplaceapp.utils.DataUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelFactory implements IModelFactoryService, IVendedorDtoCrud{
    private static ModelFactory modelFactory;
    private MarketPlace marketPlace;
    IPrestamoMapping mapper;
    public String usuarioActual;



    public static ModelFactory getInstance(){
        if(modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    public String getUsuarioActual() {
        return usuarioActual;
    }

    public ModelFactory(){
        mapper = new PrestamoMappingImpl();
        marketPlace = DataUtil.inicializarDatos();

    }

    @Override
    public List<VendedorDto> obtenerListaVendedorDto() {
        return mapper.getVendedores(marketPlace.getListaVendedores());
    }

    @Override
    public List<Usuario> obtenerListaUsuario() {
        return marketPlace.getListaUsuarios();
    }

    @Override
    public List<UsuarioDto> obtenerListaUsuarioDto() {
        return mapper.usuarioToUsuarioDto(marketPlace.getListaUsuarios());
    }


    @Override
    public boolean agregarVendedorDto(VendedorDto vendedorDto) {
        return marketPlace.agregarVendedor(mapper.vendedorDtoToVendedor(vendedorDto));

    }

    @Override
    public boolean actualizarVendedor(VendedorDto vendedorOld, VendedorDto vendedorActualizado) {
        return marketPlace.actualizarVendedor(mapper.vendedorDtoToVendedor(vendedorOld),mapper.vendedorDtoToVendedor(vendedorActualizado));
    }

    @Override
    public boolean eliminarVendedor(VendedorDto vendedorDto) {
        return marketPlace.eliminarVendedor(mapper.vendedorDtoToVendedor(vendedorDto));
    }




    @Override
    public boolean validaUsuario(UsuarioDto usuario) {
        return marketPlace.validarUsuario(usuario);
    }

    @Override
    public List<ProductoDto> obtenerListaProductosVendedor(String nickNameTab) {
        List<ProductoDto>productosDto=new ArrayList<>();
        productosDto.addAll(mapper.listaProductosTolistaProductosDto(marketPlace.obtenerProductosVendedor(nickNameTab)));
        return productosDto;
    }

    @Override
    public boolean agregarProducto(ProductoDto productoDto, String nickNameTab) {
        Producto producto = mapper.ProductoDtoToProducto(productoDto);
        return marketPlace.agregarProductoVendedor(producto,nickNameTab);
    }

    @Override
    public boolean publicarProducto(String idProducto, String nickNameTab) {
        return marketPlace.publicarProducto(idProducto,nickNameTab);
    }

    @Override
    public List<Publicacion> obtenerPublicaciones(String nickNameTab) {
        return marketPlace.obtenerPublicaciones(nickNameTab);
    }

    @Override
    public boolean agregarComentario(Comentario comentario, String idProducto, String nickNameTab) {
        return marketPlace.agregarComentario(comentario,idProducto,nickNameTab);
    }

    @Override
    public boolean agregarLike(Publicacion publicacionSeleccionada, String nickNameTab) {
        return marketPlace.agregarLike(publicacionSeleccionada,nickNameTab);
    }

    @Override
    public boolean actualizarProducto(ProductoDto productoActualizado, String nickNameTab) {
        Producto producto = mapper.ProductoDtoToProducto(productoActualizado);
        return marketPlace.actualizarProducto(producto,nickNameTab);
    }

    @Override
    public boolean actualizarProductoPublicado(ProductoDto productoActualizado, String nickName) {
        Producto producto = mapper.ProductoDtoToProducto(productoActualizado);
        return marketPlace.actualizarProductoPublicado(producto,nickName);
    }

    @Override
    public boolean eliminarProducto(String idProducto, String nickNameTab) {
        return marketPlace.eliminarProducto(idProducto,nickNameTab);
    }

    @Override
    public boolean eliminarProductoPublicado(String idProducto, String nickNameTab) {
        return marketPlace.eliminarProductoPublicado(idProducto,nickNameTab);
    }

    @Override
    public boolean productoVendido(String idProducto, String nickNameTab) {
        return marketPlace.productoVendido(idProducto,nickNameTab);
    }

    @Override
    public List<Usuario> obtenerListaUsuariosVendedores() {
        return marketPlace.obtenerListaUsuariosVendedores();
    }

    @Override
    public void iniciarVista(String nickname) {
        this.usuarioActual=nickname;
    }

    @Override
    public String obtenerUsuarioActual() {
        return usuarioActual;
    }

    @Override
    public List<SugerenciaDto> listaSugerencias(String nickName) {
        return mapper.VendedorToSugerenciaDto(marketPlace.obtenerListaUsuariosSugerencia(nickName));
    }

    @Override
    public boolean agregarContacto(String agregar, String usuarioActual) {
        return marketPlace.agregarContacto(agregar,usuarioActual);
    }

    @Override
    public List<ProductoDto> obtener(String nickName) {
        return marketPlace.obtener(nickName);
    }

    @Override
    public ProductoDto obtenerProductoAliado(String nickName,String idProducto) {
        return marketPlace.obtenerProductoAliado(nickName, idProducto);
    }

    @Override
    public String contactosPorVendedor() {
        return marketPlace.contactosPorVendedor();
    }

    @Override
    public Administrador obtenerAdmin() {
        return marketPlace.obtenerAdmin();
    }

    @Override
    public List<SugerenciaDto> obtenerUsuarioContacto(String nickName) {
        return mapper.VendedorToSugerenciaDto(marketPlace.obtenerUsuarioContacto(nickName));
    }

    @Override
    public String obtenerMensajesVendedor(String nickNameTab, String nickNameSeleccion) {
        return marketPlace.obtenerMensajesVendedor(nickNameTab,nickNameSeleccion);
    }

    @Override
    public boolean enviarMensaje(Mensaje mensaje) {
        return marketPlace.enviarMensaje(mensaje);
    }

    @Override
    public String cantidadMensajesEntreVendedores() {
        return marketPlace.cantidadMensajesEntreVendedores();
    }

    @Override
    public String obtenerProductosTop(){
        return marketPlace.obtenerProductosTop();
    }

    @Override
    public String cantidadProductosPublicados(String nickName) {
        return marketPlace.cantidadProductosPublicados(nickName);
    }

    @Override
    public String obtenerPublicacionesFecha(LocalDate fechaPublicacion) {
        return marketPlace.obtenerPublicacionesFecha(fechaPublicacion);
    }

    @Override
    public String obtenerUsuario(UsuarioDto usuario) {
        return marketPlace.obtenerUsuario(usuario);
    }
}