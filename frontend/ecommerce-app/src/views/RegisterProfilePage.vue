<template>
  <div id="registerFormContainer">
    <form class="form" @submit.prevent="onSubmit">
      <h1>Register profile</h1>
      <fieldset>
        <legend>Type in your firstname</legend>
        <BaseInput
            v-model="firstName"
            label="Firstname:"
            placeholder="Ola"
            type="text"
        />
      </fieldset>

      <fieldset>
        <legend>Type in your lastname</legend>
        <BaseInput
            v-model="lastName"
            label="Lastname:"
            placeholder="Norman"
            type="text"      
        />
      </fieldset>

      <fieldset>
        <legend>Type in your e-mail</legend>
        <BaseInput
            v-model="eMail"
            label="E-mail:"
            placeholder="Ola.Nordman@mail.com"
            type="text"
        />
      </fieldset>

      <fieldset>
        <legend>Which county are you from?</legend>
        <BaseSelect
            v-model="location.county"
            :options="counties"
            label="County:"
        />
      </fieldset>
    
      <fieldset>
        <legend>Type in your home city</legend>
        <BaseInput
            v-model="location.city"
            label="City:"
            placeholder="Oslo"
            type="text"
        />
      </fieldset>

      <fieldset>
        <legend></legend>
        <BaseInput
            v-model="location.address"
            label="Address:"
            placeholder="Olav tryggvasons gate 24"
            type="text"
        />
      </fieldset>

      <fieldset>
        <legend>Type in your password:</legend>
        <BaseInput
            v-model="password"
            label="Password:"
            placeholder="**********"
            type="password"
        />
      </fieldset>
      <input class="button" type="button" value="Register" @click="register">
    </form>
  </div>
</template>

<script>
import httputils from "@/utils/httputils";
import "../assets/style/RegisterProfilePage.css"
import "../assets/style/BaseInput.css"
//import { useField } from 'vee-validate'

export default {
  name: "RegsiterProfilePage",
  data () {
    return {
      firstName: '',
      lastName: '',
      eMail: '',
      location: {
        county: '',
        city: '',
        address: ''
      },
      password: '',
      counties: []
    }
  },
  async mounted () {
    // todo: load in counties from database
    let countiesPromise = await httputils.getCounties();

    countiesPromise.data.forEach(conty => {
      this.counties.push(conty.countyName)
    });
  },
  /*
  setup() {
    const { value: eMail, errorMessage: eMailError } = useField('eMail', function (value) {
      if (!value) return 'This field is required'


      const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      if (!regex.test(String(value).toLowerCase())) return 'Please enter a valid email address'

      return true
    })

    const { value: firstName, errorMessage: firstNameError } = useField('firstName', function (value) {this.validateString(value)});
    const { value: lastName, errorMessage: lastNameError } = useField('lastName', function (value) {this.validateString(value)});
    const { value: city, errorMessage: cityError } = useField('city', function (value) {this.validateString(value)});
    
    return {
      eMail, 
      eMailError,
      firstName, 
      firstNameError,
      lastName,
      lastNameError,
      city,
      cityError
    }

  },
  */
  methods: {
    register () {
      //todo: push to database and sign in
    },
    validateString (value) {
      if (!value) return 'This field is required'
      const regex = /^[a-z ,.'-]+$/i
      if (!regex.test((String(value)))) {
        return 'Please enter a valid name'
      }
      return true
    }
  }
}
</script>
