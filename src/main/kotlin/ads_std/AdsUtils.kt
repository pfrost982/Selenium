package ads_std

import ads_api.AdsApiStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WindowType
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.sikuli.script.*
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.time.Duration


suspend fun openProfile(number: Int): ChromeDriver {
    /*
        println("start profile $number...")
        var response = AdsApiStore.api.checkOpenStatusProfile(profiles[number - 1])
        println("profile $number status: ${response.data?.status}")
        if (response.data?.status.contentEquals("Active")) {
            response.data?.webdriver?.let { System.setProperty("webdriver.chrome.driver", it) }
            val options = ChromeOptions()
            options.setExperimentalOption("debuggerAddress", response.data?.ws?.selenium)
            println("profile $number driver created.")
            return ChromeDriver(options)
        }
        delay(2000L)
    */
    println("start profile $number, wait response...")
    val response = AdsApiStore.api.openProfile(profiles[number - 1], 1)
    println("get response profile $number: ${response.msg}")
    response.data?.webdriver?.let { System.setProperty("webdriver.chrome.driver", it) }
    val options = ChromeOptions()
    options.setExperimentalOption("debuggerAddress", response.data?.ws?.selenium)
    options.setPageLoadStrategy(PageLoadStrategy.EAGER)
    val driver = ChromeDriver(options)
    println("profile $number driver created.")
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT))
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
    return driver
}

suspend fun openProfileWithoutDriver(number: Int, x: Int = 0, y: Int = 0, w: Int = 960, h: Int = 540) {
    println("start profile $number, wait response...")
    val response = AdsApiStore.api.openProfile(
        profiles[number - 1], 1, 1,
        "[\"--window-position=$x,$y\", \"--window-size=$w,$h\"]"
    )
    println("get response profile $number: ${response.msg}")
}

suspend fun closeProfile(number: Int, driver: ChromeDriver) {
    driver.quit()
    val response = AdsApiStore.api.closeProfile(profiles[number - 1])
    println("profile $number closed: ${response.msg}")
}

suspend fun closeProfileWithoutDriver(number: Int) {
    val response = AdsApiStore.api.closeProfile(profiles[number - 1])
    println("profile $number closed: ${response.msg}")
}

fun closeAllTabs(driver: ChromeDriver) {
    driver.switchTo().newWindow(WindowType.TAB)
    val newTab = driver.windowHandle
    for (windowHandle in driver.windowHandles) {
        if (!newTab!!.contentEquals(windowHandle)) {
            driver.switchTo().window(windowHandle)
            driver.close()
        }
    }
    driver.switchTo().window(newTab)
}

fun closeOtherTabs(driver: ChromeDriver) {
    val currentTab = driver.windowHandle
    for (windowHandle in driver.windowHandles) {
        if (!currentTab!!.contentEquals(windowHandle)) {
            driver.switchTo().window(windowHandle)
            driver.close()
        }
    }
    driver.switchTo().window(currentTab)
}

fun nextTab(driver: ChromeDriver) {
    val currentTab = driver.windowHandle
    for (windowHandle in driver.windowHandles) {
        if (!currentTab!!.contentEquals(windowHandle)) {
            driver.switchTo().window(windowHandle)
            return
        }
    }
}

fun insertTextTroughClipboard(screen: Screen, text: String) {
    putTextInClipboard(text)
    screen.type("a", Key.CTRL)
    screen.type("v", Key.CTRL)
}

fun putTextInClipboard(text: String) {
    val selection = StringSelection(text)
    val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(selection, selection)
}

fun openURLsikuliX(screen: Screen, url: String) {
    println("Open URL $url")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(Pattern("browser_refresh_button.png").targetOffset(200, 0), 24.0)
    screen.click()
    screen.paste(url)
    screen.type(Key.ENTER)
}

fun openURLsikuliXDark(screen: Screen, url: String) {
    println("Open URL $url")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(Pattern("browser_refresh_button_dark.png").targetOffset(200, 0), 24.0)
    screen.click()
    screen.paste(url)
    screen.type(Key.ENTER)
}

fun openMetamask(screen: Screen) {
    println("Open metamask")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait("extensions.png")
    screen.click()
    screen.wait("extensions_metamask.png")
    screen.click()
}

suspend fun metamaskUnlock(screen: Screen): String {
    var language: String? = null
    println("Unlock metamask")
    val en = CoroutineScope(Dispatchers.Default).launch {
        val inputPassword = screen.exists("metamask_password_input.png", 24.0)
        if (inputPassword != null) {
            language = "en"
        }
    }
    val ru = CoroutineScope(Dispatchers.Default).launch {
        val inputPassword = screen.exists("metamask_password_input_ru.png", 24.0)
        if (inputPassword != null) {
            language = "ru"
        }
    }
    while (language == null) {
        delay(500)
    }
    en.cancel()
    ru.cancel()
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
    return language as String
}

fun browserCloseLanguageSelection(screen: Screen): Boolean {
    println("Scan browser language selection...")
    ImagePath.add("src/main/kotlin/ads_std/png")
    val information = screen.exists(Pattern("browser_language_selection_close.png").similar(0.95), 5.0)
    return if (information != null) {
        println("Language selection founded")
        screen.click()
        true
    } else {
        println("Language selection not founded")
        false
    }
}

