package cl.josemanuel.socialconnector2.entities;

public class ContactEntity {

    private long id;
    private String name;
    private String email;
    private String skype;
    private PhotoEntity avatar;

    public ContactEntity(String nombre, String correo, String skype) {
        this.name = nombre;
        this.email = correo;
        this.skype = skype;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
