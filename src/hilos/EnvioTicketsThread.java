package hilos;

import actividades.Actividad;
import modelo.EventoUniversitario;
import modelo.Inscripcion;

public class EnvioTicketsThread
        extends Thread {

    private EventoUniversitario evento;

    public EnvioTicketsThread(
            EventoUniversitario evento) {

        this.evento = evento;
    }

    @Override
    public void run() {

        System.out.println(
                "\n[HILO ENVIO] " +
                        "Comenzando envío de tickets..."
        );

        for (Actividad actividad :
                evento.getActividades()) {

            for (Inscripcion inscripcion :
                    actividad.getInscripciones()) {

                if (inscripcion.estaConfirmada()) {

                    if (inscripcion.getTicket() == null) {

                        inscripcion.generarTicket();
                    }

                    if (inscripcion.getTicket() != null) {

                        System.out.println(
                                "[HILO ENVIO] " +
                                        "Enviando ticket de " +
                                        inscripcion
                                                .getEstudiante()
                                                .getNombre()
                        );

                        inscripcion
                                .getTicket()
                                .enviarTicket();

                        try {

                            Thread.sleep(1000);

                        } catch (InterruptedException e) {

                            System.out.println(
                                    "[HILO ENVIO] " +
                                            "El envío fue interrumpido."
                            );

                            Thread.currentThread()
                                    .interrupt();

                            return;
                        }
                    }
                }
            }
        }

        System.out.println(
                "[HILO ENVIO] " +
                        "Todos los tickets fueron enviados."
        );
    }
}