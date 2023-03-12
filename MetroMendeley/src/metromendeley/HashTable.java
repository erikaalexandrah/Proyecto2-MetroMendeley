/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metromendeley;

/**
 * Descripción: Primitiva HashTable 
 * @author Erika Hernández 
 * Fecha: 11/03/2023
 */

public class HashTable {
    private Sumary[] sumarys;

    // Constructor vacío
    public HashTable(int i) {
        this.sumarys = new Sumary[i];
    }
    
    // Método: Hash Function DJB2 para obtener un index del artículo científico a partir de su title. 
    public int DBJ2 (Sumary sumary){
        // Se designa al comienzo el numero primo 5381 descrito por Bernstein
        long hash = 5381;
        for (int i = 0; i < sumary.getTitle().length(); i++) {
        // Se multiplica por 2^5 bits el resultado y se suma el valor ASCII del carácter actual al resultado obtenido en cada iteración.
        hash = ((hash*32) + hash) + sumary.getTitle().charAt(i);
        }
        // Si el valor del hash obtenido es negativo por overflow, se convierte a positivo.
        if (hash < 0) {
            hash = -hash;
        }
        // Se obtiene el residuo del modulo del hash % len del array del hashTable. 
    return Long.valueOf(hash % Long.valueOf(this.sumarys.length)).intValue();
    }
    
    // Método: Agregar resumen (paper) al HashTable.
    public void addSumary(Sumary sumary, int position){
        this.sumarys[position]=sumary;
    }
    
    // Getters & Setters
    
    /**
     * @return the sumarys
     */
    public Sumary[] getSumarys() {
        return sumarys;
    }

    /**
     * @param sumarys the sumarys to set
     */
    public void setSumarys(Sumary[] sumarys) {
        this.sumarys = sumarys;
    }
    
    
    
    
}
