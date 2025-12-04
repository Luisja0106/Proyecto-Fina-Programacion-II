package controller;

import java.io.*;
import java.nio.file.*;
import model.Nodo;
import model.Productos;
import utils.UserSession;

public class WishLista extends Listas<Productos> {

    private final String CARPETA_DB = "DataBase" + File.separator + "Wishlist";
    private String rutaArchivoUsuario;

    public WishLista() {
        super();
        String email = UserSession.getInstance().getCorreoUsuario();

        if (email == null || email.isEmpty()) {
            email = "guest";
        }

        // ruta
        String nombreTxt = "Wishlist_" + email + ".txt";
        this.rutaArchivoUsuario = CARPETA_DB + File.separator + nombreTxt;

        setCargarWishList();
    }

    /**
     * Agrega un producto a la wishlist si no existe ya.
     */
    public boolean agregarAWishList(Productos p) {
        if (existeEnWishList(p.getId())) {
            return false; // Ya está en la lista
        }
        addF(p);
        guardarWishListTxt();
        return true;
    }

    /**
     * Elimina un producto por ID y actualiza el TXT
     */
    public boolean eliminarDeWishList(String id) {
        if (getEsVacia()) return false;

        Nodo<Productos> actual = cabecera;
        boolean encontrado = false;

        do {
            if (actual.info.getId().equals(id)) {
                encontrado = true;
                break;
            }
            actual = actual.sig;
        } while (actual != cabecera);

        if (!encontrado) return false;

        if (actual == cabecera && actual.sig == cabecera) {
            cabecera = null;
        } else {
            Nodo<Productos> anterior = actual.ant;
            Nodo<Productos> siguiente = actual.sig;
            anterior.sig = siguiente;
            siguiente.ant = anterior;
            if (actual == cabecera) cabecera = siguiente;
        }

        guardarWishListTxt();
        return true;
    }

    public boolean existeEnWishList(String id) {
        if (getEsVacia()) return false;
        Nodo<Productos> actual = cabecera;
        do {
            if (actual.info.getId().equals(id)) return true;
            actual = actual.sig;
        } while (actual != cabecera);
        return false;
    }


    private void guardarWishListTxt() {
        createFile(); // Asegura que carpeta y archivo existan

        if (getEsVacia()) {
            // Si está vacía, limpiamos el archivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.rutaArchivoUsuario))) {
                bw.write("");
            } catch (IOException e) { e.printStackTrace(); }
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.rutaArchivoUsuario))) {
            Nodo<Productos> actual = cabecera;
            do {
                bw.write(actual.info.toCsv());
                bw.newLine();
                actual = actual.sig;
            } while (actual != cabecera);
        } catch (IOException e) {
            System.err.println("Error guardando wishlist: " + e.getMessage());
        }
    }

    public void setCargarWishList() {
        File archivo = createFile();
        if (!archivo.exists()) return;

        setVaciar();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 6) {
                    // Normalización de rutas (como hicimos en ProductoLista)
                    String rutaNormalizada = datos[4].replace("/", File.separator).replace("\\", File.separator);

                    Productos p = new Productos(datos[0], datos[1], datos[2],
                            Float.parseFloat(datos[3]), rutaNormalizada,
                            Integer.parseInt(datos[5]), datos[6]);
                    addF(p);
                }
            }
        } catch (Exception e) {
            System.err.println("Error cargando wishlist: " + e.getMessage());
        }
    }

    private File createFile() {
        try {
            Path rutaFolder = Paths.get(CARPETA_DB);
            if (!Files.exists(rutaFolder)) Files.createDirectories(rutaFolder);

            Path rutaArchivo = Paths.get(this.rutaArchivoUsuario);
            if (!Files.exists(rutaArchivo)) Files.createFile(rutaArchivo);

            return rutaArchivo.toFile();
        } catch (IOException e) {
            return new File(this.rutaArchivoUsuario);
        }
    }
}