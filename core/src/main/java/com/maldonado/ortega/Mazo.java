package com.maldonado.ortega;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private final List<Carta> cartas = new ArrayList<>();

    public Mazo() {
        armar();
    }

    public void armar() {
        cartas.clear();
        for (Carta.Palo palo : Carta.Palo.values()) {
            for (int numero : Carta.NUMEROS) {
                cartas.add(new Carta(palo, numero));
            }
        }
    }

    public void mezclar() {
        for (int i = cartas.size() - 1; i > 0; i--) {
            Collections.swap(cartas, i, MathUtils.random(i));
        }
    }

    public Carta repartir() {
        return cartas.remove(cartas.size() - 1);
    }

    public List<Carta> repartirMano(int cantidad) {
        List<Carta> mano = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) mano.add(repartir());
        return mano;
    }

    public int cantidad() { return cartas.size(); }
}
