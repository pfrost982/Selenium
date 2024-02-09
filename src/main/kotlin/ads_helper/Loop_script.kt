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

const val START = 1920
const val END = 2715
const val TOP = 0
const val BOTTOM = 630
suspend fun main(): Unit = coroutineScope {
    val screen = Screen()
    screen.x = START
    screen.y = TOP
    screen.w = END - START
    screen.h = BOTTOM - TOP
    //serve(screen)
    collectCoins(screen)
}

fun serve(screen: Screen) {
    ImagePath.add("src/main/kotlin/ads_helper/png/pvz")
    water(screen)
    spray(screen)
    music(screen)
}

fun collectCoins(old_screen: Screen) {
    ImagePath.add("src/main/kotlin/ads_helper/png/pvz")
    val screen = Screen()
    screen.x = old_screen.x
    screen.y = old_screen.y + 130
    screen.w = old_screen.w
    screen.h = old_screen.h - 260
    while (true) {
        val silver = screen.exists("coin_silver.png", 0.2)
        if (silver != null) {
            screen.click()
        }
        val gold = screen.exists("coin_gold.png", 0.2)
        if (gold != null) {
            screen.click()
        }
    }
}

private fun water(screen: Screen) {
    serveNeed(screen, "water_drop.png", "water_can.png")
}

private fun feed(screen: Screen) {
    serveNeed(screen, "plant_food_icon.png", "plant_food_top_menu.png")
}

private fun spray(screen: Screen) {
    serveNeed(screen, "spray_icon.png", "sprayer_top_menu.png")
}

private fun music(screen: Screen) {
    serveNeed(screen, "music_icon.png", "music_top_menu.png")
}

private fun serveNeed(screen: Screen, icon: String, menuButton: String) {
    while (screen.exists(Pattern(icon).targetOffset(-30, 30), 18.0) != null) {
        println(icon)
        screen.click()
        screen.wait(menuButton)
        screen.click()
        val need = screen.exists(Pattern(icon).targetOffset(-30, 30), 1.0)
        if (need != null) {
            screen.click()
        } else {
            screen.rightClick()
        }
        screen.wait(1.5)
    }
}

fun pvzPlansMining(screen: Screen) {
    ImagePath.add("src/main/kotlin/ads_helper/png/pvz")
    println("PvZ plants mining start...")
    val needPlants = 6
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
    println("Go to garden")
    screen.wait("puzzle_back_button.png")
    screen.click()
    screen.wait(1.0)
    screen.wait("dzen_garden.png")
    screen.click()
    water(screen)
    println("No more water drops")
    println("Go to feed the plants")
    feed(screen)
    screen.wait("garden_right_arrow.png")
    screen.click()
    screen.wait(0.3)
    screen.click()
    screen.wait(0.3)
    screen.click()
    pvzTreeOfWisdom(screen)
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