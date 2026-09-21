package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {

    private LocalDate fecha;
    private String estado;
    private TicketDeAcceso ticket;

    private Estudiante estudiante;

    public Inscripcion(
            LocalDate fecha,
            String estado,
            Estudiante estudiante) {

        this.fecha = fecha;
        this.estado = estado;
        this.estudiante = estudiante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean estaConfirmada() {
        return "CONFIRMADA".equalsIgnoreCase(estado);
    }

    public TicketDeAcceso generarTicket() {

        if (!estaConfirmada()) {
            return null;
        }

        ticket = new TicketDeAcceso();

        return ticket;
    }

    public TicketDeAcceso getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", estudiante=" + estudiante +
                '}';
    }

    // =========================================
    // CLASE ANIDADA
    // =========================================

    public class TicketDeAcceso implements Serializable {

        private String idTicket;
        private LocalDate fechaEmision;

        public TicketDeAcceso() {

            this.idTicket =
                    "T-" +
                            estudiante.getLegajo() +
                            "-" +
                            fecha.toString();

            this.fechaEmision =
                    LocalDate.now();
        }

        public String getIdTicket() {
            return idTicket;
        }

        public LocalDate getFechaEmision() {
            return fechaEmision;
        }

        public void enviarTicket() {

            System.out.println(
                    "Enviando ticket " +
                            idTicket +
                            " a " +
                            estudiante.getNombre()
            );
        }

        @Override
        public String toString() {
            return "TicketDeAcceso{" +
                    "idTicket='" + idTicket + '\'' +
                    ", fechaEmision=" + fechaEmision +
                    '}';
        }
    }
}