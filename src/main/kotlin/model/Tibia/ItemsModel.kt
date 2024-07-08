package model.Tibia


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

