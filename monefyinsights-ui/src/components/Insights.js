import {useEffect, useState} from "react";
import {InsightsApi} from "../model/InsightsApi";
import {MonthlyInsightsChart} from "./charts/Charts";

const investmentsCategoryName = 'Inwestycje'

async function fetchInsights(categoryName) {
  const result = await InsightsApi.getInsights(categoryName)
  return await result.json()
}

function fetchCategory(callback, categoryName = 'All') {
  fetchInsights(categoryName).then(({insights}) => callback(insights))
}

export default function Insights() {
  const [insights, setInsights] = useState([])
  const [investments, setInvestments] = useState([])
  useEffect(() => fetchCategory(setInsights), []);
  useEffect(() => fetchCategory(setInvestments, investmentsCategoryName), []);

  return <>
    <MonthlyInsightsChart chartId={'TOTAL_ACCOUNT_INCOME_BY_MONTH'}
                          insights={insights}
                          label={'Income'}
                          metric={'TOTAL_ACCOUNT_INCOME_BY_MONTH'}/>
    <MonthlyInsightsChart
        chartId={'TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS_CHART'}
        insights={insights}
        label={'Outcome excl. investments'}
        metric={'TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS'}/>
    <MonthlyInsightsChart chartId={'TOTAL_ACCOUNT_SPEND_BY_MONTH_CHART'}
                          insights={insights}
                          label={'Outcome incl. investments'}
                          metric={'TOTAL_ACCOUNT_SPEND_BY_MONTH'}/>

    <MonthlyInsightsChart
        chartId={'INVESTMENTS_TOTAL_ACCOUNT_SPEND_BY_MONTH_CHART'}
        insights={investments}
        label={'Investments'}
        metric={'TOTAL_ACCOUNT_SPEND_BY_MONTH'}/>

    <div>{JSON.stringify(insights)}</div>
  </>
}