/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio1;

/**
 *
 * @author tavar
 */
import java.util.Random;
import java.util.Scanner;

// Clase para el nodo de la lista enlazada
class Nodo {
    int dato;
    Nodo siguiente;
    
    // Constructor
    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// Clase para la lista enlazada
class ListaEnlazada {
    private Nodo cabeza;
    private Nodo cola;
    
    // Constructor
    public ListaEnlazada() {
        this.cabeza = null;
        this.cola = null;
    }
    
    // 1. Insertar al final de la lista
    public void insertarAlFinal(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        
        if (cabeza == null) {
            // Si la lista está vacía
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            // Si la lista no está vacía
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
    }
    
    // 2. Recorrer y mostrar la lista
    public void mostrarLista() {
        if (cabeza == null) {
            System.out.println("La lista está vacia");
            return;
        }
        
        Nodo actual = cabeza;
        System.out.print("Lista: ");
        
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
    
    // 3. Eliminar nodos mayores a un valor dado
    public void eliminarMayoresQue(int valorLimite) {
        if (cabeza == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        
        // Eliminar nodos al inicio que sean mayores al valor límite
        while (cabeza != null && cabeza.dato > valorLimite) {
            cabeza = cabeza.siguiente;
        }
        
        // Si queda vacia la lista
        if (cabeza == null) {
            cola = null;
            System.out.println("Todos los nodos fueron eliminados.");
            return;
        }
        
        // Eliminar nodos en el medio y al final
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato > valorLimite) {
                actual.siguiente = actual.siguiente.siguiente;
                
                // Actualizar cola al eliminar
                if (actual.siguiente == null) {
                    cola = actual;
                }
            } else {
                actual = actual.siguiente;
            }
        }
    }
    
    // Método para generar números aleatorios y crear la lista
    public void crearListaAleatoria(int cantidad, int rangoMaximo) {
        Random random = new Random();
        
        for (int i = 0; i < cantidad; i++) {
            int numero = random.nextInt(rangoMaximo) + 1; // Números entre 1 y rangoMaximo
            insertarAlFinal(numero);
        }
    }
}


public class Actividad01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaEnlazada lista = new ListaEnlazada();
        
        System.out.println("=== MANIPULACION DE LISTA ENLAZADA ===");
        
        System.out.print("Cuantos numeros deseas generar? ");
        int cantidad = scanner.nextInt();
        
        System.out.print("Valor maximo para los numeros aleatorios? ");
        int rangoMaximo = scanner.nextInt();
        
        lista.crearListaAleatoria(cantidad, rangoMaximo);
        
        System.out.println("\n--- Lista Original ---");
        lista.mostrarLista();
        
        System.out.print("\nIngresa el valor limite para eliminar nodos: ");
        int valorLimite = scanner.nextInt();
        
        lista.eliminarMayoresQue(valorLimite);
        
        System.out.println("\n--- Lista despues de eliminar nodos > " + valorLimite + " ---");
        lista.mostrarLista();
        
        scanner.close();
        System.out.println("\n=== PROGRAMA TERMINADO ===");
    }
}
