package github

import ads_std.*
import org.sikuli.script.Key
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun openGithub(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    browserOpenUrl(screen, "https://github.com/login")
    screen.wait(Pattern("github_sign_in_to_github.png").targetOffset(0, 94), 12.0)
    screen.wait(1.0)
    screen.queueTakeClick()
    screen.type("a", Key.CTRL)
    screen.paste(GMails.getGMail(workRegion.profile))
    screen.queueRelease()
    screen.wait(Pattern("github_sign_in_to_github.png").targetOffset(0, 174))
    screen.queueTakeClick()
    screen.type("a", Key.CTRL)
    screen.paste(GITHUB_PASS)
    screen.queueRelease()
    screen.wait(Pattern("sign_in_button.png"))
    screen.queueTakeClickRelease()
    screen.wait("github_create_repository_button.png", 12.0)
    screen.wait(3.0)
}

suspend fun forkGithub(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    val num1 = (1..100).random()
    var num2: Int
    do {
        num2 = (1..100).random()
    } while (num2 == num1)
    val repository1 = Repositories.getRepository(num1)
    val repository2 = Repositories.getRepository(num2)
    println("Profile ${workRegion.profile} repository1: $repository1")
    println("Profile ${workRegion.profile} repository2: $repository2")
    fork(screen, repository1)
    fork(screen, repository2)
}

private suspend fun fork(screen: Screen, repository: String) {
    browserOpenUrl(screen, repository)
    screen.wait("github_fork.png", 24.0)
    screen.wait(2.0)
    screen.wait("github_fork.png")
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait("github_create_fork_button.png", 12.0)
    screen.wait(1.0)
    screen.queueTakeClickRelease()
    screen.wait(3.0)
    screen.wait(Pattern("github_code_button.png").similar(0.94), 12.0)
    screen.wait(2.0)
}
