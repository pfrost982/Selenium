package ads_helper

import ads_std.queueClickRelease
import ads_std.queueTakeClickRelease
import ads_std.scrollPattern
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

const val START = 1960
const val END = 2770
const val TOP = 10
const val BOTTOM = 640
suspend fun main(): Unit = coroutineScope {
    val screen = Screen()
    screen.x = START
    screen.y = TOP
    screen.w = END - START
    screen.h = BOTTOM - TOP
    pvzPlansMining(screen)
    //pvzTreeOfWisdom(screen)
}

fun pvzTreeOfWisdom(screen: Screen) {
    ImagePath.add("src/main/kotlin/ads_helper/png/pvz")
    println("PvZ the cultivation of the tree of Wisdom has begun...")
    while (screen.exists(Pattern("not_enough_money.png").similar(0.9), 0.3) == null) {
        while (screen.exists(Pattern("tree_food_empty.png").similar(0.95), 0.3) == null) {
            screen.wait("tree_food.png")
            screen.click()
            screen.wait("feeding_place.png")
            screen.click()
            screen.wait(1.4)
        }
        println("Tree food is over")
        screen.wait("store_label.png")
        screen.click()
        screen.wait(1.0)
        while (screen.exists(Pattern("tree_food_in_storage_is_sold.png").similar(0.9), 0.3) == null) {
            screen.wait("tree_food_in_storage.png")
            screen.click()
            screen.wait("yes_button.png")
            screen.click()
        }
        println("Tree food bought")
        screen.wait("back_button.png")
        screen.click()
    }
}

fun pvzPlansMining(screen: Screen) {
    ImagePath.add("src/main/kotlin/ads_helper/png/pvz")
    println("PvZ plants mining start...")
    val needPlants = Int.MAX_VALUE
    var plants = 0
    while (plants < needPlants) {
        println("Search puzzle screen...")
        val puzzle = screen.exists("puzzle_i_zombie_endless.png", 30.0)
        if (puzzle != null) {
            println("Puzzle screen founded")
            screen.click()
            screen.wait("continue_button.png")
            screen.click()
            println("Wait prize...")
            CoroutineScope(Dispatchers.Default).launch {
                val bag = screen.exists(Pattern("prize_bag.png").similar(0.9))
                if (bag != null) {
                    println("Prize is BAG!")
                    screen.wait(1.0)
                    screen.wait("prize_bag.png")
                    screen.click()
                } else {
                    println("Bag not found")
                }
            }
            CoroutineScope(Dispatchers.Default).launch {
                val plant = screen.exists(Pattern("prize_plant.png").similar(0.9))
                if (plant != null) {
                    plants++
                    println("Prize is PLANT!!!")
                    screen.wait(1.0)
                    screen.wait("prize_plant.png")
                    screen.click()
                } else {
                    println("Plant not found")
                }
            }
            CoroutineScope(Dispatchers.Default).launch {
                val bag = screen.exists(Pattern("prize_chocolate.png").similar(0.9))
                if (bag != null) {
                    println("Prize is CHOCOLATE!")
                    screen.wait(1.0)
                    screen.wait("prize_chocolate.png")
                    screen.click()
                } else {
                    println("Chocolate not found")
                }
            }
            screen.wait("try_again_button.png", 12.0)
            screen.wait("menu_button.png")
            println("Go to puzzle screen")
            screen.click()
        }
        println("--------------------------------------------------------------")
        println("Plants founded: $plants")
        println("--------------------------------------------------------------")
    }
}

suspend fun adsLanguageSwitchLoop(screen: Screen) {
    ImagePath.add("src/main/kotlin/ads_helper/png")
    var profile = 263
    while (true) {
        println("Proxy label search...")
        val proxy = screen.exists(Pattern("ads_proxy_label.png").similar(0.9), 180.0)
        if (proxy != null) {
            println("Proxy label founded")
            screen.wait(2.0)
            scrollPattern(screen, Pattern("ads_proxy_label.png"), 3)
            screen.wait("ads_advanced_label.png")
            screen.queueTakeClickRelease()
            scrollPattern(screen, Pattern("ads_advanced_label.png"), 3)
            screen.wait(Pattern("ads_language_label.png").targetOffset(90, 0).similar(0.9))
            screen.queueClickRelease()
            screen.wait(Pattern("ads_switch_off.png").similar(0.9))
            println("Switch off")
            screen.wait(Pattern("ads_ok_button.png").similar(0.9))
            screen.queueTakeClickRelease()
            screen.wait(1.0)
            screen.wait(Pattern("ads_tips_label.png").targetOffset(600, 150).similar(0.9))
            screen.queueTakeClickRelease()
            screen.wait(2.0)
            profile++
            println("\u001B[32mNext profile $profile\u001B[39m")
        }
    }
}