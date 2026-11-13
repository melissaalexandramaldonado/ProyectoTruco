package com.maldonado.ortega;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.List;

public class PantallaJuego implements Screen, EscuchaEntrada {

    public enum Estado { JUGANDO, ANIMANDO, RESOLVIENDO, PAUSA }

    private final Main juego;

    private final OrthographicCamera camaraMundo = new OrthographicCamera();
    private final FitViewport viewportMundo = new FitViewport(Main.ANCHO, Main.ALTO, camaraMundo);
    private final OrthographicCamera camaraHud = new OrthographicCamera();
    private final FitViewport viewportHud = new FitViewport(Main.ANCHO, Main.ALTO, camaraHud);

    private final GlyphLayout renglon = new GlyphLayout();
    private final Vector3 mouse = new Vector3();

    private Estado estado = Estado.JUGANDO;
    private Estado estadoPrevio = Estado.JUGANDO;

    private final Mazo mazo = new Mazo();
    private final JugadorIA ia = new JugadorIA();

    private List<Carta> manoJugador;
    private List<Carta> manoIA;
    private Carta cartaJugador, cartaIA;

    private int puntosJugador = 0, puntosIA = 0;
    private int ganadasJugador = 0, ganadasIA = 0, bazasJugadas = 0;
    private int puntosEnJuego = 1;
    private boolean trucoCantado = false, vale6Cantado = false;

    private float tiempoAnimacion = 0f;
    private float esperaResolucion = 0f;
    private String mensaje = "Tu turno: elegi una carta";

    private final Rectangle botonTruco = new Rectangle(940, 150, 300, 55);
    private final Rectangle botonVale6 = new Rectangle(940, 85, 300, 55);

    private static final float Y_MANO_JUGADOR = 20f;
    private static final float Y_MESA         = 255f;
    private static final float Y_MANO_IA      = 465f;
    private static final float ESCALA_IA      = 0.7f;

    public PantallaJuego(Main juego) {
        this.juego = juego;
        repartirNuevaMano();
    }

    private void repartirNuevaMano() {
        mazo.armar();
        mazo.mezclar();
        manoJugador = mazo.repartirMano(3);
        manoIA = mazo.repartirMano(3);
        cartaJugador = null;
        cartaIA = null;
        ganadasJugador = 0;
        ganadasIA = 0;
        bazasJugadas = 0;
        puntosEnJuego = 1;
        trucoCantado = false;
        vale6Cantado = false;
        estado = Estado.JUGANDO;
        mensaje = "Nueva mano. Elegi una carta";
        acomodarManoJugador();
        juego.recursos.sonar(juego.recursos.repartir);
    }

    private void acomodarManoJugador() {
        float separacion = 40f;
        float total = manoJugador.size() * Carta.ANCHO + (manoJugador.size() - 1) * separacion;
        float x = (Main.ANCHO - total) / 2f;
        for (Carta c : manoJugador) {
            c.posicionar(x, Y_MANO_JUGADOR);
            x += Carta.ANCHO + separacion;
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new ControlEntrada(viewportMundo, this));
    }

    @Override
    public void render(float delta) {
        actualizar(delta);

        ScreenUtils.clear(Color.BLACK);

        viewportMundo.apply();
        juego.batch.setProjectionMatrix(camaraMundo.combined);
        juego.batch.begin();
        dibujarMundo();
        juego.batch.end();

        viewportHud.apply();
        juego.batch.setProjectionMatrix(camaraHud.combined);
        juego.batch.begin();
        dibujarHud();
        if (estado == Estado.PAUSA) dibujarPausa();
        juego.batch.end();
    }

    private void actualizar(float delta) {
        if (estado == Estado.PAUSA) return;

        if (estado == Estado.ANIMANDO) {
            tiempoAnimacion += delta;
            if (juego.recursos.animacionGiro.isAnimationFinished(tiempoAnimacion)) {
                jugarLaIA();
            }
        } else if (estado == Estado.RESOLVIENDO) {
            esperaResolucion -= delta;
            if (esperaResolucion <= 0f) resolverBaza();
        }
    }

    private void jugarLaIA() {
        cartaIA = ia.elegirCarta(manoIA, cartaJugador);
        manoIA.remove(cartaIA);
        juego.recursos.sonar(juego.recursos.tirar);
        estado = Estado.RESOLVIENDO;
        esperaResolucion = 1.3f;
        mensaje = "La IA jugo el " + cartaIA.getNombre();
    }

