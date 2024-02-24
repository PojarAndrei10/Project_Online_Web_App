package com.proiectis.masinifa.entitati;

import javax.persistence.*;

@Entity
@Table(name = "tb_profile_picture")
public class ProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_picture")
    private int idPicture;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Lob
    @Column(nullable = false)
    private String image;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_profile")
    private UserProfile profile; // Profilul asociat acestei imagini de profil

    public ProfilePicture() {
    }

    // Constructor care accepta parametri pentru a seta detaliile imaginii de profil
    public ProfilePicture(String fileName, String fileType, String image, UserProfile profile) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.image = image;
        this.profile = profile;
    }

    public int getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(int idPicture) {
        this.idPicture = idPicture;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}
