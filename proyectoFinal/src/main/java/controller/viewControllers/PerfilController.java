package controller.viewControllers;

import java.io.IOException;
import controller.WishLista;
import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Nodo;
import model.Productos;
import utils.InputDialog;
import utils.Paths;
import utils.UserSession;

public class PerfilController {

  @FXML
  private ComboBox<?> cbOptions;

  @FXML
  private TextField lblCVV;

  @FXML
  private TextField lblCiudad;

  @FXML
  private TextField lblContacto;

  @FXML
  private TextField lblDireccion;

  @FXML
  private TextField lblFechaExp;

  @FXML
  private TextField lblNomEnvio;

  @FXML
  private TextField lblNomTar;

  @FXML
  private TextField lblNumTar;

  @FXML
  private VBox productos;

  @FXML
  void goToAdmin(ActionEvent event) {
    UserSession.getInstance().getUser().setAdmin(true);
    App.app.setScene(Paths.GESTIONAR_ADMIN_VIEW);
  }

  @FXML
  void goToCart(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CARRITO_VIEW);
  }

  @FXML
  void goToHistory(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_HISTORIAL_VIEW);
  }

  @FXML
  void goToHome(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CATALOGO_VIEW);
  }

  @FXML
  void guardarPerfil(ActionEvent event) {

  }


  private void cargarWishListUsuario() {
      productos.getChildren().clear(); // Limpiamos datos de ejemplo

      WishLista wishListManager = new WishLista();

      if (wishListManager.getEsVacia()) {
          // Podrías mostrar un label "Lista vacía"
          return;
      }

      Nodo<Productos> actual = wishListManager.cabecera;
      do {
          crearTarjetaWishList(actual.info);
          actual = actual.sig;
      } while (actual != wishListManager.cabecera);
  }

  private void crearTarjetaWishList(Productos p) {
      try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_WISHLIST_VIEW));
          HBox tarjeta = loader.load();

          ProductoWishController controller = loader.getController();
          // Pasamos el objeto completo para tener el ID
          controller.setDatosProducto(p);

          productos.getChildren().add(tarjeta);
      } catch (IOException e) {
          e.printStackTrace();
          InputDialog.error("Error", "Error al cargar item de wishlist: " + e.getMessage());
      }
    }
    public void initialize() {
        cargarWishListUsuario();
    }
}
