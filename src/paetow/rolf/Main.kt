/**
 * Created by Rolf on 20.05.2017.
 */
package paetow.rolf

import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

fun main(args: Array<String>) {

    val f = JFrame("Load Image Sample")

    f.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            System.exit(0)
        }
    })

    f.add(LoadImageApp())
    f.pack()
    f.isVisible = true
}
