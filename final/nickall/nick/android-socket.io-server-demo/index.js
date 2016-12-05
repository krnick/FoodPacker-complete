var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var userlist=new Object();
app.get('/',function(req,res){
    res.sendFile(__dirname+'/index.html');
})
io.on('connection',function(socket){
    console.log('one user connected '+socket.id);

//add user
        socket.on('user',function(username){
        userlist[username]=socket.id;
        socket.username=username;
        console.log(username+''+socket.id);
        })




				//the other name
	socket.on('not',function(data,delete_id,desg,money,score){


	console.log('傳送id'+userlist[data]);

	console.log('欲刪除id'+delete_id);
        console.log('price'+money);
	console.log('desg'+desg);	

	io.to(userlist[data]).emit('new message',{hello:socket.username,id:delete_id,desg:desg,money:money,score:score});

	console.log('送出資料給'+data);
					})

	                         //the other name
        socket.on('deal',function(data,FBname){


        console.log('傳送id'+userlist[data]);


        io.to(userlist[data]).emit('deal',{hello:FBname});

        console.log('送出資料給'+data);
                                        })



})



http.listen(3000,function(){
    console.log('server listening on port 3000');
})
