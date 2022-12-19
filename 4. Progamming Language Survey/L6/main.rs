/*
Author: Christopher LaFave
Desc: Takes a bible verse from the user 
      and prints it to the console and a file.
12/6/22: Main framework done!
         Takes in verses and outputs correct verse to console.
12/8/22: Added main while-loop, format_verse, switched to reading files 
         instead of Strings, and finished everything not done yet.
         All done!!
*/

use std::fs::*;
use std::io::*;

fn main() {
    //open files, create file readers
    let bible_in = OpenOptions::new().read(true)
                        .open(".\\src\\Bible.txt").unwrap();
    let mut bible_reader = BufReader::new(bible_in);
    let bible_abrvs_in = OpenOptions::new().read(true)
                        .open(".\\src\\Bible_Abbreviations.csv").unwrap();
    let mut bible_abrvs_reader = BufReader::new(bible_abrvs_in);
    let mut verses_out = OpenOptions::new().append(true)
                        .open(".\\src\\verses.txt").unwrap();

    //create main variables
    let mut book_in;
    let mut chap_in;
    let mut verse_in;
    let mut keep_looping = String::from("Y");

    //main loop, keeps asking for verses until user inputs "N" or "n"
    while keep_looping.to_uppercase() != "N"{
        //set iteratoes to the top of the file
        _ = bible_abrvs_reader.rewind();
        _ = bible_reader.rewind();

        //Ask for verse block
        //Formats the book on input, this runs it through the .csv
        println!("Please enter your verse: ");
        print!("  Book: ");
        book_in = format_book(get_input().to_uppercase(), 
                              bible_abrvs_reader.by_ref());
        print!("  Chapter: ");
        chap_in = get_input();
        print!("  Verse: ");
        verse_in = get_input();

        
        //initializes error-checking booleans
        let mut book_found = false;
        let mut chap_found = false;
        let mut verse_found = false;
        
        /*
        For each line in Bible.txt:
            Find book:
                If book not found:
                    book_found will be false and send an error message
            Find chapter:
                If chapter not found:
                    chap_found will be false and send an error message
            Find verse:
                If verse found:
                    Format the verse to under 80 cols
                    Print to console
                    Append to verses.txt
                If verse not found:
                    verse_found will be false and send an error message
        */
        for ref line in bible_reader.by_ref().lines(){
            if line.as_ref().unwrap() == &(format!("THE BOOK OF {}", book_in))
            && !book_found {
                book_found = true;
            }
            else if book_found && !chap_found{
                if line.as_ref().unwrap() == &(format!("CHAPTER {}", chap_in))
                || line.as_ref().unwrap() == &(format!("PSALM {}", chap_in)){
                    chap_found = true;
                }
                else if line.as_ref().unwrap().contains("THE BOOK OF ") 
                && !line.as_ref().unwrap().contains(&book_in){
                    break;
                }
            }
            else if chap_found{
                let line_num = String::from((line.as_ref().unwrap()
                            .split(" ").collect::<Vec<&str>>())[0]);
                if line_num == verse_in{
                    verse_found = true;
                    let verse = format_verse(format!("{} {}:{}\n\n",
                                 book_in, chap_in, line.as_ref().unwrap()));
                    print!("{}", verse);
                    verses_out.write_all(&verse.as_bytes()).unwrap();
                    break;
                }
                else if line.as_ref().unwrap().is_empty(){
                    break;
                }
            }
        }

        //print any errors found
        if !book_found {
            println!("Book {} not found", book_in);
        }
        else if !chap_found {
            println!("Chapter {} of book {} not found", chap_in, book_in);
        }
        else if !verse_found {
            println!("Verse {} of chapter {} in book {} not found",
                     verse_in, chap_in, book_in);
        }

        //ask if the user wants to keep going and clears the screen
        //doesn't go until the user inputs "Y"/"y" or "N"/"n"
        keep_looping = String::from("");
        while keep_looping.to_uppercase() != String::from("Y") 
        && keep_looping.to_uppercase() != String::from("N"){
            println!("Would you like to enter another verse? (Y/N)");
            keep_looping = get_input();
            print!("{}[2J", 27 as char);
        }
    }
    
    /*Functions only past this point!!!! No mains allowed!!!!!*/

    /* Formats the user-inputted book title correctly
    Checks through each line of the .csv for a match
    If match found:
        -return correct book title
    Else (no match found):
        -return user's input uneditted
    
    NOTE: everything gets capitalized to help with comparison.*/
    fn format_book(book_in: String, file: &mut BufReader<File>) -> String{
        for line in file.lines(){
            let ab_list:Vec<&str> = line.as_ref().unwrap()
                                    .split(",").collect();
            if book_in.eq(&String::from(ab_list[0]).to_uppercase()){
                return String::from(ab_list[1]).to_uppercase();
            }
        }
        return book_in.to_uppercase();
    }

    //makes getting input MUCH easier
    //trim statement in the return cuts off the \n that's always at the end
    fn get_input() -> String{
        let mut input = String::new();
        _ = stdout().flush();
        stdin().read_line(&mut input).unwrap();
        return String::from(input.trim());
    }

    /* Takes a string and keeps it within the 80 column rule
    If statement #1: 
       -Keeps track of the last space in the string before column 80
    If statement #2
       -When you hit column 80:
           -Slice string from the start to the last " " (excluding the space)
           -Add a newline (replacing the space)
           -Format the rest of the string (when it's 80+ columns) recursively!
    When it gets to the end, it'll hit the return and recurse back up.*/
    fn format_verse(mut verse: String) -> String{
        let mut last_space = 0;
        for i in 0..verse.len(){
            if i < 80 && verse.chars().nth(i).unwrap() == ' ' {
                last_space = i;
            }
            if i == 80 {
                verse = String::from(&verse[..last_space]) 
                      + &String::from("\n") 
                      + &format_verse(String::from(&verse[last_space+1..]));
            }
        }
        return verse;
    }
}
