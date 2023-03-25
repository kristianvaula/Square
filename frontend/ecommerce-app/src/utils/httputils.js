import axios from 'axios';
import { useTokenStore } from "@/store/token";

const baseurl = "http://localhost:8081";
const configBasic = {
    headers: {
        "Content-type": "application/json"
    },
};

export default {
    getCounties() {
        return axios.get(`${baseurl}/unauthorized/counties`, configBasic);
    },
    createUser(profile) {
        return axios.post(`${baseurl}/unauthorized/new-profile`, JSON.stringify(profile), configBasic);
    },

    getJwtToken(profile) {
        return axios.post(`${baseurl}/unauthorized/token`, JSON.stringify(profile), configBasic);
    },
    getToken(eMail, password) {
        return axios.post(`${baseurl}/unauthorized/token2`, JSON.stringify({eMail, password}), configBasic);
    },
    getProfile(eMail, password) {
        const tokenStore = useTokenStore();
        
        const config = {
            headers: {
                "Content-type": "application/json",
                "Authorization": "Bearer " + tokenStore.jwtToken
            },
        };

        return axios.post(`${baseurl}/user/profile`, JSON.stringify({eMail, password}), config);
    },
    getProfileId(eMail) {
        const tokenStore = useTokenStore();
        
        const config = {
            headers: {
                "Content-type": "application/json",
                "Authorization": "Bearer " + tokenStore.jwtToken
            },
        };
        console.log(eMail)
        console.log(tokenStore.jwtToken)
        return axios.get(`${baseurl}/user/profile/${eMail}`, config);
    },
    getProfileByEmail(eMail) {
        const tokenStore = useTokenStore();
        
        const config = {
            headers: {
                "Content-type": "application/json",
                "Authorization": "Bearer " + tokenStore.jwtToken
            },
        };

        return axios.get(`${baseurl}/profile/by-email/${eMail}`, config);
    },
    getLocation(id) {
        const tokenStore = useTokenStore(); 
        const config = {
            headers: {
                "Content-type": "application/json",
                "Authorization": "Bearer " + tokenStore.jwtToken
            },
        }; 
        return axios.get(`${baseurl}/user/address/${id}`, config);
    }
}
