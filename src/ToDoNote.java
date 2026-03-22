public class ToDoNote extends Note{

    private int ugrencyLevel = -1;
    private final static String prefix = "TODO_NOTES";

    public ToDoNote(){
        super(prefix);
    }

    public ToDoNote (String noteTitle) {
        super(noteTitle, prefix);
    }

    public ToDoNote (String title, int urgencyLevel) {
        super(title, prefix);
        this.ugrencyLevel = urgencyLevel;
    }

    public void setUrgencyLevel(int zeroToThree) {
        this.ugrencyLevel = zeroToThree;
    }

    public int getUrgencyLevel() {
        return ugrencyLevel;
    }

    @Override
    public String toString() {
        return getTitle() + " | " + getUrgencyLevel();
        
    }
    
}
