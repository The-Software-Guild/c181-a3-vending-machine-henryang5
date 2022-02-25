package vendingmachine.ui;

import java.util.Scanner;

public class UserIOConsoleImpl {
    Scanner sc;

    public UserIOConsoleImpl()
    {
        sc = new Scanner(System.in);
    }

    public void print(String message)
    {
        System.out.println(message);
    }

    public String readString(String prompt)
    {
        System.out.println(prompt);
        return sc.nextLine();
    }

    public int readInt(String prompt)
    {
        System.out.println(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    public int readInt(String prompt, int min, int max)
    {
        int num;
        do {
            System.out.println(prompt);
            num = Integer.parseInt(sc.nextLine());
        }
        while(num < min || num > max);

        return num;
    }

    public double readDouble(String prompt)
    {
        System.out.println(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    public double readDouble(String prompt, double min, double max)
    {
        double num;
        do {
            System.out.println(prompt);
            num = sc.nextInt();
        }
        while(num < min || num > max);

        return num;
    }

    public float readFloat(String prompt)
    {
        System.out.println(prompt);
        return sc.nextFloat();
    }

    public float readFloat(String prompt, float min, float max)
    {
        float num;
        do {
            System.out.println(prompt);
            num = sc.nextFloat();
        }
        while(num < min || num > max);

        return num;
    }

    public long readLong(String prompt)
    {
        System.out.println(prompt);
        return sc.nextLong();
    }

    public long readLong(String prompt, long min, long max)
    {
        long num;
        do {
            System.out.println(prompt);
            num = sc.nextLong();
        }
        while(num < min || num > max);

        return num;
    }
}
