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

Para la persistencia de datos se utilizará SQLite junto con JDBC.

La base de datos permitirá almacenar información relacionada con los jugadores,
partidas y estadísticas del juego.

Las tablas previstas son:

- `Jugadores`
- `Partidas`
- `EstadisticasJugador`
- `HistorialCantos`
- `Configuracion`

Las tablas tendrán sus respectivas claves primarias y, cuando corresponda,
claves foráneas para establecer las relaciones entre jugadores, partidas y
estadísticas.

Las operaciones previstas incluyen:

- `INSERT` para registrar nuevos jugadores, partidas y estadísticas.
- `SELECT` para consultar información.
- `UPDATE` para modificar datos existentes.
- `DELETE` para eliminar datos cuando sea necesario.

La implementación de la base de datos se realizará mediante SQLite y la
conexión con Java se realizará utilizando JDBC.


### Información a persistir

* Nombre del jugador.
* Partidas jugadas.
* Partidas ganadas.
* Partidas perdidas.
* Dificultad seleccionada.
* Puntajes obtenidos.
* Cantidad de veces que se cantó Truco.
* Cantidad de veces que se cantó Envido.
* Cantidad de veces que se utilizó Ultra Envido.
* Cantidad de veces que se utilizó Vale 6.
* Mejor racha de victorias.
* Historial de partidas.
* Configuraciones seleccionadas.

### Tablas previstas

* Jugadores
* Partidas
* EstadisticasJugador
* HistorialCantos
* Configuracion

### Operaciones SQL previstas

* INSERT: para guardar nuevos jugadores, partidas y estadísticas.
* SELECT: para consultar información y estadísticas.
* UPDATE: para actualizar estadísticas y configuraciones.
* DELETE: para eliminar registros cuando sea necesario.

## Requisitos

Para ejecutar el proyecto se necesita:

* JDK 21 instalado.
* Git instalado.
* Windows para utilizar el comando de ejecución indicado.

## Instalación y ejecución

Clonar el repositorio:

```bash
git clone https://github.com/melissaalexandramaldonado/ProyectoTruco.git
cd ProyectoTruco
```

Ejecutar el proyecto en Windows:

```bash
gradlew.bat lwjgl3:run
```

El proyecto se ejecutará mediante el backend LWJGL3 para escritorio.

## Wiki

La documentación completa del proyecto y la propuesta formal se encuentran en la [Wiki del proyecto](https://github.com/melissaalexandramaldonado/ProyectoTruco/wiki).




