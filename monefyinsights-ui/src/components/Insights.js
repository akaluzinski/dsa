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

  return <>
    <div>Insights</div>
    <LineChart></LineChart>
    <div>{JSON.stringify(insights)}</div>
  </>

}