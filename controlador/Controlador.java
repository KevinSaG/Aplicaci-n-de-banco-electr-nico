/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import modelo.ConsultasCliente;
import org.apache.commons.codec.digest.DigestUtils;
import vista.Credenciales;
import vista.Identidad;
import vista.Login;
import vista.Registro;
import vista.Seguridad;

/**
 *
 * @author kvnsg
 */
public class Controlador implements MouseListener {

    //Modelo
    private ConsultasCliente conC;

    //Vistas
    private Registro registro;
    private Login lg;

    private Seguridad seg = new Seguridad();
    private Identidad identidad = new Identidad();
    private Credenciales cred = new Credenciales();

    public Controlador(Login lg, Registro reg, ConsultasCliente conC) {
        this.lg = lg;
        this.registro = reg;
        this.seg = seg;
        this.conC = conC;

        this.identidad.btnISg.addMouseListener(this);
        this.cred.btnCSg.addMouseListener(this);
        this.seg.btnSFinalizar.addMouseListener(this);

        this.showPanel(this.identidad);
        this.showPass(this.lg.psfPass, 0);
        this.showPass(this.cred.psCPass, 0);
        this.showPass(this.cred.psCCPass, 0);
        //this.showPass(this.identidad.txtCed, 0);

        this.lg.txtUser.addMouseListener(this);
        this.lg.psfPass.addMouseListener(this);
        this.lg.lblMostrar.addMouseListener(this);
        this.lg.lblAbrir.addMouseListener(this);
        this.lg.btnIngresar.addMouseListener(this);
        this.identidad.txtCed.addMouseListener(this);
        this.identidad.txtClaveTemp.addMouseListener(this);

        this.cred.txtCUser.addMouseListener(this);
        this.cred.psCPass.addMouseListener(this);
        this.cred.psCCPass.addMouseListener(this);
        this.cred.btnCMostrar1.addMouseListener(this);
        this.cred.btnCMostrar2.addMouseListener(this);

        this.identidad.btnICancelar.addMouseListener(this);
        this.cred.btnCCancelar.addMouseListener(this);
        this.seg.btnSCancelar.addMouseListener(this);

        this.identidad.btnIBack.addMouseListener(this);
        this.cred.btnCBack.addMouseListener(this);
        this.seg.btnSBack.addMouseListener(this);
    }

    public void iniciar() {
        lg.setTitle("Banco Ecuatoriano EPN");
        lg.setLocationRelativeTo(null);
        registro.setTitle("Registrar");
        registro.setLocationRelativeTo(null);
    }

    public void showPanel(JPanel p) {
        p.setSize(400, 580);
        p.setLocation(0, 0);
        registro.pnlContent.removeAll();
        registro.pnlContent.add(p, BorderLayout.CENTER);
        registro.pnlContent.revalidate();
        registro.pnlContent.repaint();
    }

    public void showFrame(JFrame a, JFrame b) {
        a.setVisible(false);
        b.setVisible(true);
    }

    public void showPass(JPasswordField p, int i) {
        p.setEchoChar((char) i);
    }

    public void switchTextUser(JTextField t, JPasswordField p) {
        if (t.getText().equals("Usuario")) {
            t.setText("");
            t.setForeground(Color.black);
        }
        if (String.valueOf(p.getPassword()).isEmpty()) {
            this.showPass(p, 0);
            p.setText("Contraseña");
            p.setForeground(Color.gray);
        }

    }

    public void switchTextPass(JTextField t, JPasswordField p) {
        if (String.valueOf(p.getPassword()).equals("Contraseña")) {
            p.setText("");
            this.showPass(p, 42);
            p.setForeground(Color.black);
        }
        if (t.getText().isEmpty()) {
            t.setText("Usuario");
            t.setForeground(Color.gray);
        }

    }
//switchTextUser de Identidad

    public void switchTextUserI(JTextField t, JTextField p) {
        if (t.getText().equals("Cédula de identidad")) {
            t.setText("");
            t.setForeground(Color.black);
        }
        if (p.getText().isEmpty()) {
            p.setText("Clave");
            p.setForeground(Color.gray);
        }

    }

    public void switchTextPassI(JTextField t, JTextField p) {
        if (p.getText().equals("Clave")) {
            p.setText("");
            p.setForeground(Color.black);
        }
        if (t.getText().isEmpty()) {
            t.setText("Cédula de identidad");
            t.setForeground(Color.gray);
        }

    }

