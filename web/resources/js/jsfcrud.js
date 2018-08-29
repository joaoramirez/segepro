function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}


$( "#mas" ).click(function() {
  alert( "Handler for .click() called." );
});



$( document ).ready(function() {
    
    
    $( ".fa-bars" ).click(function() {
        $(".nav-label").toggle( "slow" );        
        $(".nav-header").toggle( "slow" );
       if($("#side-menu").css('width') == '70px'){            
              $("#page-wrapper").css("margin" , "0 0 0 220px !important");           
              $(".nav>li").css("width" , "220px");
              $("#side-menu").css("width" , "220px");
              $(".sidebar-collapse").css("width" , "220px");
              $(".navbar-static-side").css("width" , "220px");
        }else{            
              $("#page-wrapper").css("margin" , "0 0 0 70px");           
              $(".nav>li").css("width" , "70px");
              $("#side-menu").css("width" , "70px");
              $(".sidebar-collapse").css("width" , "70px");
              $(".navbar-static-side").css("width" , "70px");
              
              
            
            
        }
         
        
    });
    
    $( ".nav-firts" ).children("a").click(function (e) {     
        
        if($(this).parent().children('ul.nav-second-level').hasClass("in")){
             $(this).parent().children('ul.nav-second-level').removeClass("in");
        }else{
             $(this).parent().children('ul.nav-second-level').addClass("in");
        }
    })


    $( ".nav-second-level > li.nav-second" ).click(function (e) {
        
   if($(this).children('ul.nav-third-level').hasClass("in")){
         $(this).children('ul.nav-third-level').removeClass("in");
    }else{
         $(this).children('ul.nav-third-level').addClass("in");
        
    }
})



});
        