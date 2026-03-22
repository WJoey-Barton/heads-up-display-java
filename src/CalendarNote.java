import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalendarNote extends Note {

    private LocalDate eventDate;
    private final static String prefix = "CALENDAR_NOTES";

    public CalendarNote(){
        super(prefix);
    }

    public CalendarNote(String noteTitle, LocalDate dateOfEvent) {
        super(noteTitle, prefix);
        this.eventDate = dateOfEvent;
        
    }

    @Override
    public LocalDate getEventDate() {
        return this.eventDate;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return eventDate.format(formatter); 
    }

    @Override
    public String toString() {
        return getTitle() + " | " + eventDate;
    }
    
}
