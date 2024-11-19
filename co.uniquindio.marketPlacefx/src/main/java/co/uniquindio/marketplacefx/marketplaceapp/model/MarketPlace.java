package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.UsuarioDto;

import java.time.LocalDate;
import java.util.*;

public class MarketPlace {
    private Administrador administrador;
    private List<Vendedor>listaVendedores = new ArrayList<>();
    private List<Usuario>listaUsuarios = new ArrayList<>();


    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public boolean agregarVendedor(Vendedor vendedor) {
        Vendedor vendedorEncontrado = obtenerVendedorCedula(vendedor.getCedula());
        if((vendedorEncontrado==null)&&((verificarUsuario(vendedor.getUsuario())))&&listaUsuarios.size()<11){
            listaVendedores.add(vendedor);
            listaUsuarios.add(vendedor.getUsuario());
            return true;
        }else{
            return false;
        }
    }

    private boolean verificarUsuario(Usuario usuario) {
        for(Usuario usuario2 : listaUsuarios){
            if(usuario2.getNickUsuario().equals(usuario.getNickUsuario())){
                return false;
            }
        }
        return true;
    }

    private Vendedor obtenerVendedor(String nickName) {
        Vendedor vendedor = null;
        for(Vendedor vendedo1 : listaVendedores){
            if(vendedo1.getUsuario().getNickUsuario().equals(nickName)){
                vendedor = vendedo1;
                break;
            }
        }
        return vendedor;
    }
    private Vendedor obtenerVendedorCedula(String cedula) {
        Vendedor vendedor = null;
        for(Vendedor vendedo1 : listaVendedores){
            if(vendedo1.cedula.equals(cedula)){
                vendedor = vendedo1;
                break;
            }
        }
        return vendedor;
    }


    public boolean actualizarVendedor(Vendedor vendedorOld,Vendedor vendedorActualizado) {
        Vendedor vendedor = obtenerVendedorCedula(vendedorOld.getCedula());
        //int index = 0;

        if (vendedor != null) {
            if (!vendedor.getCedula().equals(vendedorActualizado.getCedula())) {
                    System.out.println("Error: La cédula '" + vendedorActualizado.getCedula() + "' ya está en uso.");
                    return false; // No se puede actualizar porque la cédula ya existe
            }
            if ((!vendedor.usuario.getNickUsuario().equals(vendedorActualizado.getUsuario().getNickUsuario())) ||
            (!vendedor.usuario.getContrasena().equals(vendedorActualizado.getUsuario().getContrasena()))){
                System.out.println("Error: usuario '" + vendedorActualizado.getUsuario().getNickUsuario() + " "
                        + vendedorActualizado.getUsuario().getContrasena() + "' no se puede modificar");
                return false; // No se puede actualizar porque la cédula ya existe
            }
            // Actualizar los atributos del vendedor
            vendedor.setNombre(vendedorActualizado.getNombre());
            vendedor.setApellido(vendedorActualizado.getApellido());
            vendedor.setCedula(vendedorActualizado.getCedula()); // Esto se puede hacer sin problemas si la cédula es la misma
            vendedor.setDireccion(vendedorActualizado.getDireccion());
            vendedor.setUsuario(vendedorActualizado.getUsuario());
            return true; // Indica que la actualización fue exitosa
        }
        return false; // Si no se encontró el vendedor
    }

    public int getIndexVendedor(String cedula) {
        int index=0;
        for(Vendedor v:listaVendedores) {
            if (v.equals(cedula)) {
                index = listaVendedores.indexOf(v);
                break;
            }
        }
        return index;
    }

    public int getIndexUsuario(Usuario usuario) {
        int index=-1;
        for(Usuario v:listaUsuarios) {
            if (v.getNickUsuario().equals(usuario.getNickUsuario())) {
                index = listaUsuarios.indexOf(v);
                break;
            }
        }
        return index;
    }




