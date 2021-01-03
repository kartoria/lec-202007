/**
 * 
 */
let prod_buyerTag = $("[name='prod_buyer']");
let prod_lguTag = $("[name='prod_lgu']").getLprodAndBuyer(prod_buyerTag);

$("#prodForm").validate({
	onsubmit:true,
	onfocusout:function(element, event){
		return this.element(element);
	},
	errorPlacement: function(error, element) {
		error.appendTo( $(element).parents("td:first") );
  	}
});