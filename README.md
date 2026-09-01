# TrucoArgentino2D

### Integrantes:
* Melissa Alexandra Maldonado
* Tomás Lautaro Ortega

### Descripción del Videojuego:
Adaptación digital del Truco Argentino en 2D para PC. El jugador se enfrenta 1 contra 1
contra una IA, con mazo español de 40 cartas y partidas a 30 puntos. Además de las apuestas
clásicas (Envido, Real Envido, Falta Envido, Truco, Retruco y Vale Cuatro), el juego suma dos
variantes propias: "Ultra Envido" (7 puntos) y "Vale 6" (6 puntos). Estética pixel art y
control por mouse.

### Tecnologías:
* LibGDX 1.14.0
* Java 21
* Gradle (wrapper incluido en el repositorio)
* IntelliJ IDEA
* Plataforma objetivo: Escritorio (Windows, Linux y macOS) mediante el módulo LWJGL3

### Documentación:
* [Propuesta del Proyecto (Wiki)](https://github.com/melissaalexandramaldonado/ProyectoTruco/wiki/Propuesta-del-Proyecto)
* [Registro de cambios (CHANGELOG.md)](CHANGELOG.md)

### Estructura del Proyecto:
* `core/`: contiene la lógica principal del videojuego, clases de las cartas, mecánicas del truco y control de pantallas.
* `lwjgl3/`: módulo de escritorio encargado del lanzamiento y configuración de la ventana principal en PC usando LWJGL3.
* `assets/`: almacena los recursos visuales (imágenes, texturas de las cartas) y sonoros del juego.

### Requisitos previos:
* JDK 21 o superior (verificar con `java -version`).
* Git instalado.
* No es necesario instalar Gradle: el proyecto incluye el Gradle Wrapper.

### Cómo compilar y ejecutar:

1. Clonar el repositorio:

```bash
git clone https://github.com/melissaalexandramaldonado/ProyectoTruco.git
cd ProyectoTruco
```

2. Ejecutar el juego desde la terminal.

En Linux o macOS:

```bash
./gradlew lwjgl3:run
```

En Windows:

```bash
gradlew.bat lwjgl3:run
```

3. Alternativa desde el IDE: abrir la carpeta del proyecto en IntelliJ IDEA como proyecto
Gradle, esperar a que termine la importación y ejecutar la clase `Lwjgl3Launcher` del
módulo `lwjgl3`.
