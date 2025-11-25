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

    //getters
    public String getNombre() {
        return nombre;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public String getCorreo() {
        return correo;
    }
    public String getPassword() {
        return password;
    }

    //setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}