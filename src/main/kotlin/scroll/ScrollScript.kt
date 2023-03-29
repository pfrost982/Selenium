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
        screen.wait("guild_you_have_access.png", 8.0)
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
        metamaskGotIt(screen)
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
        //closeProfileWithoutDriver(number)
    } else {
        println("profile $number script ended without errors")
        closeProfileWithoutDriver(number)
    }
}

suspend fun scrollSyncSwapScript(number: Int) {
    isError = false
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/scroll/png")
    try {
        closeTabsSikuliX(screen)
        openURLsikuliX(screen, "https://staging.syncswap.xyz/swap")
        screen.wait("sync_swap_start_button.png", 8.0)
        screen.click()
        screen.type(Key.ESC)
        screen.wait(Pattern("sync_swap_connect_wallet.png").targetOffset(-136, 0))
        screen.click()
        screen.wait("sync_swap_scroll_button.png")
        screen.click()
        screen.wait("sync_swap_connect_wallet.png")
        screen.click()
        screen.wait("sync_swap_metamask_button.png")
        screen.click()
        val metamaskLanguage = metamaskUnlock(screen)
        println("Metamask language = $metamaskLanguage")
        //metamaskGotIt(screen)
        metamaskNext(screen, metamaskLanguage)
        metamaskConnect(screen, metamaskLanguage)
        screen.wait("sync_swap_try_unlock_button.png")
        screen.click()
        val switch = screen.exists("sync_swap_switch_network_button.png", 8.0)
        if (switch != null) {
            println("Switch case")
            screen.click()
            screen.wait(2.0)
            metamaskAllowAddNetwork(screen, metamaskLanguage)
            metamaskSwitchNetwork(screen, metamaskLanguage)
        } else {
            println("No switch case")
        }
        screen.wait(1.0)
        screen.wait("sync_swap_25.png")
        screen.wait(1.0)
        println("Click 25%")
        screen.click()
        screen.wait("sync_swap_swap_button.png")
        println("Click swap button")
        screen.click()
        metamaskScroll(screen, 3)
        screen.wait(0.5)
        metamaskConfirm(screen, metamaskLanguage)
        println("Confirmed")
        screen.wait("sync_swap_swapped.png", 32.0)
        println("Swapped")
        screen.type(Key.ESC)

        screen.wait("sync_swap_arrow.png")
        screen.click()
        screen.wait(Pattern("sync_swap_enter_amount.png").targetOffset(160, -100))
        println("Click bottom token select")
        screen.click()
        screen.wait(1.0)
        screen.wheel(Mouse.WHEEL_DOWN, 3)
        screen.wait(0.5)
        screen.wait("sync_swap_aave.png")
        println("Click AAVE")
        screen.click()
        screen.wait(1.0)
        screen.wait("sync_swap_100.png")
        println("Click 100%")
        screen.click()
        screen.wait("sync_swap_permit_usdc_button.png")
        println("Click Permit USDC")
        screen.click()
        metamaskScroll(screen, 2, delayBeforeScroll = 1.0)
        screen.wait(0.5)
        metamaskScroll(screen, 2)
        screen.wait(0.5)
        metamaskBigSign(screen, metamaskLanguage)
        println("Metamask Big confirmed")
        screen.wait("sync_swap_swap_button.png", 32.0)
        println("Click swap button")
        screen.click()
        metamaskScroll(screen, 3)
        screen.wait(0.5)
        metamaskConfirm(screen, metamaskLanguage)
        println("Metamask Confirmed")
        screen.wait("sync_swap_swapped.png", 32.0)
        println("Swapped")
        screen.type(Key.ESC)

        screen.wait("sync_swap_arrow.png")
        screen.click()
        screen.wait(Pattern("sync_swap_enter_amount.png").targetOffset(160, -100))
        println("Click bottom token select")
        screen.click()
        screen.wait(1.0)
        screen.wheel(Mouse.WHEEL_DOWN, 3)
        screen.wait(0.5)
        screen.wait("sync_swap_usdt.png")
        println("Click USDT")
        screen.click()
        screen.wait(1.0)
        screen.wait("sync_swap_100.png")
        println("Click 100%")
        screen.click()
        screen.wait("sync_swap_unlock_aave_button.png")
        println("Click Unlock AAVE")
        screen.click()
        metamaskScroll(screen, 2, delayBeforeScroll = 1.0)
        screen.wait(0.5)
        metamaskConfirm(screen, metamaskLanguage)
        println("Metamask confirmed")
        screen.wait(2.0)
        screen.type(Key.ESC)
        screen.wait("sync_swap_swap_button.png", 32.0)
        println("Click swap button")
        screen.click()
        metamaskScroll(screen, 3)
        screen.wait(0.5)
        metamaskConfirm(screen, metamaskLanguage)
        println("Metamask Confirmed")
        screen.wait("sync_swap_swapped.png", 32.0)
        println("Swapped")
        screen.type(Key.ESC)

        screen.wait("sync_swap_arrow.png")
        screen.click()
        screen.wait(Pattern("sync_swap_enter_amount.png").targetOffset(160, -100))
        println("Click bottom token select")
        screen.click()
        screen.wait(1.0)
        screen.wait("sync_swap_eth.png")
        println("Click ETH")
        screen.click()
        screen.wait(1.0)
        screen.wait("sync_swap_100.png")
        println("Click 100%")
        screen.click()
        screen.wait("sync_swap_unlock_usdt_button.png")
        println("Click Unlock USDT")
        screen.click()
        metamaskScroll(screen, 2, delayBeforeScroll = 1.0)
        screen.wait(0.5)
        metamaskConfirm(screen, metamaskLanguage)
        println("metamask Confirmed")
        screen.wait(2.0)
        screen.type(Key.ESC)
        screen.wait("sync_swap_swap_button.png", 32.0)
        println("Click swap button")
        screen.click()
        metamaskScroll(screen, 3)
        screen.wait(0.5)
        metamaskConfirm(screen, metamaskLanguage)
        println("Metamask Confirmed")
        screen.wait("sync_swap_swapped.png", 32.0)
        println("Swapped")
        screen.type(Key.ESC)
        screen.wait(1.0)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        errorList.add(number)
        println("profile $number: script ended with ERROR, added to error list")
        //closeProfileWithoutDriver(number)
    } else {
        println("profile $number script ended without errors")
        closeProfileWithoutDriver(number)
    }
}

