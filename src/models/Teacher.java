package models;

import java.util.ArrayList;

public class Teacher extends Person{

    private String subject;
    private int yearsOfExperience;
    private int salary;

    public void giveRaise(int percents) {
        this.salary = salary + (salary / 100 * percents);
    }

    @Override
    public String toString() {
        return super.toString() + " I teach " + subject + ".";
    }
}
