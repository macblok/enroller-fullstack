<template>
  <div id="app">
    <h1>
      <img src="./assets/logo.svg" alt="Enroller" class="logo" />
      System do zapisów na zajęcia
    </h1>
    <div v-if="authenticatedUsername">
      <h2>
        Witaj {{ authenticatedUsername }}!
        <a
          @click="logout()"
          class="float-right button-outline button"
        >Wyloguj</a>
      </h2>
      <meetings-page :username="authenticatedUsername"></meetings-page>
    </div>
    <div v-else>
      <button :class="registering ? 'button-outline' : ''" @click="registering = false">Loguję się</button> <!-- ternary operator, uzyskiwanie reaktywnego przycisku -->
      <button :class="!registering ? 'button-outline' : ''" @click="registering = true">Rejestruję się</button>

      <login-form v-if="!registering" @login="login($event)"></login-form>
      <login-form v-if="registering" @login="register($event)" button-label="Zarejestruj się"></login-form>
      <!-- odnosimy sie do wlasciwosci buttonLabel w LoginForm.vue -->
    </div>
  </div>
</template>

<script>
import "milligram"; //milligram - CSS framework
import LoginForm from "./LoginForm";
import MeetingsPage from "./meetings/MeetingsPage";

export default {
  components: { LoginForm, MeetingsPage },
  data() {
    return {
      authenticatedUsername: "",
      registering: false
    };
  },
  methods: {
    login(user) {
      this.authenticatedUsername = user.login;
    },
    register(user) {},
    logout() {
      this.authenticatedUsername = "";
    }
  }
};
</script>

<style>
#app {
  max-width: 1000px;
  margin: 0 auto;
}

.logo {
  vertical-align: middle;
}
</style>

