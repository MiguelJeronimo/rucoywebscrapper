package Tibia.Catalog

import API.Tibia.models.Catalog
import Jsoup.Scrapper

class CatalogGame(val scrapper: Scrapper, baseurl: String) {
    private val url = "${baseurl}/Main_Page"
    private val request = scrapper.Soup(url)
    private val catalogs = ArrayList<Catalog>()
    fun options(): ArrayList<Catalog> {
        val container = request.getElementById("gallery-0")
        val galleryItems = container?.select("[class=\"thumb\"]")
        val image = galleryItems?.select("img")
        image?.forEach {
            val attributes = it.attributes()
            //no agregar al array los elementos repetidos
            if (attributes.get("data-src").isEmpty()){
                if (catalogs.isEmpty()){
                    catalogs.add(
                        Catalog(
                            name = it.attributes().get("alt"),
                            image = it.attributes().get("src")
                        )
                    )
                } else {
                    if (catalogs.last().name != it.attributes().get("alt")){
                        catalogs.add(
                            Catalog(
                                name = it.attributes().get("alt"),
                                image = it.attributes().get("src")
                            )
                        )
                    }
                }
            } else {
                if (catalogs.isEmpty()){
                    catalogs.add(
                        Catalog(
                            name = it.attributes().get("alt"),
                            image = it.attributes().get("src")
                        )
                    )
                } else {
                    if (catalogs.last().name != it.attributes().get("alt")){
                        catalogs.add(
                            Catalog(
                                name = it.attributes().get("alt"),
                                image = it.attributes().get("data-src")
                            )
                        )
                    }
                }
            }
        }
        return catalogs
    }
}