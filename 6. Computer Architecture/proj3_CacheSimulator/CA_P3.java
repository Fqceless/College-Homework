package ca_p3;

import java.util.Scanner;

public class CA_P3 {

    public static void main(String[] args) {
        String mem[] = new String[]{
            "92", "70", "8C", "FD", "B9", "E2", "40", "C2",
            "0D", "9A", "D1", "F8", "43", "7E", "B7", "75",
            "FB", "44", "DD", "F6", "A6", "43", "11", "17",
            "98", "88", "08", "6A", "6D", "B8", "BC", "12",
            "0A", "F1", "4C", "45", "63", "2C", "40", "98",
            "91", "65", "0E", "76", "EE", "5D", "18", "29",
            "85", "13", "60", "C5", "56", "F2", "89", "9E",
            "06", "E2", "0B", "A2", "B2", "41", "B1", "7B"
        };
        
        String cache[][] = new String[][]{
            {"N/A", "N/A", "N/A", "N/A"},
            {"N/A", "N/A", "N/A", "N/A"},
            {"N/A", "N/A", "N/A", "N/A"},
            {"N/A", "N/A", "N/A", "N/A"}
        };
        
        boolean toggles[] = new boolean[]{
            true, true, true, true
        };
        
        Scanner read = new Scanner(System.in);
                
        while(true){
            System.out.println("Please input the [decimal] address to lookup:");
            System.out.println("Input \"C\" to look at the cache");
            System.out.println("Input \"Q\" to quit");
            String in = read.next();
            if(in.equals("Q")){System.exit(0);}
            System.out.println("\n");
            System.out.println("Input: " + in);
            
            if(isInt(in) && between(Integer.parseInt(in), 0, mem.length-1)){
                String address = formatBinaryString(in);
                int set = Integer.parseInt(address.substring(4), 2);
                String tag = address.substring(0,4);
                System.out.println("Address: " + address);
                
                if(!cache[set][0].equals(tag) && !cache[set][2].equals(tag)){
                    System.out.println("Cache Miss!");
                    String data = mem[Integer.parseInt(in)];
                    if(toggles[set]){
                        cache[set][0] = tag;
                        cache[set][1] = data;
                        toggles[set] = !toggles[set];
                        System.out.println("Data: " + cache[set][1]);
                    }
                    else{
                        cache[set][2] = tag;
                        cache[set][3] = data;
                        toggles[set] = !toggles[set];
                        System.out.println("Data: " + cache[set][3]);
                    }
                    
                }
                else{
                    System.out.println("Cache Hit!");
                    if(cache[set][0].equals(tag)){
                        System.out.println("Data: " + cache[set][1]);
                    }
                    else{
                        System.out.println("Data: " + cache[set][3]);
                    }
                }
            }
            else if(in.equals("C")){
                System.out.println("Set:\tTag:\tData:\tTag:\tData:\t");
                for(int i = 0; i < cache.length; i++){
                    System.out.print(i + ":");
                    for(int j = 0; j < cache[i].length; j++){
                        System.out.print("\t" + cache[i][j]);
                    }
                    System.out.println();
                }
            }
            else{
                System.out.println("I'm sorry, that was an invalid input");
            }
        }
    }
    
    public static boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    
    public static String formatBinaryString(String s){
        String str = Integer.toBinaryString(Integer.parseInt(s));
        while(str.length() < 6){
            str = "0" + str;
        }
        return str;
    }
    
    public static boolean between(int num, int lower, int upper){
        return num >= lower && num <= upper;
    }
}
