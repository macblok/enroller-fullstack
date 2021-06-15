<template>
  <div id="app">
    <h1>
      <img src="./assets/logo.svg" alt="Enroller" class="logo" />
      System do zapisów na zajęcia
    </h1>
    <div v-if="authenticatedUsername">
      <h2>
        Witaj {{ authenticatedUsername }}!
        <a @click="logout()" class="float-right button-outline button"
        >Wyloguj</a>
      </h2>
      <meetings-page :username="authenticatedUsername"></meetings-page>
    </div>
    <div v-else>
      <button :class="registering ? 'button-outline' : ''" @click="registering = false">Loguję się</button>
      <!-- ternary operator, uzyskiwanie reaktywnego przycisku -->
      <button :class="!registering ? 'button-outline' : ''" @click="registering = true">Rejestruję się</button>

    <div v-if="errorMessage" class="alert-warning">{{ errorMessage }}</div>

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
      registering: false,
      errorMessage: ''
    };
  },
  methods: {
    login(user) {
      this.authenticatedUsername = user.login;
    },
    register(user) {
      this.errorMessage = '';
      this.$http.post("participants", user) //odnosimy sie do aktualnego komponentu, usluga $http dostarczana jest przez vue-resources, udostepnia ona komunikacje z backendem, zapytanie bedzie wyslane do entpointa participants
        .then(response => {
          this.registering = false; //jezeli udalo sie zarejestrowac to przechodzi do logowania
        })
        .catch(response => {
          //console.log(response); //w przegladarce w narzedziach dev (F12) w console mozemy podgladnac nazwe wlasciwosci z odpowiedzia z backendu (w tym przypadku bodyText)
          //this.errorMessage = 'Nie udało się zarejestrować konta'
          this.errorMessage = response.bodyText;
        });
    },
    logout() {
      this.authenticatedUsername = "";
    }
  }
};
</script>

<!-- style CSS -->

<style>
#app {
  max-width: 1000px;
  margin: 0 auto;
}

.logo {
  vertical-align: middle;
}

.alert-warning {
  border: 3px red dotted;
  padding: 5px;
  background: pink;
  border-radius: 50%;
  text-align: center;
}

</style>

