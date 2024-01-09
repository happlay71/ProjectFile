package experiments.person;

public class Student extends Person {
    Time time = new Time();

    private long number;
    private String specialized;

    public Student (String name, String gender, int age, long number, int year, int mouth, int day, String specialized) {
        super(name, gender, age);
        this.number = number;
        this.time.setYear(year);
        this.time.setMouth(mouth);
        this.time.setDay(day);
        this.specialized = specialized;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + super.getName() + '\'' +
                ", gender='" + super.getGender() + '\'' +
                ", age=" + super.getAge() +
                ", number=" + number +
                ", time=" + getTime().getYear() + '.' + getTime().getMouth() + '.' + getTime().getDay() +
                ", specialized='" + specialized + '\'' + '}';
    }
}
