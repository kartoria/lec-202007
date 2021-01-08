<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/fancytree/skin-win8/ui.fancytree.min.css">
<script src="${pageContext.request.contextPath }/js/fancytree/jquery.fancytree-all.min.js"></script>
<script>
	$(function(){
		const NODEURL = "${pageContext.request.contextPath }/server/explorer.do";
		const COMMANDURL = "${pageContext.request.contextPath }/server/fileCommand.do";
		function commandProcess(param){
			let srcFile = param.srcNode.getKeyPath();
			let destFolder = param.destNode?param.destNode.getKeyPath():"";
			$.ajax({
				url:COMMANDURL,
				data:{
					command : param.command
					, srcFile : srcFile
					, destFolder : destFolder
				},
				method:"post",
				dataType:"text",
				success:function(resp){
					if("SUCCESS"==resp){
						switch (param.command) {
						case "COPY":
							param.srcNode.copyTo(param.destNode, "child");
							break;
						case "MOVE":
							param.srcNode.moveTo(param.destNode, "child");
							break;
						case "DELETE":
							param.srcNode.remove();
						default:
							break;
						}
					}
				},
				error:function(xhr){
					console.log(xhr);
				}
			});
		}
		
		let tree1 = $("#tree1").fancytree({
			extensions : [ "filter", "dnd" ],
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
						base : node.getKeyPath()
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
			},
			dnd : {
				autoExpandMS : 400,
				focusOnClick : true,
				preventVoidMoves : true, // Prevent dropping nodes 'before self', etc.
				preventRecursiveMoves : true, // Prevent dropping nodes on own descendants
				dragStart : function(node, data) {
					console.log("==========start==========")
					console.log(node);
					console.log(data);
					console.log("==========================")
					return !node.folder;
				},
				dragEnter : function(node, data) {
					console.log("==========enter==========")
					console.log(node);
					console.log(data);
					console.log("==========================")
					/** data.otherNode may be null for non-fancytree droppables.
					 *  Return false to disallow dropping on node. In this case
					 *  dragOver and dragLeave are not called.
					 *  Return 'over', 'before, or 'after' to force a hitMode.
					 *  Return ['before', 'after'] to restrict available hitModes.
					 *  Any other return value will calc the hitMode from the cursor position.
					 */
					// Prevent dropping a parent below another parent (only sort
					// nodes under the same parent)
					/*           if(node.parent !== data.otherNode.parent){
					            return false;
					          }
					          // Don't allow dropping *over* a node (would create a child)
					          return ["before", "after"];
					 */
					return node.folder && node!==data.otherNode.parent?["over"]:false;
				},
				dragDrop : function(node, data) {
					console.log("==========drop==========")
					console.log(node);
					console.log(data);
					console.log("==========================")
					/** This function MUST be defined to enable dropping of items on
					 *  the tree.
					 */
					let param = {
						srcNode : data.otherNode
						, destNode : node
						, command : data.originalEvent.ctrlKey?"COPY":"MOVE"
					} 
					commandProcess(param);
				}
			},
		});
		
		let tree = $.ui.fancytree.getTree(tree1);

		$("#search").on("change", function() {
			console.log(tree);
			let keyword = $(this).val();
			tree.filterNodes(keyword);
		});
		
		$(window).on("keyup", function(event){
			let node = tree.getActiveNode();
			if(node && node.folder) return false;
			if(event.keyCode==46 && confirm("진짜 지울래?")){
				let param = {
					command:"DELETE"
					, srcNode:node
				}
				commandProcess(param);
			}
// 			console.log(event);
		});
	});
</script>
<h4>탐색기</h4>
<input type="text" id="search" class="form-control"
	placeholder="Filtering" />
<div id="tree1"></div>















