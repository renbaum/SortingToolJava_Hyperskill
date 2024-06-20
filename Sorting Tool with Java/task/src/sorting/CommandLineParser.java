package sorting;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class StructParameter{
    String defaultValue = "";
    List<String> possibleValues = new ArrayList<>();

    public StructParameter(String defaultValue, List<String> possibleValues){
        this.defaultValue = defaultValue;
        this.possibleValues = possibleValues;
    }
}

public class CommandLineParser{
    private HashMap<String, String> parameters = new HashMap<>();
    private HashMap<String, StructParameter> defaults = new HashMap<>();

    public void addParameter(String key, String defaultValue){
        defaults.put(key, new StructParameter(defaultValue, null));
    }

    public void addParameter(String key, String defaultValue, List<String> possibleValues){
        defaults.put(key, new StructParameter(defaultValue, possibleValues));
        parameters.put(key, defaultValue);
    }

    public void add(String key, String value){
        parameters.put(key, checkValue(key, value));
    }

    public void add(String[] args){
        for (int i = 0; i < args.length; i += 2) {
            if(i+1 >= args.length){
                add(args[i], "");
                checkError(args[i]);
                return;
            }
            if(args[i].charAt(0) == '-' && args[i+1].charAt(0) == '-'){
                add(args[i], "");
                checkError(args[i]);
                return;
            }
            add(args[i], args[i + 1]);
        }
    }

    private void checkError(String argument){
        switch(argument){
            case "-sortingType":
                throw new IllegalArgumentException("No sorting type defined!");
            case "-dataType":
                throw new IllegalArgumentException("No data type defined!");
            default:
                throw new IllegalArgumentException(String.format("\"%s\" is not a valid parameter. It will be skipped.",
                        argument));
        }
    }

    private String checkValue(String key, String value){
        StructParameter param = defaults.get(key);
        if(param == null) return value;
        if(param.possibleValues != null){
            if(param.possibleValues.contains(value)) return value;
            return param.defaultValue;
        }
        if(value.isEmpty()) return param.defaultValue;
        return value;
    }

    public String getValue(String key){
        return parameters.get(key);
    }
}
