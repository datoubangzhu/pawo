
function login(){
    let da = {
            "username": "goucc",
            "password": "25d55ad283aa400af464c76d713c07ad"
        };

    $.ajax({
        type: 'POST',
        url:"http://192.168.0.170:9002/i2/login",
        contentType:"application/json",
        
        data: JSON.stringify(da),
        dataType:'json',
        success: function (data,status) {
                $.each(data,function(i, result){
                    alert("请求成功,状态："+status+"数据："+i+' '+result);
                });
                window.open('index.html');
        },
        error: function (data,status) {
            alert("请求失败,状态："+status);
            window.history.back(-1);
        }
    });
}



function getUserInfo() {
    $.ajax({
        type: 'GET',
        url:"http://127.0.0.1:20002/pawo/user/list",
        success: function (data) {
            showTable(data)
        },
        error: function (data,status) {
            console.log("进入请求");

            alert("请求失败,状态："+status);
            window.history.back(-1);
        }
    });
}


function showTable(data) {
    $("#name1").val(data.username);
    $("#xinghao1").val(data.type);
    $("#address1").val(data.createTime);
    $("#department1").val(data.department);
    $("#unit1").val(data.unit);
    $("#number1").val(data.number);
    $("#price1").val(data.price);
    $("#totalprice1").val(data.totalprice);
    $("#come1").val(data.come);
    $("#buytime1").val(data.buytime);
    $("#useperson1").val(data.useperson);
    $("#handleperson1").val(data.handleperson);
    $("#admini1").val(data.admini);
    // 显示模态框
    $('#queryInfo').modal('show');
}