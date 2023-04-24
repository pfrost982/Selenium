package multiRegion_request_sui

import ads_std.*
import org.sikuli.script.Key
import org.sikuli.script.Pattern

suspend fun requestTokenClicker(workRegion: WorkRegion) {
    val screen = workRegion.screen
    var numberOfRequest = 0
    var numberOfRequestNotFound = 0
    println("start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}")
    while (numberOfRequest < 7 && numberOfRequestNotFound < 7) {
        val walletNotOpen = screen.exists(
            Pattern("favorites_and_extensions.png")
                .targetOffset(20, 0)
                .similar(0.95),
            1.0
        )
        if (walletNotOpen != null) {
            println("profile ${workRegion.profile}: Open wallet")
            screen.queueTakeClick()
            screen.wait("sui_wallet.png")
            screen.queueClickRelease()
        } else {
            println("profile ${workRegion.profile}: Wallet opened")
        }

        val password = screen.exists("password.png", 1.0)
        if (password != null) {
            println("profile ${workRegion.profile}: Unlock wallet")
            screen.queueTakeClick()
            screen.paste(WALLET_PASS)
            screen.type(Key.ENTER)
            screen.queueClickRelease()
        } else {
            println("profile ${workRegion.profile}: Wallet unlocked")
        }

        val requestTokens = screen.exists("request_tokens.png", 1.0)
        if (requestTokens != null) {
            println("profile ${workRegion.profile}: Request tokens")
            numberOfRequest++
            screen.queueTakeClickRelease()
        } else {
            println("profile ${workRegion.profile}: Request button not founded")
            numberOfRequestNotFound++
        }
    }
    println("finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}")
}
