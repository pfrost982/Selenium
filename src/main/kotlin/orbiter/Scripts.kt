package orbiter

import ads_std.*
import org.sikuli.script.Pattern

suspend fun sendGorliToSepolia(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    openUrlSikuliDark(screen, "chrome-extension://mldahpfajfokgbplbigojfbmmagidkge/home.html")
    metamaskUnlockEn(screen)
    screen.wait(2.0)
    openUrlSikuliDark(screen, "https://test.orbiter.finance/?source=G%C3%B6rli&dest=Sepolia%28G%29")
    screen.wait(2.0)
    screen.wait("orbiter_connect_wallet.png")
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait(Pattern("orbiter_metamask.png").targetOffset(160, 0))
    screen.queueClickRelease()
    metamaskNext(screen)
    metamaskConnect(screen)
    metamaskSwitchNetwork(screen)
}