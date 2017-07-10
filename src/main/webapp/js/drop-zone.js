var displayLast5UploadedImages = function(){
        $.ajax({
            url: '/img/last5',
            method: 'GET',
            success: function(data,status) {
             console.log(status);
             console.log(data);
             var prefix = 'http://imgur.com/';
             var surfix = 's.png';
             $.each(data,function(index,val){
                 if(index == 0) $("#img1").attr('src',prefix+val.imgurId+surfix);
                 if(index == 1) $("#img2").attr('src',prefix+val.imgurId+surfix);
                 if(index == 2) $("#img3").attr('src',prefix+val.imgurId+surfix);
                 if(index == 3) $("#img4").attr('src',prefix+val.imgurId+surfix);
                 if(index == 4) $("#img5").attr('src',prefix+val.imgurId+surfix);
             });
            },
            error: function(request, status, error){
                console.log(request);
                console.log(status);
                console.log(error);
            }
          });
}
$(document).ready(function() {
    'use strict';
    var dropZone = $('#drop-zone');
    var uploadForm = $('#js-upload-form');
    displayLast5UploadedImages();
    var startUpload = function(files) {
        console.log(files);
        if(files.length>1)
            console.log("More than 1 file are uploaded. Only the first file is selected.");
        var file = files[0];
        var FR = new FileReader();
        FR.onload = function(e){
            var idata =e.target.result;
            var base64_data = idata.substring(idata.indexOf(","),idata.length);
            imgurUpload(base64_data);
        }; 
        FR.readAsDataURL(file);        
    };
    
    var imgurUpload =  function(base64_data){
        $.ajax({
            url: 'https://api.imgur.com/3/image',
            method: 'POST',
            headers: {
              Authorization: 'Client-ID 360bd34dd9a6ad3',
              Accept: 'application/json'
            },
            data: {
              image: base64_data
            },
            success: function(result) {
              var imgId = result.data.id;
              var imgLink = result.data.link;
             console.log(imgLink);
             logFileUpload(result.data);
            },
            error: function(request, status, error){
                console.log(request);
                console.log(status);
                console.log(error);
            }
          });
    }
    
    var logFileUpload = function(data){
        var imgId = data.id;
        var imgLink = data.link;
        var image = {
            'imgurId': imgId,
            'tagString': $("#fileTags").val()
        };
        console.log(image);
        $.ajax({
           url: '/img',
           type: 'POST',
           dataType : "json",
           contentType: "application/json; charset=utf-8",
           data: JSON.stringify(image),
           success: function(result){
               console.log(result);
               $("#uploadResult").prepend($('<img>',{id:imgId, src:imgLink}));
           }
        });
        
    }

    uploadForm.submit(function(e) {
        var uploadFiles = document.getElementById('js-upload-files').files;
        e.preventDefault();
        startUpload(uploadFiles);
        displayLast5UploadedImages();
    });

    dropZone.ondrop = function(e) {
        e.preventDefault();
        e.stopPropagatioin();
        this.className = 'upload-drop-zone';
        var files = e.dataTransfer.files;  
        startUpload(files);
    };

    dropZone.ondragover = function(e) {
        e.preventDefault();
        e.stopPropagatioin();
        this.className = 'upload-drop-zone drop';
        return false;
    };

    dropZone.ondragleave = function(e) {
        e.preventDefault();
        e.stopPropagatioin();
        this.className = 'upload-drop-zone';
        return false;
    };

});