    private void resolverBaza() {
        int resultado = ReglasTruco.compararCartas(cartaJugador, cartaIA);
        bazasJugadas++;

        if (resultado == ReglasTruco.GANA_JUGADOR) {
            ganadasJugador++;
            mensaje = "Ganaste la baza con el " + cartaJugador.getNombre();
            juego.recursos.sonar(juego.recursos.gana);
        } else if (resultado == ReglasTruco.GANA_IA) {
            ganadasIA++;
            mensaje = "La IA gano la baza con el " + cartaIA.getNombre();
        } else {
            mensaje = "Parda: las dos cartas valen lo mismo";
        }

        cartaJugador = null;
        cartaIA = null;

        if (ReglasTruco.manoTerminada(ganadasJugador, ganadasIA, bazasJugadas)) {
            terminarMano();
        } else {
            estado = Estado.JUGANDO;
        }
    }

    private void terminarMano() {
        int ganador = ReglasTruco.ganadorDeLaMano(ganadasJugador, ganadasIA);
        if (ganador == ReglasTruco.GANA_JUGADOR) {
            puntosJugador += puntosEnJuego;
            mensaje = "Ganaste la mano: +" + puntosEnJuego;
            juego.recursos.sonar(juego.recursos.gana);
        } else {
            puntosIA += puntosEnJuego;
            mensaje = "La IA gano la mano: +" + puntosEnJuego;
        }
        verificarFinDePartida();
    }

    private void verificarFinDePartida() {
        if (puntosJugador >= ReglasTruco.PUNTOS_PARA_GANAR || puntosIA >= ReglasTruco.PUNTOS_PARA_GANAR) {
            juego.setScreen(new PantallaFin(juego, puntosJugador, puntosIA));
        } else {
            repartirNuevaMano();
        }
    }

    private void cantar(boolean esVale6) {
        int propuesta = esVale6 ? 6 : 2;
        boolean acepta = ia.aceptaApuesta(manoIA, propuesta);
        juego.recursos.sonar(juego.recursos.clic);

        if (acepta) {
            puntosEnJuego = propuesta;
            mensaje = "La IA dijo QUIERO. La mano vale " + propuesta;
            if (esVale6) vale6Cantado = true; else trucoCantado = true;
        } else {
            mensaje = "La IA dijo NO QUIERO. Te llevas " + puntosEnJuego;
            puntosJugador += puntosEnJuego;
            verificarFinDePartida();
        }
    }

    private void dibujarMundo() {
        juego.batch.draw(juego.recursos.mesa, 0, 0, Main.ANCHO, Main.ALTO);

        for (int i = 0; i < manoIA.size(); i++) {
            float ancho = Carta.ANCHO * ESCALA_IA;
            float alto  = Carta.ALTO  * ESCALA_IA;
            float sep = 30f;
            float total = manoIA.size() * ancho + (manoIA.size() - 1) * sep;
            float x = (Main.ANCHO - total) / 2f + i * (ancho + sep);
            juego.batch.draw(juego.recursos.dorso, x, Y_MANO_IA, ancho, alto);
        }

        if (estado == Estado.ANIMANDO && cartaJugador != null) {
            TextureRegion cuadro = juego.recursos.animacionGiro.getKeyFrame(tiempoAnimacion, false);
            juego.batch.draw(cuadro, Main.ANCHO / 2f - 170, Y_MESA);
        } else if (cartaJugador != null) {
            juego.batch.draw(region(cartaJugador), Main.ANCHO / 2f - 170, Y_MESA);
        }

        if (cartaIA != null) {
            juego.batch.draw(region(cartaIA), Main.ANCHO / 2f + 30, Y_MESA);
        }

        mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewportMundo.unproject(mouse);

        for (Carta c : manoJugador) {
            float levantar = (estado == Estado.JUGANDO && c.contiene(mouse.x, mouse.y)) ? 25f : 0f;
            juego.batch.draw(region(c), c.getArea().x, c.getArea().y + levantar);
        }
    }

    private TextureRegion region(Carta c) {
        return juego.recursos.regionesCartas[c.getFila()][c.getColumna()];
    }

