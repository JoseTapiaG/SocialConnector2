package cl.josemanuel.socialconnector2.entities;

import java.io.Serializable;
import java.util.Date;

public class MessageEntity implements Serializable {

    private long id;
    private String text;
    private Date date;
    private boolean seen;
    private ContactEntity contact;
    private PhotoEntity photo;

    public MessageEntity(String text, Date date, boolean seen) {
        this.text = text;
        this.date = date;
        this.seen = seen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public PhotoEntity getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoEntity photo) {
        this.photo = photo;
    }
}