    public boolean eliminarVendedor(Vendedor vendedor) {
        Vendedor vendedorExisten = obtenerVendedorCedula(vendedor.getCedula());
        if (vendedorExisten != null){
            for (Vendedor vendedor1: listaVendedores){
                if(vendedor1.getCedula().equalsIgnoreCase(vendedor.getCedula())){
                    listaVendedores.remove(vendedor1);
                    listaUsuarios.remove(vendedor1.getUsuario());
                    for(Vendedor v:vendedorExisten.getVendedoresAliados()){
                        v.getVendedoresAliados().remove(vendedorExisten);
                        List<Mensaje>aux=new ArrayList<>();
                        for(Mensaje mensaje:v.getMuro().mensajes){

                            if((mensaje.destinatario.equals(vendedorExisten.usuario.getNickUsuario()))||
                                    (mensaje.remitente.equals(vendedorExisten.usuario.getNickUsuario()))){
                                aux.add(mensaje);
                            }
                        }
                        v.getMuro().mensajes.removeAll(aux);
                        aux.clear();
                    }
                    vendedorExisten.getListaProductos().clear();
                    vendedorExisten.getMuro().publicaciones.clear();
                    vendedorExisten.getMuro().mensajes.clear();
                    return true;

                }

            }
        }

        return false;
    }

    public boolean actualizarProducto(Producto productoActualizado , String nickName) {
        Vendedor vendedor = obtenerVendedor(nickName);
        Producto productoViejo = vendedor.obtenerProducto(productoActualizado.getId());
        boolean validar=false;
        for(Producto producto : vendedor.getListaProductos()){
            if(producto.getId().equalsIgnoreCase(productoActualizado.getId())){
                validar=true;
                break;
            }
        }
        if(validar){
            vendedor.getListaProductos().remove(productoViejo);
            vendedor.getListaProductos().add(productoActualizado);
            return true;
        }else{return false;}
    }

    public String obtenerUsuario(UsuarioDto usuario) {
        String cedula = "";
        for (Usuario usuario1: listaUsuarios){
            if(usuario1.getNickUsuario().equals(usuario.nickUsuario()) &&
                    (usuario1.getContrasena().equals(usuario.contrasena()))){
                cedula = usuario1.getCedulaAsociada();
                break;
            }
        }
        return cedula;
    }

    public boolean validarUsuario(UsuarioDto usuario) {
        for(Usuario usuario1:getListaUsuarios()){
            System.out.println(usuario1.getNickUsuario()+" "+usuario1.getContrasena());
            if(usuario1.getNickUsuario().equals(usuario.nickUsuario())&&
                    (usuario1.getContrasena().equals(usuario.contrasena()))){
                return true;
            }
        }
        return false;
    }

    public List<Producto> obtenerProductosVendedor(String nickNameTab) {
        List<Producto> productosVendedor = new ArrayList<>();
        for(Vendedor vendedor: listaVendedores){
            if(vendedor.getUsuario().getNickUsuario().equalsIgnoreCase(nickNameTab)){
                productosVendedor.addAll(vendedor.getListaProductos());
                break;
            }
        }
        return productosVendedor;
    }

    public boolean agregarProductoVendedor(Producto producto, String nickNameTab) {
        Vendedor vendedor = obtenerVendedor(nickNameTab);
        boolean validacion = false;
        if(vendedor != null){
            for(Vendedor vendedor1: listaVendedores){
                if(vendedor1.getUsuario().getNickUsuario().equalsIgnoreCase(nickNameTab)){
                    vendedor1.getListaProductos().add(producto);
                    validacion = true;
                    break;
                }
            }
        }
        return validacion;

    }

    public boolean publicarProducto(String idProducto, String nickNameTab) {
        Vendedor vendedor = obtenerVendedor(nickNameTab);
        Producto producto = vendedor.obtenerProducto(idProducto);
        boolean validacion = false;
        if(vendedor != null && producto != null){
            Publicacion publicacion= crearPublicacion(nickNameTab,idProducto,producto.getDescripcion());
            for(Vendedor vendedor1: listaVendedores){
                if(vendedor1.getUsuario().getNickUsuario().equalsIgnoreCase(nickNameTab)){
                    vendedor1.getMuro().publicaciones.add(publicacion);
                    publicacion.reportarPublicacion();
                    validacion = true;
                    break;
                }
            }
        }
        return validacion;
    }

    public Publicacion crearPublicacion(String nicknameTab,String idProducto, String descripcion) {
        Vendedor vendedor=obtenerVendedor(nicknameTab);
        Producto producto = vendedor.obtenerProducto(idProducto);
        Publicacion publicacion = new Publicacion(producto.getNombre(),idProducto,descripcion,new ArrayList<>(),LocalDate.now(),new ArrayList<>(), nicknameTab);
        publicacion.observadores.addAll(vendedor.getVendedoresAliados());
        return publicacion;
    }

