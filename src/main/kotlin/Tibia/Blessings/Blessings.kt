package Tibia.Blessings

import Jsoup.Scrapper

class Blessings(val scrapper: Scrapper, baseurl: String) {
    private val url = "${baseurl}/Blessings"
    private val request = scrapper.Soup(url)

    fun blessingsInformation() {
        val container = request.select("[class=\"wikitable\"]")
        val introduction = request.select("h2 + p").first()!!.text()+
                request.select("h2 + p + p").first()!!.text() +
                "${request.select("h2 + p +p + ol").first()?.text()} "
        val blesingsCharmsTable = container[0].select("tbody")
        blesingsCharmsTable.select("tr").forEach {
            val th = it.children()
            println(th[0].text())
            println(th[1].text())
            println(th[2].select("img").attr("data-src"))
            println(th[3].text())
            println(th[4].text())
            println(th[5].select("span").attr("title"))
            println("*/*-*/-/////////////-*-----------------------")
        }
    }
}