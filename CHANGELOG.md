# Changelog

## [0.1.0] - 2026-09-02

### Agregado

* Estructura inicial del proyecto generada con LibGDX Liftoff sobre Java 21, con los módulos `core` y `lwjgl3`.
* Archivo `.gitignore` para excluir archivos generados y temporales del proyecto.
* Documentación del proyecto: `README.md`, `CHANGELOG.md` y la propuesta formal completa en la Wiki.
* Planificación de la base de datos con SQLite y JDBC: tablas, campos, claves, relaciones y operaciones SQL previstas.
* Prototipo jugable del Truco Argentino contra una IA, con partidas a 15 puntos.
* Clase `Carta` con la jerarquía completa del truco y clase `Mazo` con mezclado Fisher-Yates.
* Clase `ControlEntrada` basada en `InputAdapter` para procesar mouse y teclado, con conversión de coordenadas de pantalla a coordenadas del mundo.
* Selección de cartas por área rectangular, con realce visual al pasar el mouse.
* Animación de giro de carta a partir de un spritesheet de 8 cuadros usando la clase `Animation`.
* Gestión de pantallas con `Game` y `Screen`: menú, juego y fin de partida, más los estados de juego en curso y pausa.
* Cámara ortográfica con `FitViewport` para el mundo y una segunda para el HUD, adaptables a cualquier tamaño de ventana.
* HUD fijo con puntaje, número de baza, puntos en juego y estado del sonido.
* IA rival con decisión probabilística basada en la evaluación de sus propias cartas.
* Mecánicas propias de apuesta: cantar Truco y Vale 6, con respuesta de la IA.
* Música de fondo en bucle y efectos de sonido para repartir, tirar carta, clic y victoria.
* Controles de volumen y silencio.
* Recursos gráficos propios: mazo español de 40 cartas, dorso, spritesheet de animación y fondo de mesa.

### Modificado

* `Main` pasa de extender `ApplicationAdapter` a extender `Game` para permitir el cambio de pantallas.
* Resolución de la ventana ampliada de 640x480 a 1280x720.
* Carga de recursos centralizada en la clase `Recursos`, con liberación de memoria en `dispose()`.

### Arreglado

* El silencio ahora también detiene los efectos que ya estaban sonando.
* Efecto de reparto acortado a 1,5 segundos.
* Corrección de la superposición entre el mensaje del HUD y las cartas del rival.
