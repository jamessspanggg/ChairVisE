package sg.edu.nus.comp.cs3219.viz.ui.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.comp.cs3219.viz.common.datatransfer.AccessLevel;
import sg.edu.nus.comp.cs3219.viz.common.entity.Presentation;
import sg.edu.nus.comp.cs3219.viz.common.entity.PresentationSection;
import sg.edu.nus.comp.cs3219.viz.common.entity.PresentationSectionUserFile;
import sg.edu.nus.comp.cs3219.viz.common.entity.PresentationSectionRequestResponse;
import sg.edu.nus.comp.cs3219.viz.common.exception.PresentationNotFoundException;
import sg.edu.nus.comp.cs3219.viz.common.exception.PresentationSectionNotFoundException;
import sg.edu.nus.comp.cs3219.viz.logic.GateKeeper;
import sg.edu.nus.comp.cs3219.viz.logic.PresentationLogic;
import sg.edu.nus.comp.cs3219.viz.logic.PresentationSectionLogic;
import sg.edu.nus.comp.cs3219.viz.logic.PresentationSectionUserFileLogic;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PresentationSectionController extends BaseRestController {

    private final PresentationLogic presentationLogic;
    private final PresentationSectionLogic presentationSectionLogic;
    private final PresentationSectionUserFileLogic presentationSectionUserFileLogic;

    private final GateKeeper gateKeeper;

    public PresentationSectionController(PresentationLogic presentationLogic,
                                         PresentationSectionLogic presentationSectionLogic,
                                         PresentationSectionUserFileLogic presentationSectionUserFileLogic,
                                         GateKeeper gateKeeper) {
        this.presentationLogic = presentationLogic;
        this.presentationSectionLogic = presentationSectionLogic;
        this.presentationSectionUserFileLogic = presentationSectionUserFileLogic;
        this.gateKeeper = gateKeeper;
    }

    @GetMapping("/presentations/{presentationId}/sections")
    public List<PresentationSectionRequestResponse> all(@PathVariable Long presentationId) {
        Presentation presentation = presentationLogic.findById(presentationId)
                .orElseThrow(() -> new PresentationNotFoundException(presentationId));
        gateKeeper.verifyAccessForPresentation(presentation, AccessLevel.CAN_READ);

        List<PresentationSectionRequestResponse> presentationSectionRequestResponses = new ArrayList<>();

        List<PresentationSection> presentationSections = presentationSectionLogic.findAllByPresentation(presentation);
        for (PresentationSection presentationSection : presentationSections) {
            List<PresentationSectionUserFile> presentationSectionUserFiles = presentationSectionUserFileLogic.findAllByPresentationSection(presentationSection);

            PresentationSectionRequestResponse presentationSectionRequestResponse = new PresentationSectionRequestResponse();
            presentationSectionRequestResponse.setPresentationSection(presentationSection);
            presentationSectionRequestResponse.setFileIds(presentationSectionUserFiles.stream().map((presentationSectionUserFile) -> presentationSectionUserFile.getFileId()).collect(Collectors.toList()));

            presentationSectionRequestResponses.add(presentationSectionRequestResponse);
        }

        return presentationSectionRequestResponses;
    }

    @PostMapping("/presentations/{presentationId}/sections")
    public ResponseEntity<?> newPresentationSection(@PathVariable Long presentationId, @RequestBody PresentationSectionRequestResponse presentationSectionRequestResponse) throws URISyntaxException {
        Presentation presentation = presentationLogic.findById(presentationId)
                .orElseThrow(() -> new PresentationNotFoundException(presentationId));
        gateKeeper.verifyAccessForPresentation(presentation, AccessLevel.CAN_WRITE);

        PresentationSection newPresentationSection = presentationSectionLogic.saveForPresentation(presentation, presentationSectionRequestResponse.getPresentationSection());

        List<Long> newFileIds = new ArrayList<>();
        for (Long fileId : presentationSectionRequestResponse.getFileIds()) {
            PresentationSectionUserFile newPresentationSectionUserFile = presentationSectionUserFileLogic.savePresentationUserFile(newPresentationSection, fileId);
            newFileIds.add(newPresentationSectionUserFile.getFileId());
        }

        PresentationSectionRequestResponse newPresentationSectionRequestResponse = new PresentationSectionRequestResponse();
        newPresentationSectionRequestResponse.setPresentationSection(newPresentationSection);
        newPresentationSectionRequestResponse.setFileIds(newFileIds);

        return ResponseEntity
                .created(new URI("/presentations/" + presentationId + "/section/" + newPresentationSection.getId()))
                .body(newPresentationSectionRequestResponse);
    }

    @PutMapping("/presentations/{presentationId}/sections/{sectionId}")
    public ResponseEntity<?> updatePresentationSection(@PathVariable Long presentationId, @PathVariable Long sectionId,
                                                       @RequestBody PresentationSection newPresentationSection) throws URISyntaxException {
        PresentationSection oldPresentationSection = presentationSectionLogic.findById(sectionId)
                .orElseThrow(() -> new PresentationSectionNotFoundException(presentationId, sectionId));

        Presentation presentation = oldPresentationSection.getPresentation();
        gateKeeper.verifyAccessForPresentation(presentation, AccessLevel.CAN_WRITE);

        PresentationSection updatedPresentationSection =
                presentationSectionLogic.updatePresentation(oldPresentationSection, newPresentationSection);

        return ResponseEntity
                .created(new URI("/presentations/" + presentationId + "/section/" + updatedPresentationSection.getId()))
                .body(updatedPresentationSection);
    }

    @DeleteMapping("/presentations/{presentationId}/sections/{sectionId}")
    public ResponseEntity<?> deletePresentationSection(@PathVariable Long presentationId, @PathVariable Long sectionId) {
        PresentationSection presentationSection = presentationSectionLogic.findById(sectionId)
                .orElseThrow(() -> new PresentationSectionNotFoundException(presentationId, sectionId));

        Presentation presentation = presentationSection.getPresentation();
        gateKeeper.verifyAccessForPresentation(presentation, AccessLevel.CAN_WRITE);

        presentationSectionLogic.deleteById(sectionId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/presentations/{presentationId}/sections/{sectionId}")
    public ResponseEntity<?> copyPresentationSection(@PathVariable Long presentationId, @PathVariable Long sectionId) throws URISyntaxException {
        Presentation presentation = presentationLogic.findById(presentationId)
                .orElseThrow(() -> new PresentationNotFoundException(presentationId));
        gateKeeper.verifyAccessForPresentation(presentation, AccessLevel.CAN_WRITE);

        PresentationSection presentationSection = presentationSectionLogic.findById(sectionId)
                .orElseThrow(() -> new PresentationSectionNotFoundException(presentationId, sectionId));

        List<PresentationSectionUserFile> presentationSectionUserFiles = presentationSectionUserFileLogic.findAllByPresentationSection(presentationSection);

        PresentationSection newPresentationSection = presentationSectionLogic.saveForPresentation(presentation, presentationSection);

        List<Long> newFileIds = new ArrayList<>();
        for (PresentationSectionUserFile presentationSectionUserFile : presentationSectionUserFiles) {
            PresentationSectionUserFile newPresentationSectionUserFile = presentationSectionUserFileLogic.savePresentationUserFile(newPresentationSection, presentationSectionUserFile.getFileId());
            newFileIds.add(newPresentationSectionUserFile.getFileId());
        }

        PresentationSectionRequestResponse newPresentationSectionRequestResponse = new PresentationSectionRequestResponse();
        newPresentationSectionRequestResponse.setPresentationSection(newPresentationSection);
        newPresentationSectionRequestResponse.setFileIds(newFileIds);


        return ResponseEntity
                .created(new URI("/presentations/" + presentationId + "/section/" + newPresentationSection.getId()))
                .body(newPresentationSectionRequestResponse);
    }

}
