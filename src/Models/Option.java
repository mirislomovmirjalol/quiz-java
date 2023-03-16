package Models;

public class Option {
    private String option;
    private boolean isCorrect = false;

    public Option(String option, boolean isCorrect) {
        this.option = option;
        this.isCorrect = isCorrect;
    }

    public String getOption() {
        return option;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
