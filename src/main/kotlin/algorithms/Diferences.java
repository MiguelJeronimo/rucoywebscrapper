package algorithms;
import java.util.*;
import java.util.ArrayList;

public class Diferences {
    private final ArrayList<Integer> union = new ArrayList<>();

    public final ArrayList<Integer> diferencesAsimetric(ArrayList<Integer>... items){
        union.clear();
        int count = 0;
        System.out.println(items.length);
        for (ArrayList<Integer>item: items){
            count++;
            System.out.println("ARRAY: "+count+" "+item.toString());
            union.addAll(item);

        }
        //ordenamos el array
        Collections.sort(union);
        System.out.println("Array ordenado: "+union);
        ArrayList<Integer> arrayRemove = new ArrayList<>();
        for (int i = 0; i<union.size(); i++){
            if (i>0){
                if (union.get(i - 1) == union.get(i)){
                    //guardamos los elementos que se repiten
                    arrayRemove.add(union.get(i));
                }
            }
        }
        //eliminamos los elementos repetidos del arreglo original
        union.removeAll(arrayRemove);
        return union;
    }
}