    public List<Publicacion> obtenerPublicaciones(String nickNameTab) {
        Vendedor vendedor = obtenerVendedor(nickNameTab);
        return vendedor.getMuro().getPublicaciones();
    }

    public boolean agregarComentario(Comentario comentario, String idProducto, String nickNameTab) {
        boolean validacion = false;
        Vendedor vendedor = obtenerVendedor(nickNameTab);
        if(vendedor != null){
            for(Publicacion publicacion: vendedor.getMuro().getPublicaciones()){
                if(publicacion.idProducto.equals(idProducto)){
                    publicacion.getComentarios().add(comentario);
                    validacion = true;
                    break;
                }
            }
        }
        if(validacion == false){
            for(Vendedor vendedor1: vendedor.getVendedoresAliados()){
                for(Publicacion publicacion: vendedor1.getMuro().getPublicaciones()){
                    if(publicacion.idProducto.equals(idProducto)){
                        publicacion.getComentarios().add(comentario);
                        validacion = true;
                        break;
                    }
                }
            }
        }
        return validacion;
    }

    public boolean agregarLike(Publicacion publicacionSeleccionada, String nickNameTab) {
        boolean validacion = false;
        Vendedor vendedor = obtenerVendedor(nickNameTab);
        if(vendedor != null && publicacionSeleccionada != null){
            for(Publicacion publicacion: vendedor.getMuro().getPublicaciones()){
                if(publicacion.idProducto.equals(publicacionSeleccionada.idProducto)){
                    boolean label=false;
                    for(String nombre: publicacion.likes){
                        if(nombre.equals(nickNameTab)){
                            label=true;
                            break;
                        }
                    }
                    if(!label){publicacion.likes.add(nickNameTab);}

                    validacion = true;
                }
            }
        }
        return validacion;

    }

    public boolean actualizarProductoPublicado(Producto producto, String nickName) {
        boolean validacion = false;
        Vendedor vendedor = obtenerVendedor(nickName);
        if(actualizarProducto(producto,nickName)){
            for(Publicacion publicacion: vendedor.getMuro().getPublicaciones()){
                if(publicacion.idProducto.equals(producto.getId())){
                    publicacion.descripcion=producto.getDescripcion();
                    publicacion.nombre=producto.getNombre();
                    validacion = true;
                    break;
                }
            }

        }
        return validacion;
    }

    public boolean eliminarProducto(String idProducto, String nickNameTab) {
        Vendedor vendedor = obtenerVendedor(nickNameTab);
        Producto producto = vendedor.obtenerProducto(idProducto);
        if(vendedor != null) {
           vendedor.getListaProductos().remove(producto);
           return true;
        }
        return false;
    }

    public boolean eliminarProductoPublicado(String idProducto, String nickNameTab) {
        boolean validacion= false;
        Vendedor vendedor = obtenerVendedor(nickNameTab);
        Publicacion publicacion=null;
        if(vendedor != null){
            for (Publicacion publicacion2 : vendedor.getMuro().getPublicaciones()) {
                if (publicacion2.idProducto.equals(idProducto)) {
                    publicacion = publicacion2;
                    validacion=true;
                    break;
                }
            }
            vendedor.getMuro().getPublicaciones().remove(publicacion);
        }
        return validacion;
    }

    public boolean productoVendido(String idProducto, String nickNameTab) {
        boolean validacion = false;
        Vendedor vendedor = obtenerVendedor(nickNameTab);
        Publicacion publicacion = null;
        if (vendedor != null) {
            for (Publicacion publicacion2 : vendedor.getMuro().getPublicaciones()) {
                if (publicacion2.idProducto.equals(idProducto)) {
                    publicacion = publicacion2;
                    validacion = true;
                    break;
                }
            }
            vendedor.getMuro().getPublicaciones().remove(publicacion);
        }
        return validacion;
    }

