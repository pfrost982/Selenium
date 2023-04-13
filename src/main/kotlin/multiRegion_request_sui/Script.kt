package multiRegion_request_sui

import ads_std.*
import org.sikuli.script.Key
import org.sikuli.script.Pattern

suspend fun requestTokenClicker(workRegion: WorkRegion) {
    queueOpenProfile(workRegion)
    val screen = workRegion.screen
    var numberOfRequest = 0
    println("start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}")
    while (numberOfRequest < 10) {
        val walletNotOpen = screen.exists(
            Pattern("favorites_and_extensions.png")
                .targetOffset(20, 0)
                .similar(0.95),
            1.0
        )
        if (walletNotOpen != null) {
            println("profile ${workRegion.profile}: Open wallet")
            queueAddClick(workRegion)
            screen.wait("sui_wallet.png")
            queueClickRelease(workRegion)
        } else {
            println("profile ${workRegion.profile}: Wallet opened")
        }

        val password = screen.exists("password.png")
        if (password != null) {
            println("profile ${workRegion.profile}: Unlock wallet")
            queueAddClick(workRegion)
            screen.paste(WALLET_PASS)
            screen.type(Key.ENTER)
            queueRelease(workRegion)
        } else {
            println("profile ${workRegion.profile}: Wallet unlocked")
        }

        val requestTokens = screen.exists("request_tokens.png", 1.0)
        if (requestTokens != null) {
            println("profile ${workRegion.profile}: Request tokens")
            numberOfRequest++
            queueAddClickRelease(workRegion)
        } else {
            println("profile ${workRegion.profile}: Request button not founded")
        }
    }
    queueCloseProfile(workRegion)
    println("finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}")
}
