package ads_std

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sikuli.script.*

//chrome-extension://cknlgcbdfhknkoidcefkpmlplfndfmll/home.html#settings
suspend fun metamaskCloseInformation(screen: Screen) {
    var thereWasInformation = tryToClickQueue(screen, Pattern("metamask_close_information_button.png"))
    while (thereWasInformation) {
        thereWasInformation = tryToClickQueue(screen, Pattern("metamask_close_information_button.png"))
    }
}

suspend fun metamaskNext(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClickQueue(screen, Pattern("metamask_next_button.png"))
        "ru" -> tryToClickQueue(screen, Pattern("metamask_next_button_ru.png"))
    }
}

suspend fun metamaskConnect(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClickQueue(screen, Pattern("metamask_connect_button.png"))
        "ru" -> tryToClickQueue(screen, Pattern("metamask_connect_button_ru.png"))
    }
}

suspend fun metamaskApprove(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClickQueue(screen, Pattern("metamask_approve_button.png"))
        "ru" -> tryToClickQueue(screen, Pattern("metamask_approve_button_ru.png"))
    }
}

suspend fun metamaskSwitchNetwork(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClickQueue(screen, Pattern("metamask_switch_network_button.png"))
        "ru" -> tryToClickQueue(screen, Pattern("metamask_switch_network_button_ru.png"))
    }
}

suspend fun metamaskSign(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClickQueue(screen, Pattern("metamask_sign_button.png"))
        "ru" -> tryToClickQueue(screen, Pattern("metamask_sign_button_ru.png"))
    }
}

suspend fun metamaskBigSign(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClickQueue(screen, Pattern("metamask_big_sign_button.png"))
        "ru" -> tryToClickQueue(screen, Pattern("metamask_big_sign_button_ru.png"))
    }
}

suspend fun metamaskConfirm(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClickQueue(screen, Pattern("metamask_confirm_button.png"))
        "ru" -> tryToClickQueue(screen, Pattern("metamask_confirm_button_ru.png"))
    }
}

suspend fun metamaskReject(screen: Screen, language: String = "en", time: Double = 3.0) {
    when (language) {
        "en" -> tryToClickQueue(screen, Pattern("metamask_reject.png"), time)
        "ru" -> tryToClickQueue(screen, Pattern("metamask_reject_ru.png"), time)
    }
}

suspend fun metamaskConfirmUntilItDisappears(screen: Screen, language: String = "en", time: Double = 3.0) {
    var disappeared = false
    var pattern = Pattern()
    when (language) {
        "en" -> pattern = Pattern("metamask_confirm_button.png")
        "ru" -> pattern = Pattern("metamask_confirm_button_ru.png")
    }
    screen.wait("metamask_handler_icon_dark.png", time)
    val job = CoroutineScope(Dispatchers.Default).launch {
        screen.waitVanish("metamask_handler_icon_dark.png", 32.0)
        disappeared = true
    }
    screen.queueTakeAndWait()
    while (!disappeared) {
        tryToClick(screen, pattern, 0.5)
        screen.wait(0.5)
    }
    screen.queueRelease()
    job.cancel()
}

suspend fun metamaskGotIt(screen: Screen, language: String = "en") {
    when (language) {
        "en" -> tryToClickQueue(screen, Pattern("metamask_got_it.png"))
        "ru" -> tryToClickQueue(screen, Pattern("metamask_got_it_ru.png"))
    }
}

suspend fun metamaskMax(screen: Screen) {
    tryToClickQueue(screen, Pattern("metamask_max.png"))
}

fun metamaskIsOpened(screen: Screen, time: Double = 7.0): Boolean {
    val metamask = screen.exists("metamask_handler_icon.png", time)
    return if (metamask != null) {
        println("Metamask is opened")
        true
    } else {
        println("Metamask is not opened")
        false
    }
}

fun metamaskIsOpenedDark(screen: Screen, time: Double = 7.0): Boolean {
    val metamask = screen.exists("metamask_handler_icon_dark.png", time)
    return if (metamask != null) {
        println("Metamask is opened")
        true
    } else {
        println("Metamask is not opened")
        false
    }
}

suspend fun metamaskScroll(
    screen: Screen,
    steps: Int,
    yOffsetFromIcon: Int = 500,
    xOffsetFromIcon: Int = 0,
    delayBeforeScroll: Double = 0.0
) {
    println("Metamask scroll")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(Pattern("metamask_handler_icon.png").targetOffset(xOffsetFromIcon, yOffsetFromIcon), 12.0)
    screen.queueTakeAndWait()
    screen.mouseMove()
    screen.wait(delayBeforeScroll)
    screen.wheel(Mouse.WHEEL_DOWN, steps)
    screen.queueRelease()
    screen.wait(0.7)
}

suspend fun metamaskScrollDark(
    screen: Screen,
    steps: Int,
    yOffsetFromIcon: Int = 500,
    xOffsetFromIcon: Int = 0,
    delayBeforeScroll: Double = 0.0
) {
    println("Metamask scroll")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(Pattern("metamask_handler_icon_dark.png").targetOffset(xOffsetFromIcon, yOffsetFromIcon), 12.0)
    screen.queueTakeAndWait()
    screen.mouseMove()
    screen.wait(delayBeforeScroll)
    screen.wheel(Mouse.WHEEL_DOWN, steps)
    screen.queueRelease()
    screen.wait(0.5)
}

suspend fun metamaskAllowAddNetwork(screen: Screen, language: String = "en") {
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

suspend fun metamaskOpen(screen: Screen) {
    println("Open metamask")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait("extensions.png")
    screen.queueTakeClickRelease()
    screen.wait("extensions_metamask.png")
    screen.queueTakeClickRelease()
}

suspend fun metamaskUnlock(screen: Screen): String {
    var language: String? = null
    println("${screen.x}, ${screen.y} Unlock metamask")
    val en = CoroutineScope(Dispatchers.Default).launch {
        val inputPassword = screen.exists(Pattern("metamask_unlock.png").targetOffset(0, -75), 24.0)
        if (inputPassword != null) {
            language = "en"
        }
    }
    val ru = CoroutineScope(Dispatchers.Default).launch {
        val inputPassword = screen.exists(Pattern("metamask_unlock_ru.png").targetOffset(0, -75), 24.0)
        if (inputPassword != null) {
            language = "ru"
        }
    }
    while (language == null) {
        delay(500)
    }
    en.cancel()
    ru.cancel()
    screen.queueTakeClick()
    screen.paste(WALLET_META)
    screen.type(Key.ENTER)
    screen.queueRelease()
    return language as String
}

suspend fun metamaskUnlockEn(screen: Screen) {
    screen.wait(Pattern("metamask_unlock.png").targetOffset(0, -75), 24.0)
    screen.wait(0.5)
    screen.queueTakeClick()
    screen.paste(WALLET_PASS_HARD)
    screen.type(Key.ENTER)
    screen.queueRelease()
}