package scroll

import ads_std.*
import org.sikuli.script.Screen

suspend fun poolOutScript(screen: Screen) {
        browserOpenUrl(screen, "https://scroll.io/bridge")
        val metamaskLanguage = metamaskUnlock(screen)
        println("Metamask language = $metamaskLanguage")
        screen.wait(2.5)
        if (metamaskIsOpenedDark(screen)) {
            metamaskSwitchNetwork(screen, metamaskLanguage)
        }

        metamaskGotIt(screen)

        screen.wait("sync_swap_connect_wallet.png")
        screen.queueTakeClickRelease()
        screen.wait("sync_swap_metamask_button.png")
        screen.queueTakeClickRelease()

        val switch = screen.exists("sync_swap_switch_network_button.png", 4.0)
        if (switch != null) {
            println("Switch case")
            screen.queueTakeClickRelease()
            metamaskAllowAddNetwork(screen, metamaskLanguage)
            metamaskSwitchNetwork(screen, metamaskLanguage)
        } else {
            println("No switch case")
        }
}
