import java.io.*;
import java.util.*;

public class Externalsort {
    public static void main(String[] args) throws Exception {
        String entrada = "datos.txt";
        String salida = "ordenado.txt";

        
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(entrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                numeros.add(Integer.parseInt(linea));
            }
        }

        int mitad = numeros.size() / 2;
        List<Integer> parte1 = new ArrayList<>(numeros.subList(0, mitad));
        List<Integer> parte2 = new ArrayList<>(numeros.subList(mitad, numeros.size()));

       
        Collections.sort(parte1);
        Collections.sort(parte2);
        guardarArchivo(parte1, "run1.txt");
        guardarArchivo(parte2, "run2.txt");

        
        fusionar("run1.txt", "run2.txt", salida);

        System.out.println("Archivo ordenado generado en: " + salida);
    }

    private static void guardarArchivo(List<Integer> lista, String nombre) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
            for (int num : lista) {
                bw.write(num + "\n");
            }
        }
    }

    private static void fusionar(String f1, String f2, String salida) throws IOException {
        try (BufferedReader br1 = new BufferedReader(new FileReader(f1));
             BufferedReader br2 = new BufferedReader(new FileReader(f2));
             BufferedWriter bw = new BufferedWriter(new FileWriter(salida))) {

            String l1 = br1.readLine();
            String l2 = br2.readLine();

            while (l1 != null && l2 != null) {
                int n1 = Integer.parseInt(l1);
                int n2 = Integer.parseInt(l2);

                if (n1 <= n2) {
                    bw.write(n1 + "\n");
                    l1 = br1.readLine();
                } else {
                    bw.write(n2 + "\n");
                    l2 = br2.readLine();
                }
            }

            
            while (l1 != null) {
                bw.write(l1 + "\n");
                l1 = br1.readLine();
            }
            while (l2 != null) {
                bw.write(l2 + "\n");
                l2 = br2.readLine();
            }
        }
    }
}
