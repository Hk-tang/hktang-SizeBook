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

    /**
     * Instantiates a new Record.
     *
     * @param name the name
     */
    public Record(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets neck.
     *
     * @return the neck
     */
    public Double getNeck() {
        return neck;
    }

    /**
     * Gets bust.
     *
     * @return the bust
     */
    public Double getBust() {
        return bust;
    }

    /**
     * Gets chest.
     *
     * @return the chest
     */
    public Double getChest() {
        return chest;
    }

    /**
     * Gets waist.
     *
     * @return the waist
     */
    public Double getWaist() {
        return waist;
    }

    /**
     * Gets hip.
     *
     * @return the hip
     */
    public Double getHip() {
        return hip;
    }

    /**
     * Gets inseam.
     *
     * @return the inseam
     */
    public Double getInseam() {
        return inseam;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets date.
     *
     * Formats the date and assures proper input
     */
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
            
            Integer month = Integer.parseInt(datePieces.get(1));
            Integer day = Integer.parseInt(datePieces.get(2));

            if ((month < 1) || (month > 12)) {
                throw new IndexOutOfBoundsException();
            }
            if((day < 1) || (day > 31)){
                throw new IndexOutOfBoundsException();
            }

            this.date = date;
        }
    }

    /**
     * Sets neck.
     * Formats the neck field
     * @param neckText the neck text
     */
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

    /**
     * Sets bust.
     * Formats the bust field
     * @param bustText the bust text
     */
    public void setBust(String bustText) {
        if(bustText.length() != 0) {
            Double bust = Double.parseDouble(bustText);
            if (bust < 0){
                throw new IndexOutOfBoundsException();
            }
            this.bust = (double)Math.round(bust * 10d) / 10d;
        }
    }

    /**
     * Sets chest.
     * Formats the chest field
     * @param chestText the chest text
     */
    public void setChest(String chestText) {
        if(chestText.length() != 0){
            Double chest = Double.parseDouble(chestText);
            if (chest < 0){
                throw new IndexOutOfBoundsException();
            }
            this.chest = (double)Math.round(chest * 10d) / 10d;
        }
    }

    /**
     * Sets waist.
     * Formats the waist field
     * @param waistText the waist text
     */
    public void setWaist(String waistText) {
        if(waistText.length() != 0) {
            Double waist = Double.parseDouble(waistText);
            if (waist < 0) {
                throw new IndexOutOfBoundsException();
            }
            this.waist = (double) Math.round(waist * 10d) / 10d;
        }
    }

    /**
     * Sets hip.
     * Formats the hip field
     * @param hipText the hip text
     */
    public void setHip(String hipText) {
        if(hipText.length() != 0) {
            Double hip = Double.parseDouble(hipText);
            if(hip < 0) {
                throw new IndexOutOfBoundsException();
            }
            this.hip = (double) Math.round(hip * 10d) / 10d;
        }
    }

    /**
     * Sets inseam.
     * Formats the inseam field
     * @param inseamText the inseam text
     */
    public void setInseam(String inseamText) {
        if(inseamText.length() != 0) {
            Double inseam = Double.parseDouble(inseamText);
            if(inseam < 0) {
                throw new IndexOutOfBoundsException();
            }
            this.inseam = (double) Math.round(inseam * 10d) / 10d;
        }
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {

        this.comment = comment;
    }

    // This is what is shown on the Main screen when the records are listed
    @Override
    public String toString() {
        String display = "Name: " + getName() + "\n";
        if(this.getBust() != null ){
            display += "Bust Circumference: " + getBust() + "\n";
        }
        if(this.getChest() != null){
            display += "Chest Circumference: " + getChest() + "\n";
        }
        if(this.getWaist() != null){
            display += "Waist Circumference: " + getWaist() + "\n";
        }
        if(this.getInseam() != null) {
            display += "Inseam Length: " + getInseam();
        }
        return display;
    }
}
