package Tibia.Weapons

import API.Tibia.models.Ammunition
import API.Tibia.models.Throwing
import API.Tibia.models.Weapon
import API.Tibia.models.Weapons
import Jsoup.Scrapper

class Weapons(scrapper: Scrapper, baseurl: String) {
    private val request = scrapper.Soup("${baseurl}/Distance_Weapons")
    fun weapons(): Weapons {
        return Weapons(
            bows = bows(),
            crossBows = crowBows(),
            arrows = arrows(),
            bolts = bolts(),
            throwing = throwingWeapons()
        )
    }
    //six tables
    private fun bows(): ArrayList<Weapon> {
        val weaponsList = ArrayList<Weapon>()
        val body = request.getElementsByClass("tabber wds-tabber")
            .tagName("tbody").select("[class=\"wikitable sortable full-width\"]")[0].select("tbody")
        body.select("tr").forEach {
            val data = it.children()
            val image = data[1].select("img").attr("data-src")
            weaponsList.add(
                Weapon(
                    name = data[0].text(),
                    image = image.ifEmpty { null },
                    level = data[2].text(),
                    range = data[3].text(),
                    atkMode = data[4].text(),
                    hit = data[5].text(),
                    resist = data[6].text(),
                    embuimentSlots = data[7].text(),
                    classs = data[8].text(),
                    attributes = data[9].text(),
                    weight = data[10].text()
                )
            )
        }
        weaponsList.removeFirst()
        return weaponsList
    }
    private fun crowBows(): ArrayList<Weapon> {
        val weaponsList = ArrayList<Weapon>()
        val body = request.getElementsByClass("tabber wds-tabber")
            .tagName("tbody").select("[class=\"wikitable sortable full-width\"]")[1].select("tbody")
        body.select("tr").forEach {
            val data = it.children()
            val image = data[1].select("img").attr("data-src")
            weaponsList.add(
                Weapon(
                    name = data[0].text(),
                    image = image.ifEmpty { null },
                    level = data[2].text(),
                    range = data[3].text(),
                    atkMode = data[4].text(),
                    hit = data[5].text(),
                    resist = data[6].text(),
                    embuimentSlots = data[7].text(),
                    classs = data[8].text(),
                    attributes = data[9].text(),
                    weight = data[10].text()
                )
            )
        }
        weaponsList.removeFirst()
        return weaponsList
    }

    private fun arrows(): ArrayList<Ammunition> {
        val ammunitionList = ArrayList<Ammunition>()
        val body = request.getElementsByClass("tabber wds-tabber")
            .tagName("tbody").select("[class=\"wikitable sortable full-width\"]")[2].select("tbody")
        body.select("tr").forEach {
            val data = it.children()
            val image = data[1].select("img").attr("data-src")
            ammunitionList.add(
                Ammunition(
                    name = data[0].text(),
                    image = image.ifEmpty { null },
                    level = data[2].text(),
                    atk = data[3].text(),
                    weight = data[4].text(),
                    npcPrice = data[5].text()
                )
            )
        }
        ammunitionList.removeFirst()
        return ammunitionList
    }

    private fun bolts(): ArrayList<Ammunition> {
        val ammunitionList = ArrayList<Ammunition>()
        val body = request.getElementsByClass("tabber wds-tabber")
            .tagName("tbody").select("[class=\"wikitable sortable full-width\"]")[3].select("tbody")
        body.select("tr").forEach {
            val data = it.children()
            val image = data[1].select("img").attr("data-src")
            ammunitionList.add(
                Ammunition(
                    name = data[0].text(),
                    image = image.ifEmpty { null },
                    level = data[2].text(),
                    atk = data[3].text(),
                    weight = data[4].text(),
                    npcPrice = data[5].text()
                )
            )
        }
        ammunitionList.removeFirst()
        return ammunitionList
    }

    private fun throwingWeapons(): ArrayList<Throwing> {
        val ammunitionList = ArrayList<Throwing>()
        val body = request.getElementsByClass("tabber wds-tabber")
            .tagName("tbody").select("[class=\"wikitable sortable full-width\"]")[4]
            .select("tbody")
        body.select("tr").forEach {
            val data = it.children()
            val image = data[1].select("img").attr("data-src")
            ammunitionList.add(
                Throwing(
                    name = data[0].text(),
                    image = image.ifEmpty { null },
                    level = data[2].text(),
                    atk = data[3].text(),
                    range = data[4].text(),
                    weight = data[5].text(),
                    npcPrice = data[6].text()
                )
            )
        }
        ammunitionList.removeFirst()
        return ammunitionList
    }
}