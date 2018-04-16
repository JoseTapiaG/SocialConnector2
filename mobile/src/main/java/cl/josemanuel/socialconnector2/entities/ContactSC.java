package cl.josemanuel.socialconnector2.entities;

import java.util.ArrayList;

public class ContactSC {

    private String relationship;
    private String rol;
    private String favorite_media;
    private String affective_closeness;
    private String physical_closeness;
    private String user;
    private String avatar;
    private String first_name;
    private String last_name;
    private String gender;
    private String birthday;
    private String age_group;
    private String created_date;
    private String phone;
    private String phone2;
    private String address;
    private boolean isActive;
    private ArrayList<String> social_networks;
    private String hash;

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFavorite_media() {
        return favorite_media;
    }

    public void setFavorite_media(String favorite_media) {
        this.favorite_media = favorite_media;
    }

    public String getAffective_closeness() {
        return affective_closeness;
    }

    public void setAffective_closeness(String affective_closeness) {
        this.affective_closeness = affective_closeness;
    }

    public String getPhysical_closeness() {
        return physical_closeness;
    }

    public void setPhysical_closeness(String physical_closeness) {
        this.physical_closeness = physical_closeness;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ArrayList<String> getSocial_networks() {
        return social_networks;
    }

    public void setSocial_networks(ArrayList<String> social_networks) {
        this.social_networks = social_networks;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
