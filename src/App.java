import actividades.Actividad;
import actividades.Charla;
import actividades.Curso;
import actividades.Taller;
import certificacion.Certificable;
import excepciones.CupoExcedidoException;
import hilos.EnvioTicketsThread;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.Sala;

import java.util.List;

public class App {

    public static void main(String[] args) {

        System.out.println(
                "======================================"
        );

        System.out.println(
                "     TRABAJO PRACTICO N° 2"
        );

        System.out.println(
                "======================================"
        );


        // ==========================================
        // EJERCICIO 1 Y 2
        // CREAR ESTUDIANTES
        // ==========================================

        Estudiante rocio =
                new Estudiante(
                        "001",
                        "Rocio"
                );

        Estudiante zoe =
                new Estudiante(
                        "002",
                        "Zoe"
                );

        Estudiante erika =
                new Estudiante(
                        "003",
                        "Erika"
                );

        Estudiante valentina =
                new Estudiante(
                        "004",
                        "Valentina"
                );

        System.out.println(
                "\n--- ESTUDIANTES ---"
        );

        System.out.println(rocio);
        System.out.println(zoe);
        System.out.println(erika);
        System.out.println(valentina);


        // ==========================================
        // CREAR EVENTO
        // ==========================================

        EventoUniversitario evento =
                new EventoUniversitario(
                        "E001",
                        "Jornada Universitaria",
                        5000,
                        false
                );


        // ==========================================
        // CREAR SALA
        // ==========================================

        Sala sala =
                new Sala(
                        1,
                        "Aula Magna"
                );

        evento.asignarSala(sala);


        // ==========================================
        // CREAR ACTIVIDADES
        // ==========================================

        Charla charla =
                new Charla(
                        1,
                        "Introduccion a Java",
                        10,
                        "Profesor Juan"
                );

        Taller taller =
                new Taller(
                        2,
                        "Programacion Orientada a Objetos",
                        10,
                        true
                );

        Curso curso =
                new Curso(
                        3,
                        "Java Avanzado",
                        10,
                        2
                );


        evento.agregarActividad(charla);
        evento.agregarActividad(taller);
        evento.agregarActividad(curso);


        // ==========================================
        // MOSTRAR EVENTO
        // ==========================================

        System.out.println(
                "\n--- DATOS DEL EVENTO ---"
        );

        evento.mostrarDatos();


        // ==========================================
        // EJERCICIO 1
        // INSCRIPCIONES
        // ==========================================

        System.out.println(
                "\n--- INSCRIPCIONES ---"
        );

        try {

            charla.inscribir(rocio);

            taller.inscribir(zoe);

            curso.inscribir(erika);

            taller.inscribir(valentina);

            System.out.println(
                    "Inscripciones realizadas correctamente."
            );

        } catch (CupoExcedidoException e) {

            System.out.println(
                    "Error de cupo: " +
                            e.getMessage()
            );

        } finally {

            System.out.println(
                    "Finalizo el proceso de inscripcion."
            );
        }


        // ==========================================
        // CASO FALLIDO
        // PROBAR CUPO EXCEDIDO
        // ==========================================

        System.out.println(
                "\n--- PRUEBA DE EXCEPCION ---"
        );

        try {

            for (int i = 1; i <= 11; i++) {

                Estudiante estudiante =
                        new Estudiante(
                                "TEMP" + i,
                                "Estudiante " + i
                        );

                charla.inscribir(estudiante);

                System.out.println(
                        "Inscripcion " +
                                i +
                                " realizada."
                );
            }

        } catch (CupoExcedidoException e) {

            System.out.println(
                    "CASO FALLIDO CONTROLADO:"
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            System.out.println(
                    "Finalizo la prueba de excepcion."
            );
        }


        // ==========================================
        // MOSTRAR INSCRIPCIONES
        // ==========================================

        System.out.println(
                "\n--- INSCRIPCIONES DE LAS ACTIVIDADES ---"
        );

        charla.mostrarInscripciones();

        taller.mostrarInscripciones();

        curso.mostrarInscripciones();


        // ==========================================
        // EJERCICIO 2
        // CERTIFICADOS
        // ==========================================

        System.out.println(
                "\n--- CERTIFICADOS ---"
        );

        emitirCertificado(
                taller,
                zoe
        );

        emitirCertificado(
                curso,
                erika
        );

        System.out.println(
                "\nLas charlas no son certificables."
        );


        // ==========================================
        // EJERCICIO 3
        // FILTRAR ACTIVIDADES
        // ==========================================

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "       EJERCICIO 3"
        );

        System.out.println(
                "======================================"
        );


        List<Charla> charlas =
                evento.filtrarActividadesPorTipo(
                        Charla.class
                );

        List<Taller> talleres =
                evento.filtrarActividadesPorTipo(
                        Taller.class
                );

        List<Curso> cursos =
                evento.filtrarActividadesPorTipo(
                        Curso.class
                );


        System.out.println(
                "Cantidad de Charlas: " +
                        charlas.size()
        );

        System.out.println(
                "Cantidad de Talleres: " +
                        talleres.size()
        );

        System.out.println(
                "Cantidad de Cursos: " +
                        cursos.size()
        );


        // ==========================================
        // MOSTRAR TIPOS DE LISTAS
        // ==========================================

        System.out.println(
                "\nList<Charla> creada correctamente."
        );

        System.out.println(
                "List<Taller> creada correctamente."
        );

        System.out.println(
                "List<Curso> creada correctamente."
        );


        // ==========================================
        // COSTOS
        // ==========================================

        double costoCharlas =
                evento.calcularCostoMateriales(
                        charlas
                );

        double costoTalleres =
                evento.calcularCostoMateriales(
                        talleres
                );

        double costoCursos =
                evento.calcularCostoMateriales(
                        cursos
                );


        System.out.println(
                "\nCosto de materiales de Charlas: $" +
                        costoCharlas
        );

        System.out.println(
                "Costo de materiales de Talleres: $" +
                        costoTalleres
        );

        System.out.println(
                "Costo de materiales de Cursos: $" +
                        costoCursos
        );


        // ==========================================
        // EJERCICIO 1
        // PERSISTENCIA
        // ==========================================

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "       PERSISTENCIA"
        );

