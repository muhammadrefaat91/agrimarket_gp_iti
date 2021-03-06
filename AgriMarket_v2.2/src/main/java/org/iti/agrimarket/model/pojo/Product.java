package org.iti.agrimarket.model.pojo;
// Generated Apr 29, 2016 4:07:51 PM by Hibernate Tools 4.3.1

import com.google.gson.annotations.Expose;
import java.util.HashSet;
import java.util.Set;

/**
 * Product generated by hbm2java
 */
public class Product implements java.io.Serializable {

    @Expose
    private Integer id;
    @Expose
    private String nameAr;
    @Expose
    private String nameEn;
    @Expose
    private String imageUrl;
    @Expose
    private Boolean organic;
    @Expose
    private String soundUrl;
    @Expose
    private byte[] image;
    
    private Category category;
    
    private Set userOfferProductFixeds = new HashSet(0);
    private Set histories = new HashSet(0);
    private Set userPlantsPlants = new HashSet(0);
    private Set users = new HashSet(0);
    private Set seasons = new HashSet(0);

    public Product() {
    }

    public Product(Category category) {
        this.category = category;
    }

    public Product(Category category, String nameAr, String nameEn, String imageUrl, Boolean organic, String soundUrl, Set userOfferProductFixeds, Set histories, Set userPlantsPlants, Set users, Set seasons) {
        this.category = category;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.imageUrl = imageUrl;
        this.organic = organic;
        this.soundUrl = soundUrl;
        this.userOfferProductFixeds = userOfferProductFixeds;
        this.histories = histories;
        this.userPlantsPlants = userPlantsPlants;
        this.users = users;
        this.seasons = seasons;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getOrganic() {
        return this.organic;
    }

    public void setOrganic(Boolean organic) {
        this.organic = organic;
    }

    public String getSoundUrl() {
        return this.soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    public Set getUserOfferProductFixeds() {
        return this.userOfferProductFixeds;
    }

    public void setUserOfferProductFixeds(Set userOfferProductFixeds) {
        this.userOfferProductFixeds = userOfferProductFixeds;
    }

    public Set getHistories() {
        return this.histories;
    }

    public void setHistories(Set histories) {
        this.histories = histories;
    }

    public Set getUserPlantsPlants() {
        return this.userPlantsPlants;
    }

    public void setUserPlantsPlants(Set userPlantsPlants) {
        this.userPlantsPlants = userPlantsPlants;
    }

    public Set getUsers() {
        return this.users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }

    public Set getSeasons() {
        return this.seasons;
    }

    public void setSeasons(Set seasons) {
        this.seasons = seasons;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", nameAr=" + nameAr + ", nameEn=" + nameEn + ", imageUrl=" + imageUrl + ", organic=" + organic + ", soundUrl=" + soundUrl +  '}';
    }

}
