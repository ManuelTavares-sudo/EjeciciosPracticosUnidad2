/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjerciciosColas;

/**
 *
 * @author tavar
 */
import java.util.*;

public class SimulacionSupermercadoEsperanza {

    public static void main(String[] args) {

        Queue<ClienteAtencion> fila = new LinkedList<>();
        List<ClienteAtencion>[] cajas = new ArrayList[4];
        for (int i = 0; i < cajas.length; i++) cajas[i] = new ArrayList<>();

        Random rnd = new Random();

        int tiempoSimulacion = 420; // 7 horas * 60 min
        int clientesAtendidos = 0;
        int maxFila = 0;
        int totalTamanosFila = 0;
        int tiempoMaxEspera = 0;
        int tiempoApertura4Caja = -1;

        for (int minuto = 0; minuto < tiempoSimulacion; minuto++) {

            // Llega cliente (85% de probabilidad)
            if (rnd.nextDouble() < 0.85) {
                int tiempoAtencion = 3 + rnd.nextInt(5); // 3-7 min
                fila.add(new ClienteAtencion(tiempoAtencion, minuto));
            }

            if (fila.size() > 20 && tiempoApertura4Caja == -1) {
                tiempoApertura4Caja = minuto;
            }

            // Asignar clientes a cajas vacías
            for (int i = 0; i < 4; i++) {
                if (i < 3 || tiempoApertura4Caja != -1) {
                    if (cajas[i].isEmpty() && !fila.isEmpty()) {
                        cajas[i].add(fila.poll());
                    }
                }
            }

            // Procesar cajas
            for (int i = 0; i < 4; i++) {
                if (!cajas[i].isEmpty()) {
                    ClienteAtencion c = cajas[i].get(0);
                    c.tiempoAtencion--;
                    if (c.tiempoAtencion <= 0) {
                        cajas[i].remove(0);
                        clientesAtendidos++;

                        int espera = minuto - c.tiempoLlegada;
                        tiempoMaxEspera = Math.max(tiempoMaxEspera, espera);

                        if (!fila.isEmpty()) cajas[i].add(fila.poll());
                    }
                }
            }

            maxFila = Math.max(maxFila, fila.size());
            totalTamanosFila += fila.size();
        }

        double tamanioMedioFila = (double) totalTamanosFila / tiempoSimulacion;

        // Resultados
        System.out.println("===== SIMULACION =====");
        System.out.println("Clientes atendidos: " + clientesAtendidos);
        System.out.println("Tamaño maximo de la fila: " + maxFila);
        System.out.printf("Tamaño medio de la fila: %.2f\n", tamanioMedioFila);
        System.out.println("Tiempo maximo de espera: " + tiempoMaxEspera + " min");

        if (tiempoApertura4Caja == -1)
            System.out.println("La cuarta caja NO fue necesaria.");
        else
            System.out.println("La cuarta caja se abrio en seguida: " + tiempoApertura4Caja);
    }
}


