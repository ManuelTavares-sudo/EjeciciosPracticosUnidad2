/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjerciciosPilas;

/**
 * Simulación básica de operaciones en una pila.
 * Se insertan elementos con push y se eliminan con pop.
 * 
 * Resultado esperado: [5, 10]
 * @author tavar
 */
import java.util.Stack;

public class SimulacionPilaBasica {

    public static void main(String[] args) {
        // Crear pila de enteros
        Stack<Integer> pila = new Stack<>();

        // Insertar elementos (PUSH)
        pila.push(5);
        pila.push(10);
        pila.push(15);
        pila.push(20);

        // Eliminar dos elementos (POP)
        pila.pop();
        pila.pop();

        // Mostrar contenido final de la pila
        System.out.println("Contenido actual de la pila: " + pila);
    }
}

