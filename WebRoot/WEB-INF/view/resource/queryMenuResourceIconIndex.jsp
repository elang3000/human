<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统" />
<link rel="stylesheet" href="static/style/global.css" media="all">
<style type="text/css">
.layui-form-radio {
	margin: 10px 10px 0 26px;
}
</style>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="searchForm">
					<smart:textInput type="hidden" name="id" value="${id}"></smart:textInput>
					<smart:gridRow>
						<ul class="site-doc-icon">
							<li><i class="fa fa-address-book" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-address-book" <c:if test="${icon == 'fa-address-book'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-address-book-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-address-book-o" <c:if test="${icon == 'fa-address-book-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-address-card" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-address-card" <c:if test="${icon == 'fa-address-card'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-address-card-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-address-card-o" <c:if test="${icon == 'fa-address-card-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bandcamp" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bandcamp" <c:if test="${icon == 'fa-bandcamp'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bath" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bath" <c:if test="${icon == 'fa-bath'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bathtub" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bathtub" <c:if test="${icon == 'fa-bathtub'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-drivers-license" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-drivers-license" <c:if test="${icon == 'fa-drivers-license'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-drivers-license-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-drivers-license-o" <c:if test="${icon == 'fa-drivers-license-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-eercast" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-eercast" <c:if test="${icon == 'fa-eercast'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-envelope-open" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-envelope-open" <c:if test="${icon == 'fa-envelope-open'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-envelope-open-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-envelope-open-o" <c:if test="${icon == 'fa-envelope-open-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-etsy" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-etsy" <c:if test="${icon == 'fa-etsy'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-free-code-camp" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-free-code-camp" <c:if test="${icon == 'fa-free-code-camp'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-grav" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-grav" <c:if test="${icon == 'fa-grav'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-handshake-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-handshake-o" <c:if test="${icon == 'fa-handshake-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-id-badge" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-id-badge" <c:if test="${icon == 'fa-id-badge'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-id-card" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-id-card" <c:if test="${icon == 'fa-id-card'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-id-card-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-id-card-o" <c:if test="${icon == 'fa-id-card-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-imdb" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-imdb" <c:if test="${icon == 'fa-imdb'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-linode" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-linode" <c:if test="${icon == 'fa-linode'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-meetup" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-meetup" <c:if test="${icon == 'fa-meetup'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-microchip" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-microchip" <c:if test="${icon == 'fa-microchip'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-podcast" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-podcast" <c:if test="${icon == 'fa-podcast'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-quora" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-quora" <c:if test="${icon == 'fa-quora'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ravelry" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ravelry" <c:if test="${icon == 'fa-ravelry'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-s15" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-s15" <c:if test="${icon == 'fa-s15'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-shower" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-shower" <c:if test="${icon == 'fa-shower'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-snowflake-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-snowflake-o" <c:if test="${icon == 'fa-snowflake-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-superpowers" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-superpowers" <c:if test="${icon == 'fa-superpowers'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-telegram" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-telegram" <c:if test="${icon == 'fa-telegram'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer" <c:if test="${icon == 'fa-thermometer'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-0" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-0" <c:if test="${icon == 'fa-thermometer-0'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-1" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-1" <c:if test="${icon == 'fa-thermometer-1'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-2" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-2" <c:if test="${icon == 'fa-thermometer-2'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-3" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-3" <c:if test="${icon == 'fa-thermometer-3'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-4" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-4" <c:if test="${icon == 'fa-thermometer-4'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-empty" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-empty" <c:if test="${icon == 'fa-thermometer-empty'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-full" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-full" <c:if test="${icon == 'fa-thermometer-full'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-half" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-half" <c:if test="${icon == 'fa-thermometer-half'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-quarter" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-quarter" <c:if test="${icon == 'fa-thermometer-quarter'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-three-quarters" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-three-quarters" <c:if test="${icon == 'fa-thermometer-three-quarters'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-times-rectangle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-times-rectangle" <c:if test="${icon == 'fa-times-rectangle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-times-rectangle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-times-rectangle-o" <c:if test="${icon == 'fa-times-rectangle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-circle" <c:if test="${icon == 'fa-user-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-circle-o" <c:if test="${icon == 'fa-user-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-o" <c:if test="${icon == 'fa-user-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-vcard" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-vcard" <c:if test="${icon == 'fa-vcard'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-vcard-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-vcard-o" <c:if test="${icon == 'fa-vcard-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-close" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-close" <c:if test="${icon == 'fa-window-close'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-close-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-close-o" <c:if test="${icon == 'fa-window-close-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-maximize" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-maximize" <c:if test="${icon == 'fa-window-maximize'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-minimize" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-minimize" <c:if test="${icon == 'fa-window-minimize'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-restore" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-restore" <c:if test="${icon == 'fa-window-restore'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wpexplorer" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wpexplorer" <c:if test="${icon == 'fa-wpexplorer'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-address-book" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-address-book" <c:if test="${icon == 'fa-address-book'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-address-book-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-address-book-o" <c:if test="${icon == 'fa-address-book-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-address-card" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-address-card" <c:if test="${icon == 'fa-address-card'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-address-card-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-address-card-o" <c:if test="${icon == 'fa-address-card-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-adjust" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-adjust" <c:if test="${icon == 'fa-adjust'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-american-sign-language-interpreting" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-american-sign-language-interpreting" <c:if test="${icon == 'fa-american-sign-language-interpreting'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-anchor" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-anchor" <c:if test="${icon == 'fa-anchor'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-archive" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-archive" <c:if test="${icon == 'fa-archive'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-area-chart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-area-chart" <c:if test="${icon == 'fa-area-chart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrows" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrows" <c:if test="${icon == 'fa-arrows'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrows-h" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrows-h" <c:if test="${icon == 'fa-arrows-h'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrows-v" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrows-v" <c:if test="${icon == 'fa-arrows-v'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-asl-interpreting" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-asl-interpreting" <c:if test="${icon == 'fa-asl-interpreting'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-assistive-listening-systems" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-assistive-listening-systems" <c:if test="${icon == 'fa-assistive-listening-systems'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-asterisk" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-asterisk" <c:if test="${icon == 'fa-asterisk'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-at" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-at" <c:if test="${icon == 'fa-at'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-audio-description" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-audio-description" <c:if test="${icon == 'fa-audio-description'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-automobile" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-automobile" <c:if test="${icon == 'fa-automobile'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-balance-scale" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-balance-scale" <c:if test="${icon == 'fa-balance-scale'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ban" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ban" <c:if test="${icon == 'fa-ban'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bank" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bank" <c:if test="${icon == 'fa-bank'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bar-chart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bar-chart" <c:if test="${icon == 'fa-bar-chart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bar-chart-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bar-chart-o" <c:if test="${icon == 'fa-bar-chart-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-barcode" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-barcode" <c:if test="${icon == 'fa-barcode'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bars" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bars" <c:if test="${icon == 'fa-bars'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bath" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bath" <c:if test="${icon == 'fa-bath'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bathtub" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bathtub" <c:if test="${icon == 'fa-bathtub'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery" <c:if test="${icon == 'fa-battery'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-0" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-0" <c:if test="${icon == 'fa-battery-0'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-1" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-1" <c:if test="${icon == 'fa-battery-1'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-2" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-2" <c:if test="${icon == 'fa-battery-2'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-3" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-3" <c:if test="${icon == 'fa-battery-3'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-4" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-4" <c:if test="${icon == 'fa-battery-4'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-empty" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-empty" <c:if test="${icon == 'fa-battery-empty'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-full" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-full" <c:if test="${icon == 'fa-battery-full'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-half" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-half" <c:if test="${icon == 'fa-battery-half'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-quarter" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-quarter" <c:if test="${icon == 'fa-battery-quarter'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-battery-three-quarters" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-battery-three-quarters" <c:if test="${icon == 'fa-battery-three-quarters'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bed" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bed" <c:if test="${icon == 'fa-bed'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-beer" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-beer" <c:if test="${icon == 'fa-beer'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bell" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bell" <c:if test="${icon == 'fa-bell'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bell-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bell-o" <c:if test="${icon == 'fa-bell-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bell-slash" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bell-slash" <c:if test="${icon == 'fa-bell-slash'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bell-slash-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bell-slash-o" <c:if test="${icon == 'fa-bell-slash-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bicycle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bicycle" <c:if test="${icon == 'fa-bicycle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-binoculars" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-binoculars" <c:if test="${icon == 'fa-binoculars'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-birthday-cake" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-birthday-cake" <c:if test="${icon == 'fa-birthday-cake'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-blind" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-blind" <c:if test="${icon == 'fa-blind'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bluetooth" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bluetooth" <c:if test="${icon == 'fa-bluetooth'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bluetooth-b" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bluetooth-b" <c:if test="${icon == 'fa-bluetooth-b'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bolt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bolt" <c:if test="${icon == 'fa-bolt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bomb" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bomb" <c:if test="${icon == 'fa-bomb'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-book" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-book" <c:if test="${icon == 'fa-book'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bookmark" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bookmark" <c:if test="${icon == 'fa-bookmark'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bookmark-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bookmark-o" <c:if test="${icon == 'fa-bookmark-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-braille" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-braille" <c:if test="${icon == 'fa-braille'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-briefcase" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-briefcase" <c:if test="${icon == 'fa-briefcase'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bug" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bug" <c:if test="${icon == 'fa-bug'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-building" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-building" <c:if test="${icon == 'fa-building'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-building-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-building-o" <c:if test="${icon == 'fa-building-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bullhorn" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bullhorn" <c:if test="${icon == 'fa-bullhorn'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bullseye" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bullseye" <c:if test="${icon == 'fa-bullseye'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bus" <c:if test="${icon == 'fa-bus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cab" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cab" <c:if test="${icon == 'fa-cab'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-calculator" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-calculator" <c:if test="${icon == 'fa-calculator'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-calendar" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-calendar" <c:if test="${icon == 'fa-calendar'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-calendar-check-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-calendar-check-o" <c:if test="${icon == 'fa-calendar-check-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-calendar-minus-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-calendar-minus-o" <c:if test="${icon == 'fa-calendar-minus-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-calendar-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-calendar-o" <c:if test="${icon == 'fa-calendar-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-calendar-plus-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-calendar-plus-o" <c:if test="${icon == 'fa-calendar-plus-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-calendar-times-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-calendar-times-o" <c:if test="${icon == 'fa-calendar-times-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-camera" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-camera" <c:if test="${icon == 'fa-camera'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-camera-retro" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-camera-retro" <c:if test="${icon == 'fa-camera-retro'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-car" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-car" <c:if test="${icon == 'fa-car'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-square-o-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-square-o-down" <c:if test="${icon == 'fa-caret-square-o-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-square-o-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-square-o-left" <c:if test="${icon == 'fa-caret-square-o-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-square-o-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-square-o-right" <c:if test="${icon == 'fa-caret-square-o-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-square-o-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-square-o-up" <c:if test="${icon == 'fa-caret-square-o-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cart-arrow-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cart-arrow-down" <c:if test="${icon == 'fa-cart-arrow-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cart-plus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cart-plus" <c:if test="${icon == 'fa-cart-plus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc" <c:if test="${icon == 'fa-cc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-certificate" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-certificate" <c:if test="${icon == 'fa-certificate'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-check" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-check" <c:if test="${icon == 'fa-check'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-check-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-check-circle" <c:if test="${icon == 'fa-check-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-check-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-check-circle-o" <c:if test="${icon == 'fa-check-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-check-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-check-square" <c:if test="${icon == 'fa-check-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-check-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-check-square-o" <c:if test="${icon == 'fa-check-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-child" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-child" <c:if test="${icon == 'fa-child'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-circle" <c:if test="${icon == 'fa-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-circle-o" <c:if test="${icon == 'fa-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-circle-o-notch" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-circle-o-notch" <c:if test="${icon == 'fa-circle-o-notch'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-circle-thin" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-circle-thin" <c:if test="${icon == 'fa-circle-thin'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-clock-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-clock-o" <c:if test="${icon == 'fa-clock-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-clone" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-clone" <c:if test="${icon == 'fa-clone'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-close" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-close" <c:if test="${icon == 'fa-close'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cloud" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cloud" <c:if test="${icon == 'fa-cloud'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cloud-download" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cloud-download" <c:if test="${icon == 'fa-cloud-download'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cloud-upload" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cloud-upload" <c:if test="${icon == 'fa-cloud-upload'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-code" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-code" <c:if test="${icon == 'fa-code'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-code-fork" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-code-fork" <c:if test="${icon == 'fa-code-fork'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-coffee" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-coffee" <c:if test="${icon == 'fa-coffee'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cog" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cog" <c:if test="${icon == 'fa-cog'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cogs" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cogs" <c:if test="${icon == 'fa-cogs'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-comment" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-comment" <c:if test="${icon == 'fa-comment'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-comment-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-comment-o" <c:if test="${icon == 'fa-comment-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-commenting" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-commenting" <c:if test="${icon == 'fa-commenting'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-commenting-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-commenting-o" <c:if test="${icon == 'fa-commenting-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-comments" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-comments" <c:if test="${icon == 'fa-comments'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-comments-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-comments-o" <c:if test="${icon == 'fa-comments-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-compass" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-compass" <c:if test="${icon == 'fa-compass'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-copyright" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-copyright" <c:if test="${icon == 'fa-copyright'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-creative-commons" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-creative-commons" <c:if test="${icon == 'fa-creative-commons'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-credit-card" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-credit-card" <c:if test="${icon == 'fa-credit-card'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-credit-card-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-credit-card-alt" <c:if test="${icon == 'fa-credit-card-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-crop" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-crop" <c:if test="${icon == 'fa-crop'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-crosshairs" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-crosshairs" <c:if test="${icon == 'fa-crosshairs'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cube" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cube" <c:if test="${icon == 'fa-cube'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cubes" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cubes" <c:if test="${icon == 'fa-cubes'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cutlery" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cutlery" <c:if test="${icon == 'fa-cutlery'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-dashboard" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-dashboard" <c:if test="${icon == 'fa-dashboard'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-database" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-database" <c:if test="${icon == 'fa-database'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-deaf" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-deaf" <c:if test="${icon == 'fa-deaf'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-deafness" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-deafness" <c:if test="${icon == 'fa-deafness'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-desktop" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-desktop" <c:if test="${icon == 'fa-desktop'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-diamond" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-diamond" <c:if test="${icon == 'fa-diamond'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-dot-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-dot-circle-o" <c:if test="${icon == 'fa-dot-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-download" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-download" <c:if test="${icon == 'fa-download'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-drivers-license" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-drivers-license" <c:if test="${icon == 'fa-drivers-license'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-drivers-license-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-drivers-license-o" <c:if test="${icon == 'fa-drivers-license-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-edit" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-edit" <c:if test="${icon == 'fa-edit'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ellipsis-h" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ellipsis-h" <c:if test="${icon == 'fa-ellipsis-h'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ellipsis-v" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ellipsis-v" <c:if test="${icon == 'fa-ellipsis-v'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-envelope" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-envelope" <c:if test="${icon == 'fa-envelope'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-envelope-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-envelope-o" <c:if test="${icon == 'fa-envelope-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-envelope-open" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-envelope-open" <c:if test="${icon == 'fa-envelope-open'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-envelope-open-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-envelope-open-o" <c:if test="${icon == 'fa-envelope-open-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-envelope-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-envelope-square" <c:if test="${icon == 'fa-envelope-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-eraser" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-eraser" <c:if test="${icon == 'fa-eraser'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-exchange" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-exchange" <c:if test="${icon == 'fa-exchange'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-exclamation" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-exclamation" <c:if test="${icon == 'fa-exclamation'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-exclamation-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-exclamation-circle" <c:if test="${icon == 'fa-exclamation-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-exclamation-triangle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-exclamation-triangle" <c:if test="${icon == 'fa-exclamation-triangle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-external-link" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-external-link" <c:if test="${icon == 'fa-external-link'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-external-link-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-external-link-square" <c:if test="${icon == 'fa-external-link-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-eye" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-eye" <c:if test="${icon == 'fa-eye'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-eye-slash" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-eye-slash" <c:if test="${icon == 'fa-eye-slash'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-eyedropper" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-eyedropper" <c:if test="${icon == 'fa-eyedropper'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fax" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fax" <c:if test="${icon == 'fa-fax'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-feed" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-feed" <c:if test="${icon == 'fa-feed'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-female" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-female" <c:if test="${icon == 'fa-female'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fighter-jet" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fighter-jet" <c:if test="${icon == 'fa-fighter-jet'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-archive-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-archive-o" <c:if test="${icon == 'fa-file-archive-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-audio-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-audio-o" <c:if test="${icon == 'fa-file-audio-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-code-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-code-o" <c:if test="${icon == 'fa-file-code-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-excel-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-excel-o" <c:if test="${icon == 'fa-file-excel-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-image-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-image-o" <c:if test="${icon == 'fa-file-image-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-movie-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-movie-o" <c:if test="${icon == 'fa-file-movie-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-pdf-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-pdf-o" <c:if test="${icon == 'fa-file-pdf-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-photo-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-photo-o" <c:if test="${icon == 'fa-file-photo-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-picture-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-picture-o" <c:if test="${icon == 'fa-file-picture-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-powerpoint-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-powerpoint-o" <c:if test="${icon == 'fa-file-powerpoint-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-sound-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-sound-o" <c:if test="${icon == 'fa-file-sound-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-video-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-video-o" <c:if test="${icon == 'fa-file-video-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-word-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-word-o" <c:if test="${icon == 'fa-file-word-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-zip-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-zip-o" <c:if test="${icon == 'fa-file-zip-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-film" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-film" <c:if test="${icon == 'fa-film'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-filter" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-filter" <c:if test="${icon == 'fa-filter'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fire" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fire" <c:if test="${icon == 'fa-fire'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fire-extinguisher" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fire-extinguisher" <c:if test="${icon == 'fa-fire-extinguisher'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-flag" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-flag" <c:if test="${icon == 'fa-flag'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-flag-checkered" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-flag-checkered" <c:if test="${icon == 'fa-flag-checkered'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-flag-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-flag-o" <c:if test="${icon == 'fa-flag-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-flash" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-flash" <c:if test="${icon == 'fa-flash'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-flask" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-flask" <c:if test="${icon == 'fa-flask'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-folder" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-folder" <c:if test="${icon == 'fa-folder'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-folder-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-folder-o" <c:if test="${icon == 'fa-folder-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-folder-open" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-folder-open" <c:if test="${icon == 'fa-folder-open'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-folder-open-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-folder-open-o" <c:if test="${icon == 'fa-folder-open-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-frown-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-frown-o" <c:if test="${icon == 'fa-frown-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-futbol-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-futbol-o" <c:if test="${icon == 'fa-futbol-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gamepad" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gamepad" <c:if test="${icon == 'fa-gamepad'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gavel" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gavel" <c:if test="${icon == 'fa-gavel'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gear" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gear" <c:if test="${icon == 'fa-gear'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gears" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gears" <c:if test="${icon == 'fa-gears'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gift" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gift" <c:if test="${icon == 'fa-gift'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-glass" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-glass" <c:if test="${icon == 'fa-glass'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-globe" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-globe" <c:if test="${icon == 'fa-globe'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-graduation-cap" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-graduation-cap" <c:if test="${icon == 'fa-graduation-cap'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-group" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-group" <c:if test="${icon == 'fa-group'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-grab-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-grab-o" <c:if test="${icon == 'fa-hand-grab-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-lizard-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-lizard-o" <c:if test="${icon == 'fa-hand-lizard-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-paper-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-paper-o" <c:if test="${icon == 'fa-hand-paper-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-peace-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-peace-o" <c:if test="${icon == 'fa-hand-peace-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-pointer-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-pointer-o" <c:if test="${icon == 'fa-hand-pointer-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-rock-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-rock-o" <c:if test="${icon == 'fa-hand-rock-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-scissors-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-scissors-o" <c:if test="${icon == 'fa-hand-scissors-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-spock-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-spock-o" <c:if test="${icon == 'fa-hand-spock-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-stop-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-stop-o" <c:if test="${icon == 'fa-hand-stop-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-handshake-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-handshake-o" <c:if test="${icon == 'fa-handshake-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hard-of-hearing" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hard-of-hearing" <c:if test="${icon == 'fa-hard-of-hearing'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hashtag" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hashtag" <c:if test="${icon == 'fa-hashtag'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hdd-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hdd-o" <c:if test="${icon == 'fa-hdd-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-headphones" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-headphones" <c:if test="${icon == 'fa-headphones'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-heart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-heart" <c:if test="${icon == 'fa-heart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-heart-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-heart-o" <c:if test="${icon == 'fa-heart-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-heartbeat" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-heartbeat" <c:if test="${icon == 'fa-heartbeat'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-history" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-history" <c:if test="${icon == 'fa-history'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-home" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-home" <c:if test="${icon == 'fa-home'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hotel" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hotel" <c:if test="${icon == 'fa-hotel'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hourglass" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hourglass" <c:if test="${icon == 'fa-hourglass'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hourglass-1" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hourglass-1" <c:if test="${icon == 'fa-hourglass-1'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hourglass-2" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hourglass-2" <c:if test="${icon == 'fa-hourglass-2'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hourglass-3" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hourglass-3" <c:if test="${icon == 'fa-hourglass-3'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hourglass-end" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hourglass-end" <c:if test="${icon == 'fa-hourglass-end'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hourglass-half" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hourglass-half" <c:if test="${icon == 'fa-hourglass-half'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hourglass-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hourglass-o" <c:if test="${icon == 'fa-hourglass-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hourglass-start" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hourglass-start" <c:if test="${icon == 'fa-hourglass-start'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-i-cursor" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-i-cursor" <c:if test="${icon == 'fa-i-cursor'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-id-badge" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-id-badge" <c:if test="${icon == 'fa-id-badge'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-id-card" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-id-card" <c:if test="${icon == 'fa-id-card'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-id-card-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-id-card-o" <c:if test="${icon == 'fa-id-card-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-image" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-image" <c:if test="${icon == 'fa-image'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-inbox" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-inbox" <c:if test="${icon == 'fa-inbox'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-industry" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-industry" <c:if test="${icon == 'fa-industry'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-info" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-info" <c:if test="${icon == 'fa-info'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-info-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-info-circle" <c:if test="${icon == 'fa-info-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-institution" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-institution" <c:if test="${icon == 'fa-institution'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-key" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-key" <c:if test="${icon == 'fa-key'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-keyboard-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-keyboard-o" <c:if test="${icon == 'fa-keyboard-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-language" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-language" <c:if test="${icon == 'fa-language'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-laptop" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-laptop" <c:if test="${icon == 'fa-laptop'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-leaf" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-leaf" <c:if test="${icon == 'fa-leaf'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-legal" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-legal" <c:if test="${icon == 'fa-legal'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-lemon-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-lemon-o" <c:if test="${icon == 'fa-lemon-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-level-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-level-down" <c:if test="${icon == 'fa-level-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-level-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-level-up" <c:if test="${icon == 'fa-level-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-life-bouy" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-life-bouy" <c:if test="${icon == 'fa-life-bouy'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-life-buoy" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-life-buoy" <c:if test="${icon == 'fa-life-buoy'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-life-ring" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-life-ring" <c:if test="${icon == 'fa-life-ring'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-life-saver" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-life-saver" <c:if test="${icon == 'fa-life-saver'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-lightbulb-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-lightbulb-o" <c:if test="${icon == 'fa-lightbulb-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-line-chart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-line-chart" <c:if test="${icon == 'fa-line-chart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-location-arrow" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-location-arrow" <c:if test="${icon == 'fa-location-arrow'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-lock" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-lock" <c:if test="${icon == 'fa-lock'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-low-vision" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-low-vision" <c:if test="${icon == 'fa-low-vision'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-magic" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-magic" <c:if test="${icon == 'fa-magic'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-magnet" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-magnet" <c:if test="${icon == 'fa-magnet'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mail-forward" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mail-forward" <c:if test="${icon == 'fa-mail-forward'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mail-reply" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mail-reply" <c:if test="${icon == 'fa-mail-reply'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mail-reply-all" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mail-reply-all" <c:if test="${icon == 'fa-mail-reply-all'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-male" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-male" <c:if test="${icon == 'fa-male'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-map" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-map" <c:if test="${icon == 'fa-map'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-map-marker" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-map-marker" <c:if test="${icon == 'fa-map-marker'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-map-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-map-o" <c:if test="${icon == 'fa-map-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-map-pin" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-map-pin" <c:if test="${icon == 'fa-map-pin'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-map-signs" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-map-signs" <c:if test="${icon == 'fa-map-signs'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-meh-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-meh-o" <c:if test="${icon == 'fa-meh-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-microchip" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-microchip" <c:if test="${icon == 'fa-microchip'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-microphone" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-microphone" <c:if test="${icon == 'fa-microphone'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-microphone-slash" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-microphone-slash" <c:if test="${icon == 'fa-microphone-slash'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-minus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-minus" <c:if test="${icon == 'fa-minus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-minus-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-minus-circle" <c:if test="${icon == 'fa-minus-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-minus-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-minus-square" <c:if test="${icon == 'fa-minus-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-minus-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-minus-square-o" <c:if test="${icon == 'fa-minus-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mobile" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mobile" <c:if test="${icon == 'fa-mobile'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mobile-phone" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mobile-phone" <c:if test="${icon == 'fa-mobile-phone'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-money" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-money" <c:if test="${icon == 'fa-money'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-moon-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-moon-o" <c:if test="${icon == 'fa-moon-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mortar-board" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mortar-board" <c:if test="${icon == 'fa-mortar-board'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-motorcycle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-motorcycle" <c:if test="${icon == 'fa-motorcycle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mouse-pointer" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mouse-pointer" <c:if test="${icon == 'fa-mouse-pointer'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-music" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-music" <c:if test="${icon == 'fa-music'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-navicon" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-navicon" <c:if test="${icon == 'fa-navicon'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-newspaper-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-newspaper-o" <c:if test="${icon == 'fa-newspaper-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-object-group" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-object-group" <c:if test="${icon == 'fa-object-group'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-object-ungroup" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-object-ungroup" <c:if test="${icon == 'fa-object-ungroup'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-paint-brush" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-paint-brush" <c:if test="${icon == 'fa-paint-brush'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-paper-plane" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-paper-plane" <c:if test="${icon == 'fa-paper-plane'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-paper-plane-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-paper-plane-o" <c:if test="${icon == 'fa-paper-plane-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-paw" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-paw" <c:if test="${icon == 'fa-paw'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pencil" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pencil" <c:if test="${icon == 'fa-pencil'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pencil-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pencil-square" <c:if test="${icon == 'fa-pencil-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pencil-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pencil-square-o" <c:if test="${icon == 'fa-pencil-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-percent" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-percent" <c:if test="${icon == 'fa-percent'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-phone" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-phone" <c:if test="${icon == 'fa-phone'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-phone-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-phone-square" <c:if test="${icon == 'fa-phone-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-photo" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-photo" <c:if test="${icon == 'fa-photo'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-picture-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-picture-o" <c:if test="${icon == 'fa-picture-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pie-chart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pie-chart" <c:if test="${icon == 'fa-pie-chart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plane" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plane" <c:if test="${icon == 'fa-plane'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plug" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plug" <c:if test="${icon == 'fa-plug'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plus" <c:if test="${icon == 'fa-plus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plus-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plus-circle" <c:if test="${icon == 'fa-plus-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plus-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plus-square" <c:if test="${icon == 'fa-plus-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plus-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plus-square-o" <c:if test="${icon == 'fa-plus-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-podcast" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-podcast" <c:if test="${icon == 'fa-podcast'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-power-off" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-power-off" <c:if test="${icon == 'fa-power-off'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-print" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-print" <c:if test="${icon == 'fa-print'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-puzzle-piece" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-puzzle-piece" <c:if test="${icon == 'fa-puzzle-piece'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-qrcode" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-qrcode" <c:if test="${icon == 'fa-qrcode'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-question" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-question" <c:if test="${icon == 'fa-question'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-question-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-question-circle" <c:if test="${icon == 'fa-question-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-question-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-question-circle-o" <c:if test="${icon == 'fa-question-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-quote-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-quote-left" <c:if test="${icon == 'fa-quote-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-quote-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-quote-right" <c:if test="${icon == 'fa-quote-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-random" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-random" <c:if test="${icon == 'fa-random'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-recycle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-recycle" <c:if test="${icon == 'fa-recycle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-refresh" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-refresh" <c:if test="${icon == 'fa-refresh'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-registered" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-registered" <c:if test="${icon == 'fa-registered'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-remove" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-remove" <c:if test="${icon == 'fa-remove'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-reorder" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-reorder" <c:if test="${icon == 'fa-reorder'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-reply" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-reply" <c:if test="${icon == 'fa-reply'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-reply-all" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-reply-all" <c:if test="${icon == 'fa-reply-all'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-retweet" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-retweet" <c:if test="${icon == 'fa-retweet'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-road" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-road" <c:if test="${icon == 'fa-road'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rocket" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rocket" <c:if test="${icon == 'fa-rocket'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rss" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rss" <c:if test="${icon == 'fa-rss'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rss-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rss-square" <c:if test="${icon == 'fa-rss-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-s15" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-s15" <c:if test="${icon == 'fa-s15'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-search" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-search" <c:if test="${icon == 'fa-search'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-search-minus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-search-minus" <c:if test="${icon == 'fa-search-minus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-search-plus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-search-plus" <c:if test="${icon == 'fa-search-plus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-send" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-send" <c:if test="${icon == 'fa-send'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-send-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-send-o" <c:if test="${icon == 'fa-send-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-server" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-server" <c:if test="${icon == 'fa-server'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-share" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-share" <c:if test="${icon == 'fa-share'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-share-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-share-alt" <c:if test="${icon == 'fa-share-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-share-alt-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-share-alt-square" <c:if test="${icon == 'fa-share-alt-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-share-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-share-square" <c:if test="${icon == 'fa-share-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-share-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-share-square-o" <c:if test="${icon == 'fa-share-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-shield" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-shield" <c:if test="${icon == 'fa-shield'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ship" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ship" <c:if test="${icon == 'fa-ship'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-shopping-bag" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-shopping-bag" <c:if test="${icon == 'fa-shopping-bag'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-shopping-basket" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-shopping-basket" <c:if test="${icon == 'fa-shopping-basket'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-shopping-cart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-shopping-cart" <c:if test="${icon == 'fa-shopping-cart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-shower" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-shower" <c:if test="${icon == 'fa-shower'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sign-in" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sign-in" <c:if test="${icon == 'fa-sign-in'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sign-language" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sign-language" <c:if test="${icon == 'fa-sign-language'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sign-out" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sign-out" <c:if test="${icon == 'fa-sign-out'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-signal" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-signal" <c:if test="${icon == 'fa-signal'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-signing" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-signing" <c:if test="${icon == 'fa-signing'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sitemap" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sitemap" <c:if test="${icon == 'fa-sitemap'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sliders" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sliders" <c:if test="${icon == 'fa-sliders'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-smile-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-smile-o" <c:if test="${icon == 'fa-smile-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-snowflake-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-snowflake-o" <c:if test="${icon == 'fa-snowflake-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-soccer-ball-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-soccer-ball-o" <c:if test="${icon == 'fa-soccer-ball-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort" <c:if test="${icon == 'fa-sort'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-alpha-asc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-alpha-asc" <c:if test="${icon == 'fa-sort-alpha-asc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-alpha-desc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-alpha-desc" <c:if test="${icon == 'fa-sort-alpha-desc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-amount-asc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-amount-asc" <c:if test="${icon == 'fa-sort-amount-asc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-amount-desc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-amount-desc" <c:if test="${icon == 'fa-sort-amount-desc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-asc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-asc" <c:if test="${icon == 'fa-sort-asc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-desc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-desc" <c:if test="${icon == 'fa-sort-desc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-down" <c:if test="${icon == 'fa-sort-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-numeric-asc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-numeric-asc" <c:if test="${icon == 'fa-sort-numeric-asc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-numeric-desc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-numeric-desc" <c:if test="${icon == 'fa-sort-numeric-desc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sort-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sort-up" <c:if test="${icon == 'fa-sort-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-space-shuttle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-space-shuttle" <c:if test="${icon == 'fa-space-shuttle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-spinner" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-spinner" <c:if test="${icon == 'fa-spinner'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-spoon" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-spoon" <c:if test="${icon == 'fa-spoon'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-square" <c:if test="${icon == 'fa-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-square-o" <c:if test="${icon == 'fa-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-star" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-star" <c:if test="${icon == 'fa-star'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-star-half" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-star-half" <c:if test="${icon == 'fa-star-half'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-star-half-empty" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-star-half-empty" <c:if test="${icon == 'fa-star-half-empty'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-star-half-full" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-star-half-full" <c:if test="${icon == 'fa-star-half-full'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-star-half-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-star-half-o" <c:if test="${icon == 'fa-star-half-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-star-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-star-o" <c:if test="${icon == 'fa-star-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sticky-note" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sticky-note" <c:if test="${icon == 'fa-sticky-note'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sticky-note-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sticky-note-o" <c:if test="${icon == 'fa-sticky-note-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-street-view" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-street-view" <c:if test="${icon == 'fa-street-view'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-suitcase" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-suitcase" <c:if test="${icon == 'fa-suitcase'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sun-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sun-o" <c:if test="${icon == 'fa-sun-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-support" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-support" <c:if test="${icon == 'fa-support'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tablet" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tablet" <c:if test="${icon == 'fa-tablet'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tachometer" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tachometer" <c:if test="${icon == 'fa-tachometer'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tag" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tag" <c:if test="${icon == 'fa-tag'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tags" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tags" <c:if test="${icon == 'fa-tags'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tasks" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tasks" <c:if test="${icon == 'fa-tasks'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-taxi" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-taxi" <c:if test="${icon == 'fa-taxi'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-television" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-television" <c:if test="${icon == 'fa-television'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-terminal" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-terminal" <c:if test="${icon == 'fa-terminal'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer" <c:if test="${icon == 'fa-thermometer'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-0" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-0" <c:if test="${icon == 'fa-thermometer-0'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-1" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-1" <c:if test="${icon == 'fa-thermometer-1'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-2" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-2" <c:if test="${icon == 'fa-thermometer-2'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-3" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-3" <c:if test="${icon == 'fa-thermometer-3'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-4" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-4" <c:if test="${icon == 'fa-thermometer-4'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-empty" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-empty" <c:if test="${icon == 'fa-thermometer-empty'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-full" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-full" <c:if test="${icon == 'fa-thermometer-full'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-half" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-half" <c:if test="${icon == 'fa-thermometer-half'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-quarter" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-quarter" <c:if test="${icon == 'fa-thermometer-quarter'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thermometer-three-quarters" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thermometer-three-quarters" <c:if test="${icon == 'fa-thermometer-three-quarters'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thumb-tack" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thumb-tack" <c:if test="${icon == 'fa-thumb-tack'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thumbs-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thumbs-down" <c:if test="${icon == 'fa-thumbs-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thumbs-o-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thumbs-o-down" <c:if test="${icon == 'fa-thumbs-o-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thumbs-o-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thumbs-o-up" <c:if test="${icon == 'fa-thumbs-o-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thumbs-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thumbs-up" <c:if test="${icon == 'fa-thumbs-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ticket" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ticket" <c:if test="${icon == 'fa-ticket'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-times" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-times" <c:if test="${icon == 'fa-times'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-times-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-times-circle" <c:if test="${icon == 'fa-times-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-times-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-times-circle-o" <c:if test="${icon == 'fa-times-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-times-rectangle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-times-rectangle" <c:if test="${icon == 'fa-times-rectangle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-times-rectangle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-times-rectangle-o" <c:if test="${icon == 'fa-times-rectangle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tint" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tint" <c:if test="${icon == 'fa-tint'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-down" <c:if test="${icon == 'fa-toggle-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-left" <c:if test="${icon == 'fa-toggle-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-off" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-off" <c:if test="${icon == 'fa-toggle-off'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-on" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-on" <c:if test="${icon == 'fa-toggle-on'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-right" <c:if test="${icon == 'fa-toggle-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-up" <c:if test="${icon == 'fa-toggle-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-trademark" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-trademark" <c:if test="${icon == 'fa-trademark'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-trash" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-trash" <c:if test="${icon == 'fa-trash'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-trash-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-trash-o" <c:if test="${icon == 'fa-trash-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tree" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tree" <c:if test="${icon == 'fa-tree'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-trophy" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-trophy" <c:if test="${icon == 'fa-trophy'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-truck" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-truck" <c:if test="${icon == 'fa-truck'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tty" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tty" <c:if test="${icon == 'fa-tty'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tv" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tv" <c:if test="${icon == 'fa-tv'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-umbrella" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-umbrella" <c:if test="${icon == 'fa-umbrella'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-universal-access" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-universal-access" <c:if test="${icon == 'fa-universal-access'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-university" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-university" <c:if test="${icon == 'fa-university'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-unlock" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-unlock" <c:if test="${icon == 'fa-unlock'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-unlock-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-unlock-alt" <c:if test="${icon == 'fa-unlock-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-unsorted" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-unsorted" <c:if test="${icon == 'fa-unsorted'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-upload" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-upload" <c:if test="${icon == 'fa-upload'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user" <c:if test="${icon == 'fa-user'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-circle" <c:if test="${icon == 'fa-user-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-circle-o" <c:if test="${icon == 'fa-user-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-o" <c:if test="${icon == 'fa-user-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-plus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-plus" <c:if test="${icon == 'fa-user-plus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-secret" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-secret" <c:if test="${icon == 'fa-user-secret'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-times" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-times" <c:if test="${icon == 'fa-user-times'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-users" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-users" <c:if test="${icon == 'fa-users'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-vcard" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-vcard" <c:if test="${icon == 'fa-vcard'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-vcard-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-vcard-o" <c:if test="${icon == 'fa-vcard-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-video-camera" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-video-camera" <c:if test="${icon == 'fa-video-camera'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-volume-control-phone" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-volume-control-phone" <c:if test="${icon == 'fa-volume-control-phone'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-volume-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-volume-down" <c:if test="${icon == 'fa-volume-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-volume-off" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-volume-off" <c:if test="${icon == 'fa-volume-off'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-volume-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-volume-up" <c:if test="${icon == 'fa-volume-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-warning" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-warning" <c:if test="${icon == 'fa-warning'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wheelchair" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wheelchair" <c:if test="${icon == 'fa-wheelchair'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wheelchair-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wheelchair-alt" <c:if test="${icon == 'fa-wheelchair-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wifi" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wifi" <c:if test="${icon == 'fa-wifi'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-close" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-close" <c:if test="${icon == 'fa-window-close'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-close-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-close-o" <c:if test="${icon == 'fa-window-close-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-maximize" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-maximize" <c:if test="${icon == 'fa-window-maximize'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-minimize" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-minimize" <c:if test="${icon == 'fa-window-minimize'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-window-restore" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-window-restore" <c:if test="${icon == 'fa-window-restore'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wrench" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wrench" <c:if test="${icon == 'fa-wrench'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-american-sign-language-interpreting" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-american-sign-language-interpreting" <c:if test="${icon == 'fa-american-sign-language-interpreting'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-asl-interpreting" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-asl-interpreting" <c:if test="${icon == 'fa-asl-interpreting'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-assistive-listening-systems" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-assistive-listening-systems" <c:if test="${icon == 'fa-assistive-listening-systems'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-audio-description" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-audio-description" <c:if test="${icon == 'fa-audio-description'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-blind" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-blind" <c:if test="${icon == 'fa-blind'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-braille" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-braille" <c:if test="${icon == 'fa-braille'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc" <c:if test="${icon == 'fa-cc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-deaf" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-deaf" <c:if test="${icon == 'fa-deaf'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-deafness" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-deafness" <c:if test="${icon == 'fa-deafness'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hard-of-hearing" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hard-of-hearing" <c:if test="${icon == 'fa-hard-of-hearing'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-low-vision" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-low-vision" <c:if test="${icon == 'fa-low-vision'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-question-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-question-circle-o" <c:if test="${icon == 'fa-question-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sign-language" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sign-language" <c:if test="${icon == 'fa-sign-language'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-signing" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-signing" <c:if test="${icon == 'fa-signing'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tty" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tty" <c:if test="${icon == 'fa-tty'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-universal-access" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-universal-access" <c:if test="${icon == 'fa-universal-access'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-volume-control-phone" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-volume-control-phone" <c:if test="${icon == 'fa-volume-control-phone'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wheelchair" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wheelchair" <c:if test="${icon == 'fa-wheelchair'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wheelchair-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wheelchair-alt" <c:if test="${icon == 'fa-wheelchair-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-grab-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-grab-o" <c:if test="${icon == 'fa-hand-grab-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-lizard-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-lizard-o" <c:if test="${icon == 'fa-hand-lizard-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-o-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-o-down" <c:if test="${icon == 'fa-hand-o-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-o-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-o-left" <c:if test="${icon == 'fa-hand-o-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-o-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-o-right" <c:if test="${icon == 'fa-hand-o-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-o-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-o-up" <c:if test="${icon == 'fa-hand-o-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-paper-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-paper-o" <c:if test="${icon == 'fa-hand-paper-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-peace-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-peace-o" <c:if test="${icon == 'fa-hand-peace-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-pointer-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-pointer-o" <c:if test="${icon == 'fa-hand-pointer-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-rock-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-rock-o" <c:if test="${icon == 'fa-hand-rock-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-scissors-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-scissors-o" <c:if test="${icon == 'fa-hand-scissors-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-spock-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-spock-o" <c:if test="${icon == 'fa-hand-spock-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-stop-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-stop-o" <c:if test="${icon == 'fa-hand-stop-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thumbs-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thumbs-down" <c:if test="${icon == 'fa-thumbs-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thumbs-o-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thumbs-o-down" <c:if test="${icon == 'fa-thumbs-o-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thumbs-o-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thumbs-o-up" <c:if test="${icon == 'fa-thumbs-o-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-thumbs-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-thumbs-up" <c:if test="${icon == 'fa-thumbs-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ambulance" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ambulance" <c:if test="${icon == 'fa-ambulance'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-automobile" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-automobile" <c:if test="${icon == 'fa-automobile'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bicycle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bicycle" <c:if test="${icon == 'fa-bicycle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bus" <c:if test="${icon == 'fa-bus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cab" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cab" <c:if test="${icon == 'fa-cab'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-car" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-car" <c:if test="${icon == 'fa-car'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fighter-jet" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fighter-jet" <c:if test="${icon == 'fa-fighter-jet'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-motorcycle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-motorcycle" <c:if test="${icon == 'fa-motorcycle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plane" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plane" <c:if test="${icon == 'fa-plane'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rocket" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rocket" <c:if test="${icon == 'fa-rocket'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ship" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ship" <c:if test="${icon == 'fa-ship'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-space-shuttle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-space-shuttle" <c:if test="${icon == 'fa-space-shuttle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-subway" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-subway" <c:if test="${icon == 'fa-subway'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-taxi" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-taxi" <c:if test="${icon == 'fa-taxi'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-train" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-train" <c:if test="${icon == 'fa-train'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-truck" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-truck" <c:if test="${icon == 'fa-truck'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wheelchair" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wheelchair" <c:if test="${icon == 'fa-wheelchair'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wheelchair-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wheelchair-alt" <c:if test="${icon == 'fa-wheelchair-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-genderless" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-genderless" <c:if test="${icon == 'fa-genderless'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-intersex" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-intersex" <c:if test="${icon == 'fa-intersex'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mars" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mars" <c:if test="${icon == 'fa-mars'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mars-double" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mars-double" <c:if test="${icon == 'fa-mars-double'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mars-stroke" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mars-stroke" <c:if test="${icon == 'fa-mars-stroke'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mars-stroke-h" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mars-stroke-h" <c:if test="${icon == 'fa-mars-stroke-h'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mars-stroke-v" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mars-stroke-v" <c:if test="${icon == 'fa-mars-stroke-v'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mercury" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mercury" <c:if test="${icon == 'fa-mercury'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-neuter" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-neuter" <c:if test="${icon == 'fa-neuter'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-transgender" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-transgender" <c:if test="${icon == 'fa-transgender'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-transgender-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-transgender-alt" <c:if test="${icon == 'fa-transgender-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-venus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-venus" <c:if test="${icon == 'fa-venus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-venus-double" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-venus-double" <c:if test="${icon == 'fa-venus-double'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-venus-mars" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-venus-mars" <c:if test="${icon == 'fa-venus-mars'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file" <c:if test="${icon == 'fa-file'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-archive-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-archive-o" <c:if test="${icon == 'fa-file-archive-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-audio-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-audio-o" <c:if test="${icon == 'fa-file-audio-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-code-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-code-o" <c:if test="${icon == 'fa-file-code-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-excel-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-excel-o" <c:if test="${icon == 'fa-file-excel-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-image-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-image-o" <c:if test="${icon == 'fa-file-image-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-movie-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-movie-o" <c:if test="${icon == 'fa-file-movie-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-o" <c:if test="${icon == 'fa-file-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-pdf-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-pdf-o" <c:if test="${icon == 'fa-file-pdf-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-photo-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-photo-o" <c:if test="${icon == 'fa-file-photo-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-picture-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-picture-o" <c:if test="${icon == 'fa-file-picture-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-powerpoint-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-powerpoint-o" <c:if test="${icon == 'fa-file-powerpoint-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-sound-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-sound-o" <c:if test="${icon == 'fa-file-sound-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-text" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-text" <c:if test="${icon == 'fa-file-text'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-text-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-text-o" <c:if test="${icon == 'fa-file-text-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-video-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-video-o" <c:if test="${icon == 'fa-file-video-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-word-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-word-o" <c:if test="${icon == 'fa-file-word-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-zip-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-zip-o" <c:if test="${icon == 'fa-file-zip-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-circle-o-notch" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-circle-o-notch" <c:if test="${icon == 'fa-circle-o-notch'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cog" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cog" <c:if test="${icon == 'fa-cog'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gear" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gear" <c:if test="${icon == 'fa-gear'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-refresh" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-refresh" <c:if test="${icon == 'fa-refresh'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-spinner" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-spinner" <c:if test="${icon == 'fa-spinner'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-check-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-check-square" <c:if test="${icon == 'fa-check-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-check-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-check-square-o" <c:if test="${icon == 'fa-check-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-circle" <c:if test="${icon == 'fa-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-circle-o" <c:if test="${icon == 'fa-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-dot-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-dot-circle-o" <c:if test="${icon == 'fa-dot-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-minus-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-minus-square" <c:if test="${icon == 'fa-minus-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-minus-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-minus-square-o" <c:if test="${icon == 'fa-minus-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plus-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plus-square" <c:if test="${icon == 'fa-plus-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plus-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plus-square-o" <c:if test="${icon == 'fa-plus-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-square" <c:if test="${icon == 'fa-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-square-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-square-o" <c:if test="${icon == 'fa-square-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-amex" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-amex" <c:if test="${icon == 'fa-cc-amex'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-diners-club" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-diners-club" <c:if test="${icon == 'fa-cc-diners-club'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-discover" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-discover" <c:if test="${icon == 'fa-cc-discover'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-jcb" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-jcb" <c:if test="${icon == 'fa-cc-jcb'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-mastercard" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-mastercard" <c:if test="${icon == 'fa-cc-mastercard'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-paypal" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-paypal" <c:if test="${icon == 'fa-cc-paypal'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-stripe" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-stripe" <c:if test="${icon == 'fa-cc-stripe'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-visa" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-visa" <c:if test="${icon == 'fa-cc-visa'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-credit-card" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-credit-card" <c:if test="${icon == 'fa-credit-card'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-credit-card-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-credit-card-alt" <c:if test="${icon == 'fa-credit-card-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-google-wallet" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-google-wallet" <c:if test="${icon == 'fa-google-wallet'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-paypal" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-paypal" <c:if test="${icon == 'fa-paypal'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-area-chart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-area-chart" <c:if test="${icon == 'fa-area-chart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bar-chart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bar-chart" <c:if test="${icon == 'fa-bar-chart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bar-chart-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bar-chart-o" <c:if test="${icon == 'fa-bar-chart-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-line-chart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-line-chart" <c:if test="${icon == 'fa-line-chart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pie-chart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pie-chart" <c:if test="${icon == 'fa-pie-chart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bitcoin" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bitcoin" <c:if test="${icon == 'fa-bitcoin'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-btc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-btc" <c:if test="${icon == 'fa-btc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cny" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cny" <c:if test="${icon == 'fa-cny'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-dollar" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-dollar" <c:if test="${icon == 'fa-dollar'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-eur" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-eur" <c:if test="${icon == 'fa-eur'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-euro" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-euro" <c:if test="${icon == 'fa-euro'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gbp" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gbp" <c:if test="${icon == 'fa-gbp'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gg" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gg" <c:if test="${icon == 'fa-gg'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gg-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gg-circle" <c:if test="${icon == 'fa-gg-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ils" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ils" <c:if test="${icon == 'fa-ils'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-inr" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-inr" <c:if test="${icon == 'fa-inr'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-jpy" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-jpy" <c:if test="${icon == 'fa-jpy'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-krw" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-krw" <c:if test="${icon == 'fa-krw'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-money" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-money" <c:if test="${icon == 'fa-money'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rmb" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rmb" <c:if test="${icon == 'fa-rmb'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rouble" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rouble" <c:if test="${icon == 'fa-rouble'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rub" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rub" <c:if test="${icon == 'fa-rub'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ruble" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ruble" <c:if test="${icon == 'fa-ruble'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rupee" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rupee" <c:if test="${icon == 'fa-rupee'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-shekel" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-shekel" <c:if test="${icon == 'fa-shekel'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sheqel" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sheqel" <c:if test="${icon == 'fa-sheqel'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-try" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-try" <c:if test="${icon == 'fa-try'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-turkish-lira" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-turkish-lira" <c:if test="${icon == 'fa-turkish-lira'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-usd" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-usd" <c:if test="${icon == 'fa-usd'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-won" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-won" <c:if test="${icon == 'fa-won'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-yen" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-yen" <c:if test="${icon == 'fa-yen'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-align-center" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-align-center" <c:if test="${icon == 'fa-align-center'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-align-justify" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-align-justify" <c:if test="${icon == 'fa-align-justify'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-align-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-align-left" <c:if test="${icon == 'fa-align-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-align-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-align-right" <c:if test="${icon == 'fa-align-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bold" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bold" <c:if test="${icon == 'fa-bold'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chain" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chain" <c:if test="${icon == 'fa-chain'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chain-broken" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chain-broken" <c:if test="${icon == 'fa-chain-broken'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-clipboard" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-clipboard" <c:if test="${icon == 'fa-clipboard'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-columns" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-columns" <c:if test="${icon == 'fa-columns'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-copy" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-copy" <c:if test="${icon == 'fa-copy'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cut" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cut" <c:if test="${icon == 'fa-cut'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-dedent" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-dedent" <c:if test="${icon == 'fa-dedent'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-eraser" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-eraser" <c:if test="${icon == 'fa-eraser'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file" <c:if test="${icon == 'fa-file'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-o" <c:if test="${icon == 'fa-file-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-text" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-text" <c:if test="${icon == 'fa-file-text'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-file-text-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-file-text-o" <c:if test="${icon == 'fa-file-text-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-files-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-files-o" <c:if test="${icon == 'fa-files-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-floppy-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-floppy-o" <c:if test="${icon == 'fa-floppy-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-font" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-font" <c:if test="${icon == 'fa-font'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-header" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-header" <c:if test="${icon == 'fa-header'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-indent" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-indent" <c:if test="${icon == 'fa-indent'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-italic" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-italic" <c:if test="${icon == 'fa-italic'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-link" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-link" <c:if test="${icon == 'fa-link'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-list" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-list" <c:if test="${icon == 'fa-list'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-list-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-list-alt" <c:if test="${icon == 'fa-list-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-list-ol" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-list-ol" <c:if test="${icon == 'fa-list-ol'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-list-ul" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-list-ul" <c:if test="${icon == 'fa-list-ul'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-outdent" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-outdent" <c:if test="${icon == 'fa-outdent'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-paperclip" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-paperclip" <c:if test="${icon == 'fa-paperclip'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-paragraph" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-paragraph" <c:if test="${icon == 'fa-paragraph'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-paste" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-paste" <c:if test="${icon == 'fa-paste'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-repeat" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-repeat" <c:if test="${icon == 'fa-repeat'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rotate-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rotate-left" <c:if test="${icon == 'fa-rotate-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rotate-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rotate-right" <c:if test="${icon == 'fa-rotate-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-save" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-save" <c:if test="${icon == 'fa-save'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-scissors" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-scissors" <c:if test="${icon == 'fa-scissors'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-strikethrough" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-strikethrough" <c:if test="${icon == 'fa-strikethrough'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-subscript" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-subscript" <c:if test="${icon == 'fa-subscript'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-superscript" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-superscript" <c:if test="${icon == 'fa-superscript'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-table" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-table" <c:if test="${icon == 'fa-table'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-text-height" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-text-height" <c:if test="${icon == 'fa-text-height'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-text-width" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-text-width" <c:if test="${icon == 'fa-text-width'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-th" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-th" <c:if test="${icon == 'fa-th'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-th-large" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-th-large" <c:if test="${icon == 'fa-th-large'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-th-list" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-th-list" <c:if test="${icon == 'fa-th-list'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-underline" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-underline" <c:if test="${icon == 'fa-underline'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-undo" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-undo" <c:if test="${icon == 'fa-undo'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-unlink" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-unlink" <c:if test="${icon == 'fa-unlink'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-angle-double-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-angle-double-down" <c:if test="${icon == 'fa-angle-double-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-angle-double-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-angle-double-left" <c:if test="${icon == 'fa-angle-double-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-angle-double-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-angle-double-right" <c:if test="${icon == 'fa-angle-double-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-angle-double-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-angle-double-up" <c:if test="${icon == 'fa-angle-double-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-angle-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-angle-down" <c:if test="${icon == 'fa-angle-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-angle-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-angle-left" <c:if test="${icon == 'fa-angle-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-angle-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-angle-right" <c:if test="${icon == 'fa-angle-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-angle-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-angle-up" <c:if test="${icon == 'fa-angle-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-circle-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-circle-down" <c:if test="${icon == 'fa-arrow-circle-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-circle-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-circle-left" <c:if test="${icon == 'fa-arrow-circle-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-circle-o-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-circle-o-down" <c:if test="${icon == 'fa-arrow-circle-o-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-circle-o-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-circle-o-left" <c:if test="${icon == 'fa-arrow-circle-o-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-circle-o-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-circle-o-right" <c:if test="${icon == 'fa-arrow-circle-o-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-circle-o-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-circle-o-up" <c:if test="${icon == 'fa-arrow-circle-o-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-circle-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-circle-right" <c:if test="${icon == 'fa-arrow-circle-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-circle-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-circle-up" <c:if test="${icon == 'fa-arrow-circle-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-down" <c:if test="${icon == 'fa-arrow-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-left" <c:if test="${icon == 'fa-arrow-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-right" <c:if test="${icon == 'fa-arrow-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrow-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrow-up" <c:if test="${icon == 'fa-arrow-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrows" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrows" <c:if test="${icon == 'fa-arrows'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrows-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrows-alt" <c:if test="${icon == 'fa-arrows-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrows-h" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrows-h" <c:if test="${icon == 'fa-arrows-h'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrows-v" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrows-v" <c:if test="${icon == 'fa-arrows-v'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-down" <c:if test="${icon == 'fa-caret-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-left" <c:if test="${icon == 'fa-caret-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-right" <c:if test="${icon == 'fa-caret-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-square-o-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-square-o-down" <c:if test="${icon == 'fa-caret-square-o-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-square-o-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-square-o-left" <c:if test="${icon == 'fa-caret-square-o-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-square-o-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-square-o-right" <c:if test="${icon == 'fa-caret-square-o-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-square-o-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-square-o-up" <c:if test="${icon == 'fa-caret-square-o-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-caret-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-caret-up" <c:if test="${icon == 'fa-caret-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chevron-circle-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chevron-circle-down" <c:if test="${icon == 'fa-chevron-circle-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chevron-circle-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chevron-circle-left" <c:if test="${icon == 'fa-chevron-circle-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chevron-circle-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chevron-circle-right" <c:if test="${icon == 'fa-chevron-circle-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chevron-circle-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chevron-circle-up" <c:if test="${icon == 'fa-chevron-circle-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chevron-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chevron-down" <c:if test="${icon == 'fa-chevron-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chevron-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chevron-left" <c:if test="${icon == 'fa-chevron-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chevron-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chevron-right" <c:if test="${icon == 'fa-chevron-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chevron-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chevron-up" <c:if test="${icon == 'fa-chevron-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-exchange" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-exchange" <c:if test="${icon == 'fa-exchange'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-o-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-o-down" <c:if test="${icon == 'fa-hand-o-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-o-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-o-left" <c:if test="${icon == 'fa-hand-o-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-o-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-o-right" <c:if test="${icon == 'fa-hand-o-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hand-o-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hand-o-up" <c:if test="${icon == 'fa-hand-o-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-long-arrow-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-long-arrow-down" <c:if test="${icon == 'fa-long-arrow-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-long-arrow-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-long-arrow-left" <c:if test="${icon == 'fa-long-arrow-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-long-arrow-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-long-arrow-right" <c:if test="${icon == 'fa-long-arrow-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-long-arrow-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-long-arrow-up" <c:if test="${icon == 'fa-long-arrow-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-down" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-down" <c:if test="${icon == 'fa-toggle-down'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-left" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-left" <c:if test="${icon == 'fa-toggle-left'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-right" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-right" <c:if test="${icon == 'fa-toggle-right'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-toggle-up" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-toggle-up" <c:if test="${icon == 'fa-toggle-up'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-arrows-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-arrows-alt" <c:if test="${icon == 'fa-arrows-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-backward" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-backward" <c:if test="${icon == 'fa-backward'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-compress" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-compress" <c:if test="${icon == 'fa-compress'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-eject" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-eject" <c:if test="${icon == 'fa-eject'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-expand" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-expand" <c:if test="${icon == 'fa-expand'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fast-backward" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fast-backward" <c:if test="${icon == 'fa-fast-backward'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fast-forward" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fast-forward" <c:if test="${icon == 'fa-fast-forward'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-forward" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-forward" <c:if test="${icon == 'fa-forward'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pause" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pause" <c:if test="${icon == 'fa-pause'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pause-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pause-circle" <c:if test="${icon == 'fa-pause-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pause-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pause-circle-o" <c:if test="${icon == 'fa-pause-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-play" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-play" <c:if test="${icon == 'fa-play'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-play-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-play-circle" <c:if test="${icon == 'fa-play-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-play-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-play-circle-o" <c:if test="${icon == 'fa-play-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-random" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-random" <c:if test="${icon == 'fa-random'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-step-backward" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-step-backward" <c:if test="${icon == 'fa-step-backward'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-step-forward" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-step-forward" <c:if test="${icon == 'fa-step-forward'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-stop" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-stop" <c:if test="${icon == 'fa-stop'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-stop-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-stop-circle" <c:if test="${icon == 'fa-stop-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-stop-circle-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-stop-circle-o" <c:if test="${icon == 'fa-stop-circle-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-youtube-play" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-youtube-play" <c:if test="${icon == 'fa-youtube-play'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-500px" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-500px" <c:if test="${icon == 'fa-500px'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-adn" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-adn" <c:if test="${icon == 'fa-adn'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-amazon" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-amazon" <c:if test="${icon == 'fa-amazon'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-android" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-android" <c:if test="${icon == 'fa-android'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-angellist" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-angellist" <c:if test="${icon == 'fa-angellist'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-apple" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-apple" <c:if test="${icon == 'fa-apple'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bandcamp" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bandcamp" <c:if test="${icon == 'fa-bandcamp'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-behance" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-behance" <c:if test="${icon == 'fa-behance'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-behance-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-behance-square" <c:if test="${icon == 'fa-behance-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bitbucket" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bitbucket" <c:if test="${icon == 'fa-bitbucket'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bitbucket-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bitbucket-square" <c:if test="${icon == 'fa-bitbucket-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bitcoin" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bitcoin" <c:if test="${icon == 'fa-bitcoin'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-black-tie" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-black-tie" <c:if test="${icon == 'fa-black-tie'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bluetooth" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bluetooth" <c:if test="${icon == 'fa-bluetooth'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-bluetooth-b" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-bluetooth-b" <c:if test="${icon == 'fa-bluetooth-b'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-btc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-btc" <c:if test="${icon == 'fa-btc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-buysellads" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-buysellads" <c:if test="${icon == 'fa-buysellads'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-amex" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-amex" <c:if test="${icon == 'fa-cc-amex'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-diners-club" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-diners-club" <c:if test="${icon == 'fa-cc-diners-club'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-discover" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-discover" <c:if test="${icon == 'fa-cc-discover'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-jcb" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-jcb" <c:if test="${icon == 'fa-cc-jcb'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-mastercard" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-mastercard" <c:if test="${icon == 'fa-cc-mastercard'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-paypal" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-paypal" <c:if test="${icon == 'fa-cc-paypal'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-stripe" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-stripe" <c:if test="${icon == 'fa-cc-stripe'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-cc-visa" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-cc-visa" <c:if test="${icon == 'fa-cc-visa'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-chrome" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-chrome" <c:if test="${icon == 'fa-chrome'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-codepen" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-codepen" <c:if test="${icon == 'fa-codepen'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-codiepie" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-codiepie" <c:if test="${icon == 'fa-codiepie'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-connectdevelop" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-connectdevelop" <c:if test="${icon == 'fa-connectdevelop'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-contao" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-contao" <c:if test="${icon == 'fa-contao'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-css3" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-css3" <c:if test="${icon == 'fa-css3'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-dashcube" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-dashcube" <c:if test="${icon == 'fa-dashcube'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-delicious" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-delicious" <c:if test="${icon == 'fa-delicious'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-deviantart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-deviantart" <c:if test="${icon == 'fa-deviantart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-digg" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-digg" <c:if test="${icon == 'fa-digg'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-dribbble" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-dribbble" <c:if test="${icon == 'fa-dribbble'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-dropbox" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-dropbox" <c:if test="${icon == 'fa-dropbox'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-drupal" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-drupal" <c:if test="${icon == 'fa-drupal'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-edge" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-edge" <c:if test="${icon == 'fa-edge'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-eercast" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-eercast" <c:if test="${icon == 'fa-eercast'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-empire" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-empire" <c:if test="${icon == 'fa-empire'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-envira" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-envira" <c:if test="${icon == 'fa-envira'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-etsy" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-etsy" <c:if test="${icon == 'fa-etsy'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-expeditedssl" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-expeditedssl" <c:if test="${icon == 'fa-expeditedssl'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fa" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fa" <c:if test="${icon == 'fa-fa'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-facebook" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-facebook" <c:if test="${icon == 'fa-facebook'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-facebook-f" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-facebook-f" <c:if test="${icon == 'fa-facebook-f'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-facebook-official" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-facebook-official" <c:if test="${icon == 'fa-facebook-official'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-facebook-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-facebook-square" <c:if test="${icon == 'fa-facebook-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-firefox" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-firefox" <c:if test="${icon == 'fa-firefox'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-first-order" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-first-order" <c:if test="${icon == 'fa-first-order'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-flickr" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-flickr" <c:if test="${icon == 'fa-flickr'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-font-awesome" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-font-awesome" <c:if test="${icon == 'fa-font-awesome'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fonticons" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fonticons" <c:if test="${icon == 'fa-fonticons'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-fort-awesome" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-fort-awesome" <c:if test="${icon == 'fa-fort-awesome'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-forumbee" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-forumbee" <c:if test="${icon == 'fa-forumbee'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-foursquare" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-foursquare" <c:if test="${icon == 'fa-foursquare'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-free-code-camp" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-free-code-camp" <c:if test="${icon == 'fa-free-code-camp'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ge" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ge" <c:if test="${icon == 'fa-ge'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-get-pocket" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-get-pocket" <c:if test="${icon == 'fa-get-pocket'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gg" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gg" <c:if test="${icon == 'fa-gg'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gg-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gg-circle" <c:if test="${icon == 'fa-gg-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-git" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-git" <c:if test="${icon == 'fa-git'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-git-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-git-square" <c:if test="${icon == 'fa-git-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-github" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-github" <c:if test="${icon == 'fa-github'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-github-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-github-alt" <c:if test="${icon == 'fa-github-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-github-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-github-square" <c:if test="${icon == 'fa-github-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gitlab" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gitlab" <c:if test="${icon == 'fa-gitlab'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gittip" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gittip" <c:if test="${icon == 'fa-gittip'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-glide" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-glide" <c:if test="${icon == 'fa-glide'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-glide-g" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-glide-g" <c:if test="${icon == 'fa-glide-g'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-google" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-google" <c:if test="${icon == 'fa-google'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-google-plus" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-google-plus" <c:if test="${icon == 'fa-google-plus'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-google-plus-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-google-plus-circle" <c:if test="${icon == 'fa-google-plus-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-google-plus-official" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-google-plus-official" <c:if test="${icon == 'fa-google-plus-official'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-google-plus-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-google-plus-square" <c:if test="${icon == 'fa-google-plus-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-google-wallet" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-google-wallet" <c:if test="${icon == 'fa-google-wallet'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-gratipay" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-gratipay" <c:if test="${icon == 'fa-gratipay'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-grav" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-grav" <c:if test="${icon == 'fa-grav'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hacker-news" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hacker-news" <c:if test="${icon == 'fa-hacker-news'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-houzz" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-houzz" <c:if test="${icon == 'fa-houzz'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-html5" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-html5" <c:if test="${icon == 'fa-html5'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-imdb" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-imdb" <c:if test="${icon == 'fa-imdb'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-instagram" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-instagram" <c:if test="${icon == 'fa-instagram'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-internet-explorer" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-internet-explorer" <c:if test="${icon == 'fa-internet-explorer'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ioxhost" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ioxhost" <c:if test="${icon == 'fa-ioxhost'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-joomla" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-joomla" <c:if test="${icon == 'fa-joomla'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-jsfiddle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-jsfiddle" <c:if test="${icon == 'fa-jsfiddle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-lastfm" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-lastfm" <c:if test="${icon == 'fa-lastfm'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-lastfm-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-lastfm-square" <c:if test="${icon == 'fa-lastfm-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-leanpub" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-leanpub" <c:if test="${icon == 'fa-leanpub'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-linkedin" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-linkedin" <c:if test="${icon == 'fa-linkedin'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-linkedin-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-linkedin-square" <c:if test="${icon == 'fa-linkedin-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-linode" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-linode" <c:if test="${icon == 'fa-linode'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-linux" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-linux" <c:if test="${icon == 'fa-linux'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-maxcdn" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-maxcdn" <c:if test="${icon == 'fa-maxcdn'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-meanpath" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-meanpath" <c:if test="${icon == 'fa-meanpath'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-medium" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-medium" <c:if test="${icon == 'fa-medium'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-meetup" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-meetup" <c:if test="${icon == 'fa-meetup'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-mixcloud" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-mixcloud" <c:if test="${icon == 'fa-mixcloud'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-modx" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-modx" <c:if test="${icon == 'fa-modx'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-odnoklassniki" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-odnoklassniki" <c:if test="${icon == 'fa-odnoklassniki'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-odnoklassniki-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-odnoklassniki-square" <c:if test="${icon == 'fa-odnoklassniki-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-opencart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-opencart" <c:if test="${icon == 'fa-opencart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-openid" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-openid" <c:if test="${icon == 'fa-openid'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-opera" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-opera" <c:if test="${icon == 'fa-opera'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-optin-monster" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-optin-monster" <c:if test="${icon == 'fa-optin-monster'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pagelines" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pagelines" <c:if test="${icon == 'fa-pagelines'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-paypal" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-paypal" <c:if test="${icon == 'fa-paypal'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pied-piper" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pied-piper" <c:if test="${icon == 'fa-pied-piper'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pied-piper-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pied-piper-alt" <c:if test="${icon == 'fa-pied-piper-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pied-piper-pp" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pied-piper-pp" <c:if test="${icon == 'fa-pied-piper-pp'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pinterest" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pinterest" <c:if test="${icon == 'fa-pinterest'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pinterest-p" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pinterest-p" <c:if test="${icon == 'fa-pinterest-p'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-pinterest-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-pinterest-square" <c:if test="${icon == 'fa-pinterest-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-product-hunt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-product-hunt" <c:if test="${icon == 'fa-product-hunt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-qq" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-qq" <c:if test="${icon == 'fa-qq'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-quora" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-quora" <c:if test="${icon == 'fa-quora'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ra" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ra" <c:if test="${icon == 'fa-ra'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ravelry" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ravelry" <c:if test="${icon == 'fa-ravelry'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-rebel" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-rebel" <c:if test="${icon == 'fa-rebel'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-reddit" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-reddit" <c:if test="${icon == 'fa-reddit'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-reddit-alien" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-reddit-alien" <c:if test="${icon == 'fa-reddit-alien'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-reddit-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-reddit-square" <c:if test="${icon == 'fa-reddit-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-renren" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-renren" <c:if test="${icon == 'fa-renren'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-resistance" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-resistance" <c:if test="${icon == 'fa-resistance'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-safari" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-safari" <c:if test="${icon == 'fa-safari'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-scribd" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-scribd" <c:if test="${icon == 'fa-scribd'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-sellsy" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-sellsy" <c:if test="${icon == 'fa-sellsy'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-share-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-share-alt" <c:if test="${icon == 'fa-share-alt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-share-alt-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-share-alt-square" <c:if test="${icon == 'fa-share-alt-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-shirtsinbulk" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-shirtsinbulk" <c:if test="${icon == 'fa-shirtsinbulk'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-simplybuilt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-simplybuilt" <c:if test="${icon == 'fa-simplybuilt'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-skyatlas" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-skyatlas" <c:if test="${icon == 'fa-skyatlas'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-skype" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-skype" <c:if test="${icon == 'fa-skype'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-slack" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-slack" <c:if test="${icon == 'fa-slack'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-slideshare" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-slideshare" <c:if test="${icon == 'fa-slideshare'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-snapchat" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-snapchat" <c:if test="${icon == 'fa-snapchat'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-snapchat-ghost" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-snapchat-ghost" <c:if test="${icon == 'fa-snapchat-ghost'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-snapchat-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-snapchat-square" <c:if test="${icon == 'fa-snapchat-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-soundcloud" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-soundcloud" <c:if test="${icon == 'fa-soundcloud'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-spotify" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-spotify" <c:if test="${icon == 'fa-spotify'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-stack-exchange" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-stack-exchange" <c:if test="${icon == 'fa-stack-exchange'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-stack-overflow" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-stack-overflow" <c:if test="${icon == 'fa-stack-overflow'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-steam" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-steam" <c:if test="${icon == 'fa-steam'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-steam-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-steam-square" <c:if test="${icon == 'fa-steam-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-stumbleupon" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-stumbleupon" <c:if test="${icon == 'fa-stumbleupon'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-stumbleupon-circle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-stumbleupon-circle" <c:if test="${icon == 'fa-stumbleupon-circle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-superpowers" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-superpowers" <c:if test="${icon == 'fa-superpowers'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-telegram" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-telegram" <c:if test="${icon == 'fa-telegram'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tencent-weibo" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tencent-weibo" <c:if test="${icon == 'fa-tencent-weibo'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-themeisle" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-themeisle" <c:if test="${icon == 'fa-themeisle'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-trello" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-trello" <c:if test="${icon == 'fa-trello'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tripadvisor" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tripadvisor" <c:if test="${icon == 'fa-tripadvisor'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tumblr" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tumblr" <c:if test="${icon == 'fa-tumblr'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-tumblr-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-tumblr-square" <c:if test="${icon == 'fa-tumblr-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-twitch" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-twitch" <c:if test="${icon == 'fa-twitch'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-twitter" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-twitter" <c:if test="${icon == 'fa-twitter'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-twitter-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-twitter-square" <c:if test="${icon == 'fa-twitter-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-usb" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-usb" <c:if test="${icon == 'fa-usb'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-viacoin" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-viacoin" <c:if test="${icon == 'fa-viacoin'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-viadeo" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-viadeo" <c:if test="${icon == 'fa-viadeo'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-viadeo-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-viadeo-square" <c:if test="${icon == 'fa-viadeo-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-vimeo" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-vimeo" <c:if test="${icon == 'fa-vimeo'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-vimeo-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-vimeo-square" <c:if test="${icon == 'fa-vimeo-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-vine" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-vine" <c:if test="${icon == 'fa-vine'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-vk" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-vk" <c:if test="${icon == 'fa-vk'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wechat" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wechat" <c:if test="${icon == 'fa-wechat'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-weibo" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-weibo" <c:if test="${icon == 'fa-weibo'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-weixin" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-weixin" <c:if test="${icon == 'fa-weixin'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-whatsapp" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-whatsapp" <c:if test="${icon == 'fa-whatsapp'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wikipedia-w" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wikipedia-w" <c:if test="${icon == 'fa-wikipedia-w'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-windows" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-windows" <c:if test="${icon == 'fa-windows'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wordpress" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wordpress" <c:if test="${icon == 'fa-wordpress'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wpbeginner" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wpbeginner" <c:if test="${icon == 'fa-wpbeginner'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wpexplorer" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wpexplorer" <c:if test="${icon == 'fa-wpexplorer'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wpforms" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wpforms" <c:if test="${icon == 'fa-wpforms'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-xing" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-xing" <c:if test="${icon == 'fa-xing'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-xing-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-xing-square" <c:if test="${icon == 'fa-xing-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-y-combinator" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-y-combinator" <c:if test="${icon == 'fa-y-combinator'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-y-combinator-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-y-combinator-square" <c:if test="${icon == 'fa-y-combinator-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-yahoo" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-yahoo" <c:if test="${icon == 'fa-yahoo'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-yc" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-yc" <c:if test="${icon == 'fa-yc'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-yc-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-yc-square" <c:if test="${icon == 'fa-yc-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-yelp" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-yelp" <c:if test="${icon == 'fa-yelp'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-yoast" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-yoast" <c:if test="${icon == 'fa-yoast'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-youtube" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-youtube" <c:if test="${icon == 'fa-youtube'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-youtube-play" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-youtube-play" <c:if test="${icon == 'fa-youtube-play'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-youtube-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-youtube-square" <c:if test="${icon == 'fa-youtube-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-ambulance" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-ambulance" <c:if test="${icon == 'fa-ambulance'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-h-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-h-square" <c:if test="${icon == 'fa-h-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-heart" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-heart" <c:if test="${icon == 'fa-heart'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-heart-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-heart-o" <c:if test="${icon == 'fa-heart-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-heartbeat" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-heartbeat" <c:if test="${icon == 'fa-heartbeat'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-hospital-o" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-hospital-o" <c:if test="${icon == 'fa-hospital-o'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-medkit" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-medkit" <c:if test="${icon == 'fa-medkit'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-plus-square" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-plus-square" <c:if test="${icon == 'fa-plus-square'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-stethoscope" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-stethoscope" <c:if test="${icon == 'fa-stethoscope'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-user-md" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-user-md" <c:if test="${icon == 'fa-user-md'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wheelchair" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wheelchair" <c:if test="${icon == 'fa-wheelchair'}">checked</c:if>>
								</div></li>
							<li><i class="fa fa-wheelchair-alt" style="font-size: 60px;"></i>
							<div class="doc-icon-fontclass">
									<input type="radio" name="icon" value="fa-wheelchair-alt" <c:if test="${icon == 'fa-wheelchair-alt'}">checked</c:if>>
								</div></li>

						</ul>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="12">
							<smart:buttonGroup container="true">
								<button class="layui-btn layui-btn-sm" type="button" lay-submit lay-filter="smart-submit" title="确认">
									<i class="fa fa-check"></i>&nbsp;确认
								</button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="form,layer,element">
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			var mask = layer.load(1, {
				 shade: [0.5,'#fff']
			});
			$.post('resource/menu/icon/save',
				obj.field,
				function(result) {	
					layer.close(mask);
					if (result.success) {
						layer.msg(result.message, {
							time: 1000
							,icon:1
							,anim:5
						});
						parent.updateMenu(result.data);
						parent.layer.close(index);
					} else {							
						layer.msg(result.message, {
							time:1000
							,icon:2
							,anim:6
						});
					}
				},'json');
			});
		</smart:scriptHead>
</smart:body>