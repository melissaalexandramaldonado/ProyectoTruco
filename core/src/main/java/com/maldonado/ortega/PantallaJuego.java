package com.maldonado.ortega;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaJuego implements Screen, EscuchaEntrada {

    public enum Estado { JUGANDO, PAUSA }

    private final Main juego;
    private final OrthographicCamera camara;
    private final FitViewport viewport;
    private final GlyphLayout renglon = new GlyphLayout();

    private Estado estado = Estado.JUGANDO;

    public PantallaJuego(Main juego) {
        this.juego = juego;
        camara = new OrthographicCamera();
        viewport = new FitViewport(Main.ANCHO, Main.ALTO, camara);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new ControlEntrada(viewport, this));
        juego.recursos.sonar(juego.recursos.repartir);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        juego.batch.setProjectionMatrix(camara.combined);

        juego.batch.begin();
        juego.batch.draw(juego.recursos.mesa, 0, 0, Main.ANCHO, Main.ALTO);

        juego.fuente.setColor(Color.WHITE);
        centrar("Mesa de juego lista - las cartas llegan en la proxima etapa", Main.ALTO / 2);
        centrar("P pausa   -   ESC volver al menu   -   M silencia", 60);

        if (estado == Estado.PAUSA) {
            juego.batch.setColor(0f, 0f, 0f, 0.65f);
            juego.batch.draw(juego.recursos.pixel, 0, 0, Main.ANCHO, Main.ALTO);
            juego.batch.setColor(Color.WHITE);

            juego.fuenteTitulo.setColor(new Color(0.95f, 0.88f, 0.70f, 1f));
            renglon.setText(juego.fuenteTitulo, "PAUSA");
            juego.fuenteTitulo.draw(juego.batch, renglon,
                (Main.ANCHO - renglon.width) / 2, Main.ALTO / 2 + 60);

            juego.fuente.setColor(Color.WHITE);
            centrar("Apreta P para continuar", Main.ALTO / 2 - 30);
        }

        juego.batch.end();
    }

    private void centrar(String texto, float y) {
        renglon.setText(juego.fuente, texto);
        juego.fuente.draw(juego.batch, renglon, (Main.ANCHO - renglon.width) / 2, y);
    }

    @Override
    public void alHacerClic(float x, float y) {
        // En la proxima etapa: seleccionar cartas.
    }

    @Override
    public void alPresionarTecla(int tecla) {
        if (tecla == Input.Keys.P) {
            estado = (estado == Estado.JUGANDO) ? Estado.PAUSA : Estado.JUGANDO;
            juego.recursos.sonar(juego.recursos.clic);
        }
        if (tecla == Input.Keys.ESCAPE) {
            juego.setScreen(new PantallaMenu(juego));
            dispose();
        }
        if (tecla == Input.Keys.M) juego.recursos.alternarSilencio();
        if (tecla == Input.Keys.PLUS || tecla == Input.Keys.EQUALS) juego.recursos.cambiarVolumen(0.1f);
        if (tecla == Input.Keys.MINUS) juego.recursos.cambiarVolumen(-0.1f);
    }

    @Override public void resize(int ancho, int alto) { viewport.update(ancho, alto, true); }
    @Override public void pause()  { estado = Estado.PAUSA; }
    @Override public void resume() { }
    @Override public void hide()   { }
    @Override public void dispose(){ }
}
