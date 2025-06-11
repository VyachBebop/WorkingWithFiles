import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

//        taskOne();
//        taskTwo();
        taskThree();

    }

//    Задание 1:
//1. Пользователь вводит с клавиатуры путь к файлу.
//2. Программа считывает содержимое файла.
//3. Программа анализирует содержимое файла и подсчитывает количество букв, чисел и знаков препинания.
//4. Программа выводит на экран количество букв, чисел и знаков препинания в файле.
    public static void taskOne(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу: ");
        String path = scanner.nextLine();

        try {
            long letterCount = Files.lines(Paths.get(path))
                    .flatMapToInt(String::chars) // превращает строки в поток символов
                    .filter(Character::isLetter) //  только буквы
                    .count();
            long digitCount = Files.lines(Paths.get(path))
                    .flatMapToInt(String::chars) // превращает строки в поток символов
                    .filter(Character::isDigit) //  только цифры
                    .count();
            long punctuationCount = Files.lines(Paths.get(path))
                    .flatMapToInt(String::chars) // превращает строки в поток символов
                    .filter(ch -> Character.getType(ch) == Character.OTHER_PUNCTUATION) // только знаки препинания
                    .count();

            System.out.println("Количество букв: " + letterCount);
            System.out.println("Количество цифр: " + digitCount);
            System.out.println("Количество знаков препинания: " + punctuationCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    Задание 2:
// 1.	Пользователь с клавиатуры вводит путь к файлу, искомое слово и слово для замены.
// 2.	Программа открывает файл и ищет искомое слово в его содержимом.
// 3.	Если слово найдено, программа заменяет его на указанное слово.
// 4.	Программа подсчитывает количество произведенных замен.
// 5.	Программа выводит на экран отчет о количестве замен.
    public static void taskTwo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу: ");
        String path = scanner.nextLine();
        System.out.println("Введите искомое слово: ");
        String FindWord = scanner.nextLine();
        System.out.println("Введите слово для замены: ");
        String NewWord = scanner.nextLine();

        try {
            // Читаю содержимое файла
            String text = Files.readString(Path.of(path));
            System.out.println(text);

            // Считаю количество замен
            int count = 0;
            int index = 0;
            while ((index = text.indexOf(FindWord, index)) != -1) {
                count++;
                index += FindWord.length();
            }

            //  Заменяю все вхождения слова
            String updatedText = text.replace(FindWord, NewWord);

            //  Записываю обратно в файл
            Files.writeString(Paths.get(path), updatedText, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);

            //  Отчёт
            System.out.println("Количество замен: " + count);

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
    //    Задание 3:
// 1. Пользователь вводит с клавиатуры пути к четырем файлам.
// 2. Программа открывает каждый из четырех файлов и считывает их содержимое.
// 3. Программа записывает содержимое трех файлов в четвертый файл.
// 4. Программа подсчитывает количество перенесенных байт.
// 5. Программа выводит на экран отчет о количестве перенесенных байт.
    public static void taskThree() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к первому файлу: ");
        String pathOne = scanner.nextLine();
        List<String> one = new ArrayList<>(Files.readAllLines(Paths.get(pathOne)));

        System.out.println("Введите путь ко второму файлу: ");
        String pathTwo = scanner.nextLine();
        List<String> two = new ArrayList<>(Files.readAllLines(Paths.get(pathTwo)));

        System.out.println("Введите путь ко третьему файлу: ");
        String pathThree = scanner.nextLine();
        List<String> three = new ArrayList<>(Files.readAllLines(Paths.get(pathThree)));

        System.out.println("Введите путь к четвертому файлу: ");
        String pathFour = scanner.nextLine();

        // Объединяю
        List<String> allLines = new ArrayList<>();
        allLines.addAll(one);
        allLines.addAll(two);
        allLines.addAll(three);

        //Записываю в четвертый файл
        Files.write(Paths.get(pathFour), allLines);

        // Считаю байты
        long totalBytes = Files.size(Paths.get(pathOne)) + Files.size(Paths.get(pathTwo)) + Files.size(Paths.get(pathThree));
        System.out.println("Всего перенесено байт: " + totalBytes);

    }

}