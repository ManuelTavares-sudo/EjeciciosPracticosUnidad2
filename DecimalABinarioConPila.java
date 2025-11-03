/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EjerciciosPilas;

/**
 * Conversión de número decimal a binario utilizando una pila.
 *
 * Se divide el número entre 2 y se apilan los residuos.
 * Luego se desapilan para mostrar el número binario.
 *
 * Ejemplo:
 * Entrada: 13
 * Salida: 1101
 */
import java.util.Stack;
import java.util.Scanner;

public class DecimalABinarioConPila {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese número: ");
        int num = sc.nextInt();

        Stack<Integer> pila = new Stack<>();

        int numeroOriginal = num; // Guardar para mostrar si quieres

        // Apilar los residuos de dividir entre 2
        while (num > 0) {
            pila.push(num % 2);
            num /= 2;
        }

        System.out.print("Binario: ");

        // Desapilar para invertir los bits y mostrar el binario correcto
        while (!pila.isEmpty()) {
            System.out.print(pila.pop());
        }

        sc.close();
    }
}

