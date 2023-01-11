package fuel_wallet

import ads_std.*
import org.sikuli.script.*

suspend fun fuelScript(number: Int) {
    val seed = Seeds.getSeed(number)
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    closeAllTabs(driver)
    driver.manage().window().maximize()
    driver.get("chrome-extension://keomgfjjdfafellomeiicgdkjnedoadc/index.html#/sign-up/welcome")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/fuel_wallet/png")
    try {
        screen.wait("i_have_wallet.png", 7.0)
        screen.click()
        screen.wait(Pattern("will_need_next_step.png").similar(0.9).targetOffset(-110, 65))
        screen.click()
        insertTextTroughClipboard(screen, seed)
        screen.wait("next.png")
        screen.click()
        screen.wait("type_password.png")
        screen.click()
        insertTextTroughClipboard(screen, WALLET_PASS)
        screen.wait("confirm_password.png")
        screen.click()
        insertTextTroughClipboard(screen, WALLET_PASS)
        screen.wait(Pattern("i_agree.png").targetOffset(-55, 0))
        screen.click()
        screen.wait("next.png")
        screen.click()
        screen.wait("wallet_created.png", 10.0)
        screen.wait("extensions.png")
        screen.click()
        screen.wait("fuel_wallet.png")
        screen.click()
        screen.wait("faucet.png", 7.0)
        screen.click()
        screen.wait(Pattern("solved.png").similar(0.9), 180.0)
        screen.wait("give_me_ether.png")
        screen.click()
/*
        driver.get("https://fuels-wallet.vercel.app/docs/how-to-use/")
        screen.wait("connect.png", 7.0)
        screen.click()
        screen.wait(Pattern("connect_window.png").similar(0.95), 7.0)
        screen.click()
*/


    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }
    profileWork = false

    isError = true //

    if (isError) {
        driver.quit()
    } else {
        closeProfile(number, driver)
    }
}