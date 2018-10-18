package lesson1;

import kotlin.NotImplementedError;
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     *
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        Reader rd = new FileReader(inputName);
        ArrayList<Double> tmp = new ArrayList<Double>();
        BufferedReader br = new BufferedReader(rd);
        String localStr;
        while (true) {
            localStr = br.readLine();
            if (localStr == null)
                break;
            tmp.add(Double.valueOf(localStr));
        }
        Collections.sort(tmp);
        FileWriter writer = new FileWriter(new File(outputName));
        for (int i = 0; i <= tmp.size() - 1; i++) {
            if (i == tmp.size() - 1)
                writer.write(tmp.get(i).toString());
            else writer.write(tmp.get(i).toString() + "\n");
        }
        writer.close();
    }
    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        int max = 0;
        Integer num = null;
        HashMap<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        ArrayList<Integer> numList = new ArrayList<Integer>();
        Reader rd = new FileReader(inputName);
        BufferedReader br = new BufferedReader(rd);
        String localStr;
        while (true) {
            localStr = br.readLine();
            if (localStr == null)
                break;
            arr.add(Integer.valueOf(localStr));
        }
        for (int i = 0; i <= arr.size() - 1; i++ ) {
            if (!frequency.containsKey(arr.get(i))) {
                frequency.put(arr.get(i), 1);
                numList.add(arr.get(i));
            }
            else {
                Integer obj = frequency.get(arr.get(i));
                frequency.put(arr.get(i),obj + 1);
            }
        }
        for (int i = 0; i <= numList.size() - 1; i++) {
            if (frequency.get(numList.get(i)) > max) {
                max = frequency.get(numList.get(i));
                num = numList.get(i);
            }
            if (frequency.get(numList.get(i)).equals(max)) {
                if (numList.get(i) < num) {
                    max = frequency.get(numList.get(i));
                    num = numList.get(i);
                }
            }
        }
        arr.removeAll(Collections.singleton(num));
        while (max != 0) {
            arr.add(num);
            max--;
        }
        FileWriter writer = new FileWriter(new File(outputName));
        for (int i = 0; i <= arr.size() - 1; i++) {
            if (i == arr.size() - 1)
                writer.write(arr.get(i).toString());
            else writer.write(arr.get(i).toString() + "\n");
        }
        writer.close();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
