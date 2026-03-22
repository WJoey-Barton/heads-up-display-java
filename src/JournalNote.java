import java.time.LocalDate;

public class JournalNote extends Note{

    private LocalDate eventDate;
    private final static String prefix = "JOURNAL_NOTES";
    private String journalEntry;

    public JournalNote() {
        super(prefix);
    }

    public JournalNote(String journalEntry, LocalDate date) {
        super(journalEntry, prefix);
        this.journalEntry = journalEntry;
        this.eventDate = date;
    }

    @Override 
    public LocalDate getEventDate() {
        return this.eventDate;
    }

    @Override
    public String toString() {
        return eventDate + " ||| " + journalEntry;
    }
    
}
