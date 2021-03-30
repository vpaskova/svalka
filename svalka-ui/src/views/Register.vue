<template>
  <div class="photoContainer">
    <div class="registerform">
      <form name="form" class="text-center border border-light p-5" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <div class="form-group">
        <p class="h4 mb-4">Регистрация</p>
          <input
            placeholder="Потребителско име"
            v-model="user.username"
            v-validate="'required|min:3|max:20'"
            type="text"
            class="form-control"
            name="username"
            id="username"
          />
            <div
              v-if="submitted && errors.has('username')"
              class="alert-danger"
            >{{errors.first('username')}}</div>
          </div>
          <div class="form-group">
            <input
              placeholder="Имейл адрес"
              v-model="user.email"
              v-validate="'required|email|max:50'"
              type="email"
              class="form-control"
              name="email"
              id="email"
            />
            <div
              v-if="submitted && errors.has('email')"
              class="alert-danger"
            >{{errors.first('email')}}</div>
          </div>
          <div class="form-group">
            <input
              placeholder="Парола"
              v-model="user.password"
              v-validate="'required|min:6|max:40'"
              type="password"
              class="form-control"
              name="password"
              id="password"
            />
            <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >{{errors.first('password')}}</div>
          </div>
        <div class="form-group">
          <button class="btn btn-info btn-block my-4 registerButton" >

            <span>Регистрация</span>
          </button>
        </div>
        </div>
      </form>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >{{message}}</div>
    </div>
  </div>
</template>
<script>
import User from '../models/user'

export default {
  name: 'Register',
  data () {
    return {
      user: new User('', '', ''),
      submitted: false,
      successful: false,
      message: ''
    }
  },
  computed: {
    loggedIn () {
      return this.$store.state.auth.status.loggedIn
    }
  },
  mounted () {
    if (this.loggedIn) {
      this.$router.push('/profile')
    }
  },
  methods: {
    handleRegister () {
      this.message = ''
      this.submitted = true
      this.$validator.validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('auth/register', this.user).then(
            data => {
              this.$router.push('/profile')
              this.message = data.message
              this.successful = true
            },
            error => {
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString()
              this.successful = false
            }
          )
        }
      })
    }
  }
}
</script>
<style scoped>
.registerform{
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
.registerButton{
  background-color: sienna;
  border: sienna;
}
</style>
