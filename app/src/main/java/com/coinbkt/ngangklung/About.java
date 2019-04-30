package com.coinbkt.ngangklung;

public class About {
    String aboutListTitle;
    String aboutListLink;

    public String getAboutListTitle() {
        return aboutListTitle;
    }

    public String getAboutListLink() {
        return aboutListLink;
    }

    public About(String aboutListTitle, String aboutListLink) {

        this.aboutListTitle = aboutListTitle;
        this.aboutListLink = aboutListLink;
    }
}
