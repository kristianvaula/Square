import { defineStore } from "pinia";
import httputils from "@/utils/httputils"

export const useTokenStore = defineStore("token", {
    state: () => ({
        jwtToken: null,
        loggedInUser: null,
    }),
    persist: {
        storage: sessionStorage, // note that data in sessionStorage is cleared when the page session ends
    },
    actions: {
        async getTokenAndSaveInStore(eMail, password) {
            try{
                let response = await httputils.getJwtToken(eMail, password);
                let data = response.data;
                if(data !== null && data !== '' && data !== undefined){
                    this.jwtToken = data;
                    response = await httputils.getProfile(eMail, password);
                    console.log("Sets loggedInUser");
                    console.log(response.data.email)
                    this.loggedInUser = response.data.email
                    console.log("logged in user is set to: " + this.loggedInUser)
                }
            } catch (err){
                console.log(err)
            }
        }
    },
});
