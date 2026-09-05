# TrucoArgentino2D

### Integrantes:
* Melissa Alexandra Maldonado
* Tomas Lautaro Ortega

### Descripcion del Videojuego:
Adaptacion digital del Truco Argentino en 2D para PC. El jugador se enfrenta uno contra uno
a una inteligencia artificial, con el mazo espanol de 40 cartas y partidas a 30 puntos. El
juego incluye las apuestas clasicas (Envido, Real Envido, Falta Envido, Truco, Retruco y
Vale Cuatro) y suma dos variantes propias: "Ultra Envido" y "Vale 6". La estetica es pixel
art y el juego se controla con el mouse.

## Video de demostración
https://drive.google.com/file/d/1WEVFCH_as3rALLEE5G7-uMiSR8bvnFz1/view

### Tecnologias:
* LibGDX 1.14.0
* Java 21
* Gradle (con wrapper incluido en el repositorio)
* IntelliJ IDEA
* Plataforma objetivo: Escritorio (Windows, Linux y macOS) a traves del modulo LWJGL3

### Documentacion:
* [Propuesta del Proyecto](https://github.com/melissaalexandramaldonado/ProyectoTruco/wiki/Propuesta-del-Proyecto)
* [Registro de cambios](CHANGELOG.md)

### Estructura del Proyecto:
* `core/`: contiene la logica principal del videojuego, las clases de las cartas, las mecanicas del truco y el control de pantallas.
* `lwjgl3/`: modulo de escritorio encargado del lanzamiento y la configuracion de la ventana principal en PC usando LWJGL3.
* `assets/`: almacena los recursos visuales (imagenes y texturas de las cartas) y los sonidos del juego.

### Requisitos previos:
* Tener instalado el JDK 21 o superior. Se puede verificar ejecutando `java -version` en la terminal.
* Tener instalado Git.
* No hace falta instalar Gradle, porque el proyecto incluye el Gradle Wrapper.

### Como compilar y ejecutar:

1. Clonar el repositorio:

```bash
git clone https://github.com/melissaalexandramaldonado/ProyectoTruco.git
cd ProyectoTruco
```

2. Ejecutar el juego desde la terminal.

En Windows:

```bash
gradlew.bat lwjgl3:run
```

En Linux o macOS:

```bash
./gradlew lwjgl3:run
```

3. Tambien se puede ejecutar desde el IDE: abrir la carpeta del proyecto en IntelliJ IDEA
como proyecto Gradle, esperar a que termine la importacion y ejecutar la clase
`Lwjgl3Launcher` del modulo `lwjgl3`.



