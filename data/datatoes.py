from elasticsearch import Elasticsearch
import pymysql

es = Elasticsearch([{'host':'127.0.0.1','port':9111}])

# 打开数据库连接
db = pymysql.connect("localhost","root","root","mkds" )
 
sqlIds = "select S_Id from subject where ET_Id = 1028"

cursor = db.cursor()

cursor.execute(sqlIds)

sqlIdsResults = cursor.fetchall()

for ids  in sqlIdsResults:
    S_Id = ids[0]
    sqlData = "select S_Id, E_Id, EO_Id, ST_Id, Title, Content, Analysis, Result, Point from subject where S_Id = %d"%(S_Id)
    cursor.execute(sqlData)
    data = cursor.fetchone()

    esid = data[0]
    groupId = data[1]
    realTopicId = data[2]
    estype = data[3]
    title = data[4]
    content = data[5]
    analysis = data[6]
    result = data[7]
    grade = data[8]

    es.index(index="build", doc_type="_doc", id=esid, body={"id": esid, "groupId": groupId, "realTopicId": realTopicId, "type": estype, "title": title, "content": content, "analysis": analysis, "result": result, "grade": grade})

# 关闭数据库连接
db.close()