import CategoryInsight from "./CategoryInsight";

export default function CategoryInsights({categories}) {
  
  return <>{categories.map(
      category => <CategoryInsight category={category}></CategoryInsight>)}</>
}