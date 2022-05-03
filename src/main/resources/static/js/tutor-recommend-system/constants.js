//原始：const BASE_URL = "http://10.12.45.49:8081/" 修改为下面调试
const BASE_URL = "http://localhost:8081/"
const TOKEN_KEY = "ida_token"
const ENTITY_TYPE = [
    {name: "论文"},
    {name: "专利"},
    {name: "横向项目"},
    {name: "纵向项目"},
    {name: "组织"},
    {name: "导师"}
]
const RELATION_TYPE = {
    "Publish": "发表",
    "Participate": "参与",
    "Belong": "属于",
    "Lead": "主导",
    "FirstInvent": "第一发明人",
    "SecondInvent": "次发明人"
}
