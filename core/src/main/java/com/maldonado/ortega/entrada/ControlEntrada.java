package com.maldonado.ortega;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ControlEntrada extends InputAdapter {

    private final Viewport viewport;
    private final EscuchaEntrada escucha;
    private final Vector3 punto = new Vector3();

    public ControlEntrada(Viewport viewport, EscuchaEntrada escucha) {
        this.viewport = viewport;
        this.escucha = escucha;
    }

    @Override
    public boolean touchDown(int pantallaX, int pantallaY, int puntero, int boton) {
        punto.set(pantallaX, pantallaY, 0);
        viewport.unproject(punto);
        escucha.alHacerClic(punto.x, punto.y);
        return true;
    }

    @Override
    public boolean keyDown(int tecla) {
        escucha.alPresionarTecla(tecla);
        return true;
    }
}
