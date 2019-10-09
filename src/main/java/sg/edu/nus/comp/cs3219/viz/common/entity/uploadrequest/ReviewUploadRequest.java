package sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest;

import sg.edu.nus.comp.cs3219.viz.common.entity.record.ReviewRecord;

import java.util.List;

public class ReviewUploadRequest implements UploadRequestInterface<ReviewRecord> {


    private String fileName;
    private String fileType;
    private List<ReviewRecord> recordList;


    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getFileType() { return this.fileType; }

    @Override
    public void setFileType(String fileType) { this.fileType = fileType; }

    @Override
    public List<ReviewRecord> getRecordList() {
        return this.recordList;
    }

    @Override
    public void setRecordList(List<ReviewRecord> recordList) {
        this.recordList = recordList;
    }


}
