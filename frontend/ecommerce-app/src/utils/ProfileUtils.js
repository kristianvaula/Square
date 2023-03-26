import axios from 'axios';
import { useTokenStore } from "@/store/token";

const baseurl = "http://localhost:8081";
const configBasic = {
    headers: {
        "Content-type": "application/json"
    },
};
function getAxiosConfig() {
    let tokenStore = useTokenStore(); 

    return {
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + tokenStore.jwtToken
        },
    }; 
}

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
        let config = getAxiosConfig();
        return axios.post(`${baseurl}/user/profile`, JSON.stringify({eMail, password}), config);
    },
    getProfileId(eMail) {
        let config = getAxiosConfig();
        return axios.get(`${baseurl}/user/profile/${eMail}`, config);
    },
    getProfileByEmail(eMail) {
        let config = getAxiosConfig();
        return axios.get(`${baseurl}/profile/by-email/${eMail}`, config);
    },
    getLocation(id) {
        let config = getAxiosConfig();
        return axios.get(`${baseurl}/user/address/${id}`, config);
    }
}
