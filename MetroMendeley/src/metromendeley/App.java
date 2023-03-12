/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metromendeley;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

/**
 * Descipción: Clase App
 * @author Erika Hernández
 * Fecha: 11/03/2023
 */
public class App {
    
    
    /** Con el método updateDefaultFile se precarga los artículos científicos dado en el pdf del proyecto. 
     *  Se utiliza JFileChooser para cargar el TXT. 
     *  Posteriormente se llama al método DJB2 con el propósito de asignarle un index al key (title). 
     *  Y se utiliza al método DoubleHashing en caso que exista colisión en la asignación del index. 
    */ 
    public void updateDefaultFile(){
        String path = "src//Files//DefaultFile.txt"; 
        String line;
        String txt = "";
      
        //// LEER EL TXT de DefaultFile  Y ALMACENARLO EN UNA VARIABLE 
        File file = new File(path);
        try {
            FileReader fr = new FileReader (file);
            BufferedReader br = new BufferedReader (fr);
            while ((line = br.readLine()) != null) {
                    txt += line + "\n";
                }
            br.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se logró cargar el archivo. Intente nuevamente.");
        }
         String[] arrayAux1 = txt.split("%");
         String[] arrayAux2, arrayAux3, arrayAux4, arrayAux5, arrayAux6;
         
        HashTable hashTable = new HashTable(10);
         for (int i=0; i<arrayAux1.length; i++){
            arrayAux2 = arrayAux1[i].split("Autores");
            // Con arrayAux2[0] se accede al titulo del paper. 
            arrayAux3 =arrayAux2[1].split("Resumen"); 
            // Con arrayAux4 se tiene un arreglo que contiene todos los autores de ese paper. 
            arrayAux4 = arrayAux3[0].split("\n");
            // Con arrayAux5[0] se accede al resumen del paper. 
            arrayAux5 = arrayAux3[1].split("Palabras claves:"); 
            // Con arrayAux6 Se tiene un arreglo que contiene todas las palabras claves de ese paper. 
            arrayAux6 = arrayAux5[1].split(","); 
            // Se crea el objeto SUMARY que contendrá todo lo anteriormente mencionado como atributo. 
            Sumary sumary = new Sumary(arrayAux2[0], arrayAux4, arrayAux5[0], arrayAux6);
            System.out.println(arrayAux2[0]);
            
            // Se agrega temporalmente al hashTable el paper (OJO, es temporalmente porque todavía falta el método DoubleHashing que evite las posible colisiones derivadas de DBJ2
            hashTable.addSumary(sumary, hashTable.DBJ2(sumary));
         }

    }
    
    
    
}
