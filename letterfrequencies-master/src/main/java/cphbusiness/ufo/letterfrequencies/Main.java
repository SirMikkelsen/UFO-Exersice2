package cphbusiness.ufo.letterfrequencies;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;
import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String fileName = "/Users/Gordon/Desktop/letterfrequencies-master/src/main/resources/FoundationSeries.txt";
        Long firstRunTime =   System.currentTimeMillis();
        Reader reader = new FileReader(fileName);
        BufferedReader reader2 = new BufferedReader(reader);
        Map<Integer, Long> freq = new HashMap<>();
        tallyChars(reader2, freq);
        print_tally(freq);


        Long secondRunTime =  System.currentTimeMillis();
        System.out.println("First runtime " + firstRunTime + " milliseconds");
        System.out.println("Second runtime "+ secondRunTime + " milliseconds");
        Long difference = secondRunTime - firstRunTime;
        System.out.println("difference  "+ difference);

    }

    private static void tallyChars(Reader reader, Map<Integer, Long> freq) throws IOException {


        int b;
        while ((b = reader.read()) != -1) {
            try
            {
                freq.put(b, freq.get(b) + 1);
            }
            catch (NullPointerException np) {
                freq.put(b, 1L);
            };
            /*
            if (freq.containsKey(b)){

                freq.put(b, freq.get(b) + 1);
            } else  {
                freq.put(b, 1L);
            };
             */
        }



        /*int count = 0;
        for (int i = 97;  i <= 122; i++) {
            String fileName = "/Users/Gordon/Desktop/letterfrequencies-master/src/main/resources/FoundationSeries.txt";
            //   String fileName = "/Users/kasper/GITHUB/ufo/letterfrequencies/src/main/resources/FoundationSeries.txt";
            Reader reader = new FileReader(fileName);
            while ((b = reader.read()) != -1) {
                if (b == i ){
                    count++;
                 //   freq.put(b, freq.get(b) + 1);
                }
            }
            Long countFinal = Long.valueOf(count);
            freq.put(i, countFinal);
            count = 0;
        }

         */
    }

    private static void print_tally(Map<Integer, Long> freq) {
        int dist = 'a' - 'A';
        Map<Character, Long> upperAndlower = new LinkedHashMap();
        for (Character c = 'A'; c <= 'Z'; c++) {
            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
        }
        Map<Character, Long> sorted = upperAndlower
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Character c : sorted.keySet()) {
            System.out.println("" + c + ": " + sorted.get(c));;
        }
    }
}
