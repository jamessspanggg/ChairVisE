package sg.edu.nus.comp.cs3219.viz.common.entity;


import sg.edu.nus.comp.cs3219.viz.common.entity.record.Exportable;

import javax.persistence.*;
import java.util.Date;

@Exportable(name = "User File", nameInDB = "user_file")
@Entity
public class UserFile {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "file_name")
    private String fileName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    public UserFile(String userEmail, String fileName) {
        this.userEmail = userEmail;
        this.fileName = fileName;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
