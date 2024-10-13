import {InsightsApi} from "../model/InsightsApi";

async function fetchInsights(categoryName = 'All') {
  const result = await InsightsApi.getInsights(categoryName)
  return await result.json()
}

export function fetchBasicInsights(insightsCallback,
    expenseCategoriesCallback) {
  fetchInsights().then(({insights, metadata}) => {
    insightsCallback(insights)
    expenseCategoriesCallback(metadata.expenseCategories)
  })
}

export function fetchCategoryInsights(categoryName, insightsCallback) {
  if (!categoryName) {
    return
  }
  console.log(`Fetch category insights of "${categoryName}"`)
  fetchInsights(categoryName).then(({insights}) => {
    // debugger
    insightsCallback(insights)
  })
}