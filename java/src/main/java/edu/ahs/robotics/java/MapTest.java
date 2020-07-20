package edu.ahs.robotics.java;

import java.util.HashMap;
import java.util.HashSet;

public class MapTest {

    public void makeAMap() {
        HashMap<String, Integer> gradesByName = new HashMap<>();
        gradesByName.put("John", 100);
        gradesByName.put("Bob", 72);
        gradesByName.put("Fred", 63);

        System.out.println("John's grade is " + gradesByName.get("John"));
    }

    public void setThingy() {
        HashSet<String> students = new HashSet<>();
        students.add("John");
        students.add("Bob");
        students.add("Fred");

        if (students.contains("Bob")) {
            System.out.println("Bob is in the class.");
        }

    }
}
