package src;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.sh0nk.matplotlib4j.Plot.create;

public class Main {
    static double f = 4.0;
    static double T = 1.0;
    static double F = (7 * Math.PI) / 9;
    static int fs = 100;

    public static void main(String[] args) throws IOException, PythonExecutionException {

        List<Double> t = NumpyUtils.linspace(0, T, fs);

        Plot plt1 = create();
        plt1.plot()
                .add(functionXN(t))
                .label("label")
                .linestyle("-");
        plt1.xlabel("xlabel");
        plt1.ylabel("ylabel");
        plt1.text(0.5, 0.2, "text");
        plt1.title("Zadanie nr 1");
        plt1.legend();
        plt1.show();

        Plot plt2 = create();
        plt2.plot()
                .add(functionZN(t))
                .label("label")
                .linestyle("-");
        plt2.xlabel("xlabel");
        plt2.ylabel("ylabel");
        plt2.text(0.5, 0.2, "text");
        plt2.title("Zadanie nr 2.a");
        plt2.legend();
        plt2.show();

        Plot plt3 = create();
        plt3.plot()
                .add(functionVN(t))
                .label("label")
                .linestyle("-");
        plt3.xlabel("xlabel");
        plt3.ylabel("ylabel");
        plt3.text(0.5, 0.2, "text");
        plt3.title("Zadanie nr 2.b");
        plt3.legend();
        plt3.show();

        T = 3.0;
        fs = 1200;
        t = NumpyUtils.linspace(0, T, (int) T * fs);

        Plot plt4 = create();
        plt4.plot()
                .add(functionUN(t))
                .label("label")
                .linestyle("-");
        plt4.xlabel("xlabel");
        plt4.ylabel("ylabel");
        plt4.text(0.5, 0.2, "text");
        plt4.title("Zadanie nr 3");
        plt4.legend();
        plt4.show();


        T = 4.0;
        fs = 10000;
        int H = 2;
        t = NumpyUtils.linspace(0, T, fs);

        Plot plt5 = create();
        plt5.plot()
                .add(functionGN(t, H))
                .label("label")
                .linestyle("-");
        plt5.xlabel("xlabel");
        plt5.ylabel("ylabel");
        plt5.text(0.5, 0.2, "text");
        plt5.title("Zadanie nr 4");
        plt5.legend();
        plt5.show();
        System.out.println(t);
    }

    private static List<Double> functionXN(List<Double> n) {
        //0.7 * asin(2 * pi * f * n / fs + F) * n

        List<Double> results = new ArrayList<>();
        for (Double ni : n) {
            results.add(0.7 * Math.sin(2 * Math.PI * f * ni / fs + F) * ni);
        }
        return results;
    }

    private static List<Double> functionYN(List<Double> n) {
        //(x(n) + 1) / (x(n) + 10)

        List<Double> results = new ArrayList<>();
        List<Double> resultsFromX = functionXN(n);
        for (Double aResultsFromX : resultsFromX) {
            results.add((aResultsFromX + 1) / (aResultsFromX + 10));
        }

        return results;
    }

    private static List<Double> functionZN(List<Double> n) {
        //y(n) * abs(x(n) ** 0.333)
        List<Double> results = new ArrayList<>();
        List<Double> resultsFromX = functionXN(n);
        List<Double> resultsFromY = functionYN(n);
        for (int i = 0; i < resultsFromX.size(); i++) {
            results.add(resultsFromY.get(i) * Math.pow(Math.abs(resultsFromX.get(i)), 0.333));
        }

        return results;
    }

    private static  List<Double> functionVN(List<Double> n) {
        //3 * x(n) + y(n) * (abs(x(n)) + 1.78)
        List<Double> results = new ArrayList<>();
        List<Double> resultsFromX = functionXN(n);
        List<Double> resultsFromY = functionYN(n);
        for (int i = 0; i < resultsFromX.size(); i++) {
            results.add(3 * resultsFromX.get(i) + resultsFromY.get(i) * (Math.abs(resultsFromX.get(i)) + 1.78));
        }

        return results;
    }

    private static List<Double> functionUN(List<Double> n) {
        List<Double> results = new ArrayList<>();
        for (Double Ni : n) {
            if (Ni < 0.2 && Ni >= 0)
                results.add(0.8 * Math.sin(20 * Math.PI * Ni));
            else if (Ni < 0.4 && Ni >= 0.2)
                results.add(Math.pow(Math.E, Ni - 0.2) * 0.8 * Math.sin(20 * Math.PI * Ni));
            else if (Ni < 0.6 && Ni >= 0.4)
                results.add(0.6 * Math.sin(10 * Math.PI * Ni));
            else if (Ni < 0.8 && Ni >= 0.6)
                results.add(Math.pow(Math.E, Ni - 0.6) * 0.6 * Math.sin(10 * Math.PI * Ni));
            else if (Ni < 1.0 && Ni >= 0.8)
                results.add((Math.log(0.7 * Ni)/Math.log(2)) * 0.5 * Math.sin(40 * Math.PI * Ni));
        }

        return results;
    }

    private static List<Double> functionGN(List<Double> t, int H) {

        List<Double> results = new ArrayList<>();
        double sum = 0;
        for (Double Ti : t) {
            for (int n = 1; n < H; n++) {
                sum += Math.sin((n * Ti * Math.PI)/2) * Math.sin(20 * n * Math.PI * Ti);
            }
            results.add(sum * (9 / Math.pow(Math.PI, 2)));
            sum = 0;
        }

        return results;
    }
}
