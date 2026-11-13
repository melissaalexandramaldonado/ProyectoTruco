
package com.maldonado.ortega;

public class Perfil {

    private String nombre;
    private int partidasJugadas;
    private int partidasGanadas;
    private int partidasPerdidas;

    public Perfil(String nombre, int partidasJugadas,
                  int partidasGanadas, int partidasPerdidas) {
        this.nombre = nombre;
        this.partidasJugadas = partidasJugadas;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void registrarVictoria() {
        partidasJugadas++;
        partidasGanadas++;
    }

    public void registrarDerrota() {
        partidasJugadas++;
        partidasPerdidas++;
    }
}
