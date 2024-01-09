package experiments.个人通讯录管理系统;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Function {
    Scanner scanner = new Scanner(System.in);
    List<Individual> individuals = new ArrayList<>();

    int i = 1;

    // 增
    public void add() {
        Individual individual = new Individual();

        System.out.print("请输入姓名：");
        individual.setName(scanner.next());

        System.out.print("请输入您的性别：");
        individual.setGender(scanner.next());

        int phoneNumber;
        boolean validPhoneNumber = false;
        while (!validPhoneNumber) {
            System.out.print("请输入电话号：");
            phoneNumber = scanner.nextInt();
            if (phoneNumber > 0) {
                // 检查电话号码是否已存在
                boolean isPhoneNumberExists = false;
                for (Individual existingIndividual : individuals) {
                    if (existingIndividual.getPhoneNumber() == phoneNumber) {
                        isPhoneNumberExists = true;
                        break;
                    }
                }

                if (!isPhoneNumberExists) {
                    validPhoneNumber = true;
                    individual.setPhoneNumber(phoneNumber);
                } else {
                    System.out.println("电话号已存在，请重新输入：");
                }
            } else {
                System.out.println("电话号必须是正整数，请重新输入：");
            }
        }

        System.out.print("请输入住址：");
        individual.setAddress(scanner.next());

        if (individual.getName() == null || individual.getName().isEmpty()) {
            System.out.println("姓名不能为空");
            System.out.print("请输入姓名：");
            individual.setName(scanner.next());
        }
        if (individual.getPhoneNumber() == 0) {
            System.out.println("电话号不能为空， 请重新输入：");
            individual.setPhoneNumber(scanner.nextInt());
        }

        individuals.add(individual);
        System.out.println("个人信息存入成功");

    }

    // 删
    public void delete(int phoneNumber) {
        Iterator iterator = individuals.iterator();

        // 避免ConcurrentModificationException异常
        while (iterator.hasNext()) {
            Individual individual = (Individual) iterator.next();
            if (individual.getPhoneNumber() == phoneNumber) {
                iterator.remove();
            }
        }

        System.out.println("用户信息删除成功");
    }

    // 改
    public void update(int phoneNumber) {
        Individual individual = select(phoneNumber);

        System.out.print("请输入姓名：");
        individual.setName(scanner.next());

        System.out.print("请输入您的性别：");
        individual.setGender(scanner.next());

        int newPhoneNumber;
        boolean validPhoneNumber = false;
        while (!validPhoneNumber) {
            System.out.print("请输入电话号：");
            newPhoneNumber = scanner.nextInt();
            if (phoneNumber > 0) {
                // 检查电话号码是否已存在
                boolean isPhoneNumberExists = false;
                for (Individual existingIndividual : individuals) {
                    if (existingIndividual.getPhoneNumber() == newPhoneNumber) {
                        isPhoneNumberExists = true;
                        break;
                    }
                }

                if (!isPhoneNumberExists) {
                    validPhoneNumber = true;
                    individual.setPhoneNumber(newPhoneNumber);
                } else {
                    System.out.println("电话号已存在，请重新输入：");
                }
            } else {
                System.out.println("电话号必须是正整数，请重新输入：");
            }
        }

        System.out.print("请输入住址：");
        individual.setAddress(scanner.next());

        if (individual.getName() == null || individual.getName().isEmpty()) {
            System.out.println("姓名不能为空");
            System.out.print("请输入姓名：");
            individual.setName(scanner.next());
        }
        if (individual.getPhoneNumber() == 0) {
            System.out.println("电话号不能为空， 请重新输入：");
            individual.setPhoneNumber(scanner.nextInt());
        }

        individuals.add(individual);
        System.out.println("个人信息修改成功");

    }

    // 查
    public Individual select(String name) {
        System.out.println("用户名\t性别\t\t电话号\t\t住址");

        for (Individual individual : individuals) {
            if (individual.getName().equals(name)) {
                show(individual);
                return individual;
            }
        }

        System.out.println("用户信息不存在");
        return null;
    }

    public Individual select(int phoneNumber) {
        boolean flag = false;
        System.out.println("用户名\t性别\t\t电话号\t\t住址");

        for (Individual individual : individuals) {
            if (individual.getPhoneNumber() == phoneNumber) {
                show(individual);
                return individual;
            }
        }

        System.out.println("用户信息不存在");
        return null;
    }

    // 展示用户
    public void show(Individual individual) {
        System.out.println(individual.getName() + "\t\t" + individual.getGender() + "\t\t" + individual.getPhoneNumber()
                + "\t\t" + individual.getAddress());
    }

    // 存在文件里
    public void save(String filename) {

//        String desktopPath = System.getProperty("user.home") + "/Desktop/"; // 获取桌面路径
        String file = "E:/E/桌面/" + filename + ".txt";  // 我的桌面地址，别人的桌面地址需要手动更换，且‘\’这个斜杠在java中会报错，应用正斜杠'/'
        File desktopDir = new File(file);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            if (!desktopDir.exists()) {
                desktopDir.createNewFile(); // 如果目录不存在，创建它
            }

            for (Individual individual : individuals) {
                writer.write("用户" + i + "\n");
                writer.write("Name: " + individual.getName() + "\n");
                writer.write("Gender: " + individual.getGender() + "\n");
                writer.write("Phone Number: " + individual.getPhoneNumber() + "\n");
                writer.write("Address: " + individual.getAddress() + "\n");
                writer.write("\n");  // 个人信息之间使用空行分隔
                i++;
            }
            System.out.println("个人信息已保存到文件: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("保存文件时发生错误：" + e.getMessage());
        }
    }
}
