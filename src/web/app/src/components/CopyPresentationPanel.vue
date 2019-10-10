<template>
  <div>
    <h4>Presentations created by me</h4>
    <el-menu :default-active="$route.path" v-loading="isLoading" router>
      <li v-for="presentation in presentations" v-if="presentationId !== presentation.id" :key="presentation.id">
        <el-menu-item @click="handlesSelectPresentation(presentation)">
          <i class="el-icon-document"></i>
          <span slot="title">
          {{ presentation.name }}
        </span>
        </el-menu-item>
      </li>
    </el-menu>
  </div>
</template>

<script>
  export default {
    props: {
      presentationId: {
        type: String,
        required: true
      },
      sectionId: {
        type: String,
        required: true
      }
    },

    data() {
      return {}
    },
    watch: {
      'isError'() {
        if (!this.isError) {
          return
        }
        this.$notify.error({
          title: 'Presentation list API request fail',
          message: this.$store.state.presentation.presentationListStatus.apiErrorMsg,
          duration: 0
        });
      }
    },
    computed: {
      isLoading() {
        return this.$store.state.presentation.presentationListStatus.isLoading
          || this.$store.state.presentation.presentationFormStatus.isLoading
          || this.$store.state.section.sectionListStatus.isLoading
          || this.$store.state.section.sectionList.some(s => s.status.isLoading)
      },
      presentations() {
        return this.$store.state.presentation.presentationList
      },
      isError() {
        return this.$store.state.presentation.presentationListStatus.isApiError
      },
    },
    methods: {
      handlesSelectPresentation(presentation) {
        console.log(presentation.id + " " + this.sectionId);
        this.$store.dispatch('copySectionDetail', {
          presentationId: presentation.id,
          id: this.sectionId
        }).then(() => {
          if (this.isError) {
            return
          }
          this.$router.replace({
            name: 'analyze',
            params: {
              id: presentation.id
            }
          });
        });
      }
    },
    mounted() {
      this.$store.dispatch('getPresentationList')
    }
  }
</script>
