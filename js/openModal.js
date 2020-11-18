var identifierTagMap = new Map();
$(document).ready(function() {
    var modalDiv = $('<div></div>').attr('id', 'infoModalId').attr('class', 'infoModalClass');
    modalDiv.appendTo('body');

});

function setFetchDataBaseUrl(baseUrl,module){
    $.get(baseUrl+'informationModal/moduleModalInfo?moduleName='+module, // url
            function(data, textStatus, jqXHR) { // success callback
        		for(i=0; i<data.moduleModalInfos.dealer.modalInfos.length;i++){
        			identifierTagMap[data.moduleModalInfos.dealer.modalInfos[i].identifierTag] = data.moduleModalInfos.dealer.modalInfos[i];
        		}
            });
}

function openModalPopup(element) {
    var id = element.getAttribute("data-identifier");
    var windowHeight= $(window).height();
    var widowWidth = $(window).width();
    var topPos = element.getBoundingClientRect().top + window.scrollY;
    var leftPos = element.getBoundingClientRect().left + window.scrollX;
    if((topPos+identifierTagMap[id].modalHeightInPx)>windowHeight){
    	if(topPos>identifierTagMap[id].modalHeightInPx){
    		topPos = topPos-identifierTagMap[id].modalHeightInPx+10;
    	} else {
    		topPos =0;
    	}
    }
    if((leftPos+identifierTagMap[id].modalWidthInPx)>widowWidth){
    	if(leftPos>identifierTagMap[id].modalWidthInPx){
    		leftPos = leftPos-identifierTagMap[id].modalWidthInPx+10;
    	} else {
    		leftPos = 0;
    	}
    }
    var large = '<div class="informationModal" id="informationModalId" style="left: '+leftPos+'px; top: '+topPos+'px; padding: 0pt; visibility: visible; position: absolute; overflow: visible;">'+
    	'<div><table cellspacing="0" cellpadding="0" style="width: 100%; height: 100%;"><tbody><tr>'+
    	'<td align="left" style="vertical-align: top;"><table cellspacing="0" cellpadding="0" class="mk-popup-title-panel" style="width: 100%;">'+
    	'<tbody><tr><td align="left" style="vertical-align: middle;"><div class="mk-h6">'+identifierTagMap[id].modalHeader+
    	'</div></td><td align="right" style="vertical-align: middle;"><div class="mk-popup-close-icon" onclick="hideModalPopup(this)" title="Close">'+
    	'</div></td></tr></tbody></table></td></tr><tr><td align="left" style="vertical-align: top;">'+
    	'<table cellspacing="0" cellpadding="0" class="informationModalPanel"><tbody><tr><td align="center" style="vertical-align: middle;">'+
    	'<table cellspacing="0" cellpadding="0" class="informationModalVpPanel"><tbody><tr><td align="left" style="vertical-align: top;">'+
    	(identifierTagMap[id].modalContentType=='URL'?
    	'<iframe class="gwt-Frame informationIframePanel" id="informationIframePanelId" src="'+identifierTagMap[id].modalContent+'"></iframe>':identifierTagMap[id].modalContent) +
    	'</td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></div></div>';
    
    $("#infoModalId").empty();
    $("#infoModalId").append(large);
    $("#infoModalId").css("display", "block");
    $("#informationModalId").css("width", identifierTagMap[id].modalWidthInPx+'px');
    $("#informationIframePanelId").css("height", identifierTagMap[id].modalHeightInPx+'px');
}

function showTitle(element) {
    if (element.childNodes.length==1) {
    	var span = document.createElement('span');
    	span.innerHTML = identifierTagMap[element.getAttribute("data-identifier")].altText;
    	span.classList.add('infotooltiptext');
    	element.appendChild(span);
    }
}

function hideModalPopup(element) {
    $("#infoModalId").css("display", "none");
}