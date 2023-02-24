/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author kvnsg
 */
public class ConsultasCliente {

    private static final String SELECCIONAR = "SELECT * FROM cliente";
    private static PreparedStatement ps;
    private static DefaultTableModel dt;
    private static ResultSet rs;
    private final Conexion CON;

    public ConsultasCliente() {
        this.ps = null;
        this.CON = new Conexion();
    }


    public boolean verificarExistencia(String cedula) throws SQLException {
        ps = CON.conecta().prepareStatement("SELECT * FROM cliente WHERE ced_cliente='" + cedula + "'");
        rs = ps.executeQuery();
        if (!rs.next()) {
            //ResultSet is empty
            return false;
        }

        return true;
    }

    public boolean verificarExistenciaUser(String user) throws SQLException {
        ps = CON.conecta().prepareStatement("SELECT * FROM cliente WHERE usuario='" + user + "'");
        rs = ps.executeQuery();
        if (!rs.next()) {
            //ResultSet is empty
            return false;
        }

        return true;
    }

    public String getPassTemp(String ced) {
        String SELECCIONAR = "SELECT clave_t FROM cliente WHERE ced_cliente = '" + ced + "'";

        Object[] fila = new Object[1];
        try {
            ps = CON.conecta().prepareStatement(SELECCIONAR);
            rs = ps.executeQuery();

            while (rs.next()) {

                fila[0] = rs.getString(1);
            }
        } catch (Exception e) {
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        String cedt = "" + fila[0];
        return cedt;
    }

    public String[] getLogin(String user) {
        String SELECCIONAR = "SELECT usuario,clave_h,correo_e FROM cliente WHERE usuario = '" + user + "'";

        Object[] fila = new Object[3];
        try {
            ps = CON.conecta().prepareStatement(SELECCIONAR);
            rs = ps.executeQuery();

            while (rs.next()) {

                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
            }
        } catch (Exception e) {
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        String[] cedt = {"" + fila[0], "" + fila[1], "" + fila[2]};
        return cedt;
    }

    public static void modificarClt(String ced, String user, String claveh, String op) {
        Connection cont = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            cont = Conexion.conecta();
            ps = cont.prepareStatement("UPDATE cliente SET opcion_ver='" + op + "'" + ",usuario='" + user + "'" + ",clave_h='" + claveh + "' WHERE ced_cliente='" + ced + "'");

            int res = ps.executeUpdate();

            if (res <= 0) {

                JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    }
