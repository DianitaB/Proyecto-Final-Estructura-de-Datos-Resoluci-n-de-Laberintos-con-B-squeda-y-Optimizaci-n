package views;
import javax.swing.*;
import java.awt.*;

public class ResultsDialog extends JDialog {

    public ResultsDialog(JFrame parent, int pasos, int tiempoMs) {
        super(parent, "🎉 Resultado", true);
        setSize(350, 170);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panelInfo = new JPanel(new GridLayout(2, 1));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panelInfo.setBackground(Color.WHITE);

        JLabel lblPasos = new JLabel("🔢 Pasos realizados: " + pasos);
        JLabel lblTiempo = new JLabel("⏱ Tiempo total: " + tiempoMs + " ms");
        lblPasos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblTiempo.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        panelInfo.add(lblPasos);
        panelInfo.add(lblTiempo);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBackground(new Color(70, 130, 180));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);
        panelBtn.add(btnCerrar);

        add(panelInfo, BorderLayout.CENTER);
        add(panelBtn, BorderLayout.SOUTH);
    }
}