fun tryToClick(screen: Screen, url: Pattern, time: Double = 4.0): Boolean {
    ImagePath.add("src/main/kotlin/ads_std/png")
    val click = screen.exists(url, time)
    if (click != null) {
        println("Click ${url.filename.split("\\").last()}")
        screen.click()
        return true
    } else {
        println("Not founded ${url.filename.split("\\").last()}")
        return false
    }
}

fun metamaskCloseInformation(screen: Screen) {
    var thereWasInformation = tryToClick(screen, Pattern("metamask_close_information_button.png"))
    while (thereWasInformation) {
        thereWasInformation = tryToClick(screen, Pattern("metamask_close_information_button.png"))
    }
}

fun metamaskNext(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClick(screen, Pattern("metamask_next_button.png"))
        "ru" -> tryToClick(screen, Pattern("metamask_next_button_ru.png"))
    }
}

fun metamaskConnect(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClick(screen, Pattern("metamask_connect_button.png"))
        "ru" -> tryToClick(screen, Pattern("metamask_connect_button_ru.png"))
    }
}

fun metamaskApprove(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClick(screen, Pattern("metamask_approve_button.png"))
        "ru" -> tryToClick(screen, Pattern("metamask_approve_button_ru.png"))
    }
}

fun metamaskSwitchNetwork(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClick(screen, Pattern("metamask_switch_network_button.png"))
        "ru" -> tryToClick(screen, Pattern("metamask_switch_network_button_ru.png"))
    }
}

fun metamaskSign(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClick(screen, Pattern("metamask_sign_button.png"))
        "ru" -> tryToClick(screen, Pattern("metamask_sign_button_ru.png"))
    }
}

fun metamaskBigSign(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClick(screen, Pattern("metamask_big_sign_button.png"))
        "ru" -> tryToClick(screen, Pattern("metamask_big_sign_button_ru.png"))
    }
}

fun metamaskConfirm(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClick(screen, Pattern("metamask_confirm_button.png"))
        "ru" -> tryToClick(screen, Pattern("metamask_confirm_button_ru.png"))
    }
}

fun metamaskReject(screen: Screen, language: String = "en", time: Double = 3.0) {
    when (language) {
        "en" -> tryToClick(screen, Pattern("metamask_reject.png"), time)
        "ru" -> tryToClick(screen, Pattern("metamask_reject_ru.png"), time)
    }
}

fun metamaskConfirmUntilItDisappears(screen: Screen, language: String = "en") {
    var pattern = Pattern()
    when (language) {
        "en" -> pattern = Pattern("metamask_confirm_button.png")
        "ru" -> pattern = Pattern("metamask_confirm_button_ru.png")
    }
    var click = tryToClick(screen, pattern)
    while (click) {
        click = tryToClick(screen, pattern, 0.5)
    }
}

fun metamaskGotIt(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClick(screen, Pattern("metamask_got_it.png"))
        "ru" -> tryToClick(screen, Pattern("metamask_got_it_ru.png"))
    }
}

fun metamaskIsOpened(screen: Screen, time: Double = 7.0): Boolean {
    val metamask = screen.exists("metamask_handler_icon.png", time)
    if (metamask != null) {
        println("Metamask is opened")
        return true
    } else {
        println("Metamask is not opened")
        return false
    }
}
fun metamaskIsOpenedDark(screen: Screen, time: Double = 7.0): Boolean {
    val metamask = screen.exists("metamask_handler_icon_dark.png", time)
    if (metamask != null) {
        println("Metamask is opened")
        return true
    } else {
        println("Metamask is not opened")
        return false
    }
}

fun metamaskScroll(screen: Screen, steps: Int, offsetFromIcon: Int = 500, delayBeforeScroll: Double = 0.0) {
    println("Metamask scroll")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(Pattern("metamask_handler_icon.png").targetOffset(0, offsetFromIcon), 12.0)
    screen.mouseMove()
    screen.wait(delayBeforeScroll)
    screen.wheel(Mouse.WHEEL_DOWN, steps)
}

fun metamaskAllowAddNetwork(screen: Screen, language: String = "en") {
    var pattern = Pattern()
    println("Metamask add network")
    ImagePath.add("src/main/kotlin/ads_std/png")
    when (language) {
        "en" -> pattern = Pattern("metamask_allow_this_site.png")
        "ru" -> pattern = Pattern("metamask_allow_this_site_ru.png")
    }
    val allow = screen.exists(pattern, 7.0)
    if (allow != null) {
        println("Founded Allow add network")
        metamaskScroll(screen, 3)
        screen.wait(0.5)
        metamaskApprove(screen, language)
    } else {
        println("Not founded Allow add network")
    }
}

fun twitFollow(screen: Screen) {
    tryToClick(screen, Pattern("follow.png"))
}

fun closeTabsSikuliX(screen: Screen) {
    println("Close tabs")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(3.0)
    var firstTab = screen.exists("ads_account_id.png")
    while (firstTab == null) {
        screen.type(Key.F4, Key.CTRL)
        firstTab = screen.exists("ads_account_id.png", 0.5)
    }
}