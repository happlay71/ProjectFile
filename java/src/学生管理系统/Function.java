package 学生管理系统;




public class Function {
    StudentList studentList = new StudentList(4);

    public void enterMainMenu(){
        boolean isFlag = true;
        while (isFlag){
            System.out.println("---------------学生信息管理软件------------\n");
            System.out.println("\t\t\t\t1 添加学生");
            System.out.println("\t\t\t\t2 修改学生");
            System.out.println("\t\t\t\t3 删除学生");
            System.out.println("\t\t\t\t4 学生列表");
            System.out.println("\t\t\t\t5  退 出");
            System.out.print("\t 请选择(1-5):");

            char menu = Utility.readMenuSelection();
            switch(menu){
                case '1':addStudent();break;
                case '2':modifyStudent();break;
                case '3':deleteStudent();break;
                case '4':listAllStudents();break;
                case '5':
                    System.out.print("确认是否退出(Y/N)：");
                    char isExit = Utility.readConfirmSelection();
                    if(isExit == 'Y'){
                        isFlag = false;
                    }
                    break;
            }
        }
    }

    public void addStudent(){
        System.out.println("--------------添加学生信息----------------");

        int number;
        for(;;){
            System.out.print("(-1退出，1继续)：");
            number = Utility.readInt();
            if(number == -1){
                return;
            }
            System.out.print("姓名：");
            String name = Utility.readString(10);
            System.out.print("性别：");
            char gender = Utility.readChar();
            System.out.print("数学：");
            int math = Utility.readInt();
            System.out.print("英语：");
            int english = Utility.readInt();
            System.out.print("计算机：");
            int computer = Utility.readInt();

            Student stud = new Student(name, gender, math, english, computer);
            boolean isSuccess = studentList.addStudent(stud);
            if(isSuccess){
                System.out.println("-----------------添加完成----------------");
            }else {
                System.out.println("-----------------添加失败----------------");
            }
        }
    }

    public void modifyStudent(){
        System.out.println("--------------修改学生信息----------------");
        Student stud;
        int number;
        for(;;){
            System.out.print("请选择待修改的客户编号(-1退出)：");
            number = Utility.readInt();
            if(number == -1){
                return;
            }
            stud = studentList.getStudent(number - 1);
            if(stud == null){
                System.out.println("无法找到指定学生！");
            }else{
                break;
            }
        }

        System.out.print("姓名：");
        String name = Utility.readString(10);
        System.out.print("性别：");
        char gender = Utility.readChar();
        System.out.print("数学：");
        int math = Utility.readInt();
        System.out.print("英语：");
        int english = Utility.readInt(3);
        System.out.print("计算机：");
        int computer = Utility.readInt(3);

        Student newStud = new Student(name, gender, math, english, computer);
        boolean isRepalaced = studentList.replaceStudent(number - 1, newStud);
        if(isRepalaced){
            System.out.println("-----------------修改成功----------------");
        }else {
            System.out.println("-----------------修改失败----------------");
        }
    }

    public void deleteStudent(){
        System.out.println("--------------删除学生信息----------------");
        int number;
        for(;;){
            System.out.print("请选择待修改的客户编号(-1退出)：");
            number = Utility.readInt();
            if(number == -1){
                return;
            }
            Student stud = studentList.getStudent(number - 1);
            if(stud == null){
                System.out.println("无法找到指定学生！");
            }else{
                break;
            }
        }

        System.out.print("确认是否删除(Y/N)：");
        char isDelete = Utility.readConfirmSelection();
        if(isDelete == 'Y'){
            boolean deleteSuccess = studentList.deleteStudent(number - 1);
            if(deleteSuccess){
                System.out.println("-----------------删除成功----------------");
            }else {
                System.out.println("-----------------删除失败----------------");
            }
        }
    }

    public void listAllStudents(){
        System.out.println("-----------------学生列表----------------");

        int total = studentList.getTotal();
        if(total == 0){
            System.out.println("没有学生记录！");
        }else {
            System.out.println("编号\t姓名\t性别\t数学\t英语\t计算机");
            Student[] studs = studentList.getAllStudents();
            for(int i = 0; i < studs.length; i++){
                Student stud = studs[i];
                System.out.println((i + 1) + "\t" + stud.getName() + "\t" + stud.getGender() + "\t" + stud.getMath() + "\t" +
                        stud.getEnglish() + "\t" + stud.getComputer());
            }
            System.out.print("是否需要按成绩排序（Y/N）：");
            char isEixt = Utility.readChar();
            if (isEixt == 'Y'){
                System.out.println("请输入想要排序的成绩名称：");
                String object = Utility.readString(3);
                System.out.println("编号\t姓名\t性别\t数学\t英语\t计算机");
                Student[] q_studs = studentList.quickSort(0, total - 1, object);
                for(int i = 0; i < q_studs.length - 1; i++){
                    Student stud = q_studs[i];
                    System.out.println((i + 1) + "\t" + stud.getName() + "\t" + stud.getGender() + "\t" + stud.getMath() + "\t" +
                            stud.getEnglish() + "\t" + stud.getComputer());
                }
            }else {return;}
        }
        System.out.println("----------------学生列表完成--------------\n");
    }
}
