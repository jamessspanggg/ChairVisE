//package sg.edu.nus.comp.cs3219.viz.ui.controller.api;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.http.MediaType;
//import sg.edu.nus.comp.cs3219.viz.BaseTestREST;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class RecordControllerTest extends BaseTestREST {
//
//    @Override
//    protected String getDataBundleName() {
//        return "/RecordControllerTest.json";
//    }
//
//    @Test
//    public void testImportAuthorRecord_notLogin_shouldNotAccess() throws Exception {
//        gaeSimulation.logoutUser();
//        mvc.perform(
//                post("/api/record/author")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectToJson(dataBundle.authorUploadRequest)))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void testImportReviewRecord_notLogin_shouldNotAccess() throws Exception {
//        gaeSimulation.logoutUser();
//
//        mvc.perform(
//                post("/api/record/review")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectToJson(dataBundle.reviewUploadRequest)))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void testImportSubmissionRecord_notLogin_shouldNotAccess() throws Exception {
//        gaeSimulation.logoutUser();
//
//        mvc.perform(
//                post("/api/record/submission")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectToJson(dataBundle.submissionUploadRequest)))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void testImportAuthorRecord_login_shouldNotOverrideExistingRecord() throws Exception {
//        gaeSimulation.loginUser("test@example.com");
//
//        mvc.perform(
//                post("/api/record/author")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectToJson(dataBundle.authorUploadRequest)))
//                .andExpect(status().isCreated());
//
//        Assert.assertEquals(2,
//                authorRecordRepository.findByDataSetEquals("test@example.com").size());
//        Assert.assertTrue(
//                authorRecordRepository.findById(dataBundle.authorUploadRequest.getRecordList().get(0).getId()).isPresent());
//    }
//
//    @Test
//    public void testImportReviewRecord_login_shouldNotOverrideExistingRecord() throws Exception {
//        gaeSimulation.loginUser("test@example.com");
//
//        mvc.perform(
//                post("/api/record/review")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectToJson(dataBundle.reviewUploadRequest)))
//                .andExpect(status().isCreated());
//
//        Assert.assertEquals(2,
//                reviewRecordRepository.findByDataSetEquals("test@example.com").size());
//        Assert.assertTrue(
//                reviewRecordRepository.findById(dataBundle.reviewUploadRequest.getRecordList().get(0).getId()).isPresent());
//    }
//
//    @Test
//    public void testImportSubmissionRecord_login_shouldNotOverrideExistingRecord() throws Exception {
//        gaeSimulation.loginUser("test@example.com");
//
//        mvc.perform(
//                post("/api/record/submission")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectToJson(dataBundle.submissionUploadRequest)))
//                .andExpect(status().isCreated());
//
//        Assert.assertEquals(2,
//                submissionRecordRepository.findByDataSetEquals("test@example.com").size());
//        Assert.assertTrue(
//                submissionRecordRepository.findById(dataBundle.submissionUploadRequest.getRecordList().get(0).getId()).isPresent());
//    }
//
//    @Test
//    public void testImportAuthorRecord_loginDifferentUser_shouldWriteDifferentDataSetRecord() throws Exception {
//        gaeSimulation.loginUser("test1@example.com");
//
//        // pass dataSet maliciously
//        Assert.assertNotEquals("test1@example.com", dataBundle.authorUploadRequest.getRecordList().get(0).getDataSet());
//
//        mvc.perform(
//                post("/api/record/author")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectToJson(dataBundle.authorUploadRequest)))
//                .andExpect(status().isCreated());
//
//        Assert.assertEquals(1,
//                authorRecordRepository.findByDataSetEquals("test@example.com").size());
//        Assert.assertEquals(1,
//                authorRecordRepository.findByDataSetEquals("test1@example.com").size());
//    }
//
//    @Test
//    public void testImportReviewRecord_loginDifferentUser_shouldWriteDifferentDataSetRecord() throws Exception {
//        gaeSimulation.loginUser("test1@example.com");
//
//        // pass dataSet maliciously
//        Assert.assertNotEquals("test1@example.com", dataBundle.reviewUploadRequest.getRecordList().get(0).getDataSet());
//
//        mvc.perform(
//                post("/api/record/review")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectToJson(dataBundle.reviewUploadRequest)))
//                .andExpect(status().isCreated());
//
//        Assert.assertEquals(1,
//                reviewRecordRepository.findByDataSetEquals("test@example.com").size());
//        Assert.assertEquals(1,
//                reviewRecordRepository.findByDataSetEquals("test1@example.com").size());
//    }
//
//    @Test
//    public void testImportSubmissionRecord_loginDifferentUser_shouldWriteDifferentDataSetRecord() throws Exception {
//        gaeSimulation.loginUser("test1@example.com");
//
//        // pass dataSet maliciously
//        Assert.assertNotEquals("test1@example.com", dataBundle.submissionUploadRequest.getRecordList().get(0).getDataSet());
//
//        mvc.perform(
//                post("/api/record/submission")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectToJson(dataBundle.submissionUploadRequest)))
//                .andExpect(status().isCreated());
//
//        Assert.assertEquals(1,
//                submissionRecordRepository.findByDataSetEquals("test@example.com").size());
//        Assert.assertEquals(1,
//                submissionRecordRepository.findByDataSetEquals("test1@example.com").size());
//    }
//}
