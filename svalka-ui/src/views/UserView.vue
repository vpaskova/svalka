<template>
  <div class="containerr" style="display: inline-flex">
    <div class="back">
    <h1 style="margin-top: 30px; color: saddlebrown; ">Профил</h1>
    </div>
    <div class="imageAndTextBox" style = "display: inline-block ">
    <div class="divImage">
      <b-img rounded class="image-fit" v-bind:src="'data:image/jpeg;base64, '+displayFile.img"></b-img>
    </div>
    <div style="margin: auto; width: 50% " >
      <b-table-simple class="table2">
      <b-tr>
        <b-td><b><i>{{userr.firstName}} {{userr.lastName}}</i></b></b-td>
      </b-tr>
      <b-tr>
        <b-td><b><i>{{userr.age}} години</i></b></b-td>
      </b-tr>
    </b-table-simple>
    </div>
    </div>

     <div class="userData">
       <div class=" col-md-9 col-lg-9 ">
      <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad"  >
        <b-table-simple class="table" style="width: 800px ; height: 700px">
          <b-tr>
            <b-td><b>Пол</b></b-td>
            <b-td>{{userr.gender.genderType}}</b-td>
          </b-tr>
          <b-tr>
            <b-td><b>Населено място</b></b-td>
            <b-td>{{userr.city}}</b-td>
          </b-tr>
          <b-tr>
            <b-td><b>Зодия</b></b-td>
            <b-td>{{userr.zodiacSign.zodiacType}}</b-td>
          </b-tr>
          <b-tr>
            <b-td><b>Търси</b></b-td>
            <b-td>
              <div v-for="gender in userr.genderPreference" :key="gender.id">
                {{gender.genderType}}
              </div>
            </b-td>
          </b-tr>
          <b-tr>
            <b-td><b>Вид взаимоотношения</b></b-td>
            <b-td>
              <div v-for="relationship in userr.relationshipPreference" :key="relationship.id">
                {{relationship.relationshipType}}
              </div>
            </b-td>
          </b-tr>
          <b-tr>
            <b-td><b>Мото</b></b-td>
            <b-td>{{userr.moto}}</b-td>
          </b-tr>
          <b-tr>
            <b-td><b>Описание</b></b-td>
            <b-td class="description">{{userr.description }}</b-td>
          </b-tr>
          <b-tr>
            <b-td><b>Телефонен номер</b></b-td>
            <b-td>{{userr.phoneNumber}}</b-td>
          </b-tr>
          <b-tr>
            <b-td><b>Email</b></b-td>
            <b-td><a href="mailto:">{{userr.email}}</a></b-td>
          </b-tr>
        </b-table-simple>
      </div>
      </div>
    </div>
    <div class="photo"></div>
  </div>

</template>

<script>

import UserViewService from '../services/userView-service'
import TryService from '../services/try-service'
export default {
  name: 'UserView',
  // computed: {
  //   currentUser () {
  //     return this.$store.state.auth.user
  //   }
  // },
  data () {
    return {
      userr:
      [{
        id: '',
        firstName: '',
        lastName: '',
        city: '',
        age: '',
        gender: {
          id: '',
          genderType: ''
        },
        zodiacSign: {
          id: '',
          zodiacType: ''
        },
        genderPreference: {
          id: '',
          genderType: ''
        },
        relationshipPreference: {
          id: '',
          relationshipType: ''
        },
        moto: '',
        description: '',
        phoneNumber: '',
        email: '',
        file: null
      }],
      displayFile: {
        id: '',
        name: '',
        type: '',
        img: null
      }
    }
  },
  beforeRouteEnter (to, from, next) {
    TryService.getUserByiId(to.params.id).then(response => {
      next(vm =>
        vm.setData(response)
      )
    })
    // } else if (from.name === 'Profile') {
    //   ProfileService.getUserByiId(to.params.id).then(response => {
    //     next(vm =>
    //       vm.setData(response)
    //     )
    //   })
    // }
  },
  mounted () {
    this.loadImage()
  },
  methods: {
    setData (response) {
      console.log(response)
      this.userr = response.data
    },
    loadImage () {
      UserViewService.loadImage(this.userr.id).then(
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
    }
  }
}
</script>

<style scoped>
.back{
  background-image: url('../assets/background2.jpg');
  width: 100%;
  height: 100%;
  /*object-fit: contain;*/
  position: absolute;
  top: 0px;
  margin-top: 70px;
  left: 0px;
  z-index: 0;
}
.form{
  width: 100%;
  height: 100%;
  object-fit: contain;
  margin-top: -675px;
}
.photo{
  background-image: url('../assets/hands8.png');
  image-resolution: normal;
  position: absolute;
  width: 504px;
  height: 670px;
  margin-left: 900px;
  margin-right: 200px;
  margin-top: 125px;
  /*margin-top: 120px;*/
  /**margin-left: 700px;*/

}
.imageAndTextBox{
  margin-left: -400px;
  margin-top: 125px;
  margin-right:  30px;
  margin-bottom:  30px;
  position: absolute;
}

.table{
  background-image: url('../assets/resized1.jpg');
  width: 600px;
  height: 300px;
  margin-top: 0px;
  border-style: dashed;
  border-color: saddlebrown;
  border-width: 2px;
}
.table2{
  background-image: none;
  width: 200px;
  height: 100px;
  border-style: dashed;
  border-color: saddlebrown;
  border-width: 2px;
  margin-top: 60px;
  margin-left: -90px;
}
.divImage{
  margin-bottom: 30px;
  margin-left: -110px;
}
.toppad {
  margin-top: 50px;
}

.userData{
  margin-top: 70px;
  margin-left: -20px;
}
.description{
  overflow-wrap: anywhere;
}
.image-fit{
  width: 400px;
  height: 400px;
  border-color: saddlebrown;
  border-width: 2px;
  /*margin-left: -100px;*/
}
</style>
