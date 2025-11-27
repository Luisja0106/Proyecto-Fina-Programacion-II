package controller.login;

import controller.Listas;
import model.Nodo;
import model.Usuarios;
import utils.InputDialog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListaUsers {
  private Listas<Usuarios> users = new Listas();

  public ListaUsers() {
  }

  public boolean crearCuenta(String user, String password, String correo) {
    if (!verificacionesCrear(user, password, correo)) { // verificiaciones de seguridad
      return false;
    }
    Usuarios nuevo = crearUser(user, password, correo);// se crea el usuario con su respectivo txt
    if (nuevo == null) {
      return false;
    }
    if (!crearTxt(nuevo)) {
      InputDialog.error("ha ocurrido un error", "Ha ocurrido un error");
      return false;
    }
    users.addF(nuevo);
    InputDialog.information("Cuenta creada", "La cuenta se ha creado correctamente");
    return true;
  }

  public boolean importarCuenta(Usuarios user) {
    return users.addF(user);
  }

  private Usuarios crearUser(String user, String password, String correo) {
    Usuarios nuevo = new Usuarios(user, false, correo, password);
    return nuevo;
  }

  private boolean crearTxt(Usuarios user) {
    // Se crea el Json
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String resu = gson.toJson(user);
      if (resu.isEmpty() || resu.isBlank() || resu == null) {
        return false;
      }
      // se crea la carpeta
      Path directorio = Paths.get("../DataBase");
      if (!Files.exists(directorio)) {
        Files.createDirectory(directorio);
      }
      String nomTXT = "Cuenta_" + user.getCorreo() + ".txt";
      Path file = directorio.resolve(nomTXT);
      if (!Files.exists(file)) {
        Files.writeString(file, resu);
      }
      return true;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  private boolean verificacionesCrear(String user, String password, String correo) {
    // si la contraseña tiene menos de 7 caracteres
    if (password.length() <= 7) {
      InputDialog.warning("Error", "Error la contraseña, es demasiado corta");
      return false;
    }
    // verificar que tenga un upper, un lower case y un numero
    char[] pass = password.toCharArray();
    int upper = 0;
    int lower = 0;
    int number = 0;
    for (char x : pass) {
      if (Character.isUpperCase(x)) {
        upper++;
        continue;
      }
      if (Character.isDigit(x)) {
        number++;
        continue;
      }
      if (Character.isLowerCase(x)) {
        lower++;
        continue;
      }
    }
    if (upper <= 0 || lower <= 0 || number <= 0) {
      InputDialog.warning("Contaseña debil", "Su contraseña es demasiado debil, favor revisar");
      return false;
    }
    // si el correo o nombres de usuario ya estan registrados
    if (!users.getEsVacia()) {
      Nodo<Usuarios> aux = users.cabecera;
      do {
        if (aux.info.getCorreo().equals(correo)) {
          InputDialog.warning("Correo ya registrado", "El correo ingresado ya fue registrado");
          return false;
        }
        aux = aux.sig;
      } while (aux != users.cabecera);
    }
    return true;
  }
}
