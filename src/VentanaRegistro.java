import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class VentanaRegistro extends JFrame {

    private JTextField txtIdUsuario, txtNombre, txtApellido, txtBarrio, txtTelefono;
    private JPasswordField txtPassword;
    private JButton btnRegistrar, btnCancelar;
    private JDateChooser dateChooser;

    public VentanaRegistro() {
        setTitle("Registro de Usuario");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ----------- LOGO ARRIBA -----------
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("imagenes/logoapp.png"));
        Image img = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);
        add(logoLabel, BorderLayout.NORTH);

        // ----------- PANEL DE REGISTRO -----------
        JPanel registroPanel = new JPanel(new GridBagLayout());
        registroPanel.setBorder(BorderFactory.createTitledBorder("Registrar nuevo usuario"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        registroPanel.add(new JLabel("ID Usuario:"), gbc);
        txtIdUsuario = new JTextField(15);
        gbc.gridx = 1;
        registroPanel.add(txtIdUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        registroPanel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(15);
        gbc.gridx = 1;
        registroPanel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        registroPanel.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField(15);
        gbc.gridx = 1;
        registroPanel.add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        registroPanel.add(new JLabel("Fecha de Nacimiento:"), gbc);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        gbc.gridx = 1;
        registroPanel.add(dateChooser, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        registroPanel.add(new JLabel("Barrio:"), gbc);
        txtBarrio = new JTextField(15);
        gbc.gridx = 1;
        registroPanel.add(txtBarrio, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        registroPanel.add(new JLabel("Teléfono:"), gbc);
        txtTelefono = new JTextField(15);
        gbc.gridx = 1;
        registroPanel.add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        registroPanel.add(new JLabel("Contraseña:"), gbc);
        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        registroPanel.add(txtPassword, gbc);

        add(registroPanel, BorderLayout.CENTER);

        // ----------- BOTONES ABAJO -----------
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // ----------- EVENTOS -----------
        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnCancelar.addActionListener(e -> {
            new VentanaLogin().setVisible(true);
            dispose();
        });
    }

    private void registrarUsuario() {
        String id = txtIdUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        Date fechaNacimiento = dateChooser.getDate();
        String barrio = txtBarrio.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String pass = new String(txtPassword.getPassword());

        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                fechaNacimiento == null || barrio.isEmpty() || telefono.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor complete todos los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Guardar en archivo (persistencia)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            bw.write(id + "," + nombre + "," + apellido + "," + fechaNacimiento + "," + barrio + "," + telefono + "," + pass);
            bw.newLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar usuario.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
        new VentanaLogin().setVisible(true);
        dispose();
    }

}
