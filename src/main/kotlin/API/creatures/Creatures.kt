package API.creatures

import model.Creatures
import model.ItemsCreatures
import org.jsoup.nodes.Document

class Creatures {
    fun getGeneralDataCreature(scrapper: Document): Creatures {
        var arrayItemsCreature = ArrayList<ItemsCreatures>()
        val creatureName = scrapper.select("h2[data-source]").text()
        val urlImgCreature = scrapper.getElementsByClass("image image-thumbnail").attr("href")
        val creatureAtributte = scrapper.getElementsByClass("pi-data-value pi-font")
        val tableLoot = scrapper.getElementsByClass("article-table")
        val lootList = tableLoot.select("tbody")
        var level = creatureAtributte[0].text()
        val hp = creatureAtributte[1].text()
        val exp = creatureAtributte[2].text()
        val spawn = creatureAtributte[3].text()
        for (loot in lootList){
            val center = loot.select("tr")
            for (data in center){
                val url = data.getElementsByTag("a")
                if (url.toString() != ""){
                    val urlData = url.first()?.firstChild()?.attr("data-src")
                    if (url.first()?.firstElementChild()?.tagName() != null){
                        if (urlData == ""){
                            arrayItemsCreature.add(ItemsCreatures(data.text(),url.first()?.firstChild()?.attr("src")))
                        } else{
                            arrayItemsCreature.add(ItemsCreatures(data.text(),urlData))
                        }
                    } else {
                        arrayItemsCreature.add(ItemsCreatures(data.select("span").text(), url.first()?.firstElementChild()?.tagName()))
                    }
                }

            }
        }
        val creatureProfile = Creatures(creatureName,urlImgCreature,level, hp,exp,spawn, arrayItemsCreature)
        return creatureProfile
    }
}