package sg.edu.nus.comp.cs3219.viz.ui.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.comp.cs3219.viz.common.datatransfer.UserInfo;
import sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest.AuthorUploadRequest;
import sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest.ReviewUploadRequest;
import sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest.SubmissionUploadRequest;
import sg.edu.nus.comp.cs3219.viz.logic.GateKeeper;
import sg.edu.nus.comp.cs3219.viz.logic.RecordLogic;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class RecordController extends BaseRestController {

    private GateKeeper gateKeeper;

    private RecordLogic recordLogic;

    public RecordController(GateKeeper gateKeeper, RecordLogic recordLogic) {
        this.gateKeeper = gateKeeper;
        this.recordLogic = recordLogic;
    }

    @PostMapping("/record/author")
    public ResponseEntity<?> importAuthorRecord(@RequestBody AuthorUploadRequest authorUploadRequest) throws URISyntaxException {
        UserInfo userInfo = gateKeeper.verifyLoginAccess();

        this.recordLogic.persistAuthorRecordForDataSet(userInfo.getUserEmail(), authorUploadRequest);

        return ResponseEntity.created(new URI("/record/author")).build();
    }

    @PostMapping("/record/review")
    public ResponseEntity<?> importReviewRecord(@RequestBody ReviewUploadRequest reviewUploadRequest) throws URISyntaxException {
        UserInfo userInfo = gateKeeper.verifyLoginAccess();

        this.recordLogic.persistReviewRecordForDataSet(userInfo.getUserEmail(), reviewUploadRequest);

        return ResponseEntity.created(new URI("/record/review")).build();
    }

    @PostMapping("/record/submission")
    public ResponseEntity<?> importSubmissionRecord(@RequestBody SubmissionUploadRequest submissionUploadRequest) throws URISyntaxException {
        UserInfo userInfo = gateKeeper.verifyLoginAccess();

        this.recordLogic.persistSubmissionRecordForDataSet(userInfo.getUserEmail(), submissionUploadRequest);

        return ResponseEntity.created(new URI("/record/review")).build();
    }

    @DeleteMapping("/record/author/{id}")
    public ResponseEntity<?> deleteAuthorRecord(@PathVariable(value = "id") Long file_id) {
        UserInfo userInfo = gateKeeper.verifyLoginAccess();
        this.recordLogic.removeAuthorRecordByFileId(userInfo.getUserEmail(), file_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/record/review/{id}")
    public ResponseEntity<?> deleteReviewRecord(@PathVariable(value = "id") Long file_id) {
        UserInfo userInfo = gateKeeper.verifyLoginAccess();
        this.recordLogic.removeReviewRecordByFileId(userInfo.getUserEmail(), file_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/record/submission/{id}")
    public ResponseEntity<?> deleteSubmissionRecord(@PathVariable(value = "id") Long file_id) {
        UserInfo userInfo = gateKeeper.verifyLoginAccess();
        this.recordLogic.removeSubmissionRecordByFileId(userInfo.getUserEmail(), file_id);
        return ResponseEntity.noContent().build();
    }
}
