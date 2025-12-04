package utils;

public class UserSession {

    private static UserSession instance;
    private String correoUsuario; // Aquí guardaremos el email

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correo) {
        this.correoUsuario = correo;
    }

    public void logout() {
        this.correoUsuario = null;
    }
}