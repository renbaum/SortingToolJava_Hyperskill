package sorting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Strategy Interface
interface OutputStrategy{

    void print(String format);
    void setFileName(String fileName);

}

// concrete Strategy Number sorting
class OutputConsole implements OutputStrategy{

    @Override
    public void print(String format) {
        System.out.print(format);
    }

    @Override
    public void setFileName(String fileName) {

    }
}

class OutputFile implements OutputStrategy{
    BufferedWriter writer;

    public void setFileName(String fileName){
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print(String format) {
        try {
            writer.write(format);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}

// context
public class OutputContext{
    public final OutputStrategy strategy;

    public OutputContext(OutputStrategy strategy){
        this.strategy = strategy;
    }

    public void print(String format) {
        strategy.print(format);
    }
}
