/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.adaentrega1;

import static java.lang.StrictMath.random;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author roger
 */
public class AdaEntrega1 {

    public static void main(String[] args) {
        int contador;
        int v[];
        Random random = new Random();
        int asignaciones1[] = new int[10];
        int asignaciones2[] = new int[10];
        int asignaciones3[] = new int[10];

        int comparaciones1[] = new int[10];
        int comparaciones2[] = new int[10];
        int comparaciones3[] = new int[10];

        long tiempo1[] = new long[10];
        long tiempo2[] = new long[10];
        long tiempo3[] = new long[10];
        for (contador = 0; 10 > contador; contador++) {
            v = new int[(contador + 1) * 10000];
            int v1[], v2[], v3[], asignaciones=0, comparaciones=0;
            v1 = v.clone();
            v2 = v.clone();
            v3 = v.clone();

            aleatorio(v, 10000 * (contador + 1), random);
            
            //primera funcion 
            Instant start = Instant.now();
            ordena1(v1, 10000 * (contador + 1), comparaciones, asignaciones);
            Instant finish = Instant.now();
            asignaciones1[contador]=asignaciones;
            comparaciones1[contador]=comparaciones;
            tiempo1[contador] = Duration.between(start, finish).toNanos();
            //------------------------
            
            //segunda funcion ---
            
            
            //---
            
            // tercera funcion ---
            
            //------
            
            
            
        }
        
        generarCSV( comparaciones1, asignaciones1, tiempo1, "ordena1.csv");
        generarCSV( comparaciones1, asignaciones1, tiempo1, "ordena2.csv");
        generarCSV( comparaciones1, asignaciones1, tiempo1, "ordena3.csv");
    }

    public static void ordena1(int v[], int tam, int comparaciones, int asignaciones) {
        int i, j, temp;
        i = 1;
        j = 2;
        while (i < tam) {
            if (v[i - 1] <= v[i]) {
                i = j;
                j = j + 1;
            } else {
                temp = v[i - 1];
                v[i - 1] = v[i];
                v[i] = temp;
                i = i - 1;
                if (i == 0) {
                    i = 1;
                }
            }
        }
    }

    public void ordena2(int[] v, int tam) {
        int k;
        int n = tam;
        for (k = n / 2; k >= 1; k--) {
            f(v, k, n);
        }
        k = n;
        while (k > 1) {
            g(v, 1, k--);
            f(v, 1, k);
        }
    }

    private void f(int[] v, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if ((j < n) && (v[j - 1] < v[j])) {
                j++;
            }
            if (v[k - 1] >= v[j - 1]) {
                break;
            }
            g(v, k, j);
            k = j;
        }
    }

    private void g(int[] v, int i, int j) {
        int temp = v[i - 1];
        v[i - 1] = v[j - 1];
        v[j - 1] = temp;
    }

    public void ordena3(int[] v, int tam) {
        int m = h(v, tam);
        int[] c = new int[m + 1];
        int[] w = new int[tam];
        for (int i = 0; i < tam; i++) {
            c[v[i]] = c[v[i]] + 1;
        }
        for (int i = 1; i < m + 1; i++) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = tam - 1; i >= 0; i--) {
            w[c[v[i]] - 1] = v[i];
            c[v[i]] = c[v[i]] - 1;
        }
        for (int i = 0; i < tam; i++) {
            v[i] = w[i];
        }
    }

    private int h(int[] v, int tam) {
        int i;
        int m = v[0];
        for (i = 1; i < tam; i++) {
            if (v[i] > m) {
                m = v[i];
            }
        }
        return m;
    }

    static void aleatorio(int v[], int tam, Random random) {
        int i;
        for (i = 0; i < tam; i++) {
            v[i] = i;
        }
        for (i = tam - 1; i > 0; i--) {
// random.nextInt(k) devuelve un entero pseudoaleatorio distribuido
// uniformemente entre 0 (incluido) y el valor especificado k (excluido)
            int j = random.nextInt(i + 1);
            int temp = v[i];
            v[i] = v[j];
            v[j] = temp;
        }
    }

    public static void generarCSV( int[] comparaciones, int[] asignaciones, long[] tiempo, String nombreArchivo) {

        String archivoCSV = nombreArchivo; // Nombre del archivo de salida

        try (FileWriter escritor = new FileWriter(archivoCSV)) {
            // Escribimos la cabecera del archivo CSV
            escritor.append("Tamaño;Comparaciones;Asignaciones;Tiempo\n");

            // Escribimos los datos en el archivo CSV
            for (int i = 0; i < 10; i++) {
                escritor.append(10000 * (i+1) + ";" + comparaciones[i] + ";" + asignaciones[i] + ";" + tiempo[i] + "\n");
            }

            System.out.println("Archivo CSV generado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo CSV.");
            e.printStackTrace();
        }
    }
}
