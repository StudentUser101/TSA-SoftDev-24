import chromadb
import cohere



co = cohere.Client('dl3QEtESsmTw931F4SgWwNTxVVPLaaf7WbkfpA41')



client = chromadb.PersistentClient(path="/Users/karunyapenumalla/pynotebooks/Flask_testing/DB")
collection = client.get_collection(name="GenEnhance")
print("x")
def chroma_to_llm_parser(documents):
	docs=[]
	for num in range(len(documents["ids"])):
		for num1 in range(len(documents["ids"][num])):
			x = {"title":documents["ids"][num][num1],"snippet":documents["documents"][num][num1]}
			docs.append(x)
	return docs


def chroma_to_rerank(documents):
	doc=[]
	for num in range(len(documents["ids"])):
		for num1 in range(len(documents["ids"][num])):
			x = documents["documents"][num][num1]
			doc.append(x)
	return doc


def rerank_to_cohere(documents):
	doc=[]
	for num in range(len(documents.results)):
		x = {"title":f"{documents.results[num].index}","snippet":documents.results[num].document["text"]}
		doc.append(x)
	return doc



def get_answer(question, keyword = " "):
	print(question)
	documents = collection.query(
    query_texts= question,
    n_results= 3
	)
	print(documents)
	answer = co.chat(message = question, documents = chroma_to_llm_parser(documents))
	
	return(answer.text)

