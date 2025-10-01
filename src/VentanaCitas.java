import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaCitas extends JFrame {

    private DefaultListModel<String> modeloCitas;
    private JList<String> listaCitas;
    private JButton btnAgendar, btnCancelar;
    private String idUsuario;

    public VentanaCitas(String idUsuario) {
        this.idUsuario = idUsuario;
        setTitle("Gestión de Citas - Usuario: " + idUsuario);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modeloCitas = new DefaultListModel<>();
        listaCitas = new JList<>(modeloCitas);

        JScrollPane scroll = new JScrollPane(listaCitas);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgendar = new JButton("Agendar Cita");
        btnCancelar = new JButton("Cancelar Cita");
        panelBotones.add(btnAgendar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción para agendar
        btnAgendar.addActionListener(e -> agendarCita());

        // Acción para cancelar
        btnCancelar.addActionListener(e -> cancelarCita());
    }

    private void agendarCita() {
        String especialidad = JOptionPane.showInputDialog(
                this, "Especialidad (ej. Cardiología):");
        if (especialidad != null && !especialidad.trim().isEmpty()) {
            String fechaHora = JOptionPane.showInputDialog(
                    this, "Fecha y hora (ej. 12/10 10:00am):");
            if (fechaHora != null && !fechaHora.trim().isEmpty()) {
                modeloCitas.addElement(especialidad + " - " + fechaHora);
            }
        }
    }

    private void cancelarCita() {
        int index = listaCitas.getSelectedIndex();
        if (index >= 0) {
            modeloCitas.remove(index);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una cita para cancelar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}