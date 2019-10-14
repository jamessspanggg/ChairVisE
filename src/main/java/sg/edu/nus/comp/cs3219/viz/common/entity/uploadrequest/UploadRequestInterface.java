package sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest;

import java.util.List;

public interface UploadRequestInterface<T> {

    public String getFileName();
    public void setFileName(String fileName);
    public String getFileType();
    public void setFileType(String fileType);
    public List<T> getRecordList();
    public void setRecordList(List<T> recordList);
    public String getTableType();
}
