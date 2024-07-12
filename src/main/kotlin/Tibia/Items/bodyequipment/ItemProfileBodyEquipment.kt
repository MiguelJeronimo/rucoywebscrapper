package com.miguel.tibia_merchants_api.Tibia.Items.bodyequipment

import Jsoup.Scrapper

class ItemProfileBodyEquipment (scrapper: Scrapper, baseurl: String, name: String){
    private val url = "${baseurl}/${name}"
    private val request = scrapper.Soup(url)
    fun item(){
        println("url final: $url")
        val npcNotes = request.getElementById("npc-notes")
        val nameNPC = request.getElementsByClass("mw-page-title-main").text()
        val imgNPC = request.getElementById("twbox-image")?.select("img")
        //val mapImage = request.getElementsByClass("map_image").select("img").attr("src")
        println(nameNPC)
        println("Notes: $npcNotes")
        println("img: $imgNPC")

    }
}