import API.Items.Bows.BowsListRucoy
import API.Items.ItemsProfile.ItemsProfile
import API.Items.Potions.PotionsRucoy
import API.Items.Swords.SwordListRucoy
import API.Items.Wands.WandsListRucoy
import API.Items.items.Items
import Calculate.Training
import Jsoup.Scrapper
import model.error

fun main(args: Array<String>) {
    var guildName = "Last Time"
    var url = "https://rucoy-online.fandom.com/wiki/Equipment"
    val scrapper = Scrapper().Soup(url)
    Items().getItemsList(scrapper).forEach {
        println(it)
    }
    //val urlCreatures = "https://rucoy-online.fandom.com/wiki/Evil Santa"
    //val urlCreatures = "https://rucoy-online.fandom.com/wiki/Dragon Warden"
    //val urlItems = "https://rucoy-online.fandom.com/wiki/Sword_List"
    //val urlItems = "https://rucoy-online.fandom.com/wiki/Wand_List"
    //val items_profile = "https://rucoy-online.fandom.com/wiki/asdasdasd"
    //val training = Training()
    /*val scrapper = Scrapper().Soup(urlItems)
    val creatureProfile = SwordListRucoy().getSwordList(scrapper = scrapper)
    creatureProfile.itemsRucoy.forEach {
        println(it)
    }*/
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
    /*training.trainingCalculator(
        277,
        276,
        5,
        0
    )*/
    /*val urlItems = "https://rucoy-online.fandom.com/wiki/Potions_List"
    val scrapper = Scrapper().Soup(urlItems)
    val creatureProfile = PotionsRucoy().getItemPotionsRucoy(scrapper = scrapper)
    for (data in creatureProfile){
        println(data)
    }*/
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
