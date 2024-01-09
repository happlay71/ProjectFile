package experiments.person;

public class Teacher extends Person {
    private String job;
    private String department;

    public Teacher(String name, String gender, int age, String job, String department) {
        super(name, gender, age);
        this.job = job;
        this.department = department;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Teacher{" + "name='" + super.getName() + '\'' +
                ", gender='" + super.getGender() + '\'' +
                ", age=" + super.getAge() +
                ", job='" + job + '\'' +
                ", department='" + department + '\'' + "}";
    }
}
