package Model;


public class LongestProject {
    private String name;
    private int countMonth;

    public void setName(String name) {
        this.name = name;
    }

    public void setCountMonth(int countMonth) {
        this.countMonth = countMonth;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LongestProject{");
        sb.append("name='").append(name).append('\'');
        sb.append(", countMonth=").append(countMonth);
        sb.append('}');
        return sb.toString();
    }
}
