/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio4;

/**
 *
 * @author tavar
 */
import java.util.Scanner;

class NodoPolinomioCircular {
    double coeficiente;
    int exponente;
    NodoPolinomioCircular siguiente;
    
    public NodoPolinomioCircular(double coeficiente, int exponente) {
        this.coeficiente = coeficiente;
        this.exponente = exponente;
        this.siguiente = null;
    }
}

class PolinomioCircular {
    private NodoPolinomioCircular ultimo;
    
    public PolinomioCircular() {
        this.ultimo = null;
    }
    
    // 1. Insertar termino en la lista circular
    public void insertarTermino(double coeficiente, int exponente) {
        NodoPolinomioCircular nuevoNodo = new NodoPolinomioCircular(coeficiente, exponente);
        
        if (ultimo == null) {
            // Lista vacia - el nodo apunta a si mismo
            ultimo = nuevoNodo;
            ultimo.siguiente = ultimo;
        } else {
            // Insertar despues del ultimo nodo
            nuevoNodo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
        }
    }
    
    // 2. Mostrar el polinomio en formato legible
    public void mostrarPolinomio() {
        if (ultimo == null) {
            System.out.println("Polinomio vacio");
            return;
        }
        
        NodoPolinomioCircular actual = ultimo.siguiente; // Empezar desde el primero
        NodoPolinomioCircular inicio = actual;
        boolean primerTermino = true;
        
        System.out.print("P(x) = ");
        do {
            // Mostrar el signo
            if (actual.coeficiente >= 0 && !primerTermino) {
                System.out.print(" + ");
            } else if (actual.coeficiente < 0) {
                System.out.print(" - ");
            }
            
            // Mostrar coeficiente (sin signo si es negativo)
            double coefAbs = Math.abs(actual.coeficiente);
            
            // Mostrar termino
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
        } while (actual != inicio);
        System.out.println();
    }
    
    // 3. Evaluar el polinomio para un valor especifico de x
    public double evaluar(double x) {
        if (ultimo == null) {
            return 0.0;
        }
        
        double resultado = 0.0;
        NodoPolinomioCircular actual = ultimo.siguiente; // Empezar desde el primero
        NodoPolinomioCircular inicio = actual;
        
        do {
            resultado += actual.coeficiente * Math.pow(x, actual.exponente);
            actual = actual.siguiente;
        } while (actual != inicio);
        
        return resultado;
    }
    
    // 4. Mostrar tabla de evaluacion
    public void mostrarTablaEvaluacion() {
        System.out.println("\n=== TABLA DE EVALUACION ===");
        System.out.println("  x   |   P(x)");
        System.out.println("---------------");
        
        for (double x = 0.0; x <= 5.0; x += 0.5) {
            double resultado = evaluar(x);
            System.out.printf("%4.1f  | %8.3f%n", x, resultado);
        }
    }
    
    // 5. Validar que no haya exponentes duplicados
    public boolean existeExponente(int exponente) {
        if (ultimo == null) {
            return false;
        }
        
        NodoPolinomioCircular actual = ultimo.siguiente;
        NodoPolinomioCircular inicio = actual;
        
        do {
            if (actual.exponente == exponente) {
                return true;
            }
            actual = actual.siguiente;
        } while (actual != inicio);
        
        return false;
    }
    
    // 6. Recorrido circular mostrando informacion de nodos
    public void mostrarRecorridoCircular() {
        if (ultimo == null) {
            System.out.println("Lista circular vacia");
            return;
        }
        
        System.out.println("\n=== RECORRIDO CIRCULAR ===");
        NodoPolinomioCircular actual = ultimo.siguiente; // Primer nodo
        NodoPolinomioCircular inicio = actual;
        int contador = 1;
        
        do {
            System.out.println("Nodo " + contador + ": " + actual.coeficiente + "x^" + actual.exponente);
            System.out.println("  Apunta a: " + actual.siguiente.coeficiente + "x^" + actual.siguiente.exponente);
            actual = actual.siguiente;
            contador++;
        } while (actual != inicio);
        
        System.out.println("Ultimo nodo apunta al primero: " + ultimo.coeficiente + "x^" + ultimo.exponente + 
                          " -> " + ultimo.siguiente.coeficiente + "x^" + ultimo.siguiente.exponente);
    }
}

public class Actividad04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PolinomioCircular polinomio = new PolinomioCircular();
        
        System.out.println("=== POLINOMIO CON LISTA ENLAZADA CIRCULAR ===");
        System.out.println("Instrucciones:");
        System.out.println("- Ingresa los terminos del polinomio como: coeficiente exponente");
        System.out.println("- Ejemplo: para 3x^4 - 4x^2 + 11, ingresa:");
        System.out.println("  3 4");
        System.out.println("  -4 2");
        System.out.println("  11 0");
        System.out.println("- Ingresa 'fin' para terminar la entrada");
        System.out.println();
        
        // 1. Entrada del polinomio
        System.out.println("Ingresa los terminos del polinomio:");
        
        while (true) {
            System.out.print("Termino (coeficiente exponente): ");
            
            if (scanner.hasNext("fin")) {
                scanner.next();
                break;
            }
            
            if (scanner.hasNextDouble()) {
                double coeficiente = scanner.nextDouble();
                
                if (scanner.hasNextInt()) {
                    int exponente = scanner.nextInt();
                    
                    if (exponente < 0) {
                        System.out.println("Error: El exponente debe ser no negativo.");
                        continue;
                    }
                    
                    if (polinomio.existeExponente(exponente)) {
                        System.out.println("Error: Ya existe un termino con exponente " + exponente);
                        continue;
                    }
                    
                    polinomio.insertarTermino(coeficiente, exponente);
                    System.out.println("Termino anadido: " + coeficiente + "x^" + exponente);
                    
                } else {
                    System.out.println("Error: Debes ingresar un exponente entero.");
                    scanner.next();
                }
            } else {
                System.out.println("Error: Entrada invalida. Usa el formato: coeficiente exponente");
                scanner.next();
            }
        }
        
        // 2. Mostrar el polinomio ingresado
        System.out.println("\n--- POLINOMIO INGRESADO ---");
        polinomio.mostrarPolinomio();
        
        // 3. Mostrar recorrido circular
        polinomio.mostrarRecorridoCircular();
        
        // 4. Mostrar tabla de evaluacion
        polinomio.mostrarTablaEvaluacion();
        
        // 5. Evaluacion adicional
        System.out.println("\n--- EVALUACION ADICIONAL ---");
        System.out.println("Deseas evaluar el polinomio en un valor especifico? (s/n)");
        scanner.nextLine();
        
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
