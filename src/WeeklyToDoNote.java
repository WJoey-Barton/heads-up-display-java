//Hard Coded for now
//Therefore didn't inherit from Note.java

import java.time.LocalDate;
import java.util.ArrayList;

public class WeeklyToDoNote{

    private final static String prefix = "WEEKLY_TODO";
    private static ArrayList<String> oneWeek;
    private static LocalDate today = LocalDate.now();
    private static String todaysDay = today.getDayOfWeek().toString();

    public WeeklyToDoNote() {
        oneWeek = setWeek();
    }

    public ArrayList<String> setWeek() {
        ArrayList<String> thisWeek = new ArrayList<>();

        //Sunday
        thisWeek.add("Audit");

        //Monday
        thisWeek.add("null");

        //Tuesday
        thisWeek.add("null");

        //Wednesday
        thisWeek.add("Laundry");

        //Thursday
        thisWeek.add("null");

        //Friday
        thisWeek.add("null");

        //Saturday
        thisWeek.add("null");
        
        
        return thisWeek;
    }

    public String getTodaysToDo() {
        if(todaysDay.equals("MONDAY")) {
            return "Monday: " + oneWeek.get(1);
        }
        if(todaysDay.equals("TUESDAY")) {
            return "Tuesday: " + oneWeek.get(2);
        }
        if(todaysDay.equals("WEDNESDAY")) {
            return "Wednesday: " + oneWeek.get(3);
        }
        if(todaysDay.equals("THURSDAY")) {
            return "Thursday: " + oneWeek.get(4);
        }
        if(todaysDay.equals("FRIDAY")) {
            return "Friday: " + oneWeek.get(5);
        }
        if(todaysDay.equals("SATURDAY")) {
            return "Saturday: " + oneWeek.get(6);
        }
        if(todaysDay.equals("SUNDAY")) {
            return "Sunday: " + oneWeek.get(0);
        }
        return null;

    }




    
}
