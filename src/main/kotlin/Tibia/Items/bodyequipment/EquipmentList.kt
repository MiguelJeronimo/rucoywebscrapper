package com.miguel.tibia_merchants_api.Tibia.Items.bodyequipment

import Jsoup.Scrapper
import model.Tibia.items.BodyEquipment
import model.Tibia.items.Item

class EquipmentList(scrapper: Scrapper, baseurl: String, name: String) {
    private val url = "${baseurl}/${name}"
    private val request = scrapper.Soup(url)
    fun item(): ArrayList<Item> {
        val bodyEquipment = BodyEquipment()
        val items = ArrayList<Item>()
        val container = request.getElementsByClass("wikitable sortable full-width")
        val tbody = container.select("tbody")
        val tr = tbody.select("tr")
        tr.forEach {
            val data = it.children()
            val item = Item().apply {
                name = data[0].text()
                img = data[0].select("img").attr("data-src")
                arm = data[1].text()
                weight = data[2].text()
                data[3].text().also { att->
                    attributes = att.ifEmpty { null }
                }
                resist = data[4].text()
                slots = data[5].text()
                classs = data[6].text()
                data[7].text().also { lvl->
                    level = lvl.ifEmpty{null}
                }
                vocation = data[8].text()
            }
            items.add(item)
        }
        items.removeFirst()
        return items.also { bodyEquipment.items = it }
    }
}