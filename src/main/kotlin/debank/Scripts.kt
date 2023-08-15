package debank

import ads_std.*
import org.sikuli.script.Key
import org.sikuli.script.Pattern

suspend fun registerRabby(workRegion: WorkRegion) {
    val screen = workRegion.screen
    val key = PrivateKeysEVM.getKey(workRegion.profile)
    screen.wait(2.0)
    openExtension(screen, Pattern("rabby_wallet.png"))
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

}