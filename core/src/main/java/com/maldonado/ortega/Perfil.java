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
}

