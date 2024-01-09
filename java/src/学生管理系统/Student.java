package 学生管理系统;

public class Student {
    private String name;
    private char gender;
    private int math;
    private int english;
    private int computer;

    public Student(String name, char gender, int math, int english, int computer) {
        this.name = name;
        this.gender = gender;
        this.math = math;
        this.english = english;
        this.computer = computer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getComputer() {
        return computer;
    }

    public void setComputer(int computer) {
        this.computer = computer;
    }
}
