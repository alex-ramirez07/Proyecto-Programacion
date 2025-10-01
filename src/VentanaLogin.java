import javax.swing.*;
import java.awt.*;

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
            String id = txtIdUsuario.getText();
            String pass = new String(txtPassword.getPassword());

            // Validación simple
            if (id.equals("123") && pass.equals("123")) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}