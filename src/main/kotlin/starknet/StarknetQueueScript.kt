package starknet

import ads_std.*
import org.sikuli.script.FindFailed
import org.sikuli.script.Key
import org.sikuli.script.Pattern

suspend fun createBraavosWalletScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    println(
        backgroundGreen + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
    try {
        screen.wait(2.0)
        openExtension(screen, Pattern("braavos_wallet.png"))
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
        fileAppendString(seeds_file, "${workRegion.profile} $seed")
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
        fileAppendString(address_file, "${workRegion.profile} $address")
        println("${workRegion.profile} $address")
        screen.queueRelease()
        screen.wait(2.0)
    } catch (e: FindFailed) {
        println(backgroundRed + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        if (workQueue.peek() == screen) {
            workQueue.poll()
        }
        errorList.add(workRegion.profile)
    }
    println(
        backgroundGreen + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
}

suspend fun setupBraavosWalletScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    println(
        backgroundGreen + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
    try {
        screen.wait(2.0)
        openExtension(screen, Pattern("braavos_wallet.png"))
        screen.wait(Pattern("braavos_login_wallet.png").targetOffset(0, 68))
        screen.queueTakeClick()
        screen.paste(WALLET_PASS)
        screen.type(Key.ENTER)
        screen.queueRelease()
        screen.wait("braavos_setup_button.png")
        screen.queueTakeClick()
        screen.wait(Pattern("braavos_setup_account_button.png").similar(0.98), 5.0)
        screen.queueClickRelease()
        screen.wait(2.0)
    } catch (e: FindFailed) {
        println(backgroundRed + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        if (workQueue.peek() == screen) {
            workQueue.poll()
        }
        errorList.add(workRegion.profile)
    }
    println(
        backgroundGreen + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
}

suspend fun sendMoneyFromArgentXToBraavosScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    println(
        backgroundGreen + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
    try {
        screen.wait(2.0)
        openExtension(screen, Pattern("argentx_extension_title.png"))
        screen.wait("argentx_password_input.png", 8.0)
        screen.queueTakeClick()
        screen.paste(WALLET_PASS)
        screen.type(Key.ENTER)
        screen.queueRelease()
        screen.wait(2.0)
        var send =screen.exists("argentx_send_button.png")
        while (send == null) {
            openExtension(screen, Pattern("argentx_extension_title.png"))
            screen.wait(2.0)
            send =screen.exists("argentx_send_button.png")
        }
        screen.wait(0.5)
        screen.queueTakeClickRelease()
        screen.wait(2.0)
        screen.wait("argentx_ethereum.png")
        screen.queueTakeClick()
        screen.paste("0.006")
        screen.type(Key.TAB)
        screen.paste(BraavosAddress.getAddress(workRegion.profile))
        screen.queueRelease()
        screen.wait("argentx_next_button.png")
        screen.queueTakeClickRelease()
        screen.wait("argentx_confirm_button.png")
        screen.wait(0.5)
        screen.queueTakeClickRelease()
        screen.wait("argentx_pending_transaction_icon.png", 18.0)
        screen.queueTakeClickRelease()
        screen.wait(3.0)
    } catch (e: FindFailed) {
        workQueue.remove(screen)
        println(backgroundRed + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        if (workQueue.peek() == screen) {
            workQueue.poll()
        }
        errorList.add(workRegion.profile)
    }
    println(
        backgroundGreen + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
}