package botTrainSkills.PowerTrainner

import botTrainSkills.Formulas
import model.*


class PowerTrainner {
    fun ptrain(
        classtype: Int,
        base: Int,
        stat: Int,
        buff: Int,
        weaponatk: Int,
        tick: Int,
        mobs: Array<Mob>
    ) {
        val sprite: ArrayList<Sprite> = ArrayList()
        val sprite1 = Sprite(null, null)
        val train = Train(null, null, null, null,null,null)
        val deal = Deal(null, null)
        val need = Need(null, null)
        val str0: String // You can power train effectively on...
        val str1: String // Max Damage... Tickrate...
        val str2: String // Average time to kill...
        var str3 = "" // You need... stats to train effectively on...
        var str4 = "" // You can deal... max damage to...
        var classEmoji = ""

        val stat1 = stat + buff
        val max_raw_damage: Double
        val min_raw_damage: Double

        if (classtype == 2) {
            min_raw_damage =
                Formulas().special_magic_min_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            max_raw_damage =
                Formulas().special_magic_max_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            classEmoji = "Magic :fire:"
        } else {
            min_raw_damage =
                Formulas().special_meldist_min_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            max_raw_damage =
                Formulas().special_meldist_max_raw_damage_Calc(stat1.toDouble(), weaponatk.toDouble(), base.toDouble())
            classEmoji = if (classtype == 0) {
                "Melee :crossed_swords:"
            } else {
                "Distance :bow_and_arrow:"
            }
        }
        val header =
            "Base: **$base**  Stat: **$stat**  Buffs: **+$buff**  Weapon: **$weaponatk Atk **  Tick: **$tick**\n"

        val max_raw_crit_damage: Double = Formulas().max_raw_crit_damage_Calc(max_raw_damage)
        var accuracy = 0.0
        var pos = 0
        val threshold: Double = Formulas().threshold_Calc(tick.toDouble())

        //Iterate through loop until you find a mob you can power train on with greater than .1749 accuracy
        for (x in mobs.size - 1 downTo 0) {
            if (x == 13 || x == 19 || x == 20 || x == 22 || x == 24 || x == 25 || x == 26 || x == 29 || x == 31 || x == 33 || x == 36 || x == 37 || x >= 39) {
                continue
            }
            accuracy = Formulas().accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, x, mobs)
            if (accuracy >= threshold) {
                pos = x
                break
            }
        }

        //Calculate average damage which you need for average time to kill
        val min_damage: Double = Formulas().min_damage_Calc(min_raw_damage, pos, mobs)
        val max_damage: Double = Formulas().max_damage_Calc(max_raw_damage, pos, mobs)
        val max_crit_damage: Double = Formulas().max_crit_damage_Calc(max_raw_crit_damage, pos, mobs)
        val avgdmg: Double = Formulas().average_damage_Calc(accuracy, max_damage, min_damage, max_crit_damage)
        val totalaccuracy: Double = Formulas().total_accuracy_Calc(accuracy, tick.toDouble())
        val maxtickrate: Double = Formulas().max_tickrate_Calc(tick.toDouble()) as Double
        val powertickrate: Double = Formulas().powertickrate_Calc(totalaccuracy, maxtickrate)
        val time: Double = Formulas().time_to_kill_Calc(avgdmg, pos, mobs)

        str0 = "You can power train ** " + classEmoji + "** on  **" + mobs[pos].name+"**!\n"
        sprite1.nameCreature = mobs[pos].name
        //calculating stats you need to train on the next mob
        var statadd = 0
        var checked = false
        var alrdealdamage = false
        var dealdamage = false
        var new_max_damage: Double
        var newaccuracy = 0.0
        var newpos = pos + 1
        while (true) {
            if (newpos == 13 || newpos == 19 || newpos == 20 || newpos == 22 || newpos == 24 || newpos == 25 || newpos == 26 || newpos == 29 || newpos == 31 || newpos == 33 || newpos == 36 || newpos == 37) {
                newpos++
            } else if (newpos >= 38) {
                newpos = 38
                break
            } else {
                break
            }
        }
        val checknextmob = pos != 38

