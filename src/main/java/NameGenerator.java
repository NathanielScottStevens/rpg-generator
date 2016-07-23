import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NameGenerator {

    static ArrayList<String> maleFirstNames = new ArrayList<String>();
    static ArrayList<String> femaleFirstNames = new ArrayList<String>();
    static ArrayList<String> lastNames = new ArrayList<String>();

    static {
        initArray("male-first-names.txt", maleFirstNames);
        initArray("female-first-names.txt", femaleFirstNames);
        initArray("last-names.txt", lastNames);
    }

    static void initArray(String fileName, ArrayList<String> array){
        Path path = Paths.get("build/resources/main/" + fileName);

        try {
            Scanner scanner =  new Scanner(path);
            while (scanner.hasNextLine()){
                array.add(scanner.nextLine());
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    static int getRandom(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static String getFirstName(Sex sex){
        switch (sex){
            default:
            case Male:
                return maleFirstNames.get(getRandom(0, maleFirstNames.size()));
            case Female:
                return femaleFirstNames.get(getRandom(0, femaleFirstNames.size()));
        }
    }

    public static String getLastName() {
        return lastNames.get(getRandom(0, lastNames.size()));
    }

    public static String getFullName(Sex sex){
        String first = getFirstName(sex);
        String last = getLastName();
        return first + " " + last;
    }

    public static void main(String[] args){
        System.out.println(getFirstName(Sex.Male));
        System.out.println(getFirstName(Sex.Female));
        System.out.println(getLastName());
        System.out.println(getFullName(Sex.Male));
        System.out.println(getFullName(Sex.Female));
    }
}
