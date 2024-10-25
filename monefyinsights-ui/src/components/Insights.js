import {useEffect, useState} from "react";
import {MonthlyInsightsChart} from "./charts/Charts";
import {fetchBasicInsights} from "../services/insights_service";
import CategoryInsights from "./CategoryInsights";

export default function Insights() {
  const [insights, setInsights] = useState([])
  const [categories, setCategories] = useState([])
  useEffect(() => fetchBasicInsights(setInsights, setCategories), []);

  return <>
    <MonthlyInsightsChart chartId={'BASIC_COSTS_OF_LIVING'}
                          insights={insights}
                          label={'Costs of living'}
                          metric={'BASIC_COSTS_OF_LIVING'}/>
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


    <CategoryInsights categories={categories}></CategoryInsights>
  </>
}