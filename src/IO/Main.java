package IO;

import java.io.File;
import java.util.ArrayList;

public class Main {
    static int counter = 1;
    static int counter2 = 0;
    static int sumCountFileInDirectory = 0;
    static int sumCountDirectory = 0;
    static ArrayList<String> listWithNameFiles = new ArrayList<>();

    public static void main(String[] args) {
        File dir = new File("C:/Фото");

        if (dir.isDirectory()) getTreeDirectory(dir);

        else {
            getInformationAboutTree(dir);
        }
    }


    static void getTreeDirectory(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isFile())
                print(file);
            else if (file.isDirectory()) {
                print(file);
                counter++;
                counter2++;
                getTreeDirectory(file);
            }
        }
        counter--;
        counter2--;
    }

    static void print(File file) {
        if (file.isFile()) {
            for (int i = 0; i < counter2; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < counter; i++) {
                System.out.print("-");
            }
            System.out.println(" " + file.getName());
        } else if (file.isDirectory()) {
            for (int i = 0; i < counter2; i++) {
                System.out.print(" ");
            }
            System.out.print("|");
            for (int i = 0; i < counter; i++) {
                System.out.print("-");
            }
            System.out.println(" " + file.getName());
        }
    }

    static void getInformationAboutTree(File dir) {
        int countFile = 0;
        int countDirectory = 0;
        String s = dir.getParent();
        File file = new File(s);
        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) countFile++;
            else if (fileList[i].isDirectory()) countDirectory++;
        }

        System.out.println("Количество файлов " + countFile);
        System.out.println("Количество папок " + countDirectory);
        System.out.println("Среднее количество файлов в папке " + getMiddleFilesInDirectory(file));
        System.out.println("Среднее длина имени файла " + getMiddleLengthNameFiles(file));


    }


    static double getMiddleFilesInDirectory(File dir) {

        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                sumCountFileInDirectory++;
            }
            else if (file.isDirectory())
            {
                    sumCountDirectory++;
                    getMiddleFilesInDirectory(file);

                }
            }
            return (double) sumCountFileInDirectory / sumCountDirectory;
        }


    static double getMiddleLengthNameFiles(File dir) {
        int totalLengthFiles = 0;

        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                listWithNameFiles.add(file.getName());
            }
            else if (file.isDirectory())
            {
                getMiddleLengthNameFiles(file);
            }
        }
        for (String lengthFiles : listWithNameFiles){
            totalLengthFiles += lengthFiles.length();
        }
        return (double) totalLengthFiles / listWithNameFiles.size();
    }
}


