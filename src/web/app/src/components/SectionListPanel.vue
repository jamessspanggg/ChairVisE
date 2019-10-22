<template>
  <div>
    <el-row class="addRowRightAlign" v-if="isNewPresentation">
      <el-alert title="Please create presentation before adding sections" type="info" show-icon></el-alert>
    </el-row>
    <div v-loading="isLoadingDBMetaData || isLoadingSectionList" v-if="!isNewPresentation">
      <div class="selectFiles">
        <h3>Select files for analysis</h3>

        <el-row
          v-for="userFile in userFilesSelectOptions"
          :key="userFile.type"
          class="fileSelection"
        >
          <el-select
            v-model="selectedTableTypeSection[userFile.idx]"
            :placeholder="userFile.type"
            class="fileTypeSelect"
            @change="handleFileSelectionChange"
          >
            <el-option label="Select Field" value="-1"></el-option>
            <el-option
              v-for="file in userFile.files"
              :key="file.id"
              :label="file['display_name']"
              :value="file.id"
            ></el-option>
          </el-select>
        </el-row>

        <el-row v-if="isLogin && isPresentationEditable">
          <el-select
            v-model="selectedNewSection"
            placeholder="Please select a section to add"
            style="width: 300px"
            filterable
            :disabled="sectionDropDownControl"
          >
            <el-option-group v-for="group in selections" :key="group.label" :label="group.label">
              <el-option
                v-for="item in group.options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-option-group>
          </el-select>
          <el-button class="addButtonLeft" type="success" @click="addNewSection">Add New Section</el-button>
        </el-row>
      </div>
      <br />
      <el-alert v-if="isSectionListApiError" :title="sectionListApiErrorMsg" type="error" show-icon></el-alert>
      <abstract-section-detail
        class="presentation-section"
        v-for="section in sectionList"
        :sectionDetail="section"
        :key="section.id"
        :presentationId="presentationId"
      />
    </div>
  </div>
</template>

<script>
import AbstractSectionDetail from "@/components/AbstractSectionDetail.vue";
import { ID_NEW_PRESENTATION } from "@/common/const";
import PredefinedQueries from "@/store/data/predefinedQueries";

