/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EjerciciosPilas;

/**
 * Verificar si una pila está vacía
 * 
 * Se crea una pila, se verifica si está vacía,
 * luego se agrega un elemento y se vuelve a verificar.
 * 
 */
import java.util.Stack;

public class VerificarPilaVacia {

    public static void main(String[] args) {
        Stack<Integer> pila = new Stack<>();

        // Verificar si la pila está vacía al inicio
        System.out.println("¿Está vacía la pila? " + pila.isEmpty());

        // Agregar un elemento a la pila
        pila.push(1);

        // Verificar nuevamente
        System.out.println("¿Está vacía la pila? " + pila.isEmpty());
    }
}

