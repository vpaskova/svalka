<template>
  <div class="profileform">
    <b-card class="form">
      <b-form-group
        label-cols-lg="8"
        label="Редактиране на профил"
        label-size="lg"
        label-align-sm="right"
        label-class="font-weight-bold pt-0"
        class="title">
      </b-form-group>
      <b-form-group
        label-for="firstName"
        class="firstName">
        <b-input-group prepend="Име"  class="firstName" >
          <b-form-input id="firstName" v-model="filters.firstName"></b-form-input>
        </b-input-group>
      </b-form-group>

      <b-form-group label-for="lastName"
                    class="lastName">
        <b-input-group prepend="Фамилия" class="lastName" >
          <b-form-input id="lastName" v-model="filters.lastName"></b-form-input>
        </b-input-group>
      </b-form-group>

      <b-form-group label-for="age"
                    class="age">
        <b-input-group prepend="Години" class="age" >
          <b-form-input type="number" id="age" v-model="filters.age"></b-form-input>
        </b-input-group>
      </b-form-group>

      <b-form-group label-for="email"
                    class="email">
        <b-input-group prepend="e-mail" class="mail" >
          <b-form-input id="email" v-model="filters.email"></b-form-input>
        </b-input-group>
      </b-form-group>

      <b-form-group label-for="moto"
                    class="moto">
        <b-form-textarea

          placeholder="Напишете Вашето мото, което Ви различава от всички останали..."
          rows="3"
          max-rows="6"
          id="moto" v-model="filters.moto"
        ></b-form-textarea>
      </b-form-group>

      <b-form-group label-for="city"
                    class="city">
        <b-input-group prepend="Град" class="city">
          <b-form-input id="city" v-model="filters.city"></b-form-input>
          <b-input-group-append>
          </b-input-group-append>
        </b-input-group>
      </b-form-group>

      <b-form-group  class="phoneNumber">
        <b-input-group prepend="Номер" class="phoneNumber" >
          <b-form-input id="phoneNumber" v-model="filters.phoneNumber"></b-form-input>
        </b-input-group>
      </b-form-group>

      <b-form-group
        label-cols-sm="1"
        label="Пол:"
        label-align-sm="right"
        class="gender">
        <b-form-radio-group
          id="gender" v-model="filters.gender"
          :options="genderOptions"
          name="gender">
        </b-form-radio-group>
      </b-form-group>

      <b-form-group
        label-cols-sm="1"
        label="Търся:"
        label-align-sm="right"
        class="genderPreference">
        <b-form-radio-group
          id="genderPreference"
          v-model="filters.genderPreference"
          :options="genderOptions"
          name="genderPreference">
        </b-form-radio-group>
      </b-form-group>
<!--      <b-form-group-->
<!--        label-cols-sm="1"-->
<!--        label="Търся:"-->
<!--        label-align-sm="right"-->
<!--        class="genderPreference">-->
<!--        <b-form-radio-group-->
<!--          v-for="gender in filters.genderPreference"-->
<!--          v-bind:key="gender.id"-->
<!--          id="genderPreference"-->
<!--          v-model="gender.genderType"-->
<!--          :options="genderOptions"-->
<!--          name="genderPreference">-->
<!--        </b-form-radio-group>-->
<!--      </b-form-group>-->

       <b-form-group class="zodiacSign">
        <b-input-group prepend="Зодия" class="zodiacSign" >
          <b-form-input id="zodiacSign"  placeholder="Изберете вашата зодия -->"></b-form-input>
          <b-input-group-append >
            <b-dropdown id="dropdown-divider" text="Избери" class="choose">
              <b-form-select :options="zodiacSignOptions" v-model="filters.zodiacSign" name="dropdown-inline"></b-form-select>
              </b-dropdown>
          </b-input-group-append>
        </b-input-group>
      </b-form-group>

      <b-form-group
        label-cols-sm="4"
        label="Тип взаимоотношения:"
        label-align-sm="right"
        class="relationshipPreference">
        <b-form-radio-group
          id="relationshipPreference"
          v-model="filters.relationshipPreference"
          :options="relationshipOptions"
          name="relationshipPreference">
        </b-form-radio-group>
      </b-form-group>

      <b-form-group class="description">
        <b-form-textarea
          id="textarea"
          v-model="filters.description"
          placeholder="Разкажете нещо за себе си, за да отриете хора със сходни интереси като Вашите..."
          rows="3"
          max-rows="6"
        ></b-form-textarea>
      </b-form-group>

      <b-button-group>
        <b-button class="button" v-on:click="saveUser">Запази</b-button>
      </b-button-group>

<!--      <b-button-group>-->
<!--        <b-button @click="reset()">Reset</b-button>-->
<!--      </b-button-group>-->
    </b-card>

      <b-card-group class ="bcard">
      <b-form-group>
        <div class="userPhoto">
        <b-card-img
          :src="'data:image/jpeg;base64, '+displayFile.img"
          class="avatar"
          title="Моят профил"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem;"
        >
        </b-card-img></div>
        <b-card class="imageee">
          <b-card-text>
            {{this.$store.state.auth.user.username}}
          </b-card-text>
          <b-button style="background-color: saddlebrown;">
          <router-link :to="{ name: 'UserView', params: {id: $store.state.auth.user.id} }" style="color: white"  class="btn-group" href="#" variant="primary">Виж моята визитка</router-link>
          </b-button>
            <b-form-group
            label-cols-sm="4"
            label-align-sm="right"
            class="image">
            <b-form-file v-model="image" :state="Boolean(image)" class="mt-3" plain accept="image/*" ></b-form-file>
          </b-form-group>
          <b-button class="button" v-on:click="saveImage();loadImage();">
            Качи снимка
          </b-button>
        </b-card>
      </b-form-group>
      </b-card-group >
  </div>
