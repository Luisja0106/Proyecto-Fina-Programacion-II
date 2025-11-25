package controller.viewControllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import utils.InputDialog;
import utils.Paths;

public class CatalogoController {

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

  public void initialize() {
    ToggleGroup filtro = new ToggleGroup();
    rbDAC.setToggleGroup(filtro);
    rbIEMS.setToggleGroup(filtro);
    rbAuriculares.setToggleGroup(filtro);
    rbDisco.setToggleGroup(filtro);
    rbTWS.setToggleGroup(filtro);
    rbTocaDisco.setToggleGroup(filtro);
    cbFiltroOrdenar.getItems().addAll("A-Z", "Z-A", "Menor Precio", "Mayor Precio");
    for (int i = 0; i < 20; i++) {
      addGrig();
    }
  }

  public void cargarProducto() {
    // TODO: Configrmar como se van a agregar los productos
  }

  private void addGrig() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_PRODUCTOS_VIEW));
      VBox tarjeta = loader.load();
      ProductController controller = loader.getController();
      controller.setProducto("Kz castor bass", 5000000, "llksjlfkjsld");
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
