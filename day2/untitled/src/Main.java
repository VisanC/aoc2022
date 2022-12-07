import java.io.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
        Map<String, String> beats=Map.of("A","B","B","C","C","A");
        Map<String,String> losesTo=Map.of("A","C","B","A","C","B");
        Integer total = 0;
        while (reader.ready()){
            String line = reader.readLine();
            String first = line.split(" ")[0];
            String second = line.split(" ")[1];
            total += (second.toCharArray()[0]-88) * 3;
            total += second.equals("X") ? (losesTo.get(first).toCharArray()[0] - 64) : second.equals("Y")
                    ? (first.toCharArray()[0]-64) : (beats.get(first).toCharArray()[0] -64);
        }
        System.out.println(total);
    }
}

/*
 A loses to B
 B loses to C
 C loses to A

 A beats C
 B beats A
 C beats B
 */