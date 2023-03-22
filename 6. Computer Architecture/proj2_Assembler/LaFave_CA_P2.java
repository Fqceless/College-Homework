package CA_P2;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class LaFave_CA_P2 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //-p flag checker
        boolean printToConsole = false;
        
        //makes sure it has the right args
        //if no input, it prints a readme
        if(args.length < 1 || args[0].equals("-help")){
            ReadMe();
            System.exit(0);
        }
        else if(args[1].equals("-p")){
            printToConsole = true;
        }
        else if(args.length > 1){
            if(args[1].equals("-p")){
                printToConsole = true;
            }
            else{
                System.out.println("Please enter only one file name and valid flags");
                System.exit(0);
            }
        }
        
        //open file
        File testFile = new File(args[0]);
        Scanner read = new Scanner(testFile);
        
        //write to pass1
        File passOneFile = new File("pass1.txt");
        FileWriter outOne = new FileWriter(passOneFile);
        
        //write to pass2
        File passTwoFile = new File("pass2.txt");
        FileWriter outTwo = new FileWriter(passTwoFile);
        
        //Get beginning memory value
        //cnt counts *from* the ORG (I add them together to get what I need)
        String line = read.nextLine();
        final String progTop = line.substring(9, 12);
        String tempTop = progTop;
        int cnt = 0;
        
        /* Complete First Pass*/
        if(printToConsole){System.out.println("\nFirst Pass:");}
        
        //labels is a key-value pair holder for all the Labels in the program
        //lines is a key-value pair holder for every line in the program
        HashMap<String, String> labels = new HashMap();
        HashMap<String, String> lines = new HashMap();
        String lineChunks[];
        while(read.hasNext()){
            line = read.nextLine();
            //split each line into [cmd], [ads], [I?]
            lineChunks = line.substring(5).split(" ");
            
            //handles changing the top of the code chunk
            //cnt is set to -1 because the end of this loop increments it
            if(lineChunks[0].equals("ORG")){
                tempTop = lineChunks[1];
                cnt = -1;
            }
            /*Separates lines into "[LineNum], [Cmd Mem]" pairs*/
            //line only has a command, ex: "HLT"
            else if(lineChunks.length == 1 || (lineChunks.length > 1 && lineChunks[1].length() == 0)){
                lines.put(hexAddInt(tempTop, cnt), lineChunks[0] + " ---");
            }
            //line has a command and an address, ex: "LDA ADS"
            else if(lineChunks.length == 2 || (lineChunks.length > 2 && lineChunks[2].length() == 0)){
                lines.put(hexAddInt(tempTop, cnt), lineChunks[0] + " " + lineChunks[1]);
            }
            //line has command, address, and is indirect, ex: "ADD PTR I"
            else if(lineChunks[2].equals("I")){
                lines.put(hexAddInt(tempTop, cnt), lineChunks[0] + lineChunks[2] + " " + lineChunks[1]);
            }
            
            //checks whether or not there is a label
            //stores labels as [Label], [LineNum] and prints it out.
            if(!line.substring(0, 3).equals("   ")){
                labels.put(line.substring(0, 3), hexAddInt(tempTop, cnt));
                outOne.write(line.substring(0, 3) + ", " + hexAddInt(tempTop, cnt) + "\n");
                if(printToConsole){System.out.println(line.substring(0, 3) + ", " + hexAddInt(tempTop, cnt));}
            }
            cnt++;
        }
        //this causes the write to complete
        outOne.close();
        
        /*Complete Second Pass*/
        if(printToConsole){System.out.println("\nSecond Pass:");}
        //i refrences the memory address of the next line
        //cnt counts how many lines its gone through
        String i = progTop;
        for(cnt = 0; cnt < lines.size() - 1; cnt++, i = hexAddInt(i, 1)){
            //if i doesn't refer to anything in the hashmap, just keep going
            while(lines.get(i) == null){
                i = hexAddInt(i, 1);
            }
            
            //grabs the command and address and writes them
            if(!lines.get(i).substring(0, 3).equals("END")){
                outTwo.write(i + ": " + cmdToHex(lines.get(i), labels) + "\n");
                if(printToConsole){System.out.println(i + ": " + cmdToHex(lines.get(i), labels));}
            }
        }
        //this causes the write to complete
        outTwo.close();
    }
    
    //uses java magic to add an int to a hex string
    //also makes sure it is 3 digits long
    private static String hexAddInt(String hex, int dec){
        hex = (Integer.toHexString(Integer.parseInt(hex, 16) + dec)).toUpperCase();
        while(hex.length() < 3){hex = "0" + hex;}
        return hex;
    }
    
    //big swtich statement to convert to correct 
    private static String cmdToHex(String cmd, HashMap<String, String> labels){
        //separates into [cmd], [ads]
        String cmdChunks[] = cmd.split(" ");
        String mem;
        //if this throws an error, it's a label and not a number
        try{
            mem = Integer.toHexString(Integer.parseInt(cmdChunks[1], 16)).toUpperCase();
        }
        catch(NumberFormatException ex){
            mem = labels.get(cmdChunks[1]);
        }
        
        //cmd + ads -> HEX
        switch(cmdChunks[0]){
            case "AND" ->  {return "0" + mem;}
            case "ADD" ->  {return "1" + mem;}
            case "LDA" ->  {return "2" + mem;}
            case "STA" ->  {return "3" + mem;}
            case "BUN" ->  {return "4" + mem;}
            case "BSA" ->  {return "5" + mem;}
            case "ISZ" ->  {return "6" + mem;}
            case "ANDI" -> {return "8" + mem;}
            case "ADDI" -> {return "9" + mem;}
            case "LDAI" -> {return "A" + mem;}
            case "STAI" -> {return "B" + mem;}
            case "BUNI" -> {return "C" + mem;}
            case "BSAI" -> {return "D" + mem;}
            case "ISZI" -> {return "E" + mem;}
            case "CLA" ->  {return "7800";}
            case "CLE" ->  {return "7400";}
            case "CMA" ->  {return "7200";}
            case "CME" ->  {return "7100";}
            case "CIR" ->  {return "7080";}
            case "CIL" ->  {return "7040";}
            case "INC" ->  {return "7020";}
            case "SPA" ->  {return "7010";}
            case "SNA" ->  {return "7008";}
            case "SZA" ->  {return "7004";}
            case "SZE" ->  {return "7002";}
            case "HLT" ->  {return "7001";}
            case "INP" ->  {return "F800";}
            case "OUT" ->  {return "F400";}
            case "SKI" ->  {return "F200";}
            case "SKO" ->  {return "F100";}
            case "ION" ->  {return "F080";}
            case "IOF" ->  {return "F040";}
            case "HEX" ->  {while(mem.length() < 4){mem = "0" + mem;}return mem;}
            case "DEC" ->  {
                //converts to hex
                mem = Integer.toHexString(Integer.parseInt(cmdChunks[1])).toUpperCase();
                while(mem.length() < 4){mem = "0" + mem;}
                //here because negative numbers are 8 chars long
                if(mem.length() > 4){mem = mem.substring(mem.length() - 4);}
                return mem;
            }
            default ->     {return "MISS";}
        }
    }
    
    //read me
    private static void ReadMe(){
        System.out.println("\n***READ ME***");
        System.out.println("Author: Chris LaFave");
        System.out.println("All you need to do is type your test-file name after this .java file");
        System.out.println("This program will create two files, pass1.txt and pass2.txt, in this current directory");
        System.out.println("You can also add the \"-p\" flag to output directly to the console");
    }
}
