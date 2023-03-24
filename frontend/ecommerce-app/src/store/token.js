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
                    
                    console.log(this.jwtToken);

                    response = await httputils.getProfile(eMail, password);                        
                
                    this.loggedInUser = response.data.email                
                }
            } catch (err){
                console.log(err)
            }
        }
    },
});
