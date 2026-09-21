package modelo;

import actividades.Actividad;
import actividades.Charla;
import actividades.Curso;
import actividades.Taller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario
        implements Serializable {

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;

    private static int cantidadEventos = 0;

    private Sala sala;

    private List<Actividad> actividades;

    public EventoUniversitario(
            String id,
            String titulo,
            double costoBase,
            boolean gratuito) {

        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;

        this.actividades =
                new ArrayList<>();

        cantidadEventos++;
    }

    public EventoUniversitario(
            EventoUniversitario otro) {

        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;

        this.actividades =
                new ArrayList<>(
                        otro.actividades
                );

        cantidadEventos++;
    }

    public double calcularCostoEstimado() {

        if (gratuito) {
            return 0;
        }

        double costo =
                costoBase;

        for (Actividad actividad :
                actividades) {

            costo +=
                    actividad.calcularCostoMateriales();
        }

        return costo;
    }

    public void asignarSala(Sala sala) {

        this.sala = sala;
    }

    public void crearActividad(
            int id,
            String titulo,
            int cupo) {

        Actividad actividad =
                new Charla(
                        id,
                        titulo,
                        cupo,
                        "Sin especificar"
                );

        actividades.add(actividad);
    }

    public void agregarActividad(
            Actividad actividad) {

        actividades.add(actividad);
    }

    public void mostrarDatos() {

        System.out.println();
        System.out.println(
                "========== EVENTO =========="
        );

        System.out.println(
                "ID: " + id
        );

        System.out.println(
                "Título: " + titulo
        );

        System.out.println(
                "Costo base: $" + costoBase
        );

        System.out.println(
                "Gratuito: " + gratuito
        );

        if (sala != null) {

            System.out.println(
                    "Sala: " +
                            sala.getNombre()
            );

        } else {

            System.out.println(
                    "Sala: No asignada"
            );
        }

        System.out.println();
        System.out.println("Actividades:");

        if (actividades.isEmpty()) {

            System.out.println(
                    "No hay actividades."
            );

        } else {

            for (Actividad actividad :
                    actividades) {

                System.out.println(
                        "- " +
                                actividad.getTipo() +
                                ": " +
                                actividad.getTitulo()
                );

                actividad.mostrarInscripciones();
            }
        }

        System.out.println(
                "Costo estimado: $" +
                        calcularCostoEstimado()
        );

        System.out.println(
                "============================"
        );
    }

    // ==========================================
    // EJERCICIO 3
    // FILTRADO GENERICO
    // ==========================================

    public <T extends Actividad>
    List<T> filtrarActividadesPorTipo(
            Class<T> tipo) {

        List<T> resultado =
                new ArrayList<>();

        for (Actividad actividad :
                actividades) {

            if (tipo.isInstance(actividad)) {

                resultado.add(
                        tipo.cast(actividad)
                );
            }
        }

        return resultado;
    }

    // ==========================================
    // EJERCICIO 3
    // WILDCARD
    // ==========================================

    public double calcularCostoMateriales(
            List<? extends Actividad> actividades) {

        double total = 0;

        for (Actividad actividad :
                actividades) {

            total +=
                    actividad.calcularCostoMateriales();
        }

        return total;
    }

    // ==========================================
    // PERSISTENCIA
    // ==========================================

    public boolean persistirEvento() {

        String nombreArchivo =
                "evento_" + id + ".dat";

        try (
                FileOutputStream archivo =
                        new FileOutputStream(
                                nombreArchivo
                        );

                ObjectOutputStream salida =
                        new ObjectOutputStream(
                                archivo
                        )
        ) {

            salida.writeObject(this);

            System.out.println(
                    "Evento guardado correctamente."
            );

            return true;

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Error: no se pudo crear o encontrar " +
                            "el archivo."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error de entrada/salida al guardar."
            );

        } catch (SecurityException e) {

            System.out.println(
                    "Error de permisos al guardar el evento."
            );
        }

        return false;
    }

    public static EventoUniversitario
    recuperarEvento(String id) {

        String nombreArchivo =
                "evento_" + id + ".dat";

        try (
                FileInputStream archivo =
                        new FileInputStream(
                                nombreArchivo
                        );

                ObjectInputStream entrada =
                        new ObjectInputStream(
                                archivo
                        )
        ) {

            EventoUniversitario evento =
                    (EventoUniversitario)
                            entrada.readObject();

            System.out.println(
                    "Evento recuperado correctamente."
            );

            return evento;

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Error: el archivo no existe."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error de entrada/salida al recuperar."
            );

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "Error: no se encontró la clase del objeto."
            );

        } catch (SecurityException e) {

            System.out.println(
                    "Error de permisos al recuperar."
            );
        }

        return null;
    }

    public int getCantidadEventos() {

        return cantidadEventos;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Sala getSala() {
        return sala;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }
}