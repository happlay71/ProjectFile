package 学生管理系统;

import java.util.Objects;

public class StudentList {
    private Student[] students;
    private int total = 0;

    public StudentList(int totalStudent){
        students = new Student[totalStudent];
    }

    public boolean addStudent(Student student){
        if(total >= students.length){
            return false;
        }
        students[total++] = student;
        return true;
    }

    public boolean replaceStudent(int index, Student stud){
        if(index < 0 || index >= total){
            return false;
        }
        students[index] = stud;
        return true;
    }

    public boolean deleteStudent(int index){
        if(index < 0 || index >= total){
            return false;
        }
        for(int i = index; i < total - 1; i++){
            students[i] = students[i + 1];
        }
        students[total-1] = null;
        total--;
        return true;
    }

    public Student[] quickSort(int low, int high, String object){
        int i, j, temp, i_ob, j_ob;
        Student t;
        if(low > high){
            return students;
        }
        i = low;
        j = high;
        if(Objects.equals(object, "数学")){
            i_ob = students[i].getMath();
            j_ob = students[j].getMath();
            temp = students[low].getMath();
        }else if(Objects.equals(object, "英语")){
            i_ob = students[i].getEnglish();
            j_ob = students[j].getEnglish();
            temp = students[low].getEnglish();
        }else if(Objects.equals(object, "计算机")){
            i_ob = students[i].getComputer();
            j_ob = students[j].getComputer();
            temp = students[low].getComputer();
        }else {
            System.out.println("没有这项科目！");
            return students;
        }

        while (i < j){
            while (temp >= i_ob && i < j){i++;}
            while (temp <= j_ob && i < j){j--;}
            if(i <= j){
                t = students[i];
                students[i] = students[j];
                students[j] = t;
            }
        }
        Student stud = students[low];
        students[low] = students[i];
        students[i] = stud;
        quickSort(low, j - 1, object);
        quickSort(j + 1, high, object);
        return students;
    }

    public Student[] getAllStudents(){
        Student[] studs = new Student[total];
        for(int i = 0; i < total; i++){
            studs[i] = students[i];
        }
        return studs;
    }

    public Student getStudent(int index){
        if(index < 0 || index >= total){
            return null;
        }
        return students[index];
    }

    public int getTotal(){
        return total;
    }
}
