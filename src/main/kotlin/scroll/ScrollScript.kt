package scroll

import ads_std.*
import org.sikuli.script.*

suspend fun scrollScript(number: Int) {
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/scroll/png")
    try {
        openURLsikuliX(screen, "https://scroll.io/alpha")
        browserCloseLanguageSelection(screen)
        openMetamask(screen)
        unlockMetamask(screen)
        var thereWasInformation = metamaskCloseInformation(screen)
        while (thereWasInformation) {
            screen.wait(1.0)
            thereWasInformation = metamaskCloseInformation(screen)
        }
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
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/scroll/png")
    try {
        openURLsikuliX(screen, "https://guild.xyz/scrollzkp")
        screen.wait(1.0)
        browserCloseLanguageSelection(screen)
        screen.wait("guild_connect_wallet.png")
        screen.click()
        screen.wait("guild_metamask_button.png")
        screen.click()
        unlockMetamask(screen)
        var thereWasInformation = metamaskCloseInformation(screen)
        while (thereWasInformation) {
            screen.wait(1.0)
            thereWasInformation = metamaskCloseInformation(screen)
        }
        val verify = screen.exists("guild_verify_account.png", 7.0)
        if (verify != null) {
            println("Verify founded")
            screen.click()
            screen.wait(3.0)
            metamaskScroll(screen, 3)
            metamaskSign(screen)
        } else {
            println("Verify not founded")
        }
        screen.wait("guild_scroll_zkp_button.png")
        screen.click()
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