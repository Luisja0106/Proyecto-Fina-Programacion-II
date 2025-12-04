package controller.viewControllers;

import java.io.File;
import java.io.IOException;

import application.App;
import controller.admin.AdminProductos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Admin;
import model.Nodo;
import model.Productos;
import utils.InputDialog;
import utils.Paths;
import utils.UserSession;

public class AdminController {
  @FXML
  private Button btnImg;

  @FXML
  private TextField lblstock;

  @FXML
  private TextField lblCategoria;

  @FXML
  private TextField lblNom;

  @FXML
  private TextField lblPrecio;

  @FXML
  private TextField lblSKU;

  @FXML
  private VBox productos;

  private String imagenR;

  AdminProductos admin = new AdminProductos();

  @FXML
  void AggProdu(ActionEvent event) {
    try {
      if (!verificaciones())
        return;
      String nombre = lblNom.getText();
      float precio = Float.parseFloat(lblPrecio.getText());
      String imagen = imagenR;
      String cate = lblCategoria.getText();
      int stock = Integer.parseInt(lblstock.getText());
      if (!admin.addLista(nombre, precio, imagen, cate, stock))
        return;
      productos.getChildren().clear();
      Admin user = UserSession.getInstance().getUser();
      Nodo<Productos> aux = user.getProductos().cabecera;
      do {
        addGrid(aux.info);
        aux = aux.sig;
      } while (aux != user.getProductos().cabecera);

    } catch (NumberFormatException e) {
      InputDialog.warning("Datos invalidos", "Favor ingrese unos datos validos");
    }
  }

  @FXML
  void addImg(ActionEvent event) {
    String ruta = selectImage(btnImg);
    if (ruta != null) {
      imagenR = ruta;
    }
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
  void goToProfile(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_PERFIL_VIEW);
  }

  private void addGrid(Productos produ) { // metodo para añadir a la grid
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_STOCKADMIN_VIEW));
      HBox tarjeta = loader.load();
      AdminProductController controller = loader.getController();
      controller.setProducto(produ.getNombre(), "", produ.getPrecio(), produ.getStock(), produ.getImagen());
      productos.getChildren().add(tarjeta);
    } catch (IOException e) {
      e.printStackTrace();
      InputDialog.error("error", "error" + e.getMessage());
    }
  }

  private void setGrid(Admin user) {
    if (!admin.setLista()) {
      productos.getChildren().clear();
      return;
    }
    Nodo<Productos> aux = user.getProductos().cabecera;
    do {
      addGrid(aux.info);
      aux = aux.sig;
    } while (aux != user.getProductos().cabecera);
  }

  public void initialize() {
    setGrid(UserSession.getInstance().getUser());
  }

  private String selectImage(Button btn) {
    FileChooser fileC = new FileChooser();
    fileC.setTitle("Seleccionar una imagen"); // titulo del fileChooser

    fileC.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Imagenes", "*.jpg", "*.png"));
    Stage stage = (Stage) btn.getScene().getWindow();
    File archivo = fileC.showOpenDialog(stage);

    if (archivo != null) {
      String ruta = archivo.getAbsolutePath();
      return ruta;
    }
    return null;
  }

  private boolean verificaciones() {
    if (lblNom.getText().isEmpty() || lblSKU.getText().isEmpty() || lblstock.getText().isEmpty()
        || lblPrecio.getText().isEmpty() || lblCategoria.getText().isEmpty() || imagenR.isEmpty()) {
      InputDialog.warning("Favor llene todos los datos", "Favor llene todos los datos");
      return false;
    }
    return true;
  }
}
