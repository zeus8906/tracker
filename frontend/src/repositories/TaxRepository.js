import axios from 'axios'

const baseUrl = '/api/tax'

export default {
  getAll () {
    return axios.get(baseUrl + '/get_all')
      .then(response => response.data)
  },
  update (payload) {
    return axios.put(baseUrl + '/update', payload)
      .then(response => response.data)
  },
  save (payload) {
    return axios.post(baseUrl + '/add', payload)
      .then(response => response.data)
  },
  delete (payload) {
    return axios.delete(baseUrl + '/delete/' + payload)
      .then(response => true)
  }
}
