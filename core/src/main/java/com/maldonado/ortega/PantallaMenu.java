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

public class PantallaMenu implements Screen, EscuchaEntrada {

    private final Main juego;
    private final OrthographicCamera camara;
    private final FitViewport viewport;
    private final GlyphLayout renglon = new GlyphLayout();

    private final Rectangle botonJugar = new Rectangle(Main.ANCHO / 2 - 170, 280, 340, 72);
    private final Rectangle botonSalir = new Rectangle(Main.ANCHO / 2 - 170, 185, 340, 72);

    public PantallaMenu(Main juego) {
        this.juego = juego;
        camara = new OrthographicCamera();
        viewport = new FitViewport(Main.ANCHO, Main.ALTO, camara);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new ControlEntrada(viewport, this));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        juego.batch.setProjectionMatrix(camara.combined);

        juego.batch.begin();
        juego.batch.draw(juego.recursos.mesa, 0, 0, Main.ANCHO, Main.ALTO);

        juego.fuenteTitulo.setColor(new Color(0.95f, 0.88f, 0.70f, 1f));
        centrar(juego.fuenteTitulo, "TRUCO ARGENTINO", Main.ALTO - 130);

        juego.fuente.setColor(Color.WHITE);
        centrar(juego.fuente, "Melissa Maldonado  -  Tomas Ortega", Main.ALTO - 200);

        dibujarBoton(botonJugar, "JUGAR");
        dibujarBoton(botonSalir, "SALIR");

        juego.fuente.setColor(new Color(0.85f, 0.85f, 0.85f, 1f));
        centrar(juego.fuente, "M silencia  -  + / - volumen  -  Volumen: "
            + juego.recursos.volumenPorcentaje() + "%"
            + (juego.recursos.estaSilenciado() ? "  (SILENCIADO)" : ""), 90);

        juego.batch.end();
    }

    private void dibujarBoton(Rectangle r, String etiqueta) {
        juego.batch.setColor(0.10f, 0.22f, 0.15f, 0.92f);
        juego.batch.draw(juego.recursos.pixel, r.x, r.y, r.width, r.height);
        juego.batch.setColor(0.83f, 0.66f, 0.23f, 1f);
        juego.batch.draw(juego.recursos.pixel, r.x, r.y, r.width, 3);
        juego.batch.draw(juego.recursos.pixel, r.x, r.y + r.height - 3, r.width, 3);
        juego.batch.setColor(Color.WHITE);

        juego.fuente.setColor(new Color(0.96f, 0.92f, 0.80f, 1f));
        renglon.setText(juego.fuente, etiqueta);
        juego.fuente.draw(juego.batch, renglon,
            r.x + (r.width - renglon.width) / 2,
            r.y + (r.height + renglon.height) / 2);
    }

    private void centrar(com.badlogic.gdx.graphics.g2d.BitmapFont f, String texto, float y) {
        renglon.setText(f, texto);
        f.draw(juego.batch, renglon, (Main.ANCHO - renglon.width) / 2, y);
    }

    @Override
    public void alHacerClic(float x, float y) {
        if (botonJugar.contains(x, y)) {
            juego.recursos.sonar(juego.recursos.clic);
            juego.setScreen(new PantallaJuego(juego));
            dispose();
        } else if (botonSalir.contains(x, y)) {
            juego.recursos.sonar(juego.recursos.clic);
            Gdx.app.exit();
        }
    }

    @Override
    public void alPresionarTecla(int tecla) {
        if (tecla == Input.Keys.M) juego.recursos.alternarSilencio();
        if (tecla == Input.Keys.PLUS || tecla == Input.Keys.EQUALS) juego.recursos.cambiarVolumen(0.1f);
        if (tecla == Input.Keys.MINUS) juego.recursos.cambiarVolumen(-0.1f);
        if (tecla == Input.Keys.ESCAPE) Gdx.app.exit();
    }

    @Override public void resize(int ancho, int alto) { viewport.update(ancho, alto, true); }
    @Override public void pause()  { }
    @Override public void resume() { }
    @Override public void hide()   { }
    @Override public void dispose(){ }
}
