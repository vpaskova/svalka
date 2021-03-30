<template>
  <div class="back">
    <div class="photo"></div>
    <b-card class="myForm">
      <b-form-group
        label-cols-lg="8"
        label="Какво търсиш?"
        label-size="lg"
        label-align-sm="right"
        label-class="font-weight-bold pt-0"
        class="title">
      </b-form-group>

      <b-form-group
        label-cols-sm="3"
        label="Град:"
        label-align-sm="right"
        label-for="city"
        class="city">
        <b-form-input id="city" v-model="filters.city"></b-form-input>
      </b-form-group>

      <b-form-group
        label-cols-sm="4"
        label="Пол:"
        label-align-sm="right"
        class="gender">
        <b-form-radio-group
          v-model="filters.gender"
          :options="genderOptions"
          name="radio-inline">
        </b-form-radio-group>
      </b-form-group>

      <b-form-group
        label-cols-sm="4"
        label="Тип взаимоотношения:"
        label-align-sm="right"
        class="relationship">
        <b-form-radio-group
          v-model="filters.relationship"
          :options="relationshipOptions"
          name="radio-inli">
        </b-form-radio-group>
      </b-form-group>

      <b-button class="button" v-on:click="searchUsers">
        Търси
      </b-button>
    </b-card>

    <b-table class="table"
             id="userTable"
             striped
             hover
             bordered
             :items="users"
             :fields="fields"
             :current-page="currentPage"
             :sort-by.sync="sortBy"
             :sort-desc.sync="sortDesc">

      <template v-slot:cell(actions)="row">
        <router-link :to="{ name: 'UserView', params: {id:row.item.id} }" class="btn-group">Отвори</router-link>
      </template>
<!--      //  responsive-->
<!--      :no-border-collapse="noCollapse">-->
    </b-table>
    <b-pagination
      v-model="currentPage"
      pills
      :total-rows="rows"
      :per-page="perPage"
      @input="searchUsers"
      aria-controls="userTable">
    </b-pagination>
  </div>
</template>

<script>
import TryService from '../services/try-service'

export default {
  name: 'Try',
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    }
  },
  data () {
    return {
      //  stickyHeader: true,
      //  noCollapse: false,

      rows: '',
      perPage: 8,
      currentPage: 1,
      sortBy: 'firstName',
      sortDesc: false,
      msg: '',
      totalItems: '',
      users: [{
        id: '',
        firstName: '',
        lastName: '',
        city: '',
        age: ''
      }],
      genderOptions: [
        { text: 'Мъж', value: 'мъж' },
        { text: 'Жена', value: 'жена' },
        { text: 'Друго', value: 'друго' }],
      relationshipOptions: [
        { text: 'За една вечер', value: 'за една вечер' },
        { text: 'Сериозна връзка', value: 'сериозна връзка' },
        { text: 'Просто среща', value: 'просто среща' },
        { text: 'Приятели с привилегии', value: 'приятели с привилегии' }
      ],
      filters: {
        city: '',
        gender: '',
        relationship: ''
      },
      fields: [
        { key: 'firstName', label: 'Име' },
        { key: 'lastName', label: 'Фамилия' },
        { key: 'city', label: 'Град' },
        { key: 'age', label: 'Възраст' },
        { key: 'actions', label: 'Действия' }
      ]
    }
  },
  mounted () {
    this.searchUsers()
    if (!this.currentUser) {
      this.$router.push('/')
    }
  },
  methods: {
    searchUsers () {
      TryService.getUsersSearch(this.filters, this.currentPage, this.perPage).then(
        response => {
          this.users = response.data.users
          this.rows = response.data.totalItems
          this.totalItems = response.data.totalItems
        },
        error => {
          this.msg =
          (error.response && error.response.data) ||
          error.message ||
          error.toString()
        }
      )
    }
  }
}
</script>

<style scoped>
.back{
  background-image: url('../assets/background2.jpg');
  width: 100%;
  height: 100%;
  position: absolute;
  left: 0px;
  object-fit: contain;
  margin-top: auto;
  margin-left: auto;
}
.myForm{
  background-image: url('../assets/backform.jpg');
  margin-top: 50px;
  margin-left: 300px;
  color: black;
  width: 600px;
  height: 300px;
  border-style: dashed;
  border-width: 2px;
  border-color: saddlebrown;
}
.photo{
  background-image: url('../assets/lovelycouple11.jpg');
  image-resolution: normal;
  position: absolute;
  width: 690px;
  height: 754px;
  margin-left: 1053px;
  margin-top: 50px;
  object-fit: fill;
  z-index: 0;
}
.title{
  color: saddlebrown;
  size: auto;
}
.city{
  margin-right: 80px;
}
.gender{
  margin-right: 60px;
}
.button{
  background-color: saddlebrown;
}
.table{
  background-image: url('../assets/backtable.jpg');
  width: 600px;
  height: 300px;
  margin-left: 300px;
  margin-top: 10px;
  border-color: saddlebrown;
  border-width: 2px;
}
</style>
