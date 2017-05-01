package cl.josemanuel.socialconnector2.entities;

public class ContactEntity {

    private long id;
    private String nombre;
    private String correo;
    private String skype;
    private PhotoEntity avatar;

    public ContactEntity(String nombre, String correo, String skype) {
        this.nombre = nombre;
        this.correo = correo;
        this.skype = skype;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public PhotoEntity getAvatar() {
        return avatar;
    }

    public void setAvatar(PhotoEntity avatar) {
        this.avatar = avatar;
    }
}
