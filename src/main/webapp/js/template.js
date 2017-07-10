$(function(){
    $("#body").load("home-body.html");
    
    $("#home").on('click',function(){
        $("#body").load("home-body.html");
    })
    $("#upload").on('click',function(){
        $("#body").load("imgur.html");
    })
})

