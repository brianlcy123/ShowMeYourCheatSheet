$(document).ready(function() {
    'use strict';
    var dropZone = $('#drop-zone');
    var uploadForm = $('#js-upload-form');

    var startUpload = function(files) {
        console.log(files);
        if(files.length>1)
            console.log("More than 1 file are uploaded. Only the first file is selected.");
        var file = files[0];
        var FR = new FileReader();
        FR.onload = function(e){
            var idata =e.target.result;
            //$(".preview").attr('src', idata);
            var base64_data = idata.substring(idata.indexOf(","),idata.length);
            console.log(base64_data);
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
              $("#uploadResult").prepend($('<img>',{id:imgId, src:imgLink}));
            },
            error: function(request, status, error){
                console.log(request);
                console.log(status);
                console.log(error);
            }
          });
        }; 
        FR.readAsDataURL(file);        
    };

    uploadForm.submit(function(e) {
        var uploadFiles = document.getElementById('js-upload-files').files;
        e.preventDefault();
        startUpload(uploadFiles);
        
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