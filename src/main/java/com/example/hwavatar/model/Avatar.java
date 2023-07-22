package com.example.hwavatar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Arrays;

@Entity
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;

    private String filePath;

    private long fileSize;

    private String mediaType;

//    можно раскомментировать для того, чтобы не отображался массив картинки как файла
//    @JsonIgnore
    private byte[] data;

    @OneToOne
    private Faculty faculty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avatar)) return false;

        Avatar avatar = (Avatar) o;

        if (getFileSize() != avatar.getFileSize()) return false;
        if (getId() != null ? !getId().equals(avatar.getId()) : avatar.getId() != null) return false;
        if (getFilePath() != null ? !getFilePath().equals(avatar.getFilePath()) : avatar.getFilePath() != null)
            return false;
        if (getMediaType() != null ? !getMediaType().equals(avatar.getMediaType()) : avatar.getMediaType() != null)
            return false;
        if (!Arrays.equals(getData(), avatar.getData())) return false;
        return getFaculty() != null ? getFaculty().equals(avatar.getFaculty()) : avatar.getFaculty() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFilePath() != null ? getFilePath().hashCode() : 0);
        result = 31 * result + (int) (getFileSize() ^ (getFileSize() >>> 32));
        result = 31 * result + (getMediaType() != null ? getMediaType().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getData());
        result = 31 * result + (getFaculty() != null ? getFaculty().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", faculty=" + faculty +
                '}';
    }
}
