import API.Items.ItemsProfile.ItemsProfile
import Jsoup.Scrapper

fun main(args: Array<String>) {
    var guildName = "Last Time"
    var url = "https://www.rucoyonline.com/news"
    //val urlCreatures = "https://rucoy-online.fandom.com/wiki/Evil Santa"
    //val urlCreatures = "https://rucoy-online.fandom.com/wiki/Dragon Warden"
    //val urlItems = "https://rucoy-online.fandom.com/wiki/Sword_List"
    val urlItems = "https://rucoy-online.fandom.com/wiki/Bow_List"
    var items_profile = "https://rucoy-online.fandom.com/wiki/Lava Bow"
    //val urlItems = "https://rucoy-online.fandom.com/wiki/Potions_List"
    //val creatureProfile = PotionsRucoy().getItemPotionsRucoy(scrapper)
    try {
        val scrapper = Scrapper().Soup(items_profile)
        val creatureProfile = ItemsProfile().itemsProfile(scrapper)
        println(creatureProfile.description_general)
        println("IMG: ${creatureProfile.image}")
        println("Type: ${creatureProfile.type}")
        println(creatureProfile.level)
        println("Effect: ${creatureProfile.effect}")
        println("Source: ${creatureProfile.source}")
        println("Cost to buy: ${creatureProfile.cost_to_buy}")
        println("Gold for sell: ${creatureProfile.gold_for_sell}")
    }catch (e: Exception){
        println(e.message)
    }

    /*creatureProfile?.itemsRucoy?.forEach { data->
        println(data)
    }*/

    //val newsRucoy = New().NewsRucoy(scrapper)
    /*for (data in newsRucoy.newData){
        println(data)
    }*/
    //val searchCharacters = CharactersRucoy().searchCharacter(scrapper)
    //val highscoresExperience = GuildsData().dataGuild(scrapper)
    /*for (highscore in highscoresExperience){
        println(highscore)
    }*/
    //println(newsRucoy)
    /*println("Name: ${highscoresExperience.name} ")
    println("Description: ${highscoresExperience.description}")
    println("Guild: ${highscoresExperience.founded_on}")
    println("---------------------------Members Guild------------------------------------")
    if (highscoresExperience.member !== null){
        highscoresExperience.member?.forEach { death-> println(death) }
    }*/
}
