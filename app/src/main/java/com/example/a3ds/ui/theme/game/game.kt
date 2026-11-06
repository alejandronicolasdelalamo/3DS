package com.example.a3ds.ui.theme.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a3ds.ui.theme._3DSTheme

// Todas las medidas están en "píxeles de la imagen de referencia".
// El lienzo mide 746 x 720 y se escala con la unidad u (dp por píxel).
private const val ANCHO = 746f
private const val ALTO = 720f

private val Negro = Color(0xFF111111)
private val Azul = Color(0xFF0050A1)
private val AzulOscuro = Color(0xFF003B80)
private val Gris = Color(0xFFD9D9D9)
private val Amarillo = Color(0xFFFFCC00)
private val Marron = Color(0xFFA66108)

/** Rectángulo colocado por posición (x, y) y tamaño (w, h).*/
@Composable
private fun Rectangulo(
    u: Dp, x: Float, y: Float, w: Float, h: Float, color: Color,
    forma: Shape = RectangleShape,
    borde: Color? = null,
    contenido: @Composable () -> Unit = {},
) {
    var m = Modifier.offset(u * x, u * y).size(u * w, u * h).clip(forma).background(color)
    if (borde != null) m = m.border(u * 1.5f, borde, forma)
    Box(m) { contenido() }
}

@Composable
private fun Circulo(u: Dp, cx: Float, cy: Float, d: Float, color: Color, borde: Color? = null) =
    Rectangulo(u, cx - d / 2, cy - d / 2, d, d, color, CircleShape, borde)

@Composable
private fun Texto(u: Dp, x: Float, y: Float, texto: String, tam: Float, color: Color, borde: Color? = null) {
    if (borde != null) {
        Text(
            texto,
            modifier = Modifier.offset(u * x, u * y),
            color = borde,
            fontSize = (u.value * tam).sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            style = TextStyle(drawStyle = Stroke(width = 5f, join = StrokeJoin.Round)),
        )
    }
    Text(
        texto,
        modifier = Modifier.offset(u * x, u * y),
        color = color,
        fontSize = (u.value * tam).sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
    )
}

// ---------- PANTALLA PRINCIPAL ----------
@Composable
fun Game(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier.fillMaxSize().background(Color(0xFF222222)).systemBarsPadding(),
        contentAlignment = Alignment.Center,
    ) {
        // Escala que cabe en ancho y alto sin deformar (móvil y tablet)
        val u = minOf(maxWidth / ANCHO, maxHeight / ALTO)

        Box(
            Modifier.size(u * ANCHO, u * ALTO)
                .clip(RoundedCornerShape(u * 30))
                .background(Azul)
                .border(u * 2, Negro, RoundedCornerShape(u * 30))
        ) {
            Cabecera(u)
            PantallaSuperior(u)
            Bisagra(u)
            PantallaInferior(u)
            Controles(u)
        }
    }
}

// Cámara, altavoces
@Composable
private fun Cabecera(u: Dp) {
    // Centrados entre el borde de arriba (y = 0) y la pantalla (y = 23)
    Circulo(u, 148f, 11.5f, 13f, Negro)
    Circulo(u, 374f, 11.5f, 14f, Color.White)
    Circulo(u, 599f, 11.5f, 13f, Negro)
    Altavoz(u, 42f, 154f)
    Altavoz(u, 704f, 154f)
}

// Altavoz
@Composable
private fun Altavoz(u: Dp, cx: Float, cy: Float) {
    val s = 9f
    val puntos = listOf(
        0 to -2, -1 to -1, 1 to -1, -2 to 0, 0 to 0, 2 to 0, -1 to 1, 1 to 1, 0 to 2,
    )
    puntos.forEach { (i, j) -> Circulo(u, cx + i * s, cy + j * s, 8f, Negro) }
}

// ---------- PANTALLA SUPERIOR ----------
@Composable
private fun PantallaSuperior(u: Dp) {
    Rectangulo(u, 85f, 23f, 575f, 351f, Negro) {
        // Pantalla del juego (offset 6,4 dentro del marco)
        Rectangulo(u, 6f, 4f, 563f, 342f, Color(0xFF08C5EF)) { Escena(u) }
    }
}

