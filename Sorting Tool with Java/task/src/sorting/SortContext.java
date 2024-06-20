package sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Strategy Interface
interface SortingStrategy{
    void sort(InputContext inputContext);

    void output(OutputContext outputContext);

    List<String> createStringList();
}

// concrete Strategy Number sorting
class SortingNumbers implements SortingStrategy{
    List<Long> inputNumbers;

    @Override
    public void sort(InputContext inputContext) {
        inputNumbers = inputContext.getInput().stream()
                .filter(obj -> obj instanceof Long)
                .map(obj -> (Long) obj)
                .collect(Collectors.toList());

        Collections.sort(inputNumbers);
    }

    @Override
    public void output(OutputContext outputContext) {
        outputContext.print(String.format("Total numbers: %d.\n", inputNumbers.size()));
        Singleton.getInstance().sortType.showElementsInteger(createStringList(), false);
    }

    @Override
    public List<String> createStringList() {
        return inputNumbers.stream().map(String::valueOf).collect(Collectors.toList());
    }
}

class SortingLines implements SortingStrategy{
    List<String> inputString;

    @Override
    public void sort(InputContext inputContext) {
        inputString = inputContext.getInput().stream()
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                .collect(Collectors.toList());

        Collections.sort(inputString);
        //inputString.sort(Comparator.comparing(String::length));
    }

    @Override
    public void output(OutputContext outputContext) {
        outputContext.print(String.format("Total lines: %d.\n", inputString.size()));
        Singleton.getInstance().sortType.showElementsString(createStringList(), true);
    }

    @Override
    public List<String> createStringList() {
        return inputString;
    }
}

class SortingWords implements SortingStrategy{
    List<String> inputString;

    @Override
    public void sort(InputContext inputContext) {
        inputString = inputContext.getInput().stream()
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                .collect(Collectors.toList());

        Collections.sort(inputString);
        // inputString.sort(Comparator.comparing(String::length));
    }

    @Override
    public void output(OutputContext outputContext) {
        outputContext.print(String.format("Total words: %d.\n", inputString.size()));
        Singleton.getInstance().sortType.showElementsString(createStringList(), false);
    }

    @Override
    public List<String> createStringList() {
        return inputString;
    }

}

public class SortContext {
    private final SortingStrategy sortingStrategy;
    private InputContext inputContext;
    private OutputContext outputContext;

    public SortContext(SortingStrategy sortingStrategy){
        this.sortingStrategy = sortingStrategy;
    }

    public void setInputContext(InputContext inputContext){
        this.inputContext = inputContext;
    }

    public void setOutputContext(OutputContext outputContext){
        this.outputContext = outputContext;
    }

    public void sort() {
        sortingStrategy.sort(inputContext);
        sortingStrategy.output(outputContext);
    }
}
