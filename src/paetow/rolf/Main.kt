/**
 * Created by Rolf on 20.05.2017.
 */
package paetow.rolf

import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.JFrame

fun main(args: Array<String>) {

    val f = JFrame("Load Image Sample")

    f.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            System.exit(0)
        }
    })

    val loadImageApp = LoadImageApp()
    f.add(loadImageApp)
    f.pack()
    f.isVisible = true

    val loadedImage = loadImageApp.img

    f.addMouseListener(object : MouseListener{
        override fun mouseReleased(e: MouseEvent?) {}

        override fun mouseEntered(e: MouseEvent?) {}

        override fun mouseExited(e: MouseEvent?) {}

        override fun mousePressed(e: MouseEvent?) {}

        override fun mouseClicked(e: MouseEvent?) {
            if (loadedImage != null) {
                val showSepiaImage = ShowSepiaImage(loadedImage)
                f.remove(loadImageApp)
                f.add(showSepiaImage)
                f.revalidate()
            }
        }
    })
}

class LoadImageApp : Component() {
    var img: BufferedImage? = null

    init {
        try {
            img = ImageIO.read(File("strawberry.jpg"))
        } catch (e: IOException) {
        }
    }

    override fun paint(g: Graphics?) {
        g!!.drawImage(img, 0, 0, null)
    }

    override fun getPreferredSize(): Dimension {
        if (img == null) {
            return Dimension(100, 100)
        } else {
            return Dimension(img!!.getWidth(null), img!!.getHeight(null))
        }
    }
}

class ShowSepiaImage(var image: BufferedImage) : Component() {
    init {
        image = applySepiaFilter(image)
    }
    override fun paint(g: Graphics?) {
        g!!.drawImage(image, 0, 0, null)
    }

    override fun getPreferredSize(): Dimension {
        if (image == null) {
            return Dimension(100, 100)
        } else {
            return Dimension(image!!.getWidth(null), image!!.getHeight(null))
        }
    }
}