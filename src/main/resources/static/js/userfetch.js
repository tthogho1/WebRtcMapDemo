const baseurl = "../ajaxPostUserInfo";
const headers = {
  'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
};


self.addEventListener('message', function (e) {

  if (!e.data.userid || !e.data.longitude || !e.data.latitude ) {
    
    self.postMessage('入力エラーです');
    
  } else {
	var url = baseurl ;
	//var sendData = "id=" +  e.data.userid + "&longitude=" + e.data.longitude + "&latitude=" + e.data.latitude;
//	const headers = {'Accept':'application/json'};	
	const method = "POST";
	
	var formData = new FormData();
	formData.append('id',e.data.userid);
	formData.append('longitude',e.data.longitude);
	formData.append('latitude',e.data.latitude);
	
	//console.log("fetch API" + sendData);
	fetch(url,{
		method:'POST',
		body:formData
		})
		.then((res)=>res.json())
		.then((data)=>{
			if (data.status){
				console.log("error : " + data.status);
				data={}; 
			}
			self.postMessage(data);
		})
		.catch((error)=>{
			console.log(error);
		});
  }
})
