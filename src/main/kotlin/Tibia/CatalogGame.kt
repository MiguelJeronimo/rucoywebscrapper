package Tibia

import org.jsoup.nodes.Document

class CatalogGame(val scrapper: Document) {
    fun catalogGame(){
        val container = scrapper.getElementById("gallery-0")
        val galleryItems = container?.getElementsByClass("wikia-gallery-item")
        galleryItems?.forEach {
            println(it)
//                .select("[class=\"image link-internal\"]").forEach {
//                    println("items: ${it}")
//                }
//            println(it.getElementsByClass("thumb")
//                .select("[class=\"image link-internal\"]").tagName("img"))
        }
    }
}