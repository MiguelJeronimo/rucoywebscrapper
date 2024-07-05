package Tibia.Vocations

import API.Tibia.models.RuneSpell
import API.Tibia.models.Spell
import API.Tibia.models.Vocation
import API.Tibia.models.Vocations
import Jsoup.Scrapper

class Vocations(val scrapper: Scrapper, private val baseurl: String) {
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

    fun paladin(): Vocation {
        val vocation = Vocation()
        val spellList = ArrayList<Spell>()
        val runeSpellList = ArrayList<RuneSpell>()
        val url = "${baseurl}/Paladin"
        val request = scrapper.Soup(url)
        val container = request.getElementsByClass("mw-parser-output")
        val description = container.text().replace("[ ]","")
        vocation.description = description
        val table = request.getElementsByClass("wikitable sortable full-width")
        val trSpells = table[0].select("tbody").select("tr")
        val trRuneSpells = table[1].select("tbody").select("tr")

        trSpells.forEach {
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

        trRuneSpells.forEach {
            val children = it.children()
            runeSpellList.add(
                RuneSpell(
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

        vocation.runeSpells = runeSpellList
        vocation.spells = spellList
        vocation.runeSpells!!.removeFirst()
        vocation.spells!!.removeFirst()
        return vocation
    }

    fun sorcerer(): Vocation {
        val vocation = Vocation()
        val spellList = ArrayList<Spell>()
        val runeSpellList = ArrayList<RuneSpell>()
        val url = "${baseurl}/Sorcerer"
        val request = scrapper.Soup(url)
        val container = request.getElementsByClass("mw-parser-output")
        val description = container.text().replace("[ ]","")
        vocation.description = description
        val table = request.getElementsByClass("wikitable sortable full-width")
        val trSpells = table[0].select("tbody").select("tr")
        val trRuneSpells = table[1].select("tbody").select("tr")

        trSpells.forEach {
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

        trRuneSpells.forEach {
            val children = it.children()
            runeSpellList.add(
                RuneSpell(
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

        vocation.runeSpells = runeSpellList
        vocation.spells = spellList
        vocation.runeSpells!!.removeFirst()
        vocation.spells!!.removeFirst()
        return vocation
    }

    fun druid(): Vocation {
        val vocation = Vocation()
        val spellList = ArrayList<Spell>()
        val spellsIsNotAvaibleList = ArrayList<Spell>()
        val runeSpellList = ArrayList<RuneSpell>()
        val url = "${baseurl}/Druid"
        val request = scrapper.Soup(url)
        val container = request.getElementsByClass("mw-parser-output")
        val description = container.text().replace("[ ]","")
        vocation.description = description
        val table = request.getElementsByClass("wikitable sortable full-width")
        val trSpells = table[0].select("tbody").select("tr")
        val trSpellsIsNotAvaible = table[1].select("tbody").select("tr")
        val trRuneSpells = table[2].select("tbody").select("tr")
        println(table.size)
        trSpells.forEach {
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

        trSpellsIsNotAvaible.forEach {
            val children = it.children()
            spellsIsNotAvaibleList.add(
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

        trRuneSpells.forEach {
            val children = it.children()
            runeSpellList.add(
                RuneSpell(
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
        vocation.spellsIsNotAvaible = spellsIsNotAvaibleList
        vocation.runeSpells = runeSpellList
        vocation.spells = spellList
        vocation.runeSpells!!.removeFirst()
        vocation.spells!!.removeFirst()
        vocation.spellsIsNotAvaible!!.removeFirst()
        return vocation
    }


}