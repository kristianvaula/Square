<template>
    <div class="profile-details">
      <div>
        <label for="name">Name:</label>
        <span id="name">{{ ProfileInfo?.firstName + " " + ProfileInfo?.lastName }}</span>
      </div>
      <div>
        <label for="email">Email:</label>
        <span id="email">{{ ProfileInfo?.eMail }}</span>
      </div>
      <div>
        <label for="location">Location:</label>
        <span id="location">{{ ProfileInfo?.location }}</span>
      </div>
      <div>
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
  import httputils from '@/utils/httputils';
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

    mounted () {
            let vm = this
            httputils.getProfileByEmail(this.store.loggedInUser)
                .then((response) => {
                if(response.data) {
                    console.log(response.data)
                    vm.ProfileInfo = response.data
                }
            })
                .catch((err) => {
                console.log(err)
                })
        }, 

  };
  </script>
  