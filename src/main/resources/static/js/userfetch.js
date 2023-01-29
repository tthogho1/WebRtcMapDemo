const baseurl = "../ajaxPostUser";
const headers = {
  'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
};


self.addEventListener('message', function (e) {

  if (!e.data.userid || !e.data.longitude || !e.data.latitude ) {
    
    self.postMessage('入力エラーです');
    
  } else {
	var url = baseurl ;
	//var sendData = "id=" +  e.data.userid + "&longitude=" + e.data.longitude + "&latitude=" + e.data.latitude;
	const headers = {'Accept':'application/json'};	
	const method = "POST";
	
	var formData = new FormData();
	formData.append('id',e.data.userid);
	formData.append('longitude',e.data.longitude);
	formData.append('latitude',e.data.latitude);
	
	//console.log("fetch API" + sendData);
	fetch(url,{
		method:'POST',
		headers:headers,
		body:formData
		}).then((res)=>res.json())
		.then((data)=>{
			console.log(data.status);
			self.postMessage(data)
		})
		.catch(console.error);
//	self.postMessage(Number(e.data.width) * Number(e.data.height));
  }
  
})
