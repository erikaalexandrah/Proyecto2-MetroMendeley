/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metromendeley;

/**
 * Descripción: Clase resumen de artículo científico. 
 * @author Erika Hernández
 * Fecha: 11/03/2023
 */
public class Sumary {
    
    private String title; 
    private String[] authors;
    private String body;
    private String[] keywords;

    // Constructor 
    public Sumary(String title, String[] authors, String body, String[] keywords) {
        this.title = title;
        this.authors = authors;
        this.body = body;
        this.keywords = keywords;
    }
    
    public String showAttr(){
        String authors = "";
        for (int i=0; i<this.authors.length; i++){
            authors += this.authors[i]+ ", ";
        }
        String keywords = "";
        for (int j=0; j<this.keywords.length; j++){
            int counter = 0;
            int position = this.body.toLowerCase().indexOf(this.keywords[j].toLowerCase().replace("\n","").replace(".",""));
            while (position !=-1) {
                counter ++;
                position = this.body.toLowerCase().indexOf(this.keywords[j].toLowerCase().replace("\n","").replace(".",""), position +1);
            }
            keywords += "- " + this.keywords[j].replace("\n","") + ": Frecuencia con la cual aparece en el resumen: " + counter + " veces.\n";
        }
        return "TÍTULO: \n" + this.title + "\n" + "AUTORES: \n" + authors + "\n" + "\n" + "PALABRAS CLAVES: \n"+keywords;
    }
    
    // Getters & Setters
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the authors
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the keywords
     */
    public String[] getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }
    
    
    
}
