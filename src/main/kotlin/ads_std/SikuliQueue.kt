package ads_std

import java.util.concurrent.ConcurrentLinkedQueue

val workQueue = ConcurrentLinkedQueue<WorkRegion>()
fun queueAddClickRelease(workRegion: WorkRegion) {
    workQueue.add(workRegion)
    while (workQueue.peek() != workRegion) {
        workRegion.screen.wait(0.01)
    }
    workRegion.screen.click()
    workQueue.poll()
}

fun queueAddClick(workRegion: WorkRegion) {
    workQueue.add(workRegion)
    while (workQueue.peek() != workRegion) {
        workRegion.screen.wait(0.01)
    }
    workRegion.screen.click()
}

fun queueClickRelease(workRegion: WorkRegion) {
    while (workQueue.peek() != workRegion) {
        workRegion.screen.wait(0.01)
    }
    workRegion.screen.click()
    workQueue.poll()
}

fun queueRelease(workRegion: WorkRegion) {
    while (workQueue.peek() != workRegion) {
        workRegion.screen.wait(0.01)
    }
    workQueue.poll()
}
