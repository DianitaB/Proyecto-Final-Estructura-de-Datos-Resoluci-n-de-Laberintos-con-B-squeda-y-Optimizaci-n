package views;
import javax.swing.*;
import java.awt.*;

public class MazeFrame extends JFrame {

    private MazePanel mazePanel;

    public MazeFrame() {
        setTitle("Laberinto Interactivo");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(250, 248, 240)); // Fondo beige claro
        setJMenuBar(crearMenu());

        pedirDimensiones();
        add(crearPanelLateral(), BorderLayout.WEST);
        add(crearPanelInferior(), BorderLayout.SOUTH);
        crearMazePanel();

        setVisible(true);
    }

    private JMenuBar crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuAyuda = new JMenu("Ayuda");
        menuArchivo.add(new JMenuItem("Salir"));
        menuBar.add(menuArchivo);
        menuBar.add(menuAyuda);
        return menuBar;
    }

    private int filas, columnas;

    private void pedirDimensiones() {
        filas = Integer.parseInt(JOptionPane.showInputDialog("Filas del laberinto:"));
        columnas = Integer.parseInt(JOptionPane.showInputDialog("Columnas del laberinto:"));
    }

    private JPanel crearPanelLateral() {
        JPanel lateral = new JPanel();
        lateral.setLayout(new BoxLayout(lateral, BoxLayout.Y_AXIS));
        lateral.setPreferredSize(new Dimension(180, 0));
        lateral.setBackground(new Color(240, 235, 220)); // Beige más oscuro
        lateral.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel lbl = new JLabel("🎯 MODO");
        lbl.setFont(new Font("Verdana", Font.BOLD, 16));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnInicio = crearBotonPlano("🟢 Inicio", new Color(34, 139, 34));
        JButton btnFin = crearBotonPlano("🔴 Fin", new Color(178, 34, 34));
        JButton btnMuro = crearBotonPlano("⬛ Muro", Color.DARK_GRAY);

        btnInicio.addActionListener(e -> mazePanel.setModo("START"));
        btnFin.addActionListener(e -> mazePanel.setModo("END"));
        btnMuro.addActionListener(e -> mazePanel.setModo("WALL"));

        lateral.add(lbl);
        lateral.add(Box.createVerticalStrut(20));
        lateral.add(btnInicio);
        lateral.add(Box.createVerticalStrut(10));
        lateral.add(btnFin);
        lateral.add(Box.createVerticalStrut(10));
        lateral.add(btnMuro);

        return lateral;
    }

    private JButton crearBotonPlano(String texto, Color fondo) {
        JButton btn = new JButton(texto);
        btn.setBackground(fondo);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    private JPanel crearPanelInferior() {
        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 12));
        inferior.setBackground(new Color(235, 232, 222));

        JComboBox<String> combo = new JComboBox<>(new String[]{"Recursivo", "BFS", "DFS"});
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JButton btnResolver = crearBotonPlano("🧠 Resolver", new Color(60, 179, 113));
        JButton btnPaso = crearBotonPlano("👣 Paso a paso", new Color(255, 165, 0));
        JButton btnLimpiar = crearBotonPlano("🧼 Limpiar", new Color(105, 105, 105));

        btnLimpiar.addActionListener(e -> mazePanel.limpiar());
        btnResolver.addActionListener(e -> new ResultsDialog(this, 23, 119).setVisible(true));

        inferior.add(new JLabel("Algoritmo:"));
        inferior.add(combo);
        inferior.add(btnResolver);
        inferior.add(btnPaso);
        inferior.add(btnLimpiar);

        return inferior;
    }

    private void crearMazePanel() {
        mazePanel = new MazePanel(filas, columnas);
        JScrollPane scroll = new JScrollPane(mazePanel);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scroll, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MazeFrame::new);
    }
}
