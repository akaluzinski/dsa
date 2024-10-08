export class InsightsApi {

  static API_PATH = 'http://localhost:8080/insights'

  constructor() {
  }

  static async getInsights() {
    return await fetch(InsightsApi.API_PATH)
  }

}