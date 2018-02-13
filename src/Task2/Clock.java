package Task2;

public class Clock  implements Comparable {
    String brand;
    String type;
    int price;
    int count;
    Manufacturer manufacturer = new Manufacturer();

    Clock(String brand, String type, int price, int count, String title, String country)
    {
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.count = count;
        this.manufacturer.title = title;
        this.manufacturer.country = country;
    }

    public int compareTo(Object obj)
    {
        Clock tmp = (Clock)obj;
        if (this.price < tmp.price)
            return -1;
        else if (this.price > tmp.price)
            return 1;

        return 0;
    }
}
