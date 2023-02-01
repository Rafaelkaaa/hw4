package model;

public class MaxProjectCountClient{
    private String name;
    private int projectCount;

    public void setName(String name) {
        this.name = name;
    }
    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("name='").append(name).append('\'');
        sb.append(", projectCount=").append(projectCount);
        sb.append('}');
        return sb.toString();
    }
}
