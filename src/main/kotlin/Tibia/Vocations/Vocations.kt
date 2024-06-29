package Tibia.Vocations

import API.Tibia.models.Spell
import API.Tibia.models.Vocation
import API.Tibia.models.Vocations
import Jsoup.Scrapper

class Vocations(val scrapper: Scrapper, val baseurl: String) {
    private val url = "${baseurl}/Vocation"
    private val request = scrapper.Soup(url)
    fun vocationList(): ArrayList<Vocations> {
        val vocation = arrayListOf(
            Vocations(name = "Knight",description="Masters of hand-to-hand combat and melee weapons."),
            Vocations(name = "Paladin", description = "Masters of distance fighting with bows and crossbows and average magic users."),
            Vocations(name = "Druid:", description = "Masters of healing and supportive magic, as well as the Ice and Earth elements."),
            Vocations(name = "Sorcerer", description = "Masters of aggressive and offensive magic, as well as the Fire, Energy and Death elements.")
            )
        return vocation
    }

    fun knight(): Vocation {
        val vocation = Vocation()
        val spellList = ArrayList<Spell>()
        val url = "${baseurl}/Knight"
        val request = scrapper.Soup(url)
        val container = request.getElementsByClass("mw-parser-output")
        val description = container.text().replace("[ ]","")
        vocation.description = description
        val table = request.getElementsByClass("wikitable sortable full-width")
        val tr = table.select("tbody").select("tr")
        tr.forEach {
            val children = it.children()
            spellList.add(
                Spell(
                    name = children[0].text(),
                    image = children[1].select("img").attr("data-src"),
                    words = children[2].text(),
                    premium = children[3].select("span").attr("title"),
                    level = children[4].text(),
                    mana = children[5].text(),
                    price = children[6].text(),
                    group = children[7].text(),
                    effect = children[8].text(),
                )
            )
        }
        vocation.spells = spellList
        vocation.spells!!.removeFirst()
        return vocation
    }
}