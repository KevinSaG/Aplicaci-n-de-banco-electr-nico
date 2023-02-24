/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author caice
 */
public class TipoTransaccion {
    //Atributos
    private String id_trans;
    private String tipo_trans;
    private String descripcion;
    private float costo_opn;
    
    //Get y Set

    public String getId_trans() {
        return id_trans;
    }

    public void setId_trans(String id_trans) {
        this.id_trans = id_trans;
    }

    public String getTipo_trans() {
        return tipo_trans;
    }

    public void setTipo_trans(String tipo_trans) {
        this.tipo_trans = tipo_trans;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCosto_opn() {
        return costo_opn;
    }

    public void setCosto_opn(float costo_opn) {
        this.costo_opn = costo_opn;
    }
    
}
