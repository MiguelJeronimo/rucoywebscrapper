package API.guildss

import model.GuildContent
import model.GuildsListModel
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class Guilds {
    fun getGuildsList(scrapper: Document): GuildsListModel {
        val guildsListModel = GuildsListModel(null, null)
        val guildListContent = ArrayList<GuildContent>()
        val pagerData = ArrayList<String>()
        val pager = scrapper.getElementsByClass("pagination no-margin pull-right")
//        println("paginador: ${pager.text()}")
        val pagerList = pager.select("li").eachText()
        pagerList.removeFirst()
        pagerList.removeLast()
//        println("paginador: ${pagerList}")
        pagerList.forEach { pagerData.add(it) }
        guildsListModel.pager = pagerData
        val titulos = scrapper.select("h4")
        for (tittle in titulos){
            val name = tittle.text()
            val description = tittle.nextElementSibling()?.nextElementSibling()?.text()
            val members = tittle.nextElementSibling()?.nextElementSibling()?.nextElementSibling()?.text()
            guildListContent.add(GuildContent(
                name,
                description,
                members
            ))
        }
        guildsListModel.list_guild = guildListContent
        return guildsListModel
    }
}