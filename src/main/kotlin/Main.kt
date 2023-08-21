import API.Items.Potions.PotionsRucoy
import Jsoup.Scrapper

fun main(args: Array<String>) {
    var guildName = "Last Time"
    var url = "https://www.rucoyonline.com/news"
    //val urlCreatures = "https://rucoy-online.fandom.com/wiki/Evil Santa"
    val urlCreatures = "https://rucoy-online.fandom.com/wiki/Dragon Warden"
    //val urlItems = "https://rucoy-online.fandom.com/wiki/Sword_List"
    val urlItems = "https://rucoy-online.fandom.com/wiki/Potions_List"
    val scrapper = Scrapper().Soup(urlItems)
    val creatureProfile = PotionsRucoy().getItemPotionsRucoy(scrapper)
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