    public List<Usuario> obtenerListaUsuariosVendedores() {
        List<Usuario> usuariosVendedor = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores){
            usuariosVendedor.add(vendedor.usuario);
        }
        return usuariosVendedor;
    }

    public List<Vendedor> obtenerListaUsuariosSugerencia(String nickName) {
        Vendedor vendedor = obtenerVendedor(nickName);
        List<Vendedor> filtroVendedores=new ArrayList<>();
        if(vendedor != null) {
            filtroVendedores.addAll(listaVendedores);
            filtroVendedores.remove(vendedor);
            for(Vendedor veendedor:vendedor.getVendedoresAliados()){
                filtroVendedores.remove(veendedor);
            }
        }
        return filtroVendedores;
    }


    public boolean agregarContacto(String agregar, String usuarioActual) {
        Vendedor vendedor1 = obtenerVendedor(usuarioActual);
        Vendedor vendedor2 = obtenerVendedor(agregar);
        if(vendedor1 != null && vendedor2 != null){
            vendedor1.getVendedoresAliados().add(vendedor2);
            vendedor2.getVendedoresAliados().add(vendedor1);
            return true;
        }
        return false;
    }

    public List<ProductoDto> obtener(String nickName) {
        Vendedor vendedor = obtenerVendedor(nickName);
        List<ProductoDto>productos= new ArrayList<>();
        if(vendedor != null){
            for (Producto producto : vendedor.getListaProductos()){
                productos.add(productoToProductoDto(producto));
            }
        }
        return productos;
    }

    private ProductoDto productoToProductoDto(Producto producto) {
        return new ProductoDto(producto.getNombre(),producto.getId(),producto.getCategoria(),producto.getPrecio(),producto.getRutaImagen(),producto.getDescripcion());
    }

    public ProductoDto obtenerProductoAliado(String nickName, String idProducto) {
        Vendedor vendedor = obtenerVendedor(nickName);
        Producto producto = vendedor.obtenerProducto(idProducto);
        ProductoDto productoDto = productoToProductoDto(producto);
        return productoDto;
    }

    public String contactosPorVendedor() {
        String mensaje="Reporte de contactos por vendedor:\n";
        for(Vendedor vendedor:listaVendedores){
            mensaje+="Usuario "+vendedor.getUsuario().getNickUsuario()+
                    " tiene "+vendedor.getVendedoresAliados().size()+" vendedores asociados\n";
        }
        mensaje+="\n";
        return mensaje;
    }

    public Administrador obtenerAdmin() {
        return administrador;
    }

    public List<Vendedor> obtenerUsuarioContacto(String nickName) {
        Vendedor vendedor = obtenerVendedor(nickName);
        return vendedor.getVendedoresAliados();
    }


    public String obtenerMensajesVendedor(String nickNameTab, String nickNameSeleccion) {
        String mensaje="";
        Vendedor vendedor = obtenerVendedor(nickNameTab);
        if(vendedor != null) {
            for(Mensaje mensaje1:vendedor.getMuro().mensajes){
                if(mensaje1.destinatario.equalsIgnoreCase(nickNameSeleccion) ||
                    mensaje1.remitente.equalsIgnoreCase(nickNameSeleccion)){
                    mensaje+="mensaje de "+ mensaje1.remitente+" para "+mensaje1.destinatario+" ("+mensaje1.fecha+")\n"+
                            mensaje1.mensaje+"\n\n";
                }
            }
        }
        if(vendedor.getMuro().getPublicaciones().size()==0){
            return "El vendedor "+vendedor.usuario.getNickUsuario()+" no tiene mensajes\n\n";
        }
        return mensaje;
    }

    public boolean enviarMensaje(Mensaje mensaje) {
        Vendedor remitente=obtenerVendedor(mensaje.getRemitente());
        Vendedor destinatario=obtenerVendedor(mensaje.getDestinatario());
        if(remitente!=null && destinatario!=null){
            remitente.getMuro().getMensajes().add(mensaje);
            destinatario.getMuro().getMensajes().add(mensaje);
            return true;
        }
        return false;
    }
    public List<String>nickNameVendedores(List<Usuario> usuarios){
        List<String> listaNickNameUsuarios=new ArrayList<>();
        for(Usuario usuario:usuarios){
            listaNickNameUsuarios.add(usuario.getNickUsuario());
        }
        return listaNickNameUsuarios;
    }

    public String cantidadMensajesEntreVendedores() {
        List<String> vendedores = nickNameVendedores(obtenerListaUsuariosVendedores());
        List<Mensaje> mensajes = new ArrayList<>();
        List<MensajesEntreVendedores>relaciones=new ArrayList<>();
        List<MensajesEntreVendedores>relaciones2=new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            for (Mensaje mensaje : vendedor.getMuro().getMensajes()) {
                mensajes.add(mensaje);
            }
        }
        for (String v1 : vendedores){
            for (String v2 : vendedores) {
                MensajesEntreVendedores reporte=new MensajesEntreVendedores(v1,v2,0);
                for(Mensaje mensaje:mensajes){
                    if(mensaje.destinatario.equals(v1) && mensaje.remitente.equals(v2)){
                        reporte.cantidad++;
                    }
                }
                relaciones.add(reporte);
            }
        }
        return construirReporte(relaciones);
    }

    public String construirReporte(List<MensajesEntreVendedores> relaciones) {
        String reporte="Reporte mensajes enviados entre dos vendedores son:\n";
        for(MensajesEntreVendedores mensaje:relaciones){
            if(mensaje.cantidad>0){
                reporte+="mensajes entre "+mensaje.remitente+" y "+mensaje.destinatario+" "+mensaje.cantidad+"\n";
            }

        }
        reporte+="\n";
        if(relaciones.size()>0){
            return "No existen mensajes entre vendedores\n\n";
        }
        return  reporte;
    }

    public String obtenerProductosTop() {
        List<Publicacion>publicaicones=new ArrayList<>();
        Publicacion publicacionTop=new Publicacion();
        List<Publicacion>publicaiconesTop=new ArrayList<>();
        Publicacion aux=new Publicacion();
        int likes=0;
        for(Vendedor vendedor : listaVendedores){
            publicaicones.addAll(vendedor.getMuro().getPublicaciones());
        }
        for(Publicacion p:publicaicones){
            if(likes<= p.getLikes()){
                publicacionTop=p;
                likes=(int)p.getLikes();
            }
        }
        publicaiconesTop.add(publicacionTop);
        publicaicones.remove(publicacionTop);
        for(int i=likes;((10<publicaicones.size())&&(i!=0));i--){
            for(Publicacion p:publicaicones){
                if(i< p.getLikes()){
                    aux=p;
                }
            }
            publicaiconesTop.add(aux);
        }

        return construirReporteProductosTop(publicaiconesTop);
    }

    private String construirReporteProductosTop(List<Publicacion> publicaiconesTop) {
        String reporte="Reporte de productos Top:\n";
        for(Publicacion p:publicaiconesTop){
            reporte+="Id producto: "+p.idProducto+" con "+p.getLikes()+" likes\n";
        }
        reporte+="\n";
        if(publicaiconesTop.isEmpty()){
            return "No hay publicaciones con likes";
        }
        return reporte;
    }

    public String cantidadProductosPublicados(String nickName) {
        Vendedor vendedor = obtenerVendedor(nickName);
        String cantidad = "Reporte cantidad de productos publicados";
        if (vendedor != null){
            cantidad += " por "+vendedor.usuario.getNickUsuario()+" Fue de "
                    + Integer.toString(vendedor.getMuro().getPublicaciones().size())+"\n\n";
        }
        return cantidad;
    }

    public String obtenerPublicacionesFecha(LocalDate fechaPublicacion) {
        List<Publicacion>publicaciones=new ArrayList<>();
        List<Publicacion>publicacionsfecha=new ArrayList<>();
        for(Vendedor vendedor : listaVendedores){
            publicaciones.addAll(vendedor.getMuro().getPublicaciones());
        }
        for(Publicacion p:publicaciones){
            if(p.fecha.equals(fechaPublicacion)){
                publicacionsfecha.add(p);
            }
        }
        return construirReportePublicacionesFecha(publicacionsfecha,fechaPublicacion);
    }

    private String construirReportePublicacionesFecha(List<Publicacion> publicacionsfecha,LocalDate fechaPublicacion) {
        String reporte="Reporte de las publicaciones hechas en "+fechaPublicacion.toString()+"\n";
        for(Publicacion p:publicacionsfecha){
            reporte+="Publicacion "+p.nombre+" del producto "+p.idProducto+" publicada por "+p.nickName+"\n";
        }
        reporte+="\n";
        if(publicacionsfecha.size()==0){
            return "No hay publicaciones en la fecha"+fechaPublicacion.toString()+"\n\n";
        }
        return reporte;
    }
}


