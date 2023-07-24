package com.goit.myproject10;

import java.io.*;
import java.util.*;

public class PhoneValidator {

    public static void main(String[] args) {
        String fileName = "file.txt";
        Map<String, Integer> phoneFrequencyMap = getPhoneFrequencyMap(fileName);
        printPhoneFrequencySorted(phoneFrequencyMap);
    }

    public static Map<String, Integer> getPhoneFrequencyMap(String fileName) {
        Map<String, Integer> phoneFrequencyMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isValidPhoneNumber(line)) {
                    phoneFrequencyMap.put(line, phoneFrequencyMap.getOrDefault(line, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return phoneFrequencyMap;
    }

    public static void printPhoneFrequencySorted(Map<String, Integer> phoneFrequencyMap) {
        List<Map.Entry<String, Integer>> phoneFrequencyList = new ArrayList<>(phoneFrequencyMap.entrySet());
        Collections.sort(phoneFrequencyList, (a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : phoneFrequencyList) {
            System.out.println(entry.getKey());
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {

        String regex = "^\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}$";
        return phoneNumber.matches(regex);
    }
}


