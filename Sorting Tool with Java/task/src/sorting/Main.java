package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        try {
            CommandLineParser parser = new CommandLineParser();
//        parser.addParameter("-sortIntegers", "longsort");
            parser.addParameter("-dataType", "word", Arrays.asList("long", "word", "line"));
            parser.addParameter("-sortingType", "natural", Arrays.asList("natural", "byCount"));
            parser.addParameter("-inputFile", "");
            parser.addParameter("-outputFile", "");

            parser.add(args);
/*        String dataType = parser.getValue("-sortIntegers");
        if(dataType == null || dataType.isEmpty()){
            dataType = parser.getValue("-dataType");
        }

 */
            String dataType = parser.getValue("-dataType");
            String sortingType = parser.getValue("-sortingType");
            String inputFile = parser.getValue("-inputFile");
            String outputFile = parser.getValue("-outputFile");
            Singleton.getInstance().setContexts(dataType, sortingType, inputFile, outputFile);

            Singleton.getInstance().sort();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
