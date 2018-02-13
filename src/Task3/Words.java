package Task3;

/**
 * @author Pavel Omelchuk
 * @version 1.0
 * ��������������� �����. ����� ��� ������ � 3-� ��������. ������ ���� {@link Words#word} � {@link Words#wordcount}
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
     * @serial ����� �� ������
     */
    String word;
    /**
     * @serial ������� ����� � ������
     */
    int wordcount;

    /**
     * @serial ����� ��������� ����
     * @param s ����� ��� ���������
     */
 /*   public boolean equals(String s)
    {
        return s == word;
    }
    */

    /**
     * @serial ����� ����������, ��������� ������� � ������ �������
     * @param obj ������ ��� ���������
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