        while (newaccuracy < threshold && checknextmob) {
            val statneeded = stat1 + statadd

            var new_max_raw_damage: Double
            var new_min_raw_damage: Double

            if (classtype == 2) {
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
            } else {
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
            }

            val new_max_raw_crit_damage: Double = Formulas().max_raw_crit_damage_Calc(new_max_raw_damage)

            new_max_damage = Formulas().max_damage_Calc(new_max_raw_damage, newpos, mobs)

            newaccuracy =
                Formulas().accuracy_Calc(new_max_raw_crit_damage, new_max_raw_damage, new_min_raw_damage, newpos, mobs)
            if (new_max_damage >= 1 && !checked) { //if you can already deal damage to the next mob
                str4 = "You can deal **" + new_max_damage.toInt() + "** max damage to **" + mobs.get(newpos).name + "**!" //part of output
                alrdealdamage = true
                deal.MAX_ATTACK = new_max_damage.toInt()
                deal.nameCreature = mobs[newpos].name
            } else if (new_max_damage > 1 && !alrdealdamage && !dealdamage) { //if you cant deal damage to the next mob yet, you can deal damage in a certain amount of stats!
                str4 = "You can deal **" + new_max_damage.toInt() + "** max damage to **" + mobs.get(newpos).name + "** in **" + statadd + "** stats!" //part of output
                dealdamage = true
                deal.MAX_ATTACK = new_max_damage.toInt()
                deal.nameCreature = mobs[newpos].name
            }
            checked = true
            statadd++
        }

        //Building remaining Strings
        if (checknextmob) {
            str1 = "Max. Damage: **" + max_damage.toInt() + "** " + " Tickrate: **" + powertickrate.toInt() + " / " + maxtickrate.toInt() + "**\n"
            str2 = "Average time to kill **" + mobs.get(pos).name + "**: **" + time.toInt() / 60 + "** min. **" + time.toInt() % 60 + "** sec.\n"
            str3 = "You need **" + statadd + "** stats to power train effectively on **" + mobs.get(newpos).name + "**!\n"
            train.MAX_ATTACK = max_damage.toInt()
            train.tickrate = "${powertickrate.toInt()} / ${maxtickrate.toInt()}"
            train.MAX_ATTACK = max_damage.toInt()
            train.MIN_ATTACK = min_damage.toInt()
            need.nameCreature = mobs[newpos].name
            need.stats = statadd
            sprite1.nameCreature =  mobs[pos].name
            sprite1.average_time_to_kill = "${time.toInt() / 60} mins. ${time.toInt() % 60 } sec."
        } else {
            str1 = "Min. Damage (Auto): **" + min_damage.toInt() + "** " + " Max. Damage (Auto): **" + max_damage.toInt() + "**\n"
            str2 = "Average time to kill **" + mobs.get(pos).name + "**: **" + time.toInt() / 60 + "** min. **" + time.toInt() % 60 + "** sec.\n"
            train.MAX_ATTACK = max_damage.toInt()
            train.MIN_ATTACK = min_damage.toInt()
            sprite1.nameCreature =  mobs[pos].name
            sprite1.average_time_to_kill = "${time.toInt() / 60} mins. ${time.toInt() % 60 } sec."
        }
        sprite.add(sprite1)
        train.deal = deal
        train.train_effective = sprite
        train.need = need
        println("Header: $header")
        println("You can train effectively: $str0")
        println("Max. Damage y tickrate: $str1")
        println("Average time to kill: $str2")
        println("Average time to kill: $str3")
        println("You need: $str4")
        println("emojiClass: $classEmoji")
        println("///////////////////////////////////////////////////////////////////////////////")
        println(train)
    }
}