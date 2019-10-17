package sg.edu.nus.comp.cs3219.viz.ui.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.comp.cs3219.viz.common.datatransfer.UserInfo;
import sg.edu.nus.comp.cs3219.viz.common.entity.UserFile;
import sg.edu.nus.comp.cs3219.viz.logic.GateKeeper;
import sg.edu.nus.comp.cs3219.viz.logic.UserFileLogic;

import java.util.List;

@RestController
public class UserFileController extends BaseRestController {
    private GateKeeper gateKeeper;
    private UserFileLogic userFileLogic;

    public UserFileController(GateKeeper gateKeeper, UserFileLogic userFileLogic) {
        this.gateKeeper = gateKeeper;
        this.userFileLogic = userFileLogic;
    }

    @GetMapping("/files")
    public List<UserFile> getUserFile() {
        UserInfo userInfo = gateKeeper.verifyLoginAccess();

        List<UserFile> userFiles = this.userFileLogic.findByUserEmail(userInfo.getUserEmail());

        return userFiles;
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<?> deleteUserFile(@PathVariable(value = "id") Long id) {
        UserInfo userInfo = gateKeeper.verifyLoginAccess();
        this.userFileLogic.removeUserFileByFileId(userInfo.getUserEmail(), id);
        return ResponseEntity.noContent().build();
    }
}
