import { defineStore } from "pinia";
import httputils from "@/utils/ProfileUtils"

export const useTokenStore = defineStore("token", {
    state: () => ({
        jwtToken: null,
        loggedInUser: null,
    }),
    persist: {
        storage: sessionStorage, // note that data in sessionStorage is cleared when the page session ends
    },
    actions: {        
        async getTokenAndSaveInStore(profile) {
            try{
                let response = await httputils.getJwtToken(profile);
                let data = response.data;                        

                if(data !== null && data !== '' && data !== undefined){
                    this.jwtToken = data;                
                    this.loggedInUser = profile.eMail                   
                }
            } catch (err){
                console.log(err)
            }
        },
        setTokenAndLoggedInUser(token, profile) {
            this.jwtToken = token;        
            this.loggedInUser = profile;
        }
    },
});
