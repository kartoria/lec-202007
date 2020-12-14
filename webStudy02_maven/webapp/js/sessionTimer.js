/**
 * 
 */
if($ && !$.fn.sessionTimer){
   $.timeToText = function(time){
      let min = Math.trunc(time/60);
      let sec = time%60;
      return min + ":" + sec;
   }
   
   

   $.fn.sessionTimer = function(param){
      const TIMEOUT = param.timeout;
      const SESSIONURL = param.sessionURL;
      let timeoutId = null;
      const TIMEUNIT = 100;
      let time = TIMEOUT;
      let timeArea = this;
      let jobId = null;
      let msgArea = null;
      makeMessageModal = function(){
         let msgArea = $("<div>").html(MSGMODALSRC.trim()).find("#messageModal").modal();
         let idTime = (new Date()).getTime();
         let id = msgArea.prop("id");
         msgArea.prop("id", id+"_"+idTime);
         msgArea.find("[id]").each(function(index, element){
            let tmpId = $(this).prop("id");
            $(this).prop("id", tmpId+"_"+idTime);
         });
         $("body").append(msgArea);
         msgArea.on("click", ".msgBtn",function(){
      //      $(this).closest("div").hide();
            msgArea.modal("hide");
            if(this.id.indexOf("yes") == 0 ){
               $.ajax({
                  url : SESSIONURL,
                  method : "head"
               }).done(function(){
                  
                  init();
               });
            }
         });
         return msgArea;
      }
      let init = function(){
         time = TIMEOUT;
         if(msgArea == null)
            msgArea = makeMessageModal();
         if(timeoutId == null)
            timeoutId = setTimeout(function(){
               msgArea.modal("show");
               timeoutId = null;
            }, (TIMEOUT-60)*TIMEUNIT);
         
         if(jobId == null)
            jobId = setInterval(() => {
               if(time == 0){
                  clearInterval(jobId);
               } else {
                  timeArea.text($.timeToText(--time));
               }
            }, TIMEUNIT);
      }
   
      init();
      return this;
   }

   const MSGMODALSRC =  
   '<div class="modal fade" id="messageModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">'+
   '<div class="modal-dialog">                                                                                     '+
   '  <div class="modal-content">                                                                                  '+
   '    <div class="modal-header">                                                                                 '+
   '      <h5 class="modal-title" id="messageModalLabel">Modal title</h5>                                          '+
   '    </div>                                                                                                     '+
   '    <div class="modal-body">                                                                                   '+
   '       세션 연장?                                                                                              '+
   '    </div>                                                                                                     '+
   '    <div class="modal-footer">                                                                                 '+
   '       <button class="msgBtn" id="yes">예</button>                                                             '+
   '       <button class="msgBtn" id="no">아니요</button>                                                          '+
   '    </div>                                                                                                     '+
   '  </div>                                                                                                       '+
   '</div>                                                                                                         '+
   '</div>                                                                                                         ';
}