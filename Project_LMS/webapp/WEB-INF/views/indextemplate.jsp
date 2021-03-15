<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to SmartLMS</title>
<tiles:insertAttribute name="preScript" />
<link rel="stylesheet" type="text/css" href="${cPath}/selectReosource/styles/style.css">
<link rel="stylesheet" type="text/css" href="${cPath}/selectReosource/styles/custom-responsive-style.css">
<style type="text/css">
	.each-icon{
		height: 60vh;
	}
	.box-1, .box-5{
		background-color:#CBDCED;
	}
	.box-2, .box-4{
		background-color:#FFE08C;
	}
	.box-3, .box-6{
		background-color:#9FBFDF;
	}
	h1, h5, h2{
		margin-bottom:0;
	}
	.icon-wrap>h1{
		font-family: "Arial Black", sans-serif;
        font-size: 36px;
        font-weight: bold;
        color: #ffffff;
        text-shadow: 2px 2px 2px gray;
	}
	.icon-wrap>h2{
		font-family: "Arial Black", sans-serif;
        font-size: 30px;
        font-weight: bold;
        color: #ffffff;
        text-shadow: 2px 2px 2px gray;
	}
	.container-fluid{
		min-height: calc(100vh - 181px) !important;
	}
	.fa-arrow-right{
		font-size:36px;
	}
	.Abox-1{
		background-color:#809bce;
	}
	.Abox-2{
		background-color:#95b8d1;
	}
	.Abox-3{
		background-color:#b8e0d2;
	}
	.Abox-4{
		background-color:#d6eadf;
	}
	.Abox-5{
		background-color:#eac4d5;
	}
	.Abox-6{
		background-color:#cc7d8b;
	}
</style>
</head>
<body>
	<div class="preloader">
        <div class="lds-ripple">
       	     <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
			data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
	    <tiles:insertAttribute name="topMenu"/>
	    <div class="page-wrapper ml-0">
	    	<div class="container-fluid p-0">
	    		<section id="Gain">
					<div id="bodyAria" class="row justify-content-center">
						<tiles:insertAttribute name="contents" />
					</div>
				</section>
			</div>
				<tiles:insertAttribute name="footer"/>
		</div>
	</div>
	
</body>
</html>