package com.example.hp.houseonrent;

public class OwnerItem {
    public String Locality;
    public String Review;
    public String Himage;
    public String Avail;

    public String getHimage() {
        return Himage;
    }

    public String getLocality() {
        return Locality;
    }

    public String getReview() {
        return Review;
    }

    public String getAvail() {
        return Avail;
    }

    public OwnerItem(String avail, String review, String locality, String himage) {
        Locality = locality;
        Review = review;
        Himage = himage;
        Avail = avail;
    }
}
