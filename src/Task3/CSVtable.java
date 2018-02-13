package Task3;

import java.util.*;
import java.io.*;

/**
 * @author Pavel Omelchuk
 * @version 1.0
 * Третее задание. Класс используемый для создания CSV таблицы с данными о частоте слов в тексте
 */
public class CSVtable
{
    public static void main(String[] args)
    {
        System.out.println("Привет, давайте простроим CSV таблицу.");
        System.out.println("Введите путь к файлу с исходным текстом:");

        Scanner in = new Scanner(System.in);

        String filename;
        boolean flag = false;

        while (!flag)
        {
            filename = in.next();
            File file = new File(filename);
            if (file.exists())
            {
                try
                {
                    Scanner freader = new Scanner(new File(filename));
                    Map<String, Integer> wordsMap = new TreeMap<>();
                    String text = "";
                    String word = "";
                    int wordsInText = 0;

                    while (freader.hasNext())
                        text += freader.nextLine();
                    freader.close();

                    for (int i = 0; i < text.length(); i++)
                    {
                        if (Character.isLetterOrDigit(text.charAt(i)))
                        {
                            word += text.charAt(i);
                            i++;
                            while (Character.isLetterOrDigit(text.charAt(i)))
                            {
                                word += text.charAt(i);
                                i++;
                            }
                            wordsInText++;

                            if (wordsMap.containsKey(word))
                                wordsMap.put(word, wordsMap.get(word) + 1);
                            else
                                wordsMap.put(word, 1);
                        }
                        word = "";
                    }

                    List list = new ArrayList(wordsMap.entrySet());
                    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
                    {
                       @Override // совпадения -1 + 1
                       public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b)
                       {
                           if (b.getValue() - a.getValue() == 0)
                               return -1;
                           return b.getValue() - a.getValue();
                       }
                    });
                    System.out.println("CSV таблица:");
                    for (int i = 0; i < list.size(); i++)
                        CSVTable(list.get(i).toString(), wordsInText);
                }
                catch(IOException e)
                {
                    throw new RuntimeException(e);
                }
                flag = true;
            }
            else
            {
                System.out.println("Такого файла не существует, повторите попытку.");
                flag = false;
            }
        }
    }
    public static void CSVTable(String str, int wordsInTheText)
    {
        String key = "";
        String value = "";
        int i = 0;

        while (str.charAt(i) != '=')
        {
            key += str.charAt(i);
            i++;
        }
        i++;
        while (i < str.length() && (Character.isLetterOrDigit(str.charAt(i))))
        {
            value += str.charAt(i);
            i++;
        }
        System.out.print(key);
        System.out.print("; ");
        System.out.print(value);
        System.out.print("; ");
        System.out.print((double) Integer.parseInt(value) / wordsInTheText * 100 + " %;");
        System.out.println("");
    }

}
