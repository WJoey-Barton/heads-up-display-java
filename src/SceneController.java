import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneController {

    @FXML
    private TextArea ToDo_TextArea;
    

    @FXML
    private TextArea Calendar_TextArea;

    @FXML
    private Button AddTaskToDo_Button;

    @FXML
    private Button RemoveTask_Button;

    @FXML
    private Button RemoveCalendar_Button;

    @FXML
    private Button AddCalendar_Button;

    @FXML
    private Button Journal_Button;

    @FXML
    private Label Date_Label;

    @FXML
    private Label WeekOfYear_Label;

    @FXML
    private Label DailyTask_Label;

    @FXML
    private Label DaysTillWedding_Label;

    @FXML
    private Label ToDo_Label;

    @FXML
    private Label Calendar_Label;

    @FXML
    private TextField TaskEvent_TextField;

    @FXML
    private TextField Date_TextField;

    //Today's Date
    //This week's Date
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
    DateTimeFormatter weekFormatter = DateTimeFormatter.ofPattern("w");
    String todaysDate = today.format(formatter);
    String todaysWeekDate = "Week " + today.format(weekFormatter);

    WeeklyToDoNote WTD = new WeeklyToDoNote();
    NoteManager m = new NoteManager();

    //Day's till Wedding
    LocalDate weddingDay = LocalDate.of(2027, 5, 29);
    long daysTillWedding = ChronoUnit.DAYS.between(today, weddingDay);

    CalendarNote loadCalendarNotes = new CalendarNote();
    ToDoNote loadToDoNotes = new ToDoNote();
    

    @FXML
    private void initialize() {

        ToDo_TextArea.setEditable(false);
        Calendar_TextArea.setEditable(false);

        //Center Labels
        Date_Label.setText(todaysDate);
        WeekOfYear_Label.setText(todaysWeekDate);
        DailyTask_Label.setText(WTD.getTodaysToDo());
        DaysTillWedding_Label.setText("Day's till Wedding: " + daysTillWedding);

        try {
            m.setNote(loadCalendarNotes);
            Calendar_TextArea.setText(m.loadNotes());
            m.setNote(loadToDoNotes);
            ToDo_TextArea.setText(m.loadNotes());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        


        // NoteManager m = new NoteManager();
        // CalendarNote c = new CalendarNote("STEM Expo", LocalDate.of(2025, 12, 10));
        // CalendarNote c2 = new CalendarNote("Wedding Day!", LocalDate.of(2027, 5, 29));

        // ToDoNote t = new ToDoNote("Head Shot");
        // ToDoNote t2 = new ToDoNote("FAFSA");

        // ArrayList<Note> CalendarNotes;
        // m.setNote(t2);
        
        // try {
        //     //m.saveNote();
        //     //System.out.println(m.loadNotes());
        //     //m.loadNotes();
        //     CalendarNotes = m.parseNotes(m.loadNotes());
        //     System.out.println();

        //     for(int i = 0; i < CalendarNotes.size(); i++) {
        //        System.out.println(CalendarNotes.get(i));
        //     }
        // } catch (IOException e) {
            
        //     e.printStackTrace();
        // }

        // WeeklyToDoNote w = new WeeklyToDoNote();


        // System.out.println(w.getTodaysToDo());
    }

    @FXML
    private void AddToDoButton_clicked() throws IOException {

        String ugrencyLevel = Date_TextField.getText();
        int level = -1;

        if(!ugrencyLevel.isEmpty()) {
           level = Integer.parseInt(ugrencyLevel); 
        }

        ToDoNote newNote = new ToDoNote(TaskEvent_TextField.getText(), level);
        m.setNote(newNote);
        m.saveNote();

        TaskEvent_TextField.clear();
        Date_TextField.clear();
        ToDo_TextArea.setText(m.loadNotes());
    }

    @FXML
    private void RemoveTaskButton_clicked() throws IOException {
        String selectedText = ToDo_TextArea.getSelectedText();

        if(selectedText == null || selectedText.trim().isEmpty()) {
            return;
        }

        m.setNote(loadToDoNotes);
        String allNotes = m.loadNotes();
        ArrayList<Note> todoList = m.parseNotes(allNotes);

        String selectedLine = selectedText.trim();
        todoList.removeIf(note -> note.toString().equals(selectedLine));

        rewriteFile(todoList, loadToDoNotes);

        ToDo_TextArea.setText(m.loadNotes());
    }

    @FXML
    private void RemoveCalendarButton_clicked() throws IOException {
        String selectedText = Calendar_TextArea.getSelectedText();

        if(selectedText == null || selectedText.trim().isEmpty()) {
            return;
        }

        m.setNote(loadCalendarNotes);
        String allNotes = m.loadNotes();
        ArrayList<Note> calendarList = m.parseNotes(allNotes);

        String selectedLine = selectedText.trim();
        calendarList.removeIf(note -> note.toString().equals(selectedLine));

        rewriteFile(calendarList, loadCalendarNotes);

        Calendar_TextArea.setText(m.loadNotes());
    }

    @FXML
    private void AddCalendarButton_clicked() throws IOException {
        String dateToString = Date_TextField.getText();

        

        if(dateToString.equals("")) {
            CalendarNote newNote = new CalendarNote(TaskEvent_TextField.getText(), today);
            m.setNote(newNote);
            m.saveNote();
        } else {
            LocalDate getDate = LocalDate.parse(dateToString);
            CalendarNote newNote = new CalendarNote(TaskEvent_TextField.getText(), getDate);
            m.setNote(newNote);
            m.saveNote();
        }

        TaskEvent_TextField.clear();
        Date_TextField.clear();
        Calendar_TextArea.setText(m.loadNotes());
    }

    @FXML
    private void JournalButton_clicked() throws IOException {
        Stage stage = (Stage) ToDo_TextArea.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Journal.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void rewriteFile(ArrayList<Note> notes, Note noteType) throws IOException {
        m.setNote(noteType);
        String filePath = m.getPath();

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));

        for(Note note : notes) {
            writer.write(note.toString() + "\n");
        }

        writer.close();
    }
    
}
