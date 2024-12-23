package models;

import java.util.ArrayList;

public class Student extends Person {

    private int studentID;
    private ArrayList<Integer> grades = new ArrayList<>();

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
