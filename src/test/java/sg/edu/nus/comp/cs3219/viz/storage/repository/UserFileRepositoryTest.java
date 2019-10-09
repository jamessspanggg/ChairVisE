package sg.edu.nus.comp.cs3219.viz.storage.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sg.edu.nus.comp.cs3219.viz.BaseTestWithDBAccess;
import sg.edu.nus.comp.cs3219.viz.common.entity.UserFile;

import java.util.List;

public class UserFileRepositoryTest extends BaseTestWithDBAccess {

    @Override
    protected String getDataBundleName() { return "/UserFileRepositoryTest.json"; }

    @Autowired
    private UserFileRepository userFileRepository;

    @Test
    public void testFindByUserEmail_typicalCase_shouldReturnAllCorrespondingUserFiles() {
        List<UserFile> actualList = userFileRepository.findByUserEmail("test1@viz.test");
        Assert.assertEquals(2, actualList.size());

        actualList = userFileRepository.findByUserEmail("test2@viz.test");
        Assert.assertEquals(1, actualList.size());

        actualList = userFileRepository.findByUserEmail("test3@viz.test");
        Assert.assertEquals(1, actualList.size());
    }
}
