package cl.josemanuel.socialconnector2.entities;

import java.util.Date;

public class MessageEntity {

    private long id;
    private String text;
    private Date fecha;
    private boolean seen;

    public MessageEntity(String text, Date fecha, boolean seen) {
        this.text = text;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
