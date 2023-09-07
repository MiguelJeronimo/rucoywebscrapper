package Calculate.Formulas

class Formula {
    //min attack
    fun normalMinRawDamage(stat: Float, weapon: Float, base: Float): Int {
        //((B2/2)*(B3/10))+B1/4
        val stat_weapon = ((stat*weapon)/20)
        val level = (base/4)
        val min_raw_damage = stat_weapon+level
        return Math.round(min_raw_damage)
    }
    fun specialPhysicalMinSpecialDamage(stat: Float, weapon: Float, base: Float): Int {
        //((B2/2)*(B3/10)+B1/4)*1.5
        val min_raw_damage = 1.5*normalMinRawDamage(stat, weapon, base)
        return Math.round(min_raw_damage.toFloat())
    }
    fun specialMagicMinDamage(stat: Float, weapon: Float, base: Float): Int {
        val min_damage = 1.6*normalMinRawDamage(stat, weapon, base)
        return Math.round(min_damage.toFloat())
    }
    fun normalMaxRawDamage(stat: Float, weapon: Float, base: Float): Int {
        //(B2*(B3/10))+B1/4
        val max_raw_damage = ((stat*weapon)/10)+(base/4)
        return Math.round(max_raw_damage.toFloat())
    }

    fun specialMaxDamage(stat: Float, weapon: Float, base: Float): Int {
        val max_damage = 1.5 * normalMaxRawDamage(stat, weapon, base)
        return Math.round(max_damage.toFloat())
    }
    fun specialMagicMaxDamage(stat: Float, weapon: Float, base: Float): Int {
        val min_raw_damage = 1.6 * normalMaxRawDamage(stat, weapon, base)
        return Math.round(min_raw_damage.toFloat())
    }
    //Maximos y minimos normal attack
    fun MinDamage(min_damage: Float, mob_defense: Float): Int {
        var min_damage = min_damage - mob_defense
        if (min_damage < 0F){
            min_damage = 0F
            return min_damage.toInt()
        }else{
          return min_damage.toInt()
        }
    }
    fun MaxDamage(max_damage: Float, mob_defense: Float): Int {
        var max_damage: Float = max_damage - mob_defense
        if (max_damage < 0F) {
            max_damage = 0F
            return max_damage.toInt()
        } else{
            return max_damage.toInt()
        }
    }
    //porcentaje de daño
    fun AttackPorcentageDamage(normal_attack_max_damage: Float, min_damage: Float, max_damage: Float): Int {
        var normalAttackPorcentageDamage: Float = ((normal_attack_max_damage) / (max_damage - min_damage))
        if (normalAttackPorcentageDamage>1.00){
             normalAttackPorcentageDamage = 1.0F
             return normalAttackPorcentageDamage.toInt()*100
        }
        return (normalAttackPorcentageDamage*100).toInt()
    }

/*
    fun autoCriticDamage(max_raw_damage: Int, mob_defense: Int): Double {
                                //max_raw_crit_damage
        val max_critic_damage = (max_raw_damage*1.05) - mob_defense
        return max_critic_damage
    }

    //Special Attacks Distances/Mele
    fun specialPhysicalMaxRawDamage(stat: Int, weapon: Int, base: Int){
        val max_raw_damage = 1.5*((stat*weapon/10)+(base/4))
    }

    fun specialPhysicalMaxDamage(max_raw_damage: Int, mob_defense: Int): Int {
        val max_damage = max_raw_damage - mob_defense
        return max_damage
    }

    fun specialPhysicalMinDamage(min_raw_damage: Int, mob_defense: Int): Int {
        val min_damage = min_raw_damage - mob_defense
        return min_damage
    }

    fun specialPhysicalRange(max_damage: Int, min_damage: Int): Int {
        val range = max_damage - min_damage
        return range
    }
    fun specialPhysicalautoCriticDamage(max_raw_damage: Int, mob_defense: Int): Double {
        val max_critic_damage = (max_raw_damage*1.05) - mob_defense
        return max_critic_damage
    }
    //Magic Level
    fun specialMagicMaxRawDamage(stat: Int, weapon: Int, base: Int): Double {
        val max_raw_damage = 1.5*((1.05*stat*weapon)/10)+((9*base)/32)
        return max_raw_damage
    }

    fun specialMagicMaxDamage(max_raw_damage: Int, mob_defense: Int): Int {
        val max_damage = max_raw_damage - mob_defense
        return max_damage
    }



    fun specialMagicRange(max_damage: Int, min_damage: Int): Int {
        val range = max_damage - min_damage
        return range
    }
    fun specialMagicCriticDamage(max_raw_damage: Int, mob_defense: Int): Double {
        val max_critic_damage = (max_raw_damage*1.05) - mob_defense
        return max_critic_damage
    }
    //Presicion de golpe basico
    fun normalAccuracy(max_raw_damage: Int,min_raw_damage: Int,mob_defense: Int,): Double {
        var normalAccuracy: Double = ((max_raw_damage - mob_defense)/(max_raw_damage - min_raw_damage)).toDouble()
        if (normalAccuracy > 1.00){
            normalAccuracy = 1.00
        }
        return normalAccuracy
    }
    /*
*     fun autoCriticDamage(max_raw_damage: Int, mob_defense: Int): Double {
                            //max_raw_crit_damage
    val max_critic_damage = (max_raw_damage*1.05) - mob_defense
    return max_critic_damage
}
* */
    fun normalCriticalAccuracy(autoCriticDamage: Double, max_raw_damage: Int, mob_defense: Int): Double {
        val max_raw_critical_damage = autoCriticDamage + mob_defense
        var normalCriticalAccuracy: Double = (autoCriticDamage/( max_raw_critical_damage - max_raw_damage))
        if (normalCriticalAccuracy > 1.00){
            normalCriticalAccuracy = 1.00
        }
        return normalCriticalAccuracy
    }
    fun generalAccuracyBasicAttack(normalAccuracy:Double, normalCriticalAccuracy: Double): Double {
        val generalAccuracy = (normalAccuracy * 0.99) + (normalCriticalAccuracy*0.01)
        return generalAccuracy
    }*/
}