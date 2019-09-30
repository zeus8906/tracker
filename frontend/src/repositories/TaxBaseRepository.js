import axios from 'axios'

const baseUrl = '/api/args/tax_base'

export default {
  get () {
    return axios.get(baseUrl)
      .then(response => response.data)
  },
  update (payload) {
    return axios.put(baseUrl + '/' + payload)
      .then(response => response.data)
  }
}
