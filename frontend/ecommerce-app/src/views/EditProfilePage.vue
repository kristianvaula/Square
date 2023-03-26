<template>
    <div class="profile-page">
        <img src="@/assets/images/profileicon.png" alt="Profile Picture">
        <div class="edit-profile">
            <form @submit.prevent="saveChanges">
                <div>
                    <legend>Change your Location Data:</legend>
                    <BaseSelect
                        v-model="locationInfo.county"
                        :options="counties"
                        label="County:"
                    />
                    <BaseInput
                        v-model="locationInfo.city"
                        label="City:"
                        placeholder=""
                        type="text"
                    />
                    <BaseInput
                        v-model="locationInfo.address"
                        label="Address:"
                        placeholder=""
                        type="text"
                    />
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
  import ProfileUtils from '@/utils/ProfileUtils';
  import { useTokenStore } from "@/store/token.js";
import router from '@/router';
  
  export default {
    data () {
    return {
      locationInfo: {
        county: '',
        city: '',
        address: ''
      },
      password: '',
      counties: [],
      ProfileInfo: null,
      profileToUpdate: null
    }
  },
  methods: {
    async saveChanges() {


      this.ProfileInfo = {
        profileId: this.profileToUpdate.profileId,
        firstName: this.profileToUpdate.firstName,
        lastName: this.profileToUpdate.lastName,
        eMail: this.profileToUpdate.eMail,
        county: this.locationInfo.county,
        city: this.locationInfo.city,
        address: this.locationInfo.address,
        password: this.profileToUpdate.password, // implement for password and update to be this.password (instead of profileInfo.password)
      }

      await ProfileUtils.updateUser(this.ProfileInfo);          
        
      router.push("/my-profile")

    }
  },
  setup() {
      const store = useTokenStore()
      return {
          store
      }
    },
    async mounted () {    
      let countiesPromise = await ProfileUtils.getCounties();
      countiesPromise.data.forEach(conty => {
        this.counties.push(conty.countyName)
      });

      let profilePromise = await ProfileUtils.getProfileByEmail(this.store.loggedInUser);      
      this.profileToUpdate = profilePromise.data;
      let locationPromise;
      if (this.profileToUpdate) {    
        locationPromise = await ProfileUtils.getLocation(this.profileToUpdate.addressId);
      }
      this.locationInfo = locationPromise.data;
    }
  }
  </script>
  