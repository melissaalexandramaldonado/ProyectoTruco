package com.maldonado.ortega;

import com.badlogic.gdx.math.MathUtils;

import java.util.List;

public class JugadorIA {

    public Carta elegirCarta(List<Carta> mano, Carta cartaEnMesa) {
        if (mano.isEmpty()) return null;

        if (cartaEnMesa == null) {
            return MathUtils.randomBoolean(0.7f) ? masBaja(mano) : masAlta(mano);
        }

        Carta elegida = null;
        for (Carta c : mano) {
            if (c.getFuerza() > cartaEnMesa.getFuerza()) {
                if (elegida == null || c.getFuerza() < elegida.getFuerza()) elegida = c;
            }
        }
        return elegida != null ? elegida : masBaja(mano);
    }

    public boolean aceptaApuesta(List<Carta> mano, int puntosEnJuego) {
        if (mano.isEmpty()) return false;
        int suma = 0;
        for (Carta c : mano) suma += c.getFuerza();
        float promedio = (float) suma / mano.size();
        float umbral = 5.5f + puntosEnJuego * 0.4f;
        return promedio >= umbral || MathUtils.randomBoolean(0.2f);
    }

    private Carta masBaja(List<Carta> mano) {
        Carta r = mano.get(0);
        for (Carta c : mano) if (c.getFuerza() < r.getFuerza()) r = c;
        return r;
    }

    private Carta masAlta(List<Carta> mano) {
        Carta r = mano.get(0);
        for (Carta c : mano) if (c.getFuerza() > r.getFuerza()) r = c;
        return r;
    }
}
