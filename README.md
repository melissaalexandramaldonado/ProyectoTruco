# TrucoArgentino2D

### Integrantes:
* Melissa Maldonado y Tomas Ortega

### Descripción del Videojuego:
Un videojuego clásico de Truco Argentino en 2D desarrollado como proyecto escolar.

### Tecnologías:
* LibGDX (Java 21)
* IntelliJ IDEA

### Documentación:
* [https://github.com/melissaalexandramaldonado/ProyectoTruco/wiki/Propuesta-del-Proyecto]

  ### Estructura del Proyecto:
* `core/`: Contiene la lógica principal del videojuego, clases de las cartas, mecánicas del truco y control de pantallas.
* `lwjgl3/`: Módulo de escritorio encargado del lanzamiento y configuración de la ventana principal en PC usando LWJGL3.
* `assets/`: Almacena los recursos visuales (imágenes, texturas de las cartas) y sonoros del juego.

### Cómo ejecutar el proyecto:
Para correr el videojuego de forma local, clonar el repositorio y ejecutar el siguiente comando en la terminal de IntelliJ:
```bash
./gradlew lwjgl3:run
