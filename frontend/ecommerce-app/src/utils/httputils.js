import axios from 'axios';
import { useTokenStore } from "@/store/token";

//const baseurl = "http://localhost:8081";
const config = {
    headers: {
        "Content-type": "application/json"
    },
};

export default {
    getCounties() {
        return axios.get("http://localhost:8081/unauthorized/counties", config);
    },
    createUser(profile) {
        return axios.post("http://localhost:8081/unauthorized/new-profile", JSON.stringify(profile), config);
    },

    getJwtToken(profile) {
        return axios.post("http://localhost:8081/unauthorized/token", JSON.stringify(profile), config);
    },
    getToken(eMail, password) {
        return axios.post("http://localhost:8081/unauthorized/token2", JSON.stringify({eMail, password}), config);
    },
    getProfile(eMail, password) {
        const tokenStore = useTokenStore();
        
        const config = {
            headers: {
                "Content-type": "application/json",
                "Authorization": "Bearer " + tokenStore.jwtToken
            },
        };

        return axios.post("http://localhost:8081/user/profile", JSON.stringify({eMail, password}), config);
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
        return axios.get("http://localhost:8081/user/profile/" + eMail, config);
    },
    getProfileByEmail(eMail) {
        const tokenStore = useTokenStore();
        
        const config = {
            headers: {
                "Content-type": "application/json",
                "Authorization": "Bearer " + tokenStore.jwtToken
            },
        };

        return axios.post("http://localhost:8081/profile/by-email", JSON.stringify({eMail}), config);
    }
}
