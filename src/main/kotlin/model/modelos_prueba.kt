package model

data class Mob(
    val name: String,
    val defense: Int,
    val health: Int
)

data class NormalTrain(val creaturename: String, val MIN_ATTACK: Int, val MAX_ATTACK: Int, val porcentage_damage: Int)

data class Train(
    var MIN_ATTACK: Int?,
    var MAX_ATTACK: Int?,
    var tickrate: String?,
    var train_effective: ArrayList<Sprite>?,
    var deal: Deal?,
    var need:Need?
)
data class Deal(
    var nameCreature: String?,
    var MAX_ATTACK: Int?,
)
data class Need(
    var nameCreature: String?,
    var stats: Int?,
)

data class Sprite(
    var nameCreature: String?,
    //val url: String,
    var average_time_to_kill: String?
)