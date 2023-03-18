package scroll

import ads_std.*
import org.sikuli.script.*
import java.util.*
import kotlin.random.Random

suspend fun scrollScript(number: Int) {
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/scroll/png")
    try {
        openURLsikuliX(screen, "https://scroll.io/alpha")
        browserCloseLanguageSelection(screen)
        openMetamask(screen)
        metamaskUnlock(screen)
        metamaskCloseInformation(screen)
        screen.type(Key.ESC)
        screen.wait(Pattern("scroll_connect_wallet.png").targetOffset(380, 0))
        screen.click()
        screen.wait("metamask_button.png")
        screen.click()
        metamaskNext(screen)
        metamaskConnect(screen)
        screen.wait(Pattern("scroll_connect_wallet.png").targetOffset(380, 0))
        screen.click()
        metamaskScroll(screen, 3)
        metamaskApprove(screen)
        metamaskSwitchNetwork(screen)
        screen.wait(Pattern("goerli_connect_waller.png").targetOffset(400, 0))
        screen.click()
        metamaskScroll(screen, 3)
        metamaskApprove(screen)
        metamaskSwitchNetwork(screen)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        println("profile $number: ended with ERROR")
    } else {
        //closeProfileWithoutDriver(number)
    }
}

suspend fun scrollGuildScript(number: Int) {
    isError = false
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/scroll/png")
    try {
        closeTabsSikuliX(screen)
        openURLsikuliX(screen, "https://guild.xyz/scrollzkp")
        screen.wait("guild_connect_wallet.png", 24.0)
        screen.click()
        screen.wait("guild_metamask_button.png")
        screen.click()
        val metamaskLanguage = metamaskUnlock(screen)
        println("Metamask language = $metamaskLanguage")
        val verify = screen.exists("guild_verify_account.png", 8.0)
        if (verify != null) {
            println("Verify founded")
            screen.click()
            screen.wait(3.0)
            metamaskGotIt(screen)
            metamaskScroll(screen, 3)
            screen.wait(0.5)
            metamaskSign(screen, metamaskLanguage)
        } else {
            println("Verify not founded")
        }
        screen.wait("guild_scroll_zkp_button.png")
        screen.click()
        screen.wait(3.0)
        twitFollow(screen)
        screen.wait(5.0)
        screen.type(Key.F4, Key.CTRL)
        screen.wait(0.5)
        screen.type(Key.F5)
        screen.wait(4.0)
        screen.wait("you_have_access.png", 8.0)
        println("You have access")
        screen.wait("guild_join_to_get_roles.png")
        screen.click()
        screen.wait("guild_join.png")
        screen.click()
        screen.wait("guild_joined.png", 12.0)
        screen.wait(3.0)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        errorList.add(number)
        println("profile $number: ended with ERROR, added to error list")
        //closeProfileWithoutDriver(number)
    } else {
        println("profile $number registered!")
        closeProfileWithoutDriver(number)
    }
}

suspend fun scrollBridgeScript(number: Int) {
    isError = false
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/scroll/png")
    try {
        closeTabsSikuliX(screen)
        openURLsikuliX(screen, "https://scroll.io/alpha/bridge")
        if (!metamaskIsOpened(screen)) {
            screen.wait("bridge_connect_wallet.png", 8.0)
            screen.click()
            screen.wait("bridge_metamask_button.png")
            screen.click()
        }
        val metamaskLanguage = metamaskUnlock(screen)
        println("Metamask language = $metamaskLanguage")
        metamaskNext(screen, metamaskLanguage)
        metamaskConnect(screen, metamaskLanguage)
        metamaskSwitchNetwork(screen, metamaskLanguage)
        for (txn in 1..8) {
            println("Transaction $txn")
            screen.wait(Pattern("goerli_goerli_testnet.png").targetOffset(-50, -65))
            screen.click()
            val eth = Random.nextDouble(0.2) + 0.6
            println("eth = $eth")
            screen.paste(String.format(Locale.US, "%.2f", eth))
            screen.wheel(Mouse.WHEEL_DOWN, 4)
            screen.wait(1.0)
            screen.wait("goerli_send_eth_to_scroll.png")
            screen.click()
            metamaskScroll(screen, 4)
            screen.wait(0.5)
            metamaskConfirmUntilItDisappears(screen, metamaskLanguage)
            println("Confirmed")
            screen.wait("goerli_txn_hash.png")
            println("Transaction screen")
            screen.type(Key.HOME, Key.CTRL)
            screen.wait(0.5)
            screen.type(Key.F5)
            screen.wait(3.0)
        }
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        errorList.add(number)
        println("profile $number: script ended with ERROR, added to error list")
        closeProfileWithoutDriver(number)
    } else {
        println("profile $number script ended without errors")
        closeProfileWithoutDriver(number)
    }
}