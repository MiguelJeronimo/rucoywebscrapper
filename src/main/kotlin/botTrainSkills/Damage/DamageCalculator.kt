package botTrainSkills.Damage

import botTrainSkills.Formulas
import com.sun.source.tree.ArrayAccessTree
import model.Mob


class DamageCalculator {
    fun damageCalculator(
        attacktype: Int,
        mob: Int,
        mobs: Array<Mob>,
        base: Int,
        stat: Int,
        buff: Int,
        weaponatk: Int
    ) {
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

        str1 = if (normalaccuracy == 1.00) {
            "Min. Damage (" + attacktypestring + "): **" + min_damage.toInt() + "** " + " Max. Damage (" + attacktypestring + "): **" + max_damage.toInt() + "**\n"
        } else if (normalaccuracy > 0) {
            "Max. Damage (" + attacktypestring + "): **" + max_damage.toInt() + "**\n"
        } else {
            "You aren't strong enough to deal normal damage to this mob."
        }
        str2 = if (critaccuracy > 0) {
            "Maximum Critical Damage (" + attacktypestring + "): **" + max_crit_damage.toInt() + "**\n"
        } else {
            "You aren't strong enough to deal critical damage to this mob."
        }
        //embed.appendDescription(header + str0 + str1 + str2)
        println("STR1: $str0")
        println("STR1: $str1")
        println("STR1: $str2")
    }

//    fun weapon(event: SlashCommandInteractionEvent, attacktype: Int, mob: Int, base: Int, stat: Int, buff: Int) {
//        val header = ((("Mob: **" + mobs.get(mob).getMob_name() + mobs.get(mob)
//            .getEmoji_code() + slime_lord_emoji).toString() + "** Base: **" + base + "** " + slime_lord_emoji).toString() + " Stat: **" + stat + "** " + slime_lord_emoji).toString() + " Buffs: **+" + buff + "** \n"
//        val attackstrings: Array<String>
//        val str0: String
//        val str1: String
//        val str2: String
//        var str3 = ""
//
//        var tick = 1
//        var min_raw_damage = 0.0
//        var max_raw_damage = 0.0
//        var max_raw_crit_damage = 0.0
//        var accuracy = 0.0
//
//        val threshold: Double
//        if (attacktype == 0) { //Auto
//            threshold = 0.1749
//            attackstrings = arrayOf("(Auto)", "train")
//        } else if (attacktype == 1) { //Melee
//            tick = 4
//            threshold = Formulas.threshold_Calc(tick.toDouble())
//            attackstrings = arrayOf("(Spec)", "power train **Melee :crossed_swords:**")
//        } else if (attacktype == 2) { //Distance
//            tick = 4
//            threshold = Formulas.threshold_Calc(tick.toDouble())
//            attackstrings = arrayOf("(Spec)", "power train **Distance :bow_and_arrow:**")
//        } else { //Magic
//            tick = 4
//            threshold = Formulas.threshold_Calc(tick.toDouble())
//            attackstrings = arrayOf("(Spec)", "power train **Magic :fire:**")
//        }
//        //An index variable for weapons[]
//        var pos = 0
//
//        //Iterate through loop until you find a mob you can train on with greater than .1749 accuracy
//        for (x in 0 until weapons.length) {
//            val stat1: Int = stat + buff + weapons.get(x).getWeapon_buffs()
//            if (attacktype == 0) { //Auto
//                min_raw_damage = Formulas.auto_min_raw_damage_Calc(
//                    stat1.toDouble(),
//                    weapons.get(x).getWeapon_attack(),
//                    base.toDouble()
//                )
//                max_raw_damage = Formulas.auto_max_raw_damage_Calc(
//                    stat1.toDouble(),
//                    weapons.get(x).getWeapon_attack(),
//                    base.toDouble()
//                )
//                max_raw_crit_damage = Formulas.max_raw_crit_damage_Calc(max_raw_damage)
//                accuracy = Formulas.accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, mob)
//            } else if (attacktype == 1) { //Melee
//                min_raw_damage = Formulas.special_meldist_min_raw_damage_Calc(
//                    stat1.toDouble(),
//                    weapons.get(x).getWeapon_attack(),
//                    base.toDouble()
//                )
//                max_raw_damage = Formulas.special_meldist_max_raw_damage_Calc(
//                    stat1.toDouble(),
//                    weapons.get(x).getWeapon_attack(),
//                    base.toDouble()
//                )
//                max_raw_crit_damage = Formulas.max_raw_crit_damage_Calc(max_raw_damage)
//                accuracy = Formulas.accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, mob)
//            } else if (attacktype == 2) { //Distance
//                min_raw_damage = Formulas.special_meldist_min_raw_damage_Calc(
//                    stat1.toDouble(),
//                    weapons.get(x).getWeapon_attack(),
//                    base.toDouble()
//                )
//                max_raw_damage = Formulas.special_meldist_max_raw_damage_Calc(
//                    stat1.toDouble(),
//                    weapons.get(x).getWeapon_attack(),
//                    base.toDouble()
//                )
//                max_raw_crit_damage = Formulas.max_raw_crit_damage_Calc(max_raw_damage)
//                accuracy = Formulas.accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, mob)
//            } else { //Magic
//                min_raw_damage = Formulas.special_magic_min_raw_damage_Calc(
//                    stat1.toDouble(),
//                    weapons.get(x).getWeapon_attack(),
//                    base.toDouble()
//                )
//                max_raw_damage = Formulas.special_magic_max_raw_damage_Calc(
//                    stat1.toDouble(),
//                    weapons.get(x).getWeapon_attack(),
//                    base.toDouble()
//                )
//                max_raw_crit_damage = Formulas.max_raw_crit_damage_Calc(max_raw_damage)
//                accuracy = Formulas.accuracy_Calc(max_raw_crit_damage, max_raw_damage, min_raw_damage, mob)
//            }
//            if (accuracy >= threshold) {
//                pos = x
//                break
//            }
//        }
//        val stat2: Int = stat + buff + weapons.get(pos).getWeapon_buffs()
//
//        val min_damage: Double = Formulas.min_damage_Calc(min_raw_damage, mob)
//        val max_damage: Double = Formulas.max_damage_Calc(max_raw_damage, mob)
//        val max_crit_damage: Double = Formulas.max_crit_damage_Calc(max_raw_crit_damage, mob)
//        val avgdmg: Double = Formulas.average_damage_Calc(accuracy, max_damage, min_damage, max_crit_damage)
//        val maxtickrate: Double = Formulas.max_tickrate_Calc(tick.toDouble())
//
//        val alltickrate: Double
//        if (attacktype == 0) {
//            alltickrate = Formulas.tickrate_Calc(accuracy, 3600.0)
//        } else {
//            val totalaccuracy: Double = Formulas.total_accuracy_Calc(accuracy, tick.toDouble())
//            alltickrate = Formulas.powertickrate_Calc(totalaccuracy, maxtickrate)
//        }
//
//        val time: Double = Formulas.time_to_kill_Calc(avgdmg, mob)
//        str0 = (("You can " + attackstrings[1] + " effectively on **" + mobs.get(mob).getMob_name() + mobs.get(mob)
//            .getEmoji_code()).toString() + "** with a **" + weapons.get(pos).getWeapon_name() + weapons.get(pos)
//            .getEmoji_code()).toString() + "**!\n"
//        str2 = ("Average time to kill **" + mobs.get(mob).getMob_name() + mobs.get(mob)
//            .getEmoji_code()).toString() + "**: **" + time.toInt() / 60 + "** min. **" + time.toInt() % 60 + "** sec.\n"
//
//        var statadd = 0
//        var newaccuracy = 0.0
//        var checknextweapon = true
//        if (pos >= 33 || pos <= 0) {
//            checknextweapon = false
//        }
//        while (newaccuracy < threshold && checknextweapon) {
//            val statneeded = stat2 + statadd
//
//            val new_min_raw_damage: Double = Formulas.auto_min_raw_damage_Calc(
//                statneeded.toDouble(),
//                weapons.get(pos - 1).getWeapon_attack(),
//                base.toDouble()
//            )
//            val new_max_raw_damage: Double = Formulas.auto_max_raw_damage_Calc(
//                statneeded.toDouble(),
//                weapons.get(pos - 1).getWeapon_attack(),
//                base.toDouble()
//            )
//
//            val new_max_raw_critdamage: Double = Formulas.max_raw_crit_damage_Calc(new_max_raw_damage)
//
//            newaccuracy = Formulas.accuracy_Calc(new_max_raw_critdamage, new_max_raw_damage, new_min_raw_damage, mob)
//            statadd++
//        }
//        if (checknextweapon) {
//            str1 =
//                ("Max. Damage " + attackstrings[0] + ": **" + max_damage.toInt() + "** " + slime_lord_emoji).toString() + " Tickrate: **" + alltickrate.toInt() + " / " + maxtickrate.toInt() + "**\n"
//            str3 = (("You need **" + statadd + "** stats to " + attackstrings[1] + " effectively on **" + mobs.get(mob)
//                .getMob_name() + mobs.get(mob).getEmoji_code()).toString() + "** with a **" + weapons.get(pos - 1)
//                .getWeapon_name() + weapons.get(pos).getEmoji_code()).toString() + "**!\n"
//        } else {
//            str1 =
//                ("Min. Damage " + attackstrings[0] + ": **" + min_damage.toInt() + "** " + slime_lord_emoji).toString() + " Max. Damage " + attackstrings[0] + ": **" + max_damage.toInt() + "**\n"
//        }
//
//        //embed.appendDescription(header + str0 + str1 + str2 + str3)
//    }

}