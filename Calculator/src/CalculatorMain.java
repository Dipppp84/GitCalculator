import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;



/*
Строчный калькулятор, разбивает цифры и операции и выполняет пошагово решение согласно правилам арифметики
Добавить деление
         приоритет операциям в скобках
         распознавание отрицательных цифр
         вывод каждого этапа в консоль
 */

public class CalculatorMain {
    public static void main(String[] args) {
        System.out.println(calculator("5+1 *8 +1 ="));

    }

    public static String calculator(String result) {
        //Форматирование строки, удаление пробелов и знака =
        String str = result.replaceAll("\\s+", "").replaceAll("=", "");

        String[] massOperation = str.split("\\d+");
        ArrayList<String> listOperation = new ArrayList<>(Arrays.asList(massOperation)); //добавляем операции в списочный массив

        String[] massNumber = str.split("\\W");
        ArrayList<Double> listNumber = new ArrayList<>();
        for (String s : massNumber)                   //Добавляем и парсим массив цифр
            listNumber.add(Double.parseDouble(s));  //Парсим в доубл

        // Умножение и деление
        for (int i = 1; i < listOperation.size(); )
            if (listOperation.get(i).equals("*")) {
                listNumber.set(i - 1, listNumber.get(i - 1) * listNumber.get(i));
                listNumber.remove(i);
                listOperation.remove(i);
            } else
                i++;

        // Сложение и вычтание
        for (int i = 1; i < listOperation.size(); ) {
            if (listOperation.get(i).equals("+") || listOperation.get(i).equals("-")) {
                if (listOperation.get(i).equals("+")) {
                    listNumber.set(i - 1, listNumber.get(i - 1) + listNumber.get(i));
                    listNumber.remove(i);
                    listOperation.remove(i);
                } else if (listOperation.get(i).equals("-")) {
                    listNumber.set(i - 1, (listNumber.get(i - 1) - listNumber.get(i)));
                    listNumber.remove(i);
                    listOperation.remove(i);
                } else
                    i++;
            }
        }

        // не выводить остаток если ответ целочисленый
        DecimalFormat format = new DecimalFormat();
        format.setDecimalSeparatorAlwaysShown(false);
        //String answer = result + "\nAnswer: " + (format.format(listNumber.get(0)) );
        return result + "\nAnswer: " + (format.format(listNumber.get(0)) );
    }
}
