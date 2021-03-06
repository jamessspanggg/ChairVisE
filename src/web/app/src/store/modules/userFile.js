import axios from 'axios'

export default {
  state: {
    userFileList: [],
    userFileListStatus: {
      isLoading: true,
      isApiError: false,
      apiErrorMsg: '',
    },
    userFileForm: {
      id: '',
      fileName: '',
      fileType: '',
      tableType: '',
      createdAt: '',
    },
    userFileFormStatus: {
      isLoading: false,
      isApiError: false,
      apiErrorMsg: '',
    },
    selectedFileIndex: ''
  },
  mutations: {
    setUserFileListLoading(state, isLoading) {
      if (isLoading) {
        state.userFileListStatus.isApiError = false;
      }

      state.userFileListStatus.isLoading = isLoading;
    },

    setUserFileListApiError(state, errMsg) {
      state.userFileListStatus.isApiError = true;
      state.userFileListStatus.apiErrorMsg = errMsg;
    },

    setUserFileList(state, userFileList) {
      state.userFileList = userFileList;
    },

    addToUserFileList(state, userFileListItem) {
      state.userFileList.push(userFileListItem);
    },

    setUserFileFormLoading(state, isLoading) {
      if (isLoading) {
        state.userFileFormStatus.isApiError = false;
      }

      state.userFileFormStatus.isLoading = isLoading;
    },

    setUserFileFormApiError(state, errMsg) {
      state.userFileFormStatus.isApiError = true;
      state.userFileFormStatus.apiErrorMsg = errMsg;
    },

    setUserFileForm(state, payload) {
      state.userFileForm = payload;
    },

    setUserFileFormField(state, { field, value }) {
      state.userFileForm[field] = value;
    },

    setSelectedFileIndex(state, index) {
      state.selectedFileIndex = index;
    },
  },

  actions: {
    async getUserFileList({ commit }) {
      commit('setUserFileListLoading', true)
      axios.get('/api/files')
        .then(response => {
          commit('setUserFileList', response.data);
        })
        .catch(e => {
          commit('setUserFileListApiError', e.toString());
        })
        .finally(() => {
          commit('setUserFileListLoading', false);
        })
    },
    async deleteUploadedFile({ commit, state }) {
      commit("setPageLoadingStatus", true);
      let selectedFile = state.userFileList[state.selectedFileIndex];

      // delete user file and corresponding record
      await axios.delete("/api/files/" + selectedFile.id)
          .then(() => axios.delete("/api/record/" + selectedFile.tableType + "/" + selectedFile.id))
          .then(() => state.userFileList.splice(state.selectedFileIndex, 1)) // remove the file from list
          .then(() => commit("setPageLoadingStatus", false));
    }
  }
}
