package org.homework.hibernatehw7.config;

import java.io.Closeable;
import java.io.Serializable;
import java.util.Scanner;

public class ScannerConsole implements Closeable, Serializable {

    private static final long serialVersionUID = 3335444651928374654L;

    private static Scanner scanner;

    public static Scanner getInstance() {
        if (scanner == null) {
            synchronized (ScannerConsole.class) {
                if (scanner == null) {
                    scanner = new Scanner(System.in);
                }
            }
        }
        return scanner;
    }

    @Override
    public void close() {
        scanner.close();
    }
}
