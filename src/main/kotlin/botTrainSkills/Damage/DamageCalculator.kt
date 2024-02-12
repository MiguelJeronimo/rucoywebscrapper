package botTrainSkills.Damage

import botTrainSkills.Formulas
import model.Damage
import model.Mob
import model.OneShotModel
import model.Weapon
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException


class DamageCalculator {

    /**
     * Calculate damage of mobs rucoy online
     * @param attacktype-> {mele, distances, magic}
     * @param poistion-> position of the array creatures
     * @param mobs-> arraylist of mobs
     * @param base-> level of character
     * @param stat-> skills of character
     * @param buff-> buff of weapon (example: attack 53 + 1 (buff))
     * @param weaponatk -> attack points of weapon
     * */
    fun damageCalculator(
        attacktype: Int,
        mob: Int,
        mobs: Array<Mob>,
        base: Int,
        stat: Int,
        buff: Int,
        weaponatk: Int
    ) {
        val damage = Damage(null, null,null, null,null, null)
        val header =
            "Base: **$base**  Stat: **$stat**  Buffs: **+$buff**  Weapon: **$weaponatk Atk **\n"
        val str1: String
        val str2: String

        var attacktypestring = ""

        val stat1 = stat + buff
        val max_raw_damage: Double
        val min_raw_damage: Double

        if (attacktype == 0) {
            min_raw_damage = Formulas().auto_min_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            max_raw_damage = Formulas().auto_max_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            attacktypestring = "Auto"
        } else if (attacktype == 3) {
            min_raw_damage =
                Formulas().special_magic_min_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            max_raw_damage =
                Formulas().special_magic_max_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            attacktypestring = "Special :fire:"
        } else {
            min_raw_damage =
                Formulas().special_meldist_min_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            max_raw_damage =
                Formulas().special_meldist_max_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            attacktypestring = if (attacktype == 1) {
                "Special :crossed_swords:"
            } else {
                "Special :bow_and_arrow:"
            }
        }

        val max_raw_crit_damage: Double = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
        val min_damage: Double = Formulas().min_damage_Calc(min_raw_damage, mob, mobs)
        val max_damage: Double = Formulas().max_damage_Calc(max_raw_damage, mob, mobs)
        val max_crit_damage: Double = Formulas().max_crit_damage_Calc(max_raw_crit_damage, mob, mobs)

        val normalaccuracy: Double = Formulas().normal_accuracy_Calc(max_raw_damage, min_raw_damage, mob, mobs)
        val critaccuracy: Double = Formulas().crit_accuracy_Calc(max_raw_crit_damage, max_raw_damage, mob, mobs)

        val str0 = "Mob: **" + mobs.get(mob).name + "**\n"
        damage.nameCreature = mobs[mob].name
        //damage.url = mobs[mob].url

        str1 = if (normalaccuracy == 1.00) {
            damage.MIN_ATTACK = min_damage.toInt()
            damage.MAX_ATTACK = max_damage.toInt()
            "Min. Damage (" + attacktypestring + "): **" + min_damage.toInt() + "** " + " Max. Damage (" + attacktypestring + "): **" + max_damage.toInt() + "**\n"

        } else if (normalaccuracy > 0) {
            damage.MAX_ATTACK = max_damage.toInt()
            "Max. Damage (" + attacktypestring + "): **" + max_damage.toInt() + "**\n"
        } else {
            damage.NO_DAMAGE = "You aren't strong enough to deal normal damage to this mob."
            "You aren't strong enough to deal normal damage to this mob."
        }
        str2 = if (critaccuracy > 0) {
            damage.MAX_CRITICAL_ATTACK = max_crit_damage.toInt()
            "Maximum Critical Damage (" + attacktypestring + "): **" + max_crit_damage.toInt() + "**\n"
        } else {
            damage.NO_DAMAGE = "You aren't strong enough to deal normal damage to this mob."
            "You aren't strong enough to deal critical damage to this mob."
        }
        //embed.appendDescription(header + str0 + str1 + str2)
        println("STR1: $str0")
        println("STR1: $str1")
        println("STR1: $str2")
        println("DATA: $damage")
    }
    private val weapons: Array<Weapon> = arrayOf(
        /*00*/
        Weapon(
            "Training Weapon(4)",
            "<:4_7_9_11_13_golden_dagger:802411966684987422>",
            "<:4_7_9_11_13_golden_bow:802411945369927711>",
            "<:4_7_9_11_13_golden_wand:802411976001191946>",
            4,
            0
        ),  /*01*/
        Weapon(
            "Training Weapon(5)",
            "<:4_5_15_17_19_dagger:781573394603966504>",
            "<:4_5_15_17_19_bow:781573383217348658>",
            "<:4_5_15_17_19_wand:781573410525413376>",
            5,
            0
        ),  /*02*/
        Weapon(
            "Training Weapon(7)",
            "<:4_7_9_11_13_golden_dagger:802411966684987422>",
            "<:4_7_9_11_13_golden_bow:802411945369927711>",
            "<:4_7_9_11_13_golden_wand:802411976001191946>",
            7,
            0
        ),  /*03*/
        Weapon(
            "Training Weapon(9)",
            "<:4_7_9_11_13_golden_dagger:802411966684987422>",
            "<:4_7_9_11_13_golden_bow:802411945369927711>",
            "<:4_7_9_11_13_golden_wand:802411976001191946>",
            9,
            0
        ),  /*04*/
        Weapon(
            "Training Weapon(11)",
            "<:4_7_9_11_13_golden_dagger:802411966684987422>",
            "<:4_7_9_11_13_golden_bow:802411945369927711>",
            "<:4_7_9_11_13_golden_wand:802411976001191946>",
            11,
            0
        ),  /*05*/
        Weapon(
            "Training Weapon(13)",
            "<:4_7_9_11_13_golden_dagger:802411966684987422>",
            "<:4_7_9_11_13_golden_bow:802411945369927711>",
            "<:4_7_9_11_13_golden_wand:802411976001191946>",
            13,
            0
        ),  /*06*/
        Weapon(
            "Weapon(15)",
            "<:4_5_15_17_19_dagger:781573394603966504>",
            "<:4_5_15_17_19_bow:781573383217348658>",
            "<:4_5_15_17_19_wand:781573410525413376>",
            15,
            0
        ),  /*07*/
        Weapon(
            "Weapon(17)",
            "<:4_5_15_17_19_dagger:781573394603966504>",
            "<:4_5_15_17_19_bow:781573383217348658>",
            "<:4_5_15_17_19_wand:781573410525413376>",
            17,
            0
        ),  /*08*/
        Weapon(
            "Weapon(19)",
            "<:4_5_15_17_19_dagger:781573394603966504>",
            "<:4_5_15_17_19_bow:781573383217348658>",
            "<:4_5_15_17_19_wand:781573410525413376>",
            19,
            0
        ),  /*09*/
        Weapon(
            "Weapon(20)",
            "<:20_22_24_short_sword:781573430142566460>",
            "<:20_22_24_studded_bow:781573439143673922>",
            "<:20_22_24_novice_wand:781573421523402753>",
            20,
            0
        ),  /*10*/
        Weapon(
            "Weapon(22)",
            "<:20_22_24_short_sword:781573430142566460>",
            "<:20_22_24_studded_bow:781573439143673922>",
            "<:20_22_24_novice_wand:781573421523402753>",
            22,
            0
        ),  /*11*/
        Weapon(
            "Weapon(24)",
            "<:20_22_24_short_sword:781573430142566460>",
            "<:20_22_24_studded_bow:781573439143673922>",
            "<:20_22_24_novice_wand:781573421523402753>",
            24,
            0
        ),  /*12*/
        Weapon(
            "Weapon(25)",
            "<:25_27_29_sword:781573471007146035>",
            "<:25_27_29_iron_bow:781573448425275392>",
            "<:25_27_29_priest_wand:781573463285956668>",
            25,
            0
        ),  /*13*/
        Weapon(
            "Weapon(27)",
            "<:25_27_29_sword:781573471007146035>",
            "<:25_27_29_iron_bow:781573448425275392>",
            "<:25_27_29_priest_wand:781573463285956668>",
            27,
            0
        ),  /*14*/
        Weapon(
            "Weapon(29)",
            "<:25_27_29_sword:781573471007146035>",
            "<:25_27_29_iron_bow:781573448425275392>",
            "<:25_27_29_priest_wand:781573463285956668>",
            29,
            0
        ),  /*15*/
        Weapon(
            "Drow Weapon(30)",
            "<:30_32_34_341_broadsword:781573477776883723>",
            "<:30_32_34_341_drow_bow:781573484400607243>",
            "<:30_32_34_341_royal_priest_wand:781573492524449794>",
            30,
            0
        ),  /*16*/
        Weapon(
            "Drow Weapon(32)",
            "<:30_32_34_341_broadsword:781573477776883723>",
            "<:30_32_34_341_drow_bow:781573484400607243>",
            "<:30_32_34_341_royal_priest_wand:781573492524449794>",
            32,
            0
        ),  /*17*/
        Weapon(
            "Drow Weapon(34)",
            "<:30_32_34_341_broadsword:781573477776883723>",
            "<:30_32_34_341_drow_bow:781573484400607243>",
            "<:30_32_34_341_royal_priest_wand:781573492524449794>",
            34,
            0
        ),  /*18*/
        Weapon(
            "Drow Weapon(34+1)",
            "<:30_32_34_341_broadsword:781573477776883723>",
            "<:30_32_34_341_drow_bow:781573484400607243>",
            "<:30_32_34_341_royal_priest_wand:781573492524449794>",
            34,
            1
        ),  /*19*/
        Weapon(
            "Lizard/Gargoyle Weapon(35)",
            "<:35_37_39_391_lizard_slayer:781573548241059950><:35_37_39_391_gargoyle_slayer:781573514564993034>",
            "<:35_37_39_391_lizard_bow:781573535676104736><:35_37_39_391_gargoyle_bow:781573500762193921>",
            "<:35_37_39_391_shaman_wand:781573555975749663><:35_37_39_391_gargoyle_wand:781573524255014942>",
            35,
            0
        ),  /*20*/
        Weapon(
            "Lizard/Gargoyle Weapon(37)",
            "<:35_37_39_391_lizard_slayer:781573548241059950><:35_37_39_391_gargoyle_slayer:781573514564993034>",
            "<:35_37_39_391_lizard_bow:781573535676104736><:35_37_39_391_gargoyle_bow:781573500762193921>",
            "<:35_37_39_391_shaman_wand:781573555975749663><:35_37_39_391_gargoyle_wand:781573524255014942>",
            37,
            0
        ),  /*21*/
        Weapon(
            "Lizard/Gargoyle Weapon(39)",
            "<:35_37_39_391_lizard_slayer:781573548241059950><:35_37_39_391_gargoyle_slayer:781573514564993034>",
            "<:35_37_39_391_lizard_bow:781573535676104736><:35_37_39_391_gargoyle_bow:781573500762193921>",
            "<:35_37_39_391_shaman_wand:781573555975749663><:35_37_39_391_gargoyle_wand:781573524255014942>",
            39,
            0
        ),  /*22*/
        Weapon(
            "Lizard/Gargoyle Weapon(39+1)",
            "<:35_37_39_391_lizard_slayer:781573548241059950><:35_37_39_391_gargoyle_slayer:781573514564993034>",
            "<:35_37_39_391_lizard_bow:781573535676104736><:35_37_39_391_gargoyle_bow:781573500762193921>",
            "<:35_37_39_391_shaman_wand:781573555975749663><:35_37_39_391_gargoyle_wand:781573524255014942>",
            39,
            1
        ),  /*23*/
        Weapon(
            "Dragon/Minotaur Weapon(40+1)",
            "<:401_422_443_dragon_slayer:781573572421484555><:401_422_443_minotaur_slayer:781573599219286066>",
            "<:401_422_443_dragon_bow:781573565287891024><:401_422_443_minotaur_bow:781573590403121172>",
            "<:401_422_443_dragon_wand:781573581246955601><:401_422_443_minotaur_wand:781573607322681384>",
            40,
            1
        ),  /*24*/
        Weapon(
            "Dragon/Minotaur Weapon(42+2)",
            "<:401_422_443_dragon_slayer:781573572421484555><:401_422_443_minotaur_slayer:781573599219286066>",
            "<:401_422_443_dragon_bow:781573565287891024><:401_422_443_minotaur_bow:781573590403121172>",
            "<:401_422_443_dragon_wand:781573581246955601><:401_422_443_minotaur_wand:781573607322681384>",
            42,
            2
        ),  /*25*/
        Weapon(
            "Dragon/Minotaur Weapon(44+3)",
            "<:401_422_443_dragon_slayer:781573572421484555><:401_422_443_minotaur_slayer:781573599219286066>",
            "<:401_422_443_dragon_bow:781573565287891024><:401_422_443_minotaur_bow:781573590403121172>",
            "<:401_422_443_dragon_wand:781573581246955601><:401_422_443_minotaur_wand:781573607322681384>",
            44,
            3
        ),  /*26*/
        Weapon(
            "Icy Weapon(45+1)",
            "<:451_472_493_icy_broadsword:781573626281328690>",
            "<:451_472_493_icy_bow:781573616629841951>",
            "<:451_472_493_icy_wand:781573633792540692>",
            45,
            1
        ),  /*27*/
        Weapon(
            "Icy Weapon(47+2)",
            "<:451_472_493_icy_broadsword:781573626281328690>",
            "<:451_472_493_icy_bow:781573616629841951>",
            "<:451_472_493_icy_wand:781573633792540692>",
            47,
            2
        ),  /*28*/
        Weapon(
            "Icy Weapon(49+3)",
            "<:451_472_493_icy_broadsword:781573626281328690>",
            "<:451_472_493_icy_bow:781573616629841951>",
            "<:451_472_493_icy_wand:781573633792540692>",
            49,
            3
        ),  /*29*/
        Weapon(
            "Golden Weapon(50+1)",
            "<:501_522_543_golden_broadsword:802412010616520716>",
            "<:501_522_543_golden_bow:802412021806792755>",
            "<:501_522_543_golden_wand:802411996715679794>",
            50,
            1
        ),  /*30*/
        Weapon(
            "Golden Weapon(52+2)",
            "<:501_522_543_golden_broadsword:802412010616520716>",
            "<:501_522_543_golden_bow:802412021806792755>",
            "<:501_522_543_golden_wand:802411996715679794>",
            52,
            2
        ),  /*31*/
        Weapon(
            "Golden Weapon(54+3)",
            "<:501_522_543_golden_broadsword:802412010616520716>",
            "<:501_522_543_golden_bow:802412021806792755>",
            "<:501_522_543_golden_wand:802411996715679794>",
            54,
            3
        ),  /*32*/
        Weapon(
            "Golden Weapon(56+4)",
            "<:501_522_543_golden_broadsword:802412010616520716>",
            "<:501_522_543_golden_bow:802412021806792755>",
            "<:501_522_543_golden_wand:802411996715679794>",
            54,
            3
        ),  /*32*/
        Weapon(
            "Golden Weapon(58+5)",
            "<:501_522_543_golden_broadsword:802412010616520716>",
            "<:501_522_543_golden_bow:802412021806792755>",
            "<:501_522_543_golden_wand:802411996715679794>",
            54,
            3
        ),
    )
    fun weapon(attacktype: Int, mob: Int, mobs: Array<Mob>, base: Int, stat: Int, buff: Int) {
        val header = "Mob: **" + mobs.get(mob).name + mobs.get(mob)+"** Base: **" + base + " Stat: **" + stat + "** " + " Buffs: **+" + buff + "** \n"
        val attackstrings: Array<String>
        val str0: String
        val str1: String
        val str2: String
        var str3 = ""

        var tick = 1
        var min_raw_damage = 0.0
        var max_raw_damage = 0.0
        var max_raw_crit_damage = 0.0
        var accuracy = 0.0

        val threshold: Double
        if (attacktype == 0) { //Auto
            threshold = 0.1749
            attackstrings = arrayOf("(Auto)", "train")
        } else if (attacktype == 1) { //Melee
            tick = 4
            threshold = Formulas().threshold_Calc(tick.toDouble())
            attackstrings = arrayOf("(Spec)", "power train **Melee :crossed_swords:**")
        } else if (attacktype == 2) { //Distance
            tick = 4
            threshold = Formulas().threshold_Calc(tick.toDouble())
            attackstrings = arrayOf("(Spec)", "power train **Distance :bow_and_arrow:**")
        } else { //Magic
            tick = 4
            threshold = Formulas().threshold_Calc(tick.toDouble())
            attackstrings = arrayOf("(Spec)", "power train **Magic :fire:**")
        }
        //An index variable for weapons[]
        var pos = 0

        //Iterate through loop until you find a mob you can train on with greater than .1749 accuracy
        for (x in 0 until weapons.size) {
            val stat1: Int = stat + buff + weapons.get(x).buff
            if (attacktype == 0) { //Auto
                min_raw_damage = Formulas().auto_min_raw_damage_Calc(
                    stat1.toDouble(),
                    weapons[x].attack.toDouble(),
                    base.toDouble()
                )
                max_raw_damage = Formulas().auto_max_raw_damage_Calc(
                    stat1.toDouble(),
                    weapons[x].attack.toDouble(),
                    base.toDouble()
                )
                max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
                accuracy = Formulas().accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, mob, mobs)
            } else if (attacktype == 1) { //Melee
                min_raw_damage = Formulas().special_meldist_min_raw_damage_Calc(
                    stat1.toDouble(),
                    weapons.get(x).attack.toDouble(),
                    base.toDouble()
                )
                max_raw_damage = Formulas().special_meldist_max_raw_damage_Calc(
                    stat1.toDouble(),
                    weapons.get(x).attack.toDouble(),
                    base.toDouble()
                )
                max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
                accuracy = Formulas().accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, mob, mobs)
            } else if (attacktype == 2) { //Distance
                min_raw_damage = Formulas().special_meldist_min_raw_damage_Calc(
                    stat1.toDouble(),
                    weapons.get(x).attack.toDouble(),
                    base.toDouble()
                )
                max_raw_damage = Formulas().special_meldist_max_raw_damage_Calc(
                    stat1.toDouble(),
                    weapons.get(x).attack.toDouble(),
                    base.toDouble()
                )
                max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
                accuracy = Formulas().accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, mob, mobs)
            } else { //Magic
                min_raw_damage = Formulas().special_magic_min_raw_damage_Calc(
                    stat1.toDouble(),
                    weapons.get(x).attack.toDouble(),
                    base.toDouble()
                )
                max_raw_damage = Formulas().special_magic_max_raw_damage_Calc(
                    stat1.toDouble(),
                    weapons.get(x).attack.toDouble(),
                    base.toDouble()
                )
                max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
                accuracy = Formulas().accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, mob, mobs)
            }
            if (accuracy >= threshold) {
                pos = x
                break
            }
        }
        val stat2: Int = stat + buff + weapons.get(pos).buff

        val min_damage: Double = Formulas().min_damage_Calc(min_raw_damage, mob, mobs)
        val max_damage: Double = Formulas().max_damage_Calc(max_raw_damage, mob, mobs)
        val max_crit_damage: Double = Formulas().max_crit_damage_Calc(max_raw_crit_damage, mob, mobs)
        val avgdmg: Double = Formulas().average_damage_Calc(accuracy, max_damage, min_damage, max_crit_damage)
        val maxtickrate: Double = Formulas().max_tickrate_Calc(tick.toDouble()) as Double

        val alltickrate: Double
        if (attacktype == 0) {
            alltickrate = Formulas().tickrate_Calc(accuracy, 3600.0)
        } else {
            val totalaccuracy: Double = Formulas().total_accuracy_Calc(accuracy, tick.toDouble())
            alltickrate = Formulas().powertickrate_Calc(totalaccuracy, maxtickrate)
        }

        val time: Double = Formulas().time_to_kill_Calc(avgdmg, mob, mobs)
        str0 = "You can " + attackstrings[1] + " effectively on " + mobs.get(mob).name + " with a " + weapons.get(pos).name+ "!\n"
        str2 = "Average time to kill " + mobs.get(mob).name + ": " + time.toInt() / 60 + " min. " + time.toInt() % 60 + " sec.\n"

        var statadd = 0
        var newaccuracy = 0.0
        var checknextweapon = true
        if (pos >= 33 || pos <= 0) {
            checknextweapon = false
        }
        while (newaccuracy < threshold && checknextweapon) {
            val statneeded = stat2 + statadd

            val new_min_raw_damage: Double = Formulas().auto_min_raw_damage_Calc(
                statneeded.toDouble(),
                weapons.get(pos - 1).attack.toDouble(),
                base.toDouble()
            )
            val new_max_raw_damage: Double = Formulas().auto_max_raw_damage_Calc(
                statneeded.toDouble(),
                weapons.get(pos - 1).attack.toDouble(),
                base.toDouble()
            )

            val new_max_raw_critdamage: Double = Formulas().max_raw_crit_damage_Calc(new_max_raw_damage)

            newaccuracy = Formulas().accuracy_Calc(new_max_raw_critdamage, new_max_raw_damage, new_min_raw_damage, mob, mobs)
            statadd++
        }
        if (checknextweapon) {
            str1 = "Max. Damage " + attackstrings[0] + ": " + max_damage.toInt() + " " + " Tickrate: " + alltickrate.toInt() + " / " + maxtickrate.toInt() + "\n"
            str3 = "You need " + statadd + " stats to " + attackstrings[1] + " effectively on " + mobs.get(mob).name + " with a " + weapons.get(pos - 1)
                .name + "!\n"
        } else {
            str1 = "Min. Damage " + attackstrings[0] + ": " + min_damage.toInt() + " " + " Max. Damage " + attackstrings[0] + ": " + max_damage.toInt() + "\n"
        }

        println(str0)
        println(str1)
        println(str2)
        println(str3)
    }

    fun offline(stat1: Int, stat2: Int, hours: Int): String? {
        var message: String? = null
        if (stat2 > 0 && hours <= 0) {
            if (stat1 > stat2) {
                println("Stat2 must be greater than Stat1")
                message = "Stat2 must be greater than Stat1"
            }
            val ticks1: Double
            val ticks2: Double
            if (stat1 <= 54) {
                ticks1 = Formulas().stat0to54_Calc(stat1.toDouble())
            } else {
                ticks1 = Formulas().stat55to99_Calc(stat1.toDouble())
            }

            if (stat2 <= 54) {
                ticks2 = Formulas().stat0to54_Calc(stat2.toDouble())
            } else {
                ticks2 = Formulas().stat55to99_Calc(stat2.toDouble())
            }

            val totalticks = ticks2 - ticks1
            println(
                """1${
                    "Initial Stat: ** " + String.format(
                        "%,d",
                        stat1
                    ) + " **"
                } Goal Stat: **${String.format("%,d", stat2)}**
You need approximately** ${
                    String.format(
                        "%,.0f",
                        totalticks
                    )
                }** ticks until you reach stat level **${String.format("%,d", stat2)}**!
This is around **${String.format("%,.1f", totalticks * 60 / 600)}** minutes, or **${
                    String.format(
                        "%,.1f",
                        totalticks / 600
                    )
                }** hours of offline training at **600** exp/hr"""
             )
            message = """${
                "Initial Stat: ** " + String.format(
                    "%,d",
                    stat1
                ) + " **"
            } Goal Stat: **${String.format("%,d", stat2)}**
You need approximately** ${
                String.format(
                    "%,.0f",
                    totalticks
                )
            }** ticks until you reach stat level **${String.format("%,d", stat2)}**!
This is around **${String.format("%,.1f", totalticks * 60 / 600)}** minutes, or **${
                String.format(
                    "%,.1f",
                    totalticks / 600
                )
            }** hours of offline training at **600** exp/hr"""
        } else if (hours > 0 && stat2 <= 0) {
            val tickstrained = 600 * hours
            val ticks1: Double
            if (stat1 <= 54) {
                ticks1 = Formulas().stat0to54_Calc(stat1.toDouble())
            } else {
                ticks1 = Formulas().stat55to99_Calc(stat1.toDouble())
            }

            val ticks2 = tickstrained + ticks1
            val newStat = Math.round(100.0 * Formulas().findStatLevel_Calc(ticks2)) / 100.0
            if (newStat < 5) {
               println("Something went wrong: Could not find new stat")
                message = "Something went wrong: Could not find new stat"
            }
            println("""2${
                    "Initial Stat: ** " + String.format(
                        "%,d",
                        stat1
                    ) + " **"
                } Hours: **${String.format("%,d", hours)}**
Your new stat will be approximately: **$newStat** with **$hours** hours of offline training""")
            message = """2${
                "Initial Stat: ** " + String.format(
                    "%,d",
                    stat1
                ) + " **"
            } Hours: **${String.format("%,d", hours)}**
Your new stat will be approximately: **$newStat** with **$hours** hours of offline training"""
        } else {
           println("Something went wrong: Please enter either hours OR stat2")
            message = "Something went wrong: Please enter either hours OR stat2"
        }
        return message
    }

    fun oneshot(
        attacktype: Int,
        mob: Int,
        base: Int,
        stat: Int,
        buff: Int,
        mobs: Array<Mob>,
        weaponatk: Int,
        consistency: Double
    ): OneShotModel {
        val oneShotModel = OneShotModel(null, null, null, null,null,null,  null)
        val header = if (stat >= 5) {
            "Base: **$base** Stat: **$stat** Buffs: **+$buff** Weapon: **$weaponatk Atk**" + " Consistency: **" + consistency.toInt() + "%** \n"
        } else {
            "Base: **$base** Weapon: **$weaponatk Atk**" + " Consistency: **" + consistency.toInt() + "%** \n"
        }
        oneShotModel.base = header

        val mobInfo = "Mob: **" + mobs.get(mob).name + "** Health: **" + mobs.get(mob).health + "**\n"
        oneShotModel.creatureName =  mobs.get(mob).name
        oneShotModel.creatureHealth = mobs.get(mob).health.toString()
        var str0 =
            "" // You already oneshot this mob with ***% consistency, or You cannot oneshot this mob yet, DOES NOT CALCULATE IF STAT IS 0
        var str1 =
            "" // If the % consistency you can oneshot is already over consistency provided, string blank, else, say You need *** stats to oneshot this mob with ***% consistency
        var str2 = "" // Min Damage and Max Damage
        var str3 = "" // Critical Damage
        var currentConsistency = 0.0 // will be 0 if stat not provided

        var min_raw_damage = 0.0
        var max_raw_damage = 0.0
        var max_raw_crit_damage = 0.0

        val attackstrings: Array<String> = if (attacktype == 0) { // Auto
            arrayOf("(Auto)", "**Auto Attack**")
        } else if (attacktype == 1) { // Melee
            arrayOf("(Special :crossed_swords:)", "**Melee Special :crossed_swords:**")
        } else if (attacktype == 2) { // Distance
            arrayOf("(Special :bow_and_arrow:)", "**Distance Special :bow_and_arrow:**")
        } else { // Magic
            arrayOf("(Special :fire:)", "**Magic Special :fire:**")
        }

        if (stat >= 5) { // stat inputted
            val stat1 = stat + buff
            if (attacktype == 0) { // Normal
                min_raw_damage =
                    Formulas().auto_min_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
                max_raw_damage =
                    Formulas().auto_max_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
                max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
            } else if (attacktype == 3) { // Magic
                min_raw_damage =
                    Formulas().special_magic_min_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
                max_raw_damage =
                    Formulas().special_magic_max_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
                max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
            } else { // Melee and Distance
                min_raw_damage = Formulas().special_meldist_min_raw_damage_Calc(
                    stat1.toDouble(),
                    weaponatk.toDouble(),
                    base.toDouble()
                )
                max_raw_damage = Formulas().special_meldist_max_raw_damage_Calc(
                    stat1.toDouble(),
                    weaponatk.toDouble(),
                    base.toDouble()
                )
                max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
            }

            //Calculate current consistency
            currentConsistency = Formulas().consistency_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, mob, mobs)
            str0 = if (currentConsistency > 0) {
                "You **can** already one-shot a **" + mobs.get(mob).name + "** with " + attackstrings[1] + " at **" + (currentConsistency * 100).toInt() + "%** consistency!\n"
            } else {
                "You **cannot** one-shot a **" + mobs.get(mob).name + "** with " + attackstrings[1] + " yet!\n"
            }
            oneShotModel.oneShot = str0

            val min_damage: Double = Formulas().min_damage_Calc(min_raw_damage, mob, mobs)
            val max_damage: Double = Formulas().max_damage_Calc(max_raw_damage, mob, mobs)
            val max_crit_damage: Double = Formulas().max_crit_damage_Calc(max_raw_crit_damage, mob, mobs)

            val normalaccuracy: Double = Formulas().normal_accuracy_Calc(max_raw_damage, min_raw_damage, mob, mobs)
            val critaccuracy: Double = Formulas().crit_accuracy_Calc(max_raw_crit_damage, max_raw_damage, mob, mobs)

            str1 = if (normalaccuracy == 1.00) {
                "Min. Damage " + attackstrings[0] + ": **" + min_damage.toInt() + "** " + " Max. Damage " + attackstrings[0] + ": **" + max_damage.toInt() + "**\n"
            } else if (normalaccuracy > 0) {
                "Max. Damage " + attackstrings[0] + ": **" + max_damage.toInt() + "**\n"
            } else {
                "You aren't strong enough to deal normal damage to this mob!\n"
            }
            oneShotModel.damage = str1
            str2 = if (critaccuracy > 0) {
                "Maximum Critical Damage " + attackstrings[0] + ": **" + max_crit_damage.toInt() + "**\n"
            } else {
                "You aren't strong enough to deal critical damage to this mob!\n"
            }
            oneShotModel.criticalDamage = str2
        }

        var statneeded = 5
        var statFound = false
        if (currentConsistency * 100 < consistency) {
            while (!statFound && statneeded < 1000) {
                var new_min_raw_damage: Double
                var new_max_raw_damage: Double
                var new_max_raw_crit_damage: Double
                if (attacktype == 0) { // Normal
                    new_min_raw_damage =
                        Formulas().auto_min_raw_damage_Calc(statneeded.toDouble(), weaponatk.toDouble(), base.toDouble())
                    new_max_raw_damage =
                        Formulas().auto_max_raw_damage_Calc(statneeded.toDouble(), weaponatk.toDouble(), base.toDouble())
                    new_max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(new_max_raw_damage)
                } else if (attacktype == 3) { // Magic
                    new_min_raw_damage = Formulas().special_magic_min_raw_damage_Calc(
                        statneeded.toDouble(),
                        weaponatk.toDouble(),
                        base.toDouble()
                    )
                    new_max_raw_damage = Formulas().special_magic_max_raw_damage_Calc(
                        statneeded.toDouble(),
                        weaponatk.toDouble(),
                        base.toDouble()
                    )
                    new_max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(new_max_raw_damage)
                } else { // Melee and Distance
                    new_min_raw_damage = Formulas().special_meldist_min_raw_damage_Calc(
                        statneeded.toDouble(),
                        weaponatk.toDouble(),
                        base.toDouble()
                    )
                    new_max_raw_damage = Formulas().special_meldist_max_raw_damage_Calc(
                        statneeded.toDouble(),
                        weaponatk.toDouble(),
                        base.toDouble()
                    )
                    new_max_raw_crit_damage = Formulas().max_raw_crit_damage_Calc(new_max_raw_damage)
                }
                if (Formulas().consistency_Calc(
                        new_max_raw_crit_damage,
                        new_max_raw_damage,
                        new_min_raw_damage,
                        mob,
                        mobs
                    ) * 100 >= consistency
                ) {
                    statFound = true
                } else {
                    statneeded++
                }
            }
            str3 = if (!statFound) {
                "We could not find the necessary stat level for you to one-shot a **" + mobs.get(mob).name + "** because it is too high!\n" //could find appropriate stat
            } else {
                "You need stat **level " + statneeded + "** to one-shot a **" + mobs.get(mob).name + "** with **" + consistency.toInt() + "%** consistency\n"
            }
            oneShotModel.needStats = str3
        }
        //embed.appendDescription(header + mobInfo + str0 + str1 + str2 + str3)
        println("STATS: "+header)
        println("INFO: "+mobInfo)
        println("ONE-SHOT: "+str0)
        println("DAMAGE: "+str1)
        println("CRITICAL: "+str2)
        println("NEED STATS: "+str3)
        return oneShotModel
    }


}