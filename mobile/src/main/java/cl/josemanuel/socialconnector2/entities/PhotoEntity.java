package cl.josemanuel.socialconnector2.entities;

public class PhotoEntity {

    private long id;
    private String url;
    private String path;

    public PhotoEntity(String url, String path) {
        this.url = url;
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
