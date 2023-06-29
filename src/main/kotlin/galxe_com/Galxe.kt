package galxe_com

/*
import ads_std.*
import kotlinx.coroutines.delay
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
*/

suspend fun galxeScript(number: Int) {
    //val driver = openProfile(number)
    //val username = TwitLogins.getLogin(number)

    //val metaMaskPassInput = By.ByXPath("//*[@id=\"password\"]")
    //val metaMaskNextButton = By.ByXPath("//*[@id=\"app-content\"]/div/div[2]/div/div[3]/div[2]/button[2]")
    //val metaMaskConnectButton =
        //By.ByXPath("//*[@id=\"app-content\"]/div/div[2]/div/div[2]/div[2]/div[2]/footer/button[2]")
    //val navBarWithAvatarImage = By.ByXPath("//*[@id=\"topNavbar\"]/div/div[2]/div[2]")
    //val popupMenu = By.ByXPath("//*[@id=\"topNavbar\"]/div/div[3]/div")
    //val usernameInput =
        //By.ByXPath("//*[@id=\"app\"]/div[3]/div/div/div/div/div[1]/div[2]/div/div/div/div/div/label/div/div/input")
    //val usernameSaveButton = By.ByXPath("//*[@id=\"app\"]/div[3]/div/div/div/div/div[2]/button")
    //val metaMaskSignatureRequestSignButton = By.ByXPath("//*[@id=\"app-content\"]/div/div[2]/div/div[3]/button[2]")
/*

    println("profile $number: start galxe.com $username script on thread ${Thread.currentThread().name}")
    driver.manage().window().maximize()
    closeAllTabs(driver)
    delay(3000)
    driver.get("https://galxe.com")
    val galxeWindow = driver.windowHandle
    delay(5000)

    if (driver.windowHandles.size > 1) {
        println("profile $number: input metamask password")
        nextTab(driver)
        WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(metaMaskPassInput))
            .sendKeys(WALLET_PASS, Keys.ENTER)
        driver.switchTo().window(galxeWindow)
        delay(5000)
    }

    if (driver.windowHandles.size > 1) {
        println("profile $number: connect metamask to account")
        nextTab(driver)
        WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(metaMaskNextButton))
            .click()
        WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(metaMaskConnectButton))
            .click()
        driver.switchTo().window(galxeWindow)
    }

    println("profile $number: open popup menu")
    Actions(driver)
        .moveToElement(
            WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(navBarWithAvatarImage))
        )
        .perform()

    Actions(driver)
        .moveToElement(
            WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(popupMenu))
        )
        .moveByOffset(0, -100)
        .click()
        .perform()

    println("profile $number: input username")
    WebDriverWait(driver, Duration.ofSeconds(15))
        .until(ExpectedConditions.visibilityOfElementLocated(usernameInput))
        .sendKeys(username)

    driver.findElement(usernameSaveButton)
        .click()
    delay(4000)

    if (driver.windowHandles.size > 1) {
        println("profile $number: sign username in metamask")
        nextTab(driver)
        val signButton = WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.presenceOfElementLocated(metaMaskSignatureRequestSignButton))
        Actions(driver)
            .scrollToElement(signButton)
            .perform()
        signButton.click()
        driver.switchTo().window(galxeWindow)
    }
    delay(4000)
    closeProfile(number, driver)
    profileWork = false
*/
}