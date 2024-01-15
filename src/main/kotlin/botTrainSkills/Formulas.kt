package botTrainSkills

import model.Mob
import kotlin.math.pow

class Formulas {
    fun auto_min_raw_damage_Calc(stat: Double, weaponatk: Double, base: Double): Double {
        return (stat * weaponatk) / 20 + (base) / 4
    }

    fun auto_max_raw_damage_Calc(stat: Double, weaponatk: Double, base: Double): Double {
        return (stat * weaponatk) / 10 + (base) / 4
    }

    fun special_meldist_min_raw_damage_Calc(stat: Double, weaponatk: Double, base: Double): Double {
        return 1.5 * ((stat * weaponatk) / 20 + (base) / 4)
    }

    fun special_meldist_max_raw_damage_Calc(stat: Double, weaponatk: Double, base: Double): Double {
        return 1.5 * ((stat * weaponatk) / 10 + (base) / 4)
    }

    fun special_magic_min_raw_damage_Calc(stat: Double, weaponatk: Double, base: Double): Double {
        return 1.5 * (((1.05 * (stat) * weaponatk) / 20) + 9 * (base) / 32)
    }

    fun special_magic_max_raw_damage_Calc(stat: Double, weaponatk: Double, base: Double): Double {
        return 1.5 * (((1.05 * (stat) * weaponatk) / 10) + 9 * (base) / 32)
    }

    fun min_damage_Calc(min_raw_damage: Double, pos: Int, mobs: Array<Mob>): Double {
        var min_damage: Double = min_raw_damage - mobs[pos].defense
        if (min_damage < 0) {
            min_damage = 0.0
        }
        return min_damage
    }

    fun max_damage_Calc(max_raw_damage: Double, pos: Int, mobs: Array<Mob>): Double {
        return max_raw_damage - mobs[pos].defense
    }

    fun max_raw_crit_damage_Calc(max_raw_damage: Double): Double {
        return max_raw_damage * 1.05
    }

    fun max_crit_damage_Calc(max_raw_crit_damage: Double, pos: Int, mobs: Array<Mob>): Double {
        return max_raw_crit_damage - mobs.get(pos).defense
    }

    fun normal_accuracy_Calc(max_raw_damage: Double, min_raw_damage: Double, x: Int, mobs: Array<Mob>): Double {
        var normalaccuracy: Double =
            (max_raw_damage - mobs[x].defense) / (max_raw_damage - min_raw_damage)
        if (normalaccuracy > 1.00) {
            normalaccuracy = 1.00
        }
        return normalaccuracy
    }

    fun crit_accuracy_Calc(max_raw_crit_damage: Double, max_raw_damage: Double, x: Int, mobs: Array<Mob>): Double {
        var critaccuracy: Double =
            (max_raw_crit_damage - mobs.get(x).defense) / (max_raw_crit_damage - max_raw_damage)
        if (critaccuracy > 1.00) {
            critaccuracy = 1.00
        }
        return critaccuracy
    }

    fun accuracy_Calc(max_raw_crit_damage: Double, max_raw_damage: Double, min_raw_damage: Double, x: Int, mobs: Array<Mob>): Double {
        return (normal_accuracy_Calc(max_raw_damage, min_raw_damage, x, mobs) * 0.99) + (crit_accuracy_Calc(
            max_raw_crit_damage,
            max_raw_damage,
            x,
            mobs
        ) * 0.01)
    }

    fun total_accuracy_Calc(accuracy: Double, tick: Double): Double {
        return 1.0 - (1.0 - accuracy).pow(tick).pow(10.0)
    }

    fun time_to_kill_Calc(avgdmg: Double, pos: Int, mobs: Array<Mob>): Double {
        return mobs[pos].health / avgdmg
    }

    fun average_damage_Calc(accuracy: Double, max_damage: Double, min_damage: Double, max_crit_damage: Double): Double {
        return (accuracy) * (.99 * ((max_damage + min_damage) / 2)) + 0.01 * ((max_crit_damage + max_damage) / 2)
    }

    fun tickrate_Calc(accuracy: Double, maxtickrate: Double): Double {
        return maxtickrate * (1.0 - (1.0 - accuracy).pow(10.0))
    }

