package com.maldonado.ortega;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaFin implements Screen, EscuchaEntrada {

    private final Main juego;
    private final OrthographicCamera camara = new OrthographicCamera();
    private final FitViewport viewport = new FitViewport(Main.ANCHO, Main.ALTO, camara);
    private final GlyphLayout renglon = new GlyphLayout();

    private final int puntosJugador, puntosIA;
    private final Rectangle botonRevancha = new Rectangle(Main.ANCHO / 2f - 170, 250, 340, 70);
    private final Rectangle botonMenu     = new Rectangle(Main.ANCHO / 2f - 170, 160, 340, 70);

    public PantallaFin(Main juego, int puntosJugador, int puntosIA) {
        this.juego = juego;
        this.puntosJugador = puntosJugador;
        this.puntosIA = puntosIA;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new ControlEntrada(viewport, this));
        if (puntosJugador > puntosIA) juego.recursos.sonar(juego.recursos.gana);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        juego.batch.setProjectionMatrix(camara.combined);
        juego.batch.begin();

        juego.batch.draw(juego.recursos.mesa, 0, 0, Main.ANCHO, Main.ALTO);
        juego.batch.setColor(0f, 0f, 0f, 0.55f);
        juego.batch.draw(juego.recursos.pixel, 0, 0, Main.ANCHO, Main.ALTO);
        juego.batch.setColor(Color.WHITE);

        boolean gano = puntosJugador > puntosIA;
        juego.fuenteTitulo.setColor(gano ? new Color(0.95f, 0.88f, 0.70f, 1f)
            : new Color(0.90f, 0.55f, 0.50f, 1f));
        centrar(juego.fuenteTitulo, gano ? "GANASTE" : "PERDISTE", Main.ALTO - 160);

        juego.fuente.setColor(Color.WHITE);
        centrar(juego.fuente, "Vos " + puntosJugador + "   -   IA " + puntosIA, Main.ALTO - 250);

        dibujarBoton(botonRevancha, "REVANCHA");
        dibujarBoton(botonMenu, "VOLVER AL MENU");

        juego.batch.end();
    }

    private void dibujarBoton(Rectangle r, String etiqueta) {
        juego.batch.setColor(0.10f, 0.22f, 0.15f, 0.95f);
        juego.batch.draw(juego.recursos.pixel, r.x, r.y, r.width, r.height);
        juego.batch.setColor(0.83f, 0.66f, 0.23f, 1f);
        juego.batch.draw(juego.recursos.pixel, r.x, r.y, r.width, 3);
        juego.batch.setColor(Color.WHITE);
        juego.fuente.setColor(new Color(0.96f, 0.92f, 0.80f, 1f));
        renglon.setText(juego.fuente, etiqueta);
        juego.fuente.draw(juego.batch, renglon,
            r.x + (r.width - renglon.width) / 2f,
            r.y + (r.height + renglon.height) / 2f);
    }

    private void centrar(com.badlogic.gdx.graphics.g2d.BitmapFont f, String texto, float y) {
        renglon.setText(f, texto);
        f.draw(juego.batch, renglon, (Main.ANCHO - renglon.width) / 2f, y);
    }

    @Override
    public void alHacerClic(float x, float y) {
        if (botonRevancha.contains(x, y)) {
            juego.recursos.sonar(juego.recursos.clic);
            juego.setScreen(new PantallaJuego(juego));
        } else if (botonMenu.contains(x, y)) {
            juego.recursos.sonar(juego.recursos.clic);
            juego.setScreen(new PantallaMenu(juego));
        }
    }

    @Override
    public void alPresionarTecla(int tecla) {
        if (tecla == Input.Keys.ESCAPE) juego.setScreen(new PantallaMenu(juego));
        if (tecla == Input.Keys.M) juego.recursos.alternarSilencio();
    }

    @Override public void resize(int ancho, int alto) { viewport.update(ancho, alto, true); }
    @Override public void pause()   { }
    @Override public void resume()  { }
    @Override public void hide()    { }
    @Override public void dispose() { }
}
