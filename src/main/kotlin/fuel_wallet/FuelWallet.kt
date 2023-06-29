/*
package fuel_wallet

import ads_std.*
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Mouse.WHEEL_DOWN
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun fuelScript(number: Int) {
    val seed = FuelSeeds.getSeed(number)
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    closeAllTabs(driver)
    driver.manage().window().maximize()
    driver.get("chrome-extension://keomgfjjdfafellomeiicgdkjnedoadc/index.html#/sign-up/welcome")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/fuel_wallet/png")
    try {
        val createWallet = screen.exists("i_have_wallet.png", 7.0)
        if (createWallet != null) {
            println("profile $number: create wallet case")
            screen.click()
            screen.wait(Pattern("will_need_next_step.png").similar(0.9).targetOffset(-110, 65))
            screen.click()
            screen.paste(seed)
            screen.wait("fuel_next_button.png")
            screen.click()
            screen.wait("type_password.png")
            screen.click()
            screen.paste(WALLET_PASS)
            screen.wait("confirm_password.png")
            screen.click()
            screen.paste(WALLET_PASS)
            screen.wait(Pattern("i_agree.png").targetOffset(-55, 0))
            screen.click()
            screen.wait("fuel_next_button.png")
            screen.click()
        }
        screen.wait("wallet_created.png", 10.0)
        screen.wait("extensions.png")
        screen.click()
        screen.wait("fuel_wallet.png")
        screen.click()
        screen.wait(4.0)
        val faucet = screen.exists("fuel_faucet_button.png")
        if (faucet != null) {
            println("profile $number: faucet case")
            screen.wait(0.5)
            screen.click()
            val captchaWork = screen.exists("captcha_work.png", 5.0)
            if (captchaWork != null) {
                println("profile $number: captcha work case")
                screen.wait(Pattern("solved.png").similar(0.95), 180.0)
            } else {
                println("profile $number: without captcha case")
                screen.wait(Pattern("i_am_not_robot.png").targetOffset(-60, 0))
            }
            screen.wait("give_me_ether.png")
            screen.click()
            screen.wait(Pattern("get_test_ether.png").similar(0.95), 180.0)
        }
        closeOtherTabs(driver)
*/
/*
        driver.get("https://fuels-wallet.vercel.app/docs/how-to-use/")
        screen.wait("fuel_connect.png", 7.0)
        screen.wait(1.0)
        screen.click()
        val connectWindow = screen.exists(Pattern("connect_window.png").similar(0.95), 5.0)
        if (connectWindow != null) {
            screen.click()
        }
        screen.wheel("check_it_working.png", WHEEL_DOWN, 9)
        screen.wait(1.0)
        screen.wait("get_accounts.png")
        screen.click()
        screen.wait(1.0)
        screen.wheel("check_it_working.png", WHEEL_DOWN, 6)
        screen.wait(1.0)
        screen.wait("sign_message.png")
        screen.click()
        screen.wait(1.0)
        screen.wait("sign_window.png")
        screen.click()
        screen.wait("type_your_password.png")
        screen.click()
        screen.paste(WALLET_PASS)
        screen.wait("unlock.png")
        screen.click()
        screen.wait(3.0)
        screen.wheel("check_it_working.png", WHEEL_DOWN, 6)
        screen.wait(1.0)
        screen.wait("transfer.png")
        screen.click()
        screen.wait("confirm_window.png", 45.0)
        screen.click()
        screen.wait("type_your_password.png")
        screen.click()
        screen.paste(WALLET_PASS)
        screen.wait("confirm_transaction.png")
        screen.click()
        screen.wait("see_on_blockexplorer.png", 45.0)
        screen.wait(1.5)
*//*

    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }
    profileWork = false
    if (isError) {
        driver.quit()
    } else {
        closeProfile(number, driver)
    }
}*/
