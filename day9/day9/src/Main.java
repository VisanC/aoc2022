import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static Integer MAX_SIZE = 32768;
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));

        Set<String> positions = new HashSet<>();
        String currentHeadPosition = "0 0";
        String currentTailPosition = currentHeadPosition;
        positions.add(currentHeadPosition);
        while (br.ready()){
            String line = br.readLine();
            String command = line.split(" ")[0];
            int xdif=0;
            int ydif=0;
            if(command.equals("U")){
                xdif=0;
                ydif=1;
            }
            if(command.equals("D")){
                xdif=0;
                ydif=-1;
            }
            if(command.equals("L")){
                xdif=-1;
                ydif=0;
            }
            if(command.equals("R")){
                xdif=1;
                ydif=0;
            }
            Integer distance = Integer.valueOf(line.split(" ")[1]);
            for(int i = 0; i < distance;i++){
                int currentXHead= Integer.valueOf(currentHeadPosition.split(" ")[0]) +xdif;
                int currentYHead= Integer.valueOf(currentHeadPosition.split(" ")[1]) +ydif;
                currentHeadPosition=currentXHead+" "+currentYHead;
                int currentXTail = Integer.valueOf(currentTailPosition.split(" ")[0]);
                int currentYTail = Integer.valueOf(currentTailPosition.split(" ")[1]);

                if(Math.abs(currentXHead - currentXTail) > 1 || (Math.abs(currentYHead-currentYTail) >1 )){
                    currentYTail+=Integer.signum(currentYHead-currentYTail);
                    currentXTail+=Integer.signum(currentXHead - currentXTail);
                }
                currentTailPosition=currentXTail+" "+currentYTail;
                positions.add(currentTailPosition);
                System.out.println(currentTailPosition);
            }

        }
        System.out.println(positions.size());
    }
}