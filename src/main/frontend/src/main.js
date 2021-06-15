import Vue from 'vue';
import App from './App.vue';
import VueResource from "vue-resource";

Vue.use(VueResource);   //framework bedzie w kazdej aplikacji stworzonej za jego pomoca uzywal biblioteki vue-resource, dzieki temu bedziemy miec komunikacje dostepna w komponentach
Vue.http.options.root = '/api'; //bazowy adres URL do API

new Vue({
    el: '#app',
    render: h => h(App)
});
