package fuel_wallet

import ads_std.*
import org.sikuli.script.*

suspend fun createWalletScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    var color = backgroundGreen
    println(
        color + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundDefault
    )
    val seed = FuelSeeds.getSeed(workRegion.profile)
    try {
        browserOpenUrl(screen, "chrome-extension://keomgfjjdfafellomeiicgdkjnedoadc/index.html#/sign-up/welcome")
        val createWallet = screen.exists("i_have_wallet.png", 7.0)
        if (createWallet != null) {
            println("profile ${workRegion.profile}: create wallet case")
            screen.queueTakeClick()
            screen.type(Key.END, Key.CTRL)
            screen.queueRelease()
            screen.wait("fuel_i_accept_button.png")
            screen.queueTakeClickRelease()
            screen.wait(Pattern("fuel_i_have_12_words.png").similar(0.95).targetOffset(-180, 45))
            screen.queueTakeClick()
            screen.paste(seed)
            screen.queueRelease()
            screen.wait("fuel_next_button.png")
            screen.queueTakeClickRelease()
            screen.wait("type_password.png")
            screen.queueTakeClick()
            screen.paste(WALLET_PASS_UP)
            screen.queueRelease()
            screen.wait("confirm_password.png")
            screen.queueTakeClick()
            screen.paste(WALLET_PASS_UP)
            screen.queueRelease()
            screen.wait("fuel_next_button.png")
            screen.queueTakeClickRelease()
            screen.wait("wallet_created.png", 18.0)
            screen.wait("extensions.png")
            screen.queueTakeClick()
            screen.wait("fuel_wallet.png")
            screen.queueClickRelease()
        } else {
            screen.wait("wallet_created.png", 18.0)
            openFuelWallet(screen)
        }

        val faucet = screen.exists("fuel_faucet_button.png", 7.0)
        if (faucet != null) {
            println("profile ${workRegion.profile}: faucet case")
            screen.queueTakeClickRelease()
            screen.wait(
                Pattern("i_am_not_robot.png")
                    .similar(0.98), 60.0
            )
            screen.wait("give_me_ether.png")
            screen.queueTakeClickRelease()
            screen.wait("see_on_explorer.png", 8.0)
            screen.wait("extensions.png")
            screen.queueTakeClick()
            screen.wait("fuel_wallet.png")
            screen.queueClickRelease()
        }
        screen.wait(12.0)
    } catch (e: FindFailed) {
        println(backgroundRed + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        if (workQueue.peek() == screen) {
            workQueue.poll()
        }
        errorList.add(workRegion.profile)
    }
    println(
        color + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundDefault
    )
}

suspend fun swayScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    var color = backgroundGreen
    println(
        color + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundDefault
    )
    try {
        screen.wait(3.0)
        openFuelWallet(screen)
        screen.wait(3.0)
        browserOpenUrl(screen, "https://app.swaylend.com/#/faucet")
        screen.wait(2.0)
        screen.wait("sway_menu.png")
        screen.queueTakeClickRelease()
        val connectWallet = screen.exists(Pattern("sway_connect_wallet.png").similar(0.95))
        if (connectWallet != null) {
            screen.queueTakeClickRelease()
            screen.wait("sway_fuel_wallet.png")
            screen.queueTakeClickRelease()
            val next = screen.exists("fuel_next_button.png", 8.0)
            if (next != null) {
                screen.queueTakeClickRelease()
            }
            val connect = screen.exists("fuel_connect.png")
            if (connect != null) {
                screen.queueTakeClickRelease()
            }
        }
        screen.wait(Pattern("sway_menu_close.png").similar(0.98))
        screen.queueTakeClickRelease()
        val usdc0 = screen.exists(Pattern("sway_0_usdc.png").similar(0.95).targetOffset(150, 0))
        if (usdc0 != null) {
            screen.queueTakeClickRelease()
            screen.wait("fuel_add_assets_button.png", 8.0)
            screen.queueTakeClickRelease()
            screen.wait("fuel_approve_button.png", 8.0)
            screen.wait(0.5)
            screen.queueTakeClickRelease()
        }
        screen.wait("sway_minted.png", 120.0)
        screen.wait(3.0)
        browserOpenUrl(screen, "https://app.swaylend.com/#/dashboard")
        screen.wait(3.0)
        val withdrawUSDC = screen.exists(Pattern("sway_withdraw_usdc.png").similar(0.97))
        if (withdrawUSDC == null) {
            screen.wait("sway_supply_usdc.png")
            screen.queueTakeClickRelease()
            screen.wait("sway_max.png")
            screen.queueTakeClickRelease()
            val supplyButton = screen.exists("sway_supply_button.png")
            if (supplyButton != null) {
                screen.queueTakeClickRelease()
                screen.wait("fuel_approve_button.png", 8.0)
                screen.wait(0.5)
                screen.queueTakeClickRelease()

            }
            screen.wait(Pattern("sway_withdraw_usdc.png").similar(0.97), 120.0)
            screen.wait(4.0)
        }
        browserOpenUrl(screen, "https://app.swaylend.com/#/dashboard")
        val eth05 = screen.exists(
            Pattern("sway_eth_0_5.png")
                .similar(0.99)
                .targetOffset(435, 0)
        )
        screen.wait(3.0)
        if (eth05 != null) {
            screen.queueTakeClickRelease()
            screen.wait(Pattern("sway_supply_eth.png").targetOffset(0, 42))
            screen.queueTakeClick()
            screen.type("0.1")
            screen.wait("sway_supply_button.png")
            screen.queueClickRelease()
            screen.wait("fuel_approve_button.png", 8.0)
            screen.wait(0.5)
            screen.queueTakeClickRelease()
            screen.wait(Pattern("sway_withdraw_usdc.png").similar(0.97), 120.0)
        }

        screen.wait("sway_borrow.png")
        screen.queueTakeClickRelease()
        screen.wait("sway_borrow_usdc_button.png")
        screen.queueTakeClickRelease()
        screen.wait(Pattern("sway_borrow_usdc.png").targetOffset(0, 42))
        screen.queueTakeClick()
        screen.type("40")
        screen.wait("sway_borrow_button.png")
        screen.queueClickRelease()
        screen.wait("fuel_approve_button.png", 8.0)
        screen.wait(0.5)
        screen.queueTakeClickRelease()
        screen.wait("sway_borrow_usdc_button.png", 120.0)
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
        color + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundDefault
    )
}

private suspend fun openFuelWallet(screen: Screen) {
    screen.wait("extensions.png")
    screen.queueTakeClick()
    screen.wait("fuel_wallet.png")
    screen.queueClickRelease()
    screen.wait("type_password.png")
    screen.queueTakeClick()
    screen.paste(WALLET_PASS_UP)
    screen.type(Key.ENTER)
    screen.queueRelease()
}
