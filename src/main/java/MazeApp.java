

import javax.swing.*;

import views.MazeFrame;

public class MazeApp {
    public static void main(String[] args) {
        // Configurar apariencia del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Iniciar la GUI
        SwingUtilities.invokeLater(MazeFrame::new);
    }
}
