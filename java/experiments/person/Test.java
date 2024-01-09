package experiments.person;

public class Test {
    public static void main(String[] args) {
        Person teacher = new Teacher("Tom", "男", 30, "java教程", "编程部");
        Person student = new Student("小明", "男",
                19, 422109071, 2022, 9, 1, "软件工程");

        System.out.println(teacher.toString());
        System.out.println(student.toString());
    }
}
