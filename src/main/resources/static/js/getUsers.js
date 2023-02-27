const baseurl = "../getUsers";
/*const headers = {
  'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
};*/


const headers = {'Accept':'application/json'};		
//console.log("fetch API" + sendData);
 fetch(baseurl,{
	headers:headers,
	}).then((res)=>res.json())
	.then((data)=>{
		
		if (data.status){
			console.log("error : " + data.status);
			data={}; 
		}
		self.postMessage(data)
	}).catch(console.error);
