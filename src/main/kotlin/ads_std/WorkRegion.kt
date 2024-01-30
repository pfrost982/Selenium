package ads_std

import org.sikuli.script.Screen

class WorkRegion(val line: Int, val row: Int, val screen: Screen, val screenAdditionalWidth: Int, var profile: Int = 0)

fun WorkRegion.println(
    message: String,
    foreground: String = foregroundDefault,
    background: String = backgroundDefault
) {
    kotlin.io.println(foreground + background + "Profile ${this.profile}: $message" + foregroundDefault + backgroundDefault)
}