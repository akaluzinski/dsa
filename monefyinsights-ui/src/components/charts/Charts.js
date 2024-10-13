import {useEffect} from "react";
import * as d3 from "d3";

const metricDateFormat = "%Y-%m"

export function MonthlyInsightsChart({insights, chartId, metric, label}) {
  return <>
    <div id={chartId}/>
    {insights.length && <LineChart dataPoints={insights}
                                   chartId={chartId}
                                   metricName={metric}
                                   label={label}
    ></LineChart>}
  </>
}

function LineChart({
  dataPoints,
  metricName,
  chartId,
  label = metricName
}) {
  const createGraph = async () => {
    if (dataPoints.length === 0) {
      return <>No data</>
    }

    const rawMetricValue = dataPoints.find(
        ({metric}) => metric === metricName).value

    const data = Object.keys(rawMetricValue)
    .map((date) => ({
      date: d3.timeParse(metricDateFormat)(date),
      value: rawMetricValue[date]
    }))

    const margin = {top: 60, right: 30, bottom: 30, left: 60}
    const width = 800 - margin.left - margin.right
    const height = 600 - margin.top - margin.bottom

    const svg = d3.select(`#${chartId}`)
    .append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", `translate(${margin.left},${margin.top})`);

    const x = d3.scaleTime()
    .domain(d3.extent(data, ({date}) => date))
    .range([0, width]);
    svg.append("g")
    .attr("transform", `translate(0, ${height})`)
    .call(d3.axisBottom(x));

    const y = d3.scaleLinear()
    .domain([0, d3.max(data, d => +d.value)])
    .range([height, 0]);
    svg.append("g")
    .call(d3.axisLeft(y));

    svg.append("path")
    .datum(data)
    .attr("fill", "none")
    .attr("stroke", "steelblue")
    .attr("stroke-width", 1.5)
    .attr("d", d3.line()
        .x(d => x(d.date))
        .y(d => y(d.value))
    )

    svg.append("text")
    .attr("x", (width / 2))
    .attr("y", 0 - (margin.top / 2))
    .attr("text-anchor", "middle")
    .style("font-size", "16px")
    .style("text-decoration", "underline")
    .text(label);
  }

  useEffect(() => {
    createGraph();
  }, []);

  return <></>
}