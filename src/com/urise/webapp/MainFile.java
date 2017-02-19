package com.urise.webapp;

import java.io.File;
import java.io.IOException;

/**
 * Created by andrew on 19.02.17.
 */
public class MainFile {
    public static void main(String[] args) throws IOException {
        printDirectoryDeeply(new File("/home/andrew/IdeaProjects/resume-storage"));

    }
    //Сделать рекурсивный обход и вывод имени файлов в каталогах и подкаталогах (корневой каталог- ваш проект)
    public static void printDirectoryDeeply(File dir) {
        for (File f:dir.listFiles()) {
            if (f.isFile()) {
                System.out.println("File: "+ f.getName());
            } else if (f.isDirectory())
            {
                System.out.println("Directory: "+f.getName());
                printDirectoryDeeply(f);
            }
        }
    }

}
