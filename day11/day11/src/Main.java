import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Monkey{
    List<Long> items;
    Function<Long,Long> operation;
    Function<Long, Boolean> test;
    Integer destTrue;
    Integer destFalse;

    Long inspectCounter=0L;
    public Monkey(List<Long> items, Function<Long, Long> operation, Function<Long, Boolean> test, Integer destTrue, Integer destFalse) {
        this.items = items;
        this.operation = operation;
        this.test = test;
        this.destTrue=destTrue;
        this.destFalse=destFalse;
    }
}
public class Main {


    public static void main(String[] args) throws IOException {
        List<Monkey>  monkeys = new ArrayList<>();
        final Integer CMMC = 9699690;
        monkeys.add(new Monkey(new ArrayList<>(Arrays.asList(89L, 73L, 66L, 57L, 64L, 80L)), old->old%CMMC*3, y->y%13==0,6,2));
        monkeys.add(new Monkey(new ArrayList<>(Arrays.asList(83L, 78L, 81L, 55L, 81L, 59L, 69L)),old->old%CMMC+1, y->y%3==0,7,4));
        monkeys.add(new Monkey(new ArrayList<>(Arrays.asList(76L, 91L, 58L, 85L)),old->old%CMMC*13, y->y%7==0,1,4));
        monkeys.add(new Monkey(new ArrayList<>(Arrays.asList(71L, 72L, 74L, 76L, 68L)),old->old%CMMC*old, y->y%2==0,6,0));
        monkeys.add(new Monkey(new ArrayList<>(Arrays.asList(98L, 85L, 84L)),old->old%CMMC+7, y->y%19==0,5,7));
        monkeys.add(new Monkey(new ArrayList<>(Arrays.asList(78L)),old->old%CMMC+8, y->y%5==0,3,0));
        monkeys.add(new Monkey(new ArrayList<>(Arrays.asList(86L, 70L, 60L, 88L, 88L, 78L, 74L, 83L)),old->old%CMMC+4, y->y%11==0,1,2));
        monkeys.add(new Monkey(new ArrayList<>(Arrays.asList(81L, 58L)),old->old%CMMC+5, y->y%17==0,3,5));

        for(int k =0; k < 10000; k++){
            for (int i = 0; i < monkeys.size();i++){
                for(int j = 0; j < monkeys.get(i).items.size();j++){
                    Long currentItem= monkeys.get(i).items.get(j);
                    Long newItemValue = monkeys.get(i).operation.apply(currentItem);
//                    newItemValue=newItemValue/3;
                    if(monkeys.get(i).test.apply(newItemValue)){
                        monkeys.get(monkeys.get(i).destTrue).items.add(newItemValue);
                    }
                    else{
                        monkeys.get(monkeys.get(i).destFalse).items.add(newItemValue);
                    }
                    monkeys.get(i).items.remove(currentItem);
                    j--;
                    monkeys.get(i).inspectCounter++;
                }
            }
        }
        List<Long> prods = new ArrayList<>();
        for(Monkey m : monkeys){
           prods.add(m.inspectCounter);
        }
        prods.sort(Comparator.comparingLong(x->x));
        System.out.println(prods.get(prods.size()-1)*prods.get(prods.size()-2));
    }
}

//