package actividades;

import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {

    private int id;
    private String titulo;
    private int cupoMaximo;

    protected static final int CUPO_MINIMO = 10;

    private List<Inscripcion> inscripciones;

    public Actividad(
            int id,
            String titulo,
            int cupoMaximo) {

        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;

        this.inscripciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public Inscripcion inscribir(Estudiante estudiante)
            throws CupoExcedidoException {

        if (inscripciones.size() >= cupoMaximo) {

            throw new CupoExcedidoException(
                    "Cupo excedido en la actividad: " +
                            titulo
            );
        }

        Inscripcion inscripcion =
                new Inscripcion(
                        LocalDate.now(),
                        "CONFIRMADA",
                        estudiante
                );

        inscripciones.add(inscripcion);

        return inscripcion;
    }

    public void mostrarInscripciones() {

        System.out.println(
                "Inscripciones de: " + titulo
        );

        if (inscripciones.isEmpty()) {

            System.out.println(
                    "No hay estudiantes inscriptos."
            );

            return;
        }

        for (Inscripcion inscripcion : inscripciones) {

            System.out.println(
                    "- " +
                            inscripcion.getEstudiante().getNombre() +
                            " | Estado: " +
                            inscripcion.getEstado()
            );
        }
    }

    public final void mostrarIdentificacion() {

        System.out.println(
                "ID: " +
                        id +
                        " | Título: " +
                        titulo
        );
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public abstract double calcularCostoMateriales();

    public abstract String getTipo();
}
