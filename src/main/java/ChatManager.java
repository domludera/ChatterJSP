import java.util.ArrayList;
import java.util.Date;

public class ChatManager {

    private ArrayList<String> names;
    private ArrayList<String> messages;
    private ArrayList<Date> dates;

    public ChatManager() {
        this.names = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.dates = new ArrayList<>();
    }


    public void setLog(String name, String message) {
        this.names.add(name);
        this.messages.add(message);
        this.dates.add(new Date());
    }

    public void clearLog() {
        this.names = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.dates = new ArrayList<>();
    }

    public void clearLog(Date from, Date to) {
        int size = dates.size();
        for (int i = 0; i < size; ) {
            if (dates.get(i).compareTo(from) >= 0 && dates.get(i).compareTo(to) <= 0) {
                names.remove(i);
                messages.remove(i);
                dates.remove(i);
            } else {
                i++;
            }

        }
    }

    public ArrayList<String> getMessages() {
        return messages;
    }


    public ArrayList<Date> getDates() {
        return dates;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public String getLog() {
        int size = dates.size();
        String log = "";
        for (int i = 0; i < size; i++) {
            log += "<p>" + names.get(i) + "</br>" + messages.get(i) + "</br>" + dates.get(i) + "<p>";
        }
        return log;
    }

    public String getLog(Date from, Date to) {
        int size = dates.size();
        String log = "";
        for (int i = 0; i < size; i++) {
            if (dates.get(i).compareTo(from) >= 0 && dates.get(i).compareTo(to) <= 0) {
                log += "<p>" + names.get(i) + "</br>" + messages.get(i) + "</br>" + dates.get(i) + "<p>";

            }
        }
        if (log.isEmpty()) {
            log = "<h1>No Messages!</h1>";
        }
        return log;
    }

}
