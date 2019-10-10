package sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest;

import sg.edu.nus.comp.cs3219.viz.common.entity.record.SubmissionRecord;

import java.util.List;

public class SubmissionUploadRequest implements UploadRequestInterface<SubmissionRecord> {

    private String fileName;
    private List<SubmissionRecord> recordList;

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<SubmissionRecord> getRecordList() {
        return this.recordList;
    }

    @Override
    public void setRecordList(List<SubmissionRecord> recordList) {
        this.recordList = recordList;
    }
}
