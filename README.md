# 3DS

Maqueta de una consola Nintendo 3DS dibujada 100 % con **Jetpack Compose** (Kotlin) para Android, sin imágenes ni recursos gráficos externos: todo el aspecto de la consola, las dos pantallas y los botones se genera con código.

![Captura de la app 3DS](docs/captura-app.png)

## Proceso

Usé **Figma** como base para tener las ideas claras: ahí definí la estructura de la consola, las zonas de cada pantalla, las medidas y la paleta de colores antes de escribir código. Después lo traduje a Jetpack Compose, dividiendo cada zona en un componente independiente, y lo probé con `@Preview` y en móvil y tablet.

## Cómo funciona el código

- `MainActivity.kt`: punto de entrada, activa pantalla completa y muestra `Game()`.
- `ui/theme/game/game.kt`: toda la consola.
  - **Unidad de escala `u`:** se calcula a partir del espacio disponible (`BoxWithConstraints`), de modo que el diseño se escala sin deformarse en cualquier pantalla.
  - **Piezas base reutilizables:** `Rectangulo`, `Circulo` y `Texto` colocan cualquier elemento por posición y tamaño.
  - **Composables por zona:** `Cabecera`, `Altavoz`, `PantallaSuperior`, `Escena`, `Bisagra`, `PantallaInferior` y `Controles`.
- `ui/theme/`: tema de Material 3 generado por Android Studio (`Color.kt`, `Theme.kt`, `Type.kt`).

## Tecnologías

Kotlin · Jetpack Compose · Material 3 · Gradle (Kotlin DSL) · Android Studio · Figma

## Ejecutar el proyecto

1. Clona el repositorio y ábrelo con Android Studio.
2. Sincroniza Gradle.
3. Ejecuta la app en un emulador o dispositivo Android.
