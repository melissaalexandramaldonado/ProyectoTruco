package com.maldonado.ortega;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {

    public static final float ANCHO = 1280f;
    public static final float ALTO  = 720f;

    public SpriteBatch batch;
    public BitmapFont fuente;
    public BitmapFont fuenteTitulo;
    public Recursos recursos;

    @Override
    public void create() {
        batch = new SpriteBatch();

        fuente = new BitmapFont();
        fuente.getData().setScale(1.8f);

        fuenteTitulo = new BitmapFont();
        fuenteTitulo.getData().setScale(4f);

        recursos = new Recursos();

        setScreen(new PantallaMenu(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        fuente.dispose();
        fuenteTitulo.dispose();
        recursos.dispose();
    }
}
