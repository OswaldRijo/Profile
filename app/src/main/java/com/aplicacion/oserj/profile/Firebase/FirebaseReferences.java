package com.aplicacion.oserj.profile.Firebase;

/**
 * Created by Oserj on 28/03/2017.
 */

public class FirebaseReferences {
    final public static String CARROS = "CARROS";
    final public static String COORDENADAS = "coordenadas";
    final public static String USUARIOID = "usuarioid";
    final public static String IP = "IP";
    final public static String USUARIO = "AJ45";

    public static String usuario;
    public static String apellido;
    public static String ci;
    public static String correo;
    public static String nombre;


    public FirebaseReferences() {


    }



    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        FirebaseReferences.usuario = usuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        FirebaseReferences.apellido = apellido;
    }

    public  String getCi() {
        return ci;
    }

    public  void setCi(String ci) {
        FirebaseReferences.ci = ci;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        FirebaseReferences.correo = correo;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        FirebaseReferences.nombre = nombre;
    }
}
