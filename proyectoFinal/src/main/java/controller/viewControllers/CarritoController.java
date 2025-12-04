package controller.viewControllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import application.App;
import controller.HistorialLista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import model.Compra;
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


  @FXML
  void pagar(ActionEvent event) {
      CarritoLista carrito = new CarritoLista();
      if (carrito.getEsVacia()) {
          InputDialog.error("Carrito Vacío", "No puedes pagar un carrito vacío.");
          return;
      }


      StringBuilder detallesBuilder = new StringBuilder();
      double subtotal = 0;
      int cantidadTotalProductos = 0;

      Nodo<Productos> actual = carrito.cabecera;
      do {
          Productos p = actual.info;
          int cant = p.getStock(); // Recordamos que usamos stock como cantidad
          double precioLinea = p.getPrecio() * cant;

          subtotal += precioLinea;
          cantidadTotalProductos += cant;

          // Construimos el string: "Nombre x Cantidad"
          detallesBuilder.append(p.getNombre()).append(" x ").append(cant).append("\n");

          actual = actual.sig;
      } while (actual != carrito.cabecera);


      double envio = (cantidadTotalProductos > 0) ? 15.0 : 0;
      double descuento = (cantidadTotalProductos > 3) ? subtotal * 0.10 : 0;
      double total = subtotal + envio - descuento;

      // se genera ID único y Fecha
      String idCompra = "#" + UUID.randomUUID().toString().substring(0, 8); // Ej: #a1b2c3d4
      String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));


      Compra nuevaCompra = new Compra(idCompra, fecha, subtotal, envio, descuento, total, detallesBuilder.toString());

      // se guarda en historial
      HistorialLista historial = new HistorialLista();
      historial.agregarCompra(nuevaCompra);

      // se vacia el carrito
      carrito.vaciarCarritoTotalmente();
      actualizarResumen(0, 0); // Limpia los labels visuales
      TlProdu.getChildren().clear(); // Limpia la vista de tarjetas

      InputDialog.information("Compra Exitosa", "Se ha generado el recibo " + idCompra);


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
