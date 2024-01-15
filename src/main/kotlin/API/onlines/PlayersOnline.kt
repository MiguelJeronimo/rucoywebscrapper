package API.onlines

import org.jsoup.nodes.Document

class PlayersOnline {

    fun playersOnline(scrapper: Document): String {
        var players =  scrapper.getElementsByClass("text-center").select("p")[1].text()
        return players
    }
}