        System.out.println(
                "======================================"
        );

        try {

            evento.persistirEvento();

        } catch (Exception e) {

            System.out.println(
                    "Error inesperado al guardar: " +
                            e.getMessage()
            );

        } finally {

            System.out.println(
                    "Finalizo el proceso de persistencia."
            );
        }


        EventoUniversitario recuperado =
                EventoUniversitario
                        .recuperarEvento("E001");


        if (recuperado != null) {

            System.out.println(
                    "\nEvento recuperado correctamente:"
            );

            recuperado.mostrarDatos();

        } else {

            System.out.println(
                    "No se pudo recuperar el evento."
            );
        }


        // ==========================================
        // EJERCICIO 4
        // GENERAR TICKETS
        // ==========================================

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "       EJERCICIO 4"
        );

        System.out.println(
                "======================================"
        );


        generarTickets(
                evento
        );


        // ==========================================
        // CREAR HILO
        // ==========================================

        EnvioTicketsThread hiloEnvio =
                new EnvioTicketsThread(
                        evento
                );


        System.out.println(
                "\nHilo principal: iniciando envio..."
        );

        hiloEnvio.start();


        // ==========================================
        // EL HILO PRINCIPAL SIGUE TRABAJANDO
        // ==========================================

        System.out.println(
                "\n[HILO PRINCIPAL] " +
                        "El programa principal continua..."
        );

        System.out.println(
                "\n[HILO PRINCIPAL] " +
                        "Mostrando datos del evento:"
        );

        evento.mostrarDatos();


        System.out.println(
                "\n[HILO PRINCIPAL] " +
                        "Mostrando actividades:"
        );

        for (Actividad actividad :
                evento.getActividades()) {

            System.out.println(
                    "- " +
                            actividad.getTipo() +
                            ": " +
                            actividad.getTitulo()
            );
        }


        // ==========================================
        // ESPERAR AL HILO
        // ==========================================

        try {

            hiloEnvio.join();

        } catch (InterruptedException e) {

            System.out.println(
                    "El hilo principal fue interrumpido."
            );

            Thread.currentThread().interrupt();
        }


        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "       FIN DEL PROGRAMA"
        );

        System.out.println(
                "======================================"
        );
    }


    // ==========================================
    // METODO PARA CERTIFICADOS
    // ==========================================

    public static void emitirCertificado(
            Actividad actividad,
            Estudiante estudiante) {

        if (actividad instanceof Certificable certificable) {

            String certificado =
                    certificable
                            .generarCertificadoEstudiante(
                                    estudiante
                            );

            System.out.println();
            System.out.println(certificado);
            System.out.println(
                    "------------------------------"
            );

        } else {

            System.out.println(
                    "La actividad " +
                            actividad.getTitulo() +
                            " no es certificable."
            );
        }
    }


    // ==========================================
    // GENERAR TICKETS
    // ==========================================

    public static void generarTickets(
            EventoUniversitario evento) {

        for (Actividad actividad :
                evento.getActividades()) {

            for (Inscripcion inscripcion :
                    actividad.getInscripciones()) {

                if (inscripcion.estaConfirmada()) {

                    inscripcion.generarTicket();

                    System.out.println(
                            "Ticket generado para: " +
                                    inscripcion
                                            .getEstudiante()
                                            .getNombre()
                    );
                }
            }
        }
    }
}