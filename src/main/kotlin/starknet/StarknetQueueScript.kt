package starknet

import ads_std.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    browserOpenExtension(screen, Pattern("braavos_wallet_ext.png"))
    screen.wait("braavos_create_wallet_button.png")
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.TAB)
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
    screen.wait("braavos_copy_seed_to_clipboard.png")
    screen.queueTakeClick()
    screen.wait("braavos_copied_to_clipboard.png")
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
    screen.wait("braavos_copied_to_clipboard.png")
    val address = getTextFromClipboard()
    fileAppendString(braavos_address_file, "${workRegion.profile} $address")
    println("${workRegion.profile} $address")
    screen.queueRelease()
    screen.wait(2.0)
}

suspend fun createArgentX(workRegion: WorkRegion) {
    val screen = workRegion.screen
    browserOpenExtension(screen, Pattern("argentx_extension_title.png"))
    screen.wait("argentx_create_new_wallet.png", 8.0)
    screen.queueTakeClickRelease()
    screen.wait(Pattern("argentx_disclaimer.png").targetOffset(0, 140))
    screen.queueTakeClickRelease()
    screen.wait(Pattern("argentx_disclaimer.png").targetOffset(0, 240))
    screen.queueTakeClickRelease()
    screen.wait("argentx_continue_button.png")
    screen.queueTakeClickRelease()
    screen.wait(Pattern("argentx_new_wallet.png").targetOffset(0, 105))
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.queueRelease()
    screen.wait(Pattern("argentx_new_wallet.png").targetOffset(0, 165))
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.queueRelease()
    screen.wait("argentx_create_wallet_button.png")
    screen.queueTakeClickRelease()
    screen.wait(3.0)
    browserOpenExtension(screen, Pattern("argentx_extension_title.png"))
    screen.wait("argentx_send_button.png", 8.0)
    screen.wait(Pattern("argentx_account.png").targetOffset(0, 27))
    screen.queueTakeClick()
    screen.wait("argentx_copied.png")
    val address = getTextFromClipboard()
    fileAppendString(argentx_address_file, "${workRegion.profile} $address")
    println("${workRegion.profile} $address")
    screen.queueRelease()
    screen.wait("argentx_setup_recovery.png")
    screen.queueTakeClickRelease()
    screen.wait("argentx_save_seed.png")
    screen.queueTakeClickRelease()
    screen.wait(3.0)
    screen.wait("argentx_copy.png")
    screen.queueTakeClickRelease()
    screen.wait("argentx_copied2.png")
    screen.queueTakeClick()
    val seed = getTextFromClipboard()
    fileAppendString(argentx_seeds_file, "${workRegion.profile} $seed")
    println("${workRegion.profile} $seed")
    screen.queueRelease()
    screen.wait("argentx_continue_dark.png")
    screen.queueTakeClickRelease()
    screen.wait("argentx_yes_dark.png")
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    tryToClickQueue(screen, Pattern("argentx_i_understand_button.png"))
    screen.wait("argentx_send_button.png", 8.0)
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
    browserOpenUrl(screen, "https://starknet-journey-map.braavos.app/")
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
    browserOpenExtension(screen, Pattern("braavos_wallet_ext.png"))
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

suspend fun upgradeNow(screen: Screen) {
    openBraavos(screen)
    screen.wait("braavos_upgrade_now.png")
    screen.wait(1.0)
    screen.queueTakeClickRelease()
    screen.wait("braavos_sign_button.png", 8.0)
    screen.wait(1.0)
    screen.queueTakeClickRelease()
    screen.wait(4.0)
    screen.wait("braavos_upgrading_account.png")
    screen.waitVanish("braavos_upgrading_account.png", 60.0)
    println("Upgrade success")
    screen.wait(3.0)
}

private suspend fun openBraavos(screen: Screen) {
    screen.wait(2.0)
    browserOpenExtension(screen, Pattern("braavos_wallet_ext.png"))
    screen.wait(Pattern("braavos_login_wallet.png").targetOffset(0, 68))
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
}

suspend fun openBraavos(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    browserOpenExtension(screen, Pattern("braavos_wallet_ext.png"))
    screen.wait(Pattern("braavos_login_unlock_your_wallet.png").targetOffset(0, 60))
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
}

suspend fun starkPEPEBraavos(workRegion: WorkRegion) {
    openBraavos(workRegion)
    val screen = workRegion.screen
    val subDomain = SubdomainPEPE.getSubdomain(workRegion.profile)
    workRegion.println(subDomain)
    browserOpenUrl(screen, "https://starkpepe.xyz/id")
    screen.wait("starkpepe_connect_wallet.png", 32.0)
    screen.wait(2.0)
    screen.queueTakeClickRelease()
    screen.wait("starkpepe_braavos_wallet.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_approve.png")
    screen.wait(1.0)
    screen.queueTakeClickRelease()
    screen.wait("starkpepe_subdomain_input.png")
    screen.queueTakeClick()
    screen.paste(subDomain)
    screen.queueRelease()
    screen.wait("starkpepe_available.png", 8.0)
    workRegion.println("Available!")
    browserOpenExtension(screen, Pattern("braavos_wallet_ext.png"))
    screen.wait(Pattern("braavos_mainnet.png").targetOffset(-310, 0))
    screen.queueTakeClickRelease()
    screen.wait("braavos_lock_wallet.png")
    screen.queueTakeClickRelease()
    screen.wait(Pattern("braavos_login_unlock_your_wallet.png").targetOffset(0, 60))
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
    screen.wait("starkpepe_rocket.png")
    screen.queueTakeClickRelease()
    screen.wait("starkpepe_mint_subdomain_button.png")
    screen.queueTakeClickRelease()
    screen.wait("braavos_sign_button.png", 8.0)
    screen.queueTakeClickRelease()
    browserOpenExtension(screen, Pattern("braavos_wallet_ext.png"))
    screen.wait(Pattern("braavos_mainnet.png").targetOffset(15, 550))
    screen.queueTakeClickRelease()
    screen.wait(Pattern("braavos_mainnet.png").targetOffset(-140, 140))
    screen.queueTakeClickRelease()
    screen.wait("braavaos_complited_status.png", 60.0)
    workRegion.println("!!!SubDomain minted)))", foregroundGreen)
    screen.wait(3.0)
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
    browserOpenExtension(screen, Pattern("argentx_extension_title.png"))
    screen.wait("argentx_password_input.png", 8.0)
    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
    val recovery = screen.exists("argentx_show_recovery_phrase.png")
    if (recovery != null) {
        screen.queueTakeClickRelease()
        screen.wait(2.5)
        screen.wait("argentx_switch_circle.png")
        screen.queueTakeClickRelease()
        screen.wait("argentx_done_button.png")
        screen.queueTakeClickRelease()
    }

    /*
        screen.wait(2.0)
        var send = screen.exists("argentx_send_button.png")
        while (send == null) {
            openExtension(screen, Pattern("argentx_extension_title.png"))
            screen.wait(2.0)
            send = screen.exists("argentx_send_button.png")
        }
    */
}

suspend fun starkPEPEArgentX(workRegion: WorkRegion) {
    val screen = workRegion.screen
    val subDomain = TwitLogins.getLogin(workRegion.profile)
    workRegion.println(subDomain, foregroundGreen)
    screen.wait(2.0)
    openArgentX(workRegion.screen)
    screen.wait(2.0)
    browserOpenUrl(screen, "https://starkpepe.xyz/id")
    screen.wait("starkpepe_rocket.png", 32.0)
    val braavosWindow = screen.exists("braavos_login_unlock_your_wallet.png")
    if (braavosWindow != null) {
        workRegion.println("braavosWindow case", foregroundGreen)
        screen.queueTakeClick()
        screen.type("w", Key.CTRL)
        screen.queueRelease()
    }
    screen.wait("starkpepe_connect_wallet.png", 8.0)
    screen.wait(1.0)
    screen.queueTakeClickRelease()
    screen.wait("starkpepe_argentx_wallet.png")
    screen.queueTakeClickRelease()
    screen.wait("argentx_connect_button.png")
    screen.queueTakeClickRelease()
    screen.wait("starkpepe_subdomain_input.png")
    screen.queueTakeClick()
    screen.paste(subDomain)
    screen.queueRelease()
    screen.wait("starkpepe_available.png", 8.0)
    workRegion.println("Available!", foregroundGreen)
    screen.wait("starkpepe_mint_subdomain_button.png")
    screen.queueTakeClickRelease()
    screen.wait("argentx_confirm_button.png")
    screen.wait(3.0)
    screen.queueTakeClickRelease()
    browserOpenExtension(screen, Pattern("argentx_extension_title.png"))
    screen.wait(4.0)
    screen.waitVanish("argentx_pending_transaction_icon.png", 60.0)
    browserOpenExtension(screen, Pattern("argentx_extension_title.png"))
    screen.wait("argentx_pepe_domain.png", 12.0)
    workRegion.println("See Pepe domain", foregroundGreen)
    screen.wait(3.0)
}

suspend fun avnu(screen: Screen) {
    openArgentX(screen)
    browserOpenUrl(
        screen,
        "https://app.avnu.fi/en?tokenTo=0x68f5c6a61780768455de69077e07e89787839bf8166decfbf92b645209c0fb8&amount=0.002&tokenFrom=0x49d36570d4e46f48e99674bd3fcc84644ddd6b96f7c741b1562b82f9e004dc7"
    )
    screen.wait(6.0)
    tryToClickQueue(screen, Pattern("avnu_cookies_ok.png"))
    val start = screen.exists("avnu_close_start.png")
    if (start != null) {
        screen.queueTakeClickRelease()
        screen.wait(1.0)
        screen.queueTakeClickRelease()
    }
    val connect = screen.exists("avnu_connect.png")
    if (connect != null) {
        screen.queueTakeClickRelease()
        screen.wait("avnu_argentx.png")
        screen.queueTakeClickRelease()
        screen.wait(2.0)
        screen.wait("argentx_connect_button.png")
        screen.queueTakeClickRelease()
    }
    screen.wait(2.0)
    changeSlippage(screen)
    screen.wait(2.0)
    avnuSwap(screen)
    var result = getResult(screen)
    println(result)
    while (result == "error") {
        screen.wait("argentx_error.png")
        screen.queueTakeClick()
        screen.type(Key.F4, Key.CTRL)
        screen.queueRelease()
        avnuSwap(screen)
        result = getResult(screen)
        println(result)
    }
    screen.wait("avnu_close.png")
    screen.queueTakeClickRelease()
}

private suspend fun changeSlippage(screen: Screen) {
    screen.wait("avnu_settings.png")
    screen.queueTakeClickRelease()
    screen.wait(Pattern("avnu_settings_percent.png").targetOffset(-60, 0))
    screen.queueTakeClick()
    screen.type("a", Key.CTRL)
    screen.paste("10")
    screen.type(Key.ESC)
    screen.queueRelease()
}

private suspend fun getResult(screen: Screen): String {
    var result: String? = null
    val errorJob = CoroutineScope(Dispatchers.Default).launch {
        val error = screen.exists("argentx_error.png", 60.0)
        if (error != null) {
            result = "error"
        }
    }
    val swapJob = CoroutineScope(Dispatchers.Default).launch {
        val swap = screen.exists("avnu_transaction_in_processing.png", 60.0)
        if (swap != null) {
            result = "swap"
        }
    }
    while (result == null) {
        delay(500)
    }
    errorJob.cancel()
    swapJob.cancel()
    return result as String
}

private suspend fun avnuSwap(screen: Screen) {
    screen.wait(Pattern("avnu_swap.png").similar(0.95), 8.0)
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait(Pattern("argentx_confirm_button.png").similar(0.98), 8.0)
    screen.wait(0.2)
    screen.queueTakeClickRelease()
}

suspend fun mintNFT(screen: Screen) {
    screen.wait(2.0)
    browserOpenExtension(screen, Pattern("argentx_extension_title.png"))
    screen.wait(3.0)
    var password = screen.wait("argentx_password_input.png", 12.0)
    while (password == null) {
        browserOpenExtension(screen, Pattern("argentx_extension_title.png"))
        password = screen.wait("argentx_password_input.png", 12.0)
    }

    screen.queueTakeClick()
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    screen.queueRelease()
    screen.wait(3.0)
    browserOpenUrl(screen, "https://xplorer.argent.xyz/claim/fd6a48d5-699b-427b-ab67-baf237dc7208")
    screen.wait(3.0)
    browserScroll(screen, 6)
    screen.wait("xplorer_check_eligibility.png")
    screen.queueTakeClickRelease()
    screen.wait(3.0)
    browserScroll(screen, 4)
    screen.wait("xplorer_claim_now.png")
    screen.queueTakeClickRelease()
    screen.wait(5.0)
    val confirm = screen.exists("argentx_confirm_button.png")

    screen.queueTakeClickRelease()
    screen.wait("xplorer_share.png", 8.0)
    screen.wait(3.0)
}