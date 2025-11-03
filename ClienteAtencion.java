/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjerciciosColas;

/**
 *
 * @author tavar
 */
public class ClienteAtencion {
    int tiempoAtencion;
    int tiempoLlegada;

    public ClienteAtencion(int tiempoAtencion, int tiempoLlegada) {
        this.tiempoAtencion = tiempoAtencion;
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(int tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }
    
}
