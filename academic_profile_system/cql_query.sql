/* 查看命名图 */
call gds.graph.list()

/* 创建教师与论文的命名图 */
CALL gds.graph.create('SimilarScholar', ['Scholar', 'Article'], 'Publish');

/* 执行节点相似度算法 */
CALL gds.nodeSimilarity.stream('SimilarScholar') YIELD node1, node2, similarity
RETURN
    id(gds.util.asNode(node1)) as ID1,
    gds.util.asNode(node1).name AS Scholar1,
    id(gds.util.asNode(node2)) as ID2,
    gds.util.asNode(node2).name AS Scholar2,
    similarity
ORDER BY ID1 DESC, similarity DESC

/* 查询教师关系 */
match (s:Scholar) with s
optional match (s)-[]-(a1:Article) with a1,id(s) as id,s.name as name
optional match p=(a1)-[]-(s2:Scholar) with distinct(s2.name) as x,id,name
return id,name,collect(x) as relation order by id asc

/* 查询Co-Author */
match (s:Scholar) with s
optional match (s)-[]-(a1:Article) with id(s) as sourceID,a1
optional match p=(a1)-[]-(s2:Scholar) with distinct(id(s2)) as targetID,sourceID
match (s3:Scholar)-[]-(a2:Article)-[]-(s4:Scholar) where id(s3)=sourceID and id(s4)=targetID
return sourceID,
    s3.name as source,
    s3.organization as sourceOrg,
    targetID,
    s4.name as target,
    s4.organization as targetOrg,
    count(a2) as value
    order by sourceID asc

/* 教师数据统计 */
match (s:Scholar) with s
optional match (s)-[]-(a1:Article) with s,count(a1) as papers
optional match (s)-[]-(a2:Article) where a2.citeCount is not null with s,papers,sum(toInt(a2.citeCount)) as citation
optional match (s)-[]-(a3:Article) where a3.citeCount is not null
    with s,papers,citation,a3.citeCount as x order by toInt(a3.citeCount) desc with s,papers,citation,collect(x) as citationSort
optional match (s)-[]-(a4:Article)-[]-(s2:Scholar) with s,papers,citation,citationSort,count(distinct(s2)) as partnerCount
optional match (s)-[]-(a5:Article) where a5.keyWord is not null
    with s,papers,citation,citationSort,partnerCount,split(a5.keyWord,',') as x unwind x as y with s,papers,citation,citationSort,partnerCount,count(distinct(y)) as keywordCount
optional match (s)-[]-(a6:Article) where a6.year in ['2018','2019'] return id(s) as id,papers,citation,partnerCount,keywordCount,count(a6) as nearlyTwoYearArticleCount,citationSort order by id asc
