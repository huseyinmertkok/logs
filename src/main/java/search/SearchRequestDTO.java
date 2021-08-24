package search;


import java.util.List;

public class SearchRequestDTO {
    private String name;
    private String gemNumber;
    private int level;
    private String endDate;
    private String startDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGemNumber() {
        return gemNumber;
    }

    public void setGemNumber(String gemNumber) {
        this.gemNumber = gemNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
