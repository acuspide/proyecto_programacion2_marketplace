package co.uniquindio.marketplacefx.marketplaceapp.utils;

import co.uniquindio.marketplacefx.marketplaceapp.model.*;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    public static MarketPlace inicializarDatos() {
        MarketPlace marketPlace = new MarketPlace();
        Administrador administrador = Administrador.builder()
            .nombre("Raul")
            .apellido("Perez")
            .cedula("987644")
            .usuario(new Usuario("admin", "admin", "987644"))
            .direccion("la casa")
            .build();
        marketPlace.setAdministrador(administrador);
        marketPlace.getListaUsuarios().add(administrador.usuario);

        Vendedor vendedor1 = Vendedor.builder()
            .nombre("Jesus")
            .apellido("Luligo")
            .cedula("1010")
            .direccion("Km 5")
            .usuario(new Usuario("yisus", "1234", "1010"))
            .build();
        Vendedor vendedor2 = Vendedor.builder()
            .nombre("Luis")
            .apellido("Ruiz")
            .cedula("4040")
            .direccion("Km 10")
            .usuario(new Usuario("lucho", "4321", "4040"))
            .build();
        Vendedor vendedor3 = Vendedor.builder()
            .nombre("Pedro")
            .apellido("Sanchez")
            .cedula("5050")
            .direccion("Km 15")
            .usuario(new Usuario("peter", "5678", "5050"))
            .build();
        Vendedor vendedor4 = Vendedor.builder()
            .nombre("Maria")
            .apellido("Gonzalez")
            .cedula("6060")
            .direccion("Km 20")
            .usuario(new Usuario("mari", "8765", "6060"))
            .build();
        Vendedor vendedor5 = Vendedor.builder()
            .nombre("Ana")
            .apellido("Torres")
            .cedula("7070")
            .direccion("Km 25")
            .usuario(new Usuario("ana", "1234", "7070"))
            .build();

        Producto producto1 = Producto.builder()
            .nombre("Mango")
            .id("3030")
            .categoria("Frutas")
            .precio(32.32)
            .rutaImagen("C:\\Users\\yisus\\OneDrive\\Escritorio\\imagenes\\frutas.PNG")
            .descripcion("Fruta fresca")
            .build();
        Producto producto2 = Producto.builder()
            .nombre("Pc")
            .id("301")
            .categoria("Electrónico")
            .precio(3000.2)
            .rutaImagen("C:\\Users\\yisus\\OneDrive\\Escritorio\\imagenes\\pc.PNG")
            .descripcion("Asus TUF Gaming")
            .build();
        Producto producto3 = Producto.builder()
            .nombre("Banana")
            .id("3031")
            .categoria("Frutas")
            .precio(20.50)
            .rutaImagen("C:\\Users\\yisus\\OneDrive\\Escritorio\\imagenes\\banana.PNG")
            .descripcion("Fruta amarilla")
            .build();
        Producto producto4 = Producto.builder()
            .nombre("Televisor")
            .id("302")
            .categoria("Electrónico")
            .precio(1500.0)
            .rutaImagen("C:\\Users\\yisus\\OneDrive\\Escritorio\\imagenes\\tv.PNG")
            .descripcion("Televisor 4K")
            .build();
        Producto producto5 = Producto.builder()
            .nombre("Silla")
            .id("304")
            .categoria("Muebles")
            .precio(150.75)
            .rutaImagen("C:\\Users\\yisus\\OneDrive\\Escritorio\\imagenes\\silla.PNG")
            .descripcion("Silla ergonómica")
            .build();

        vendedor1.getListaProductos().add(producto1);
        vendedor1.getListaProductos().add(producto2);
        vendedor2.getListaProductos().add(producto3);
        vendedor3.getListaProductos().add(producto4);
        vendedor4.getListaProductos().add(producto5);

        Comentario comentaroAux1=new Comentario("es bonito");
        List<Comentario>comentariosAux=new ArrayList<>();
        comentariosAux.add(comentaroAux1);
        Comentario comentaroAux2=new Comentario("ese produccto lo encontre mas barato en otro sitio");
        comentariosAux.add(comentaroAux2);
        vendedor1.getVendedoresAliados().add(vendedor2);
        vendedor2.getVendedoresAliados().add(vendedor1);
        vendedor2.getVendedoresAliados().add(vendedor3);
        vendedor3.getVendedoresAliados().add(vendedor2);

//        List<String> likesAux=new ArrayList<>();
//        likesAux.add(vendedor2.getNombre());
//
//        Publicacion publicacionAux=new Publicacion(producto1.getNombre(),
//                                                    producto1.getId(),
//                                                    producto1.getDescripcion(),
//                                                    likesAux,
//                                                    LocalDate.now(),
//                                                    comentariosAux,
//                                                    vendedor1.usuario.getNickUsuario());
//        vendedor1.getMuro().getPublicaciones().add(publicacionAux);
//
//        vendedor4.getVendedoresAliados().add(vendedor5);
//        vendedor5.getVendedoresAliados().add(vendedor4);
//        Mensaje mensajeAux1=new Mensaje(vendedor4.usuario.getNickUsuario(),
//                                        vendedor5.usuario.getNickUsuario(),
//                                        "hola, que mas",
//                                        LocalDate.now());
//        Mensaje mensajeAux2=new Mensaje(vendedor5.usuario.getNickUsuario(),
//                                        vendedor4.usuario.getNickUsuario(),
//                                        "bn y tu?",
//                                        LocalDate.now());
//        vendedor4.getMuro().mensajes.add(mensajeAux1);
//        vendedor4.getMuro().mensajes.add(mensajeAux2);
//        vendedor5.getMuro().mensajes.add(mensajeAux1);
//        vendedor5.getMuro().mensajes.add(mensajeAux2);

        marketPlace.getListaVendedores().add(vendedor1);
        marketPlace.getListaVendedores().add(vendedor2);
        marketPlace.getListaVendedores().add(vendedor3);
        marketPlace.getListaVendedores().add(vendedor4);
        marketPlace.getListaVendedores().add(vendedor5);
        // Agregar usuarios de vendedores
        marketPlace.getListaUsuarios().add(vendedor1.usuario);
        marketPlace.getListaUsuarios().add(vendedor2.usuario);
        marketPlace.getListaUsuarios().add(vendedor3.usuario);
        marketPlace.getListaUsuarios().add(vendedor4.usuario);
        marketPlace.getListaUsuarios().add(vendedor5.usuario);
        return marketPlace;
    }
}
