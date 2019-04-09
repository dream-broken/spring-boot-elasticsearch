#pip3 install PyMySQL
import pymysql

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

    print(esid)
    print(groupId)
    print(realTopicId)
    print(estype)
    print(title)
    print(content)
    print(analysis)
    print(result)
    print(grade)

    break

# 关闭数据库连接
db.close()