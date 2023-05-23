package scroll

import ads_std.*
import org.sikuli.script.FindFailed

suspend fun poolOutScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    var backgroundColor = "\u001B[42m"
    println("$backgroundColor + Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}")
    try {
        openUrlSikuliDark(screen, "https://staging.syncswap.xyz/pool")
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
    } catch (e: FindFailed) {
        e.printStackTrace()
        backgroundColor = "\u001B[41m"
        errorListQ.add(workRegion.profile)
    }
    println(backgroundColor + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}")
}
