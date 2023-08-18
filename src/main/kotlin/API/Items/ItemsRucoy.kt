package API.Items

import model.ItemRucoyData
import model.ItemsRucoyData
import org.jsoup.nodes.Document

class ItemsRucoy {

    fun getItemRucoy(scrapper: Document): ItemsRucoyData? {
        val items = scrapper.getElementsByClass("article-table")[1]
        val tr = items.select("tbody").select("tr")
        val itemRucoyData = ArrayList<ItemRucoyData>()
        tr.forEach { data->
            val imgItem = data.select("img").attr("data-src")
            val td = data.select("[style=text-align:center;]")
            val nameItem = td[0].text()
            val arrayDamage = td[1].children().eachText()
            val arrayBuyNPC = td[2].children().eachText()
            val arrayDropBy = td[3].children().eachText()
            val arraySellNPC = td[4].children().eachText()
            itemRucoyData.add( ItemRucoyData(
                nameItem,
                imgItem,
                arrayDamage,
                arrayBuyNPC,
                arrayDropBy,
                arraySellNPC
            ))
        }
        return ItemsRucoyData(itemRucoyData)
    }
}