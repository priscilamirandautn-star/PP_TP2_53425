package actividades;

import certificacion.Certificable;
import modelo.Estudiante;

public class Curso
        extends Actividad
        implements Certificable {

    private int nivel;

    public Curso(
            int id,
            String titulo,
            int cupoMaximo,
            int nivel) {

        super(id, titulo, cupoMaximo);

        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    @Override
    public double calcularCostoMateriales() {

        return 1500 + (nivel * 100);
    }

    @Override
    public String getTipo() {
        return "Curso";
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
                "\nTipo: Curso";
    }
}