package orbiter

import ads_std.*
import org.sikuli.script.Pattern

suspend fun sendGorliToSepolia(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    browserOpenUrl(screen, "chrome-extension://mldahpfajfokgbplbigojfbmmagidkge/home.html")
    metamaskUnlockEn(screen)
    screen.wait(2.0)
    browserOpenUrl(screen, "https://test.orbiter.finance/?source=G%C3%B6rli&dest=Sepolia%28G%29")
    screen.wait(2.0)
    screen.wait("orbiter_connect_wallet.png", 8.0)
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait(Pattern("orbiter_metamask.png").targetOffset(160, 0), 8.0)
    screen.queueClickRelease()
    screen.wait("metamask_handler_icon_dark.png", 8.0)
    screen.wait(2.0)
    metamaskNext(screen)
    screen.wait(2.0)
    metamaskConnect(screen)
    screen.wait(2.0)
    metamaskSwitchNetwork(screen)
    screen.wait("orbiter_metamask_icon.png", 8.0)
    tryToClickQueue(screen, Pattern("orbiter_unlock_more.png").targetOffset(243,0))
    screen.wait("orbiter_max.png")
    screen.queueTakeClickRelease()
    screen.wait("orbiter_sepolia.png", 8.0)
    screen.wait("orbiter_send.png", 8.0)
    screen.wait(0.5)
    screen.queueTakeClickRelease()
    screen.wait("orbiter_confirm_and_sand.png", 8.0)
    screen.wait(0.5)
    screen.queueTakeClickRelease()
    metamaskConfirmUntilItDisappears(screen, time = 8.0)
    screen.wait("orbiter_confirm_icon.png", 8.0)
    screen.wait(4.0)
}