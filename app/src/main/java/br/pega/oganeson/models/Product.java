package br.pega.oganeson.models;

public class Product {

    private String FirebaseId;
    private String Name;
    private String Sku;
    private Boolean Enabled;
    private String ThumbnailSmall;

    public void setName(String name) {
        Name = name;
    }

    public void setSku(String sku) {
        Sku = sku;
    }

    public Boolean getEnabled() {
        return Enabled;
    }

    public String getName() {
        return Name;
    }

    public String getSku() {
        return Sku;
    }

    public String getFirebaseId() {
        return FirebaseId;
    }

    public void setEnabled(Boolean enabled) {
        Enabled = enabled;
    }

    public void setFirebaseId(String firebaseId) {
        FirebaseId = firebaseId;
    }

    public String getThumbnailSmall() {
        return ThumbnailSmall;
    }

    public void setThumbnailSmall(String thumbnailSmall) {
        ThumbnailSmall = thumbnailSmall;
    }

    @Override
    public String toString() {
        return "Product{" +
                "FirebaseId='" + FirebaseId + '\'' +
                ", Name='" + Name + '\'' +
                ", Sku='" + Sku + '\'' +
                ", Enabled=" + Enabled +
                ", ThumbnailSmall='" + ThumbnailSmall + '\'' +
                '}';
    }
}
