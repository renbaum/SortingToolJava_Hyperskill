package sorting;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Strategy Interface
interface InputStrategy{

    public List<Object> readInput();
    void setFileName(String filePath);
}

abstract class InputConsole implements InputStrategy{
    Scanner scanner;

    public InputConsole(){
        scanner = new Scanner(System.in);
    }

    public void setFileName(String filePath){

    }
}

abstract class InputFile implements InputStrategy{
    String filePath;

    public void setFileName(String filePath){
        this.filePath = filePath;
    }

    List<String> readLines(){
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of(filePath));
        }catch(IOException e){
            e.printStackTrace();
        }
        return lines;
    }

}

// concrete Strategy Number sorting
class InputConsoleLong extends InputConsole{

    public InputConsoleLong(){
        super();
    }

    @Override
    public List<Object> readInput() {
        List<Object> list = new ArrayList<Object>();
        while(scanner.hasNext()){
            String str = scanner.nextLine();
            String[] tokens = str.split(" ");
            for(String token : tokens){
                try{
                    if(token.isEmpty()) continue;
                    list.add((long) Long.parseLong(token));
                }catch(NumberFormatException e){
                    System.out.printf("\"%s\" is not a long. It will be skipped.\n", token);
                }
            }
        }
        return list;
    }
}

class InputConsoleLine extends InputConsole{
    public InputConsoleLine(){
        super();
    }

    @Override
    public List<Object> readInput() {
        List<Object> list = new ArrayList<Object>();
        while(scanner.hasNextLine()){
            String str = scanner.nextLine();
            list.add((String) str);
        }
        return list;
    }
}

class InputConsoleWord extends InputConsole{
    public InputConsoleWord(){
        super();
    }

    @Override
    public List<Object> readInput() {
        List<Object> list = new ArrayList<Object>();
        while(scanner.hasNextLine()){
            String str = scanner.nextLine();
            String[] words = str.split(" ");
            for(String word : words){
                word = word.trim();
                if(word.length() > 0){
                    list.add(word);
                }
            }
        }
        return list;
    }
}

class InputFileLong extends InputFile{

    @Override
    public List<Object> readInput() {
        List<String> lines = readLines();
        List<Object> list = new ArrayList<Object>();

        for(String line : lines){
            String[] tokens = line.split(" ");
            for(String token : tokens){
                try{
                    if(token.isEmpty()) continue;
                    list.add((long) Long.parseLong(token));
                }catch(NumberFormatException e){
                    System.out.printf("\"%s\" is not a long. It will be skipped.\n", token);
                }
            }
        }
        return list;
    }
}

class InputFileLine extends InputFile{

    @Override
    public List<Object> readInput() {
        List<String> list = readLines();
        return Collections.singletonList(list);
    }
}

class InputFileWord extends InputFile{

    @Override
    public List<Object> readInput() {
        List<String> list = readLines();

        List<Object> obList = new ArrayList<Object>();
        for(String line : list){
            String[] words = line.split(" ");
            for(String word : words){
                word = word.trim();
                if(word.length() > 0) {
                    obList.add(word);
                }
            }
        }
        return obList;
    }
}

public class InputContext{

    public final InputStrategy strategy;

    public InputContext(InputStrategy strategy){
        this.strategy = strategy;
    }

    public List<Object> getInput() {
        return strategy.readInput();
    }
}
