import API.Items.ItemsProfile.ItemsProfile
import Calculate.Training
import Jsoup.Scrapper
import model.error

fun main(args: Array<String>) {
    var guildName = "Last Time"
    var url = "https://www.rucoyonline.com/news"
    //val urlCreatures = "https://rucoy-online.fandom.com/wiki/Evil Santa"
    //val urlCreatures = "https://rucoy-online.fandom.com/wiki/Dragon Warden"
    //val urlItems = "https://rucoy-online.fandom.com/wiki/Sword_List"
    val urlItems = "https://rucoy-online.fandom.com/wiki/Bow_List"
    val items_profile = "https://rucoy-online.fandom.com/wiki/asdasdasd"
    val training = Training()
    /*training.trainingCalculator(
        405,
        414+13,
        11,
        0
    )*/
    /*training.trainingCalculator(
        276,
        276,
        5,
        0
    )*/
    training.trainingCalculator(
        114,
        175,
        5,
        0
    )
    //val urlItems = "https://rucoy-online.fandom.com/wiki/Potions_List"
    //val creatureProfile = PotionsRucoy().getItemPotionsRucoy(scrapper)
    /*val error = error(null)
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
        //println(e.message)
        error.error = "Error finding the information, check again"
        println(error.error)
    }*/

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
