package starknet

import ads_std.*
import org.sikuli.script.Key
import org.sikuli.script.Pattern
import org.sikuli.script.Screen
import java.io.File

val braavos_seeds_file = File("src/main/kotlin/starknet/braavos_seeds.txt")
val braavos_address_file = File("src/main/kotlin/starknet/braavos_address.txt")
val argentx_seeds_file = File("src/main/kotlin/starknet/argentx_seeds.txt")
val argentx_address_file = File("src/main/kotlin/starknet/argentx_address.txt")
suspend fun createBraavosWalletScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    openExtension(screen, Pattern("braavos_wallet_ext.png"))
    screen.wait("braavos_create_wallet_button.png")
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.TAB)
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
    screen.wait("braavos_copy_seed_to_clipboard.png")
    screen.queueTakeClick()
    val seed = getTextFromClipboard()
    fileAppendString(braavos_seeds_file, "${workRegion.profile} $seed")
    screen.queueRelease()
    screen.wait("braavos_i_saved_seed_button.png")
    screen.queueTakeClickRelease()
    println("${workRegion.profile} $seed")
    screen.wait("braavos_mainnet_button.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_continue_button.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_skip_button.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_receive.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_copy_icon.png")
    screen.queueTakeClick()
    val address = getTextFromClipboard()
    fileAppendString(braavos_address_file, "${workRegion.profile} $address")
    println("${workRegion.profile} $address")
    screen.queueRelease()
    screen.wait(2.0)
}

suspend fun registerDomain(screen: Screen, number: Int) {
    openBraavos(screen)
    screen.wait(Pattern("braavos_receive.png").targetOffset(-165, -147))
    screen.queueTakeClickRelease()
    screen.wait("braavos_settings_icon.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_register_domain.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_domain_name_input.png")
    screen.wait(1.0)
    screen.wait(Pattern("braavos_domain_name_input.png").targetOffset(-130, 0))
    screen.queueTakeClick()
    screen.paste("gangofthree" + leadingZerosString(number, 3))
    screen.queueRelease()
    screen.wait(3.0)
    screen.wait(Pattern("braavos_register_button.png").similar(0.95), 8.0)
    screen.wait(0.5)
    screen.queueTakeClickRelease()
    screen.wait("braavos_pending_transaction.png", 24.0)
    screen.wait(5.0)
}

suspend fun journeyNFT4th(screen: Screen) {
    openBraavos(screen)
    screen.wait(3.0)
    openUrlSikuliDark(screen, "https://starknet-journey-map.braavos.app/")
    screen.wait("journey_connect_wallet.png", 8.0)
    screen.wait(2.0)
    screen.wait("journey_connect_wallet.png")
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait("journey_braavos_wallet.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_approve_button.png")
    screen.queueTakeClickRelease()
    screen.wait(3.0)
    screen.wait("journey_twitter_share.png")
    screen.queueTakeClickRelease()
    screen.wait(5.0)
    screen.wait("twitter_tweet_button.png")
    screen.queueTakeClickRelease()
    screen.wait(5.0)
    screen.wait("browser_journey_tab.png")
    screen.queueTakeClickRelease()
    do {
        screen.wait("journey_claim_nft.png")
        screen.queueTakeClickRelease()
        screen.wait(3.0)
        screen.wait(Pattern("braavos_sign_button.png").similar(0.95), 12.0)
        screen.queueTakeClickRelease()
        screen.wait(5.0)
    } while (screen.exists("journey_failed.png") != null)
    openExtension(screen, Pattern("braavos_wallet_ext.png"))
    screen.wait("braavos_pending_transaction.png", 8.0)
}

suspend fun ethToDai(screen: Screen) {
    openBraavos(screen)
    screen.wait("braavos_swap.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_select_token.png")
    screen.queueTakeClick()
    screen.wait("braavos_dai_icon.png")
    screen.queueClickRelease()
    screen.wait(Pattern("braavos_dai_icon.png").targetOffset(280, 0))
    screen.queueTakeClick()
    screen.paste("3")
    screen.wait(Pattern("braavos_swap_button.png").similar(0.98), 8.0)
    screen.queueClickRelease()
    screen.wait("braavos_wallet.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_pending_transaction.png")
    screen.waitVanish("braavos_pending_transaction.png", 300.0)
}

