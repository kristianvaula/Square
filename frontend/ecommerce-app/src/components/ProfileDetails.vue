<template>
    <div class="profile-details">
      <div class="profile-detail">
        <label for="name">Name:</label>
        <span id="name">{{ ProfileInfo?.firstName + " " + ProfileInfo?.lastName }}</span>
      </div>
      <div class="profile-detail">
        <label for="email">Email:</label>
        <span id="email">{{ ProfileInfo?.eMail }}</span>
      </div>
      <div class="profile-detail">
        <label for="location">Location:</label>
        <span id="location">{{ ProfileInfo?.location }}</span>
      </div>
      <div class="profile-detail">
        <label for="address">Address:</label>
        <span id="address">{{ ProfileInfo?.address }}</span>
      </div>
      <router-link to="/edit-profile">
        <button class="edit-profile-button">Edit Profile</button>
      </router-link>
    </div>
  </template>
  
  <script>
  import '@/assets/style/ProfileDetails.css'
  import ProfileUtils from '@/utils/ProfileUtils';
  import { useTokenStore } from "@/store/token.js";

  export default {
    name: 'ProfileDetails',
    data() {
      return {
        ProfileInfo: null
      }
    },
    setup() {
      const store = useTokenStore()
      return {
          store
      }
    },
    async mounted () {                  
      let profilePromise = await ProfileUtils.getProfileByEmail(this.store.loggedInUser);      
      let profile = profilePromise.data;

      let locationPromise;
      if (profile) {    
        locationPromise = await ProfileUtils.getLocation(profile.addressId);
      }

      let location = locationPromise.data;

      this.ProfileInfo = {
        firstName: profile.firstName,
        lastName: profile.lastName,
        eMail: profile.eMail,
        location: location.county + ", " + location.city,
        address: location.address
      }
    }
  }
  </script>
  