package Task1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Fishers
{
    public Fishers() {
    }

    public static void main(String[] args)
    {
        Random generator = new Random();
        Scanner in = new Scanner(System.in);
        System.out.println("Привет. Задание про рыбаков. Вариант№1");
        System.out.println("Введите количество рыбаков");
        int fishersCount = in.nextInt();

        ArrayList<ArrayList> fishers = new ArrayList<>();

        String[] names = new String[] {"Павел", "Дмитрий", "Сергей", "Григорий", "Олег", "Алексей",
                "Владимир", "Виктор", "Роман", "Антон"};
        String[] fishes = new String[] {"Карась", "Бычок", "Окунь", "Лосось", "Щука", "Сом",
                "Форель", "Осетр", "Плотва", "Лещ", "Язь", "Белуга", "Пескарь", "Красноперка", "Стерлядь"};

        System.out.println("Рыбаки: ");

        for (int i = 0; i < fishersCount; i++)
        {
            fishers.add(new ArrayList<>());
            fishers.get(i).add(names[generator.nextInt(10)]);
            System.out.println(fishers.get(i).toString());
        }

        for (int i = 0; i < fishersCount; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                fishers.get(i).add(fishes[generator.nextInt(15)]);
            }
        }

        System.out.println("Рыба, которую поймали рыбаки: ");
        for (int i = 0; i < fishersCount; i++)
        {
            System.out.print((String)fishers.get(i).get(0) + " поймал: ");
            for (int j = 1; j < 7; j++)
                System.out.print((String) fishers.get(i).get(j) + " ");
            System.out.println("");
        }

        System.out.println("Что будем делать дальше?");
        System.out.println("1 - Посмотреть какая рыба есть у каждого рыбака");
        System.out.println("2 - Посмотреть какую рыбу не поймал ни один из рыбаков");
        System.out.println("0 - Выход");

        int answer = 0;
        for (answer = in.nextInt(); answer != 0; answer = in.nextInt())
        {
            if (answer == 1)
            {
                System.out.println("Рыба, которая есть у каждого рыбака: ");
                HashSet<String> _fishes = new HashSet<String>();
                _fishes.addAll(fishers.get(0));
                for (int i = 1; i < fishersCount; i++)
                {
                    _fishes.retainAll(fishers.get(i));
                }

                System.out.println(_fishes);
            }
            else
                if (answer == 2)
                {
                    System.out.print("Рыба, которую рыбаки ещё не поймали: ");
                    HashSet<String> notCatchedFishes = new HashSet<String>();
                    for (int i = 0; i < 15; i++)
                        notCatchedFishes.add(fishes[i]);
                    for (int i = 0; i < fishersCount; i++)
                        notCatchedFishes.removeAll(fishers.get(i));
                    System.out.println(notCatchedFishes);

                }
            System.out.println("Что будем делать дальше?");
            System.out.println("1 - Посмотреть какая рыба есть у каждого рыбака");
            System.out.println("2 - Посмотреть какую рыбу не поймал ни один из рыбаков");
            System.out.println("0 - Выход");
        }



    }

}