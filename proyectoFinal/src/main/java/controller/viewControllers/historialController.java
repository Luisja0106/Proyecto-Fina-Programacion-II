package controller.viewControllers;

import java.io.IOException;

import application.App;
import controller.HistorialLista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Compra;
import model.Nodo;
import utils.Paths;

public class historialController {

  @FXML
  private VBox prodcutos;

  @FXML
  void goToHome(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CATALOGO_VIEW);
  }

  @FXML
  void goToUser(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_PERFIL_VIEW);
  }

    private void cargarHistorialUsuario() {
        prodcutos.getChildren().clear();

        HistorialLista historialManager = new HistorialLista();

        if (historialManager.getEsVacia()) {
            return;
        }

        Nodo<Compra> actual = historialManager.cabecera;
        do {
            crearTarjetaCompra(actual.info);
            actual = actual.sig;
        } while (actual != historialManager.cabecera);
    }

    private void crearTarjetaCompra(Compra compra) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_HISTORIALPRODU_VIEW));
            HBox tarjeta = loader.load();
            historialProductoController controller = loader.getController();
            controller.setDatos(compra);

            prodcutos.getChildren().add(tarjeta);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la tarjeta de historial: " + e.getMessage());
        }
    }

  public void initialize() {
        cargarHistorialUsuario();
    }
}
