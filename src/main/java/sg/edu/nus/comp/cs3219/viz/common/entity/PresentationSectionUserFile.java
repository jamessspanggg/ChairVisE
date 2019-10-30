package sg.edu.nus.comp.cs3219.viz.common.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class PresentationSectionUserFile {

    @Id
    @GenericGenerator(name = "UseExistingIdOtherwiseGenerateUsingIdentity", strategy = "sg.edu.nus.comp.cs3219.viz.common.entity.UseExistingIdOtherwiseGenerateUsingIdentity")
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_section_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PresentationSection presentationSection;

    @Column(name = "file_id")
    private Long fileId;

    public PresentationSection getPresentationSection() {
        return presentationSection;
    }

    public void setPresentationSection(PresentationSection presentationSection) {
        this.presentationSection = presentationSection;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setUserFile(Long fileId) {
        this.fileId = fileId;
    }
}