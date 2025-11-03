/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjerciciosPilas;

/**
 * Pila de Nombres
 * 
 * Permite ingresar nombres hasta que el usuario escriba "FIN".
 * Luego imprime los nombres en orden inverso utilizando una pila.
 * 
 */
import java.util.Scanner;
import java.util.Stack;

public class PilaDeNombres {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> pila = new Stack<>();
        String nombre;

        System.out.println("=== Registro de nombres en pila ===");

        // Entrada de datos
        while (true) {
            System.out.print("Ingrese un nombre (FIN para terminar): ");
            nombre = sc.nextLine();

            if (nombre.equalsIgnoreCase("FIN")) {
                break;
            }

            pila.push(nombre); // Guardar el nombre en la pila
        }

        System.out.println("\n--- Nombres en orden inverso ---");

        // Salida de datos invirtiendo el orden
        while (!pila.isEmpty()) {
            System.out.println(pila.pop());
        }

        sc.close();
    }
}

