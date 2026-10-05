# TrucoArgentino2D

### Integrantes:
* Melissa Alexandra Maldonado
* Tomas Lautaro Ortega
# Truco Argentino en 2D

## Descripción

Truco Argentino en 2D es un videojuego de cartas basado en el clásico Truco Argentino, desarrollado para PC en modalidad singleplayer.

El jugador se enfrentará a un oponente controlado por inteligencia artificial. El juego incorpora las reglas tradicionales del Truco y dos variantes creadas por el equipo: Ultra Envido y Vale 6.

El proyecto busca aplicar conocimientos de Programación Orientada a Objetos, lógica algorítmica, inteligencia artificial, interfaces gráficas y bases de datos SQL.

## Tecnologías utilizadas

* Java 21
* LibGDX 1.14.0
* LWJGL3
* SQLite
* JDBC
* Gradle
* Git
* GitHub

### Plataforma objetivo

- Escritorio: Windows, Linux y macOS (implementado mediante LWJGL3).
- Web: no contemplada en esta etapa.
- Móvil: no contemplada en esta etapa.

### Base de datos

El proyecto utilizará SQLite como motor de base de datos y JDBC para realizar la conexión entre Java y SQLite.

La base de datos permitirá almacenar información de los jugadores, las partidas realizadas, las estadísticas y el historial de los cantos realizados durante las partidas.

Información a persistir

Se almacenará la siguiente información:

Nombre del jugador.
Partidas jugadas.
Partidas ganadas.
Partidas perdidas.
Dificultad seleccionada.
Resultado de cada partida.
Puntajes obtenidos por el jugador y la IA.
Historial de cantos realizados durante las partidas.
Configuración de sonido y volumen.
Tablas

La base de datos estará formada por las siguientes tablas:

Jugadores

id_jugador: INTEGER, clave primaria.
nombre: TEXT.

Partidas

id_partida: INTEGER, clave primaria.
id_jugador: INTEGER, clave foránea hacia Jugadores.
fecha: TEXT.
dificultad: TEXT.
resultado: TEXT.
puntos_jugador: INTEGER.
puntos_ia: INTEGER.

EstadisticasJugador

id_estadistica: INTEGER, clave primaria.
id_jugador: INTEGER, clave foránea hacia Jugadores.
partidas_jugadas: INTEGER.
partidas_ganadas: INTEGER.
partidas_perdidas: INTEGER.

HistorialCantos

id_canto: INTEGER, clave primaria.
id_partida: INTEGER, clave foránea hacia Partidas.
jugador: TEXT.
canto: TEXT.
respuesta: TEXT.

Configuracion

id_configuracion: INTEGER, clave primaria.
volumen: INTEGER.
sonido_activado: INTEGER.
Relaciones
Un jugador puede participar en muchas partidas.
Un jugador tiene un registro de estadísticas.
Una partida puede tener muchos cantos.
Cada partida pertenece a un jugador.
Cada registro de estadísticas pertenece a un jugador.
Cada canto pertenece a una partida.
Operaciones SQL previstas

Se utilizarán las siguientes operaciones:

INSERT: registrar jugadores, partidas, estadísticas y cantos.
SELECT: consultar jugadores, partidas, estadísticas e historial.
UPDATE: actualizar estadísticas y configuraciones.
DELETE: eliminar registros cuando sea necesario.

La conexión entre Java y SQLite se realizará mediante JDBC.

## Wiki

La documentación completa del proyecto y la propuesta formal se encuentran en la [Wiki del proyecto](https://github.com/melissaalexandramaldonado/ProyectoTruco/wiki).




