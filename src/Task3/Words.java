package Task3;

/**
 * @author Pavel Omelchuk
 * @version 1.0
 * Вспомагательный класс. Класс для работы с 3-м заданием. Хранит поля {@link Words#word} и {@link Words#wordcount}
 */
public class Words implements Comparable
{
    Words()
    {
        word = "";
        wordcount = 0;
    }
    Words(String s)
    {
        word = s;
        wordcount = 1;
    }
    /**
     * @serial Слово из текста
     */
    String word;
    /**
     * @serial Частота слова в тексте
     */
    int wordcount;

    /**
     * @serial Метод сравнения слов
     * @param s Слово для сравнения
     */
 /*   public boolean equals(String s)
    {
        return s == word;
    }
    */

    /**
     * @serial Метод сортировки, сортирует объекты в нужном порядке
     * @param obj объект для сравнения
     */
    public int compareTo(Object obj)
    {
        Words tmp = (Words)obj;
        if (this.wordcount < tmp.wordcount)
            return 1;
        else if (this.wordcount > tmp.wordcount)
            return -1;

        return 0;

    }
}
