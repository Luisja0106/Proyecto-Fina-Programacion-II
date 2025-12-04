package controller.viewControllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import model.Productos;
import utils.InputDialog;
import utils.Paths;

import model.Productos;
import controller.CarritoLista;
import model.Nodo;

public class CarritoController {
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblEnvio;
    @FXML
    private Label lblDescuento;
    @FXML
    private Label lblTotal;

    private final double COSTO_ENVIO_BASE = 15.0;

    @FXML
  private Button btnPagar;
  @FXML
  private TilePane TlProdu;

  @FXML
  void goToHome(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CATALOGO_VIEW);
  }

  // TODO: Metodo de pago
  @FXML
  void pagar(ActionEvent event) {

  }

    public void initialize() {
        btnPagar.setOnMouseEntered(e -> btnPagar.setText("P a g a r"));
        btnPagar.setOnMouseExited(e -> btnPagar.setText("Pagar"));

        cargarItemsDelCarrito();
    }

    private void cargarTarjetaProducto(Productos p) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_CARRITO_PRODUCTO_VIEW));
            HBox tarjeta = loader.load();
            ProductoCarritoController controller = loader.getController();
            String descripcionGenerada = p.getNomVendedor() + " - " + p.getCategoria();
            controller.setProductos(p);
            TlProdu.getChildren().add(tarjeta);
        } catch (IOException e) {
            e.printStackTrace();
            InputDialog.error("Error al cargar tarjeta", "No se pudo cargar el producto: " + p.getNombre());
        }
    }



    private void cargarItemsDelCarrito() {
        TlProdu.getChildren().clear();
        CarritoLista carrito = new CarritoLista();
        if (carrito.getEsVacia()) {
            return;
        }

        // Variables para el cálculo
        double subtotal = 0;
        int cantidadProductos = 0;

        if (!carrito.getEsVacia()) {
            Nodo<Productos> actual = carrito.cabecera;
            do {
                cargarTarjetaProducto(actual.info);
                int cantidad = actual.info.getStock();
                subtotal += actual.info.getPrecio() * cantidad;
                cantidadProductos += cantidad;
                actual = actual.sig;
            } while (actual != carrito.cabecera);
        }
        actualizarResumen(subtotal, cantidadProductos);
    }

    private void actualizarResumen(double subtotal, int cantidad) {
        double envio = (cantidad > 0) ? COSTO_ENVIO_BASE : 0; //
        double descuento = 0;
        double total = 0;


        if (cantidad > 3) {
            descuento = subtotal * 0.10;

            lblDescuento.setStyle("-fx-text-fill: #27ae60;"); // Verde
        } else {
            lblDescuento.setStyle("-fx-text-fill: black;"); // Color normal
        }

        total = subtotal + envio - descuento;

        lblSubtotal.setText(String.format("$ %.2f", subtotal));
        lblEnvio.setText(String.format("$ %.2f", envio));
        lblDescuento.setText(String.format("-$ %.2f", descuento));
        lblTotal.setText(String.format("$ %.2f", total));
    }

  private void addGrid(Productos produ) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_CARRITO_PRODUCTO_VIEW));
      HBox tarjeta = loader.load();
      ProductoCarritoController controller = loader.getController();
      /*
       * Note: aqui en el segundo paramatro deberia ir la info del del producto pero
       * creo
       * que la categoria tambien quedaria bien
       */
      controller.setProductos(produ);
      TlProdu.getChildren().add(tarjeta);
    } catch (IOException e) {
      e.printStackTrace();
      InputDialog.error("error", "error" + e.getMessage());
    }
  }

  @FXML
  void goToLogin(ActionEvent event) {
      controller.CarritoLista limpiador = new controller.CarritoLista();
      limpiador.vaciarCarritoTotalmente();
      App.app.setScene(Paths.GESTIONAR_LOGIN_VIEW);
  }

  @FXML
  void goToProfile(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_PERFIL_VIEW);
  }


    //  private void addGrid() {
//    try {
//      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_CARRITO_PRODUCTO_VIEW));
//      HBox tarjeta = loader.load();
//      ProductoCarritoController controller = loader.getController();
//      controller.setProductos("Kz castor bass", "Audifonos in-ear centrados en tener unos bajos profundos y definidos",
//          500000, 1, "/Imagenes/KZ-castor-bass.jpg");
//      TlProdu.getChildren().add(tarjeta);
//    } catch (IOException e) {
//      e.printStackTrace();
//      InputDialog.error("error", "error" + e.getMessage());
//    }
//  }

}
