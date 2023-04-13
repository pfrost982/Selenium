package ads_std

import kotlinx.coroutines.delay
import java.util.concurrent.ConcurrentLinkedQueue

val workQueue = ConcurrentLinkedQueue<WorkRegion>()
val ApiRequestQueue = ConcurrentLinkedQueue<WorkRegion>()
suspend fun queueOpenProfile(workRegion: WorkRegion) {
    ApiRequestQueue.add(workRegion)
    queueWait(workRegion, ApiRequestQueue)
    openProfileWithoutDriver(
        workRegion.profile,
        workRegion.screen.x,
        workRegion.screen.y,
        workRegion.screen.w,
        workRegion.screen.h
    )
    ApiRequestQueue.poll()
}

suspend fun queueCloseProfile(workRegion: WorkRegion) {
    ApiRequestQueue.add(workRegion)
    delay(1000)
    queueWait(workRegion, ApiRequestQueue)
    closeProfileWithoutDriver(workRegion.profile)
    ApiRequestQueue.poll()
}

suspend fun queueAddClickRelease(workRegion: WorkRegion) {
    workQueue.add(workRegion)
    queueWait(workRegion, workQueue)
    workRegion.screen.click()
    workQueue.poll()
}

suspend fun queueAddClick(workRegion: WorkRegion) {
    workQueue.add(workRegion)
    queueWait(workRegion, workQueue)
    workRegion.screen.click()
}

suspend fun queueClickRelease(workRegion: WorkRegion) {
    queueWait(workRegion, workQueue)
    workRegion.screen.click()
    workQueue.poll()
}

suspend fun queueRelease(workRegion: WorkRegion) {
    queueWait(workRegion, workQueue)
    workQueue.poll()
}

 suspend fun queueWait(workRegion: WorkRegion, queue: ConcurrentLinkedQueue<WorkRegion>) {
    while (queue.peek() != workRegion) {
        delay(10)
    }
}
