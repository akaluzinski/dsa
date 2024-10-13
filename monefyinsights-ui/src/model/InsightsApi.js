export class InsightsApi {

  static API_PATH = 'http://localhost:8080/insights'

  constructor() {
  }

  static async getInsights(category = 'All') {
    if (category === 'All') {
      return await fetch(InsightsApi.API_PATH)
    } else {
      return await fetch(`${InsightsApi.API_PATH}?category=${category}`)
    }
  }

}