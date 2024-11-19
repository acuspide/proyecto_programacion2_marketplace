module co.uniquindio.marketplacefx.marketplaceapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens co.uniquindio.marketplacefx.marketplaceapp to javafx.fxml;
    exports co.uniquindio.marketplacefx.marketplaceapp;
    opens co.uniquindio.marketplacefx.marketplaceapp.controller;
    exports co.uniquindio.marketplacefx.marketplaceapp.controller;
    opens co.uniquindio.marketplacefx.marketplaceapp.viewcontroller;
    exports co.uniquindio.marketplacefx.marketplaceapp.viewcontroller;
}