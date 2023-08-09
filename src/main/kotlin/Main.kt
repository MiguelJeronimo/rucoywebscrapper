import Jsoup.Scrapper
import guildss.GuildsData
import news.New

fun main(args: Array<String>) {
    var guildName = "Last Time"
    var url = "https://www.rucoyonline.com/news"
    val scrapper = Scrapper().Soup(url)
    val newsRucoy = New().NewsRucoy(scrapper)
    for (data in newsRucoy.newData){
        println(data)
    }
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
