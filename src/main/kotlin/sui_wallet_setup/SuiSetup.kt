package sui_wallet_setup

import ads_std.*
import kotlinx.coroutines.delay
import org.sikuli.script.*

suspend fun antiCaptcha(number: Int) {
    val seed = SuiSeeds.getSeed(number)
    val driver = openProfile(number)
    println("profile $number: start sui script on thread ${Thread.currentThread().name}")
    closeTabs(driver)
    driver.get("chrome-extension://kkedgchhdjjiopemikejclildleojceh/ui.html#/welcome")
    delay(3000)
    val screen = Screen()
    ImagePath.add("src/main/kotlin/sui_wallet_setup/png")
    try {
        screen.wait("get_started_btton.png")
        screen.click()
        screen.wait("import_existing_wallet_button.png")
        screen.click()
        screen.wait("enter_recovery_phrase.png")
        screen.click()
        screen.write(seed)
        screen.wait("continue_button.png")
        screen.click()
        screen.wait("create_password.png")
        screen.click()
        screen.write(META_MASK_PASS)
        screen.wait("confirm_password.png")
        screen.click()
        screen.write(META_MASK_PASS)
        screen.wait("import_button.png")
        screen.click()
        screen.wait("open_sui_wallet.png")
        screen.click()
        println("profile $number: seed inputted")
    } catch (e: FindFailed) {
        e.printStackTrace()
    }
    delay(2000)
    closeProfile(number, driver)
    profileWork = false
}
