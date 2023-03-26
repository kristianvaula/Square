<template>
    <div class="edit-page">
        <img src="@/assets/images/profileicon.png" alt="Profile Picture">
        <div class="edit-profile">
            <form @submit.prevent="saveChanges">
                <div>
                    <legend>Change your Location Data:</legend>
                    <BaseSelect
                        v-model="location.county"
                        :options="counties"
                        label="County:"
                    />
                    <h5>Current County: ...</h5>
                    <BaseInput
                        v-model="location.city"
                        label="City:"
                        placeholder=""
                        type="text"
                    />
                    <h5>Current City: ...</h5>
                    <BaseInput
                        v-model="location.address"
                        label="Address:"
                        placeholder=""
                        type="text"
                    />
                    <h5>Current Address: ...</h5>
                </div>
                <div>
                    <legend>Change your Password:</legend>
                    <BaseInput
                        v-model="password"
                        label="Current Password:"
                        placeholder=""
                        type="password"
                    />
                    <BaseInput
                        v-model="password"
                        label="New Password:"
                        placeholder=""
                        type="password"
                    />
                    <BaseInput
                        v-model="password"
                        label="Repeat New Password:"
                        placeholder=""
                        type="password"
                    />
                </div>
                
                <button class="save-button" type="submit">Save Changes</button>
            </form>
        </div>
        <div>
            <router-link to="/my-profile">
               <Button class="cancel-button">Cancel</Button> 
            </router-link>
        </div>
  </div>
  </template>
  
  <script>
  import '../assets/style/EditProfile.css';
  
  export default {
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
  methods: {
    saveChanges() {
      // implement the saveChanges method 
    }
  }
}
  </script>
  