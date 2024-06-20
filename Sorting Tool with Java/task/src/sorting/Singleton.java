package sorting;

public class Singleton{
    public static Singleton instance;
    public InputContext input;
    public SortContext sort;
    public OutputContext output;
    public SortTypeContext sortType;

    private Singleton() {
        instance = this;
    }

    void setContexts(String dataType, String sortingType, String inputFile, String outputFile){
        switch(dataType){
            case "long":
                sort = new SortContext(new SortingNumbers());
                input = new InputContext(new InputConsoleLong());
                break;
            case "line":
                sort = new SortContext(new SortingLines());
                input = new InputContext(new InputConsoleLine());
                break;
            case "word":
                sort = new SortContext(new SortingWords());
                input = new InputContext(new InputConsoleWord());
                break;
        }
        if (inputFile != null){
            switch(dataType){
                case "long":
                    input = new InputContext(new InputFileLong());
                    break;
                case "line":
                    input = new InputContext(new InputFileLine());
                    break;
                case "word":
                    input = new InputContext(new InputFileWord());
                    break;
            }
            input.strategy.setFileName(inputFile);
        }

        if(inputFile != null){
            output = new OutputContext(new OutputFile());
        } else {
            output = new OutputContext(new OutputConsole());
        }
        output.strategy.setFileName(outputFile);

        switch(sortingType){
            case "natural":
                sortType = new SortTypeContext(new SortByNatural());
                break;
            case "byCount":
                sortType = new SortTypeContext(new SortByCount());
                break;
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void sort(){
        sort.setInputContext(input);
        sort.setOutputContext(output);
        sort.sort();
    }
}
