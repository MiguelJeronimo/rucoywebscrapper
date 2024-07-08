package Tibia.Items

import Jsoup.Scrapper
import model.Tibia.Data
import model.Tibia.Information
import model.Tibia.ItemsCatalog
import org.jsoup.select.Elements

class Items(val scrapper: Scrapper, baseurl: String) {
    private val url = "${baseurl}/items"
    private val request = scrapper.Soup(url)
    fun items(): ItemsCatalog {
        val catalogs = ItemsCatalog()
        val container = request.getElementsByClass("mw-parser-output")
        val arrayTitles = container.select("h2").eachText()
        arrayTitles.removeFirst()
        catalogs.body_equipment = Information(title = arrayTitles[0], array= bodyEquipments(container))
        catalogs.weapons = Information(title = arrayTitles[1], array= weapons(container))
        catalogs.household_items = Information(title = arrayTitles[2], array= houseHoldItems(container))
        catalogs.others = Information(title = arrayTitles[3], array= others(container))
        catalogs.tools_equipment = Information(title = arrayTitles[4], array= toolsEquipment(container))
        catalogs.other_items = Information(title = arrayTitles[5], array= othersItems(container))
        return catalogs
    }
    //Body Equipment
    private fun bodyEquipments(container: Elements): ArrayList<Data> {
        val tBody = container.select("tbody").select("img")
        val arrayData = ArrayList<Data>()
        tBody.forEach {
            if (it.attr("data-src").isEmpty()){
                arrayData.add(Data(
                    img = it.attr("src"),
                    name= it.attr("alt")
                ))
            } else{
                arrayData.add(Data(
                    img = it.attr("data-src"),
                    name= it.attr("alt")
                ))
            }
        }
        return arrayData
    }

    //Weapons
    private fun weapons(container: Elements): ArrayList<Data> {
        val items = container.select("[class=\"creature-types\"]")
        val arrayData = ArrayList<Data>()
        val div = items[0].select("div")
        div.forEach {
            val img = it.child(0).select("img").attr("data-src")
            val text = it.child(1).text()
            if (img.isEmpty()){
                arrayData.add(Data(
                    img = img,
                    name= text
                ))
            } else{
                arrayData.add(Data(
                    img = img,
                    name= text
                ))
            }

        }
        arrayData.removeFirst()
        return arrayData
    }

    //Household Items
    private fun houseHoldItems(container: Elements): ArrayList<Data> {
        val items = container.select("[class=\"creature-types\"]")
        val arrayData = ArrayList<Data>()
        items[1].select("img").forEach {
            if (it.attr("data-src").isEmpty()){
                arrayData.add(Data(
                    img = it.attr("src"),
                    name= it.attr("alt")
                ))
            } else{
                arrayData.add(Data(
                    img = it.attr("data-src"),
                    name= it.attr("alt")
                ))
            }
        }
        return arrayData
    }

    //Plants, Animal Products, Food and Drink
    private fun others(container: Elements): ArrayList<Data> {
        val items = container.select("[class=\"creature-types\"]")
        val arrayData = ArrayList<Data>()
        items[2].select("img").forEach {
            if (it.attr("data-src").isEmpty()){
                arrayData.add(Data(
                    img = it.attr("src"),
                    name= it.attr("alt")
                ))
            } else{
                arrayData.add(Data(
                    img = it.attr("data-src"),
                    name= it.attr("alt")
                ))
            }
        }
        return arrayData
    }
    //Tools and other Equipment
    private fun toolsEquipment(container: Elements): ArrayList<Data> {
        val items = container.select("[class=\"creature-types\"]")
        val arrayData = ArrayList<Data>()
        items[3].select("img").forEach {
            if (it.attr("data-src").isEmpty()){
                arrayData.add(Data(
                    img = it.attr("src"),
                    name= it.attr("alt")
                ))
            } else{
                arrayData.add(Data(
                    img = it.attr("data-src"),
                    name= it.attr("alt")
                ))
            }
        }
        return arrayData
    }
    //Other Items
    private fun othersItems(container: Elements): ArrayList<Data> {
        val items = container.select("[class=\"creature-types\"]")
        val arrayData = ArrayList<Data>()
        items[4].select("img").forEach {
            if (it.attr("data-src").isEmpty()){
                arrayData.add(Data(
                    img = it.attr("src"),
                    name= it.attr("alt")
                ))
            } else{
                arrayData.add(Data(
                    img = it.attr("data-src"),
                    name= it.attr("alt")
                ))
            }
        }
        return arrayData
    }
}