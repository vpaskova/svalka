import axios from 'axios'
// import authHeader from './auth-header'

const API_URL = 'http://localhost:8080/app'

class UserService {
  // getAllUsers () {
  //   return axios.get(API_URL + '/user', { headers: authHeader() })
  // }
  getAllUsers () {
    return axios.get(API_URL + '/user')
  }

  // getUsersPage (filters) {
  //   return axios.get(API_URL + '/search/page', { params: { firstName: filters.firstName } })
  // }

  getPublicContent () {
    return axios.get(API_URL + 'all')
  }
}
export default new UserService()
