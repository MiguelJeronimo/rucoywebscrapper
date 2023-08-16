import API.creatures.Creatures
import Jsoup.Scrapper
import API.news.New
import kotlin.reflect.typeOf

fun main(args: Array<String>) {
    var guildName = "Last Time"
    var url = "https://www.rucoyonline.com/news"
    val urlCreatures = "https://rucoy-online.fandom.com/wiki/Demon"
    val scrapper = Scrapper().Soup(urlCreatures)
    val creatureProfile = Creatures().getGeneralDataCreature(scrapper)
    println(creatureProfile.name)
    println(creatureProfile.url_img)
    println(creatureProfile.level)
    println(creatureProfile.hitpoints)
    println(creatureProfile.experience)
    println(creatureProfile.spawn)
    println(creatureProfile.genera_information)
    println("#############################Loot Items############")
    creatureProfile.items.forEach { items->
        println("Name: ${items.name}, url: ${items.url}")
    }
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
