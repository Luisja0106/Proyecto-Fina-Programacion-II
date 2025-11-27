package controller.login;

//librerias
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import com.google.gson.Gson;

//archivos locales
import model.Usuarios;
import utils.InputDialog;

public class Login implements LoginInterface {

  private ListaUsers lista = new ListaUsers();

  @Override
  public boolean login(String username, String password, String correo) {
    if (!verificaciones()) {
      return false;
    }
    return true;
  }

  // metodo publico que carga los usuarios desde una carpeta usando txt
  public void dataBase() {
    Path directorio = Paths.get("../DataBase");
    if (!Files.exists(directorio)) {
      return;
    }
    try (Stream<Path> files = Files.list(directorio)) {
      for (Path p : files.toList()) {
        if (cargarUsers(p) == null) {
          InputDialog.information("ha ocurrido un error", "ha ocurrido un error al cargar la db");
          break;
        }
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // metodo que crea Usuarios usando txt
  private Usuarios cargarUsers(Path ruta) {
    StringBuilder content = new StringBuilder(); // objeto que armara todo el json en local
    try (BufferedReader br = Files.newBufferedReader(ruta)) { // lector de archivos
      String line; // variable que leera todas las lineas
      while ((line = br.readLine()) != null) {
        content.append(line);
      }
      Gson gson = new Gson();
      String json = content.toString();
      Usuarios aux = gson.fromJson(json, Usuarios.class);
      lista.importarCuenta(aux);
      InputDialog.information("DB cargada", "Data base Cargada");
      return aux;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  // metodo que verfica los datos
  private boolean verificaciones() {
    // TODO: hacer el metodo que verfique que la cuenta exista y se loguee, usando
    // while y esas cosas
    return false;
  }
}
