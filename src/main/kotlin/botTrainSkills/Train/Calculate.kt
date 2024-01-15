package botTrainSkills.Train

import botTrainSkills.Formulas
import model.*
import model.Mob

class Calculate {
    fun train(base: Int, stat: Int, buff: Int, weaponatk: Int, mobs: Array<Mob>) {
        val sprite: ArrayList<Sprite> = ArrayList()
        val sprite1 = Sprite(null, null)
        val sprite2 = Sprite(null, null)
        val train = Train(null, null, null, null,null,null)
        val deal = Deal(null, null)
        val need = Need(null, null)
        val header =
            "Base: **$base** Stat: **$stat** Buffs: **+$buff** Weapon: **$weaponatk Atk**\n"
        println(header)
        var str0 = "" //You can train effectively on...
        var str1 = "" //Max Damage...
        var str2 = "" //Average time to kill...
        var str3 = "" //Average time to kill...
        var str4 = "" //You need...
        var str5 = "" //You deal...

        val stat1 = stat + buff
        val min_raw_damage: Double = Formulas().auto_min_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
        val max_raw_damage: Double = Formulas().auto_max_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
        val max_raw_crit_damage: Double = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
        var accuracy = 0.0

        //An index variable for mobs[]
        var pos = 0

        //Iterate through loop until you find a mob you can train on with greater than .1749 accuracy
        for (x in mobs.size - 1 downTo 0) {
            if (x == 26 || x == 31) {
                continue
            }
            accuracy = Formulas().accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, x, mobs)
            if (accuracy >= 0.1749) {
                pos = x
                break
            }
        }

        //Calculate average damage which you need for average time to kill
        val min_damage: Double = Formulas().min_damage_Calc(min_raw_damage, pos, mobs)
        val max_damage: Double = Formulas().max_damage_Calc(max_raw_damage, pos, mobs)
        val max_crit_damage: Double = Formulas().max_crit_damage_Calc(max_raw_crit_damage, pos, mobs)
        val avgdmg: Double = Formulas().average_damage_Calc(accuracy, max_damage, min_damage, max_crit_damage)
        val tickrate: Double = Formulas().tickrate_Calc(accuracy, 3600.0)

        //In certain cases you can effective train on two mobs
        var onemob = true
        var checknextmob = true
        var newpos = pos + 1
        if (pos == 5 || pos == 20 || pos == 22 || pos == 28 || pos == 30) {
            pos--
            onemob = false
        }
        if (newpos > 40) {
            checknextmob = false
        }
        if (newpos == 26 || newpos == 31) {
            newpos++
        }

        val time: Double = Formulas().time_to_kill_Calc(avgdmg, pos, mobs)
        str0 = "You can train effectively on **${mobs[pos].name}**!\n"
        sprite1.nameCreature = mobs[pos].name
//        sprite.add(
//            Sprite(
//                mobs[pos].name,
//
//            )
//        )
        if (!onemob) {
            val time2: Double = Formulas().time_to_kill_Calc(avgdmg, pos + 1, mobs)
            str0 = "You can train effectively on **" + mobs[pos].name + " & " + mobs[pos + 1].name +"**!\n"
            sprite1.nameCreature = mobs[pos].name
            sprite2.nameCreature = mobs[pos + 1].name
            str3 = "1. Average time to kill **" + mobs[pos + 1].name + "**: **" + time2.toInt() / 60 + "** min. **" + time2.toInt() % 60 + "** sec.\n" //part of output embed
            sprite2.average_time_to_kill = "${time2.toInt() / 60} min. ${time2.toInt() % 60} sec."
        }

        //calculating stats you need to train on the next mob
        var statadd = 0
        var checked = false
        var alrdealdamage = false
        var dealdamage = false
        var new_max_damage: Double
        var newaccuracy = 0.0
        while (newaccuracy < 0.1749 && checknextmob) {
            val statneeded = stat1 + statadd

            val new_min_raw_damage: Double = Formulas().auto_min_raw_damage_Calc(statneeded.toDouble(), weaponatk.toDouble(), base.toDouble())
            val new_max_raw_damage: Double = Formulas().auto_max_raw_damage_Calc(statneeded.toDouble(), weaponatk.toDouble(), base.toDouble())

            val new_max_raw_critdamage: Double = Formulas().max_raw_crit_damage_Calc(new_max_raw_damage)

            new_max_damage = Formulas().max_damage_Calc(new_max_raw_damage, newpos, mobs)
            newaccuracy = Formulas().accuracy_Calc(new_max_raw_critdamage, new_max_raw_damage, new_min_raw_damage, newpos, mobs)
            if (new_max_damage >= 1 && !checked) { //if you can already deal damage to the next mob
                str5 = (("You can deal **" + new_max_damage.toInt() + "** max damage to **" + mobs[newpos].name) + "**!") //part of output
                alrdealdamage = true
                deal.MAX_ATTACK = new_max_damage.toInt()
                deal.nameCreature = mobs[newpos].name
            } else if (new_max_damage > 1 && !alrdealdamage && !dealdamage) { //if you cant deal damage to the next mob yet, you can deal damage in a certain amount of stats!
                str5 = (("You can deal **" + new_max_damage.toInt() + "** max damage to **" + mobs[newpos].name) + "** in **" + statadd + "** stats!") //part of output
                dealdamage = true
                deal.MAX_ATTACK = new_max_damage.toInt()
                deal.nameCreature = mobs[newpos].name
            }
            checked = true
            statadd++
        }
        if (checknextmob) {
            str1 = "Max. Damage: **" + max_damage.toInt() + "** " + " Tickrate: **" + tickrate.toInt() + " / 3600**\n"
            train.MAX_ATTACK = max_damage.toInt()
            train.tickrate = "${tickrate.toInt()} / 3600"
            str2 = "2. Average time to kill **" + mobs[pos].name + "**: **" + time.toInt() / 60 + "** min. **" + time.toInt() % 60 + "** sec.\n"
            str4 = "You need **" + statadd + "** stats to train effectively on **" + mobs[newpos].name + "**!\n"
            need.nameCreature = mobs[newpos].name
            need.stats = statadd
            sprite1.nameCreature =  mobs[pos].name
            sprite1.average_time_to_kill = "${time.toInt() / 60} mins. ${time.toInt() % 60 } sec."
        } else {
            str1 = "Min. Damage (Auto): **" + min_damage.toInt() + " Max. Damage (Auto): **" + max_damage.toInt() + "**\n"
            train.MAX_ATTACK = max_damage.toInt()
            train.MIN_ATTACK = min_damage.toInt()
            str2 = "3. Average time to kill **${mobs[pos].name}**: **${time.toInt() / 60}** min. **${time.toInt() % 60}** sec.\n"
            sprite1.nameCreature =  mobs[pos].name
            sprite1.average_time_to_kill = "${time.toInt() / 60} mins. ${time.toInt() % 60 } sec."
        }
        //println(header + str0 + str1 + str2 + str3 + str4 + str5)
        //armado de mi objeto de respuesta:
        sprite.add(sprite1)
        sprite.add(sprite2)
        train.deal = deal
        train.train_effective = sprite
        train.need = need
        println("Header: $header")
        println("You can train effectively: $str0")
        println("Max. Damage y tickrate: $str1")
        println("Average time to kill: $str2")
        println("Average time to kill: $str3")
        println("You need: $str4")
        println("You can deal: $str5")
        println("//////////////////////////////////////////////////////////////////")
        println(train)

    }
}