    fun powertickrate_Calc(totalaccuracy: Double, maxtickrate: Double): Double {
        return maxtickrate * totalaccuracy
    }

    fun max_tickrate_Calc(tick: Double): Number {
        return if (tick <= 5) {
            tick * 3600
        } else {
            18000
        }
    }

    fun exp_Calc(base: Double): Double {
        return base.pow((base / 1000) + 3)
    }

    fun stat0to54_Calc(stat: Double): Double {
        return stat.pow((stat / 1000) + 2.373)
    }

    fun stat55to99_Calc(stat: Double): Double {
        // final double offset = 4692.687;
        // double adjustedStat = stat + 15.68952;
        // return Math.pow(adjustedStat, (adjustedStat/1000) + 2.272) - offset;
        return stat.pow((stat / 1000) + 2.171)
    }

    /*
    public static double stat100to599_Calc(double stat){
        final double offset = 22516.303;
        double adjustedStat = stat + 45.43406;
        // return Math.pow(adjustedStat, (adjustedStat/1000) + 2.171) - offset;
        return Math.pow(stat, (stat/1000) + 2.373);
    }
    public static double stat600plus_Calc(double stat){
        final double offset = 2766484;
        double adjustedStat = stat + 110.34322;
        return Math.pow(adjustedStat, (adjustedStat/1000) + 2.070) - offset;
    }
    */
    fun findStatLevel_Calc(ticks2: Double): Double {
        if (ticks2 <= stat0to54_Calc(54.0)) {
            for (stat in 5..54) {
                if (ticks2 <= stat0to54_Calc(stat.toDouble())) {
                    val fract =
                        (ticks2 - stat0to54_Calc((stat - 1).toDouble())) / (stat0to54_Calc(stat.toDouble()) - stat0to54_Calc(
                            (stat - 1).toDouble()
                        ))
                    return (stat - 1) + fract
                }
            }
        } else { // if (ticks2 <= stat55to99_Calc(99)){
            for (stat in 55..1000) { // 99; stat++){
                if (ticks2 <= stat55to99_Calc(stat.toDouble())) {
                    val fract =
                        (ticks2 - stat55to99_Calc((stat - 1).toDouble())) / (stat55to99_Calc(stat.toDouble()) - stat55to99_Calc(
                            (stat - 1).toDouble()
                        ))
                    return (stat - 1) + fract
                }
            }
        }
        /*
        else if (ticks2 <= stat100to599_Calc(599)) {
            for (int stat = 100; stat <= 599; stat++){
                if (ticks2 <= stat100to599_Calc(stat)){
                    double fract = (ticks2 - stat100to599_Calc(stat-1))/(stat100to599_Calc(stat) - stat100to599_Calc(stat-1));
                    return (stat-1) + fract;
                }
            }
        } else {
            for (int stat = 600; stat <= 1000; stat++){
                if (ticks2 <= stat600plus_Calc(stat)){
                    double fract = (ticks2 - stat600plus_Calc(stat-1))/(stat600plus_Calc(stat) - stat600plus_Calc(stat-1));
                    return (stat-1) + fract;
                }
            }
        }
        */
        return (-1).toDouble()
    }

    fun threshold_Calc(tick: Double): Double {
        return 1.0 - .8251.pow((1.0 / tick))
    }

    fun consistency_Calc(
        max_raw_crit_damage: Double,
        max_raw_damage: Double,
        min_raw_damage: Double,
        mob: Int,
        mobs: Array<Mob>
    ): Double {
        val health: Int = mobs[mob].health
        val defense: Int = mobs[mob].defense
        val totaldefense = health + defense

        if (totaldefense - max_raw_crit_damage > 0) {
            return 0.0
        }

        val range = max_raw_damage - min_raw_damage
        val normaloneshots = max_raw_damage - totaldefense
        if (normaloneshots > 0) {
            val normalconsistency = (normaloneshots / range)
            return normalconsistency * 0.99 + 0.01
        } else {
            val critrange = max_raw_crit_damage - max_raw_damage
            val criticaloneshots = max_raw_crit_damage - totaldefense
            return (criticaloneshots / critrange) * 0.01
        }
    }
}