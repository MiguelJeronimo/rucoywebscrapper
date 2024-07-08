package Tibia

import Jsoup.Scrapper
import Tibia.Catalog.CatalogGame
import Tibia.Items.Items
import Tibia.Vocations.Vocations
import Tibia.Weapons.Weapons as Weapons

class Tibia {
    private val baseurl = "https://tibia.fandom.com/wiki"
    val scrapper = Scrapper()
    fun weapons(): Weapons {
        return Weapons(scrapper, baseurl)
    }
    fun catalog(): CatalogGame {
        return CatalogGame(scrapper, baseurl)
    }

    fun vocations(): Vocations {
        return Vocations(scrapper, baseurl)
    }

    fun items(): Items {
        return Items(scrapper, baseurl)
    }
}