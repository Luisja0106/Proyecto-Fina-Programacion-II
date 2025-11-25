package model;

public class Usuarios extends Admin {
    private int tarjeta;
    private String direccion;

    //constructor
    public Usuarios(String nombre, boolean isAdmin, String correo, String password, int tarjeta, String direccion) {
        super(nombre, isAdmin, correo, password);
        this.tarjeta = tarjeta;
        this.direccion = direccion;
    }
}