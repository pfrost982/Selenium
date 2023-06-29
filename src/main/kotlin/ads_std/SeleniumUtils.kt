/*
package ads_std

import ads_api.AdsApiStore
import kotlinx.coroutines.delay
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WindowType
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.time.Duration

suspend fun openProfile(number: Int): ChromeDriver {
    val options = ChromeOptions()
    options.setPageLoadStrategy(PageLoadStrategy.EAGER)
    options.setExperimentalOption("excludeSwitches", "[\"enable-automation\"]")
    options.setExperimentalOption("useAutomationExtension", false)
    println("start profile $number...")
    var response = AdsApiStore.api.checkOpenStatusProfile(profiles[number - 1])
    println("profile $number status: ${response.data?.status}")
    if (response.data?.status.contentEquals("Active")) {
        response.data?.webdriver?.let { System.setProperty("webdriver.chrome.driver", it) }
        options.setExperimentalOption("debuggerAddress", response.data?.ws?.selenium)
        println("profile $number driver created.")
        return ChromeDriver(options)
    }
    delay(2000)
    println("start profile $number, wait response...")
    response = AdsApiStore.api.openProfile(profiles[number - 1], 1)
    println("get response profile $number: ${response.msg}")
    response.data?.webdriver?.let { System.setProperty("webdriver.chrome.driver", it) }
    options.setExperimentalOption("debuggerAddress", response.data?.ws?.selenium)
    val driver = ChromeDriver(options)
    println("profile $number driver created.")
*/
/*
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT))
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
*//*

    return driver
}

suspend fun closeProfile(number: Int, driver: ChromeDriver) {
    driver.quit()
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
}*/
