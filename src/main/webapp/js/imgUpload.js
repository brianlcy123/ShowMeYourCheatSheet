$(function () {
      
        var $post = $('.post');
        var $msg = $('.hidden');
        var $img = $('img');
 
        $post.click(function() {
         
          var imgdata = $img.attr('src').replace(/.*,/, '');

          $post.hide();
          $msg.show();
 
          $.ajax({
            url: 'https://api.imgur.com/3/image',
            method: 'POST',
            headers: {
              Authorization: 'Client-ID 360bd34dd9a6ad3',
              Accept: 'application/json'
            },
            data: {
              image: imgdata,
              type: 'base64'
            },
            success: function(result) {
              var imgId = result.data.id;
              var imgLink = result.data.link;
              $("#uploadResult").prepend($('<img>',{id:imgId, src:imgLink}));
            }
          });
        });
      });