<template>
  <div class="photoContainer">
  <div class="loginform">
    <form name="form" @submit.prevent="handleLogin" class="text-center border border-light p-5" >
      <p class="h4 mb-4">Вход</p>
      <div class="form-group">
        <input
          v-model="user.username"
          v-validate="'required'"
          type="text"
          class="form-control"
          name="username"
          id="username"
          placeholder="Потребителско име"
        />
        <div
          v-if="errors.has('username')"
          class="alert alert-danger"
          role="alert"
        >Не сте въвели потребителско име!</div>
      </div>
      <div class="form-group">
        <input
          v-model="user.password"
          v-validate="'required'"
          type="password"
          class="form-control"
          name="password"
          id="password"
          placeholder="Парола"
        />
        <div
          v-if="errors.has('password')"
          class="alert alert-danger"
          role="alert"
        >Не сте въвели парола!</div>
      </div>
      <div class="form-group">
        <button class="btn btn-info btn-block my-4 signInButton" :disabled="loading" type="submit" >
          <span v-show="loading" class="spinner-border spinner-border-sm"></span>
          <span>Вход</span>
        </button>
      </div>
      <div class="form-group">
        <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
      </div>
      <p>Нямате профил?
<!--        <a class="linkColor" href="" onClick={handleSubmit}>Register</a>-->
        <router-link to="/register" class="linkColor">Регистрация</router-link>
      </p>
    </form>
  </div>
  </div>
</template>

<script>
import User from '../models/user'

export default {
  name: 'Home',
  data () {
    return {
      user: new User('', ''),
      loading: false,
      message: ''
    }
  },
  computed: {
    loggedIn () {
      return this.$store.state.auth.status.loggedIn
    }
  },
  created () {
    if (this.loggedIn) {
      this.$router.push('/profile')
    }
  },
  methods: {

    handleLogin () {
      this.loading = true
      this.$validator.validateAll().then(isValid => {
        if (!isValid) {
          this.loading = false
          return
        }

        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push('/profile')
            },
            error => {
              this.loading = false
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString()
            }
          )
        }
      })
    }
  }
}
</script>

<style scoped>
.loginform{
  max-width: 700px;
  margin: 0 auto;
  margin-top: 50px;
  border: #aa0000;
  border-style: groove;
}
.photoContainer{
  background-image: url('~@/assets/svalka-background1.jpg');
  position: fixed;
  width: 100%;
  height: 100%;
  object-fit: contain;
  top: 0px;
  margin-top: 70px;
  left: 0px;
  z-index: 0;
  fetch_format: auto;
}
.signInButton{
 background-color: sienna;
  border: sienna;
}
.linkColor{
  color: sienna;
}
</style>
