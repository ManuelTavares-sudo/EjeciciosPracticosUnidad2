/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjerciciosColas;

/**
 *
 * @author tavar
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SupermercadoSimulacion {

    public static void main(String[] args) {
        Queue<ClienteSupermercado> fila = new LinkedList<>();
        Random random = new Random();

        CajaSupermercado[] cajas = new CajaSupermercado[4];
        for (int i = 0; i < 3; i++) {
            cajas[i] = new CajaSupermercado();
        }
        cajas[3] = new CajaSupermercado(); // Caja 4 (se activará después)

        int tiempoSimulacion = 420;
        int tamanoMaxFila = 0;
        int totalClientesAtendidos = 0;
        int tiempoMaxEspera = 0;
        int tiempoAperturaCaja4 = -1;

        int tiempoActual = 0;

        while (tiempoActual < tiempoSimulacion) {
            // Llegada de un cliente por minuto en promedio
            if (random.nextDouble() < 0.9) { // 90% prob → se puede ajustar
                int tiempoAtencion = 2 + random.nextInt(5); // 2 a 6 min
                fila.add(new ClienteSupermercado(tiempoActual, tiempoAtencion));
            }

            // Activar caja 4 si hay más de 20 personas
            if (fila.size() > 20 && tiempoAperturaCaja4 == -1) {
                tiempoAperturaCaja4 = tiempoActual;
            }

            // Actualizar cajas
            for (int i = 0; i < cajas.length; i++) {
                if (i == 3 && tiempoAperturaCaja4 == -1) continue;

                cajas[i].actualizar();

                if (cajas[i].estaDisponible() && !fila.isEmpty()) {
                    ClienteSupermercado cliente = fila.poll();
                    int espera = tiempoActual - cliente.getTiempoLlegada();
                    if (espera > tiempoMaxEspera) {
                        tiempoMaxEspera = espera;
                    }
                    cajas[i].atenderCliente(cliente);
                    totalClientesAtendidos++;
                }
            }

            // Estadísticas
            if (fila.size() > tamanoMaxFila) {
                tamanoMaxFila = fila.size();
            }

            tiempoActual++;
        }

        // Resultados
        System.out.println("✅ RESULTADOS DE LA SIMULACIÓN");
        System.out.println("--------------------------------");
        System.out.println("Total clientes atendidos: " + totalClientesAtendidos);
        System.out.println("Tamaño máximo de la fila: " + tamanoMaxFila);
        System.out.println("Tiempo máximo de espera: " + tiempoMaxEspera + " min");

        if (tiempoAperturaCaja4 == -1) {
            System.out.println("La cuarta caja nunca se abrió");
        } else {
            System.out.println("La cuarta caja se abrió en el minuto: " + tiempoAperturaCaja4);
        }
    }
}


