package examples.chaining.pojo;

import java.util.Arrays;
import java.util.List;

public class ChainingExample {

    private String value;
    private List valueArr;

    public ChainingExample() {
        value = "";
    }

    public  ChainingExample addText(String text){
        value += text;
        return this;
    }

    public  ChainingExample addSpace(){
        value += " ";
        return this;
    }

    public  List toArray(){
        valueArr = Arrays.asList(value.split(" "));
        return valueArr;
    }

    public  ChainingExample toUpperCase(){
        value = value.toUpperCase();
        return this;
    }

    @Override
    public String toString() {
        return value;
    }

}
