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
import model.Nodo;
import model.Usuarios;
import utils.InputDialog;

public class Login implements LoginInterface {

  private ListaUsers lista = new ListaUsers();

  public Login() {
    super();
  }

  public ListaUsers getLista() {
    return lista;
  }

  @Override
  public boolean login(String username, String password, String correo) {
    if (!verificaciones(username, correo, password)) {
      return false;
    }
    return true;
  }

  // metodo publico que carga los usuarios desde una carpeta usando txt
  public void dataBase() {
    Path directorio = Paths.get("../DataBase/Users");
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

    } catch (IOException e) {
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
      Gson gson = new Gson(); // lector de json
      String json = content.toString(); // se construye toda la info en una String
      Usuarios aux = gson.fromJson(json, Usuarios.class); // se crea un User
      lista.importarCuenta(aux); // se importa a la lista
      return aux;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  private Usuarios buscarCorreo(String correo) {
    Nodo<Usuarios> aux = lista.getUsers().cabecera;
    do {
      if (aux.info.getCorreo().equals(correo)) {
        return aux.info;
      }
      aux = aux.sig;
    } while (aux != lista.getUsers().cabecera);
    return null;
  }

  // metodo que verfica los datos
  private boolean verificaciones(String user, String correo, String password) {
    Usuarios aux = buscarCorreo(correo);
    if (aux == null) {
      InputDialog.warning("No se ha encontrado", "No se ha encontrado el correo ingresado");
      return false;
    }
    if (!aux.getNombre().equals(user)) {
      InputDialog.warning("error", "Error, el usuario ingresado no coincide con el usuario registrado a el correo");
      return false;
    }
    if (!aux.getPassword().equals(password)) {
      InputDialog.warning("Contraseña incorrecta", "Ha ingresado una contraseña incorrecta");
      return false;
    }
    return true;
  }
}
