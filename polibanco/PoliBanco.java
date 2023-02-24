/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package polibanco;

import controlador.Controlador;
import modelo.ConsultasCliente;
import vista.Identidad;
import vista.Login;
import vista.Registro;

/**
 *
 * @author kvnsg
 */
public class PoliBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConsultasCliente cCli=new ConsultasCliente();
        //Vistas
        Registro reg=new Registro();
        Login lg = new Login();
        Controlador ctrl = new Controlador(lg, reg, cCli);
        ctrl.iniciar();
        lg.setVisible(true);
    }
    
}
