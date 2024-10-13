import {fetchCategoryInsights} from "../services/insights_service";
import {useEffect, useState} from "react";
import {MonthlyInsightsChart} from "./charts/Charts";

export default function CategoryInsight({category}) {

  const [categoryInsight, setCategoryInsight] = useState([])
  useEffect(() => fetchCategoryInsights(category, setCategoryInsight),
      [category]);

  const chartId = encodeURIComponent(category).replace(/[^a-z0-9]+/gi, "x")

  return <MonthlyInsightsChart chartId={chartId}
                               insights={categoryInsight}
                               label={category}
                               metric={'TOTAL_ACCOUNT_SPEND_BY_MONTH'}/>
}