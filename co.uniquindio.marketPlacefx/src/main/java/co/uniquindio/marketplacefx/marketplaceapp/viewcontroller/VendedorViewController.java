package co.uniquindio.marketplacefx.marketplaceapp.viewcontroller;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import javafx.scene.control.Button;
import co.uniquindio.marketplacefx.marketplaceapp.controller.VendedorController;
import co.uniquindio.marketplacefx.marketplaceapp.decorator.EstadoProductoDecorator;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.SugerenciaDto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Comentario;
import co.uniquindio.marketplacefx.marketplaceapp.model.Mensaje;
import co.uniquindio.marketplacefx.marketplaceapp.model.Publicacion;
import co.uniquindio.marketplacefx.marketplaceapp.service.IPublicacionObserver;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class VendedorViewController implements IPublicacionObserver {
    public String nickNameTab = "";
    VendedorController vendedorController;
    List<Integer> idGenerados = new ArrayList<>();
    ObservableList<ProductoDto> productos = FXCollections.observableArrayList();
    ObservableList<SugerenciaDto> listaSugerencias = FXCollections.observableArrayList();
    ObservableList<EstadoProductoDecorator>productosDecorator= FXCollections.observableArrayList();
    ObservableList<SugerenciaDto> contactosVendedor = FXCollections.observableArrayList();
    ObservableList<String> categorias = FXCollections.observableArrayList(new String[]{
            "","Electrónica","Hogar", "Moda", "Salud", "Juegos", "Autopartes", "Mascotas",
            "Deportes", "Entretenimiento","Oficina"});
    List<String>publicaciones = new ArrayList<>();
    List<String>productosVendido = new ArrayList<>();
    EstadoProductoDecorator productoDecoratorSeleccionado;
    Publicacion publicacionSeleccionada;
    SugerenciaDto sugerenciaSelecioanda;
    SugerenciaDto contactoSeleccionado;

    public void setNickNameTab(String nickNameTab) {
        this.nickNameTab = nickNameTab;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView ImagenProducto;

    @FXML
    private Button bntActualizarProducto;

    @FXML
    private Button bntAgregarImagen;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private Button bntGuardarProducto;

    @FXML
    private Button btnProductoVendido;;

    @FXML
    private Button btnComentar;

    @FXML
    private Button btnAgregarContacto;

    @FXML
    private Button btnEnviarMensaje;

    @FXML
    private Button btnPublicarProducto;

    @FXML
    private Button btnLimpiarCampos;

    @FXML
    private Button btnLike;

    @FXML
    private ChoiceBox<String> choiceCategoriaProducto;

    @FXML
    private Label lbId;

    @FXML
    private Label lbContactoVendedor;


    @FXML
    private Label lbSugerenciaSeleccionada;

    @FXML
    private Label lbContadorLike;

    @FXML
    private Label lbDireccionImagen;

    @FXML
    private TableView<SugerenciaDto> tableContactosVendedor;

    @FXML
    private TableColumn<SugerenciaDto, String> tbNombreContacto;

    @FXML
    private TableColumn<SugerenciaDto, String> tbUsuarioContacto;

    @FXML
    private Tab mensajeVendedor;

    @FXML
    private Tab muroVendedor;

    @FXML
    private Tab productosVendedor;

    @FXML
    private TableView<SugerenciaDto> tableSugerencias;

    @FXML
    private TableColumn<SugerenciaDto, String> tbNombreSugerencias;

    @FXML
    private TableColumn<SugerenciaDto, String> tbUsuarioSugerencias;

    @FXML
    private TableView<EstadoProductoDecorator> tableProductos;

    @FXML
    private TableColumn<EstadoProductoDecorator, String> tbCategoriaProducto;

    @FXML
    private TableColumn<EstadoProductoDecorator, String> tbCodigoProducto;

    @FXML
    private TableColumn<EstadoProductoDecorator, String> tbNombreProducto;

    @FXML
    private TableColumn<EstadoProductoDecorator, Double> tbPrecioProducto;

    @FXML
    private TableColumn<EstadoProductoDecorator, String> tbEstadoProducto;
    @FXML
    private TableView<Publicacion> tablePublicaciones;

    @FXML
    private TableColumn<Publicacion, String> tbNombrePublicacion;


    @FXML
    private TableColumn<Publicacion, String> tbDescripcionPublicacion;

    @FXML
    private TableColumn<Publicacion, String> tbNickNamePublicacion;

    @FXML
    private TableColumn<Publicacion, String> tbFechaPublicacion;

    @FXML
    private Label lbCaracteristicasProducto;

    @FXML
    private ImageView imagenProductoPublicado;

    @FXML
    private TextArea txtComentario;

    @FXML
    private TextArea txtDescripcionProduccto;

    @FXML
    private TextField txtEscribirComentarioProducto;

    @FXML
    private TextArea txtEscribirMensajeVendedor;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecioProducto;

    @FXML
    private TextArea txtVerMensajeVendedor;

    @FXML
    void initialize() {
        vendedorController = new VendedorController();
        initDataBinding();
        choiceCategoriaProducto.setItems(categorias);
        listenerSelectionProducto();
        listenerSelectionPublicacion();
        listenerSelectionSugerencias();
        listenerSelectionContacto();
        bloqueoViews();

    }

    public void actualizarViews(){
        actualizarListaProductos();
        actualizarTablaProductos();
        actualizarListaSugerencias();
       actualizarTablaContactos();
        actualizarTablaPublicaciones();
    }



    private void initDataBinding() {
        tbNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbCodigoProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tbPrecioProducto.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrecio()).asObject());
        tbCategoriaProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        tbEstadoProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado()));

        tbNombreSugerencias.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tbUsuarioSugerencias.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nickName()));

        tbNombrePublicacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre));
        tbDescripcionPublicacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion));
        tbNickNamePublicacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nickName));
        tbFechaPublicacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fecha.toString()));

        tbNombreContacto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tbUsuarioContacto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nickName()));

    }

    @FXML
    void onLike(ActionEvent event) {
        agregarLike();
        vendedorController.actualizarViews();
        actualizarViews();
    }

    @FXML
    void onActualizarProducto(ActionEvent event) {
        actualizarProducto();
        vendedorController.actualizarViews();
        actualizarViews();
    }

    @FXML
    void onProductoEliminado(ActionEvent event) {
        eliminarProducto();
        vendedorController.actualizarViews();
        actualizarViews();
    }

    @FXML
    void onProductoVendido(ActionEvent event) {
        productoVendido();
        vendedorController.actualizarViews();
        actualizarViews();
    }

    @FXML
    void onAgregarImagen(ActionEvent event) {
        agregarImagenProducto();
        vendedorController.actualizarViews();
        actualizarViews();
    }

    @FXML
    void onLimpiarCampos(ActionEvent event) {
        limpiarCamposCrudProducto();
        vendedorController.actualizarViews();
        actualizarViews();
    }

    @FXML
    void onComentar(ActionEvent event) {
        comentarPublicacion();
        vendedorController.actualizarViews();
        actualizarViews();
    }

    @FXML
    void onEnviarMensaje(ActionEvent event) {
        enviarMensaje();
        vendedorController.actualizarViews();
        lbContactoVendedor.setText("");
        actualizarViews();
    }

    @FXML
    void onAgregarContacto(ActionEvent event) {
        agregarContacto();
        vendedorController.actualizarViews();
        actualizarViews();
    }

    @FXML
    void onPublicarProducto(ActionEvent event) {
        if(validarCamposVacios()){
            if(lbId.getText().isEmpty()){
                publicarProductoNuevo();
            }else{
                publicarProductoExistente();
            }
            limpiarCamposCrudProducto();
        }
        actualizarTablaPublicaciones();
        vendedorController.actualizarViews();
        actualizarViews();
    }

    @FXML
    void onGuardarProducto(ActionEvent event) {
        if(validarCamposVacios()){
            ProductoDto productoDto = crearProductoDto();
            if(vendedorController.agregarProducto(productoDto,vendedorController.getUsuarioActual())){//modificado
                actualizarListaProductos();
                productosDecorator.add(productoDtoToProductoDecorado(productoDto));
                System.out.println("producto guardado");

            }
            limpiarCamposCrudProducto();
        }
        vendedorController.actualizarViews();
        actualizarViews();
    }

    private void actualizarListaProductos(){
        productos.clear();
        productos.addAll(vendedorController.obtenerlistaProductos(vendedorController.getUsuarioActual()));//modificado
    }

    private void actualizarProducto() {
        if(validarCamposVacios()){
            ProductoDto productoActualizado = new ProductoDto(txtNombreProducto.getText(),productoDecoratorSeleccionado.getId(),(String)choiceCategoriaProducto.getValue(),
                    Double.parseDouble(txtPrecioProducto.getText()),lbDireccionImagen.getText(),txtDescripcionProduccto.getText());;
            EstadoProductoDecorator productoDecoratorActualizado = new EstadoProductoDecorator(productoActualizado,productoDecoratorSeleccionado.getEstado());
            if(productoDecoratorActualizado.getEstado().equalsIgnoreCase("Guardado")){
                if(vendedorController.actualizarProducto(productoActualizado,nickNameTab)){
                    productosDecorator.remove(productoDecoratorSeleccionado);
                    productosDecorator.add(productoDecoratorActualizado);
                }
            } else if (productoDecoratorActualizado.getEstado().equalsIgnoreCase("Publicado")) {
                if(vendedorController.actualizarProductoPublicado(productoActualizado, nickNameTab)){
                    productosDecorator.remove(productoDecoratorSeleccionado);
                    productosDecorator.add(productoDecoratorActualizado);
                }

            }
        }
        actualizarTablaProductos();
        actualizarTablaPublicaciones();
    }

    private ProductoDto estadoProductoDecoratorToProductoDto(EstadoProductoDecorator estadoProducto) {
        ProductoDto productoDto=new ProductoDto(estadoProducto.getNombre(),
                                                estadoProducto.getId(),
                                                estadoProducto.getCategoria(),
                                                estadoProducto.getPrecio(),
                                                estadoProducto.getRutaImagen(),
                                                estadoProducto.getDescripcion());
        return productoDto;
    }

    private void limpiarCamposCrudProducto() {
        txtDescripcionProduccto.setText("");
        txtPrecioProducto.setText("");
        choiceCategoriaProducto.setValue("");
        txtNombreProducto.setText("");
        ImagenProducto.setVisible(false);
        lbDireccionImagen.setText("");
        lbId.setText("");

    }

    private boolean validarCamposVacios() {
        if(txtNombreProducto.getText().isEmpty() ||
                choiceCategoriaProducto.getValue().equals("") ||
                txtDescripcionProduccto.getText().isEmpty() ||
                txtPrecioProducto.getText().isEmpty() ||
                lbDireccionImagen.getText().isEmpty()) {
            return false;

        }else{
            return true;
        }
    }

    public void actualizarListaSugerencias() {
        tableSugerencias.getItems().clear();
        listaSugerencias.addAll(vendedorController.listaSugerencias(nickNameTab));
        tableSugerencias.setItems(listaSugerencias);
    }
    private void actualizarTablaContactos() {
        tableContactosVendedor.getItems().clear();
        contactosVendedor.addAll(vendedorController.obtenerUsuarioContacto(nickNameTab));
        tableContactosVendedor.setItems(contactosVendedor);
        actualizarListaSugerencias();
    }

    private void actualizarPublicaciones(List<Publicacion> publicacionesActualizado) {
        limpiarCamposPublicacion();
        actualizarTablaPublicaciones();
    }

    private void actualizarTablaPublicaciones(){
        tablePublicaciones.getItems().clear();
        tablePublicaciones.getItems().addAll(vendedorController.obtenerPublicaciones(nickNameTab));
    }

    private void actualizarTablaProductos(){
        tableProductos.getItems().clear();
        productosDecorator.clear();
        productosDecorator.addAll(listaProductosDecorator(vendedorController.obtenerlistaProductos(nickNameTab)));
        tableProductos.getItems().addAll(productosDecorator);
    }

    private void actualizarEstadoProducto(String estado, String id) {
        EstadoProductoDecorator producto=null;
        for(EstadoProductoDecorator productoDecorator: productosDecorator){
            if(productoDecorator.getId().equals(id)){
                producto=productoDecorator;
                break;
            }
        }
        productosDecorator.remove(producto);
        producto.estadoProducto=estado;
        productosDecorator.add(producto);
        actualizarTablaProductos();
    }


    private ProductoDto crearProductoDto(){
        ProductoDto productoDto = new ProductoDto(txtNombreProducto.getText(),generarId(),(String)choiceCategoriaProducto.getValue(),
                Double.parseDouble(txtPrecioProducto.getText()),lbDireccionImagen.getText(),txtDescripcionProduccto.getText());
        return productoDto;
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    private String generarId(){
        String id="";
        Random random = new Random();
        do {
            id = String.valueOf( 100000 + random.nextInt(900000));
        } while (idGenerados.contains(id));

        return id;
    }

    private void listenerSelectionProducto() {
        //Sirve la dar la seleccion de la tabla cada que se seleccione se va guardar en una variable cliente seleccionado
        tableProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoDecoratorSeleccionado = newSelection;
            //Cada que se seleccione de hace una nueva seleccion y se va mostrar el seleccionado
            mostrarInformacionProducto(productoDecoratorSeleccionado);
        });
    }

    private void mostrarInformacionProducto(EstadoProductoDecorator productoDecoratorSeleccionado) {
        //Verificar que el cliente exista
        if(productoDecoratorSeleccionado !=null){
            //Mostrar en los texFiel los datos que se selecciono
            txtNombreProducto.setText(productoDecoratorSeleccionado.getNombre());
            txtDescripcionProduccto.setText(productoDecoratorSeleccionado.getDescripcion());
            txtPrecioProducto.setText(Double.toString(productoDecoratorSeleccionado.getPrecio()));
            choiceCategoriaProducto.setValue(productoDecoratorSeleccionado.getCategoria());
            lbDireccionImagen.setText(productoDecoratorSeleccionado.getRutaImagen());
            lbId.setText(productoDecoratorSeleccionado.getId());
            ImagenProducto.setImage(new Image("file:" + productoDecoratorSeleccionado.getRutaImagen()));
            ImagenProducto.setVisible(true);

        }
    }


    private void listenerSelectionPublicacion() {
        //Sirve la dar la seleccion de la tabla cada que se seleccione se va guardar en una variable cliente seleccionado
        tablePublicaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            publicacionSeleccionada = newSelection;
            //Cada que se seleccione de hace una nueva seleccion y se va mostrar el seleccionado
            mostrarPublicacionSelecionada(publicacionSeleccionada);
        });
    }
    private void mostrarPublicacionSelecionada(Publicacion publicacionSeleccionada) {
        //Verificar que el cliente exista
        if(publicacionSeleccionada !=null){
            ProductoDto productoDto=new ProductoDto("","","",0,"","");
            ProductoDto productoAliado = vendedorController.obtenerProductoAlido(publicacionSeleccionada.nickName,publicacionSeleccionada.idProducto);
            System.out.println(publicacionSeleccionada.nickName);
            System.out.println(nickNameTab);
            productos.clear();
            productos.addAll(vendedorController.obtenerlistaProductos(nickNameTab));
            for(ProductoDto producto:productos){
                if(publicacionSeleccionada.idProducto.equalsIgnoreCase(producto.id())){
                        productoDto=producto;
                } else if (publicacionSeleccionada.idProducto.equalsIgnoreCase(productoAliado.id())) {
                    productoDto=productoAliado;
                }
            }


            lbCaracteristicasProducto.setText("Precio: "+productoDto.precio()+
                                              "\nCategoria: "+productoDto.categoria()+
                                              "Id: "+productoDto.id());
            txtComentario.setText(mostrarMensajesProducto(publicacionSeleccionada.comentarios));
            lbContadorLike.setText(""+publicacionSeleccionada.getLikes());
            imagenProductoPublicado.setImage(new Image("file:" + productoDto.rutaImagen()));
            imagenProductoPublicado.setVisible(true);

        }
    }

    private void listenerSelectionSugerencias() {
        //Sirve la dar la seleccion de la tabla cada que se seleccione se va guardar en una variable cliente seleccionado
        tableSugerencias.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            sugerenciaSelecioanda = newSelection;
            //Cada que se seleccione de hace una nueva seleccion y se va mostrar el seleccionado
            mostrarSugerenciaSeleccionada(sugerenciaSelecioanda);
        });
    }
    private void mostrarSugerenciaSeleccionada(SugerenciaDto sugerenciaSeleccionada) {
        //Verificar que el cliente exista
        if(sugerenciaSeleccionada !=null){
            SugerenciaDto sugerenciaDto= sugerenciaSeleccionada;
            actualizarListaSugerencias();
            lbSugerenciaSeleccionada.setText(sugerenciaDto.nombre());

        }
    }

    private void limpiarCamposPublicacion(){
        lbCaracteristicasProducto.setText("");
        txtComentario.setText("");
        lbContadorLike.setText("");
        imagenProductoPublicado.setVisible(false);
    }

    private String mostrarMensajesProducto(List<Comentario> comentarios) {
        String mensaje="";
        for(Comentario comentario:comentarios){
            mensaje+=comentario.getComentario()+"\n";
        }
        return mensaje;
    }

    private void listenerSelectionContacto() {
        //Sirve la dar la seleccion de la tabla cada que se seleccione se va guardar en una variable cliente seleccionado
        tableContactosVendedor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            contactoSeleccionado = newSelection;
            //Cada que se seleccione de hace una nueva seleccion y se va mostrar el seleccionado
            mostrarContactoSeleccionado(contactoSeleccionado);
        });
    }
    private void mostrarContactoSeleccionado(SugerenciaDto contactoSeleccionado) {
        //Verificar que el cliente exista
        if(contactoSeleccionado !=null){
            SugerenciaDto sugerenciaDto= contactoSeleccionado;
            actualizarTablaContactos();
            lbContactoVendedor.setText(sugerenciaDto.nombre());
            txtVerMensajeVendedor.setText(vendedorController.obtenerMensajesVendedor(nickNameTab,contactoSeleccionado.nickName()));
        }
    }

    private void comentarPublicacion() {
        if(!txtEscribirComentarioProducto.equals("")){
            Comentario comentario=new Comentario(txtEscribirComentarioProducto.getText());
            if(vendedorController.agregarComentario(comentario, publicacionSeleccionada.idProducto,vendedorController.getUsuarioActual())){
                txtEscribirComentarioProducto.setText("");

                mostrarPublicacionSelecionada(publicacionSeleccionada);
                actualizarTablaPublicaciones();
            }
        }
    }
    private void agregarLike() {
        if(vendedorController.agregarLike(publicacionSeleccionada,vendedorController.getUsuarioActual())){
            mostrarPublicacionSelecionada(publicacionSeleccionada);
            actualizarTablaPublicaciones();
        }
    }


    private void productoVendido() {
        if((productoDecoratorSeleccionado !=null)&&(productoDecoratorSeleccionado.estadoProducto.equalsIgnoreCase("Publicado"))){
            if(vendedorController.productoVendido(productoDecoratorSeleccionado.getId(),nickNameTab)){
                productosVendido.add(productoDecoratorSeleccionado.getId());
                publicaciones.remove(productoDecoratorSeleccionado.getId());
                actualizarEstadoProducto("Vendido",productoDecoratorSeleccionado.getId());
                System.out.println("producto vendido");
            }

        }
        actualizarPublicaciones(vendedorController.obtenerPublicaciones(nickNameTab));
        actualizarTablaProductos();
        limpiarCamposPublicacion();
        actualizarListaSugerencias();
        actualizarTablaContactos();
    }

    private void publicarProductoNuevo() {
        ProductoDto productoDto = crearProductoDto();
        if(vendedorController.agregarProducto(productoDto,nickNameTab)){
            actualizarListaProductos();
            publicaciones.add(productoDto.id());
            productosDecorator.add(productoDtoToProductoDecorado(productoDto));
            if(vendedorController.publicarProducto(productoDto.id(),nickNameTab)){
                actualizarEstadoProducto("Publicado",productoDto.id());
                actualizarPublicaciones(vendedorController.obtenerPublicaciones(nickNameTab));
                actualizarTablaProductos();
            }
        }
    }

    private void publicarProductoExistente() {
        ProductoDto productoDto = new ProductoDto(productoDecoratorSeleccionado.getNombre(),
                productoDecoratorSeleccionado.getId(),
                productoDecoratorSeleccionado.getCategoria(),
                productoDecoratorSeleccionado.getPrecio(),
                productoDecoratorSeleccionado.getRutaImagen(),
                productoDecoratorSeleccionado.getDescripcion());
        EstadoProductoDecorator productoDecorator=null;
        for(EstadoProductoDecorator producto:productosDecorator){
            if(producto.getId().equals(productoDto.id())){
                productoDecorator=producto;
                break;
            }

        }
        if((productoDecorator != null)&&(!productoDecorator.estadoProducto.equalsIgnoreCase("Vendido")
                                    &&(!productoDecorator.estadoProducto.equalsIgnoreCase("Publicado")))){
            if(vendedorController.publicarProducto(productoDto.id(),nickNameTab)){
                publicaciones.add(productoDecorator.getId());
                actualizarEstadoProducto("Publicado",productoDto.id());
                tableProductos.getItems().clear();
                actualizarTablaProductos();
                actualizarPublicaciones(vendedorController.obtenerPublicaciones(nickNameTab));
                System.out.println("producto publicado");
            }
        }
    }

    private void agregarImagenProducto() {
        FileChooser fileChooser = new FileChooser();
        File archivoSeleccionado = fileChooser.showOpenDialog(new Stage());

        if (archivoSeleccionado != null) {
            String rutaImagen = archivoSeleccionado.getAbsolutePath();
            lbDireccionImagen.setText(rutaImagen);
            ImagenProducto.setImage(new Image("file:" + rutaImagen));
            ImagenProducto.setVisible(true);
        }
    }
    private List<EstadoProductoDecorator> listaProductosDecorator(List<ProductoDto> productosDto){
        List<EstadoProductoDecorator> productosDecorator= new ArrayList<>();
        for(ProductoDto producto: productosDto){
            productosDecorator.add(productoDtoToProductoDecorado(producto));
        }
        return productosDecorator;
    }

    private EstadoProductoDecorator productoDtoToProductoDecorado(ProductoDto productoDto) {
        EstadoProductoDecorator producto = new EstadoProductoDecorator(productoDto,obtenerEstadoProducto(productoDto));
        return producto;
    }

    private String obtenerEstadoProducto(ProductoDto productoDto) {
        boolean publicado = false;
        boolean vendido = false;
        for(String productoPublicado:publicaciones){
            if(productoDto.id().equals(productoPublicado)){
                publicado = true;
            }
        }
        for(String productoVendido:productosVendido){
            if(productoDto.id().equals(productoVendido)){
                vendido = true;
            }
        }
        if(publicado==true){
            return "Publicado";

        }else {
            if(vendido==true){
                return  "Vendido";
            }else{
                return "Guardado";
            }
        }
    }

    private void eliminarProducto() {
        EstadoProductoDecorator producto=productoDecoratorSeleccionado;
        if(validarCamposVacios()){
            if(producto.estadoProducto.equalsIgnoreCase("Guardado")){
                if(vendedorController.eliminarProducto(producto.getId(), nickNameTab)) {
                    productos.remove(estadoProductoDecoratorToProductoDto(producto));
                    tableProductos.getItems().clear();
                    actualizarTablaProductos();
                    actualizarPublicaciones(vendedorController.obtenerPublicaciones(nickNameTab));
                    System.out.println("producto eliminado");
                }
            } else if (producto.estadoProducto.equalsIgnoreCase("Publicado")) {
                if(vendedorController.eliminarProductoPublicado(producto.getId(), nickNameTab)) {
                    actualizarEstadoProducto("Guardado",producto.getId());
                    publicaciones.remove(producto.getId());
                    tableProductos.getItems().clear();
                    actualizarTablaProductos();
                    actualizarPublicaciones(vendedorController.obtenerPublicaciones(nickNameTab));
                    System.out.println("publicacion eliminada");

                }

            }

        }

    }
    private void agregarContacto() {
        if(vendedorController.agregarContacto(sugerenciaSelecioanda.nickName(),nickNameTab)){
            actualizarListaSugerencias();
            actualizarTablaContactos();

        }
    }

    private void enviarMensaje() {
        if(contactoSeleccionado!=null && !txtEscribirMensajeVendedor.equals("")){
            System.out.println(txtEscribirMensajeVendedor.getText()+" "+contactoSeleccionado.nickName());
            Mensaje mensaje=new Mensaje(nickNameTab,contactoSeleccionado.nickName(),txtEscribirMensajeVendedor.getText(),LocalDate.now());
            if(vendedorController.enviarMensaje(mensaje)){
                txtVerMensajeVendedor.setText(vendedorController.obtenerMensajesVendedor(nickNameTab,contactoSeleccionado.nickName()));
            }
            txtEscribirMensajeVendedor.setText("");
        }
    }

    @Override
    public void notificar(Publicacion publicacion) {
        vendedorController.publicarProductoSuscrito(publicacion);
    }

    public void bloqueoViews(){
        btnPublicarProducto.setDisable(false);
        bntActualizarProducto.setDisable(false);
        bntAgregarImagen.setDisable(false);
        btnEliminarProducto.setDisable(false);
        btnEnviarMensaje.setDisable(false);
        txtEscribirMensajeVendedor.setDisable(false);
        btnAgregarContacto.setDisable(false);
        btnComentar.setDisable(false);
        txtEscribirComentarioProducto.setDisable(false);
        btnLike.setDisable(false);
        mensajeVendedor.setDisable(false);
        productosVendedor.setDisable(false);
        tableSugerencias.setDisable(false);

        if(!nickNameTab.equals(vendedorController.getUsuarioActual())){
            if(vendedorController.getUsuarioActual().equals(vendedorController.obtenerAdmin())){
                btnPublicarProducto.setDisable(true);
                bntActualizarProducto.setDisable(true);
                bntAgregarImagen.setDisable(true);
                btnEliminarProducto.setDisable(true);
                btnEnviarMensaje.setDisable(true);
                //btnProductoVendido.setDisable(true);
                txtEscribirMensajeVendedor.setDisable(true);
                btnAgregarContacto.setDisable(true);
                btnComentar.setDisable(true);
                txtEscribirComentarioProducto.setDisable(true);
                btnLike.setDisable(true);

            }else{
                mensajeVendedor.setDisable(true);
                productosVendedor.setDisable(true);
                tableSugerencias.setDisable(true);
            }

        }

    }
}