@Composable
private fun Escena(u: Dp) {
    val top = RoundedCornerShape(topStart = u * 14, topEnd = u * 14)

    // Nubes
    Rectangulo(u, 102f, 79f, 151f, 34f, Color(0xFFF0EEEE), CircleShape)
    Rectangulo(u, 390f, 25f, 90f, 31f, Color(0xFFF0EEEE), CircleShape)
    Rectangulo(u, 457f, 79f, 66f, 39f, Color(0xFFF0EEEE), CircleShape)

    // Colinas
    Rectangulo(u, 0f, 42f, 70f, 190f, Color(0xFF17CB06), top)
    Rectangulo(u, 267f, 113f, 88f, 120f, Color(0xFFBDC307), top)
    Rectangulo(u, 502f, 104f, 61f, 220f, Color(0xFFF37C7C), top)

    // Suelo
    Rectangulo(u, 0f, 224f, 563f, 90f, Color(0xFF0B8A00))
    Rectangulo(u, 0f, 314f, 563f, 11f, Color(0xFF00E02B))
    Rectangulo(u, 0f, 325f, 563f, 17f, Marron)

    // Bloques flotantes
    Rectangulo(u, 178f, 213f, 26f, 26f, Color(0xFF875105)) { Box(Modifier.fillMaxSize().border(0.5.dp, Negro)) }
    Rectangulo(u, 204f, 213f, 26f, 26f, Color(0xFF875105)) { Box(Modifier.fillMaxSize().border(0.5.dp, Negro)) }

    // Monedas en diagonal
    listOf(354f to 263f, 367f to 252f, 380f to 242f, 394f to 235f, 410f to 235f)
        .forEach { (x, y) -> Circulo(u, x, y, 13f, Amarillo) }

    // Mario simplificado: gorra + cuerpo
    Rectangulo(u, 97f, 260f, 28f, 12f, Color(0xFFE10C0C))   // gorra
    Rectangulo(u, 100f, 272f, 20f, 16f, Color(0xFFFFCC99))  // cara
    Rectangulo(u, 98f, 288f, 24f, 26f, Color(0xFFE10C0C))   // cuerpo

    // Tubo amarillo y bloques de la derecha
    Rectangulo(u, 435f, 239f, 17f, 40f, Color(0xFFF5FF00))
    Rectangulo(u, 452f, 252f, 4f, 17f, Color(0xFF17CB06))
    Rectangulo(u, 509f, 216f, 54f, 63f, Marron)
    Rectangulo(u, 425f, 279f, 132f, 63f, Color(0xFFB20B93))

    // HUD
    Circulo(u, 14f, 18f, 13f, Amarillo)
    Texto(u, 26f, 8f, "49", 14f, Color.White, Negro)
    Texto(u, 523f, 4f, "490", 14f, Color.White, Negro)
}

// ---------- BISAGRA ----------
@Composable
private fun Bisagra(u: Dp) {
    Rectangulo(u, 0f, 388f, 746f, 10f, Color(0xFF05234F))
    Rectangulo(u, 52f, 388f, 642f, 10f, Color(0xFF0A2F6B))
}