    private void dibujarHud() {
        juego.batch.setColor(0f, 0f, 0f, 0.6f);
        juego.batch.draw(juego.recursos.pixel, 0, Main.ALTO - 100, Main.ANCHO, 100);
        juego.batch.setColor(0.83f, 0.66f, 0.23f, 1f);
        juego.batch.draw(juego.recursos.pixel, 0, Main.ALTO - 103, Main.ANCHO, 3);
        juego.batch.setColor(Color.WHITE);

        juego.fuente.setColor(new Color(0.96f, 0.92f, 0.80f, 1f));
        juego.fuente.draw(juego.batch, "VOS  " + puntosJugador, 40, Main.ALTO - 28);
        juego.fuente.draw(juego.batch, "IA  " + puntosIA, 220, Main.ALTO - 28);
        juego.fuente.draw(juego.batch, "Baza " + Math.min(bazasJugadas + 1, 3) + "/3", 380, Main.ALTO - 28);
        juego.fuente.draw(juego.batch, "En juego: " + puntosEnJuego, 540, Main.ALTO - 28);
        juego.fuente.draw(juego.batch, "A " + ReglasTruco.PUNTOS_PARA_GANAR + " puntos", 760, Main.ALTO - 28);
        juego.fuente.draw(juego.batch,
            juego.recursos.estaSilenciado() ? "SILENCIADO (M)" : "Vol " + juego.recursos.volumenPorcentaje() + "% (M)",
            1000, Main.ALTO - 28);

        juego.fuente.setColor(Color.WHITE);
        renglon.setText(juego.fuente, mensaje);
        juego.fuente.draw(juego.batch, renglon, (Main.ANCHO - renglon.width) / 2f, Main.ALTO - 62);

        if (estado == Estado.JUGANDO && !trucoCantado) {
            dibujarBoton(botonTruco, "CANTAR TRUCO");
        } else if (estado == Estado.JUGANDO && !vale6Cantado) {
            dibujarBoton(botonVale6, "VALE 6");
        }

        juego.fuente.setColor(new Color(0.8f, 0.8f, 0.8f, 1f));
        juego.fuente.draw(juego.batch, "P pausa   ESC menu   + / - volumen", 40, 40);
    }

    private void dibujarBoton(Rectangle r, String etiqueta) {
        juego.batch.setColor(0.10f, 0.22f, 0.15f, 0.92f);
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

    private void dibujarPausa() {
        juego.batch.setColor(0f, 0f, 0f, 0.7f);
        juego.batch.draw(juego.recursos.pixel, 0, 0, Main.ANCHO, Main.ALTO);
        juego.batch.setColor(Color.WHITE);
        juego.fuenteTitulo.setColor(new Color(0.95f, 0.88f, 0.70f, 1f));
        renglon.setText(juego.fuenteTitulo, "PAUSA");
        juego.fuenteTitulo.draw(juego.batch, renglon, (Main.ANCHO - renglon.width) / 2f, Main.ALTO / 2f + 60);
        juego.fuente.setColor(Color.WHITE);
        renglon.setText(juego.fuente, "Apreta P para continuar");
        juego.fuente.draw(juego.batch, renglon, (Main.ANCHO - renglon.width) / 2f, Main.ALTO / 2f - 20);
    }

    @Override
    public void alHacerClic(float x, float y) {
        if (estado != Estado.JUGANDO) return;

        if (!trucoCantado && botonTruco.contains(x, y)) { cantar(false); return; }
        if (trucoCantado && !vale6Cantado && botonVale6.contains(x, y)) { cantar(true); return; }

        for (Carta c : manoJugador) {
            if (c.contiene(x, y)) {
                cartaJugador = c;
                manoJugador.remove(c);
                acomodarManoJugador();
                juego.recursos.sonar(juego.recursos.tirar);
                tiempoAnimacion = 0f;
                estado = Estado.ANIMANDO;
                mensaje = "Jugaste el " + c.getNombre();
                return;
            }
        }
    }

    @Override
    public void alPresionarTecla(int tecla) {
        if (tecla == Input.Keys.P) {
            if (estado == Estado.PAUSA) {
                estado = estadoPrevio;
            } else {
                estadoPrevio = estado;
                estado = Estado.PAUSA;
            }
            juego.recursos.sonar(juego.recursos.clic);
        }
        if (tecla == Input.Keys.ESCAPE) juego.setScreen(new PantallaMenu(juego));
        if (tecla == Input.Keys.M) juego.recursos.alternarSilencio();
        if (tecla == Input.Keys.PLUS || tecla == Input.Keys.EQUALS) juego.recursos.cambiarVolumen(0.1f);
        if (tecla == Input.Keys.MINUS) juego.recursos.cambiarVolumen(-0.1f);
    }

    @Override
    public void resize(int ancho, int alto) {
        viewportMundo.update(ancho, alto, true);
        viewportHud.update(ancho, alto, true);
    }

    @Override public void pause()   { estadoPrevio = estado; estado = Estado.PAUSA; }
    @Override public void resume()  { }
    @Override public void hide()    { }
    @Override public void dispose() { }
}
