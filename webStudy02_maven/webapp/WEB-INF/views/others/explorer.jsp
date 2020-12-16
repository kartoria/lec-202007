<%@page import="kr.or.ddit.explorer.FileWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/fancytree/skin-win8/ui.fancytree.min.css">
<script src="<%=request.getContextPath() %>/js/fancytree/jquery.fancytree-all.min.js"></script>
<script>
	$(function(){
		const NODEURL = "<%=request.getContextPath()%>/server/explorer.do";
		const COMMANDURL = "<%=request.getContextPath()%>/server/fileCommand.do";
		function commandProcess(param){
			if(param.destNode != null){
				$.ajax({
					url : COMMANDURL,
					data : {
						command : param.command,
						srcFile : param.srcNode.getKeyPath(),
						destFolder : param.destNode.getKeyPath()
					},
					method: "post",
					dataType : "text",
					success:function(resp){
						if("SUCCESS" != resp) return;
						switch(param.command){
						case "COPY":
							param.srcNode.copyTo(param.destNode, "child");
							break;
						case "MOVE":
							param.srcNode.moveTo(param.destNode, "child");
							break;
						case "DELETE":
							param.srcNode.remove();
						}
					}
				});
			}else{
				$.ajax({
					url : COMMANDURL,
					data : {
						command : param.command,
						srcFile : param.srcNode.getKeyPath(),
					},
					method: "post",
					dataType : "text",
					success:function(resp){
						if("SUCCESS" != resp) return;
						switch(param.command){
						case "COPY":
							param.srcNode.copyTo(param.destNode, "child");
							break;
						case "MOVE":
							param.srcNode.moveTo(param.destNode, "child");
							break;
						case "DELETE":
							param.srcNode.remove();
						}
					}
				});
			}
		}
		$("#tree").fancytree({
			extensions : [ "filter", "dnd"],
			 dnd: {
				autoExpandMS: 400,
		        focusOnClick: true,
		        preventVoidMoves: true, 
		        preventRecursiveMoves: true,
		        dragStart: function(node, data) {
		        	console.log(node);
		        	console.log(data);
		          	return !node.folder;
		        },
		        dragEnter: function(node, data) {
		            return node.folder && node !== data.otherNode.parent ? ["over"] : false;
		        },
		        dragDrop: function(node, data) {
		        	let param = {
		        		srcNode    : data.otherNode
		        		, destNode : node
		        		, command  : data.originalEvent.ctrlKey? "COPY" : "MOVE"
		        	}
		        	commandProcess(param);
		        }
			},
			filter : {
				autoApply : true,
				autoExpand : false,
				counter : true,
				fuzzy : false,
				hideExpandedCounter : true,
				hideExpanders : false,
				highlight : true,
				leavesOnly : false,
				nodata : true,
				mode : "dimm"
			},
			source : {
				url : NODEURL,
				cache : false
			},
			lazyLoad : function(event, data) {
				var node = data.node;
				data.result = {
					url : NODEURL,
					data : { base : node.getKeyPath() },
					cache : false
				};
			}
		});

		$("#search").on("change", function() {
			let tree = $.ui.fancytree.getTree("#tree");
			console.log(tree);
			let keyword = $(this).val();
			tree.filterNodes(keyword);
		});
		$("#removeBtn").on("click", function(){
			let node = $.ui.fancytree.getTree("#tree").getActiveNode();
			let param = {
	        		srcNode    : node
	        		, destNode : null
	        		, command  : "DELETE"
	        		
	        	}
	        commandProcess(param);
		});
	});
	
</script>
<h4>탐색기</h4>
<input type="text" id="search" class="form-control"/>
<div id="tree">
</div>
<button id="removeBtn" type="button">삭제</button>
