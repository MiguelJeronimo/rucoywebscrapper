package Tibia

import Jsoup.Scrapper
import Tibia.Blessings.Blessings
import Tibia.Catalog.CatalogGame
import Tibia.Items.Items
import Tibia.Vocations.Vocations
import com.miguel.tibia_merchants_api.Tibia.Items.bodyequipment.EquipmentList
import model.Tibia.items.Item
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

    fun blessings(): Blessings {
        return Blessings(scrapper, baseurl)
    }

    fun item(name: String): ArrayList<Item> {
        return EquipmentList(scrapper, baseurl, name).item()
    }

}