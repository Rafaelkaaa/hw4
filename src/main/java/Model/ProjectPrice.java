package Model;

public class ProjectPrice {
    private String name;
    private int prise;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectPrice{");
        sb.append("name='").append(name).append('\'');
        sb.append(", prise=").append(prise);
        sb.append('}');
        return sb.toString();
    }
}
