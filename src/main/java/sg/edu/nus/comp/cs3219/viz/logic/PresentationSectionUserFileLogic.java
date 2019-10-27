package sg.edu.nus.comp.cs3219.viz.logic;

import org.springframework.stereotype.Component;
import sg.edu.nus.comp.cs3219.viz.common.entity.PresentationSection;
import sg.edu.nus.comp.cs3219.viz.common.entity.PresentationSectionUserFile;
import sg.edu.nus.comp.cs3219.viz.storage.repository.PresentationSectionUserFileRepository;

import java.util.List;

@Component
public class PresentationSectionUserFileLogic {
    private PresentationSectionUserFileRepository presentationSectionUserFileRepository;

    public PresentationSectionUserFileLogic(PresentationSectionUserFileRepository presentationSectionUserFileRepository) {
        this.presentationSectionUserFileRepository = presentationSectionUserFileRepository;
    }

    public List<PresentationSectionUserFile> findAllByPresentationSection(PresentationSection presentationSection) {
        return presentationSectionUserFileRepository.findAllByPresentationSection(presentationSection);
    }

    public PresentationSectionUserFile savePresentationUserFile(PresentationSection presentationSection, Long fileId) {
        PresentationSectionUserFile newPresentationUserFile = new PresentationSectionUserFile();

        newPresentationUserFile.setPresentationSection(presentationSection);
        newPresentationUserFile.setUserFile(fileId);

        return presentationSectionUserFileRepository.save(newPresentationUserFile);
    }
}
