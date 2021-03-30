import axios from 'axios'

// const API_URL = 'http://localhost:8080/app'

class UserViewService {
  loadImage (userrId) {
    return axios.get('http://localhost:8080/a' + '/files', { params: { id: userrId } })
  }

  // getUserByiId (id) {
  //   return axios.get(API_URL + '/search/id',
  //     {
  //       params:
  //         {
  //           id: id
  //         }
  //     })
  // }
}

export default new UserViewService()
