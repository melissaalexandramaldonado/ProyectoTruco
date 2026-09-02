package com.maldonado.ortega;

import com.badlogic.gdx.math.Rectangle;

public class Carta {

    public enum Palo { ESPADA, BASTO, ORO, COPA }

    public static final int ANCHO = 140;
    public static final int ALTO  = 210;
    public static final int[] NUMEROS = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};

    private final Palo palo;
    private final int numero;
    private final Rectangle area = new Rectangle(0, 0, ANCHO, ALTO);

    public Carta(Palo palo, int numero) {
        this.palo = palo;
        this.numero = numero;
    }

    public int getFila() {
        return palo.ordinal();
    }

    public int getColumna() {
        for (int i = 0; i < NUMEROS.length; i++) {
            if (NUMEROS[i] == numero) return i;
        }
        return 0;
    }

    public int getFuerza() {
        if (numero == 1 && palo == Palo.ESPADA) return 14;
        if (numero == 1 && palo == Palo.BASTO)  return 13;
        if (numero == 7 && palo == Palo.ESPADA) return 12;
        if (numero == 7 && palo == Palo.ORO)    return 11;
        switch (numero) {
            case 3:  return 10;
            case 2:  return 9;
            case 1:  return 8;
            case 12: return 7;
            case 11: return 6;
            case 10: return 5;
            case 7:  return 4;
            case 6:  return 3;
            case 5:  return 2;
            case 4:  return 1;
            default: return 0;
        }
    }

    public String getNombre() {
        return numero + " de " + palo.name().toLowerCase();
    }

    public void posicionar(float x, float y) {
        area.setPosition(x, y);
    }

    public boolean contiene(float x, float y) {
        return area.contains(x, y);
    }

    public Rectangle getArea() { return area; }
    public Palo getPalo()      { return palo; }
    public int getNumero()     { return numero; }
}
