import java.time.LocalDate;

public abstract class Note {

    private String title;
    private String prefix;
    private LocalDate date;

    public Note(String prefix) {
        title = "";
        this.prefix = prefix;

    }

    public Note (String noteTitle, String folderName) {
        this.title = noteTitle;
        this.prefix = folderName;

    } 

    public void setTitle(String noteTitle) {
        this.title = noteTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getPrefix() {
        return prefix;
    }

    public LocalDate getEventDate() {
        return date;
    }
    
}
