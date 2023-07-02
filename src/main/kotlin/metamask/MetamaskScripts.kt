package metamask

import ads_std.WorkRegion
import ads_std.metamaskUnlock
import ads_std.openUrlSikuliDark

suspend fun changeLanguageToEnglish(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    openUrlSikuliDark(screen, "chrome-extension://mldahpfajfokgbplbigojfbmmagidkge/home.html#settings")
    val language = metamaskUnlock(screen)
    println(language)

}