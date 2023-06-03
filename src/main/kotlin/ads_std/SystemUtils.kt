package ads_std

import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

fun displayMessage(message: String) {
    val tray = SystemTray.getSystemTray()
    val image = Toolkit.getDefaultToolkit().createImage("src/main/kotlin/robot_21_area_sui/png/idea.png")
    val trayIcon = TrayIcon(image)
    trayIcon.isImageAutoSize = true
    trayIcon.toolTip = "Message"
    tray.add(trayIcon)
    trayIcon.displayMessage("Message", message, TrayIcon.MessageType.INFO)
}

fun putTextInClipboard(text: String) {
    val selection = StringSelection(text)
    val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(selection, selection)
}

fun getTextFromClipboard(): String =
    Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor) as String
