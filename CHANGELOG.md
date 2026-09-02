# Changelog

## [1.1.0] - 2026-09-02

### Added

* Prototipo jugable del Truco Argentino contra una IA, con partidas a 15 puntos.
* Clase `Carta` con la jerarquia completa del truco y `Mazo` con mezclado Fisher-Yates.
* Clase `ControlEntrada` basada en `InputAdapter` para procesar mouse y teclado, con conversion de coordenadas de pantalla a coordenadas del mundo.
* Seleccion de cartas por area rectangular, con realce visual al pasar el mouse.
* Animacion de giro de carta a partir de un spritesheet de 8 cuadros usando la clase `Animation`.
* Gestion de pantallas con `Game` y `Screen`: menu, juego y fin de partida, mas los estados de juego en curso y pausa.
* Camara ortografica con `FitViewport` para el mundo y una segunda para el HUD, adaptables a cualquier tamano de ventana.
* HUD fijo con puntaje, numero de baza, puntos en juego y estado del sonido.
* IA rival con decision probabilistica basada en la evaluacion de sus propias cartas.
* Mecanicas propias de apuesta: cantar Truco y Vale 6, con respuesta de la IA.
* Musica de fondo en bucle y efectos de sonido para repartir, tirar carta, clic y victoria.
* Controles de volumen y silencio.
* Recursos graficos propios: mazo espanol de 40 cartas, dorso, spritesheet de animacion y fondo de mesa.

### Changed

* `Main` pasa de extender `ApplicationAdapter` a extender `Game` para permitir el cambio de pantallas.
* Resolucion de la ventana ampliada de 640x480 a 1280x720.
* Carga de recursos centralizada en la clase `Recursos`, con liberacion de memoria en `dispose()`.

### Fixed

* El silencio ahora tambien detiene los efectos que ya estaban sonando.
* Efecto de reparto acortado a 1,5 segundos.
* Correccion de la superposicion entre el mensaje del HUD y las cartas del rival.

## [1.0.1] - 2026-09-01
### Changed
* Se amplio el README con la plataforma de desarrollo objetivo, los requisitos previos y las instrucciones de clonado y ejecucion para Windows, Linux y macOS.
* Se amplio la descripcion del videojuego.

### Fixed
* Se corrigio el enlace a la Propuesta del Proyecto en el README, que no estaba escrito como enlace valido.
* Se unifico la propuesta en la Wiki: la version completa quedo en la pagina "Propuesta del Proyecto" y el Home paso a ser el indice.

## [1.0.0] - 2026-07-17
### Added
* Estructura inicial del proyecto base utilizando LibGDX Liftoff con Java 21.
* Configuración del entorno de desarrollo en IntelliJ IDEA y repositorio Git público.
* Creación de la documentación obligatoria (Propuesta en la Wiki y README detallado).
