import axios from 'axios'
import PredefinedQueries from "@/store/data/predefinedQueries"

export default {
  state: {
    sectionList: [],
    sectionListStatus: {
      isLoading: false,
      isApiError: false,
      apiErrorMsg: '',
    }
  },
  mutations: {
    setSectionListLoading(state, isLoading) {
      if (isLoading) {
        state.sectionListStatus.isApiError = false;
      }
      state.sectionListStatus.isLoading = isLoading;
    },

    setSectionListApiError(state, msg) {
      state.sectionListStatus.isApiError = true;
      state.sectionListStatus.apiErrorMsg = msg;
    },

    clearSectionList(state) {
      state.sectionList = []
    },

    addSectionDetail(state, payload) {
      state.sectionList.push(Object.assign({
        result: [],
        previewResult: [],
        status: {
          isLoading: false,
          isApiError: false,
          apiErrorMsg: '',
          apiErrorMsgDetail: '',
        }
      }, payload))
    },

    copySectionDetail(state, payload) {
      state.sectionList.push(Object.assign({
        result: [],
        previewResult: [],
        status: {
          isLoading: false,
          isApiError: false,
          apiErrorMsg: '',
          apiErrorMsgDetail: '',
        }
      }, payload))
    },

    deleteSectionDetail(state, payload) {
      let index = state.sectionList.findIndex(s => s.presentationSection.id === payload);
      state.sectionList.splice(index, 1)
    },

    updateSectionDetail(state, { id, title, description, type, dataSet, selections, involvedRecords, filters, joiners, groupers, sorters, extraData }) {
      let section = findSectionDetailById(state.sectionList, id);

      section.title = title;
      section.description = description;
      section.type = type;
      section.dataSet = dataSet;
      section.selections = selections;
      section.involvedRecords = involvedRecords;
      section.filters = filters;
      section.joiners = joiners;
      section.groupers = groupers;
      section.sorters = sorters;
      section.extraData = extraData;
    },

    setSectionDetailLoading(state, { id, isLoading }) {
      let section = findSectionDetailById(state.sectionList, id);
      if (isLoading) {
        section.status.isApiError = false;
      }
      section.status.isLoading = isLoading;
    },

    setSectionDetailApiError(state, { id, msg, msgDetail }) {
      let section = findSectionDetailById(state.sectionList, id);
      section.status.isApiError = true;
      section.status.apiErrorMsg = msg;
      section.status.apiErrorMsgDetail = msgDetail;
    },

    updateSectionAnalysisResult(state, { id, result }) {
      let section = findSectionDetailById(state.sectionList, id);
      section.result = result;
    },

    updateSectionAnalysisPreviewResult(state, { id, result }) {
      let section = findSectionDetailById(state.sectionList, id);
      section.previewResult = result;
    }
  },
  actions: {
    async fetchSectionList({ commit }, presentationId) {
      commit('setSectionListLoading', true);

      await axios.get(`/api/presentations/${presentationId}/sections`)
        .then(response => {
          commit('clearSectionList');
          response.data.forEach(s => {
            commit('addSectionDetail', s)
          });
        })
        .catch(e => {
          commit('setSectionListApiError', e.toString())
        })
        .finally(() => {
          commit('setSectionListLoading', false);
        })
    },

    async addSectionDetail({ commit }, { presentationId, selectedNewSection, dataSet, keys }) {
      commit('setSectionListLoading', true);

      let newSection = PredefinedQueries[selectedNewSection].data;
      newSection = JSON.stringify(newSection).replace(/\${PLACEHOLDER_DATA_SET}/g, dataSet);
      newSection = newSection.replace(/\${PLACEHOLDER_AUTHOR_FILE_ID}/g, keys['author_id']);
      newSection = newSection.replace(/\${PLACEHOLDER_SUBMISSION_FILE_ID}/g, keys['submission_id']);
      newSection = newSection.replace(/\${PLACEHOLDER_REVIEW_FILE_ID}/g, keys['review_id']);
      newSection = JSON.parse(newSection);

      let fileIds = PredefinedQueries[selectedNewSection].fileIds;
      fileIds = JSON.stringify(fileIds).replace(/\${PLACEHOLDER_AUTHOR_FILE_ID}/g, keys['author_id']);
      fileIds = fileIds.replace(/\${PLACEHOLDER_SUBMISSION_FILE_ID}/g, keys['submission_id']);
      fileIds = fileIds.replace(/\${PLACEHOLDER_REVIEW_FILE_ID}/g, keys['review_id']);
      fileIds = JSON.parse(fileIds);

      let bodyContent = {
        presentationSection: newSection,
        fileIds: fileIds
      }

      await axios.post(`/api/presentations/${presentationId}/sections`, bodyContent)
        .then(response => {
          commit('addSectionDetail', response.data)
        })
        .catch(e => {
          commit('setSectionListApiError', e.toString())
        })
        .finally(() => {
          commit('setSectionListLoading', false);
        })
    },

    async copySectionDetail({ commit }, { id, presentationId }) {
      commit('setSectionListLoading', true);

      await axios.post(`/api/presentations/${presentationId}/sections/${id}`)
        .then(response => {
          commit('copySectionDetail', response.data)
        })
        .catch(e => {
          commit('setSectionListApiError', e.toString())
        })
        .finally(() => {
          commit('setSectionListLoading', false);
        })
    },

    async saveSectionDetail({ commit }, { id, presentationId, title, description, type, dataSet, selections, involvedRecords, filters, joiners, groupers, sorters, extraData }) {
      commit('setSectionDetailLoading', { id, isLoading: true });

      await axios.put(`/api/presentations/${presentationId}/sections/${id}`, {
        title,
        description,
        type,
        dataSet,
        selections,
        involvedRecords,
        filters,
        joiners,
        groupers,
        sorters,
        extraData
      })
        .then(response => {
          let section = response.data;
          commit('updateSectionDetail', {
            id: section.id,
            title: section.title,
            description: section.description,
            type: section.type,
            dataSet: section.dataSet,
            selections: section.selections,
            involvedRecords: section.involvedRecords,
            filters: section.filters,
            joiners: section.joiners,
            groupers: section.groupers,
            sorters: section.sorters,
            extraData: section.extraData
          })
        })
        .catch(e => {
          commit('setSectionDetailApiError', { id, msg: e.toString(), msgDetail: JSON.stringify(e.response) });
        })
        .finally(() => {
          commit('setSectionDetailLoading', { id, isLoading: false });
        })
    },

    async deleteSectionDetail({ commit }, { id, presentationId }) {
      commit('setSectionDetailLoading', { id, isLoading: true });

      await axios.delete(`/api/presentations/${presentationId}/sections/${id}`)
        .then(() => {
          commit('deleteSectionDetail', id)
        })
        .catch(e => {
          commit('setSectionDetailApiError', { id, msg: e.toString(), msgDetail: JSON.stringify(e.response) });
          commit('setSectionDetailLoading', { id, isLoading: false });
        })
    },

    async sendPreviewAnalysisRequest({ commit }, { presentationId, id, dataSet, selections, involvedRecords, filters, joiners, groupers, sorters }) {
      commit('setSectionDetailLoading', { id, isLoading: true });

      await axios.post(`/api/presentations/${presentationId}/analysis`, {
        dataSet,
        selections,
        involvedRecords,
        filters,
        joiners,
        groupers,
        sorters
      })
        .then(response => {
          commit('updateSectionAnalysisPreviewResult', { id, result: response.data });
        })
        .catch(e => {
          commit('setSectionDetailApiError', { id, msg: e.toString(), msgDetail: JSON.stringify(e.response) });
        })
        .finally(() => {
          commit('setSectionDetailLoading', { id, isLoading: false });
        })
    },

    async sendAnalysisRequest({ state, commit }, { id, presentationId }) {
      let sectionToAnalysis = findSectionDetailById(state.sectionList, id);
      let presentationSectionToAnalysis = sectionToAnalysis.presentationSection;
      commit('setSectionDetailLoading', { id: presentationSectionToAnalysis.id, isLoading: true });

      await axios.post(`/api/presentations/${presentationId}/analysis`, {
        dataSet: presentationSectionToAnalysis.dataSet,
        selections: presentationSectionToAnalysis.selections,
        involvedRecords: presentationSectionToAnalysis.involvedRecords,
        filters: presentationSectionToAnalysis.filters,
        joiners: presentationSectionToAnalysis.joiners,
        groupers: presentationSectionToAnalysis.groupers,
        sorters: presentationSectionToAnalysis.sorters
      })
        .then(response => {
          commit('updateSectionAnalysisResult', { id: presentationSectionToAnalysis.id, result: response.data });
        })
        .catch(e => {
          commit('setSectionDetailApiError',
            { id: presentationSectionToAnalysis.id, msg: e.toString(), msgDetail: JSON.stringify(e.response) });
        })
        .finally(() => {
          commit('setSectionDetailLoading', { id: presentationSectionToAnalysis.id, isLoading: false });
        })
    }
  }
}

function findSectionDetailById(sectionList, id) {
  return sectionList.find(element => element.presentationSection.id === id);
}