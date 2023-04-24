package ads_std

import kotlinx.coroutines.delay
import org.sikuli.script.Screen
import java.util.concurrent.ConcurrentLinkedQueue

val workQueue = ConcurrentLinkedQueue<Screen>()
val ApiRequestQueue = ConcurrentLinkedQueue<Screen>()
suspend fun queueOpenProfile(workRegion: WorkRegion) {
    ApiRequestQueue.add(workRegion.screen)
    queueWait(workRegion.screen, ApiRequestQueue)
    var response = openProfileWithoutDriver(
        workRegion.profile,
        workRegion.screen.x,
        workRegion.screen.y,
        workRegion.screen.w - workRegion.screenAdditionalWidth,
        workRegion.screen.h
    )
    while (response != "success") {
        delay(700)
        response = openProfileWithoutDriver(
            workRegion.profile,
            workRegion.screen.x,
            workRegion.screen.y,
            workRegion.screen.w - workRegion.screenAdditionalWidth,
            workRegion.screen.h
        )
    }
    ApiRequestQueue.poll()
}

suspend fun queueCloseProfileReleaseWorkRegion(workRegion: WorkRegion, freeWorkRegions: MutableList<WorkRegion>) {
    ApiRequestQueue.add(workRegion.screen)
    queueWait(workRegion.screen, ApiRequestQueue)
    var response = closeProfileWithoutDriver(workRegion.profile)
    while (response != "Success") {
        delay(700)
        response = closeProfileWithoutDriver(workRegion.profile)
    }
    freeWorkRegions.add(workRegion)
    ApiRequestQueue.poll()
}

suspend fun Screen.queueTakeClickRelease() {
    workQueue.add(this)
    queueWait(this, workQueue)
    this.click()
    workQueue.poll()
}

suspend fun Screen.queueTakeClick() {
    workQueue.add(this)
    queueWait(this, workQueue)
    this.click()
}

suspend fun Screen.queueClickRelease() {
    queueWait(this, workQueue)
    this.click()
    workQueue.poll()
}

suspend fun Screen.queueTakeAndWait() {
    workQueue.add(this)
    queueWait(this, workQueue)
}

suspend fun Screen.queueRelease() {
    queueWait(this, workQueue)
    workQueue.poll()
}

suspend fun queueWait(screen: Screen, queue: ConcurrentLinkedQueue<Screen>) {
    while (queue.peek() != screen) {
        delay(10)
    }
}
