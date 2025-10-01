import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class VentanaRegistro extends JFrame {

    private JTextField txtIdUsuario, txtNombre, txtApellido, txtFechaNacimiento, txtBarrio, txtTelefono;
    private JPasswordField txtPassword;
    private JButton btnRegistrar, btnCancelar;

    public VentanaRegistro() {
        setTitle("Registro de Usuario");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // cierra solo esta ventana
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("ID Usuario:"));
        txtIdUsuario = new JTextField();
        panel.add(txtIdUsuario);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panel.add(txtApellido);

        JLabel lblFecha = new JLabel("Fecha de Nacimiento:");
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        panel.add(lblFecha);
        panel.add(dateChooser);

        panel.add(new JLabel("Barrio:"));
        txtBarrio = new JTextField();
        panel.add(txtBarrio);

        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);

        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        add(panel, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        // Acción Registrar
        btnRegistrar.addActionListener(e -> registrarUsuario());

        // Acción Cancelar
        btnCancelar.addActionListener(e -> dispose());
    }

    private JDateChooser dateChooser;
    private void registrarUsuario() {

        String id = txtIdUsuario.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        Date fechaNacimiento = dateChooser.getDate();
        String barrio = txtBarrio.getText();
        String telefono = txtTelefono.getText();
        String pass = new String(txtPassword.getPassword());

        if(id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                fechaNacimiento==null || barrio.isEmpty() || telefono.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor complete todos los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Por ahora solo mostramos los datos
        JOptionPane.showMessageDialog(this,
                "Usuario registrado:\n" +
                        "ID: " + id + "\n" +
                        "Nombre: " + nombre + " " + apellido + "\n" +
                        "Fecha Nac: " + fechaNacimiento+ "\n" +
                        "Barrio: " + barrio + "\n" +
                        "Teléfono: " + telefono,
                "Registro exitoso",
                JOptionPane.INFORMATION_MESSAGE);

        dispose(); // cerrar la ventana después de registrar
    }
}