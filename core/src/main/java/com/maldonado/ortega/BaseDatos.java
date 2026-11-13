
package com.maldonado.ortega;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {

    private static final String URL = "jdbc:sqlite:truco.db";

    private Connection conexion;

    public void conectar() {
        try {
            conexion = DriverManager.getConnection(URL);

            try (Statement sentencia = conexion.createStatement()) {
                sentencia.execute("PRAGMA foreign_keys = ON");
            }

            crearTablas();

            System.out.println("Base de datos conectada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private void crearTablas() throws SQLException {

        try (Statement sentencia = conexion.createStatement()) {

            sentencia.execute("""
                CREATE TABLE IF NOT EXISTS Jugadores (
                    id_jugador INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL
                )
            """);

            sentencia.execute("""
                CREATE TABLE IF NOT EXISTS Partidas (
                    id_partida INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_jugador INTEGER NOT NULL,
                    fecha TEXT NOT NULL,
                    dificultad TEXT NOT NULL,
                    resultado TEXT NOT NULL,
                    puntos_jugador INTEGER NOT NULL,
                    puntos_ia INTEGER NOT NULL,
                    FOREIGN KEY (id_jugador)
                        REFERENCES Jugadores(id_jugador)
                )
            """);

            sentencia.execute("""
                CREATE TABLE IF NOT EXISTS EstadisticasJugador (
                    id_estadistica INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_jugador INTEGER NOT NULL UNIQUE,
                    partidas_jugadas INTEGER NOT NULL DEFAULT 0,
                    partidas_ganadas INTEGER NOT NULL DEFAULT 0,
                    partidas_perdidas INTEGER NOT NULL DEFAULT 0,
                    FOREIGN KEY (id_jugador)
                        REFERENCES Jugadores(id_jugador)
                )
            """);

            sentencia.execute("""
                CREATE TABLE IF NOT EXISTS HistorialCantos (
                    id_canto INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_partida INTEGER NOT NULL,
                    jugador TEXT NOT NULL,
                    canto TEXT NOT NULL,
                    respuesta TEXT,
                    FOREIGN KEY (id_partida)
                        REFERENCES Partidas(id_partida)
                )
            """);

            sentencia.execute("""
                CREATE TABLE IF NOT EXISTS Configuracion (
                    id_configuracion INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_jugador INTEGER NOT NULL UNIQUE,
                    volumen INTEGER NOT NULL DEFAULT 100,
                    sonido_activado INTEGER NOT NULL DEFAULT 1,
                    FOREIGN KEY (id_jugador)
                        REFERENCES Jugadores(id_jugador),
                    CHECK (volumen BETWEEN 0 AND 100),
                    CHECK (sonido_activado IN (0, 1))
                )
            """);
        }
    }

    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Base de datos desconectada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar: " + e.getMessage());
        }
    }
}

