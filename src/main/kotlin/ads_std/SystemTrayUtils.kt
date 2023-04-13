package ads_std

import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

fun displayMessage(message: String) {
    val tray = SystemTray.getSystemTray()
    val image = Toolkit.getDefaultToolkit().createImage("src/main/kotlin/robot_21_area_sui/png/idea.png")
    val trayIcon = TrayIcon(image)
    trayIcon.isImageAutoSize = true
    trayIcon.toolTip = "Message"
    tray.add(trayIcon)
    trayIcon.displayMessage("Message", message, TrayIcon.MessageType.INFO)
}