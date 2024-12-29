import models.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        School school = new School();

        List<Student> students = readStudents();
        List<Teacher> teachers = readTeachers();

        for (Student student : students) {
            school.addMember(student);
        }

        for (Teacher teacher : teachers) {
            school.addMember(teacher);
        }

        school.getMembers().sort(Comparator.comparing(Person::getSurname));

        for (Person member : school.getMembers()) {
            if (member instanceof Student student) {
                System.out.println(student + " GPA: " + student.calculateGPA());
            } else if (member instanceof Teacher teacher) {
                if (teacher.getYearsOfExperience() > 10) {
                    teacher.giveRaise(10);
                }
                System.out.println(teacher);
            }
        }
    }

    private static List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/alexeyazarenkov/Downloads/Assigment1/src/students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                String name = data[0];
                String surname = data[1];
                int age = Integer.parseInt(data[2]);
                boolean gender = Boolean.parseBoolean(data[3]);
                Student student = new Student(name, surname, age, gender, 0); // временная оценка
                for (int i = 4; i < data.length; i++) {
                    student.addGrade(Integer.parseInt(data[i]));
                }
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static List<Teacher> readTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/alexeyazarenkov/Downloads/Assigment1/src/teachers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Teacher teacher = getTeacher(line);
                teachers.add(teacher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    private static Teacher getTeacher(String line) {
        String[] data = line.split(" ");
        String name = data[0];
        String surname = data[1];
        int age = Integer.parseInt(data[2]);
        boolean gender = Boolean.parseBoolean(data[3]);
        String subject = data[4];
        int yearsOfExperience = Integer.parseInt(data[5]);
        int salary = Integer.parseInt(data[6]);
        return new Teacher(name, surname, age, gender, subject, yearsOfExperience, salary);
    }
}