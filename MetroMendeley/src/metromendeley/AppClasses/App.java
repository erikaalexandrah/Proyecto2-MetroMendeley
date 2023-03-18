/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metromendeley.AppClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import metromendeley.MainClasses.HashTable;
import metromendeley.MainClasses.Summary;

/**
 * Descipción: Clase App
 *
 * @author Erika Hernández Fecha: 11/03/2023
 */
public class App {

    private HashTable hashTable;
    private static App app;

    public App() {
        this.hashTable = new HashTable(30, 300);
        this.updateDefaultFile();
    }

    public static synchronized App getInstance() {
        if (app == null) {
            app = new App();
        }
        return app;
    }

    /**
     * Con el método updateDefaultFile se precarga los artículos científicos
     * dado en el pdf del proyecto. Se utiliza JFileChooser para cargar el TXT.
     * Posteriormente se llama al método DJB2 con el propósito de asignarle un
     * index al key (title). Y se utiliza al método DoubleHashing en caso que
     * exista colisión en la asignación del index.
     */
    public void updateDefaultFile() {
        String path = "src//Files//DefaultFile.txt";
        String line;
        String txt = "";

        //// LEER EL TXT de DefaultFile  Y ALMACENARLO EN UNA VARIABLE 
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                txt += line + "\n";
            }
            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se logró cargar el archivo. Intente nuevamente.");
        }
        String[] arrayAux1 = txt.split("%");
        String[] arrayAux2, arrayAux3, arrayAux4, arrayAux5, arrayAux6;

        for (int i = 0; i < arrayAux1.length; i++) {
            arrayAux2 = arrayAux1[i].split("Autores\n");
            // Con arrayAux2[0] se accede al titulo del paper. 
            arrayAux3 = arrayAux2[1].split("Resumen");
            // Con arrayAux4 se tiene un arreglo que contiene todos los autores de ese paper. 
            arrayAux4 = arrayAux3[0].split("\n");
            // Con arrayAux5[0] se accede al resumen del paper. 
            arrayAux5 = arrayAux3[1].split("Palabras claves:");
            // Con arrayAux6 Se tiene un arreglo que contiene todas las palabras claves de ese paper. 
            arrayAux6 = arrayAux5[1].split(",");
            for (int j = 0; j < arrayAux6.length; j++) {
                arrayAux6[j] = arrayAux6[j].replace("\n", "").replace(".", "");
                //arrayAux6[j] = arrayAux6[j].substring(0, 1).toUpperCase() + arrayAux6[j].substring(1);
            }
            // Se crea el objeto SUMARY que contendrá todo lo anteriormente mencionado como atributo. 
            Summary sumary = new Summary(arrayAux2[0], arrayAux4, arrayAux5[0], arrayAux6);

            // Se agrega el paper al hashTable 
            int position = this.getHashTable().addSumary(sumary);
            // Se agrega los keywords al hashtable secundario (util para el requerimiento 3). 
            this.getHashTable().addKeyword(arrayAux6, position);

        }
    }
    
    
    public void uploadSumary(String path) {
        String line;
        String txt = "";

        //// LEER EL TXT de DefaultFile  Y ALMACENARLO EN UNA VARIABLE 
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                txt += line + "\n";
            }
            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se logró cargar el archivo. Intente nuevamente.");
        }

        try {
            String[] arrayAux2 = txt.split("Autores");
            String[] arrayAux3, arrayAux4, arrayAux5, arrayAux6;
            // Con arrayAux2[0] se accede al titulo del paper. 
            arrayAux3 = arrayAux2[1].split("Resumen");
            // Con arrayAux4 se tiene un arreglo que contiene todos los autores de ese paper. 
            arrayAux4 = arrayAux3[0].split("\n");
            // Con arrayAux5[0] se accede al resumen del paper.
            if (arrayAux3[1].contains("Palabras claves:")) {
                arrayAux5 = arrayAux3[1].split("Palabras claves:");
            } else {
                arrayAux5 = arrayAux3[1].split("Palabras Claves:");
            }
            // Con arrayAux6 Se tiene un arreglo que contiene todas las palabras claves de ese paper. 
            arrayAux6 = arrayAux5[1].split(",");
            // Se crea el objeto SUMARY que contendrá todo lo anteriormente mencionado como atributo. 
            Summary sumary = new Summary(arrayAux2[0], arrayAux4, arrayAux5[0], arrayAux6);
            // Se agrega temporalmente al hashTable el paper (OJO, es temporalmente porque todavía falta el método DoubleHashing que evite las posible colisiones derivadas de DBJ2
            getHashTable().addSumary(sumary);
            JOptionPane.showMessageDialog(null, "Su archivo se logró cargar efectivamente.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se logró cargar el archivo. No cumple con el formato adecuado.");
        }
    }

    /**
     * @return the hashTable
     */
    public HashTable getHashTable() {
        return hashTable;
    }

    /**
     * @param hashTable the hashTable to set
     */
    public void setHashTable(HashTable hashTable) {
        this.hashTable = hashTable;
    }
}
