import axios from 'axios';

const baseurl = "http://localhost:8081";
const config = {
    headers: {
        "Content-type": "application/json"
    },
};

export default {
    getCounties() {
        return axios.get(baseurl + "/counties", config);
    }
}
