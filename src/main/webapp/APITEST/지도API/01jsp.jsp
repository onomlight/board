<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Kakao 지도 시작하기</title>
</head>

<!-- 현재위치 위도 경도 찾기 추가하기 -->
<script>
	var latitude;
	var longitude;
	navigator.geolocation.getCurrentPosition(function(pos){
		latitude = pos.coords.latitude;
		longitude = pos.coords.longitude;
		//alert("현위 위도 경도 : " + latitude+","+longitude);
		
	})
	
</script>
<body>
	<div id="map" style="width:500px;height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e9556ec8a3c0c4bef732cf3f162d7c0b"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(latitude,longitude),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>
</body>
</html>