</template>

<script>

import ProfileService from '@/services/profile-service'
export default {
  name: 'Profile',
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    }
  },

  mounted: function () {
    if (!this.currentUser) {
      this.$router.push('/')
    }
    this.loadImage()
    this.viewUser()
    this.setData(ProfileService.getUserByiId(this.$store.state.auth.user.id))
  },
  data () {
    return {
      genderOptions: [
        { text: 'Мъж', value: 'мъж' },
        { text: 'Жена', value: 'жена' },
        { text: 'Друго', value: 'друго' }],
      zodiacSignOptions: [
        { text: 'Дева', value: 'дева' },
        { text: 'Телец', value: 'телец' },
        { text: 'Близнаци', value: 'близнаци' },
        { text: 'Рак', value: 'рак' },
        { text: 'Овен', value: 'овен' },
        { text: 'Везни', value: 'везни' },
        { text: 'Скорпион', value: 'скорпион' },
        { text: 'Стрелец', value: 'стрелец' },
        { text: 'Козирог', value: 'козирог' },
        { text: 'Водолей', value: 'водолей' },
        { text: 'Риби', value: 'риби' },
        { text: 'Лъв', value: 'лъв' }],
      relationshipOptions: [
        { text: 'За една вечер', value: 'за една вечер' },
        { text: 'Сериозна връзка', value: 'сериозна връзка' },
        { text: 'Просто среща', value: 'просто среща' },
        { text: 'Приятели с привилегии', value: 'приятели с привилегии' }],
      filters: {
        id: this.$store.state.auth.user.id,
        firstName: '',
        lastName: '',
        age: '',
        email: '',
        moto: '',
        city: '',
        phoneNumber: '',
        gender: '',
        genderPreference: '',
        zodiacSign: '',
        relationshipPreference: '',
        description: ''
      },

      image: null,
      displayFile: {
        id: '',
        name: '',
        type: '',
        img: null
      }
    }
  },

  methods: {
    saveUser () {
      ProfileService.saveData(this.filters).then(
        response => {
          console.log(this.filters)
          this.filters.id = response.data.this.$store.state.auth.user.id
          this.filters.firstName = response.data.filters.firstName
          this.filters.lastName = response.data.filters.lastName
          this.filters.age = response.data.filters.age
          this.filters.email = response.data.filters.email
          this.filters.moto = response.data.filters.moto
          this.filters.city = response.data.filters.city
          this.filters.phoneNumber = response.data.filters.phoneNumber
          this.filters.gender = response.data.filters.gender
          this.filters.genderPreference = response.data.filters.genderPreference
          this.filters.zodiacSign = response.data.filters.zodiacSign
          this.filters.relationshipPreference = response.data.filters.relationshipPreference
          this.filters.description = response.data.filters.description
        },
        error => {
          this.msg =
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        }
      )
    },
    saveImage () {
      ProfileService.saveImage(this.image, this.filters).then(
        response => {
          this.filters.id = response.data.filters.id
          this.image = response.data.file
        },
        error => {
          this.msg =
            (error.response && error.response.data) ||
          error.message
          error.toString()
        }
      )
    },
    loadImage () {
      ProfileService.loadImage(this.filters).then(
        response => {
          if (response.data.length !== 0) {
            this.displayFile = response.data
          }
        },
        error => {
          this.message =
            error.data().message.toString() ||
          error.message ||
          (error.response && error.response.data)
        }
      )
    },
    viewUser () {
      ProfileService.getUserByiId(this.filters).then(
        response => {
          if (response.data.firstName !== null) {
            this.filters = response.data
          }
        }
      )
    }
    // reset () {
    //   this.filters.genderPreference = ''
    // }
  }
}
</script>

<style scoped>
.profileform{
  background-image: url('../assets/background2.jpg');
  position: absolute;
  width: 100%;
  height: 100%;
  object-fit: contain;
  top: 0px;
  margin-top: 70px;
  left: 0px;
  z-index: 0;
  fetch_format: auto;
}
.form{
  background-image: url('../assets/backform.jpg');
  width: 900px;
  height: 890px;
  margin-right: 750px;
  margin-left: 250px;
  margin-bottom: 50px;
  margin-top: 20px;
  border-style: solid;
  border-color: saddlebrown;
  border-radius: 25px;
  border-width: 2px;
}
.bcard{
  margin-left: 350px;
  width: 900px;
  height:auto;

}
.title{
  color: saddlebrown;
  size: auto;
}
.button{
  background-color: saddlebrown;
}
.choose{
  background-color: saddlebrown;
}
.avatar {
  width: 400px;
  height: 400px;
  border-color: saddlebrown;
  border-width: 2px;
  position: absolute;
  /*max-width: 100%;*/
  /*max-height: 100%;*/
  margin-left: 1000px;
  margin-top: -800px;
  /*object-fit: fill;*/
  z-index: 0;
}
.imageee{
  position: absolute;
  width: 450px;
  height: 250px;
  margin-left: 950px;
  margin-top: -350px;
  object-fit: fill;
  z-index: 0;
  background:none;
}

/*.avatar{*/
/*  width: 400px;*/
/*  height: 400px;*/
/*  border-color: saddlebrown;*/
/*  border-width: 2px;*/
/*}*/
</style>
