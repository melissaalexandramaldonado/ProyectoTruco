package com.maldonado.ortega;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Recursos implements Disposable {

    public final Texture mesa, cartas, dorso, giro, pixel;
    public final Sound clic, repartir, tirar, gana;
    public final Music musica;

    private boolean silenciado = false;
    private float volumen = 0.7f;

    public Recursos() {
        mesa   = new Texture(Gdx.files.internal("imagenes/mesa.png"));
        cartas = new Texture(Gdx.files.internal("imagenes/cartas.png"));
        dorso  = new Texture(Gdx.files.internal("imagenes/dorso.png"));
        giro   = new Texture(Gdx.files.internal("imagenes/giro.png"));

        Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pm.setColor(Color.WHITE);
        pm.fill();
        pixel = new Texture(pm);
        pm.dispose();

        clic     = Gdx.audio.newSound(Gdx.files.internal("sonido/click.mp3"));
        repartir = Gdx.audio.newSound(Gdx.files.internal("sonido/repartir.mp3"));
        tirar    = Gdx.audio.newSound(Gdx.files.internal("sonido/tirar.mp3"));
        gana     = Gdx.audio.newSound(Gdx.files.internal("sonido/gana.mp3"));

        musica = Gdx.audio.newMusic(Gdx.files.internal("sonido/tango.mp3"));
        musica.setLooping(true);
        musica.setVolume(volumen * 0.4f);
        musica.play();
    }

    public void sonar(Sound sonido) {
        if (!silenciado) sonido.play(volumen);
    }

    public void alternarSilencio() {
        silenciado = !silenciado;
        musica.setVolume(silenciado ? 0f : volumen * 0.4f);
    }

    public void cambiarVolumen(float delta) {
        volumen = Math.max(0f, Math.min(1f, volumen + delta));
        if (!silenciado) musica.setVolume(volumen * 0.4f);
    }

    public boolean estaSilenciado() { return silenciado; }

    public int volumenPorcentaje() { return Math.round(volumen * 100); }

    @Override
    public void dispose() {
        mesa.dispose(); cartas.dispose(); dorso.dispose(); giro.dispose(); pixel.dispose();
        clic.dispose(); repartir.dispose(); tirar.dispose(); gana.dispose();
        musica.dispose();
    }
}
