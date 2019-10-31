<template>
  <el-menu mode="horizontal" router :default-active="menuDefaultActive" class="navbar">
    <el-menu-item index="/home">Home</el-menu-item >
    <el-menu-item index="/uploadedFiles" :disabled="!isLogin">Uploaded Files</el-menu-item>
    <el-menu-item index="/analyze" :disabled="!isLogin">Analyze</el-menu-item>
    <el-menu-item index="/importData" :disabled="!isLogin">Import Data</el-menu-item>
    <el-menu-item
      index="/logout"
      v-if="isLogin"
      @click="logout"
      v-loading.fullscreen.lock="isFullscreenLoading"
    >
      <el-button type="success" plain>Logout ({{ userNickname }})</el-button>
    </el-menu-item>
    <el-menu-item
      index="/login"
      v-if="!isLogin"
      :disabled="isApiError"
      @click="login"
      v-loading.fullscreen.lock="isFullscreenLoading"
    >
      <el-button type="success" plain :disabled="isApiError">Login</el-button>
    </el-menu-item>

    <el-menu-item style="float: right;" index="/help">Help</el-menu-item>
    <el-menu-item style="float: right;">
      <el-switch v-model="checked" @change="toggleMode" active-text="🌛" inactive-text="☀️"></el-switch>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "MenuBar",
  data() {
    return {
      isFullscreenLoading: false,
      checked: false
    };
  },
  created: function() {
    // `this` points to the vm instance
    const currentTheme = localStorage.getItem("theme")
      ? localStorage.getItem("theme")
      : null;

    if (currentTheme) {
      document.documentElement.setAttribute("data-theme", currentTheme);

      if (currentTheme === "dark") {
        this.checked = true;
        // toggleSwitch.checked = true;
      }
    }
  },
  computed: {
    menuDefaultActive() {
      // need to active the menu item if sub path is used. e.g. /analyze/new
      if (this.$route.path.includes("/analyze")) {
        return "/analyze";
      }
      return this.$route.path;
    },
    isLogin() {
      return this.$store.state.userInfo.isLogin;
    },
    userNickname() {
      return this.$store.state.userInfo.userNickname;
    },
    isApiError() {
      return this.$store.state.userInfo.isApiError;
    }
  },
  methods: {
    login() {
      // enter full screen loading and wait browser to redirect to google login page
      this.$data.isFullscreenLoading = true;
      window.location.href = this.$store.state.userInfo.loginUrl;
    },
    logout() {
      // enter full screen loading and wait browser to redirect to google login page
      this.$data.isFullscreenLoading = true;
      window.location.href = this.$store.state.userInfo.logoutUrl;
    },
    toggleMode(event) {
      if (this.checked) {
        document.documentElement.setAttribute("data-theme", "dark");
        localStorage.setItem("theme", "dark"); //add this
      } else {
        document.documentElement.setAttribute("data-theme", "light");
        localStorage.setItem("theme", "light"); //add this
      }
    }
  }
};
</script>