// ---------- PANTALLA INFERIOR ----------
@Composable
private fun PantallaInferior(u: Dp) {
    val redondeo = RoundedCornerShape(u * 12)

    Rectangulo(u, 144f, 411f, 459f, 277f, Negro) {
        Rectangulo(u, 5f, 5f, 449f, 269f, Color(0xFFF0F0F0)) {
            // Cabecera
            Texto(u, 24f, 10f, "WORLD 1.1", 22f, Amarillo)
            Circulo(u, 353f, 22f, 26f, Color(0xFFFF1010))
            Texto(u, 376f, 8f, "x005", 24f, Negro)

            // Barra de progreso con línea de puntos
            Rectangulo(u, 27f, 49f, 394f, 32f, Gris, RoundedCornerShape(u * 16)) {
                // Puntos de progreso
                for (i in 0 until 20) {
                    Circulo(u, 17f + i * 18.6f, 16f, 7f, Color(0xFF9A9A9A))
                }
                // Línea encima que une todos los puntos, del primero al último
                Rectangulo(u, 17f, 15f, 353.4f, 2f, Color(0xFF9A9A9A))
            }

            // Monedas grandes
            Circulo(u, 37f, 132f, 49f, Amarillo, Amarillo)
            Circulo(u, 90f, 132f, 49f, Gris, Amarillo)
            Circulo(u, 146f, 132f, 49f, Gris, Amarillo)

            // Score
            val bordeScore = Color(0xFF888888)
            Rectangulo(u, 53f, 185f, 111f, 15f, Gris, RoundedCornerShape(topStart = u * 8, topEnd = u * 8), bordeScore) {
                Texto(u, 38f, -1f, "Score", 10f, Color.Gray)
            }
            Rectangulo(u, 12f, 200f, 191f, 34f, Gris, redondeo, bordeScore) {
                Texto(u, 36f, 4f, "000009920", 22f, Color(0xFF666666))
            }

            // Caja derecha
            Rectangulo(u, 269f, 119f, 152f, 117f, Gris, RoundedCornerShape(u * 20))
        }
    }

    // SELECT / HOME / START
    listOf(144f to "SELECT", 298f to "HOME", 450f to "START").forEachIndexed { i, (x, t) ->
        val ancho = if (i == 1) 149f else 153f
        Rectangulo(u, x, 692f, ancho, 24f, Negro) {
            Rectangulo(u, 2f, 2f, ancho - 4f, 20f, Color(0xFF0A3F88))
            Texto(u, ancho / 2 - t.length * 4.5f, 5f, t, 14f, Color(0xFFE0E0E0))
        }
    }
}

// ---------- JOYSTICK, CRUCETA Y BOTONES ----------
@Composable
private fun Controles(u: Dp) {
    // Joystick
    Circulo(u, 66f, 485f, 66f, Negro)
    Circulo(u, 66f, 485f, 52f, Color(0xFF858484))

    // Cruceta
    Rectangulo(u, 38f, 586f, 56f, 14f, Negro)
    Rectangulo(u, 59f, 565f, 14f, 56f, Negro)
    // Líneas blancas de la cruceta (una por brazo)
    Rectangulo(u, 65f, 568f, 2f, 10f, Color.White)
    Rectangulo(u, 65f, 608f, 2f, 10f, Color.White)
    Rectangulo(u, 41f, 592f, 10f, 2f, Color.White)
    Rectangulo(u, 81f, 592f, 10f, 2f, Color.White)

    // Interruptor de volumen (izquierda) y de 3D (derecha)
    val trianguloIzq = GenericShape { s, _ -> moveTo(0f, 0f); lineTo(s.width, 0f); lineTo(0f, s.height); close() }
    val trianguloDer = GenericShape { s, _ -> moveTo(0f, 0f); lineTo(s.width, 0f); lineTo(s.width, s.height); close() }
    Rectangulo(u, 0f, 260f, 8f, 52f, Color(0xFF0B3E8C))
    Rectangulo(u, 0f, 296f, 8f, 16f, Color(0xFF8A8A8A))
    Rectangulo(u, 9f, 278f, 12f, 22f, Negro, trianguloIzq)
    Texto(u, 18f, 261f, "VOL", 8f, Negro)
    Texto(u, 18f, 294f, "OFF", 8f, Negro)
    Rectangulo(u, 738f, 260f, 8f, 52f, Color(0xFF0B3E8C))
    Rectangulo(u, 738f, 296f, 8f, 16f, Color(0xFF8A8A8A))
    Rectangulo(u, 725f, 278f, 12f, 22f, Negro, trianguloDer)
    Texto(u, 722f, 265f, "3D", 8f, Negro)
    Texto(u, 716f, 294f, "OFF", 8f, Negro)

    // Botones X, Y, A, B
    listOf("X" to (678f to 476f), "Y" to (650f to 506f), "A" to (707f to 506f), "B" to (678f to 533f))
        .forEach { (letra, pos) ->
            Circulo(u, pos.first, pos.second, 26f, Negro)
            Texto(u, pos.first - 4f, pos.second - 8f, letra, 11f, Color.White)
        }

    // Detalle inferior
    Circulo(u, 637f, 690f, 16f, Negro)
    // Dos rayitas debajo del botón START
    Rectangulo(u, 630f, 705f, 3f, 12f, Negro)
    Rectangulo(u, 641f, 705f, 3f, 12f, Negro)
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
private fun GamePreview() {
    _3DSTheme { Game() }
}
