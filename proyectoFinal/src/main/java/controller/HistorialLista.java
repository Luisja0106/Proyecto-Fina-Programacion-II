package controller;

import java.io.*;
import java.nio.file.*;
import model.Compra;
import utils.UserSession; // Importamos la sesión

public class HistorialLista extends Listas<Compra> {

    // Carpeta donde se guardarán los historiales
    private final String CARPETA_DB = "DataBase" + File.separator + "Historial";
    private String rutaArchivoUsuario;

    public HistorialLista() {
        super();
        String email = UserSession.getInstance().getCorreoUsuario();
        String nombreTxt = "Historial_" + email + ".txt";
        this.rutaArchivoUsuario = CARPETA_DB + File.separator + nombreTxt;
        setCargarHistorial();
    }



    public void agregarCompra(Compra c) {
        addF(c);
        guardarEnTxt(c);
    }

    private void guardarEnTxt(Compra c) {
        File archivo = createFile();
        // Usamos 'true' para añadir al final sin borrar lo anterior (append)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(c.toCsv());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error guardando historial: " + e.getMessage());
        }
    }

    public void setCargarHistorial() {
        File archivo = createFile();
        if (!archivo.exists()) return;

        setVaciar(); // Limpiamos la lista en memoria antes de cargar
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                // Asegúrate que Compra.java tenga un constructor compatible
                if (datos.length >= 7) {
                    Compra c = new Compra(datos[0], datos[1],
                            Double.parseDouble(datos[2]),
                            Double.parseDouble(datos[3]),
                            Double.parseDouble(datos[4]),
                            Double.parseDouble(datos[5]),
                            datos[6]);
                    addF(c);
                }
            }
        } catch (Exception e) {
            System.err.println("Error cargando historial: " + e.getMessage());
        }
    }

    private File createFile() {
        try {
            Path rutaFolder = Paths.get(CARPETA_DB);
            if (!Files.exists(rutaFolder)) Files.createDirectories(rutaFolder);

            Path rutaArchivo = Paths.get(this.rutaArchivoUsuario); // Usamos la ruta dinámica
            if (!Files.exists(rutaArchivo)) Files.createFile(rutaArchivo);

            return rutaArchivo.toFile();
        } catch (IOException e) {
            return new File(this.rutaArchivoUsuario);
        }
    }
}