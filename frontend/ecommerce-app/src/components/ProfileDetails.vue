<template>
    <div class="profile-details">
      <div>
        <label for="name">Name:</label>
        <span id="name">{{ name }}</span>
      </div>
      <div>
        <label for="email">Email:</label>
        <span id="email">{{ email }}</span>
      </div>
      <div>
        <label for="location">Location:</label>
        <span id="location">{{ location }}</span>
      </div>
      <div>
        <label for="address">Address:</label>
        <span id="address">{{ address }}</span>
      </div>
      <div>
        <label for="date-created">Date Created:</label>
        <span id="date-created">{{ dateCreated }}</span>
      </div>
      <router-link to="/edit-profile">
        <button class="edit-profile-button">Edit Profile</button>
      </router-link>
    </div>
  </template>
  
  <script>
  import '@/assets/style/ProfileDetails.css'

  export default {
    name: 'ProfileDetails',
    props: {
      name: {
        type: String,
        required: true,
      },
      email: {
        type: String,
        required: true,
      },
      location: {
        type: String,
        required: true,
      },
      address: {
        type: String,
        required: true,
      },
      dateCreated: {
        type: String,
        required: true,
      },
    },
  };
  </script>
  