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

    getJwtToken(eMail, password) {
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
    },
    /*
    //todo: slette når man er sikker på kode

    testGetCity() {
        let addressName = "Stavangergate"
        let cityId = 3;
        return axios.post("http://localhost:8081/unauthorized/address", JSON.stringify({addressName, cityId}), config);
    }
    */
}
