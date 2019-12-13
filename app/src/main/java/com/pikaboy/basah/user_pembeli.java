package com.pikaboy.basah;

public class user_pembeli {

    private String nama ;
    private String email;
    private String photoUrl;
    private String UID;

    public user_pembeli(){

    }

    public user_pembeli(String nama, String email, String photoUrl, String UID) {
        this.nama = nama;
        this.email = email;
        this.photoUrl = photoUrl;
        this.UID = UID;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
