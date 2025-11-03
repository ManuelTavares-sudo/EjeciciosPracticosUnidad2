/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjerciciosColas;

/**
 *
 * @author tavar
 */
public class ClienteSupermercado {
    private int tiempoLlegada;
    private int tiempoAtencion; // Duración total que tardará en caja

    public ClienteSupermercado(int tiempoLlegada, int tiempoAtencion) {
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoAtencion = tiempoAtencion;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public int getTiempoAtencion() {
        return tiempoAtencion;
    }
}

