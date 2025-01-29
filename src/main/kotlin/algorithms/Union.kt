package algorithms

class Union(private var union: ArrayList<Int> = arrayListOf()) {
    private var i = 0
    //Arreglo donde se uniran los elementos de los diferentes arrays
    fun diferencesAsimetric(vararg items: Array<Int>): ArrayList<Int> {
        ///limpiamos el arreglo
        union.clear()
        //iteramos los arreglos de params y guardamos todos los elementos del arreglo en uno solo
        items.forEach {
            i++
            println("ARRAY $i  ${it.toList()}")
            union.addAll(it)
        }
        //ordenamos la lista de menor a mayor
        val sortedList = union.sorted()
        println("Array ORdenado: $sortedList")
        //convertimos la lista en un arraylist
        union = sortedList.toList() as ArrayList<Int>
        for (index in sortedList.indices) {
            //omitimos la posicion cero
            if (index > 0) {
                //validamos el elemento anterior con el actual
                if (sortedList[index - 1] == sortedList[index]) {
                    //removemos todos los elementos del arreglo donde hagan march con el elemento actual
                    union.removeAll { it == sortedList[index] }
                }
            }
        }
        return union
    }
}

