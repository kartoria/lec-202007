<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/fancytree/skin-win8/ui.fancytree.min.css">
<script src="${pageContext.request.contextPath }/js/fancytree/jquery.fancytree-all.min.js"></script>
<script>
	$(function(){
		const NODEURL = "${pageContext.request.contextPath }/employees/hirarchy.do";
		
		$("#tree1").fancytree({
			extensions : [ "filter"],
			source : {
				url : NODEURL,
				cache : false
			},
			lazyLoad : function(event, data) {
				var node = data.node;
				// Load child nodes via Ajax GET /getTreeData?mode=children&parent=1234
				data.result = {
					url : NODEURL,
					data : {
						base : node.key
					},
					cache : false
				};
			},
			filter : {
				autoApply : true, // Re-apply last filter if lazy data is loaded
				autoExpand : false, // Expand all branches that contain matches while filtered
				counter : true, // Show a badge with number of matching child nodes near parent icons
				fuzzy : false, // Match single characters in order, e.g. 'fb' will match 'FooBar'
				hideExpandedCounter : true, // Hide counter badge if parent is expanded
				hideExpanders : false, // Hide expanders if all child nodes are hidden by filter
				highlight : true, // Highlight matches by wrapping inside <mark> tags
				leavesOnly : false, // Match end nodes only
				nodata : true, // Display a 'no data' status node if result is empty
				mode : "dimm" // Grayout unmatched nodes (pass "hide" to remove unmatched node instead)
			}
		});

		$("#search").on("change", function() {
			let tree = $.ui.fancytree.getTree("#tree1");
			console.log(tree);
			let keyword = $(this).val();
			tree.filterNodes(keyword);
		});
	});
</script>
<h4>탐색기</h4>
<input type="text" id="search" class="form-control"
	placeholder="Filtering" />
<div id="tree1"></div>















