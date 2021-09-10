/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function agregarFila(seccionId) {
    var rta = "<tr>";
    var tabla = document.getElementById("tabla" + seccionId);
    var nColumnas = $("#tabla" + seccionId + " tr:last th").length;
    var nColumnas2 = $("#tabla" + seccionId + " tr:last td").length;
    var filas = tabla.rows.length;
    var inputIn, inputOut;
    var disabled = "";
    var max = Math.max(nColumnas, nColumnas2);
    for (var i = 0; i < max; i++) {
        var key = seccionId+"-"+(filas - 1)+"-"+i;
        if(i!=1 && seccionId==1){
            inputIn = `input type="text" id="`+key+`" onkeyup="sumFields('`+key+`')" onkeypress="return validate(event,'`+key+`')" value=`+(i==0 ? filas : 0)+` `;
            inputOut = " >";
        }else{
            inputIn = "textarea ";
            inputOut = " ></textarea>";
        }
        disabled = (i==0 || i==4) && seccionId==1 ? "readonly" : ""; 
        rta += `<td><div class='form-group'><`+inputIn+` class="form-control" style="display:block;width:100%;border:none;margin:auto auto;text-align:center;" name="contenido-${seccionId }-${filas - 1}-${i}"`+disabled+inputOut+`</td></div>`;
    }
    rta += "</tr>"
    tabla.insertRow(-1).innerHTML = rta;
    document.getElementById("nfilas-" + seccionId).value = tabla.rows.length - 1;
}

function eliminarFila(seccionId) {
    var table = document.getElementById("tabla" + seccionId);
    var rowCount = table.rows.length;
    //console.log(rowCount);
    document.getElementById("nfilas-" + seccionId).value = rowCount - 2
    if (rowCount <= 1)
        alert('No se puede eliminar el encabezado');
    else
        table.deleteRow(rowCount - 1);
}
     