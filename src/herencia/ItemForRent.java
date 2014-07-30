/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herencia;

import java.util.Date;

/**
 *
 * @author Docente 17082011
 */
public class ItemForRent {
    protected int code, copies=0;
    protected double price;
    protected String title;
    protected Date publishAt;
    protected String rate;
    
    public ItemForRent(int c, String t, double p){
        code = c;
        title = t;
        price = p;
        publishAt = new Date();
        rate = "PENDING";
    }
    
    public ItemForRent(int c){
        this(c,"UNTITLED",0);
    }
    
    public ItemForRent(){
        this(-1);
    }
    
    public int getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Date getPublishAt() {
        return publishAt;
    }

    public String getRate() {
        return rate;
    }

    public void addCopies(int copies) {
        this.copies += copies;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
    
    public double rent(int days){
        if(copies > 0)
            return price * days;
        return 0;
    }
    
    public void quienSoy(){
        System.out.println("SOY EL ITEM");
    }
}
