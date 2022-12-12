import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void draw(int currentCycle,int register){
        currentCycle=currentCycle%40;
        if (currentCycle == register || currentCycle == register - 1 || currentCycle == register + 1) {
            System.out.print("#");
        } else {
        System.out.print(".");
        }
        if((currentCycle%40)==39){
            System.out.print("\n");
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        Integer register = 1;
        Integer currentCycle = 0;
        List<Integer> strenghts = new ArrayList<>();
        strenghts.add(0);
        while (br.ready()) {
            String line = br.readLine();
            String instruction = line.split(" ")[0];
            if (instruction.equals("addx")) {
                Integer value = Integer.valueOf(line.split(" ")[1]);
                draw(currentCycle,register);
                currentCycle += 1;
                strenghts.add(currentCycle * register);
                draw(currentCycle,register);
                currentCycle += 1;
                strenghts.add(currentCycle * register);

                register += value;

            } else {
                draw(currentCycle,register);
                currentCycle += 1;
                strenghts.add(currentCycle * register);


            }

        }
        //System.out.println(strenghts.get(20) + strenghts.get(60) + strenghts.get(100) + strenghts.get(140)+ strenghts.get(180) + strenghts.get(220));
    }
}