import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class JournalController {

    @FXML
    private TextArea JournalEntry_TextArea;

    @FXML
    private Button HUD_Button;

    @FXML
    private Button NewEntry_Button;

    @FXML
    private Button SaveEntry_Button;

    @FXML
    private ComboBox<String> EntrySelector_ComboBox;

    @FXML
    private Label Date_Label;

    private LocalDate currentDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    NoteManager m = new NoteManager();
    JournalNote loadJournalNote = new JournalNote();

    private ArrayList<Note> journalEntries = new ArrayList<>();

    @FXML
    private void initialize() throws IOException {
        //loadPastEntries();

        EntrySelector_ComboBox.setOnAction(event -> EntrySelector_selected());

        NewEntryButton_clicked();
    }

    @FXML
    private void HUDButton_clicked() throws IOException {
        Stage stage = (Stage) JournalEntry_TextArea.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HUD.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void NewEntryButton_clicked() {
        currentDate = LocalDate.now();
        Date_Label.setText("Date: " + currentDate.format(formatter));
        JournalEntry_TextArea.clear();
        JournalEntry_TextArea.setEditable(true);
        EntrySelector_ComboBox.setValue(null);
    }

    @FXML
    private void SaveEntryButton_clicked() throws IOException {
        String entryText = JournalEntry_TextArea.getText();

        if(!entryText.trim().isEmpty()) {
            m.setNote(loadJournalNote);
            String allNotes = m.loadNotes();
            journalEntries = m.parseNotes(allNotes);

            journalEntries.removeIf(note -> note.getEventDate().equals(currentDate));
            JournalNote newEntry = new JournalNote(entryText, currentDate);
            journalEntries.add(newEntry);

            rewriteFile(journalEntries);
            
            System.out.println("Saving entry for: " + currentDate);
            //loadPastEntries();
        }
    }

    @FXML
    private void EntrySelector_selected() {
        String selected = EntrySelector_ComboBox.getValue();
        if(selected != null) {
            System.out.println("Loading entry: " + selected);
        }
    }

    private void loadPastEntries() throws IOException {
        m.setNote(loadJournalNote);
        String allNotes = m.loadNotes();
        journalEntries = m.parseNotes(allNotes);

        EntrySelector_ComboBox.getItems().clear();
        for(Note note : journalEntries) {
            EntrySelector_ComboBox.getItems().add(note.getEventDate().format(formatter));
        }
    }
    
    private void rewriteFile(ArrayList<Note> notes) throws IOException {
        m.setNote(loadJournalNote);
        String filePath = m.getPath();

        java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filePath, false));
        for(Note note : notes) {
            writer.write(note.toString() + "\n");
        }

        writer.close();
    }
}