suspend fun scrollSyncSwapPoolScript(number: Int) {
    isError = false
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/scroll/png")
    try {
        var metamaskLanguage = "en"
        openURLsikuliX(screen, "https://staging.syncswap.xyz/swap")
        val start = screen.exists("sync_swap_start_button.png", 6.0)
        if (start != null) {
            println("Start button case")
            screen.click()
            screen.type(Key.ESC)
            screen.wait(Pattern("sync_swap_connect_wallet.png").targetOffset(-136, 0))
            screen.click()
            screen.wait("sync_swap_scroll_button.png")
            screen.click()
            screen.wait("sync_swap_connect_wallet.png")
            screen.click()
            screen.wait("sync_swap_metamask_button.png")
            screen.click()
/*
            metamaskLanguage = metamaskUnlock(screen)
            println("Metamask language = $metamaskLanguage")
            //metamaskGotIt(screen)
            metamaskNext(screen, metamaskLanguage)
            metamaskConnect(screen, metamaskLanguage)
            screen.wait("sync_swap_try_unlock_button.png")
            screen.click()
*/
        }

        metamaskLanguage = metamaskUnlock(screen)
        println("Metamask language = $metamaskLanguage")
        screen.wait(2.5)
        if (metamaskIsOpened(screen, 4.0)) {
            metamaskScroll(screen, 3, delayBeforeScroll = 1.0)
            screen.wait(0.5)
            metamaskReject(screen, metamaskLanguage)
            metamaskNext(screen, metamaskLanguage)
            metamaskConnect(screen, metamaskLanguage)
            metamaskAllowAddNetwork(screen, metamaskLanguage)
            metamaskSwitchNetwork(screen, metamaskLanguage)
        } else {
            //metamaskGotIt(screen)
            /*
                        screen.wait("sync_swap_connect_wallet.png")
                        screen.click()
                        screen.wait("sync_swap_metamask_button.png")
                        screen.click()
            */
            val switch = screen.exists("sync_swap_switch_network_button.png", 4.0)
            if (switch != null) {
                println("Switch case")
                screen.click()
                metamaskAllowAddNetwork(screen, metamaskLanguage)
                metamaskSwitchNetwork(screen, metamaskLanguage)
            } else {
                println("No switch case")
            }
        }
        screen.wait("sync_swap_25.png")
        screen.wait(2.0)
        println("Click 25%")
        screen.click()
        screen.wait("sync_swap_swap_button.png")
        println("Click swap button")
        screen.click()
        metamaskScroll(screen, 3, delayBeforeScroll = 0.5)
        screen.wait(0.5)
        metamaskConfirm(screen, metamaskLanguage)
        println("Metamask confirmed, wait transaction...")
        screen.wait("sync_swap_swapped.png", 32.0)
        println("Swapped")
        screen.type(Key.ESC)
        screen.wait("sync_swap_pool.png")
        println("Click Pool")
        screen.click()
        screen.wait("sync_swap_new_position_button.png")
        println("Click New position button")
        screen.click()
        screen.wait("sync_swap_enter_pool_button.png")
        println("Click Enter pool button")
        screen.click()
        screen.wait(1.0)
        screen.wait("sync_swap_deposit.png")
        println("Click Deposit")
        screen.click()
        screen.wait(Pattern("sync_swap_tokens_to_deposit.png").targetOffset(-35, 273))
        println("Click balanced proportion switch")
        screen.click()
        screen.wait(Pattern("sync_swap_tokens_to_deposit.png").targetOffset(287, 56))
        println("Click USDC max button")
        screen.click()
        screen.wait(Pattern("sync_swap_tokens_to_deposit.png").targetOffset(125, 330))
        println("Click Deposit button")
        screen.click()
        metamaskScroll(screen, 3, delayBeforeScroll = 0.5)
        screen.wait(0.5)
        metamaskConfirm(screen, metamaskLanguage)
        println("Metamask confirmed, wait transaction...")
        screen.wait(Pattern("sync_swap_confirmed.png").similar(0.8), 32.0)
        println("Confirmed")
        screen.type(Key.ESC)
        screen.wait(Pattern("sync_swap_tokens_to_deposit.png").targetOffset(-215, -55))
        println("Click My position")
        screen.click()
        screen.wait(3.0)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        errorList.add(number)
        println("profile $number: script ended with ERROR, added to error list")
        //closeProfileWithoutDriver(number)
    } else {
        println("profile $number script ended without errors")
        closeProfileWithoutDriver(number)
    }
}