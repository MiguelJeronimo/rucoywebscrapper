
import Tibia.Tibia

fun main(args: Array<String>) {
    var guildName = "Last Time"
    //val trainer = Calculate().train(406,415,0, 5)
//    val mobs = arrayOf(
//        /*00*/ /*P*/Mob("Rat Lv.1",  4, 25),  /*01*/ /*P*/
//        Mob("Rat Lv.3", 7, 35),  /*02*/ /*P*/
//        Mob("Crow Lv.6",  13, 40),  /*03*/ /*P*/
//        Mob("Wolf Lv.9",  17, 50),  /*04*/ /*P*/
//        Mob("Scorpion Lv.12",  18, 50),  /*05*/ /*P*/
//        Mob("Cobra Lv.13",  18, 50),  /*06*/ /*P*/
//        Mob("Worm Lv.14",  19, 55),  /*07*/ /*P*/
//        Mob("Goblin Lv.15",  21, 60),  /*08*/ /*P*/
//        Mob("Mummy Lv.25",  36, 80),  /*09*/ /*P*/
//        Mob("Pharaoh Lv.35",  51, 100),  /*10*/ /*P*/
//        Mob("Assassin Lv.45",  71, 120),  /*11*/ /*P*/
//        Mob("Assassin Lv.50",  81, 140),  /*12*/ /*P*/
//        Mob("Assassin Ninja Lv.55",  91, 160),  /*13*/
//        Mob("Skeleton Archer Lv.80",  101, 300),  /*14*/ /*P*/
//        Mob("Zombie Lv.65", 106, 200),  /*15*/ /*P*/
//        Mob("Skeleton Lv.75",  121, 300),  /*16*/ /*P*/
//        Mob("Skeleton Warrior Lv.90",  146, 375),  /*17*/ /*P*/
//        Mob("Vampire Lv.100",  171, 450),  /*18*/ /*P*/
//        Mob("Vampire Lv.110",  186, 530),  /*19*/
//        Mob("Drow Ranger Lv.125",  191, 600),  /*20*/
//        Mob("Drow Mage Lv. 130",  191, 600),  /*21*/ /*P*/
//        Mob("Drow Assassin Lv.120",  221, 620),  /*22*/
//        Mob("Drow Sorceress Lv.140",  221, 600),  /*23*/ /*P*/
//        Mob("Drow Fighter Lv.135",  246, 680),  /*24*/
//        Mob("Lizard Archer Lv.160",  271, 650),  /*25*/
//        Mob("Lizard Shaman Lv.170",  276, 600),  /*26*/
//        Mob("Dead Eyes Lv.170",  276, 600),  /*27*/ /*P*/
//        Mob("Lizard Warrior Lv.150",  301, 680),  /*28*/ /*P*/
//        Mob("Djinn Lv.150",  301, 640),  /*29*/
//        Mob("Lizard High Shaman Lv.190",  326, 740),  /*30*/ /*P*/
//        Mob("Gargoyle Lv.190",  326, 740),  /*31*/
//        Mob("Dragon Hatchling Lv. 240",  331, 10000),  /*32*/ /*P*/
//        Mob("Lizard Captain lv.180",  361, 815),  /*33*/
//        Mob("Dragon Lv.250",  501, 20000),  /*34*/ /*P*/
//        Mob("Minotaur Lv.225",  511, 4250),  /*35*/ /*P*/
//        Mob("Minotaur Lv.250",  591, 5000),  /*36*/
//        Mob("Dragon Warden Lv.280",  626, 30000),  /*37*/
//        Mob("Ice Elemental Lv.300",  676, 40000),  /*38*/ /*P*/
//        Mob("Minotaur Lv.275",  681, 5750),  /*39*/
//        Mob("Ice Dragon Lv.320",  726, 50000),  /*40*/
//        Mob("Yeti Lv.350",  826, 60000),  // new Mob("Lava Golem Lv.375",
//        // new Mob("Orthrus Lv.425",
//        // new Mob("Demon Lv.450",
//
//    )
//    val trainer = Calculate().train(255,255,0, 5, mobs)
//    val ptrain = PowerTrainner().ptrain(
//        0,
//        278,
//        278,
//        0,
//        5,
//        1,
//        mobs
//    )
//    val message = DamageCalculator().oneshot(
//        1,
//        31,
//        278,
//        278,
//        0,
//        mobs,
//        41,
//        0.0
//    )
//    println(message)
    //var url = "https://rucoy-online.fandom.com/wiki/Equipment"
    //val url = "https://www.rucoyonline.com/"
    /*val timer = Timer()
    timer.scheduleAtFixedRate(object : TimerTask() {
        override fun run() {
            val scrapper = Scrapper().Soup(url)
            val players = PlayersOnline().playersOnline(scrapper)
            println(players.replace("characters","").replace("on 24 servers","").replace("  "," "))
        }
    }, 0, 1000)*/
    /*Items().getItemsList(scrapper).forEach {
        println(it)
    }*/
//    //val urlCreatures = "https://rucoy-online.fandom.com/wiki/Evil Santa"
//    //val urlCreatures = "https://rucoy-online.fandom.com/wiki/Dragon Warden"
//    //val urlItems = "https://rucoy-online.fandom.com/wiki/Sword_List"
//    val urlItems = "https://rucoy-online.fandom.com/wiki/Rings_List"
//    val backpack = "https://rucoy-online.fandom.com/wiki/Backpack_List"
//    val belts = "https://rucoy-online.fandom.com/wiki/Belts_List"
//    val legs = "https://rucoy-online.fandom.com/wiki/Legs_List"
//    val url = "https://rucoy-online.fandom.com/wiki/Pendants_List"
//    val hats = "https://rucoy-online.fandom.com/wiki/Hats_List"
//    val hoods = "https://rucoy-online.fandom.com/wiki/Hoods_List"
//    val helmets = "https://rucoy-online.fandom.com/wiki/Helmet_List"
//    val boots = "https://rucoy-online.fandom.com/wiki/Boots_List"
//    val shield = "https://rucoy-online.fandom.com/wiki/Shield_List"
//    val robes = "https://rucoy-online.fandom.com/wiki/Robes_List"
//    val light_armor = "https://rucoy-online.fandom.com/wiki/Light_Armor_List"
//    val armor = "https://rucoy-online.fandom.com/wiki/Armor_List"
    //val items_profile = "https://rucoy-online.fandom.com/wiki/asdasdasd"
    //val training = Training()
//    val scrapper = Scrapper().Soup(armor)
//    val creatureProfile = ArmorRucoyList().getArmorList(scrapper = scrapper)
//    creatureProfile.forEach {
//        println(it)
//    }
//    val pager = 20
//    val url = "https://www.rucoyonline.com/guilds?page=$pager"
//    val scrapper = Scrapper().Soup(url)
//    val guilds = Guilds().getGuildsList(scrapper)
//    println(guilds.pager)
//    guilds.list_guild?.forEach {
//       println(it)
//    }

    //TIBIA
    val url = "https://tibia.fandom.com/wiki/Main_Page"
    val rashid = "https://tibia.fandom.com/wiki/Rashid"//FUNCIONA
    val yasir = "https://tibia.fandom.com/wiki/Yasir"//FUNCIONA
    //dijins blue
    val horoun = "https://tibia.fandom.com/wiki/Haroun"
    val nashBob = "https://tibia.fandom.com/wiki/Nah%27Bob"
    //roshamuul
    val asnarus = "https://tibia.fandom.com/wiki/Asnarus"
    //dijings green
    val alesar = "https://tibia.fandom.com/wiki/Alesar"
    val yalam = "https://tibia.fandom.com/wiki/Yaman"
    //farmine npc
    val esrik = "https://tibia.fandom.com/wiki/Esrik"
    val alexander = "https://tibia.fandom.com/wiki/Alexander"
    val tamoril = "https://tibia.fandom.com/wiki/Tamoril"
    val grizzlyAdams = "https://tibia.fandom.com/wiki/Grizzly_Adams"
    //val scrapper = Scrapper().Soup(url)
    val tibia = Tibia().items().items()
    println(tibia.body_equipment?.title)
    tibia.body_equipment?.array?.forEach { println(it) }
    println("-*-----*-*-*------------------------**-*-------------------------------*-*-")
    println(tibia.others?.title)
    tibia.others?.array?.forEach { println(it) }
    println("-*-----*-*-*------------------------**-*-------------------------------*-*-")
    println(tibia.weapons?.title)
    tibia.weapons?.array?.forEach { println(it) }
    println("-*-----*-*-*------------------------**-*-------------------------------*-*-")
    println(tibia.other_items?.title)
    tibia.other_items?.array?.forEach { println(it) }
    println("-*-----*-*-*------------------------**-*-------------------------------*-*-")
    println(tibia.household_items?.title)
    tibia.household_items?.array?.forEach { println(it) }
    println("-*-----*-*-*------------------------**-*-------------------------------*-*-")
    println(tibia.tools_equipment?.title)
    tibia.tools_equipment?.array?.forEach { println(it) }

//    println(tibia.description)
//    println("--------------------------------*-*-*-*-*-*-*-*-*")
//    tibia.spells?.forEach { println(it) }
//    println("*-*-*-*-*-*-*-*--*-Is not avaibñle*-*-*-*-*-*-*-*-*-*-*-*-*-*-")
//    tibia.spellsIsNotAvaible?.forEach { println(it) }
//    println("*-*-*-*-*-*-*-*--*-RUNES*-*-*-*-*-*-*-*-*-*-*-*-*-*-")
//    tibia.runeSpells?.forEach { println(it) }
    //tibia.forEach { println(it) }
//    val bows = tibia.bows()
//    println("-------------------bows")
//    tibia.weapons().bows.forEach { println(it) }
//    println("--------------------------CrossbowList")
//    tibia.weapons().crossBows.forEach { println(it) }
//    println("-------------------------Flechas")
//    tibia.weapons().arrows.forEach { println(it) }
//    println("-------------------------Dardos")
//    tibia.weapons().bolts.forEach { println(it) }
//    println("-------------------------Throwing")
//    tibia.weapons().throwing.forEach { println(it) }
//    val yasirData = tibia.grizzlyAdams()
//    println(yasirData.nameNPC)
//    println(yasirData.imgNPC)
//    println(yasirData.description)
//    println(yasirData)

    //yasirData.items?.forEach { println(it) }
//    println(yasirData.nameNPC)
//    println(yasirData.descrption)
//    println("buying")
//    yasirData.buyingItems?.forEach { println(it) }
//    println("spells")
//    yasirData.sellingItems?.forEach { println(it) }
//grizli adams
//    println("Name: ${tibia.nameNPC}")
//    println("Description: ${tibia.descrption}")
//    println("BUYING")
//    tibia.buyingItems?.forEach { println(it) }
//    println("SELLING")
//    tibia.sellingItems?.forEach { println(it) }
}

