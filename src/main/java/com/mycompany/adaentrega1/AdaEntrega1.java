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

    static long asignaciones=0;
    static long comparaciones=0;
             
    
    public static void main(String[] args) {
        
        int contador;
        int v[];
        Random random = new Random();
        long asignaciones1[] = new long[10];
        long asignaciones2[] = new long[10];
        long asignaciones3[] = new long[10];

        long comparaciones1[] = new long[10];
        long comparaciones2[] = new long[10];
        long comparaciones3[] = new long[10];

        long tiempo1[] = new long[10];
        long tiempo2[] = new long[10];
        long tiempo3[] = new long[10];
        
        for(int i=0;i<10;i++){
            
         
        for (contador = 0; 10 > contador; contador++) {
            int tamanio =(contador + 1) * 10000;
            v = new int[tamanio];
             aleatorio(v, 10000 * (contador + 1), random);
            
            int v1[], v2[], v3[];
          
           
            v1 = v.clone();
            v2 = v.clone();
            v3 = v.clone();

            //primera funcion 
            Instant start = Instant.now();
            ordena1(v1, 10000 * (contador + 1));
            Instant finish = Instant.now();
            asignaciones1[contador]=asignaciones;
            comparaciones1[contador]=comparaciones;
            tiempo1[contador] = Duration.between(start, finish).toNanos();
            System.out.println("Ordena1 Tamaño " + tamanio +" Asignacion "+ asignaciones+ "comparaciones" + comparaciones + " tiempo "+ tiempo1[contador] +"\n");
            //------------------------
            
            //segunda funcion ---
            
            start = Instant.now();
            comparaciones=0;
            asignaciones=0;
            ordena2(v2, 10000 * (contador + 1));
             finish = Instant.now();
            asignaciones2[contador]=asignaciones;
            comparaciones2[contador]=comparaciones;
            tiempo2[contador] = Duration.between(start, finish).toNanos();
            //---
            
            // tercera funcion ---
             start = Instant.now();
            comparaciones=0;
            asignaciones=0;
            ordena3(v3, 10000 * (contador + 1));
             finish = Instant.now();
            asignaciones3[contador]=asignaciones;
            comparaciones3[contador]=comparaciones;
            tiempo3[contador] = Duration.between(start, finish).toNanos();
            
            //------
            
            
            
        }
        
        generarCSV( comparaciones1, asignaciones1, tiempo1, "ordena1"+ i +".csv");
        generarCSV( comparaciones2, asignaciones2, tiempo2, "ordena2"+ i +".csv");
        generarCSV( comparaciones3, asignaciones3, tiempo3, "ordena3"+ i +".csv");
        }
        
    }

    public static void ordena1(int v[], int tam) {
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
                asignaciones +=2; //asignaciones al vector
                v[i] = temp;
                i = i - 1;
                if (i == 0) {
                    i = 1;
                }
             
            }
         comparaciones++;//comparacion if else
       
        }
        System.out.println("termino ordena1");
    }

    public static void ordena2(int[] v, int tam) {
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

    private static void f(int[] v, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            //separar en dos ifs
            if (j < n){
            	if(v[j - 1] < v[j]) {
                j++;
            	}
            	comparaciones++;
        }
        	comparaciones++;
            if (v[k - 1] >= v[j - 1]) {
                break;
            }
            g(v, k, j);
            k = j;
        }
    }

    private  static void g(int[] v, int i, int j) {
        int temp = v[i - 1]; 
        v[i - 1] = v[j - 1];
        v[j - 1] = temp;
        asignaciones +=3;
    }

    public static void ordena3(int[] v, int tam) {
        int m = h(v, tam);
        int[] c = new int[m + 1];
        int[] w = new int[tam];
        for (int i = 0; i < tam; i++) {
            c[v[i]] = c[v[i]] + 1; asignaciones++;
        }
        for (int i = 1; i < m + 1; i++) {
            c[i] = c[i] + c[i - 1]; asignaciones++;
        }
        for (int i = tam - 1; i >= 0; i--) {
            w[c[v[i]] - 1] = v[i]; asignaciones+=2;
            c[v[i]] = c[v[i]] - 1;
        }
        for (int i = 0; i < tam; i++) {
            v[i] = w[i]; asignaciones++;
        }
    }

    private static int h(int[] v, int tam) {
        int i;
        int m = v[0]; asignaciones++;
        for (i = 1; i < tam; i++) {
        	comparaciones++;
            if (v[i] > m) {
                m = v[i]; asignaciones++;
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

    public static void generarCSV( long[] comparaciones, long[] asignaciones, long[] tiempo, String nombreArchivo) {

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
