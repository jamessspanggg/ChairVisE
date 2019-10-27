package sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest;

import sg.edu.nus.comp.cs3219.viz.common.entity.record.AuthorRecord;

import java.util.List;


public class AuthorUploadRequest implements UploadRequestInterface<AuthorRecord> {

    private String fileName;
    private String fileType;
    private List<AuthorRecord> recordList;
    private final String tableType = "author";

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
    public List<AuthorRecord> getRecordList() {
        return this.recordList;
    }

    @Override
    public void setRecordList(List<AuthorRecord> recordList) {
        this.recordList = recordList;
    }

    @Override
    public String getTableType() {
        return this.tableType;
    }
}
