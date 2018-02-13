package Task2;

import java.util.*;
import java.io.*;

public class ClockStore
{
    public static void main(String[] args)
    {
        System.out.println("Здравствуйте! Вас приветствует магазин часов. Что бы вы хотели узнать?");
        System.out.println("1 - Показать все марки кварцевых часов");
        System.out.println("2 - Показать все марки механических часов");
        System.out.println("3 - Показать информацию о механических часах, цена на которые не превышает заданную сумму");
        System.out.println("4 - Показать марки часов изготовленных в заданной стране");
        System.out.println("5 - Показать производителей, общая стоимость которых в магазине не превышает заданную сумму");
        System.out.println("6 - Сохранить в файл");
        System.out.println("0 - Выход");

        String filename = "Clocks.txt";
        Scanner in = new Scanner(System.in);
        List<Clock> clocks = new ArrayList<>();
        try
        {
            Scanner freader = new Scanner(new File(filename));
            while (freader.hasNext())
            {
                clocks.add(new Clock(freader.nextLine(), freader.nextLine(), Integer.parseInt(freader.nextLine()), Integer.parseInt(freader.nextLine()), freader.nextLine(), freader.nextLine()));
                freader.nextLine();
            }
            freader.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        for(int answer = in.nextInt(); answer != 0; answer = in.nextInt())
        {
            if (answer == 1)
            {
                String _type;
                Set<Clock> quartzSet = new HashSet<>();
                quartzSet.addAll(clocks);

                Iterator<Clock> itrbrand = quartzSet.iterator();
                Iterator<Clock> itr = quartzSet.iterator();
                while(itr.hasNext())
                {
                    _type = itr.next().type;
                    if (_type.equals("кварцевые"))
                        System.out.println(itrbrand.next().brand);
                    else
                        itrbrand.next();
                }
            }
            if (answer == 2)
            {
                String _type;
                Set<Clock> mechSet = new HashSet<>();
                mechSet.addAll(clocks);

                Iterator<Clock> itrbrand = mechSet.iterator();
                Iterator<Clock> itr = mechSet.iterator();
                while(itr.hasNext())
                {
                    _type = itr.next().type;
                    if (_type.equals("механические"))
                        System.out.println(itrbrand.next().brand);
                    else
                        itrbrand.next();
                }
            }
            if (answer == 3)
            {
                TreeSet<Clock> tree = new TreeSet<Clock>();
                //tree.addAll(clocks);
                for (int i = 0; i < clocks.size(); i++)
                    tree.add(clocks.get(i));

                System.out.println("Введите предельную сумму для товаров:");
                double limit;
                limit = in.nextDouble();
                System.out.println("Механические часы, цена на которые не превышает заданную сумму:");
                Iterator<Clock> itr = tree.iterator();
                Iterator<Clock> priceItr = tree.iterator();
                Iterator<Clock> typeItr = tree.iterator();
                while(priceItr.next().price <= limit)
                    if (typeItr.next().type.equals("механические"))
                        System.out.println(itr.next().brand);
                    else
                        itr.next();
            }
            if (answer == 4)
            {
                System.out.println("Введите страну, товары которой вы хотите знать:");
                String _country;
                _country = in.next();

                System.out.println("Товары из заданной страны:");
                HashMap<String, String> map = new HashMap<>();
                for (int i = 0; i < clocks.size(); i++)
                    map.put(clocks.get(i).brand, clocks.get(i).manufacturer.country);
                Set<Map.Entry<String, String>> eSet = map.entrySet();
                for (Map.Entry<String, String> pair : eSet)
                {
                    if (pair.getValue().equals(_country))
                        System.out.println(pair.getKey());
                }

            }
            if (answer == 5)
            {
                System.out.println("Введите предельную суммарную сумму для товаров производителя:");
                int limit;
                int sum;
                limit = in.nextInt();
                Map sortedSum = new TreeMap();

                for (Clock clock : clocks)
                {
                    if (sortedSum.containsKey(clock.manufacturer.title))
                        sortedSum.put(clock.manufacturer.title, (int)sortedSum.get(clock.manufacturer.title) + clock.price);
                    sortedSum.put(clock.manufacturer.title, clock.price);
                }

                Map newSortedSum;
                newSortedSum = sortByValues(sortedSum);


                Set<Map.Entry<String, Integer>> eSet = newSortedSum.entrySet();
                for (Map.Entry<String, Integer> pair : eSet)
                {
                    if (pair.getValue() < limit)
                        System.out.println(pair.getKey());
                }

            }
            if (answer == 6)
            {
                System.out.println("Запишем базу товаров в текстовый файл");
                System.out.println("Введите путь к файлу, в который будут записаны данные");

                String saveFileName;
                saveFileName = in.next();

                File file = new File(saveFileName);

                try
                {
                    if (!file.exists())
                        file.createNewFile();
                    else
                    {
                        file.delete();
                        file.createNewFile();
                    }
                    PrintWriter writer = new PrintWriter(file.getAbsoluteFile());

                    try
                    {
                        for (int i = 0; i < clocks.size(); i++)
                        {
                            writer.println(clocks.get(i).brand);
                            writer.println(clocks.get(i).type);
                            writer.println(clocks.get(i).price);
                            writer.println(clocks.get(i).count);
                            writer.println(clocks.get(i).manufacturer.title);
                            writer.println(clocks.get(i).manufacturer.country);
                            writer.println("");
                        }
                    }
                    finally
                    {
                        writer.close();
                    }
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }

            }

            System.out.println("Что-то ещё?");
        }
        in.close();
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
    Comparator<K> valueComparator = new Comparator<K>() {
        @Override
        public int compare(K k1, K k2) {
            int compare = (-1) * map.get(k2).compareTo(map.get(k1));
            if (compare == 0)
                return 1;
            else
                return compare;
        }
    };

    Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
    sortedByValues.putAll(map);

    return sortedByValues;
}
}
