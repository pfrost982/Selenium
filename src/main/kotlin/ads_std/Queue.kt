package ads_std

import kotlinx.coroutines.delay
import org.sikuli.script.Screen
import java.util.concurrent.ConcurrentLinkedQueue

val workQueue = ConcurrentLinkedQueue<Screen>()
val apiRequestQueue = ConcurrentLinkedQueue<Screen>()
@Volatile
var profileOpening = false
@Volatile
var atomicOperation = false
suspend fun queueOpenProfile(workRegion: WorkRegion) {
    apiRequestQueue.add(workRegion.screen)
    queueApiWait(workRegion.screen)
    while (atomicOperation) {
        delay(100)
    }
    profileOpening = true
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
    profileOpening = false
    apiRequestQueue.poll()
}

suspend fun queueCloseProfile(workRegion: WorkRegion) {
    apiRequestQueue.add(workRegion.screen)
    queueApiWait(workRegion.screen)
    var response = closeProfileWithoutDriver(workRegion.profile)
    while (response != "Success") {
        delay(700)
        response = closeProfileWithoutDriver(workRegion.profile)
    }
    apiRequestQueue.poll()
}

suspend fun Screen.queueTakeClickRelease() {
    workQueue.add(this)
    queueWorkWait(this)
    atomicOperation = true
    this.click()
    workQueue.poll()
    atomicOperation = false
}

suspend fun Screen.queueTakeClick() {
    workQueue.add(this)
    queueWorkWait(this)
    atomicOperation = true
    this.click()
}

suspend fun Screen.queueClickRelease() {
    //queueWorkWait(this)
    this.click()
    workQueue.poll()
    atomicOperation = false
}

suspend fun Screen.queueTakeAndWait() {
    atomicOperation = true
    workQueue.add(this)
    queueWorkWait(this)
}

suspend fun Screen.queueRelease() {
    //queueWorkWait(this)
    workQueue.poll()
    atomicOperation = false
}

suspend fun queueWorkWait(screen: Screen) {
    while (workQueue.peek() != screen || profileOpening) {
        delay(100)
    }
}

suspend fun queueApiWait(screen: Screen) {
    while (apiRequestQueue.peek() != screen) {
        delay(100)
    }
}
