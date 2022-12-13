package ads_std

import ads_api.AdsApiStore
import kotlinx.coroutines.delay
import org.openqa.selenium.WindowType
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.time.Duration

suspend fun openProfile(number: Int): ChromeDriver {
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
    response = AdsApiStore.api.openProfile(profiles[number - 1])
    println("get response profile $number: ${response.msg}")
    response.data?.webdriver?.let { System.setProperty("webdriver.chrome.driver", it) }
    val options = ChromeOptions()
    options.setExperimentalOption("debuggerAddress", response.data?.ws?.selenium)
    println("profile $number driver created.")
    val driver = ChromeDriver(options)
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT))
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
    driver.manage().window().maximize()
    return driver
}

suspend fun closeProfile(number: Int, driver: ChromeDriver){
    driver.quit()
    val response = AdsApiStore.api.closeProfile(profiles[number - 1])
    println("profile $number closed: ${response.msg}")
}

fun closeTabs(driver: ChromeDriver){
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