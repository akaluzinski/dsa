import {useEffect, useState} from "react";
import {InsightsApi} from "../model/InsightsApi";
import LineChart from "./charts/Charts";

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

  const chartId = 'charts_locator'

  return <>
    <div id='charts_locator'>Insights</div>
    {insights.length && <LineChart data={insights} chartId={chartId}
                                   metric='TOTAL_ACCOUNT_INCOME_BY_MONTH'></LineChart>}
    <div>{JSON.stringify(insights)}</div>
  </>

}