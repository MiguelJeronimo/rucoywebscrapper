package API.Tibia.models

data class Weapons(
    val bows: ArrayList<Weapon>,
    val crossBows: ArrayList<Weapon>,
    val arrows: ArrayList<Ammunition>,
    val bolts: ArrayList<Ammunition>,
    val throwing: ArrayList<Throwing>
)
/**
 * Name: Yol's Bow
 * Imagen: https://static.wikia.nocookie.net/tibia/images/1/19/Yol%27s_Bow.gif/revision/latest?cb=20080624232723&path-prefix=en
 * Lvl: 60
 * Range: 7
 * Atk Mod:
 * Hit%: +7
 * Resist:
 * Imb. Slots: 0
 * Class: 4
 * Attributes:
 * Weight: 35.00
 *
 * **/
data class Weapon(
    val name: String? = null,
    val image: String? = null,
    val level:String? = null,
    val range: String? = null,
    val atkMode: String? = null,
    val hit: String? = null,
    val resist:String? = null,
    val embuimentSlots: String? = null,
    val classs:String? = null,
    val attributes:String? = null,
    val weight: String? = null
)

data class Ammunition(
    val name: String? = null,
    val image: String? = null,
    val level:String? = null,
    val atk: String? = null,
    val weight: String? = null,
    val npcPrice: String? = null
)

data class Throwing(
    val name: String? = null,
    val image: String? = null,
    val level:String? = null,
    val atk: String? = null,
    val range:String? =null,
    val weight: String? = null,
    val npcPrice: String? = null
)

data class Catalog(
    val name: String? = null,
    val image: String? = null,
)

//Vocations
data class Vocations(
    val name: String,
    val description: String,
)
data class Vocation(
    var description: String? = null,
    var spells: ArrayList<Spell> ? = null,
    var spellsIsNotAvaible: ArrayList<Spell>? = null,
    var runeSpells: ArrayList<RuneSpell>? = null
)

data class Spell(
    val name: String? = null,
    val image: String? = null,
    val words: String? = null,
    val premium: String? = null,
    val level: String? = null,
    val mana: String? = null,
    val price: String? = null,
    val group: String? = null,
    val effect: String? = null,
)

data class RuneSpell(
    val name: String? = null,
    val image: String? = null,
    val words: String? = null,
    val premium: String? = null,
    val level: String? = null,
    val mana: String? = null,
    val price: String? = null,
    val group: String? = null,
    val effect: String? = null
)