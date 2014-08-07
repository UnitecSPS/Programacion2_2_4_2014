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
public abstract class ItemForRent {
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
        this(c,"UNTITLED",1);
    }
    
    public ItemForRent(){
        this(-1);
    }
    
    public final int getCode() {
        return code;
    }

    public final double getPrice() {
        return price;
    }

    public final String getTitle() {
        return title;
    }

    public final Date getPublishAt() {
        return publishAt;
    }

    public final String getRate() {
        return rate;
    }

    public final void addCopies(int copies) {
        this.copies += copies;
    }

    public final void setPrice(double price) {
        this.price = price;
    }

    public final void setRate(String rate) {
        this.rate = rate;
    }
    
    public abstract double rent(int days);
    
    public void quienSoy(){
        System.out.println("SOY EL ITEM");
    }

    @Override
    public String toString() {
        return "- code=" + code + ", price=" + price + ", title=" + title;
    }
    
    public static ItemForRent getInstance(int code){
        ItemForRent rent = new ItemForRent(code) {
            
            @Override
            public double rent(int days) {
                return 0;
            }
        };
        return rent;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof ItemForRent){
            return this.code == ((ItemForRent)obj).getCode();
        }
        else if(obj instanceof Integer){
            return this.code == (Integer)obj;
        }
        else if(obj instanceof String){
            return this.title.equalsIgnoreCase(obj.toString());
        }
        return false;
    }
    
}
