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
            :error="firstNameError"
        />
      </fieldset>

      <fieldset>
        <legend>Type in your lastname</legend>
        <BaseInput
            v-model="lastName"
            label="Lastname:"
            placeholder="Norman"
            type="text"   
            :error="lastNameError"   
        />
      </fieldset>

      <fieldset>
        <legend>Type in your e-mail</legend>
        <BaseInput
            v-model="eMail"
            label="E-mail:"
            placeholder="Ola.Nordman@mail.com"
            type="text"
            :error="eMailError"
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
            :error="cityError"
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
            :error="passwordError"

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
import { useField } from 'vee-validate'

export default {
  name: "RegsiterProfilePage",
  data () {
    return {
      location: {
        county: '',
        address: ''
      },
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
  setup() {

    function textValidation(value) {      
      if (!value) return 'This field is required'
      const regex = /^[a-z ,.'-]+$/i
      if (!regex.test((String(value)))) {
        return 'Please enter a valid name'
      }
      return true
    }


    const { value: eMail, errorMessage: eMailError } = useField('eMail', (value) => {
      if (!value) return 'This field is required'


      const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      if (!regex.test(String(value).toLowerCase())) return 'Please enter a valid email address'

      return true
    })

    const { value: password, errorMessage: passwordError} = useField('password', (value) => {
      if (!value) return 'This field is required'

      const regex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$/;
      if (!regex.test(String(value).toLowerCase())) return 'A valid password must contian a digit, lower and upper case alphabet, a spesial carracter and have a length of 8 to 20 charactert. White spaces is not allowed in a password'

      return true;
    })

    const { value: firstName, errorMessage: firstNameError } = useField('firstName', (value) => textValidation(value));
    const { value: lastName, errorMessage: lastNameError } = useField('lastName', (value) => textValidation(value));
    const { value: city, errorMessage: cityError } = useField('city', (value) => textValidation(value));
    
    return {
      eMail, 
      eMailError,
      firstName, 
      firstNameError,
      lastName,
      lastNameError,
      city,
      cityError,
      password,
      passwordError
    }

  },
  methods: {
    register () {
      //todo: push to database and sign in
    }
  }
}
</script>
