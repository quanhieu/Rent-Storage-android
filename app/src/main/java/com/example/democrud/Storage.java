package com.example.democrud;

public class Storage {
    int id;
    String storage_type;
    String dimens_in_square_meters;
    String date_time;
    String storage_feature;
    String mounthly_rent_price;
    String name_of_reporter;
    String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorage_type() {
        return storage_type;
    }

    public void setStorage_type(String storage_type) {
        this.storage_type = storage_type;
    }

    public String getDimens_in_square_meters() {
        return dimens_in_square_meters;
    }

    public void setDimens_in_square_meters(String dimens_in_square_meters) {
        this.dimens_in_square_meters = dimens_in_square_meters;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getStorage_feature() {
        return storage_feature;
    }

    public void setStorage_feature(String storage_feature) {
        this.storage_feature = storage_feature;
    }

    public String getMounthly_rent_price() {
        return mounthly_rent_price;
    }

    public void setMounthly_rent_price(String mounthly_rent_price) {
        this.mounthly_rent_price = mounthly_rent_price;
    }

    public String getName_of_reporter() {
        return name_of_reporter;
    }

    public void setName_of_reporter(String name_of_reporter) {
        this.name_of_reporter = name_of_reporter;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
