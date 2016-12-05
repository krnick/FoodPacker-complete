var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
  res.sendfile('index.html');
});

io.on('connection', function(socket){
  console.log('a user connected');
});

http.listen(8080, '140.127.218.212',  function(){
  console.log('HTTP Server: http://127.0.0.1:8080/');
});
