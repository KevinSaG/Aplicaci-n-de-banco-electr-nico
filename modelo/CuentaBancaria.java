/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author caice
 */
public class CuentaBancaria {
    //Atributos
    private String n_cuenta;
    private float saldo;
    private char tipo_cuenta;
    private String ced_cliente;
    
    //Get y Set

    public String getN_cuenta() {
        return n_cuenta;
    }

    public void setN_cuenta(String n_cuenta) {
        this.n_cuenta = n_cuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public char getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(char tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public String getCed_cliente() {
        return ced_cliente;
    }

    public void setCed_cliente(String ced_cliente) {
        this.ced_cliente = ced_cliente;
    }
    
}
