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
  private Button btnBuscar;

  @FXML
  private Button btnCancelarBusqueda;
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
  private RadioButton rbTodos;

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
    App.app.setScene(Paths.GESTIONAR_CARRITO_VIEW);
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
    // filtro
    ToggleGroup filtro = new ToggleGroup();
    rbDAC.setToggleGroup(filtro);
    rbIEMS.setToggleGroup(filtro);
    rbAuriculares.setToggleGroup(filtro);
    rbDisco.setToggleGroup(filtro);
    rbTWS.setToggleGroup(filtro);
    rbTocaDisco.setToggleGroup(filtro);
    rbTodos.setToggleGroup(filtro);
    cbFiltroOrdenar.getItems().addAll("A-Z", "Z-A", "Menor Precio", "Mayor Precio");
    cbFiltroOrdenar.setValue("A-Z");

    // listeners
    filtro.selectedToggleProperty().addListener((observable, oldValue, newValue) -> actualizarCatalogo());
    cbFiltroOrdenar.valueProperty().addListener((observable, oldValue, newValue) -> actualizarCatalogo());
    buscador.textProperty().addListener((observable, oldValue, newValue) -> buscarProductos(newValue));
    txtPrecioMax.setOnAction(cambio -> actualizarCatalogo());
    txtPrecioMin.setOnAction(cambio -> actualizarCatalogo());
    actualizarCatalogo();
    rbTodos.setVisible(false);
  }

  @FXML
  void buscar(ActionEvent event) {
    buscarProductos(buscador.getText());
    btnCancelarBusqueda.setVisible(true);
    btnBuscar.setVisible(false);
  }

  @FXML
  void cancelarBusqueda(ActionEvent event) {
    btnBuscar.setVisible(true);
    btnCancelarBusqueda.setVisible(false);
    buscador.setText("");
    tlObjetos.getChildren().clear();
    Nodo<Productos> actual = catalogoDefault.cabecera;
    do {
      addProductsToGrig(actual.info);
      actual = actual.sig;
    } while (actual != catalogoDefault.cabecera);
  }

  public void cargarProducto() {
    // TODO: Configrmar como se van a agregar los productos
  }

  public void actualizarCatalogo() {
    rbTodos.setVisible(true);
    tlObjetos.getChildren().clear(); // Se limpia el grid

    String categoria = ""; // se asigna categoria
    if (rbIEMS.isSelected())
      categoria = "IEMs";
    else if (rbAuriculares.isSelected())
      categoria = "Auriculares";
    else if (rbTWS.isSelected())
      categoria = "TWS";
    else if (rbDAC.isSelected())
      categoria = "DACs";
    else if (rbDisco.isSelected())
      categoria = "Vinilos";
    else if (rbTocaDisco.isSelected())
      categoria = "Toca discos";
    float min = -1, max = -1; // se asignan valores default para el precio min y max
    try {
      if (!txtPrecioMin.getText().isEmpty())
        min = Float.parseFloat(txtPrecioMin.getText());
      if (!txtPrecioMax.getText().isEmpty())
        max = Float.parseFloat(txtPrecioMax.getText());
    } catch (NumberFormatException e) {
      System.out.println("Formato de precio inválido");
    }

    String orden = cbFiltroOrdenar.getValue(); // se asigna el orden (Default: A-Z)

    // Filtramos la lista
    ProductoLista listaFiltrada = catalogoDefault.getListaFiltrada(min, max, categoria, orden);

    if (!listaFiltrada.getEsVacia()) {
      Nodo<Productos> actual = listaFiltrada.cabecera;
      do {
        addProductsToGrig(actual.info);
        actual = actual.sig;
      } while (actual != listaFiltrada.cabecera);
    } else {
      Label vacio = new Label("No hay resultados con estos filtros.");
      tlObjetos.getChildren().add(vacio);
    }

  }

  private void addProductsToGrig(Productos producto) { // metodo para cargar las targetas al grid
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_PRODUCTOS_VIEW)); // se crea el producto
                                                                                                  // y el controller
      VBox tarjeta = loader.load();
      ProductController controller = loader.getController();
      controller.setProducto(producto.getNombre(), producto.getPrecio(), producto.getImagen()); // se le pasa el
                                                                                                // producto
      tlObjetos.getChildren().add(tarjeta); // se asigna a la grid
    } catch (IOException e) {
      InputDialog.error("error", "error: " + e.getMessage());
    }
  }

  private void buscarProductos(String dato) {
    if (tlObjetos.getChildren().isEmpty()) {
      btnCancelarBusqueda.setVisible(true);
      btnBuscar.setVisible(false);
      return;
    }
    if (dato.length() < 3 || dato == null) { // busquedas de 3 carateres o mas
      return;
    }
    ProductoLista lista = catalogoDefault.getBusqueda(dato);
    if (lista.getEsVacia()) {
      return;
    }
    btnCancelarBusqueda.setVisible(true);
    btnBuscar.setVisible(false);
    tlObjetos.getChildren().clear();
    Nodo<Productos> actual = lista.cabecera;
    do {
      addProductsToGrig(actual.info);
      actual = actual.sig;
    } while (actual != lista.cabecera);
  }
}
