package model;

public class Admin {
    private String nombre;
    private boolean isAdmin;
    private String correo;
    private String password;

    //metodo constructor
    public Admin(String nombre, boolean isAdmin, String correo, String password) {
        this.nombre = nombre;
        this.isAdmin = isAdmin;
        this.correo = correo;
        this.password = password;
    }

}