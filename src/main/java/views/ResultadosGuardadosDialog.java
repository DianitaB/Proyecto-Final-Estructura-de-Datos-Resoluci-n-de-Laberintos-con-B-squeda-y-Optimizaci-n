package views;

import models.AlgorithmResult;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ResultadosGuardadosDialog extends JDialog {

    private DefaultTableModel modelo;
    private JTable tabla;
    private List<AlgorithmResult> resultados;

    public ResultadosGuardadosDialog(JFrame parent) {
        super(parent, "Resultados Guardados", true);
        setSize(600, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        resultados = new ArrayList<>();

        modelo = new DefaultTableModel(new Object[]{"Algoritmo", "Celdas Camino", "Tiempo (ns)"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnGraficar = new JButton("Graficar Resultados");
        JButton btnLimpiar = new JButton("Limpiar Resultados");

        btnGraficar.addActionListener(e -> mostrarGrafica());
        btnLimpiar.addActionListener(e -> {
            resultados.clear();
            modelo.setRowCount(0);
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnGraficar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void agregarResultado(AlgorithmResult r) {
        resultados.add(r);
        modelo.addRow(new Object[]{
                r.getNombreAlgoritmo(),
                r.getPasos(),
                r.getTiempoEjecucion()
        });
    }

    public void guardarResultadosEnCSV(String rutaArchivo) {
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter(rutaArchivo);
            writer.println("Algoritmo,Celdas Camino,Tiempo (ns),Fecha");

            for (AlgorithmResult r : resultados) {
                writer.printf("%s,%d,%d,%s%n",
                        r.getNombreAlgoritmo(),
                        r.getPasos(),
                        r.getTiempoEjecucion(),
                        r.getFecha());
            }

            writer.close();
            System.out.println("✅ Resultados guardados en: " + rutaArchivo);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar resultados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void mostrarGrafica() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (AlgorithmResult r : resultados) {
            dataset.addValue(r.getTiempoEjecucion(), "Tiempo(ns)", r.getNombreAlgoritmo());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Tiempos de Ejecución por Algoritmo",
                "Algoritmo",
                "Tiempo (ns)",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        JDialog graficoDialog = new JDialog(this, "Gráfica", true);
        graficoDialog.setSize(600, 400);
        graficoDialog.setLocationRelativeTo(this);
        graficoDialog.add(chartPanel);
        graficoDialog.setVisible(true);
    }
}
