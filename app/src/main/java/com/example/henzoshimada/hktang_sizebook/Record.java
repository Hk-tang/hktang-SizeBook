package com.example.henzoshimada.hktang_sizebook;

/**
 * Created by HenzoShimada on 2017-01-26.
 */

public class Record {
    private String name;
    private String date;
    private Float neck;
    private Float bust;
    private Float chest;
    private Float waist;
    private Float hip;
    private Float inseam;
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
    public Float getNeck() {
        return neck;
    }
    public Float getBust() {
        return bust;
    }
    public Float getChest() {
        return chest;
    }

    public Float getWaist() {
        return waist;
    }

    public Float getHip() {
        return hip;
    }

    public Float getInseam() {
        return inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public void setNeck(String neckText) {

        Float neck = Float.parseFloat(neckText);
        this.neck = neck;
    }

    public void setBust(String bustText) {

        Float bust = Float.parseFloat(bustText);
        this.bust = bust;
    }

    public void setChest(String chestText) {

        Float chest = Float.parseFloat(chestText);
        this.chest = chest;
    }

    public void setWaist(String waistText) {

        Float waist = Float.parseFloat(waistText);
        this.waist = waist;
    }

    public void setHip(String hipText) {

        Float hip = Float.parseFloat(hipText);
        this.hip = hip;
    }

    public void setInseam(String inseamText) {

        Float inseam = Float.parseFloat(inseamText);
        this.inseam = inseam;
    }

    public void setComment(String comment) {

        this.comment = comment;
    }
}
