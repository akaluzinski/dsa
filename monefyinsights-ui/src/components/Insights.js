import {useEffect, useState} from "react";
import {InsightsApi} from "../model/InsightsApi";
import {MonthlyInsightsChart} from "./charts/Charts";

export default function Insights() {
  const [insights, setInsights] = useState([])

  useEffect(() => {
    async function fetchInsights() {
      const result = await InsightsApi.getInsights()
      if (!result.ok) {
        // todo
      }

      const data = await result.json()
      setInsights(data.insights)
    }

    fetchInsights()
  }, []);

  return <>
    <MonthlyInsightsChart chartId={'TOTAL_ACCOUNT_INCOME_BY_MONTH'}
                          insights={insights}
                          metric={'TOTAL_ACCOUNT_INCOME_BY_MONTH'}/>
    <MonthlyInsightsChart
        chartId={'TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS_CHART'}
        insights={insights}
        metric={'TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS'}/>
    <MonthlyInsightsChart chartId={'TOTAL_ACCOUNT_SPEND_BY_MONTH_CHART'}
                          insights={insights}
                          metric={'TOTAL_ACCOUNT_SPEND_BY_MONTH'}/>
    <div>{JSON.stringify(insights)}</div>
  </>
}