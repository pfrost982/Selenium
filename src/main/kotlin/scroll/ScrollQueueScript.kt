package scroll

import ads_std.*
import org.sikuli.script.FindFailed
import org.sikuli.script.Key
import org.sikuli.script.Pattern

suspend fun poolOutScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    println("Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}")
    try {
        var metamaskLanguage: String
        openUrlSikuliDark(screen, "https://staging.syncswap.xyz/pool")
        val start = screen.exists("sync_swap_start_button.png", 6.0)
        if (start != null) {
            println("Start button case")
            screen.queueTakeClick()
            screen.type(Key.ESC)
            screen.queueRelease()
            screen.wait(Pattern("sync_swap_connect_wallet.png").targetOffset(-136, 0))
            screen.queueTakeClickRelease()
            screen.wait("sync_swap_scroll_button.png")
            screen.queueTakeClickRelease()
            screen.wait("sync_swap_connect_wallet.png")
            screen.queueTakeClickRelease()
            screen.wait("sync_swap_metamask_button.png")
            screen.queueTakeClickRelease()
            metamaskLanguage = metamaskUnlock(screen)
            println("Metamask language = $metamaskLanguage")
            //metamaskGotIt(screen)
            metamaskNext(screen, metamaskLanguage)
            metamaskConnect(screen, metamaskLanguage)
            screen.wait("sync_swap_try_unlock_button.png")
            screen.queueTakeClickRelease()
        }

        metamaskLanguage = metamaskUnlock(screen)
        println("Metamask language = $metamaskLanguage")
        screen.wait(2.5)
        if (metamaskIsOpened(screen, 4.0)) {
            metamaskScroll(screen, 3, delayBeforeScroll = 1.0)
            screen.wait(0.5)
            metamaskReject(screen, metamaskLanguage)
            metamaskNext(screen, metamaskLanguage)
            metamaskConnect(screen, metamaskLanguage)
            metamaskAllowAddNetwork(screen, metamaskLanguage)
            metamaskSwitchNetwork(screen, metamaskLanguage)
        } else {

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
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }
    println("Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}")
}
