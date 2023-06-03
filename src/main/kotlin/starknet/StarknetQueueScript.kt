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
        screen.wait(3.0)
        openExtension(screen, Pattern("braavos_wallet.png"))
        screen.wait("braavos_create_wallet_button.png")
        screen.queueTakeClick()
        //screen.wait("braavos_next_button.png")
        screen.paste(WALLET_PASS)
        screen.type(Key.TAB)
        screen.paste(WALLET_PASS)
        screen.type(Key.ENTER)
        screen.queueRelease()
        screen.wait("braavos_copy_seed_to_clipboard.png")
        screen.queueTakeClickRelease()
        screen.wait("braavos_i_saved_seed_button.png")
        screen.queueTakeClickRelease()
        val seed = getTextFromClipboard()
        fileAppendString(seeds_file, "${workRegion.profile} $seed")
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
        screen.queueTakeClickRelease()
        val address = getTextFromClipboard()
        fileAppendString(address_file, "${workRegion.profile} $address")
        println("${workRegion.profile} $address")
        screen.wait(3.0)
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