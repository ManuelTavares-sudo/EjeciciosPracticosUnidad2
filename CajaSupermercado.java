/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjerciciosColas;

/**
 *
 * @author tavar
 */
public class CajaSupermercado {
    private boolean disponible = true;
    private int tiempoRestante = 0;
    private int clientesAtendidos = 0;

    public boolean estaDisponible() {
        return disponible;
    }

    public void atenderCliente(ClienteSupermercado cliente) {
        this.disponible = false;
        this.tiempoRestante = cliente.getTiempoAtencion();
        clientesAtendidos++;
    }

    public void actualizar() {
        if (!disponible) {
            tiempoRestante--;
            if (tiempoRestante <= 0) {
                disponible = true;
            }
        }
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }
}

