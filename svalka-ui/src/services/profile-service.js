import axios from 'axios'

const API_URL = 'http://localhost:8080/app'

class ProfileService {
  getAllUsers () {
    return axios.get(API_URL + '/user')
  }

  getUserByiId (filters) {
    return axios.get(API_URL + '/search/id',
      {
        params:
          {
            id: filters.id
          }
      })
  }

  saveImage (image, filters) {
    const formData = new FormData()
    formData.append('file', image)
    formData.append('id', filters.id)
    return axios.post('http://localhost:8080/a' + '/upload', formData)
  }

  saveData (filters) {
    const format = new FormData()
    format.append('id', filters.id)
    format.append('firstName', filters.firstName)
    format.append('lastName', filters.lastName)
    format.append('age', filters.age)
    format.append('email', filters.email)
    format.append('moto', filters.moto)
    format.append('city', filters.city)
    format.append('phone', filters.phoneNumber)
    format.append('gender', filters.gender)
    format.append('genderPreference', filters.genderPreference)
    format.append('zodiacSign', filters.zodiacSign)
    format.append('relationshipPreference', filters.relationshipPreference)
    format.append('description', filters.description)
    return axios.post(API_URL + '/save', format)
  }

  loadImage (filters) {
    return axios.get('http://localhost:8080/a' + '/files', { params: { id: filters.id } })
  }
}
export default new ProfileService()
