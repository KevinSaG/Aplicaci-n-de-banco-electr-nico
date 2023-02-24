/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author caice
 */
public class Cliente {
    //Atributos
    private String ced_cliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String usuario;
    private String clave_t;
    private String clave_h;
    private boolean bloqueo;
    
    //Get y Set

    public String getCed_cliente() {
        return ced_cliente;
    }

    public void setCed_cliente(String ced_cliente) {
        this.ced_cliente = ced_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave_t() {
        return clave_t;
    }

    public void setClave_t(String clave_t) {
        this.clave_t = clave_t;
    }

    public String getClave_h() {
        return clave_h;
    }

    public void setClave_h(String clave_h) {
        this.clave_h = clave_h;
    }

    public boolean isBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(boolean bloqueo) {
        this.bloqueo = bloqueo;
    }
    
    
}
