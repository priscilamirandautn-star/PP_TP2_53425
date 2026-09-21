package actividades;

import certificacion.Certificable;
import modelo.Estudiante;

public class Taller
        extends Actividad
        implements Certificable {

    private boolean requiereNotebook;

    public Taller(
            int id,
            String titulo,
            int cupoMaximo,
            boolean requiereNotebook) {

        super(id, titulo, cupoMaximo);

        this.requiereNotebook = requiereNotebook;
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {

        if (requiereNotebook) {
            return 1000;
        }

        return 500;
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    @Override
    public String generarCertificadoEstudiante(
            Estudiante estudiante) {

        return "CERTIFICADO DE ASISTENCIA\n" +
                "Entidad emisora: " +
                ENTIDAD_EMISORA +
                "\nEstudiante: " +
                estudiante.getNombre() +
                "\nLegajo: " +
                estudiante.getLegajo() +
                "\nActividad: " +
                getTitulo() +
                "\nTipo: Taller";
    }
}