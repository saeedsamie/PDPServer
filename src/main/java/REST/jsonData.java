package REST;

public class jsonData {

    private String start_time;
//    private String period;
//    private String date;
    private String day;
    private boolean presence;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    @Override
    public String toString() {
        return "jsonData [start time=" + start_time + ", presence=" + presence + "]";
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

//    public void setPeriod(String period) {
//        this.period = period;
//    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public String getStart_time() {
        return start_time;
    }

//    public String getPeriod() {
//        return period;
//    }

//    public String getDate() {
//        return date;
//    }

    public boolean isPresence() {
        return presence;
    }
}