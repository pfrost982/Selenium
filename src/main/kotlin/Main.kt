import ads_std.closeProfile
import ads_std.closeTabs
import ads_std.openProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch(Dispatchers.Default) {
            val driver = openProfile(3)
            closeTabs(driver)
            driver.get("https://mail.ru")
            delay(10000L)
            closeProfile(3, driver)
        }
    }
}


