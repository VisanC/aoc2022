import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
            int total = 0;
            while(reader.ready()){
                String rucksack = reader.readLine();
                String rucksack2 = reader.readLine();
                String rucksack3 = reader.readLine();
//                Character c =rucksack.chars().filter(x-> rucksack.indexOf(x) <= rucksack.length()/2 && rucksack.lastIndexOf(x) >= rucksack.length()/2)
//                        .mapToObj(e->(char)e).collect(Collectors.toList()).get(0);
                Character c = rucksack.chars().filter(x-> rucksack2.contains((char)x+"") && rucksack3.contains((char)x+""))
                        .mapToObj(e->(char)e).collect(Collectors.toList()).get(0);
                int value = c.charValue() > 96 ? c.charValue() - 96 : c.charValue() - 38;
                System.out.println(c + "     " + value);
                total+= value;
        }
        System.out.println(total);
    }
}