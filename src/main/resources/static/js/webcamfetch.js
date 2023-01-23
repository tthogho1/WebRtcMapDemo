const baseurl = "http://localhost:3000/list";
const headers = {
  'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
};


self.addEventListener('message', function (e) {

  if (!e.data.latitudegt || !e.data.latitudelt || !e.data.longitudegt || !e.data.longitudelt) {
    
    self.postMessage('入力エラーです');
    
  } else {
	var url 
		= baseurl + "?latitudegt=" + e.data.latitudegt 
			+ "&latitudelt=" + e.data.latitudelt	
			+ "&longitudegt=" + e.data.longitudegt
			+ "&longitudelt=" + e.data.longitudelt;
	
	fetch(url)
		.then((res)=>res.json())
		.then((data)=>{
			self.postMessage(data)
		})
		.catch(console.error);
//	self.postMessage(Number(e.data.width) * Number(e.data.height));
  }
  
})




