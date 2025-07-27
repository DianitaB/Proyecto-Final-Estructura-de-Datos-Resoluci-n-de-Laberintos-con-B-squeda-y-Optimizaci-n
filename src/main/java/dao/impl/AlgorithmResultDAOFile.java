package dao.impl;

import dao.AlgorithmResultDAO;
import models.AlgorithmResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmResultDAOFile implements AlgorithmResultDAO {

    private final String rutaArchivo;

    public AlgorithmResultDAOFile(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        inicializarArchivo();
    }

    private void inicializarArchivo() {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
                writer.println("Algoritmo;Pasos;Tiempo(ms);Fecha");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void guardar(AlgorithmResult resultado) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            writer.println(resultado.toString()); // usa el .toString() de AlgorithmResult
        }
    }

    @Override
    public List<AlgorithmResult> listar() throws IOException {
        List<AlgorithmResult> resultados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            reader.readLine(); // saltar encabezado
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    int pasos = Integer.parseInt(partes[1]);
                    long tiempo = Long.parseLong(partes[2]);
                    String fecha = partes[3];
                    resultados.add(new AlgorithmResult(nombre, pasos, tiempo, fecha));
                }
            }
        }
        return resultados;
    }
}
