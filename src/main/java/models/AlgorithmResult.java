package models;

public class AlgorithmResult {

    private String nombreAlgoritmo;
    private int pasos;
    private long tiempoEjecucion; // en milisegundos
    private String fecha;

    public AlgorithmResult(String nombreAlgoritmo, int pasos, long tiempoEjecucion, String fecha) {
        this.nombreAlgoritmo = nombreAlgoritmo;
        this.pasos = pasos;
        this.tiempoEjecucion = tiempoEjecucion;
        this.fecha = fecha;
    }

    public String getNombreAlgoritmo() {
        return nombreAlgoritmo;
    }

    public void setNombreAlgoritmo(String nombreAlgoritmo) {
        this.nombreAlgoritmo = nombreAlgoritmo;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public long getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(long tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return nombreAlgoritmo + ";" + pasos + ";" + tiempoEjecucion + ";" + fecha;
    }
}
