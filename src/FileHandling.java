import java.io.*;
import java.util.Scanner;

public class FileHandling {

    public static void appendSameLine(String filePath, String appendData, boolean is) {
        try {
            File f1 = new File(filePath);
            Writer output;
            output = new BufferedWriter(new FileWriter(f1.getName(), true)); // clears file every time
            if (is) {
                output.append(appendData);
            } else {
                output.append("\n" + appendData);
            }

            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendOrWrite(String path, int id, String appendData, String notAppendData) {
        File myObj = new File(path);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            if (myReader1.hasNextLine()) {
                String data1 = myReader1.nextLine();
                String[] arrData = data1.split("\\|");
                while (myReader1.hasNextLine() && Integer.parseInt(arrData[0]) != id) {
                    data1 = myReader1.nextLine();
                    arrData = data1.split("\\|");
                }
                if (Integer.parseInt(arrData[0]) == id) {
                    String data2 = data1;
                    data2 = data2 + notAppendData;
                    File myObj1 = new File(path);
                    Scanner myReader;
                    myReader = new Scanner(myObj1);
                    String data = "";
                    String temp;

                    while (myReader.hasNextLine()) {
                        temp = myReader.nextLine();
                        arrData = temp.split("\\|");
                        if (Integer.parseInt(arrData[0]) == id) {
                            data = data + data2;
                        } else {
                            data = data + temp;
                        }
                        if (myReader.hasNextLine()) {
                            data = data + "\n";
                        }

                    }
                    FileWriter fw = new FileWriter(path, false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(data);
                    bw.close();
                } else {
                    FileHandling.appendSameLine(path, appendData, false);
                }
            } else {
                FileHandling.appendSameLine(path, appendData, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendOrWrite(String path, String userName, String appendData, String notAppendData) {
        File myObj = new File(path);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            if (myReader1.hasNextLine()) {
                String data1 = myReader1.nextLine();
                String[] arrData = data1.split("\\|");
                while (myReader1.hasNextLine() && !(arrData[0].equals(userName))) {
                    data1 = myReader1.nextLine();
                    arrData = data1.split("\\|");
                }
                if ((arrData[0].equals(userName))) {
                    String data2 = data1;
                    data2 = data2 + notAppendData;
                    File myObj1 = new File(path);
                    Scanner myReader;
                    myReader = new Scanner(myObj1);
                    String data = "";
                    String temp;

                    while (myReader.hasNextLine()) {
                        temp = myReader.nextLine();
                        arrData = temp.split("\\|");
                        if ((arrData[0].equals(userName))) {
                            data = data + data2;
                        } else {
                            data = data + temp;
                        }
                        if (myReader.hasNextLine()) {
                            data = data + "\n";
                        }

                    }
                    FileWriter fw = new FileWriter(path, false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(data);
                    bw.close();
                } else {
                    FileHandling.appendSameLine(path, appendData, false);
                }
            } else {
                FileHandling.appendSameLine(path, appendData, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendOrWriteChecker(String path, String userName, String appendData, String notAppendData, String subCheck) {
        File myObj = new File(path);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            if (myReader1.hasNextLine()) {
                String data1 = myReader1.nextLine();
                String[] arrData = data1.split("\\|");
                while (myReader1.hasNextLine() && !(arrData[0].equals(userName))) {
                    data1 = myReader1.nextLine();
                    arrData = data1.split("\\|");
                }
                if ((arrData[0].equals(userName))) {
                    String data2 = data1;
                    data2 = data2 + notAppendData;
                    File myObj1 = new File(path);
                    Scanner myReader;
                    myReader = new Scanner(myObj1);
                    String data = "";
                    String temp;

                    while (myReader.hasNextLine()) {
                        temp = myReader.nextLine();
                        arrData = temp.split("\\|");
                        if ((arrData[0].equals(userName))) {
                            if (arrData[1].contains(subCheck)) {
                                data = data + data1;
                            } else {
                                data = data + data2;
                            }
                        } else {
                            data = data + temp;
                        }
                        if (myReader.hasNextLine()) {
                            data = data + "\n";
                        }

                    }
                    FileWriter fw = new FileWriter(path, false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(data);
                    bw.close();
                } else {
                    FileHandling.appendSameLine(path, appendData, false);
                }
            } else {
                FileHandling.appendSameLine(path, appendData, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeLine(String path, String userName) {
        File myObj = new File(path);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                String temp = "";
                temp = myReader.nextLine();
                String[] arrData = temp.split("\\|");
                if ((arrData[0].equals(userName))) {
                } else {
                    data = data + temp;
                }
                if (myReader.hasNextLine()) {
                    data = data + "\n";
                }
            }
            FileWriter fw = new FileWriter(path, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateProductFileLine(int id, String str) {
        String path = "D:\\IdeaProjects\\Testing\\products.txt";
        File myObj = new File(path);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                String temp = "";
                temp = myReader.nextLine();
                String[] arrData = temp.split("\\|");
                if (Integer.parseInt(arrData[1]) == id) {
                    data = data + str;
                } else {
                    data = data + temp;
                }
                if (myReader.hasNextLine()) {
                    data = data + "\n";
                }
            }
            FileWriter fw = new FileWriter(path, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}