package sg.edu.nus.comp.cs3219.viz.common.entity;

import sg.edu.nus.comp.cs3219.viz.common.entity.PresentationSection;

import java.util.List;

public class PresentationSectionRequestResponse {
    private PresentationSection presentationSection;
    private List<Long> fileIds;

    public PresentationSection getPresentationSection() {
        return this.presentationSection;
    }

    public void setPresentationSection(PresentationSection presentationSection) {
        this.presentationSection = presentationSection;
    }

    public List<Long> getFileIds() {
        return this.fileIds;
    }

    public void setFileIds(List<Long> fileIds) {
        this.fileIds = fileIds;
    }
}