suspend fun daiToEth(screen: Screen) {
    openBraavos(screen)
    screen.wait("braavos_swap.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_select_token.png")
    screen.queueTakeClick()
    screen.wait("braavos_dai_icon.png")
    screen.queueClickRelease()
    screen.wait("braavos_swap_tokens.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_max.png")
    screen.queueTakeClick()
    screen.wait(Pattern("braavos_swap_button.png").similar(0.98), 8.0)
    screen.queueClickRelease()
    var tp = screen.exists("braavos_pending_transaction.png")
    while (tp == null) {
        screen.wait(Pattern("braavos_swap_button.png").similar(0.98), 8.0)
        screen.queueTakeClickRelease()
        tp = screen.exists("braavos_pending_transaction.png")
    }
    screen.wait("braavos_wallet.png")
    screen.queueTakeClickRelease()
    screen.waitVanish("braavos_pending_transaction.png", 300.0)
    screen.wait(3.0)
}

private suspend fun openBraavos(screen: Screen) {
    screen.wait(2.0)
    openExtension(screen, Pattern("braavos_wallet_ext.png"))
    screen.wait(Pattern("braavos_login_wallet.png").targetOffset(0, 68))
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
}

suspend fun sendMoneyFromArgentXToBraavosScript(screen: Screen, profile: Int) {
    openArgentX(screen)
    screen.wait(0.5)
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait("argentx_ethereum.png")
    screen.queueTakeClick()
    screen.paste("0.006")
    screen.type(Key.TAB)
    screen.paste(BraavosAddress.getAddress(profile))
    screen.queueRelease()
    screen.wait("argentx_next_button.png")
    screen.queueTakeClickRelease()
    screen.wait("argentx_confirm_button.png")
    screen.wait(0.5)
    screen.queueTakeClickRelease()
    screen.wait("argentx_pending_transaction_icon.png", 18.0)
    screen.queueTakeClickRelease()
    screen.wait(3.0)
}

private suspend fun openArgentX(screen: Screen) {
    openExtension(screen, Pattern("argentx_extension_title.png"))
    screen.wait("argentx_password_input.png", 8.0)
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
    screen.wait(2.0)
    var send = screen.exists("argentx_send_button.png")
    while (send == null) {
        openExtension(screen, Pattern("argentx_extension_title.png"))
        screen.wait(2.0)
        send = screen.exists("argentx_send_button.png")
    }
}

suspend fun avnu(screen: Screen) {
    openArgentX(screen)
    openUrlSikuliDark(
        screen,
        "https://app.avnu.fi/en?tokenFrom=0x49d36570d4e46f48e99674bd3fcc84644ddd6b96f7c741b1562b82f9e004dc7&tokenTo=0x53c91253bc9682c04929ca02ed00b3e423f6710d2ee7e0d5ebb06f3ecf368a8&amount=0.001"
    )
    screen.wait(3.0)
    tryToClickQueue(screen, Pattern("avnu_cookies_ok.png"))
    screen.wait(1.0)
    tryToClickQueue(screen, Pattern("avnu_cookies_ok.png"))
    tryToClickQueue(screen, Pattern("avnu_start.png"))
    while (screen.exists("avnu_next.png") != null) {
        screen.wait(0.5)
        screen.queueTakeClickRelease()
    }
    val connect = screen.exists("avnu_connect.png")
    if (connect != null) {
        screen.queueTakeClickRelease()
        screen.wait("avnu_argentx.png")
        screen.queueTakeClickRelease()
        screen.wait("argentx_connect.png")
        screen.queueTakeClickRelease()
    }
    screen.wait("avnu_swap.png")
    screen.queueTakeClickRelease()
    screen.wait("argentx_confirm_button.png")
    screen.wait(0.5)
    screen.queueTakeClickRelease()
}

suspend fun mintNFT(screen: Screen) {
    screen.wait(2.0)
    openExtension(screen, Pattern("argentx_extension_title.png"))
    screen.wait(3.0)
    var password = screen.wait("argentx_password_input.png", 12.0)
    while (password == null) {
        openExtension(screen, Pattern("argentx_extension_title.png"))
        password = screen.wait("argentx_password_input.png", 12.0)
    }

    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
    screen.wait(3.0)
    openUrlSikuliDark(screen, "https://xplorer.argent.xyz/claim/fd6a48d5-699b-427b-ab67-baf237dc7208")
    screen.wait(3.0)
    scrollBrowser(screen, 6)
    screen.wait("xplorer_check_eligibility.png")
    screen.queueTakeClickRelease()
    screen.wait(3.0)
    scrollBrowser(screen, 4)
    screen.wait("xplorer_claim_now.png")
    screen.queueTakeClickRelease()
    screen.wait(5.0)
    val confirm = screen.exists("argentx_confirm_button.png")

    screen.queueTakeClickRelease()
    screen.wait("xplorer_share.png", 8.0)
    screen.wait(3.0)
}