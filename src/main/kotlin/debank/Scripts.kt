package debank

import ads_std.*
import org.sikuli.script.Key
import org.sikuli.script.Pattern

suspend fun registerRabby(workRegion: WorkRegion) {
    val screen = workRegion.screen
    val key = PrivateKeysEVM.getKey(workRegion.profile)
    screen.wait(2.0)
    openExtension(screen, Pattern("rabby_wallet.png"))
    screen.wait(2.0)
    screen.wait("rabby_next_button.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_get_started_button.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_import_private_key.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_password_input.png")
    screen.queueTakeClick()
    screen.paste(WALLET_PASS_RABBY)
    screen.type(Key.TAB)
    screen.paste(WALLET_PASS_RABBY)
    screen.queueRelease()
    screen.wait("rabby_next_button.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_private_key_input.png")
    screen.queueTakeClick()
    screen.paste(key)
    screen.queueRelease()
    screen.wait("rabby_confirm_button.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_done_button.png")
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.queueTakeClickRelease()
    screen.wait(2.0)
}

suspend fun openRabby(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    openExtension(screen, Pattern("rabby_wallet.png"))
    screen.wait(2.0)
    screen.wait("rabby_enter_password_input.png")
    screen.queueTakeClick()
    screen.paste(WALLET_PASS_RABBY)
    screen.queueRelease()
    screen.wait("rabby_unlock_button.png")
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.queueTakeClickRelease()
}

suspend fun swapRabby(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait("rabby_swap.png")
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait(Pattern("rabby_chain.png").targetOffset(25, 25))
    screen.queueTakeClickRelease()
    screen.wait("rabby_arbitrum.png")
    screen.queueTakeClickRelease()
    screen.wait(Pattern("rabby_chain.png").targetOffset(235, 105))
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait("rabby_weth.png")
    screen.queueTakeClickRelease()
    screen.wait(Pattern("rabby_chain.png").targetOffset(25, 175))
    screen.queueTakeClick()
    screen.paste("0.001")
    screen.queueRelease()
    screen.wait("rabby_get_quotes_button.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_green_best.png", 8.0)
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait("rabby_swap_button.png")
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait(Pattern("rabby_sign_button.png").similar(0.98), 8.0)
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait("rabby_swap_confirm_button.png")
    screen.queueTakeClickRelease()
}

