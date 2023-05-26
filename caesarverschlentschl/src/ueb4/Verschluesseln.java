package ueb4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Verschluesseln {


        //declaration von konstante, die nicht verändern können inzwischen das code
        public static final int NUMBER_LETTERS = 26;
        private static final char a = 'a';
        private static final char z = 'z';
        private static final char A = 'A';
        private static final char Z = 'Z';


        public static void main(String[] args) {

            // check arguments count
            // write a message to the user when there is few/many arguments!
            if (args.length < 3 || args.length > 3) {
                System.out.println("arguments = " + args.length);
                System.out.println("Too few/many arguments");
                System.exit(0);
            }
            // check types of arguments:
            // args[2] -> integer
            int versatz = 0;
            try {
                versatz = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Third argument should be of type integer!");
                System.exit(0);
            }
            // check the value of offset, it should be in the range [0, 25]
            if (versatz < 0 || versatz > 25) {
                System.out.println("Offset value is wrong!");
                System.exit(0);
            }
            // Nach der Validierung der Argumente ruft das Programm die encryptsmethode auf,
            //um die Verschlüsselung durchzuführen. Wenn die Datei nicht zum Lesen oder Schreiben gefunden werden konnte,
            //zeigt das Programm eine Fehlermeldung an und bricht ab.
            try {
                Verschluesseln.encrypt(args[0], args[1], versatz);
            } catch (IOException e) {
                System.out.println("file couldn't be found for reading/writing");
            }



            System.out.println("arguments = " + args.length);
            System.out.println("exit");


        }

        public static void encrypt(String originalFilePath, String outputFilePath, int versatz) throws IOException {
            //Der Code verwendet die Methode getAbsolutePath() der File-Klasse,
            //um den absoluten Pfad der durch originalFilePath angegebenen Klartextdatei abzurufen
            String originalPath = new File(originalFilePath).getAbsolutePath();
            //erstellt ein BufferedReader-Objekt mit dem Namen „reader“ und initialisiert es,
            //um mithilfe der FileReader-Klasse aus der durch „originalPath“ angegebenen Datei zu lesen.
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(originalPath));
            } catch (FileNotFoundException e) {
                // diagnose
                e.printStackTrace();
            }

            String encoded = "";
            try {
                String line = reader.readLine();
                System.out.println(line);

                int i = 0;
                while (i < line.length()) {
                    char newChar = (char) verschluesseln(line.charAt(i), versatz);
                    encoded += newChar;
                    i++;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // write the encoded string to the encryption file
            String outputPath = new File(outputFilePath).getAbsolutePath();
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(outputPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(encoded);
            printWriter.close();
            System.out.println(encoded);
        }



        /*
         * Diese Methode akzeptiert zwei Ganzzahlen als Eingabe: „zeichen“ und „versatz“.
           Es führt eine einfache Verschlüsselungsoperation für den Ganzzahlwert „zeichen“ durch.
           Wenn „zeichen“ zwischen den ASCII-Werten 97 (a) und 122 (z) einschließlich liegt, folgt die folgende Formel:
         */
        public static int verschluesseln(int zeichen, int versatz) {
            if ((zeichen >= a) && (zeichen <= z)) {
                return (((zeichen - a) + versatz) % NUMBER_LETTERS) + a;
            }
            if ((zeichen >= A) && (zeichen <= Z)) {
                return (((zeichen - A) + versatz) % NUMBER_LETTERS) + A;
            }
            return zeichen;
        }




    }

