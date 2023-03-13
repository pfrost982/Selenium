package ads_std

import ads_api.AdsApiStore
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

suspend fun openProfileWithoutDriver(number: Int) {
    println("start profile $number, wait response...")
    val response = AdsApiStore.api.openProfile(profiles[number - 1], 1)
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

fun openMetamask(screen: Screen) {
    println("Open metamask")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait("extensions.png")
    screen.click()
    screen.wait("extensions_metamask.png")
    screen.click()
}

fun unlockMetamask(screen: Screen) {
    println("Unlock metamask")
    screen.wait("metamask_password_input.png", 24.0)
    screen.paste(WALLET_PASS)
    screen.type(Key.ENTER)
}

fun metamaskCloseInformation(screen: Screen): Boolean {
    println("Scan metamask information...")
    ImagePath.add("src/main/kotlin/ads_std/png")
    val information = screen.exists(Pattern("metamask_close_information_button.png").similar(0.95), 5.0)
    return if (information != null) {
        println("Information founded")
        screen.click()
        true
    } else {
        println("Information not founded")
        false
    }
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

fun tryToClick(screen: Screen, url: Pattern) {
    ImagePath.add("src/main/kotlin/ads_std/png")
    val click = screen.exists(url, 7.0)
    if (click != null) {
        println("Click ${url.filename}")
        screen.click()
    } else {
        println("${url.filename} not founded")
    }
}

fun metamaskNext(screen: Screen) {
    tryToClick(screen, Pattern("metamask_next_button.png"))
}

fun metamaskConnect(screen: Screen) {
    tryToClick(screen, Pattern("metamask_connect_button.png"))
}

fun metamaskScroll(screen: Screen, steps: Int) {
    println("Metamask scroll")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(Pattern("metamask_handler_icon.png").targetOffset(100, 100), 7.0)
    screen.mouseMove()
    screen.wheel(Mouse.WHEEL_DOWN, steps)
}

fun metamaskApprove(screen: Screen) {
    tryToClick(screen, Pattern("metamask_approve_button.png"))
}

fun metamaskSwitchNetwork(screen: Screen) {
    tryToClick(screen, Pattern("metamask_switch_network_button.png"))
}

fun metamaskSign(screen: Screen) {
    tryToClick(screen, Pattern("metamask_sign_button.png"))
}