import axios from 'axios';
import { useTokenStore } from "@/store/token";

const baseurl = "http://localhost:8081";
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
        return axios.post(baseurl + "/register-user", JSON.stringify(profile), config);
    },

    getJwtToken(eMail, password) {
        const config = {
            headers: {
                "Content-type": "application/json",
            },
        };
        return axios.post("http://localhost:8081/unauthorized/token", JSON.stringify({eMail, password}), config);
    },
    getProfile(eMail, password) {
        const tokenStore = useTokenStore();
        
        const config = {
            headers: {
                "Content-type": "application/json",
                "Authorization": "Bearer " + tokenStore.jwtToken
            },
        };

        return axios.post("http://localhost:8081/profile", JSON.stringify({eMail, password}), config);
    }
}