export default {
  props: {
    presentationId: String
  },
  watch: {
    presentationId: "fetchSectionList"
  },
  data() {
    return {
      selectedNewSection: "",
      selectedTableTypeSection: [],
      checkedFilesID: [],
      selectedTableTypes: [],
      selections: [],
      sectionDropDownControl: true
    };
  },
  computed: {
    isLogin() {
      return this.$store.state.userInfo.isLogin;
    },

    isPresentationEditable() {
      return this.$store.state.presentation.isPresentationEditable;
    },

    predefinedSections() {
      let sectionOptionsGroup = {};
      // grouping the predefined queries
      for (let key in PredefinedQueries) {
        if (!PredefinedQueries.hasOwnProperty(key)) {
          continue;
        }
        let groupName = PredefinedQueries[key].group;
        if (sectionOptionsGroup[groupName] === undefined) {
          sectionOptionsGroup[groupName] = [];
        }
        sectionOptionsGroup[groupName].push({
          value: key,
          label: PredefinedQueries[key].name
        });
      }

      // generate to format that element ui requires
      let sectionOptions = [];
      for (let groupName in sectionOptionsGroup) {
        if (!sectionOptionsGroup.hasOwnProperty(groupName)) {
          continue;
        }
        sectionOptions.push({
          label: groupName,
          options: sectionOptionsGroup[groupName]
        });
      }

      return sectionOptions;
    },

    userFileList() {
      return this.$store.state.userFile.userFileList;
    },

    userFileIdMappings() {
      let mappings = {};

      let userFiles = this.userFileList;

      for (let i in userFiles) {
        let key = userFiles[i].id;
        let keyAttrs = {};

        keyAttrs["table_type"] = userFiles[i].tableType + " record";
        keyAttrs["display_name"] =
          userFiles[i].fileName + " (" + userFiles[i].fileType + ")";

        mappings[key] = keyAttrs;
      }

      return mappings;
    },

    allUserTableType() {
      let tableTypes = [];

      let userFiles = this.userFileList;

      for (let i in userFiles) {
        if (!tableTypes.includes(userFiles[i].tableType + " record")) {
          tableTypes.push(userFiles[i].tableType + " record");
        }
      }

      return tableTypes;
    },

    userTableTypesToSectionArrIdx() {
      let tableTypeToIdx = {};

      for (let i = 0; i < this.allUserTableType.length; i++) {
        tableTypeToIdx[this.allUserTableType[i]] = i;
      }

      return tableTypeToIdx;
    },

    userFilesSelectOptions() {
      let userFileMappings = [];
      let files = {};
      let userFiles = this.userFileList;

      for (let i in userFiles) {
        let currFile = {};

        currFile["id"] = userFiles[i].id;
        currFile["display_name"] =
          userFiles[i].fileName + " (" + userFiles[i].fileType + ")";

        let tableType = userFiles[i].tableType + " record";
        if (!(tableType in files)) {
          files[tableType] = [];
        }
        files[tableType].push(currFile);
      }

      let tableTypeIdx = 0;
      for (let type in files) {
        let tempFile = {};
        tempFile["type"] = type;
        tempFile["idx"] = tableTypeIdx;
        tempFile["files"] = files[type];
        userFileMappings.push(tempFile);

        tableTypeIdx++;
      }

      return userFileMappings;
    },

    existingTableTypeToPredefindeSectionMapping() {
      let tableTypeToPredefindeSectionMapping = {};

      for (let idx in this.predefinedSections) {
        let tableTypes = this.predefinedSections[idx].label.split("+");

        // Get all the combinations of predefined mapping from the current user uploaded file types
        if (
          tableTypes.every(val =>
            this.allUserTableType.includes(val.trim().toLowerCase())
          )
        ) {
          tableTypeToPredefindeSectionMapping[
            this.predefinedSections[idx].label
          ] = this.predefinedSections[idx];
        }
      }

      return tableTypeToPredefindeSectionMapping;
    },

    isNewPresentation() {
      return this.presentationId === ID_NEW_PRESENTATION;
    },

    sectionList() {
      let userFiles = this.userFileList;
      let sectionList = this.$store.state.section.sectionList;

      let mappings = {};

      for (let i in userFiles) {
        let key = userFiles[i].id;
        let keyAttrs = {};

        mappings[key] =
          userFiles[i].fileName + " (" + userFiles[i].fileType + ")";
      }

      for (let index in sectionList) {
        let fileNames = [];
        for (let key in sectionList[index]["filesId"]) {
          let fileType = key.split("_").join(" ");

          let fileName = mappings[sectionList[index]["filesId"][key]];
          fileNames.push(fileType + ": " + fileName);
        }

        sectionList[index]["filesName"] = fileNames.join(", ");
      }

      return sectionList;
    },
    isLoadingSectionList() {
      return this.$store.state.section.sectionListStatus.isLoading;
    },
    isSectionListApiError() {
      return this.$store.state.section.sectionListStatus.isApiError;
    },
    sectionListApiErrorMsg() {
      return this.$store.state.section.sectionListStatus.apiErrorMsg;
    },
    isLoadingDBMetaData() {
      return this.$store.state.dbMetaData.entitiesStatus.isLoading;
    }
  },
  components: {
    AbstractSectionDetail
  },
  mounted() {
    this.fetchSectionList();
    this.$store.dispatch("fetchDBMetaDataEntities");
    this.$store.dispatch("getUserFileList");
  },
  methods: {
    fetchSectionList() {
      if (this.isNewPresentation) {
        this.$store.commit("clearSectionList");
      } else {
        this.$store.dispatch("fetchSectionList", this.presentationId);
      }
    },

    addNewSection() {
      let ids = this.fetchSelectedIDs();

      if (this.selectedNewSection.length === 0) {
        return;
      }
      this.$store
        .dispatch("addSectionDetail", {
          presentationId: this.presentationId,
          selectedNewSection: this.selectedNewSection,
          dataSet: this.$store.state.userInfo.userEmail,
          keys: ids
        })
        .then(() => {
          this.selectedNewSection = "";
          this.selectedTableTypeSection = [];
          this.selectedTableTypes = [];
          this.selections = [];
          this.sectionDropDownControl = true;
        });
    },

    fetchSelectedIDs() {
      let ids = { author_id: -1, submission_id: -1, review_id: -1 };
      let tableTypeToIDKeysMapping = {
        "author record": "author_id",
        "submission record": "submission_id",
        "review record": "review_id"
      };

      for (let i = 0; i < this.selectedTableTypeSection.length; i++) {
        if (
          typeof this.selectedTableTypeSection[i] != "undefined" &&
          this.selectedTableTypeSection[i] !== "" &&
          this.selectedTableTypeSection[i] != -1
        ) {
          let tableType = this.userFileIdMappings[
            this.selectedTableTypeSection[i]
          ]["table_type"];

          ids[
            tableTypeToIDKeysMapping[tableType]
          ] = this.selectedTableTypeSection[i];
        }
      }

      return ids;
    },

    handleFileSelectionChange(val) {
      // -1: Reset the field.
      if (val === "-1") {
        // Set all the unselected values to empty and selected ids to -1
        let hasSelectedElements = false; // this is a flag to decide of the section selected should be enabled

        for (let idx in this.selectedTableTypeSection) {
          if (this.selectedTableTypeSection[idx] === "-1") {
            this.selectedTableTypes[idx] = "";
            this.checkedFilesID[idx] = -1;
          } else if (this.selectedTableTypeSection[idx] >= "0") {
            // assumption that the file ids always >= 0
            hasSelectedElements = true;
          }
        }

        // to enable / disable the section select
        if (hasSelectedElements) {
          this.sectionDropDownControl = false;
        } else {
          this.sectionDropDownControl = true;
        }
      } else {
        let tableType = this.userFileIdMappings[val]["table_type"];
        let tableTypeIdx = this.userTableTypesToSectionArrIdx[tableType];

        this.selectedTableTypes[tableTypeIdx] = tableType;
        this.checkedFilesID[tableTypeIdx] = val;

        this.sectionDropDownControl = false;
      }

      this.handlePredefinedSectionChange();
    },

    handlePredefinedSectionChange() {
      this.selections = [];

      for (let idx in this.existingTableTypeToPredefindeSectionMapping) {
        let tableTypes = this.existingTableTypeToPredefindeSectionMapping[
          idx
        ].label
          .split("+")
          .map(item => item.trim().toLowerCase());

        if (
          this.hasAllSeclectedTableTypes(tableTypes, this.selectedTableTypes)
        ) {
          this.selections.push(
            this.existingTableTypeToPredefindeSectionMapping[idx]
          );
        }
      }
    },

    hasAllSeclectedTableTypes(tableTypes, selectedTableTypes) {
      let visitedSelectedTableTypes = [];

      for (let j = 0; j < selectedTableTypes.length; j++) {
        if (
          selectedTableTypes[j] === "" ||
          typeof selectedTableTypes[j] == "undefined"
        ) {
          visitedSelectedTableTypes[j] = true;
        } else {
          visitedSelectedTableTypes[j] = false;
        }
      }

      for (let i = 0; i < tableTypes.length; i++) {
        let isFound = false;
        for (let j = 0; j < selectedTableTypes.length; j++) {
          if (tableTypes[i] == selectedTableTypes[j]) {
            visitedSelectedTableTypes[j] = true;
            isFound = true;
            break;
          }
        }

        if (!isFound) {
          return false;
        }
      }

      for (let j = 0; j < visitedSelectedTableTypes.length; j++) {
        if (!visitedSelectedTableTypes[j]) {
          return false;
        }
      }

      return true;
    }
  }
};
</script>

<style scoped>
.addButtonLeft {
  margin-left: 10px;
}

.addRowRightAlign {
  text-align: right;
}

.addRowCenterAlign {
  text-align: center;
}

.selectFiles {
  background-color: #f5f5f5;
  padding: 1rem 3rem 1.75rem 3rem;
}

.fileSelection {
  margin: 1.5rem 0;
}

.fileTypeSelect {
  width: 300px;
}
</style>