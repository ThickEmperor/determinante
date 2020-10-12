/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luise
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String args[]) {
        int[][] matriz;

        try {
            try (BufferedReader buffer = new BufferedReader(new FileReader("\"C:\\Users\\luise\\Documents\\ULSA\\Tercer Semestre\\Estructura de datos\\Pruebas.txt\""))) 
            {
                String linea = buffer .readLine();
                int longitud = Integer.parseInt(linea);
                matriz = new int[longitud][longitud];
                System.out.println(longitud);
                linea = buffer.readLine();
                int fila = 0;
                while (linea != null) 
                {
                    String[] enteros = linea.split(" ");
                    for (int i = 0; i < enteros.length; i++) 
                    {
                        matriz[fila][i] = Integer.parseInt(enteros[i]);
                    }
                    fila++;
                    linea = buffer.readLine();
                }
            }
            imprimir_matriz(matriz);
            
            System.out.println(determin(matriz));
        } catch (FileNotFoundException e) 
        {
            System.out.println("No se encontró el archivo :(");
        } catch (NumberFormatException e) 
        {
            System.out.println("No se pudo convertir");
        } catch (IOException e) 
        {
            System.out.println("No se pudo acceder xd");
        }

    }

    public static void imprimir_matriz(int matriz[][]) 
    {
        int i, j;
        for (i = 0; i < matriz.length; i++) 
        {
            for (j = 0; j < matriz.length; j++)
            {
                System.out.print(String.valueOf(matriz[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] submatrize(int matriz[][], int x, int y) 
    {
        int submatriz[][] = new int[matriz.length - 1][matriz.length - 1];
        int i, j;
        int var_x = 0, var_y = 0;
        for (i = 0; i < matriz.length; i++) {
            if (i != x) {
                var_y = 0;
                for (j = 0; j < matriz.length; j++) 
                {
                    if (j != y) {
                        submatriz[var_x][var_y] = matriz[i][j];
                        var_y++;
                    }
                } var_x++;
            }
        } return submatriz;
    }

    public static int determin(int matriz[][]) 
    {
        int determini = 0;
        int i, mult = 1;
        if (matriz.length > 2) {
            for (i = 0; i < matriz.length; i++) 
            {
                determini += mult * matriz[i][0] * determin(submatrize(matriz, i, 0));
                mult *= -1;
            }
            return determini;
        } else {
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        }
    }

}
