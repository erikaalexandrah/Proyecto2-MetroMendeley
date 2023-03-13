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
    public int DBJ2 (String title){
        // Se designa al comienzo el numero primo 5381 descrito por Bernstein
        long hash = 5381;
        for (int i = 0; i < title.length(); i++) {
        // Se multiplica por 2^5 bits el resultado y se suma el valor ASCII del carácter actual al resultado obtenido en cada iteración.
        hash = ((hash*32) + hash) + title.charAt(i);
        }
        // Si el valor del hash obtenido es negativo por overflow, se convierte a positivo.
        if (hash < 0) {
            hash = -hash;
        }
        // Se obtiene el residuo del modulo del hash % len del array del hashTable. 
    return Long.valueOf(hash % Long.valueOf(this.sumarys.length)).intValue();
    }
    
    /**
     * Erika Hernández.
     * Fecha: 12/03/2023
     * Método: Agregar resumen (paper) al HashTable.
     */
    public void addSumary(Sumary sumary){
        // Se llama al Hash function pripcipal DBJ2  
        int hash1 = DBJ2(sumary.getTitle());
        // Se llama al DoubleHash para el manejo de colisiones.
        int hash2 = doubleHash(sumary.getTitle());
        // Se crea una variable de iteraciones para poder seguir generando index de ser requerida mas iteraciones.
        int i = 0;
        // Se asigna como index inicial al hash1
        int index = hash1;
        // Se valida si esta vacío el slot. 
        if (this.sumarys[index] == null){
             this.sumarys[index] = sumary;
        // Si no esta vacio se valida si se esta intentado meter el mismo paper ya cargado.
         } else if (this.sumarys[index].getTitle().equals(sumary.getTitle())){
        // Si ninguno de los casos anteriores es, entonces estamos frente a una colisión y se soluciona. 
         } else {
            // Se itera hasta que el index este libre en el array. 
            while (this.sumarys[index]!=null){
            i++;
            // Se asigna index nuevo usando metodo double hashing., 
            index = (hash1 + i * hash2) % this.sumarys.length;
            }
            // Se agrega al array. 
            this.sumarys[index]= sumary;
         }
    }
    
     /**
     * Erika Hernández.
     * Fecha: 12/03/2023
     * Método: Obtener el segundo Hash Function para el doubleHasing.
     */
     public int doubleHash(String title) {
        //Se selecciona un número primo para evitar patrones en la secuencia de saltos que se generan en caso de colisiones. 
        int prime = 31;
        int hash2 = prime - (DBJ2(title) % prime);
        return hash2;
    }
    
    
    // Mostrar Artículos científicos por orden alfabeticamente 
    public String showArticlesAlphabetic(){
        String text = "";
        for (int i=0; i<this.sumarys.length; i++){
            if (this.sumarys[i]!=null){
            text += i+". "+ this.sumarys[i].getTitle()+"\n";
            }
        }
        return text;
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
