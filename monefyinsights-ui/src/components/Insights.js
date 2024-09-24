import {useEffect, useState} from "react";

export default function Insights() {
  const [insights, setInsights] = useState([])

  useEffect(() => {
    async function fetchInsights() {
      const result = await fetch('http://localhost:8080/insights')
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
    <div>{JSON.stringify(insights)}</div>
  </>

}