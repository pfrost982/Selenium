package debank

import ads_std.*
import org.sikuli.script.Key
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun registerRabby(workRegion: WorkRegion) {
    val screen = workRegion.screen
    val key = PrivateKeysEVM.getKey(workRegion.profile)
    screen.wait(3.0)
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
    screen.wait("rabby_released_confirm_button.png")
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait("rabby_done_button.png")
    screen.queueTakeClickRelease()
}

suspend fun openRabbyOrRegister(workRegion: WorkRegion) {
    val screen = workRegion.screen
    browserOpenExtension(screen, Pattern("rabby_wallet.png"))
    val next = screen.exists("rabby_next_button.png", 5.0)
    if (next != null) {
        registerRabby(workRegion)
    } else {
        screen.wait(2.0)
        screen.wait("rabby_enter_password_input.png")
        screen.queueTakeClick()
        screen.paste(WALLET_PASS_RABBY)
        screen.queueRelease()
        screen.wait("rabby_unlock_button.png")
        screen.queueTakeClickRelease()
    }
}

suspend fun claimPointsRabby(workRegion: WorkRegion) {
    val screen = workRegion.screen
    openRabbyOrRegister(workRegion)
    screen.wait("rabby_points.png")
    screen.wait(1.0)
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait("rabby_referal_code_input.png")
    screen.queueTakeClickRelease()
    screen.paste("DON")
    screen.wait(2.0)
    screen.wait("rabby_claim_button.png")
    screen.wait(2.0)
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait("rabby_sign_and_create_button.png")
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait("rabby_confirm_button.png")
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    browserOpenExtension(screen, Pattern("rabby_wallet.png"))
    screen.wait(2.0)
    screen.wait("rabby_points.png")
    screen.wait(1.0)
    screen.queueTakeClickRelease()
    screen.wait("rabby_set_my_code_button.png")
    workRegion.println("Claimed!!!", background = backgroundMagenta)
    screen.wait(3.0)
}

suspend fun swapRabby(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait("rabby_swap.png")
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait(Pattern("rabby_chain.png").targetOffset(25, 25))
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait("rabby_arbitrum.png")
    screen.queueTakeClickRelease()
    screen.wait(Pattern("rabby_chain.png").targetOffset(235, 105))
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait("rabby_weth.png")
    screen.queueTakeClickRelease()
    screen.wait(Pattern("rabby_chain.png").targetOffset(25, 175))
    screen.queueTakeClick()
    screen.paste("0.0005")
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

suspend fun mintRabby(workRegion: WorkRegion) {
    val screen = workRegion.screen
    browserOpenExtension(screen, Pattern("rabby_wallet.png"))
    screen.wait("rabby_more.png")
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait("rabby_claim.png")
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait("rabby_learn_more.png")
    screen.queueTakeClickRelease()
    screen.wait("debank_rabbit_medal.png", 16.0)
    val metamask = screen.exists(Pattern("metamask_handler_icon_dark.png").targetOffset(25, 25), 4.0)
    if (metamask != null) {
        screen.queueTakeAndWait()
        screen.mouseMove()
        screen.click()
        screen.type("w", Key.CTRL)
        screen.queueRelease()
    }
    browserOpenExtension(screen, Pattern("rabby_wallet.png"))
    flipOn(screen)
    screen.type(Key.F5)
    if (metamask == null) {
        screen.wait("debank_mint.png")
        screen.wait(4.0)
        screen.queueTakeClickRelease()
        screen.wait("debank_rabby_wallet.png")
        screen.queueTakeClickRelease()
        screen.wait("debank_connect.png")
        screen.queueTakeClickRelease()
    }
    screen.wait(2.0)
    screen.wait("rabby_connect_button.png", 8.0)
    screen.wait(2.0)
    screen.queueTakeClickRelease()
    if (metamask != null) {
        screen.wait("debank_mint.png")
        screen.wait(4.0)
        screen.queueTakeClickRelease()
        screen.wait("debank_rabby_wallet.png")
        screen.queueTakeClickRelease()
    }
    screen.wait("debank_verify.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_sign_and_submit_button.png", 120.0)
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait("rabby_swap_confirm_button.png")
    screen.queueTakeClickRelease()

    screen.wait("debank_mint.png")
    screen.wait(3.0)
    screen.queueTakeClickRelease()
    screen.wait("debank_get_code.png", 120.0)
    screen.queueTakeClickRelease()
    screen.wait("debank_copy_code.png", 8.0)
    screen.queueTakeClickRelease()
    screen.wait("debank_copied.png")
    val code = getTextFromClipboard()
    println("profile ${workRegion.profile}: " + code)
    browserOpenExtension(screen, Pattern("rabby_wallet.png"))
    flipOff(screen)
    screen.wait("rabby_more.png")
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait("rabby_claim.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_enter_claim_code_input.png", 8.0)
    screen.queueTakeClick()
    screen.paste(code)
    screen.queueRelease()
    screen.wait("rabby_claim_button.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_claim_success.png", 8.0)
    screen.wait("rabby_orange_rabbit.png")
    screen.queueTakeClickRelease()
}

suspend fun requestTokenRabby(workRegion: WorkRegion) {
    val screen = workRegion.screen
    //openExtension(screen, Pattern("rabby_wallet.png"))
    screen.wait("rabby_more.png")
    screen.queueTakeClickRelease()
    screen.wait(1.0)
    screen.wait("rabby_request_token.png")
    screen.queueTakeClickRelease()
    screen.wait("rabby_request_button.png", 8.0)
    screen.queueTakeClickRelease()
    screen.wait("rabby_request_success.png")
    screen.wait(2.0)
}

suspend fun flipOn(screen: Screen) {
    screen.wait(Pattern("rabby_flip_orange.png").similar(0.98))
    screen.queueTakeClickRelease()
}

suspend fun flipOff(screen: Screen) {
    screen.wait("rabby_blue_rabbit.png")
    screen.queueTakeAndWait()
    screen.mouseMove()
    screen.wait("rabby_flip_blue.png")
    screen.queueClickRelease()
    screen.wait("rabby_orange_rabbit.png")
}