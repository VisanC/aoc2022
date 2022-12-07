import java.io.*;
import java.util.*;

public class Main {

    public static List<Stack<Character>> parseStuff( String nums, List<String> towers){
        Optional<Integer> numTowersOptional= Arrays.stream(nums.split(" ")).filter(x-> x.length()>0).map(x->Integer.valueOf(x)).max(Integer::compareTo);
        if(numTowersOptional.isEmpty()){
            return null;
        }
        Integer numTowers = numTowersOptional.get();
        List<Stack<Character>> actualTowers = new ArrayList<>();
        for(int i = 0; i < numTowers; i++ ){
            actualTowers.add(i,new Stack<>());
        }
        List<String> parsedTowers = new ArrayList<>();
        for(String tower : towers){
            StringBuilder sb = new StringBuilder();
            for(int i =0; i< tower.length();i++){
                if(i>=1 && i< tower.length()-1){
                    if(Character.isWhitespace(tower.charAt(i-1)) && Character.isWhitespace(tower.charAt(i+1))
                            && sb.toString().charAt(sb.toString().length()-1) != '&' ){
                        sb.append("&");
                        continue;
                    }
                }
                sb.append(tower.charAt(i));

            }
            parsedTowers.add(sb.toString());
        }
        List<String> actuallyParsedTowers = new ArrayList<>();
        for(String s: parsedTowers){
            actuallyParsedTowers.add(s.replace("& &","[&]"));
        }
        List<String> actuallyParsedTowers2 = new ArrayList<>();
        for(String s: actuallyParsedTowers){
            actuallyParsedTowers2.add(s.replace("& ","[&]"));
        }

        towers=actuallyParsedTowers2;

        for(int i = towers.size()-1; i >=0; i--){
            String currentTower = towers.get(i).replace('[',' ').replace(']',' ').replaceAll(" ","");
            for(int j = 0; j < currentTower.length();j++){
                if(Character.isAlphabetic(currentTower.charAt(j))){
                    actualTowers.get(j).push(currentTower.charAt(j));
                }
            }
        }
        return actualTowers;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
        List<String> towers = new ArrayList<>();
        String nums="";
        while(reader.ready()){
            String line = reader.readLine();
            if(line.contains("1")){
                nums=line;
                break;
            }
            else{
                towers.add(line);
            }
        }
        List<Stack<Character>> parsedTowers = parseStuff(nums,towers);
        while(reader.ready()){
            String command =reader.readLine();
            if(!command.contains("move")){
                continue;
            }
            command=command.replaceAll("move","").replaceAll("from","")
                    .replaceAll("to","").strip();
            Integer count = Integer.valueOf(command.split(" ")[0]);
            Integer from = Integer.valueOf(command.split(" ")[2])-1;
            Integer to = Integer.valueOf(command.split(" ")[4])-1;
            if(count == 1){
                Character tmp = parsedTowers.get(from).pop();
                parsedTowers.get(to).push(tmp);
            }
            else{
                List<Character> tmp = new ArrayList<>();
                for(int i =0; i<count; i++) {
                    tmp.add(parsedTowers.get(from).pop());
                }
                for(int i = tmp.size()-1;i>=0;i--){
                    parsedTowers.get(to).push(tmp.get(i));
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        for(Stack<Character> s : parsedTowers){
            sb.append(s.pop());
        }
        System.out.println(sb.toString());

    }
}