package sg.edu.nus.comp.cs3219.viz.logic;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.nus.comp.cs3219.viz.common.entity.UserFile;
import sg.edu.nus.comp.cs3219.viz.storage.repository.UserFileRepository;

import java.util.List;

@Component
public class UserFileLogic {
    private UserFileRepository userFileRepository;

    public UserFileLogic (UserFileRepository userFileRepository){
        this.userFileRepository = userFileRepository;
    }

    public List<UserFile> findByUserEmail (String userEmail) {
        // return user file in descending order of date
        List<UserFile> userFiles = userFileRepository.findByUserEmailOrderByCreatedAtDesc(userEmail);

        return userFiles;
    }

    @Transactional
    public void removeUserFileByFileId(String userEmail, Long file_id) {
        userFileRepository.deleteUserFileByUserEmailAndId(userEmail, file_id);
    }
}
