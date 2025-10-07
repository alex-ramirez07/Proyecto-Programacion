import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class VentanaLogin extends JFrame {
    private JTextField txtIdUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnCrearCuenta;


    public VentanaLogin() {
        setTitle("Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ----------- LOGO ARRIBA -----------
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("imagenes/logoapp.png"));
        Image img = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);
        add(logoLabel, BorderLayout.NORTH);

        // ----------- PANEL LOGIN (CENTRO) -----------
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createTitledBorder("Iniciar sesión"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(new JLabel("ID Usuario:"), gbc);
        txtIdUsuario = new JTextField(15);
        gbc.gridx = 1;
        loginPanel.add(txtIdUsuario, gbc);

        // Contraseña
        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(new JLabel("Contraseña:"), gbc);
        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        loginPanel.add(txtPassword, gbc);

        // Botón iniciar dentro del cuadro
        btnLogin = new JButton("Iniciar");
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        loginPanel.add(btnLogin, gbc);

        add(loginPanel, BorderLayout.CENTER);

        // ----------- CREAR CUENTA (ABAJO) -----------
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel noCuentaLabel = new JLabel("¿No tienes cuenta?");
        btnCrearCuenta = new JButton("Crear cuenta");
        bottomPanel.add(noCuentaLabel);
        bottomPanel.add(btnCrearCuenta);
        add(bottomPanel, BorderLayout.SOUTH);

        // ----------- EVENTOS -----------
        btnLogin.addActionListener(e -> {
            String id = txtIdUsuario.getText().trim();
            String pass = new String(txtPassword.getPassword());

            if (validarUsuario(id, pass)) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
                new VentanaCitas(id).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "ID o contraseña incorrectos",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCrearCuenta.addActionListener(e -> {
            new VentanaRegistro().setVisible(true);
            dispose();
        });
    }

    // ----------- VALIDAR USUARIO CON ARCHIVO -----------
    private boolean validarUsuario(String id, String pass) {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 2 && datos[0].equals(id) && datos[6].equals(pass)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de usuarios.");
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }


}
