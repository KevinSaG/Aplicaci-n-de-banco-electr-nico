/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author caice
 */
public class Conexion {
    private static final String BASE = "BancoEPN";
    private static final String USER = "postgres";
    private static final String PASSWORD = "*****";
    private static final String URL = "jdbc:postgresql://localhost:5432/"+BASE;
    private static Connection CONEXION;    
    public static String ERROR = "";

    public Conexion() {
        CONEXION = null;
    }

    public static Connection conecta() {
        try {
            CONEXION = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            ERROR = "No se pudo establecer la conexión con la Base de Datos";
            JOptionPane.showMessageDialog(null, ERROR);
        }
        return CONEXION;
    }

    public void desconectar() {
        try {
            CONEXION.close();
            System.out.println("Desconexión exitosa");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
