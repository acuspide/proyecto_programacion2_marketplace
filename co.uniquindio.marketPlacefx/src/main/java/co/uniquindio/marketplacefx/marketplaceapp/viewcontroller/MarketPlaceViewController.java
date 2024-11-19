package co.uniquindio.marketplacefx.marketplaceapp.viewcontroller;

import co.uniquindio.marketplacefx.marketplaceapp.MarketPlaceApplication;
import co.uniquindio.marketplacefx.marketplaceapp.controller.MarketPlaceController;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.SugerenciaDto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Administrador;
import co.uniquindio.marketplacefx.marketplaceapp.model.ControladoresVistas;
import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class MarketPlaceViewController {
    MarketPlaceController marketPlaceController;
    List<Usuario> listaUsuarios = new ArrayList<>();
    String usuarioActual;
    List<ControladoresVistas>controladoresVistas = new ArrayList<>();
    private static MarketPlaceViewController marketPlaceViewController;

    public static MarketPlaceViewController getInstance() {
        if(marketPlaceViewController == null){
            return new MarketPlaceViewController();
        }
        return marketPlaceViewController;
    }

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab administrador;

    @FXML
    public Button btnSalida;


    @FXML
    void onSalir(ActionEvent event) throws IOException {
        MarketPlaceApplication.setRoot("login");
    }

    @FXML
    void initialize() {
        marketPlaceController = new MarketPlaceController();
        usuarioActual=marketPlaceController.obtenerUsuarioActual();

        try {
            URL adminViewUrl = getClass().getResource("/co/uniquindio/marketplacefx/marketplaceapp/administrador.fxml");
            System.out.println("URL encontrada: " + adminViewUrl);

            if (adminViewUrl == null) {
                throw new IOException("No se puede encontrar AdminViews.fxml en la ruta especificada.");
            }
            FXMLLoader loader = new FXMLLoader(adminViewUrl);
            AnchorPane adminContent = loader.load();
            AdministradorViewController adminController = loader.getController();
            adminController.setMarketplaceController(this);
            administrador.setContent(adminContent);
            obtenerUsuarios();
            cargarViewUsuario();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarViewUsuario() {
        for (Usuario usuario : listaUsuarios) {
            agregarTabVendedor(usuario.getNickUsuario());
        }
        vistaUsuarioActual();
        bloqueoViews();
        bloqueoAmigos();
    }

    private void obtenerUsuarios() {
        listaUsuarios.addAll(marketPlaceController.obtenerListaUsuariosVendedores());
    }

    public void agregarTabVendedor(String nickName) {
        if (nickName == null || nickName.isEmpty()) {
            System.out.println("Cédula no puede estar vacía.");
            return;
        }
        if(tabPane.getTabs().size()<11) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/marketplacefx/marketplaceapp/vendedor.fxml"));
                AnchorPane vendedorContent = loader.load();
                VendedorViewController vendedorController = loader.getController();
                controladoresVistas.add(new ControladoresVistas(vendedorController, nickName));
                vendedorController.setNickNameTab(nickName);
                Tab nuevoTab = new Tab();
                nuevoTab.setText(nickName); // Título del tab con la cédula
                nuevoTab.setContent(vendedorContent); // Establecer el contenido del tab
                tabPane.getTabs().add(nuevoTab);
                vendedorController.initialize();
                vendedorController.actualizarViews();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al cargar la vista de Vendedor.");
            }
        }
    }

    public void eliminarTabVendedor(String cedula) {
        if (cedula == null || cedula.isEmpty()) {
            System.out.println("Cédula no puede estar vacía.");
            return;
        }

        // Buscar el tab con el título que coincide con la cédula
        Tab tabToRemove = null;
        for (Tab tab : tabPane.getTabs()) {
            if (cedula.equals(tab.getText())) {
                tabToRemove = tab;
                break;
            }
        }

        // Si se encontró el tab, eliminarlo
        if (tabToRemove != null) {
            tabPane.getTabs().remove(tabToRemove);
            System.out.println("Tab con cédula " + cedula + " eliminado.");
        } else {
            System.out.println("No se encontró un tab con la cédula " + cedula);
        }
    }

    private void vistaUsuarioActual(){
        for (Tab tab : this.tabPane.getTabs()) {
            if (tab.getText().equals(usuarioActual)) {
                tabPane.getSelectionModel().select(tab);
                break;
            }
        }
    }

    public void actualizarViews() {
        for (ControladoresVistas controlador : controladoresVistas) {
            controlador.controlador.actualizarViews();
        }
    }

    public void bloqueoViews(){
        if(!usuarioActual.equals("admin")){
            administrador.setDisable(true);
        }
    }

    public void bloqueoAmigos(){
        if(!usuarioActual.equals("admin")){
            List<SugerenciaDto> usuarios=marketPlaceController.obtenerUsuarioContacto(usuarioActual);
            for(Tab tab: tabPane.getTabs()){
                if(usuarios.size()!=0){
                    for(SugerenciaDto sugerenciaDto: usuarios) {
                        if (tab.getText().equals(sugerenciaDto.nickName())||tab.getText().equals(usuarioActual)) {
                            tab.setDisable(false);
                            break;
                        } else {
                            tab.setDisable(true);
                        }
                    }
                }else {
                    if (!tab.getText().equals(usuarioActual)){
                    tab.setDisable(true);
                    }
                }

            }
        }
    }
}
