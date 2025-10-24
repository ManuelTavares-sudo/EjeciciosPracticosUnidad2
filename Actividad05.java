/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio5;

/**
 *
 * @author tavar
 */
import java.util.Scanner;

class NodoCaracter {
    char caracter;
    NodoCaracter anterior;
    NodoCaracter siguiente;
    
    public NodoCaracter(char caracter) {
        this.caracter = caracter;
        this.anterior = null;
        this.siguiente = null;
    }
}

class ListaDoblementeEnlazada {
    private NodoCaracter cabeza;
    private NodoCaracter cola;
    
    public ListaDoblementeEnlazada() {
        this.cabeza = null;
        this.cola = null;
    }
    
    // 1. Insertar caracter al final de la lista
    public void insertarCaracter(char caracter) {
        NodoCaracter nuevoNodo = new NodoCaracter(caracter);
        
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            nuevoNodo.anterior = cola;
            cola = nuevoNodo;
        }
    }
    
    // 2. Construir lista desde una cadena
    public void construirDesdeCadena(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (c != ' ') { // Ignorar espacios
                insertarCaracter(c);
            }
        }
    }
    
    // 3. Mostrar lista hacia adelante
    public void mostrarListaAdelante() {
        if (cabeza == null) {
            System.out.println("Lista vacia");
            return;
        }
        
        NodoCaracter actual = cabeza;
        System.out.print("Lista (adelante): ");
        while (actual != null) {
            System.out.print(actual.caracter + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
    
    // 4. Mostrar lista hacia atras
    public void mostrarListaAtras() {
        if (cola == null) {
            System.out.println("Lista vacia");
            return;
        }
        
        NodoCaracter actual = cola;
        System.out.print("Lista (atras): ");
        while (actual != null) {
            System.out.print(actual.caracter + " ");
            actual = actual.anterior;
        }
        System.out.println();
    }
    
    // 5. Ordenamiento burbuja para lista doblemente enlazada
    public void ordenarBurbuja() {
        if (cabeza == null || cabeza.siguiente == null) {
            return; // Lista vacia o con un solo elemento
        }
        
        boolean huboIntercambio;
        do {
            huboIntercambio = false;
            NodoCaracter actual = cabeza;
            
            while (actual.siguiente != null) {
                if (actual.caracter > actual.siguiente.caracter) {
                    // Intercambiar caracteres
                    char temp = actual.caracter;
                    actual.caracter = actual.siguiente.caracter;
                    actual.siguiente.caracter = temp;
                    huboIntercambio = true;
                }
                actual = actual.siguiente;
            }
        } while (huboIntercambio);
    }
    
    // 6. Ordenamiento por insercion (alternativo)
    public void ordenarInsercion() {
        if (cabeza == null || cabeza.siguiente == null) {
            return;
        }
        
        NodoCaracter actual = cabeza.siguiente;
        
        while (actual != null) {
            char valorActual = actual.caracter;
            NodoCaracter anterior = actual.anterior;
            
            while (anterior != null && anterior.caracter > valorActual) {
                anterior.siguiente.caracter = anterior.caracter;
                anterior = anterior.anterior;
            }
            
            if (anterior == null) {
                cabeza.caracter = valorActual;
            } else {
                anterior.siguiente.caracter = valorActual;
            }
            
            actual = actual.siguiente;
        }
    }
    
    // 7. Obtener cantidad de nodos
    public int obtenerCantidadNodos() {
        int contador = 0;
        NodoCaracter actual = cabeza;
        
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        
        return contador;
    }
    
    // 8. Mostrar lista con referencias (para debugging)
    public void mostrarListaConReferencias() {
        if (cabeza == null) {
            System.out.println("Lista vacia");
            return;
        }
        
        NodoCaracter actual = cabeza;
        System.out.println("\n=== REFERENCIAS DE NODOS ===");
        
        while (actual != null) {
            String anteriorStr = (actual.anterior == null) ? "null" : String.valueOf(actual.anterior.caracter);
            String siguienteStr = (actual.siguiente == null) ? "null" : String.valueOf(actual.siguiente.caracter);
            
            System.out.println("Nodo: " + actual.caracter + 
                             " | Anterior: " + anteriorStr + 
                             " | Siguiente: " + siguienteStr);
            actual = actual.siguiente;
        }
    }
}

public class Actividad05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDoblementeEnlazada lista = new ListaDoblementeEnlazada();
        
        System.out.println("=== LISTA DOBLEMENTE ENLAZADA DE CARACTERES ===");
        
        // 1. Lectura de cadena desde teclado
        System.out.print("Ingresa una cadena de texto: ");
        String cadena = scanner.nextLine();
        
        // 2. Construccion de lista doblemente enlazada
        lista.construirDesdeCadena(cadena);
        
        System.out.println("\n--- LISTA ORIGINAL ---");
        lista.mostrarListaAdelante();
        lista.mostrarListaAtras();
        System.out.println("Cantidad de caracteres: " + lista.obtenerCantidadNodos());
        
        // Mostrar referencias (opcional)
        lista.mostrarListaConReferencias();
        
        // 3. Ordenamiento alfabetico
        System.out.println("\n--- ORDENANDO LISTA ---");
        lista.ordenarBurbuja();
        
        // 4. Mostrar lista ordenada
        System.out.println("\n--- LISTA ORDENADA ---");
        lista.mostrarListaAdelante();
        lista.mostrarListaAtras();
        
        // Mostrar referencias despues del ordenamiento
        lista.mostrarListaConReferencias();
        
        scanner.close();
        System.out.println("\n=== PROGRAMA TERMINADO ===");
    }
}
