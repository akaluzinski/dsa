import {useEffect, useState} from "react";
import {InsightsApi} from "../model/InsightsApi";

import * as d3 from 'd3'

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
    <div>{d3.sum([1, 2, 3, -0.5])}</div>
    <div>{JSON.stringify(insights)}</div>
  </>

}