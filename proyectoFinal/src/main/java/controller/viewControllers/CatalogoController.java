package controller.viewControllers;

import java.io.IOException;

import application.App;
import controller.ProductoLista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.Nodo;
import model.Productos;
import utils.InputDialog;
import utils.Paths;

public class CatalogoController {
  @FXML
  private Button btnCart;

  @FXML
  private Button btnExit;

  @FXML
  private Button btnUser;

  @FXML
  private TextField buscador;

  @FXML
  private ComboBox<String> cbFiltroOrdenar;

  @FXML
  private ScrollPane center;

  @FXML
  private Label left_square_title;

  @FXML
  private RadioButton rbAuriculares;

  @FXML
  private RadioButton rbDAC;

  @FXML
  private RadioButton rbDisco;

  @FXML
  private RadioButton rbIEMS;

  @FXML
  private RadioButton rbTWS;

  @FXML
  private RadioButton rbTocaDisco;

  @FXML
  private TextField txtPrecioMax;

  @FXML
  private TextField txtPrecioMin;
  @FXML
  private TilePane tlObjetos;

  @FXML
  void cart(ActionEvent event) {
    InputDialog.information("Dirigiendose al carrito", "Dirigiendose al carrito");
  }

  @FXML
  void exit(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_LOGIN_VIEW);
  }

  @FXML
  void user(ActionEvent event) {
    InputDialog.information("Ingresando al perfil", "Ingresando al perfil");
  }

  private ProductoLista catalogoDefault = new ProductoLista();

  public void initialize() {
    ToggleGroup filtro = new ToggleGroup();
    rbDAC.setToggleGroup(filtro);
    rbIEMS.setToggleGroup(filtro);
    rbAuriculares.setToggleGroup(filtro);
    rbDisco.setToggleGroup(filtro);
    rbTWS.setToggleGroup(filtro);
    rbTocaDisco.setToggleGroup(filtro);
    cbFiltroOrdenar.getItems().addAll("A-Z", "Z-A", "Menor Precio", "Mayor Precio");
    actualizarCatalogo();
  }

  public void cargarProducto() {
    // TODO: Configrmar como se van a agregar los productos
  }

  public void actualizarCatalogo() {
    ProductoLista listaFiltrada = catalogoDefault.getListaFiltrada(0, 100000, "", "A-Z");
    Nodo<Productos> actual = listaFiltrada.cabecera;
    do {
        addProductsToGrig(actual.info);
        actual = actual.sig;
    } while (actual != listaFiltrada.cabecera);
  }

  private void addGrig() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_PRODUCTOS_VIEW));
      VBox tarjeta = loader.load();
      ProductController controller = loader.getController();
      controller.setProducto("Kz castor bass", 5000000, "/Imagenes/KZ-castor-bass.jpg");
      tlObjetos.getChildren().add(tarjeta);
    } catch (IOException e) {
      InputDialog.error("error", "error: " + e.getMessage());
    }
  }

  private void addProductsToGrig(Productos producto) {
      try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_PRODUCTOS_VIEW));
          VBox tarjeta = loader.load();
          ProductController controller = loader.getController();
          controller.setProducto(producto.getNombre(), producto.getPrecio(), producto.getImagen());
          tlObjetos.getChildren().add(tarjeta);
      } catch (IOException e) {
          InputDialog.error("error", "error: " + e.getMessage());
      }
  }
  // TODO: metodo para agg a la grid en un futuro
  // private void addGrid(Productos produ){
  // try {
  // FXMLLoader loader = new FXMLLoader(Paths.GESTIONAR_PRODUCTOS_VIEW);
  // Vbox tarjeta = loader.load();
  // ProductController controller = loader.getController();
  // controller.setProducto(produ.getNom(), produ.getPrecio(), produ.getRuta());
  // tlObjetos.getChildren().add(tarjeta);
  // }catch(IOException e){
  //
  // }
  // }
}
