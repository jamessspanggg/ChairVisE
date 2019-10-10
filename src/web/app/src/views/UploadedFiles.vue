<template>
  <div>
    <el-alert
      title="You need to login-in to view the page"
      type="error"
      v-if="!isLogin && !isAppLoading"
    >
      <el-button type="warning" plain size="mini" @click="navigateToHomPage">Return to the Home Page</el-button>
    </el-alert>

    <div v-if="isLogin">
      <h3 class="files-layout-centered file-header">All Uploaded Files</h3>

      <el-table
        :data="userFile"
        :default-sort="{prop: 'datetime', order: 'descending'}"
        class="files-layout-centered"
      >
        <el-table-column label="Date" prop="datetime"></el-table-column>
        <el-table-column label="File Name" prop="name"></el-table-column>
        <el-table-column label="File Type" prop="type"></el-table-column>
        <el-table-column label="Operations">
          <template>
            <el-button icon="el-icon-delete" type="danger"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template> 

<script>
import moment from "moment";

export default {
  name: "UploadedFiles",
  mounted() {
    this.$store.dispatch("getUserFileList");
  },
  watch: {
    isError() {
      if (!this.isError) {
        return;
      }
      this.$notify.error({
        title: "Files list API request fail",
        message: this.$store.state.userFile.userFileListStatus.apiErrorMsg,
        duration: 0
      });
    }
  },
  computed: {
    isLogin() {
      return this.$store.state.userInfo.isLogin;
    },
    isAppLoading: function() {
      return (
        this.$store.state.isPageLoading ||
        this.$store.state.dbMetaData.entitiesStatus.isLoading
      );
    },
    isError() {
      return this.$store.state.userFile.userFileListStatus.isApiError;
    },
    userFile() {
      let files = [];
      let userFiles = this.$store.state.userFile.userFileList;

      for (let i in this.$store.state.userFile.userFileList) {
        let currFile = {};

        currFile["id"] = userFiles[i].id;
        currFile["name"] = userFiles[i].fileName;
        currFile["type"] = userFiles[i].fileType;
        currFile["datetime"] = moment(userFiles[i].createdAt).format(
          "YYYY-MM-DD HH:mm"
        );

        files.push(currFile);
      }

      return files;
    }
  }
};
</script>

<style scoped>
.files-layout-centered {
  width: 80%;
  margin: 1.5rem auto;
}

.file-header {
  margin-bottom: 2rem;
}
</style>