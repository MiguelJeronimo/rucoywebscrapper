package model.Tibia.items

import Tibia.Items.Items


data class ItemsCatalog(
    var body_equipment: Information? = null,
    var weapons: Information? = null,
    var household_items: Information? = null,
    var others: Information? = null,
    var tools_equipment: Information? = null,
    var other_items: Information? = null
)

data class Information(
    val title: String? = null,
    val array: ArrayList<Data>? = null
)

data class Data(
    val name: String? = null,
    val img: String? = null
)

data class BodyEquipment(
    var items: ArrayList<Item>? = null
)

data class Item(
    var name: String? = null,
    var img: String? = null,
    var arm: String? = null,
    var weight: String? = null,
    var attributes: String? = null,
    var resist: String? = null,
    var slots: String? = null,
    var classs: String? = null,
    var level: String? = null,
    var vocation: String? = null
)

