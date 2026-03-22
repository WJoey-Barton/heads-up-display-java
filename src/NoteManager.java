/*  HOW TO USE:
    Instantiate a new NoteManager
    Set the note for each Note class (CalendarNote, ToDoNote, ...)
    Save/Load the notes
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class NoteManager {

    private Note note;
    private ArrayList<Note> parsedNotes = new ArrayList<>();
    private String output = "";
    private String prefix;
    private LocalDate date;

    public NoteManager() {

    }
    
    public NoteManager(Note n) {
        this.note = n;
        this.prefix = note.getPrefix();
    }

    public void setNote(Note n) {
        this.note = n;
        this.prefix = note.getPrefix();
    }

    public String getPath() {
        String folder = "C:\\Users\\wjoey\\Documents\\JavaProjectsPersonal\\CalendarV4\\AllNotes\\";
        String filePrefix = note.getPrefix();
        String folderPath = folder + filePrefix;

        return folderPath;
    }

    public void saveNote() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(getPath(), true));
        writer.write(note.toString() + "\n");
        writer.close();
    }

    public String loadNotes() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(getPath()));

        String line;
        String accumulator = "";
        while((line = reader.readLine()) != null) {
            accumulator += line + "\n";
            
            //System.out.println(line);
        }
        this.output = accumulator;
        
        reader.close();
        return this.output;
    }

    public ArrayList<Note> parseNotes(String output) {
        
        parsedNotes.clear();

        String[] stringNotes = output.split("\n");
        for(int i = 0; i < stringNotes.length; i++) {

            if(stringNotes[i].trim().isEmpty()) {
                continue;
            }

            if (prefix.equals("CALENDAR_NOTES")) {
                String[] parts1 = stringNotes[i].split("\\|");
                String event = parts1[0].trim();
                date = LocalDate.parse(parts1[1].trim());
                CalendarNote loadedNote = new CalendarNote(event, date);
                parsedNotes.add(loadedNote);
                sortDate(parsedNotes);
            }

            if(prefix.equals("TODO_NOTES")) {
                String[] parts = stringNotes[i].split("\\|");
                String title = parts[0].trim();
                int urgencyLevel = Integer.parseInt(parts[1].trim());
                ToDoNote loadedNote = new ToDoNote(title, urgencyLevel);
                parsedNotes.add(loadedNote);
            }

            if(prefix.equals("JOURNAL_NOTES")) {
                String[] parts = stringNotes[i].split("\\|\\|\\|");
                LocalDate entryDate = LocalDate.parse(parts[0].trim());
                String entry = parts[1].trim();
                JournalNote loadedNote = new JournalNote(entry, entryDate);
                parsedNotes.add(loadedNote);
                sortDate(parsedNotes);
            }


            // if(prefix.equals("WEEKLY_TODO")) {
            //     String[] parts = stringNotes[i].split(":");
            //     String day = parts[0].trim();
            //     String title = parts[1].trim();
            //     WeeklyToDoNote loadedNote = new WeeklyToDoNote(title);
            //     parsedNotes.add(loadedNote);
            // }
        }

        return parsedNotes;
    }  

    public void sortDate(ArrayList<Note> parsed) {
        parsed.sort(Comparator.comparing(Note::getEventDate));
    }
    
}
