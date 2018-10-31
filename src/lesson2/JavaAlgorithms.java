package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String firs, String second) {
        int max = 0;
        int maxI = 0;
        int[][] matrix = new int[firs.length()][second.length()];
        for (int i = 0; i <= firs.length() - 1; i++) {
            for (int j = 0; j <= second.length() - 1; j++) {
                matrix[i][j] = 0;
            }
        }
        for (int i = 0; i <= firs.length() - 1; i++) {
            for (int j = 0; j <= second.length() - 1; j++) {
                if (firs.charAt(i) == second.charAt(j)) {
                    matrix[i][j]++;
                    if (i != 0 && j != 0 && matrix[i - 1][j - 1] != 0)
                        matrix[i][j] += matrix[i - 1][j - 1];
                }
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    maxI = i;
                }
            }
        }
        if (max == 0)
            return "";
        maxI = maxI - (max - 1);
        char[] ansConstractor = new char[max];
        int a = 0;
        while (max >= 1) {
            ansConstractor[a] = firs.charAt(maxI);
            a++;
            maxI++;
            max--;
        }
        String ans = new String(ansConstractor);
        return ans;
    }//O(m*n)

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    public static List<String[]> field = new ArrayList();

    public static Set<String> baldaSearcher(String inputName, Set<String> words) throws IOException {
        Reader rd = new FileReader(inputName);
        Set<String> ans = new HashSet<>();
        BufferedReader br = new BufferedReader(rd);
        String localStr;
        while (true) {
            localStr = br.readLine();
            if (localStr == null)
                break;
            String[] line = localStr.split(" ");
            field.add(line);
        }
        for (int i = 0; i <= field.size() - 1; i++ ) {
            for (int j = 0; j <= field.get(0).length - 1; j++) {
                for (String s: words) {
                    char ch = s.charAt(0);
                    if (Character.toString(ch).equals( field.get(i)[j])) {
                        if (findWord(i, j, s)) {
                            ans.add(s);
                        }
                    }
                }
                if (ans == words)
                    break;
            }
        }
        return ans;
    }
    public static ArrayList<ArrayList<Integer>> lettersNear(ArrayList<ArrayList<Integer>> ar, String l) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (ArrayList<Integer> c: ar) {
            int a = c.get(0);
            int b = c.get(1);
            if (a != 0) {
                if (field.get(a - 1)[b].equals(l))
                    ans.add(new ArrayList<>(Arrays.asList(a - 1, b)));
            }
            if (b != field.get(0).length - 1) {
                if (field.get(a)[b + 1].equals(l))
                    ans.add(new ArrayList<>(Arrays.asList(a, b + 1)));
            }
            if (a != field.size() - 1) {
                if (field.get(a + 1)[b].equals(l))
                    ans.add(new ArrayList<>(Arrays.asList(a + 1, b)));
            }
            if (b != 0) {
                if (field.get(a)[b - 1].equals(l))
                    ans.add(new ArrayList<>(Arrays.asList(a, b - 1)));
            }
        }
        return ans;
    }
    public static boolean findWord (int a, int b, String w) {
        ArrayList<ArrayList<Integer>> currentArray = new ArrayList<>();
        ArrayList<Integer> firstContain = new ArrayList<>();
        firstContain.add(a);
        firstContain.add(b);
        currentArray.add(firstContain);
        boolean stop = false;
        int letter = 1;
        while (!stop) {
            String ch = Character.toString(w.charAt(letter));
            currentArray = lettersNear(currentArray,ch);
            if (currentArray.size() == 0)
                stop = true;
            else letter++;
            if (letter == w.length())
                stop = true;
        }
            return letter == w.length();
    }
} //O(n^2)
