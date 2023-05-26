package ueb4;


import java.io.IOException;


  public class Entschluesseln {

        public static void decrypt(String encryptedFilePath, String outputFilePath, int versatz) throws IOException {
            //ruft die Methode „encrypt“ aus der Klasse „Verschluesseln“ auf.
            //mit dem entgegengesetzten Verschiebungswert, um die code zu entschlüsseln
            Verschluesseln.encrypt(encryptedFilePath, outputFilePath, Verschluesseln.NUMBER_LETTERS - versatz);
        }
        //Diese Methode wird von der Methode „encrypt“ in der Klasse „Verschluesseln“
        //für jedes einzelne Zeichen in der output aufgerufen, um die eigentliche Dekodierung durchzuführen.
        public static int entschluesseln(int zeichen, int versatz) {
            //Subtrahieren Sie den Verschiebungswert von der Gesamtzahl der Buchstaben im Alphabet,
            //um den entgegengesetzten Verschiebungswert zu erhalten, der zum Dekodieren erforderlich ist.
            versatz = Verschluesseln.NUMBER_LETTERS - versatz;
            //gibt das codierte Zeichen minus des Verschiebungswerts zurück, um das decodierte Zeichen zu erhalten.
            return 0;
        }

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

            try {
                Entschluesseln.decrypt(args[0], args[1], versatz);
            } catch (IOException e) {
                System.out.println("file couldn't be found for reading/writing");
            }

            System.out.println("arguments = " + args.length);
            System.out.println("exit");
        }

    }

