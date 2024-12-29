package models;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Student extends Person {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private final int studentID;
    private final ArrayList<Integer> grades = new ArrayList<>();

    public Student(String name, String surname, int age, boolean gender, int grade) {
        super(name, surname, age, gender);
        this.studentID = idGenerator.getAndIncrement();
        addGrade(grade);
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double calculateGPA() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    @Override
    public String toString() {
        return super.toString() + " I am a student with ID " + studentID + ".";
    }
}
