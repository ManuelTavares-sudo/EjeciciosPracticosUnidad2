/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio2;

/**
 *
 * @author tavar
 */
import java.io.*;
import java.util.Scanner;

// Clase para el nodo de palabras
class NodoPalabra {
    String palabra;
    NodoPalabra siguiente;
    
    public NodoPalabra(String palabra) {
        this.palabra = palabra;
        this.siguiente = null;
    }
}

// Clase para la lista enlazada de palabras
class ListaPalabras {
    private NodoPalabra cabeza;
    private NodoPalabra cola;
    
    public ListaPalabras() {
        this.cabeza = null;
        this.cola = null;
    }
    
    // 1. Insertar palabra al final de la lista
    public void insertarAlFinal(String palabra) {
        NodoPalabra nuevoNodo = new NodoPalabra(palabra);
        
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
    }
    
    // 2. Mostrar todas las palabras de la lista
    public void mostrarLista() {
        if (cabeza == null) {
            System.out.println("La lista de palabras esta vacia.");
            return;
        }
        
        NodoPalabra actual = cabeza;
        int contador = 1;
        
        System.out.println("=== LISTA DE PALABRAS ===");
        while (actual != null) {
            System.out.println(contador + ". " + actual.palabra);
            actual = actual.siguiente;
            contador++;
        }
        System.out.println("=========================");
    }
    
    // 3. Eliminar una palabra específica
    public boolean eliminarPalabra(String palabraEliminar) {
        if (cabeza == null) {
            return false;
        }
        
        // Caso especial: eliminar la cabeza
        if (cabeza.palabra.equals(palabraEliminar)) {
            cabeza = cabeza.siguiente;
            if (cabeza == null) {
                cola = null;
            }
            return true;
        }
        
        // Buscar la palabra en el resto de la lista
        NodoPalabra actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.palabra.equals(palabraEliminar)) {
                actual.siguiente = actual.siguiente.siguiente;
                
                // Actualizar cola si eliminamos el último nodo
                if (actual.siguiente == null) {
                    cola = actual;
                }
                return true;
            }
            actual = actual.siguiente;
        }
        
        return false; // Palabra no encontrada
    }
    
    // 4. Buscar si una palabra existe en la lista
    public boolean buscarPalabra(String palabraBuscar) {
        NodoPalabra actual = cabeza;
        
        while (actual != null) {
            if (actual.palabra.equals(palabraBuscar)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    
    // 5. Leer palabras desde archivo
    public void leerDesdeArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            Scanner lectorArchivo = new Scanner(archivo);
            
            System.out.println("Leyendo palabras desde: " + nombreArchivo);
            
            while (lectorArchivo.hasNext()) {
                String palabra = lectorArchivo.next();
                // Limpiar la palabra de caracteres no deseados
                palabra = palabra.replaceAll("[^a-zA-ZáéíóúñÑ]", "").toLowerCase();
                if (!palabra.isEmpty()) {
                    insertarAlFinal(palabra);
                }
            }
            
            lectorArchivo.close();
            System.out.println("Lectura del archivo completada");
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo no encontrado. Se creara uno nuevo al guardar.");
        }
    }
    
    // 6. Escribir palabras a archivo
    public void escribirAArchivo(String nombreArchivo) {
        try {
            FileWriter escritor = new FileWriter(nombreArchivo);
            BufferedWriter bufferEscritor = new BufferedWriter(escritor);
            
            NodoPalabra actual = cabeza;
            
            while (actual != null) {
                bufferEscritor.write(actual.palabra);
                if (actual.siguiente != null) {
                    bufferEscritor.write(" "); // Separar palabras con espacios
                }
                actual = actual.siguiente;
            }
            
            bufferEscritor.close();
            System.out.println("Palabras guardadas en: " + nombreArchivo);
            
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    // 7. Obtener cantidad de palabras
    public int obtenerCantidadPalabras() {
        int contador = 0;
        NodoPalabra actual = cabeza;
        
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        
        return contador;
    }
}

// Clase principal
public class Actividad02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaPalabras lista = new ListaPalabras();
        
        final String NOMBRE_ARCHIVO = "palabras.txt";
        
        System.out.println("=== LISTA ENLAZADA DE PALABRAS DESDE ARCHIVO ===");
        
        // 1. Leer palabras desde archivo
        lista.leerDesdeArchivo(NOMBRE_ARCHIVO);
        
        int opcion;
        do {
            System.out.println("\n--- MENU DE OPCIONES ---");
            System.out.println("1.- Mostrar todas las palabras");
            System.out.println("2.- Agregar nueva palabra");
            System.out.println("3.- Eliminar palabra");
            System.out.println("4.- Buscar palabra");
            System.out.println("5.- Guardar en archivo y salir");
            System.out.print("Selecciona una opcion: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1 -> {
                    // Mostrar lista
                    lista.mostrarLista();
                    System.out.println("Total de palabras: " + lista.obtenerCantidadPalabras());
                }
                    
                case 2 -> {
                    // Añadir nueva palabra
                    System.out.print("Ingresa la nueva palabra: ");
                    String nuevaPalabra = scanner.nextLine().trim().toLowerCase();
                    
                    if (!nuevaPalabra.isEmpty()) {
                        lista.insertarAlFinal(nuevaPalabra);
                        System.out.println("Palabra '" + nuevaPalabra + "' agregada correctamente.");
                    } else {
                        System.out.println("Error: La palabra no puede estar vacia.");
                    }
                }
                    
                case 3 -> {
                    // Eliminar palabra
                    System.out.print("Ingresa la palabra a eliminar: ");
                    String palabraEliminar = scanner.nextLine().trim().toLowerCase();
                    
                    if (lista.eliminarPalabra(palabraEliminar)) {
                        System.out.println("Palabra '" + palabraEliminar + "' eliminada correctamente.");
                    } else {
                        System.out.println("La palabra '" + palabraEliminar + "' no se encontro en la lista.");
                    }
                }
                    
                case 4 -> {
                    // Buscar palabra
                    System.out.print("Ingresa la palabra a buscar: ");
                    String palabraBuscar = scanner.nextLine().trim().toLowerCase();
                    
                    if (lista.buscarPalabra(palabraBuscar)) {
                        System.out.println("La palabra '" + palabraBuscar + "' SI esta en la lista.");
                    } else {
                        System.out.println("La palabra '" + palabraBuscar + "' NO esta en la lista.");
                    }
                }
                    
                case 5 -> {
                    // Guardar y salir
                    lista.escribirAArchivo(NOMBRE_ARCHIVO);
                    System.out.println("¡Hasta luego!");
                }
                    
                default -> System.out.println("Opcion no valida. Intenta nuevamente.");
            }
            
        } while (opcion != 5);
        
        scanner.close();
    }
}
