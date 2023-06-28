package ads_std

import ads_api.AdsApiStore
import org.sikuli.script.ImagePath
import org.sikuli.script.Key
import org.sikuli.script.Mouse
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun openProfileWithoutDriver(number: Int, x: Int = 0, y: Int = 0, w: Int = 960, h: Int = 540): String {
    println("start profile $number, wait response...")
    val response = AdsApiStore.api.openProfile(
        profiles[number - 1], 1, 1,
        "[\"--window-position=$x,$y\", \"--window-size=$w,$h\"]"
    )
    println("get open response profile $number: ${response.msg}")
    return response.msg
}

suspend fun closeProfileWithoutDriver(number: Int): String {
    println("close profile $number, wait response...")
    val response = AdsApiStore.api.closeProfile(profiles[number - 1])
    println("get close response profile $number: ${response.msg}")
    return response.msg
}

fun openUrlSikuli(screen: Screen, url: String) {
    println("Open URL $url")
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(Pattern("browser_refresh_button.png").targetOffset(200, 0), 24.0)
    screen.click()
    screen.paste(url)
    screen.type(Key.ENTER)
}

suspend fun openUrlSikuliDark(screen: Screen, url: String) {
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(Pattern("browser_refresh_button_dark.png").targetOffset(200, 0), 24.0)
    screen.queueTakeClick()
    println("Open URL $url")
    screen.paste(url)
    screen.type(Key.ENTER)
    screen.queueRelease()
}

suspend fun openExtension(screen: Screen, extension: Pattern) {
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait(Pattern("browser_extensions.png").similar(0.95))
    screen.queueTakeClick()
    screen.wait(0.5)
    screen.wait(extension)
    screen.queueClickRelease()
}

suspend fun extensionOpened(screen: Screen): Boolean {
    ImagePath.add("src/main/kotlin/ads_std/png")
    return screen.exists(Pattern("browser_extesion_opened.png").similar(0.98)) != null
}

suspend fun scrollBrowser(screen: Screen, steps: Int) {
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.queueTakeAndWait()
    screen.wait(Pattern("browser_extensions.png").targetOffset(0, 160))
    screen.mouseMove()
    screen.wheel(Mouse.WHEEL_DOWN, steps)
    screen.wait(0.5)
    screen.queueClickRelease()
}

suspend fun openExtensionNotRelease(screen: Screen, extension: Pattern) {
    ImagePath.add("src/main/kotlin/ads_std/png")
    screen.wait("browser_extensions.png")
    screen.queueTakeClick()
    screen.wait(extension)
    screen.click()
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

suspend fun tryToClickQueue(screen: Screen, url: Pattern, time: Double = 3.0): Boolean {
    ImagePath.add("src/main/kotlin/ads_std/png")
    val click = screen.exists(url, time)
    return if (click != null) {
        println("Click ${url.filename.split("\\").last()}")
        screen.queueTakeClickRelease()
        true
    } else {
        println("Not founded ${url.filename.split("\\").last()}")
        false
    }
}

suspend fun tryToClick(screen: Screen, url: Pattern, time: Double = 2.0): Boolean {
    ImagePath.add("src/main/kotlin/ads_std/png")
    val click = screen.exists(url, time)
    return if (click != null) {
        println("Click ${url.filename.split("\\").last()}")
        screen.click()
        true
    } else {
        println("Not founded ${url.filename.split("\\").last()}")
        false
    }
}

suspend fun twitFollow(screen: Screen) {
    tryToClickQueue(screen, Pattern("follow.png"))
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

fun formWorkingRegions(
    lines: Int,
    rows: Int,
    x: Int = 0,
    y: Int = 0,
    w: Int,
    h: Int,
    horizontalSpace: Int = 0,
    verticalSpace: Int = 0,
    screenAdditionalWidth: Int = 0
): MutableList<WorkRegion> {
    val workRegions = mutableListOf<WorkRegion>()
    for (line in 1..lines) {
        for (row in 1..rows) {
            val screen = Screen()
            screen.w = w + screenAdditionalWidth
            screen.h = h
            screen.x = x + (row - 1) * (w + horizontalSpace + screenAdditionalWidth)
            screen.y = y + (line - 1) * (h + verticalSpace)
            val workRegion = WorkRegion(line, row, screen, screenAdditionalWidth)
            workRegions.add(workRegion)
        }
    }
    return workRegions
}