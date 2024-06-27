package Tibia

import Jsoup.Scrapper
import Tibia.Weapons.Weapons as Weapons

class Tibia {
    private val baseurl = "https://tibia.fandom.com/wiki/"
    val scrapper = Scrapper()
    fun weapons(): Weapons {
        return Weapons(scrapper, baseurl)
    }
}