/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarFila(seccionId){
    if(seccionId === 1) addRowUnidad(seccionId);
    else addRowContent(seccionId);
}

function addRowUnidad(seccionId) {
    var rta = "<tr>";
    var tabla = document.getElementById("tabla" + seccionId);
    var max = getMaxCols("tabla" + seccionId);
    var filas = tabla.rows.length;
    var inputIn, inputOut;
    var disabled = "";
    for (var i = 0; i < max; i++) {
        var key = seccionId+"-"+(filas - 1)+"-"+i;
        if(i!==1){
            inputIn = `input type="text" id="`+key+`" onkeyup="sumFields('`+key+`')" onkeypress="return validate(event,'`+key+`')" value=`+(i==0 ? filas : 0)+` `;
            inputOut = " >";
        }else{
            inputIn = "textarea ";
            inputOut = " ></textarea>";
        }
        disabled = (i===0 || i===4) ? "readonly" : ""; 
        rta += `<td><div class='form-group'><`+inputIn+` class="form-control form-micro" name="contenido-${seccionId }-${filas - 1}-${i}"`+disabled+inputOut+`</td></div>`;
    }
    rta += "</tr>";
    tabla.insertRow(-1).innerHTML = rta;
    addNumRowToSelect(tabla.rows.length - 1);
    document.getElementById("nfilas-" + seccionId).value = tabla.rows.length - 1;
}

function addNumRowToSelect(numRow){
    var table = document.getElementById("tabla5");
    for(let i=0; i<table.rows.length; i++){
        $('#contenido-5-'+i+'-0').append(new Option(numRow, numRow));
    }
}

function addRowContent(seccionId) {
    var rta = "<tr>";
    var tabla = document.getElementById("tabla" + seccionId);
    var max = getMaxCols("tabla" + seccionId);
    var filas = tabla.rows.length;
    var key;
    rta += "<td>"+getSelect(seccionId, filas-1)+"</td>";
    for (var i = 1; i < max; i++) {
        key = seccionId+"-"+(filas - 1)+"-"+i;
        rta += `<td><div class='form-group'><textarea class="form-control" style="display:block;width:100%;border:none;margin:auto auto;text-align:center;" name="contenido-${key}"></textarea></td></div>`;
    }
    rta += "</tr>";
    tabla.insertRow(-1).innerHTML = rta;
    document.getElementById("nfilas-" + seccionId).value = tabla.rows.length - 1;
}

function getSelect(idSeccion, lastRow){
    var key = "contenido-"+idSeccion+"-"+lastRow+"-0";
    var tabla = document.getElementById("tabla1");
    var body = "";
    for(let i=1; i<tabla.rows.length; i++){
        body += "<option value='"+i+"'>"+i+"</option>";
    }
    return "<select id='"+key+"' name='"+key+"'>"+body+"</select>";
}

function getMaxCols(idTabla){
    var colsHeader = $("#" + idTabla  + " tr:last th").length;
    var colsRow = $("#" + idTabla     + " tr:last td").length;
    return Math.max(colsHeader, colsRow);
}

function eliminarFila(seccionId) {
    var table = document.getElementById("tabla" + seccionId);
    var rowCount = table.rows.length;
    document.getElementById("nfilas-" + seccionId).value = rowCount - 2;
    if (rowCount <= 1){
        alert('No se puede eliminar el encabezado');
    }else{
        table.deleteRow(rowCount - 1);
        if(seccionId===1)deleteNumRowFromSelect(rowCount - 1);
    }
}

function deleteNumRowFromSelect(numRow){
    var table = document.getElementById("tabla5");
    for(let i=0; i<table.rows.length; i++){
        $("#contenido-5-"+i+"-0 option[value='"+numRow+"']").remove();
    }
}
     