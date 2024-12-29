import models.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        School school = new School();

        List<Student> students = readStudents("/Users/alexeyazarenkov/Downloads/Assigment1/src/students.txt");
        List<Teacher> teachers = readTeachers("/Users/alexeyazarenkov/Downloads/Assigment1/src/teachers.txt");

        for (Student student : students) {
            school.addMember(student);
        }

        for (Teacher teacher : teachers) {
            school.addMember(teacher);
        }

        Collections.sort(school.getMembers(), Comparator.comparing(Person::getSurname));

        for (Person member : school.getMembers()) {
            if (member instanceof Student) {
                Student student = (Student) member;
                System.out.println(student + " GPA: " + student.calculateGPA());
            } else if (member instanceof Teacher) {
                Teacher teacher = (Teacher) member;
                if (teacher.getYearsOfExperience() > 10) {
                    teacher.giveRaise(10);
                }
                System.out.println(teacher);
            }
        }
    }

    private static List<Student> readStudents(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String surname = data[1];
                int age = Integer.parseInt(data[2]);
                boolean gender = Boolean.parseBoolean(data[3]);
                int grade = Integer.parseInt(data[4]);
                Student student = new Student(name, surname, age, gender, grade);
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static List<Teacher> readTeachers(String filePath) {
        List<Teacher> teachers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String surname = data[1];
                int age = Integer.parseInt(data[2]);
                boolean gender = Boolean.parseBoolean(data[3]);
                String subject = data[4];
                int yearsOfExperience = Integer.parseInt(data[5]);
                int salary = Integer.parseInt(data[6]);
                Teacher teacher = new Teacher(name, surname, age, gender, subject, yearsOfExperience, salary);
                teachers.add(teacher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teachers;
    }
}