package ueb4;

import java.io.*;
import java.util.*;

    public class SeedPicker {

        private static final int NUM_WORDS_SEED_FILE = 24;

        private static final int NUM_WORDS_SEED_PHRASE = 12;



        public static String[] createSeedPhrase(File file) throws IOException {

            String[] seedWords = readSeedFile(file);

            String[] seedPhrase = generateRandomSeedPhrase(seedWords);

            return seedPhrase;

        }



        private static String[] readSeedFile(File file) throws IOException {

            String[] seedWords = new String[NUM_WORDS_SEED_FILE];

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            int index = 0;

            while ((line = reader.readLine()) != null && index < NUM_WORDS_SEED_FILE) {

                seedWords[index] = line.trim();

                index++;

            }

            reader.close();

            return seedWords;

        }



        private static String[] generateRandomSeedPhrase(String[] seedWords) {

            Random rand = new Random();

            String[] seedPhrase = new String[NUM_WORDS_SEED_PHRASE];

            int count = 0;

            while (count < NUM_WORDS_SEED_PHRASE) {

                int randomIndex = rand.nextInt(NUM_WORDS_SEED_FILE);

                String word = seedWords[randomIndex];

                if (!containsWord(seedPhrase, word)) {

                    seedPhrase[count] = word;

                    count++;

                }

            }

            return seedPhrase;

        }



        private static boolean containsWord(String[] seedPhrase, String word) {

            for (String existingWord : seedPhrase) {

                if (existingWord != null && existingWord.equals(word)) {

                    return true;

                }

            }

            return false;

        }



        public static String[] reverseSeedPhraseContent(String[] seedPhrase) {

            String[] reversedPhrase = new String[NUM_WORDS_SEED_PHRASE];

            for (int i = 0; i < NUM_WORDS_SEED_PHRASE; i++) {

                String word = seedPhrase[i];

                String reversedWord = reverseWord(word);

                reversedPhrase[i] = reversedWord;

            }

            return reversedPhrase;

        }



        private static String reverseWord(String word) {

            StringBuilder reversedWord = new StringBuilder();

            for (int i = word.length() - 1; i >= 0; i--) {

                reversedWord.append(word.charAt(i));

            }

            return reversedWord.toString();

        }



        public static void main(String[] args) {

            File seedFile = new File("SuperSecureSeed.txt");

            File outputFile = new File("ColdWallet/DefNotHckable.txt");



            try {

                String[] seedPhrase = createSeedPhrase(seedFile);

                String[] reversedPhrase = reverseSeedPhraseContent(seedPhrase);

                saveSeedPhraseToFile(outputFile, reversedPhrase);

                System.out.println("Seed-Phrase successfully created and saved to " + outputFile.getPath());

            } catch (IOException e) {

                System.out.println("Error reading the seed file: " + e.getMessage());

            }

        }



        private static void saveSeedPhraseToFile(File file, String[] seedPhrase) throws IOException {

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (String word : seedPhrase) {

                writer.write(word);

                writer.newLine();

            }

            writer.close();

        }

    }

