/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio3;

/**
 *
 * @author tavar
 */
import java.util.Scanner;

// Clase para el nodo del polinomio (coeficiente y exponente)
class NodoPolinomio {
    double coeficiente;
    int exponente;
    NodoPolinomio siguiente;
    
    public NodoPolinomio(double coeficiente, int exponente) {
        this.coeficiente = coeficiente;
        this.exponente = exponente;
        this.siguiente = null;
    }
}

// Clase para la lista enlazada del polinomio
class Polinomio {
    private NodoPolinomio cabeza;
    
    public Polinomio() {
        this.cabeza = null;
    }
    
    // 1. Insertar término al final del polinomio
    public void insertarTermino(double coeficiente, int exponente) {
        NodoPolinomio nuevoNodo = new NodoPolinomio(coeficiente, exponente);
        
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoPolinomio actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }
    
    // 2. Mostrar el polinomio en formato legible
    public void mostrarPolinomio() {
        if (cabeza == null) {
            System.out.println("Polinomio vacío");
            return;
        }
        
        NodoPolinomio actual = cabeza;
        boolean primerTermino = true;
        
        System.out.print("P(x) = ");
        while (actual != null) {
            // Mostrar el signo
            if (actual.coeficiente >= 0 && !primerTermino) {
                System.out.print(" + ");
            } else if (actual.coeficiente < 0) {
                System.out.print(" - ");
            }
            
            // Mostrar coeficiente (sin signo si es negativo)
            double coefAbs = Math.abs(actual.coeficiente);
            
            // Mostrar término
            if (actual.exponente == 0) {
                System.out.print(coefAbs);
            } else if (actual.exponente == 1) {
                if (coefAbs == 1) {
                    System.out.print("x");
                } else {
                    System.out.print(coefAbs + "x");
                }
            } else {
                if (coefAbs == 1) {
                    System.out.print("x^" + actual.exponente);
                } else {
                    System.out.print(coefAbs + "x^" + actual.exponente);
                }
            }
            
            actual = actual.siguiente;
            primerTermino = false;
        }
        System.out.println();
    }
    
    // 3. Evaluar el polinomio para un valor específico de x
    public double evaluar(double x) {
        if (cabeza == null) {
            return 0.0;
        }
        
        double resultado = 0.0;
        NodoPolinomio actual = cabeza;
        
        while (actual != null) {
            resultado += actual.coeficiente * Math.pow(x, actual.exponente);
            actual = actual.siguiente;
        }
        
        return resultado;
    }
    
    // 4. Mostrar tabla de evaluación desde 0.0 hasta 5.0 en incrementos de 0.5
    public void mostrarTablaEvaluacion() {
        System.out.println("\n=== TABLA DE EVALUACIÓN ===");
        System.out.println("  x   |   P(x)");
        System.out.println("---------------");
        
        for (double x = 0.0; x <= 5.0; x += 0.5) {
            double resultado = evaluar(x);
            System.out.printf("%4.1f  | %8.3f%n", x, resultado);
        }
    }
    
    // 5. Validar que no haya exponentes duplicados
    public boolean existeExponente(int exponente) {
        NodoPolinomio actual = cabeza;
        while (actual != null) {
            if (actual.exponente == exponente) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}

// Clase principal
public class Actividad03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Polinomio polinomio = new Polinomio();
        
        System.out.println("=== REPRESENTACIÓN Y EVALUACIÓN DE POLINOMIOS ===");
        System.out.println("Instrucciones:");
        System.out.println("- Ingresa los términos del polinomio como: coeficiente exponente");
        System.out.println("- Ejemplo: para 3x^4 - 4x^2 + 11, ingresa:");
        System.out.println("  3 4");
        System.out.println("  -4 2");
        System.out.println("  11 0");
        System.out.println("- Ingresa 'fin' para terminar la entrada");
        System.out.println();
        
        // 1. Entrada del polinomio
        System.out.println("Ingresa los términos del polinomio:");
        
        while (true) {
            System.out.print("Término (coeficiente exponente): ");
            
            if (scanner.hasNext("fin")) {
                scanner.next(); // Consumir "fin"
                break;
            }
            
            if (scanner.hasNextDouble()) {
                double coeficiente = scanner.nextDouble();
                
                if (scanner.hasNextInt()) {
                    int exponente = scanner.nextInt();
                    
                    // Validar exponente no negativo
                    if (exponente < 0) {
                        System.out.println("Error: El exponente debe ser no negativo.");
                        continue;
                    }
                    
                    // Validar exponente único
                    if (polinomio.existeExponente(exponente)) {
                        System.out.println("Error: Ya existe un término con exponente " + exponente);
                        continue;
                    }
                    
                    polinomio.insertarTermino(coeficiente, exponente);
                    System.out.println("Término añadido: " + coeficiente + "x^" + exponente);
                    
                } else {
                    System.out.println("Error: Debes ingresar un exponente entero.");
                    scanner.next(); // Limpiar entrada inválida
                }
            } else {
                System.out.println("Error: Entrada inválida. Usa el formato: coeficiente exponente");
                scanner.next(); // Limpiar entrada inválida
            }
        }
        
        // 2. Mostrar el polinomio ingresado
        System.out.println("\n--- POLINOMIO INGRESADO ---");
        polinomio.mostrarPolinomio();
        
        // 3. Mostrar tabla de evaluación
        polinomio.mostrarTablaEvaluacion();
        
        // 4. Evaluación adicional por si el usuario quiere probar otros valores
        System.out.println("\n--- EVALUACIÓN ADICIONAL ---");
        System.out.println("¿Deseas evaluar el polinomio en un valor específico? (s/n)");
        scanner.nextLine(); // Limpiar buffer
        
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Ingresa el valor de x a evaluar: ");
            double xEspecifico = scanner.nextDouble();
            double resultado = polinomio.evaluar(xEspecifico);
            System.out.printf("P(%.2f) = %.3f%n", xEspecifico, resultado);
        }
        
        scanner.close();
        System.out.println("\n=== PROGRAMA TERMINADO ===");
    }
}
