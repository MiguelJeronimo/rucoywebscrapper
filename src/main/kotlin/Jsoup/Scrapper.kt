package Jsoup

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class Scrapper {
    fun Soup(url:String): Document {
        //Itony Stark
        val conexion = Jsoup.connect(url).get()
        return conexion
    }

    fun soup(url: String): Document {
        val conection = Jsoup.connect(url)
            //.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
            //.header("Referer", "https://www.tibiaring.com/")
            .ignoreHttpErrors(true).timeout(5000)
            .get()
        return conection
    }
}