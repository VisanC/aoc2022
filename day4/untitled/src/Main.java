import java.io.*;

public class Main {

    public static int cond(int a1, int a2, int b1, int b2){
        return (a2-b2 >=0) && (a1-b1<=0) ? 1 : (b2-a2>=0) && (b1-a1 <=0) ? 1 : (a1-b2<=0) && (a1-b2<=0) && (b1<=a2)
                ? 1 : (a1-b1>=0) && (a2-b2>=0) && (b2>=a1) ? 1: 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
        Integer counter = 0;
        while(reader.ready()){
            String line = reader.readLine();
            Integer a1 = Integer.valueOf(line.split("-")[0]);
            Integer a2 = Integer.valueOf(line.split(",")[0].split("-")[1]);
            Integer b1 = Integer.valueOf(line.split(",")[1].split("-")[0]);
            Integer b2 = Integer.valueOf(line.split(",")[1].split("-")[1]);
            counter+= cond(a1,a2,b1,b2);

        }
        System.out.println(counter);
    }
}