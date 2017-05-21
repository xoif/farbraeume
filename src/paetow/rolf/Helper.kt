package paetow.rolf

import java.awt.image.BufferedImage

/**
 * Created by Rolf on 21.05.2017.
 */

fun applySepiaFilter(img: BufferedImage): BufferedImage {

    val sepiaImage = BufferedImage(img.width, img.height, img.type)

    for (row in 0..img.width - 1) {
        for (column in 0..img.height - 1) {
            val pixel = img.getRGB(row, column)

            var red = pixel shr 16 and 0xFF
            var green = pixel shr 8 and 0xFF
            var blue = pixel shr 0 and 0xFF

            var hsvRed = red.toDouble() / 255
            var hsvGreen = green.toDouble() / 255
            var hsvBlue = blue.toDouble() / 255

            val cmax = Math.max(hsvRed, Math.max(hsvGreen, hsvBlue))
            val cmin = Math.min(hsvRed, Math.min(hsvGreen, hsvBlue))
            val delta = cmax - cmin

            val saturation: Double
            val value: Double


            if (delta == 0.0) {
                saturation = 0.0
            } else {
                saturation = delta / cmax
            }

            value = cmax

            val hue: Double = 45.0

            if (saturation >= 0 && saturation <= 1 && value >= 0 && value <= 1) {
                val c = value * saturation
                val x = c * (1 - Math.abs(hue / 60 % 2 - 1))
                val m = value - c

                when {
                    isBetween(hue, 0.0, 60.0) -> {
                        hsvRed = c
                        hsvGreen = x
                        hsvBlue = 0.0
                    }

                    isBetween(hue, 0.0, 60.0) -> {
                        hsvRed = c
                        hsvGreen = x
                        hsvBlue = 0.0
                    }

                    isBetween(hue, 60.0, 120.0) -> {
                        hsvRed = x
                        hsvGreen = c
                        hsvBlue = 0.0
                    }

                    isBetween(hue, 120.0, 180.0) -> {
                        hsvRed = 0.0
                        hsvGreen = c
                        hsvBlue = x
                    }

                    isBetween(hue, 180.0, 240.0) -> {
                        hsvRed = 0.0
                        hsvGreen = x
                        hsvBlue = c
                    }

                    isBetween(hue, 240.0, 300.0) -> {
                        hsvRed = x
                        hsvGreen = 0.0
                        hsvBlue = c
                    }

                    isBetween(hue, 300.0, 360.0) -> {
                        hsvRed = c
                        hsvGreen = 0.0
                        hsvBlue = x
                    }
                }

                red = (255 * (hsvRed + m)).toInt()
                green = (255 * (hsvGreen + m)).toInt()
                blue = (255 * (hsvBlue + m)).toInt()
            }

            val tinchedPixel = red and 0xFF shl 16 or (green and 0xFF shl 8) or (blue and 0xFF)

            sepiaImage.setRGB(row, column, tinchedPixel)
        }

    }
    return sepiaImage
}

fun isBetween(x: Double, v1: Double, v2: Double): Boolean {
    return v1 <= x && x < v2
}