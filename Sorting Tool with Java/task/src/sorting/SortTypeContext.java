package sorting;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Strategy Interface
interface SortTypeStrategy{

    void showElementsByString(List<String> list, boolean CR);
    void showElementsByInteger(List<String> list, boolean CR);

}

// concrete Strategy Number sorting
class SortByNatural implements SortTypeStrategy{
    @Override
    public void showElementsByString(List<String> list, boolean CR) {
        Singleton.getInstance().output.print("Sorted data:");
        for (String s : list) {
            if(CR) {
                Singleton.getInstance().output.print(String.format("\n%s", s));
            }else{
                Singleton.getInstance().output.print(" " + s);
            }
        }
        Singleton.getInstance().output.print("\n");
    }

    @Override
    public void showElementsByInteger(List<String> list, boolean CR) {
        showElementsByString(list, CR);
    }
}

class SortByCount implements SortTypeStrategy{

    @Override
    public void showElementsByString(List<String> list, boolean CR) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            if(map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            }else{
                map.put(s, 1);
            }
        }
        List<Map.Entry<String, Integer>> sortedEntries = map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            Singleton.getInstance().output.print(String.format("%s: %d time(s), %d%%\n",
                    entry.getKey(),
                    entry.getValue(),
                    Math.round(entry.getValue() / (double)list.size()*100)));
        }
    }

    @Override
    public void showElementsByInteger(List<String> list, boolean CR) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String s : list) {
            int num = Integer.parseInt(s);
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }else{
                map.put(num, 1);
            }
        }
        List<Map.Entry<Integer, Integer>> sortedEntries = map.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue()
                .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());

        for (Map.Entry<Integer, Integer> entry : sortedEntries) {
            Singleton.getInstance().output.print(String.format("%s: %d time(s), %d%%\n",
                    entry.getKey(),
                    entry.getValue(),
                    Math.round(entry.getValue() / (double)list.size()*100)));
        }

    }
}

public class SortTypeContext{
    private final SortTypeStrategy strategy;


    public SortTypeContext(SortTypeStrategy sortTypeStrategy){
        this.strategy = sortTypeStrategy;
    }

    public void showElementsInteger(List<String> list, boolean CR){
        strategy.showElementsByInteger(list, CR);
    }

    public void showElementsString(List<String> list, boolean CR){
        strategy.showElementsByString(list, CR);
    }
}
