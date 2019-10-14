package sg.edu.nus.comp.cs3219.viz.logic;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest.AuthorUploadRequest;
import sg.edu.nus.comp.cs3219.viz.common.entity.UserFile;
import sg.edu.nus.comp.cs3219.viz.common.entity.record.ReviewRecord;
import sg.edu.nus.comp.cs3219.viz.common.entity.record.SubmissionAuthorRecord;
import sg.edu.nus.comp.cs3219.viz.common.entity.record.SubmissionRecord;
import sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest.ReviewUploadRequest;
import sg.edu.nus.comp.cs3219.viz.common.entity.uploadrequest.SubmissionUploadRequest;
import sg.edu.nus.comp.cs3219.viz.storage.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecordLogic {
    private AuthorRecordRepository authorRecordRepository;

    private SubmissionRecordRepository submissionRecordRepository;

    private SubmissionAuthorRecordRepository submissionAuthorRecordRepository;

    private ReviewRecordRepository reviewRecordRepository;

    private UserFileRepository userFileRepository;

    public RecordLogic(AuthorRecordRepository authorRecordRepository,
                       SubmissionRecordRepository submissionRecordRepository,
                       SubmissionAuthorRecordRepository submissionAuthorRecordRepository,
                       ReviewRecordRepository reviewRecordRepository,
                       UserFileRepository userFileRepository) {
        this.authorRecordRepository = authorRecordRepository;
        this.submissionRecordRepository = submissionRecordRepository;
        this.submissionAuthorRecordRepository = submissionAuthorRecordRepository;
        this.reviewRecordRepository = reviewRecordRepository;
        this.userFileRepository = userFileRepository;
    }

    @Transactional
    public void persistAuthorRecordForDataSet(String dataSet, AuthorUploadRequest authorUploadRequest) {
        UserFile userFile = userFileRepository.save(new UserFile(dataSet, authorUploadRequest.getFileName(),
                authorUploadRequest.getFileType()));
        authorRecordRepository.saveAll(authorUploadRequest.getRecordList().stream().peek(r -> {
            // should not set ID when creating records
            r.setId(null);
            // should set dataSet
            r.setDataSet(dataSet);
            // the other field can be arbitrary
            r.setFileId(userFile.getId());
        }).collect(Collectors.toList()));
    }

    @Transactional
    public void persistReviewRecordForDataSet(String dataSet, ReviewUploadRequest reviewUploadRequest) {
        UserFile userFile = userFileRepository.save(new UserFile(dataSet, reviewUploadRequest.getFileName(),
                reviewUploadRequest.getFileType()));
        reviewRecordRepository.saveAll(reviewUploadRequest.getRecordList().stream().peek(r -> {
            // should not set ID when creating records
            r.setId(null);
            // should set dataSet
            r.setDataSet(dataSet);
            // the other field can be arbitrary
            r.setFileId(userFile.getId());
        }).collect(Collectors.toList()));
    }

    @Transactional
    public void persistSubmissionRecordForDataSet(String dataSet, SubmissionUploadRequest submissionUploadRequest) {
        UserFile userFile = userFileRepository.save(new UserFile(dataSet, submissionUploadRequest.getFileName(),
                submissionUploadRequest.getFileType()));
        submissionRecordRepository.saveAll(submissionUploadRequest.getRecordList().stream().peek(s -> {
            // should not set ID when creating records
            s.setId(null);
            // should set dataSet
            s.setDataSet(dataSet);
            // create many to many relationship for authors
            List<SubmissionAuthorRecord> submissionAuthorRecords = s.getAuthors().stream()
                    .map(authorName -> {
                        SubmissionAuthorRecord existing = submissionAuthorRecordRepository
                                .findFirstByNameEqualsAndDataSetEqualsAndFileId(authorName, dataSet, userFile.getId());
                        if (existing == null) {
                            existing = new SubmissionAuthorRecord();
                            existing.setDataSet(dataSet);
                            existing.setName(authorName);
                            existing.setFileId(userFile.getId());
                            existing = submissionAuthorRecordRepository.save(existing);
                        }
                        return existing;
                    })
                    .collect(Collectors.toList());
            s.setAuthorSet(submissionAuthorRecords);
            // the other field can be arbitrary
            s.setFileId(userFile.getId());
        }).collect(Collectors.toList()));
    }
}
