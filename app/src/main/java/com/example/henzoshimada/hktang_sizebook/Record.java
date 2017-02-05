package com.example.henzoshimada.hktang_sizebook;

import android.content.Context;
import android.widget.Toast;

import java.io.LineNumberReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HenzoShimada on 2017-01-26.
 */

public class Record {
    private String name;
    private String date;
    private Double neck;
    private Double bust;
    private Double chest;
    private Double waist;
    private Double hip;
    private Double inseam;
    private String comment;

    public Record(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public Double getNeck() {
        return neck;
    }
    public Double getBust() {
        return bust;
    }
    public Double getChest() {
        return chest;
    }
    public Double getWaist() {
        return waist;
    }
    public Double getHip() {
        return hip;
    }
    public Double getInseam() {
        return inseam;
    }
    public String getComment() {
        return comment;
    }

    public void setDate(String date) {

        if (date.length() != 0) {
            List<String> datePieces;
            if (date.contains("-")) {
                datePieces = Arrays.asList(date.split("-"));
            } else {
                throw new IndexOutOfBoundsException();
            }
            if (datePieces.size() != 3) {
                throw new IndexOutOfBoundsException();
            }
            Integer year = Integer.parseInt(datePieces.get(0));
            Integer month = Integer.parseInt(datePieces.get(1));
            Integer day = Integer.parseInt(datePieces.get(2));

            if ((year < 1800) || (year > 2017)) {
                throw new IndexOutOfBoundsException();
            }
            if ((month < 1) || (month > 12)) {
                throw new IndexOutOfBoundsException();
            }

            this.date = date;
        }
    }

    public void setNeck(String neckText) {
        if(neckText.length() != 0) {
            Double neck = Double.parseDouble(neckText);
            if (neck < 0){
                throw new IndexOutOfBoundsException();
            }
            //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
            ////2017-02-04 17:26
            this.neck = (double)Math.round(neck * 10d) / 10d;
        }
    }

    public void setBust(String bustText) {
        if(bustText.length() != 0) {
            Double bust = Double.parseDouble(bustText);
            if (bust < 0){
                throw new IndexOutOfBoundsException();
            }
            this.bust = (double)Math.round(bust * 10d) / 10d;
        }
    }

    public void setChest(String chestText) {
        if(chestText.length() != 0){
            Double chest = Double.parseDouble(chestText);
            if (chest < 0){
                throw new IndexOutOfBoundsException();
            }
            this.chest = (double)Math.round(chest * 10d) / 10d;
        }
    }

    public void setWaist(String waistText) {
        if(waistText.length() != 0) {
            Double waist = Double.parseDouble(waistText);
            if (waist < 0) {
                throw new IndexOutOfBoundsException();
            }
            this.waist = (double) Math.round(waist * 10d) / 10d;
        }
    }

    public void setHip(String hipText) {
        if(hipText.length() != 0) {
            Double hip = Double.parseDouble(hipText);
            if(hip < 0) {
                throw new IndexOutOfBoundsException();
            }
            this.hip = (double) Math.round(hip * 10d) / 10d;
        }
    }

    public void setInseam(String inseamText) {
        if(inseamText.length() != 0) {
            Double inseam = Double.parseDouble(inseamText);
            if(inseam < 0) {
                throw new IndexOutOfBoundsException();
            }
            this.inseam = (double) Math.round(inseam * 10d) / 10d;
        }
    }

    public void setComment(String comment) {

        this.comment = comment;
    }

    @Override
    public String toString(){
         return "Name: " + getName()+ "\n"
                 + "Bust Circumference: " + getBust()+ "\n"
                 + "Chest Circumference: " + getChest()+ "\n"
                 + "Waist Circumference: " + getWaist()+ "\n"
                 + "Inseam Length: " + getInseam();
    }
}
