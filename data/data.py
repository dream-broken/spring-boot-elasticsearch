#pip3 install elasticsearch
from elasticsearch import Elasticsearch

es = Elasticsearch([{'host':'127.0.0.1','port':9200}])

es.index(index="build", doc_type="highway_second", id=2, body={"id": 2, "groupId": 1, "realTopicId": 2, "type": 2, "title": "2", "content": "2", "analysis": "2", "result": "2", "grade": 2})