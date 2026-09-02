package com.maldonado.ortega;

public class ReglasTruco {

    public static final int GANA_JUGADOR = 1;
    public static final int GANA_IA      = -1;
    public static final int EMPATE       = 0;

    public static final int PUNTOS_PARA_GANAR = 15;

    public static int compararCartas(Carta delJugador, Carta deLaIA) {
        if (delJugador.getFuerza() > deLaIA.getFuerza()) return GANA_JUGADOR;
        if (deLaIA.getFuerza() > delJugador.getFuerza()) return GANA_IA;
        return EMPATE;
    }

    public static boolean manoTerminada(int ganadasJugador, int ganadasIA, int bazasJugadas) {
        return ganadasJugador >= 2 || ganadasIA >= 2 || bazasJugadas >= 3;
    }

    public static int ganadorDeLaMano(int ganadasJugador, int ganadasIA) {
        if (ganadasJugador > ganadasIA) return GANA_JUGADOR;
        if (ganadasIA > ganadasJugador) return GANA_IA;
        return GANA_JUGADOR;
    }
}