    //switchTextUser de Credenciales
    public void switchTextUserC(JTextField t) {
        if (t.getText().equals("Usuario")) {
            t.setText("");
            t.setForeground(Color.black);
        }
    }

    public void switchTextPassC(JPasswordField t, JPasswordField p) {
        if (String.valueOf(t.getPassword()).equals("Contraseña")) {
            t.setText("");
            this.showPass(t, 42);
            t.setForeground(Color.black);
        }
        if (String.valueOf(p.getPassword()).isEmpty()) {
            this.showPass(p, 0);
            p.setText("Confirmar contraseña");
            p.setForeground(Color.gray);
        }

    }

    public void switchTextPassCC(JPasswordField t, JPasswordField p) {
        if (String.valueOf(p.getPassword()).equals("Confirmar contraseña")) {
            p.setText("");
            this.showPass(p, 42);
            p.setForeground(Color.black);
        }
        if (String.valueOf(t.getPassword()).isEmpty()) {
            this.showPass(t, 0);
            t.setText("Contraseña");
            t.setForeground(Color.gray);
        }

    }

    public void vaciarRegistro(JPasswordField p1, JPasswordField p2, JCheckBox cb1, JCheckBox cb2) {
        //Identidad
        this.identidad.txtCed.setForeground(Color.gray);
        this.identidad.txtCed.setText("Cédula de identidad");

        this.identidad.txtClaveTemp.setForeground(Color.gray);
        this.identidad.txtClaveTemp.setText("Clave");

        //Credenciales
        this.cred.txtCUser.setForeground(Color.gray);
        this.cred.txtCUser.setText("Usuario");

        this.showPass(p1, 0);
        this.cred.psCPass.setForeground(Color.gray);
        this.cred.psCPass.setText("Contraseña");

        this.showPass(p2, 0);
        this.cred.psCCPass.setForeground(Color.gray);
        this.cred.psCCPass.setText("Confirmar contraseña");

        //Seguridad
        this.seg.cbCorreo.setSelected(false);
        this.seg.cbTelf.setSelected(false);
    }

    public void vaciarLogin(JPasswordField p) {

        this.lg.txtUser.setForeground(Color.gray);
        this.lg.txtUser.setText("Usuario");

        this.showPass(p, 0);
        this.lg.psfPass.setForeground(Color.gray);
        this.lg.psfPass.setText("Contraseña");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.lg.lblAbrir) {
            showFrame(this.lg, this.registro);
            showPanel(this.identidad);
        }

        if (e.getSource() == this.cred.btnCBack) {
            this.showPanel(this.identidad);
        }

        if (e.getSource() == this.seg.btnSBack) {
            this.showPanel(this.cred);
        }

