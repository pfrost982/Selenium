package stargate

import ads_std.*
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Screen

suspend fun stargateGuildScript(number: Int) {
    isError = false
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/stargate/png")
    try {
        closeTabsSikuliX(screen)
        openURLsikuliX(screen, "https://guild.xyz/stargate")
        screen.wait("guild_connect_wallet.png", 24.0)
        screen.click()
        screen.wait("guild_metamask_button.png")
        screen.click()
        val metamaskLanguage = metamaskUnlock(screen)
        println("Metamask language = $metamaskLanguage")
        val verify = screen.exists("guild_verify_account.png", 8.0)
        if (verify != null) {
            println("Verify founded")
            screen.click()
            screen.wait(3.0)
            metamaskGotIt(screen)
            metamaskScroll(screen, 3)
            screen.wait(0.5)
            metamaskSign(screen, metamaskLanguage)
        } else {
            println("Verify not founded")
        }
        screen.wait("guild_you_have_access.png", 8.0)
        println("You have access")
        screen.wait("guild_join_to_get_roles.png")
        screen.click()
        screen.wait("guild_join.png")
        screen.click()
        screen.wait("guild_joined.png", 12.0)
        screen.wait(3.0)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        errorList.add(number)
        println("profile $number: ended with ERROR, added to error list")
        //closeProfileWithoutDriver(number)
    } else {
        println("profile $number registered!")
        closeProfileWithoutDriver(number)
    }
}