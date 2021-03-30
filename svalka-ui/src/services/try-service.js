import axios from 'axios'

const API_URL = 'http://localhost:8080/app'

class TryService {
  getAllUsers () {
    return axios.get(API_URL + '/user')
  }

  getUsersSearch (filters, currPage, perPage) {
    return axios.get(API_URL + '/search/pages',
      {
        params:
          {
            city: filters.city,
            gender: filters.gender,
            relationship: filters.relationship,
            currentPage: currPage,
            perPage: perPage
          }
      })
  }

  getUserByiId (id) {
    return axios.get(API_URL + '/search/id',
      {
        params:
          {
            id: id
          }
      })
  }
}

export default new TryService()
