/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EjerciciosPilas;

/**
 * Invertir una palabra utilizando una pila de caracteres.
 *
 * Se lee una palabra, se apilan sus caracteres
 * y luego se desapilan para mostrarla invertida.
 *
 * Ejemplo:
 * Entrada: UTNG
 * Salida: GNTU
 */
import java.util.Stack;
import java.util.Scanner;

public class InvertirPalabraConPila {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese una palabra: ");
        String palabra = sc.nextLine();

        Stack<Character> pila = new Stack<>();

        // Apilar cada letra de la palabra
        for (char c : palabra.toCharArray()) {
            pila.push(c);
        }

        System.out.print("Invertida: ");

        // Desapilar para invertir el orden
        while (!pila.isEmpty()) {
            System.out.print(pila.pop());
        }

        sc.close();
    }
}

