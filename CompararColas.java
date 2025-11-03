/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjerciciosColas;

/**
 *Este codigo compara dos colas para ver si son iguales o desiguales
 * @author tavar
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompararColas {

    // Método que compara dos colas
    public static <T> boolean compararColas(Queue<T> q1, Queue<T> q2) {
        // Si tienen diferente tamaño, ya no son iguales
        if (q1.size() != q2.size()) {
            return false;
        }

        boolean sonIguales = true;

        Queue<T> aux1 = new LinkedList<>();
        Queue<T> aux2 = new LinkedList<>();

        // Recorremos ambas colas comparando elemento por elemento
        while (!q1.isEmpty()) {
            T elem1 = q1.poll();
            T elem2 = q2.poll();

            if (!elem1.equals(elem2)) {
                sonIguales = false;
            }

            // Guardamos los elementos en las colas auxiliares
            aux1.add(elem1);
            aux2.add(elem2);
        }

        // Restauramos el contenido original de las colas
        while (!aux1.isEmpty()) {
            q1.add(aux1.poll());
        }

        while (!aux2.isEmpty()) {
            q2.add(aux2.poll());
        }

        return sonIguales;
    }

    public static void main(String[] args) {
        Queue<Integer> cola1 = new LinkedList<>();
        Queue<Integer> cola2 = new LinkedList<>();

        cola1.add(10);
        cola1.add(20);
        cola1.add(30);

        cola2.add(10);
        cola2.add(20);
        cola2.add(30);

        System.out.println("Las colas son iguales?: " + compararColas(cola1, cola2));
        System.out.println("Contenido cola1: " + cola1);
        System.out.println("Contenido cola2: " + cola2);
    }
}

