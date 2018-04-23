public class Verbo {
    private String verb, present, past;

    public Verbo(String verb, String present, String past) {
        this.verb = verb;
        this.present = present;
        this.past = past;
    }

    public Verbo() {
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getPast() {
        return past;
    }

    public void setPast(String past) {
        this.past = past;
    }
}
