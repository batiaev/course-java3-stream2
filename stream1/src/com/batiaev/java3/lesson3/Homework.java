package com.batiaev.java3.lesson3;

import javax.management.MXBean;
import java.io.*;
import java.util.*;

public class Homework {

    private static File file = new File("src/task3.txt");

    public static void main(String[] args) {
        createFiles();
        task1();
//        task2();
//        task3();
    }

    private static void task1() {
        File file = new File("src/task1.txt");
        try (FileInputStream fromFile = new FileInputStream(file)) {
            byte[] arr = new byte[(int) file.length()];
            fromFile.read(arr);
            System.out.println(Arrays.toString(arr));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task2() {

        try (FileInputStream in1 = new FileInputStream("src/task2_in1.txt");
             FileInputStream in2 = new FileInputStream("src/task2_in2.txt");
             FileInputStream in3 = new FileInputStream("src/task2_in3.txt");
             FileInputStream in4 = new FileInputStream("src/task2_in4.txt");
             FileInputStream in5 = new FileInputStream("src/task2_in5.txt");
             FileOutputStream all = new FileOutputStream("src/task2_all.txt")) {

            ArrayList<InputStream> a1 = new ArrayList<>();
            a1.add(in1);
            a1.add(in2);
            a1.add(in3);
            a1.add(in4);
            a1.add(in5);

            Enumeration<InputStream> e = Collections.enumeration(a1);
            SequenceInputStream seq = new SequenceInputStream(e);

            int available = seq.available();
            while (seq.available() >0) {
                int read = seq.read();
            }
            while (e.hasMoreElements()) {
                FileInputStream in = (FileInputStream) e.nextElement();
                int c;
                while ((c = in.read()) != -1)
                    all.write(c);
                in.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task3() {
        System.out.println("Введите номер страницы, чтобы узнать содержимое. Или /exit для выхода.");
        System.out.println("Количество страниц: " + file.length() / 1800);
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String s = scanner.next();
                if (s.equals("/exit")) {
                    return;
                } else {
                    try {
                        int num = Integer.parseInt(s) - 1;
                        if (num < 0 || num > (file.length() / 1800) - 1) {
                            System.out.println("Такой страницы не существует");
                            continue;
                        }
                        RandomAccessFile accessToFile = new RandomAccessFile(file, "r");
                        accessToFile.seek(num * 1800);
                        for (int i = 0; i < 1800; i++) {
                            System.out.print((char) accessToFile.read());
                        }
                        System.out.println();
                        accessToFile.close();
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели некорректные данные");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void createFiles() {
        try (FileWriter outTask1 = new FileWriter("src/task1.txt");
             FileWriter outTask2_1 = new FileWriter("src/task2_in1.txt");
             FileWriter outTask2_2 = new FileWriter("src/task2_in2.txt");
             FileWriter outTask2_3 = new FileWriter("src/task2_in3.txt");
             FileWriter outTask2_4 = new FileWriter("src/task2_in4.txt");
             FileWriter outTask2_5 = new FileWriter("src/task2_in5.txt")) {
            for (int i = 0; i < 50; i++) {
                outTask1.write(i);
                outTask1.flush();
            }
            for (int i = 0; i < 100; i++) {
                outTask2_1.write(i);
                outTask2_1.flush();
                outTask2_2.write(i);
                outTask2_2.flush();
                outTask2_3.write(i);
                outTask2_3.flush();
                outTask2_4.write(i);
                outTask2_4.flush();
                outTask2_5.write(i);
                outTask2_5.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter in = new BufferedWriter(new FileWriter(file))) {
            char c = 'a';
            for (int j = 0; j < 7_000; j++) {
                for (int i = 0; i < 1800; i++) {
                    in.write(c);
                }
                in.flush();
                if (c == 'z') {
                    c = 'a';
                } else c++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
