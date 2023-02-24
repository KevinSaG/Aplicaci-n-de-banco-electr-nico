/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author caice
 */
public class Transaccion {
    //Atributos
    private String id_trans;
    private float valor_trans;
    private String n_cuenta;
    
    //Get y Set

    public String getId_trans() {
        return id_trans;
    }

    public void setId_trans(String id_trans) {
        this.id_trans = id_trans;
    }

    public float getValor_trans() {
        return valor_trans;
    }

    public void setValor_trans(float valor_trans) {
        this.valor_trans = valor_trans;
    }

    public String getN_cuenta() {
        return n_cuenta;
    }

    public void setN_cuenta(String n_cuenta) {
        this.n_cuenta = n_cuenta;
    }
    
}