        if (e.getSource() == this.identidad.btnISg) {
            try {
                if (!validarCedula(this.identidad.txtCed.getText())) {
                    JOptionPane.showMessageDialog(null, "Cédula inválida");
                }

                if (!this.conC.verificarExistencia(this.identidad.txtCed.getText())) {
                    JOptionPane.showMessageDialog(null, "No existe cuenta asociada al usuario");
                } else {
                    if (!this.identidad.txtClaveTemp.getText().equals(conC.getPassTemp(this.identidad.txtCed.getText()))) {
                        JOptionPane.showMessageDialog(null, "Clave temporal incorrecta");
                    } else {
                        JOptionPane.showMessageDialog(null, "Verificación correcta");
                        showPanel(cred);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException ext) {
                JOptionPane.showMessageDialog(null, "Insertar solo números en la cédula de identidad");

            }
        }

        if (e.getSource()
                == this.cred.btnCSg) {
            try {
                int i = 4;
                if (!validarUsuario(this.cred.txtCUser.getText())) {
                    JOptionPane.showMessageDialog(null, "Usuario inválido\nEl usuario debe tener:\nLongtitud: entre 8 y 12 caracteres"
                            + "\nContener almenos una minúscula\nContener almenos una mayúscula\nContener almenos un número");
                    i--;
                }
                if (this.conC.verificarExistenciaUser(this.cred.txtCUser.getText())) {
                    JOptionPane.showMessageDialog(null, "Error, El usuario ingresado ya existe");
                    i--;

                }
                if (!validarContrasenia(String.valueOf(this.cred.psCPass.getPassword()))) {

                    JOptionPane.showMessageDialog(null, "Contraseña inválida\nLa contraseña debe tener:\nLongtitud mínima de 8 caracteres"
                            + "\nContener almenos una minúscula\nContener almenos una mayúscula\nContener almenos un número\nContener almenos un caracter especial (@, $, !, %, *, #, ?, &)");
                    i--;
                }

                String pas1 = String.valueOf(this.cred.psCPass.getPassword());
                String pas2 = String.valueOf(this.cred.psCCPass.getPassword());
                if (!pas1.equals(pas2)) {
                    JOptionPane.showMessageDialog(null, "Error, Las contraseñas no coinciden");
                    i--;
                }

                if (i == 4) {
                    JOptionPane.showMessageDialog(null, "Datos válidos");
                    showPanel(seg);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource()
                == this.seg.btnSFinalizar) {
            String op = null;//Correo = A, Telefono=B, Ambos =C
            if (this.seg.cbCorreo.isSelected() && this.seg.cbTelf.isSelected()) {
                op = "C";
            } else {
                if (this.seg.cbCorreo.isSelected()) {
                    op = "A";
                } else {
                    if (this.seg.cbTelf.isSelected()) {
                        op = "B";
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione una o mas opciones");
                    }
                }

            }
            if (op != null) {
                String claveh = encriptarClave(String.valueOf(this.cred.psCPass.getPassword()));
                this.conC.modificarClt(this.identidad.txtCed.getText(), this.cred.txtCUser.getText(), claveh, op);
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
                this.showFrame(this.registro, this.lg);
                this.vaciarRegistro(this.cred.psCPass, this.cred.psCCPass, this.seg.cbCorreo, this.seg.cbTelf);
            }

        }

        if (e.getSource()
                == this.lg.btnIngresar) {
            int i = 2;
            String user = this.lg.txtUser.getText();
            String pass = String.valueOf(this.lg.psfPass.getPassword());
            String correo = "" + this.conC.getLogin(user)[2];
            //System.out.println("asdf: "+!user.equals(this.conC.getLogin(user)[0]));
            if (!user.equals(this.conC.getLogin(user)[0])) {
                i--;
            }
            if (!encriptarClave(pass).equals(this.conC.getLogin(user)[1])) {
                i--;
            }
            if (i == 2) {
                System.out.println(correo);
                enviar(correo);
                JOptionPane.showMessageDialog(null, "Ingreso exitoso");
                vaciarLogin(this.lg.psfPass);

            } else {
                JOptionPane.showMessageDialog(null, "Ingreso fallido");
            }
        }

        if (e.getSource()
                == this.identidad.btnICancelar || e.getSource() == this.cred.btnCCancelar || e.getSource() == this.seg.btnSCancelar
                || e.getSource() == this.identidad.btnIBack) {
            this.showFrame(this.registro, this.lg);
            this.vaciarRegistro(this.cred.psCPass, this.cred.psCCPass, this.seg.cbCorreo, this.seg.cbTelf);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == this.lg.txtUser) {
            this.switchTextUser(this.lg.txtUser, this.lg.psfPass);
        }

        if (e.getSource() == this.lg.psfPass) {
            this.switchTextPass(this.lg.txtUser, this.lg.psfPass);
        }

        if (e.getSource() == this.lg.lblMostrar) {
            this.showPass(this.lg.psfPass, 0);
        }

        if (e.getSource() == this.identidad.txtCed) {
            this.switchTextUserI(this.identidad.txtCed, this.identidad.txtClaveTemp);
        }

        if (e.getSource() == this.identidad.txtClaveTemp) {
            this.switchTextPassI(this.identidad.txtCed, this.identidad.txtClaveTemp);
        }

        if (e.getSource() == this.cred.txtCUser) {
            this.switchTextUserC(this.cred.txtCUser);
        }

        if (e.getSource() == this.cred.psCPass) {
            this.switchTextPassC(this.cred.psCPass, this.cred.psCCPass);
        }

        if (e.getSource() == this.cred.psCCPass) {
            this.switchTextPassCC(this.cred.psCPass, this.cred.psCCPass);
        }

        if (e.getSource() == this.cred.btnCMostrar1) {
            this.showPass(this.cred.psCPass, 0);
        }

        if (e.getSource() == this.cred.btnCMostrar2) {
            this.showPass(this.cred.psCCPass, 0);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == this.lg.lblMostrar) {
            this.showPass(this.lg.psfPass, 42);
            if (String.valueOf(this.lg.psfPass.getPassword()).equals("Contraseña")) {
                this.showPass(this.lg.psfPass, 0);
            }
        }

        if (e.getSource() == this.cred.btnCMostrar1) {
            this.showPass(this.cred.psCPass, 42);
            if (String.valueOf(this.cred.psCPass.getPassword()).equals("Contraseña")) {
                this.showPass(this.cred.psCPass, 0);
            }
        }

        if (e.getSource() == this.cred.btnCMostrar2) {
            this.showPass(this.cred.psCCPass, 42);
            if (String.valueOf(this.cred.psCCPass.getPassword()).equals("Confirmar contraseña")) {
                this.showPass(this.cred.psCCPass, 0);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public boolean validarCedula(String Cedula) {

        long ultimodigito = Long.parseLong(Cedula) % 10;
        long aux9 = ((Long.parseLong(Cedula) / 10) % 10) * 2,
                aux8 = (Long.parseLong(Cedula) / 100) % 10,
                aux7 = ((Long.parseLong(Cedula) / 1000) % 10) * 2,
                aux6 = (Long.parseLong(Cedula) / 10000) % 10,
                aux5 = ((Long.parseLong(Cedula) / 100000) % 10) * 2,
                aux4 = (Long.parseLong(Cedula) / 1000000) % 10,
                aux3 = ((Long.parseLong(Cedula) / 10000000) % 10) * 2,
                aux2 = (Long.parseLong(Cedula) / 100000000) % 10,
                aux1 = ((Long.parseLong(Cedula) / 1000000000) % 10) * 2,
                auxa = 0,
                auxb = 0,
                auxc = 0,
                auxd = 0,
                auxe = 0,
                sumatotal = 0,
                resultado = 0,
                sumapares = 0,
                sumaimpares = 0;

        sumapares = aux2 + aux4 + aux6 + aux8;
        if (aux1 > 9) {
            auxa = aux1 - 9;
        } else {
            auxa = aux1;
        }
        if (aux3 > 9) {
            auxb = aux3 - 9;
        } else {
            auxb = aux3;
        }
        if (aux5 > 9) {
            auxc = aux5 - 9;
        } else {
            auxc = aux5;
        }
        if (aux7 > 9) {
            auxd = aux7 - 9;
        } else {
            auxd = aux7;
        }
        if (aux9 > 9) {
            auxe = aux9 - 9;
        } else {
            auxe = aux9;
        }
        sumaimpares = auxa + auxb + auxc + auxd + auxe;
        sumatotal = sumapares + sumaimpares;
        if (sumatotal > 0 && sumatotal <= 10) {
            resultado = 10 - sumatotal;
        }
        if (sumatotal > 10 && sumatotal <= 20) {
            resultado = 20 - sumatotal;
        }
        if (sumatotal > 20 && sumatotal <= 30) {
            resultado = 30 - sumatotal;
        }
        if (sumatotal > 30 && sumatotal <= 40) {
            resultado = 40 - sumatotal;
        }
        if (sumatotal > 40 && sumatotal <= 50) {
            resultado = 50 - sumatotal;
        }
        if (sumatotal > 50 && sumatotal <= 60) {
            resultado = 60 - sumatotal;
        }
        if (sumatotal > 60 && sumatotal <= 70) {
            resultado = 70 - sumatotal;
        }
        if (sumatotal > 70 && sumatotal <= 80) {
            resultado = 80 - sumatotal;
        }
        if (sumatotal > 80 && sumatotal <= 90) {
            resultado = 90 - sumatotal;
        }
        if (sumatotal > 90 && sumatotal <= 100) {
            resultado = 100 - sumatotal;
        }
        return (ultimodigito == resultado);
    }

    public boolean validarUsuario(String nombre) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,12}$");
        Matcher matcher = pattern.matcher(nombre);
        if (matcher.find() == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarContrasenia(String contra) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z[0-9]@$!%*#?&]{8,}$");
        Matcher matcher = pattern.matcher(contra);
        if (matcher.find() == true) {
            return true;
        } else {
            return false;
        }
    }

    public String encriptarClave(String clave_u) {
        String clave_h = DigestUtils.sha256Hex(clave_u);
        return clave_h;
    }

    public void enviar(String destinatario) {
        String asunto = "Ingreso a cuenta movil";
        String contenido = "Se ha ingresado a su cuenta";
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        String from = "banco.epn.2022";
        String pass = "Grupo1234";

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session sesion = Session.getDefaultInstance(props);
        MimeMessage mail = new MimeMessage(sesion);

        try {
            mail.setFrom(new InternetAddress(from));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            mail.setText(contenido);

            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(host, from, pass);
            transportar.sendMessage(mail, mail.getAllRecipients());
            transportar.close();

        } catch (AddressException ex) {
            JOptionPane.showMessageDialog(null, "Error 1");
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, "Error 2");
        }

    }

}
