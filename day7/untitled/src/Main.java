import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class Node {

    public static final String DIRECTORY = "d";

    public static final String FILE = "f";

    public static final Integer NOSIZE = 0;
    public String name;

    public String type;
    public Integer size;
    public List<Node> copii;

    public Node parent;

    public Node(String nodeName, Integer nodeSize, String nodeType, Node parent) {
        this.name = nodeName;
        this.type = nodeType;
        this.size = nodeSize;
        this.copii = new ArrayList<>();
        this.parent = parent;
    }

    public void addCopil(Node copil) {
        this.copii.add(copil);
    }

}


public class Main {


    public static Integer calculateSize(Node currentnode){
        for(Node child: currentnode.copii){
            currentnode.size+= calculateSize(child);
        }
        return currentnode.size;
    }

    public static Integer findDeletable(Node currentnode){
        Integer size = 0;
        if(currentnode.size>100000 && currentnode.copii.size() == 0){
            System.out.println("zero pt nod " + currentnode.name );
            return 0;
        }
        if(currentnode.size<=100000){
            System.out.println("size pt nod " + currentnode.name+ " cu size" + currentnode.size);
        }
        if(currentnode.type.equals(Node.DIRECTORY) && currentnode.size<=100000){
            size+=currentnode.size;
        }

        for(Node child:currentnode.copii){
             size+=findDeletable(child);
        }
        return size;
    }

    public static void findTarget(Node currentnode, Integer currentSpace,List<Node> targetList){
        if(currentnode.type.equals(Node.DIRECTORY) && currentSpace+currentnode.size >= 30000000){
            targetList.add(currentnode);
        }
        for(Node child: currentnode.copii){
            findTarget(child,currentSpace,targetList);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("input.txt")));
        Node root = new Node("/", Node.NOSIZE,  Node.DIRECTORY, null);
        String currentCommand = "";
        Node currentDirectory = root;
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            if (line.charAt(0) == '$') {
                currentCommand = line.split(" ")[1];
            }
            if (currentCommand.equals("cd")) {
                String destination = line.split(" ")[2];
                if (destination.equals("..")) {
                    currentDirectory = currentDirectory.parent;
                } else if (!destination.equals("/")) {
                    currentDirectory = currentDirectory.copii.stream().filter(x -> x.name.equals(destination)).findFirst().get();
                } else {
                    currentDirectory = root;
                }
            } else if (currentCommand.equals("ls") && line.charAt(0) =='$') {
                continue;
            }
            if(line.charAt(0) !='$' && currentCommand.equals("ls")){
                String attribute = line.split(" ")[0];
                String name = line.split(" ")[1];
                if (attribute.equals("dir"))
                    currentDirectory.addCopil(new Node(name,  Node.NOSIZE,  Node.DIRECTORY, currentDirectory));
                else
                    currentDirectory.addCopil(new Node(name, Integer.valueOf(attribute),  Node.FILE, currentDirectory));
            }
        }
        root.size=calculateSize(root);
        Integer currentSpace = 70000000 - root.size;
        List<Node> targetList = new ArrayList<>();
        findTarget(root,currentSpace,targetList);
        System.out.println(targetList.stream().min(Comparator.comparing(x->x.size)).get().size);
    }
}
