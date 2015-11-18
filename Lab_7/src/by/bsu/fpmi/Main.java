package by.bsu.fpmi;
public class Main {
    public static double sumOfRow(double x, double e) {
        if (e < 0)
        {
            e *= (-1);
        }
        double a = 1./6;
        double sum = a;
        int k = 2;
        while(Math.abs(a) > e)
        {
            a *= x*k/(k+2);
            sum += a;
            k++;
        }
        return sum;
    }

    public static void main(String[] args) {
        double x;
        double e;
        try
        {
            if (args.length != 2)
            {
                throw new IllegalArgumentException();
            }
            x = Double.parseDouble(args[0]);
            e = Double.parseDouble(args[1]);
            //NumberFormatException exept = new Exception();
        }
        catch(NumberFormatException exept)
        {
            System.out.println("NumberFormatException!");
            return;
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println("IllegalArgumentException!");
            return;
        }
        double sum = Main.sumOfRow(x, e);
        System.out.println(sum);
    }
}
