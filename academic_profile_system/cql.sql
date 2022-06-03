-- 添加论文摘要
match (n:Article) where id(n)=410078 set n.abstract="Industry alliance is a cooperative mode based on some kind of contract or restraint, which is a new stage of the evolution and development of industrial cluster. In order to enhance the sharing ability of manufacturing resources, a new application mode called cloud manufacturing for industry alliance (CMfgIA) is presented in this paper. Firstly, the concept, architecture, and operation mechanism of CMfgIA is elaborated. Secondly, a domain-driven development approach for multi- granularity manufacturing services is proposed to ensure on-demand supply of manufacturing resources. In the approach, an improved feature model which contains process information of service composition is used to construct domain requirements. Based on the feature model, two-phase development process of manufacturing service is described. In the first phase, atomic manufacturing services are developed based on atomic features, and in the second phase, coarse- grained manufacturing services are modeled through automatic service composition which is realized by feature model customization. Then, a relationship management model is applied to manage the relationships between customized feature models and manufacturing services. Finally, an application example of cloud manufacturing platform for elevator industry alliance is given to verify the feasibility and effectiveness of CMfgIA." return n
match (n:Article) where id(n)=421204 set n.abstract="针对经典 Ｃ４．５决策树算法存在过度拟合和伸缩性差的问题，提出了一种基于 Ｂａｇｇｉｎｇ 的决策树改进算法，并基于 ＭａｐＲｅｄｕｃｅ模型对改进算法进行了并行化。首先，基于Ｂａｇｇｉｎｇ技术对Ｃ４．５算法进行了改进，通过有放回采样得到多个与初始训练集大小相等的新训练集，并在每个训练集上进行训练， 得到多个分类器，再根据多数投票规则集成训练结果得到最终的分类器；然后，基于 ＭａｐＲｅｄｕｃｅ模型对改进算法进行了并行化，能够并行化处理训练集、并行选择最佳分割属性和最佳分割点，以及并行生成子节 点，实现了基于 ＭａｐＲｅｄｕｃｅＪｏｂ工作流的并行决策树改进算法，提高了对大数据集的分析能力。实验结果表明，并行Ｂａｇｇｉｎｇ决策树改进算法具有较高的准确度与敏感度，以及较好的伸缩性和加速比。" return n

-- 添加教师信息
match (n:Scholar) where id(n)=439337
    set n.homepage='http://www.homepage.zjut.edu.cn/zym/'
    set n.address='浙江工业大学计算机科学与技术学院、软件学院计算机楼B408'
    set n.experience='2010年4月至今在浙江工业大学计算机科学与技术学院从事教学科研工作'
    set n.education='2010年3月毕业于日本宇都宫大学'
    set n.bio='工学博士，副教授。入选浙江省“新世纪151人才工程”，获浙江省钱江人才（D类）项目资助，主持并参与国家基金、省部级项目以及横向项目10余项。以第一作者（通信作者）在《计算机学报》、《电子学报》、《Neurocomputing》、《 Journal of Parallel and Distributed Computing》、《 International Journal of High Performance Computing and Networking》等国内外重要期刊和国际会议上发表学术论文30余篇，授权发明专利10多项。担任《TPDS》、《计算机学报》、《计算机科学》等期刊和会议的审稿人。中国计算机学会会员，ACM会员。'
return n

match (n:Scholar) where id(n)=410077
    set n.homepage='http://www.homepage.zjut.edu.cn/feig/'
    set n.address='计算机楼'
    set n.experience=''
    set n.bio='博士、教授、博导，计算机学院党委委员、分工会主席、图形图像研究所所长。1994-2004浙江大学本、硕、博,2004年入职浙江工业大学。入选首批校级青年学术骨干、浙江省“新世纪151人才工程”第三层次、浙江工业大学青年学术带头人培养计划、浙江省高校优秀青年教师资助计划。曾赴美国The Pennsylvania State University做访问学者。长期从事视频图像大数据(工业视觉检测、目标检测与跟踪、视频理解等)、智能制造、物联网工程等方面的研究，在《机械工程学报》、《仪器仪表学报》、《International Journal of Advanced Manufacturing Technology》、《Computer-Aided Design》、《Research in Engineering Design》、《Computers in Industry》、《IEEE Trans. on Instru. and Meas.》等国内外知名期刊发表论文100余篇，申请发明专利150余件，主持与参加了国家重点研发计划、国家自然科学基金、浙江省自然科学基金、浙江省重点研发计划等纵横向项目50余项，获浙江省科学技术进步三等奖2项、浙江省首届优秀发明成果奖、浙江省首届优秀发明人才奖、浙江省计算机学会/浙江省计算机行业协会优秀成果奖、浙江工业大学“我最喜爱的老师”德才兼备奖、浙江工业大学第七届、第十二届研究生“我心目中的好导师”等多个奖项及荣誉。'
return n
